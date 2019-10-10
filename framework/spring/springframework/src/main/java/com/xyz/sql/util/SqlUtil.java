package com.xyz.sql.util;

import com.alibaba.fastjson.JSON;
import com.xyz.sql.config.ConfigCenter;
import com.xyz.sql.entity.ColumnMeta;
import com.xyz.sql.entity.TableMeta;
import com.xyz.util.SpringBeanUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlUtil {

    public static class ColumnUtil {


        public static String getDataTypeNameSql(int dataType) {
            return getDataTypeNameSql(dataType, ConfigCenter.DATATYPE_DEFAULT_LENGTH_MAP.get(dataType));
        }

        public static String getDataTypeNameSql(int dataType, int length) {
            String dataTypeSql = ConfigCenter.BI_DATATYPE_MAP.get(dataType);
            if (shouldAddLength(dataType)) {
                dataTypeSql = dataTypeSql + "(" + length + ")";
            }
            return dataTypeSql;
        }

        public static int parseDataType(String dataTypeName) {
            return ConfigCenter.BI_DATATYPE_MAP.inverse().get(dataTypeName);
        }


        private static boolean shouldAddLength(int dataType) {
            return ConfigCenter.DATATYPE_DEFAULT_LENGTH_MAP.get(dataType) > 0;
        }
    }

    public static class TableUtil {

        public static String genCreateTableSql(String tableJson) {
            TableMeta tableMeta = JSON.parseObject(tableJson, TableMeta.class);
            String tableName = tableMeta.getTableName();
            List<ColumnMeta> columns = tableMeta.getColumns();
            String comment = tableMeta.getComment();

            String commentSql = Optional.ofNullable(comment).orElse("导入表");

            StringBuilder sb = new StringBuilder();

            String headSql = String.format("CREATE TABLE `%s` (\n", tableName);
            sb.append(headSql);

            // 添加主键 sql 语句
            String pkSql = "`id` INT(11) NOT NULL AUTO_INCREMENT,\n";
            sb.append(pkSql);

            for (ColumnMeta column : columns) {
                String columnName = column.getColumnName();
                String dataTypeSql = SqlUtil.ColumnUtil.getDataTypeNameSql(column.getDataType(),
                        column.getDataTypeLength());
                String columnComment = Optional.ofNullable(column.getComment()).orElse("");
                String columnSql = String.format("`%s` %s COMMENT '%s',\n", columnName, dataTypeSql, columnComment);
                sb.append(columnSql);
            }

            List<ColumnMeta> footColumns = getFootColumns();
            for (ColumnMeta footColumn : footColumns) {
                sb.append("`").append(footColumn.getColumnName()).append("` ")
                        .append(SqlUtil.ColumnUtil.getDataTypeNameSql(footColumn.getDataType(),
                                footColumn.getDataTypeLength())).append(" NULL")
                        .append(" COMMENT '").append(footColumn.getComment()).append("',\n");
            }

            sb.append("PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='" + commentSql + "'");

            return sb.toString();
        }

        // 后续考虑缓存
        public static List<ColumnMeta> getBizColumns(String tableName) {
            List<ColumnMeta> results = new ArrayList<>();

            JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringBeanUtil.getBean("jdbcTemplate");
            SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from " + tableName);
            SqlRowSetMetaData metaData = sqlRowSet.getMetaData();

            int columnCount = metaData.getColumnCount();
            // 去除掉id, 以及 FOOT_COLUMNS
            // 这个 2 表示去除了id 和 FOOT_COLUMNS_SQL_LIST.size() - 1
            for (int i = 2; i < columnCount - ConfigCenter.FOOT_COLUMNS_SQL_LIST.size() + 1; i++) {
                ColumnMeta columnMeta = new ColumnMeta();

                String columnName = metaData.getColumnName(i);
                int columnType = metaData.getColumnType(i);
                String columnTypeName = metaData.getColumnTypeName(i);

                columnMeta.setColumnName(columnName);
                columnMeta.setDataType(columnType);
                columnMeta.setDateTypeName(columnTypeName);

                results.add(columnMeta);
            }
            return results;
        }

        /**
         * description: 获取自定义的列, 比如 operator_id, create_time 等
         *
         * @return java.util.List<com.xyz.sql.entity.ColumnMeta>
         * @author: xyz
         * @create: 2019-03-11
         */
        public static List<ColumnMeta> getFootColumns() {

            return ConfigCenter.FOOT_COLUMNS_SQL_LIST;
        }
    }

}
