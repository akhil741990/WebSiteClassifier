package org.soul.web.classifier.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class MySqlDatabase {

	private static MySqlDatabase instance = new MySqlDatabase();
	private BasicDataSource dataSource; 
	private MySqlDatabase(){
		init();
	}
	private void init(){
		this.dataSource = new BasicDataSource();
		this.dataSource.setUrl("jdbc:mysql://localhost:3306/webclassifier");
		this.dataSource.setUsername("root");
		this.dataSource.setPassword("password");
		this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.dataSource.setInitialSize(3);
		this.dataSource.setMaxActive(10);
	}
	public MySqlDatabase getInstance(){
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return this.dataSource.getConnection();
	}
}
