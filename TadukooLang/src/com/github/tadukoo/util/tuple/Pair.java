package com.github.tadukoo.util.tuple;

import java.util.Objects;

/**
 * A tuple class for holding 2 items of specified type.
 *
 * @param <L> The left item type
 * @param <R> The right item type
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class Pair<L, R>{
	/** The left item in the tuple */
	private L left;
	/** The right item in the tuple */
	private R right;
	
	/**
	 * Constructs a Pair of the two given objects.
	 *
	 * @param left The left item in the pair
	 * @param right The right item in the pair
	 */
	private Pair(L left, R right){
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Constructs a Pair of the two given objects.
	 *
	 * @param left The left item in the Pair
	 * @param right The right item in the Pair
	 * @param <L> The type of the left item
	 * @param <R> The type of the right item
	 * @return A Pair containing the two given items
	 */
	public static <L, R> Pair<L, R> of(L left, R right){
		return new Pair<>(left, right);
	}
	
	/**
	 * @return The left item from the Pair
	 */
	public L getLeft(){
		return left;
	}
	
	/**
	 * @return The left item (key) from the Pair
	 */
	public L getKey(){
		return left;
	}
	
	/**
	 * Sets the left item of the Pair
	 *
	 * @param left The left item to set
	 */
	public void setLeft(L left){
		this.left = left;
	}
	
	/**
	 * Sets the left item (key) of the Pair
	 *
	 * @param key The left item (key) to set
	 */
	public void setKey(L key){
		left = key;
	}
	
	/**
	 * @return The right item of the Pair
	 */
	public R getRight(){
		return right;
	}
	
	/**
	 * @return The right item (value) of the Pair
	 */
	public R getValue(){
		return right;
	}
	
	/**
	 * Sets the right item of the Pair
	 *
	 * @param right The right item to set
	 */
	public void setRight(R right){
		this.right = right;
	}
	
	/**
	 * Sets the right item (value) of the Pair
	 *
	 * @param value The right item (value) to set
	 */
	public void setValue(R value){
		right = value;
	}
	
	/**
	 * @return A string representation of the Pair as ({left item}, {right item})
	 */
	@Override
	public String toString(){
		return "(" + left.toString() + ", " + right.toString() + ")";
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object otherPair){
		if(otherPair instanceof Pair pair){
			return Objects.equals(this.getLeft(), pair.getLeft()) && Objects.equals(this.getRight(), pair.getRight());
		}else{
			return false;
		}
	}
}
