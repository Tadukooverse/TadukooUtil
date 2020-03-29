package com.gmail.realtadukoo.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gmail.realtadukoo.util.AutoCloseableUtil;

public class DBUtil{
	public static abstract class Query<ResultType>{
		
		public String getName(){
			return null;
		}
		
		public abstract String getSQL();

		public ResultType executeQuery(Connection conn) throws SQLException{
			Statement stmt;
			ResultSet resultSet = null;
			
			try{
				if(getName() != null){
					System.out.println("Doing query " + getName());
				}
				stmt = conn.createStatement();
				resultSet = stmt.executeQuery(getSQL());
				if(getName() != null){
					System.out.println("Finished query " + getName());
				}
				
				return convertFromResultSet(resultSet);
			}finally{
				AutoCloseableUtil.closeQuietly(resultSet);
			}
		}
		
		public abstract ResultType convertFromResultSet(ResultSet resultSet) throws SQLException;
	}
	
	public static <ResultType> Query<ResultType> createQuery(String name, String sql, 
			SQLExceptionFunction<ResultSet, ResultType> convertFromResultSet){
		return new Query<>(){
			
			@Override
			public String getName(){
				return name;
			}
			
			@Override
			public String getSQL(){
				return sql;
			}
			
			@Override
			public ResultType convertFromResultSet(ResultSet resultSet) throws SQLException{
				return convertFromResultSet.apply(resultSet);
			}
		};
	}
	
	public static abstract class Updates{
		
		public List<String> getNames(){
			return null;
		}
		
		public abstract List<String> getSQLs();

		public Boolean executeUpdates(Connection conn) throws SQLException{
			ArrayList<Statement> stmts = new ArrayList<>();
			List<String> names = getNames();
			List<String> sqls = getSQLs();
			try{
				boolean hasNames = names != null;
				if(hasNames && names.size() != sqls.size()){
					throw new IllegalArgumentException("Must have all sql statements named or pass null names array!");
				}
				for(int i = 0; i < sqls.size(); i++){
					if(hasNames){
						System.out.println("Starting " + names.get(i));
					}
					stmts.add(conn.createStatement());
					stmts.get(i).executeUpdate(sqls.get(i));
					if(hasNames){
						System.out.println("Finished " + names.get(i));
					}
				}
				return true;
			}finally{
				for(Statement stmt: stmts){
					AutoCloseableUtil.closeQuietly(stmt);
				}
			}
		}
	}
	
	public static Updates createUpdates(List<String> names, List<String> sqls){
		return new Updates(){
			@Override
			public List<String> getNames(){
				return names;
			}
			
			@Override
			public List<String> getSQLs(){
				return sqls;
			}
		};
	}
	
	public static abstract class InsertAndGetID{
		
		public abstract String getInsertString();
		
		public abstract String getInsertSQL();
		
		public abstract String getSelectString();
		
		public abstract String getSelectSQL();

		public Integer insertAndGetID(Connection conn) throws SQLException{
			Statement insert = null;
			Statement selectID = null;
			ResultSet id = null;
			
			try{
				insert = conn.createStatement();
				
				String insertSQL = getInsertSQL();
				insert.executeUpdate(insertSQL);
				System.out.println(getInsertString());
				
				selectID = conn.createStatement();
				id = selectID.executeQuery(getSelectSQL());
				System.out.println(getSelectString());
				
				id.next();
				return id.getInt(1);
			}finally{
				AutoCloseableUtil.closeQuietly(insert);
				AutoCloseableUtil.closeQuietly(selectID);
				AutoCloseableUtil.closeQuietly(id);
			}
		}
	}
	
	public static InsertAndGetID createInsertAndGetID(String table, String id_str, String[] args, String[] values){
		if(args.length != values.length){
			throw new IllegalArgumentException("Args and Values don't match up!");
		}
		
		return new InsertAndGetID(){
			@Override
			public String getInsertString(){
				return "Executed Insert of a " + table + "!";
			}
			
			@Override
			public String getInsertSQL(){
				return formatInsertStatement(table, args, values);
			}
			
			@Override
			public String getSelectString(){
				return "Pulled out " + id_str + " of just inserted " + table + "!";
			}
			
			@Override
			public String getSelectSQL(){
				return formatQuery(table, id_str, args, values);
			}
		};
	}
	
	public static void addConditionalAndToQuery(boolean prevSet, StringBuilder name, StringBuilder sql){
		if(prevSet){
			name.append(" and ");
			sql.append(" and ");
		}
	}
	
	public static boolean addConditionalIntToQuery(boolean prevSet, StringBuilder name, StringBuilder sql, String arg, int value){
		if(value != -1){
			addConditionalAndToQuery(prevSet, name, sql);
			name.append(arg + " of " + value);
			sql.append(arg + " = " + value);
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean addConditionalStringToQuery(boolean prevSet, StringBuilder name, StringBuilder sql, boolean partial, 
			String arg, String value){
		if(value != null && !value.equalsIgnoreCase("")){
			addConditionalAndToQuery(prevSet, name, sql);
			if(partial){
				name.append(arg + " with " + value);
				sql.append(arg + " like '%" + value + "%'");
			}else{
				name.append(arg + " of " + value);
				sql.append(arg + " = '" + value + "'");
			}
			return true;
		}else{
			return false;
		}
	}
	
	public static void addJunctionStringToQuery(boolean prevSet, StringBuilder name, StringBuilder sql, String junction){
		addConditionalAndToQuery(prevSet, name, sql);
		name.append(junction);
		sql.append(junction);
	}
	
	// TODO: Improve (/Rework doSearch logic into here)
	public static String formatQuery(String table, String returnPieces, String[] args, String[] values){
		String selectSQL = "select " + returnPieces + " from " + table + " where ";
		for(int i = 0; i < args.length; i++){
			if(!values[i].startsWith("SHA")){
				selectSQL += args[i] + " = ";
				if(values[i].equalsIgnoreCase("true") || values[i].equalsIgnoreCase("false")){
					selectSQL += values[i];
				}else{
					selectSQL += "'" + values[i] + "'";
				}
				if(i != args.length - 1){
					selectSQL += " and ";
				}else{
					selectSQL += ";";
				}
			}
		}
		return selectSQL;
	}
	
	public static String formatInsertStatement(String table, String[] args, String[] values){
		String insertSQL = "insert into " + table + " (";
		for(int i = 0; i < args.length; i++){
			if(i == args.length - 1){
				insertSQL += args[i] + ") select ";
			}else{
				insertSQL += args[i] + ", ";
			}
		}
		for(int i = 0; i < values.length; i++){
			if(values[i].equalsIgnoreCase("true") || values[i].equalsIgnoreCase("false") ||
					values[i].startsWith("SHA")){
				insertSQL += values[i];
			}else{
				insertSQL += "'" + values[i] + "'";
			}
			if(i == values.length - 1){
				insertSQL += ";";
			}else{
				insertSQL += ", ";
			}
		}
		return insertSQL;
	}
}
