package com.gmail.realtadukoo.util.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommonResultSetConverters{
	
	public static Boolean check(ResultSet resultSet) throws SQLException{
		return resultSet.next();
	}
	
	public static <Type> List<Type> simpleList(ResultSet resultSet, SQLFunction<Integer, Type> getter) throws SQLException{
		List<Type> values = new ArrayList<>();
		while(resultSet.next()){
			values.add(getter.apply(1));
		}
		return values;
	}
	
	public static List<Boolean> booleans(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getBoolean);
	}
	
	public static List<Integer> integers(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getInt);
	}
	
	public static List<String> strings(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getString);
	}
	
	public static List<Time> times(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getTime);
	}
	
	public static List<Timestamp> timestamps(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getTimestamp);
	}
	
	public static List<Date> dates(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getDate);
	}
	
	public static List<Short> shorts(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getShort);
	}
	
	public static List<Long> longs(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getLong);
	}
	
	public static List<Float> floats(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getFloat);
	}
	
	public static List<Double> doubles(ResultSet resultSet) throws SQLException{
		return simpleList(resultSet, resultSet::getDouble);
	}
}
