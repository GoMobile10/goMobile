package com.gomobile.technicalservices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class MySqlConnector {

	private String queryString;
	
	public MySqlConnector(){
		queryString = "";
	}
	
	public String[][] queryResultToArray() throws ClassNotFoundException, SQLException{
		ResultSet resultSet = this.getQueryResult();
		int columnCount = resultSet.getMetaData().getColumnCount();
		int rowCount = -1;
		
		List<String[]> tempList = new ArrayList<String[]>();
				
		while(resultSet.next()){
			String[] tempRowAsArray = new String[columnCount];
			
			for(int i = 1; i <= columnCount; i++){				
				tempRowAsArray[i - 1] = resultSet.getString(i);
			}
			tempList.add(tempRowAsArray);
		}
		
		rowCount = tempList.size();
		String[][] resultArray = new String[rowCount][columnCount];
		for(int i = 0; i < rowCount; i++){
			resultArray[i] = tempList.iterator().next();
		}
		
		resultSet.close();
		
		return resultArray;
		
	}
	
	private ResultSet getQueryResult() throws ClassNotFoundException, SQLException{
		PreparedStatement statement = getConnection().prepareStatement(this.getQueryString());
		ResultSet resultSet = statement.executeQuery();
		
		getConnection().close();
		
		return resultSet;
	}
	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://134.155.56.118:3306/go_mobile_db", "GoMobileApp", "GoMobile2013");
		
		return connection;
	}

	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param queryString the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
}
