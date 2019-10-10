package com.xyz.sql.entity;

import lombok.Data;

import java.util.List;

/**
 * Description: 表的元数据
 * <p>
 * PackageName: com.xyz.sql.entity
 * FileName: TableMeta.java
 * Copyright: Copyright (c)2019. songxiaocai
 *
 * @author: xyz
 * @create: 2019-03-11
 */
@Data
public class TableMeta {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 列数据
     */
    private List<ColumnMeta> columns;

    /**
     * 表备注
     */
    private String comment;
}
