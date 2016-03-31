package model;

import org.ansj.domain.Term;

public class Word {
	private Term term;
	private int cont;
	
	public Word() {
		this.cont=0;
	}
	
	public Term getTerm() {
		return term;
	}
	public void setTerm(Term term) {
		this.term = term;
	}
	public int getCont() {
		return cont;
	}
	public void setCont(int cont) {
		this.cont = cont;
	}
}
