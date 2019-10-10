package com.xyz.sql.opt;

import com.xyz.sql.util.SqlUtil;

public class CreateTable {

    private static final String tableJson = "{\"tableName\":\"sxc_inlet_table\"," +
            "\"columns\":[{\"columnName\":\"name\",\"dataType\":12}," +
            "{\"columnName\":\"age\",\"dataType\":4},{\"columnName\":\"birth\",\"dataType\":93}]}";

    public static void main(String[] args) {
        String createTableSql = SqlUtil.TableUtil.genCreateTableSql(tableJson);
        System.out.println(createTableSql);
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring.xml"});
//        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
//        jdbcTemplate.execute(createTableSql);
    }


}
