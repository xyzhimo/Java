package com.xyz.dbunit;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.*;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyJdbcApplicationTests {

	@Resource
	private DataSource dataSource;

	private IDatabaseConnection iDatabaseConnection;

	@Before
	public void contextLoads() throws SQLException, DatabaseUnitException {
		iDatabaseConnection = new DatabaseConnection(dataSource.getConnection());
	}

	@Test
	public void testQuery() throws DataSetException, IOException {
		QueryDataSet queryDataSet = new QueryDataSet(iDatabaseConnection, true);

		queryDataSet.addTable("account_tbl", "select * from account_tbl");

		FlatXmlDataSet.write(queryDataSet, new FileWriter(new File("account_tbl.xml")), "UTF-8");
	}
}
