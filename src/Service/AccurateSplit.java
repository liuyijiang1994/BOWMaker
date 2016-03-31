package Service;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

public class AccurateSplit implements WordSpliter {

	@Override
	public List<Term> splieWord(String text) {
		List<Term> parse = ToAnalysis.parse(text);
		return parse;
	}

}
;