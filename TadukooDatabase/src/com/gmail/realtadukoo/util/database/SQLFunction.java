package com.gmail.realtadukoo.util.database;

import java.sql.SQLException;

@FunctionalInterface
public interface SQLFunction<S, R>{
	
	public R apply(S obj) throws SQLException;
}
