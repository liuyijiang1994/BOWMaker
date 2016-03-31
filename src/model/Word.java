package model;


public class Word {
	private int index;
	private double weight;
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Word(int index,double weight) {
		this.index=index;
		this.weight=weight;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
