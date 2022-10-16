package com.github.tadukoo.util.tuple;

/**
 * A tuple class for holding 3 items of specified type.
 *
 * @param <L> The left item type
 * @param <M> The middle item type
 * @param <R> The right item type
 *
 * @author Logan Ferree (Tadukoo)
 * @version 0.1-Alpha-SNAPSHOT
 */
public class Triple<L, M, R>{
	/** The left item in the tuple */
	private L left;
	/** The middle item in the tuple */
	private M middle;
	/** The right item in the tuple */
	private R right;
	
	/**
	 * Constructs a Triple of the three given objects.
	 *
	 * @param left The left item in the triple
	 * @param middle The middle item in the triple
	 * @param right The right item in the triple
	 */
	private Triple(L left, M middle, R right){
		this.left = left;
		this.middle = middle;
		this.right = right;
	}
	
	/**
	 * Constructs a Triple of the three given objects.
	 *
	 * @param left The left item in the Triple
	 * @param middle The middle item in the Triple
	 * @param right The right item in the Triple
	 * @param <L> The type of the left item
	 * @param <M> The type of the middle item
	 * @param <R> The type of the right item
	 * @return A Triple containing the three given items
	 */
	public static <L, M, R> Triple<L, M, R> of(L left, M middle, R right){
		return new Triple<>(left, middle, right);
	}
	
	/**
	 * @return The left item from the Triple
	 */
	public L getLeft(){
		return left;
	}
	
	/**
	 * Sets the left item of the Triple
	 *
	 * @param left The left item to set
	 */
	public void setLeft(L left){
		this.left = left;
	}
	
	/**
	 * @return The middle item from the Triple
	 */
	public M getMiddle(){
		return middle;
	}
	
	/**
	 * Sets the middle item of the Triple
	 *
	 * @param middle The middle item to set
	 */
	public void setMiddle(M middle){
		this.middle = middle;
	}
	
	/**
	 * @return The right item from the Triple
	 */
	public R getRight(){
		return right;
	}
	
	/**
	 * Sets the right item of the Triple
	 *
	 * @param right The right item to set
	 */
	public void setRight(R right){
		this.right = right;
	}
	
	/**
	 * @return A string representation of the Triple as ({left item}, {middle item}, {right item})
	 */
	@Override
	public String toString(){
		return "(" + left.toString() + ", " + middle.toString() + ", " + right.toString() + ")";
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean equals(Object otherTriple){
		if(otherTriple instanceof Triple triple){
			return this.getLeft().equals(triple.getLeft()) && this.getMiddle().equals(triple.getMiddle()) &&
					this.getRight().equals(triple.getRight());
		}else{
			return false;
		}
	}
}
