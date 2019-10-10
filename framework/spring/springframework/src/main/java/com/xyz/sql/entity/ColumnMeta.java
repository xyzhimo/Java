package com.xyz.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 列的元数据
 * <p>
 * PackageName: com.xyz.sql.entity
 * FileName: ColumnMeta.java
 * Copyright: Copyright (c)2019. songxiaocai
 *
 * @author: xyz
 * @create: 2019-03-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColumnMeta {

    /**
     * 列名称
     */
    private String columnName;

    /**
     * 列数据类型
     */
    private int dataType;

    /**
     * 列数据类型名称
     */
    private String dateTypeName;

    /**
     * 列数据类型长度，-1 表示不需要长度
     */
    private int dataTypeLength;

    /**
     * 列备注
     */
    private String comment;
}
