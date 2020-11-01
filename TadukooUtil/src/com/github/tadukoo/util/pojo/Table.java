package com.github.tadukoo.util.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Tables hold {@link MappedPojo}s in them as rows.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.2
 *
 * @param <Item> The type of {@link MappedPojo}s stored in this Table
 */
public class Table<Item extends MappedPojo>{
	/** Contains the {@link MappedPojo}s in this Table */
	private final List<Item> rows;
	
	/**
	 * Creates a new Table and initializes the backing List to a new ArrayList
	 */
	public Table(){
		rows = new ArrayList<>();
	}
	
	/**
	 * @return A List of all the {@link MappedPojo}s in this Table
	 */
	public List<Item> getAllRows(){
		return rows;
	}
	
	/**
	 * Grabs the {@link MappedPojo} at the given index in this Table
	 *
	 * @param index The index of the {@link MappedPojo} to grab
	 * @return The {@link MappedPojo} at the given index
	 */
	public Item getRow(int index){
		return rows.get(index);
	}
	
	/**
	 * @return The number of {@link MappedPojo}s in this Table
	 */
	public int getNumRows(){
		return rows.size();
	}
	
	/**
	 * Adds the given {@link MappedPojo} to the end of this Table
	 *
	 * @param item The {@link MappedPojo} to add to this Table
	 */
	public void addRow(Item item){
		rows.add(item);
	}
	
	/**
	 * Adds the given {@link MappedPojo} to this Table at the given index
	 *
	 * @param index The index to place the {@link MappedPojo} at
	 * @param item The {@link MappedPojo} to add to this Table
	 */
	public void addRow(int index, Item item){
		rows.add(index, item);
	}
}
