package com.xyz.sql.config;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.xyz.sql.entity.ColumnMeta;

import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Description: 配置中心数据
 * <p>
 * PackageName: com.xyz.sql.config
 * FileName: ConfigCenter.java
 * Copyright: Copyright (c)2019. songxiaocai
 *
 * @author: xyz
 * @create: 2019-03-11
 */
public class ConfigCenter {

    /**
     * dataType -> dateTypeName 的映射
     */
    public static final BiMap<Integer, String> BI_DATATYPE_MAP = ImmutableBiMap.<Integer, String>builder()
            .put(Types.INTEGER, "INT")
            .put(Types.VARCHAR, "VARCHAR")
            .put(Types.TIMESTAMP, "DATETIME")
            .build();

    /**
     * -1 表示不需要加 length，比如 DATETIME。
     */
    public static final Map<Integer, Integer> DATATYPE_DEFAULT_LENGTH_MAP = ImmutableMap.<Integer, Integer>builder()
            .put(Types.INTEGER, 11)
            .put(Types.VARCHAR, 256)
            .put(Types.TIMESTAMP, -1)
            .build();

    /**
     * 透传参数列的列表，如果后续有修改的话，需要修改
     * {@see ImportSqlEventListener#decorateFootColumnArgs()}
     */
    public static final List<ColumnMeta> FOOT_COLUMNS_SQL_LIST = ImmutableList.<ColumnMeta>builder()
            .add(new ColumnMeta("import_num", Types.VARCHAR, "VARCHAR", 32, "导入分组批次号"))
            .add(new ColumnMeta("operator_id", Types.INTEGER, "INT", 11, "操作者id"))
            .add(new ColumnMeta("state", Types.INTEGER, "INT", 2, "0:删除 1:可用"))
            .add(new ColumnMeta("create_time", Types.TIMESTAMP, "DATETIME", -1, "创建时间"))
            .add(new ColumnMeta("update_time", Types.TIMESTAMP, "DATETIME", -1, "修改时间"))
            .build();

}
