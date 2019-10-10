package com.xyz.sql.opt;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.TypeUtil;
import com.google.common.collect.Lists;
import com.xyz.sql.entity.ColumnMeta;
import com.xyz.sql.entity.TableMeta;
import com.xyz.sql.util.SqlUtil;
import com.xyz.util.IdWorkerUtil;
import com.xyz.util.SpringBeanUtil;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class ImportExcel {

    public static void main(String[] args) throws IOException {

        InputStream inputStream = null;
        try {
            inputStream = ImportExcel.class.getClassLoader().getResourceAsStream("file2.xlsx");
            List<ColumnMeta> bizColumns = SqlUtil.TableUtil.getBizColumns("sxc_inlet_table");
            TableMeta tableMeta = new TableMeta();
            tableMeta.setTableName("sxc_inlet_table");
            tableMeta.setColumns(bizColumns);
            ExcelReader reader = EasyExcelFactory.getReader(inputStream, new ImportSqlEventListener(tableMeta, 56));
            reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }

    }
}

class ImportSqlEventListener extends AnalysisEventListener<List<String>> {

    private TableMeta tableMeta;

    private Integer operatorId;

    ImportSqlEventListener(TableMeta tableMeta, Integer operatorId) {
        this.tableMeta = tableMeta;
        this.operatorId = operatorId;
    }

    private JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean("jdbcTemplate");

    private List<List<String>> rows = Lists.newArrayList();

    @Override
    public void invoke(List<String> rowData, AnalysisContext context) {
        if (context.getCurrentRowNum() == 0) {
            return;
        }

        if (rowData.get(0) == null) {
            return;
        }

        rows.add(rowData);
    }

    private String genPrepareInsertSql(String tableName, List<ColumnMeta> bizColumns) {
        StringBuilder sb = new StringBuilder();

        String insertSql = "insert into " + tableName;
        sb.append(insertSql);

        sb.append("(");

        for (int i = 0; i < bizColumns.size(); i++) {
            ColumnMeta columnMeta = bizColumns.get(i);
            if (i == 0) {
                sb.append("`").append(columnMeta.getColumnName()).append("`");
            } else {
                sb.append(",`").append(columnMeta.getColumnName()).append("`");
            }
        }

        List<ColumnMeta> footColumns = SqlUtil.TableUtil.getFootColumns();
        for (ColumnMeta footColumnMeta : footColumns) {
            sb.append(",`").append(footColumnMeta.getColumnName()).append("`");
        }

        sb.append(") values (");

        for (int i = 0; i < bizColumns.size() + footColumns.size(); i++) {
            if (i == 0) {
                sb.append("?");
            } else {
                sb.append(",?");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        String tableName = tableMeta.getTableName();
        List<ColumnMeta> bizColumns = tableMeta.getColumns();
        String prepareInsertSql = genPrepareInsertSql(tableName, bizColumns);
        String importNum = IdWorkerUtil.getInstance().createUUID();

        // 搜集数据完成, 数量过多每次 500 导入
        final int batchSize = 500;

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex += batchSize) {

            final List<List<String>> batchList = rows.subList(rowIndex,
                    rowIndex + batchSize > rows.size() ? rows.size() : rowIndex + batchSize);

            jdbcTemplate.batchUpdate(prepareInsertSql, new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int rowPosition) throws SQLException {
                    List<String> rowData = rows.get(rowPosition);
                    // excel 的数据
                    for (int columnIndex = 0; columnIndex < rowData.size(); columnIndex++) {
                        String dataStr = rowData.get(columnIndex);
                        if (!StringUtils.hasText(dataStr)) {
                            continue;
                        }

                        ColumnMeta columnMeta = bizColumns.get(columnIndex);
                        if (columnMeta.getDataType() == Types.INTEGER) {
                            int cell = Integer.parseInt(dataStr);
                            ps.setInt(columnIndex + 1, cell);
                        } else if (columnMeta.getDataType() == Types.TIMESTAMP) {
                            Date cell;
                            if (dataStr.contains("-") || dataStr.contains("/") || dataStr.contains(":")) {
                                cell = TypeUtil.getSimpleDateFormatDate(dataStr, "yyyy-MM-dd HH:mm:ss");
                            } else {
                                double d = Double.parseDouble(dataStr);
                                cell = HSSFDateUtil.getJavaDate(d, false);
                            }
                            ps.setTimestamp(columnIndex + 1, Timestamp.from(cell.toInstant()));
                        } else {
                            ps.setString(columnIndex + 1, dataStr);
                        }
                    }

                    // 透传参数还是没法抽取到配置中心，不宕机启动啊，因为这里需要设置值
                    decorateFootColumnArgs(ps, bizColumns.size(),
                            Lists.newArrayList(importNum,
                                    operatorId,
                                    1,
                                    Timestamp.from(Instant.now()),
                                    Timestamp.from(Instant.now())));
                }

                @Override
                public int getBatchSize() {
                    return batchList.size();
                }
            });

        }
    }

    /**
     * description: 装饰透传列的数据参数
     *
     * @param ps
     * @param bizColumnsSize
     * @param footColumnArgs
     * @return void
     * @author: xyz
     * @create: 2019-03-11
     */
    private void decorateFootColumnArgs(PreparedStatement ps, int bizColumnsSize, List footColumnArgs) throws SQLException {
        for (int i = 0; i < footColumnArgs.size(); i++) {
            ps.setObject(bizColumnsSize + i + 1, footColumnArgs.get(i));
        }
    }
}
