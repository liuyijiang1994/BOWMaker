package model;

import java.util.Map;

import org.ansj.domain.Term;

public class Document {
	private Map<Term, Integer> terms;

	public Map<Term, Integer> getTerms() {
		return terms;
	}

	public void setTerms(Map<Term, Integer> terms) {
		this.terms = terms;
	}
	
}
