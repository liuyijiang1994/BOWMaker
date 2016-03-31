import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.ansj.domain.Term;
import org.ansj.util.FilterModifWord;

import Service.AccurateSplit;
import Service.WordSpliter;

import until.FileHelper;

import model.Document;
import model.Word;




public class mian {
	public static void main(String[] args) {
		//存储用数据结构
		int docNum=0;
		int wordNum=0;
		List<String> texts=null;
		List<Map<String, Integer>> documents=new ArrayList<Map<String, Integer>>();
		Map<String,int[]> wordVector=new TreeMap<String, int[]>();
		WordSpliter wordSpliter=new AccurateSplit();
		
		
		//去停用词
		List<String> stopWords=null;
		stopWords=FileHelper.readFileByRow(Const.STOP_WORD_DIR_STRING);
		FilterModifWord.insertStopWords(stopWords);
		stopWords=null;
		
		//读取微博数据
		texts=FileHelper.readFileByRow("/Users/sqtdnvrh/Desktop/biaozhu/negativie_1");
		for (String text : texts) {
			text=text.replaceAll("[\\pP‘'“”]", "");
			List<Term> dTerms=wordSpliter.splieWord(text);
			dTerms=FilterModifWord.modifResult(dTerms);
			Map<String, Integer> terms=new HashMap<String, Integer>();
			for (Term term : dTerms) {
				terms.put(term.getName(), terms.keySet().contains(term.getName())?terms.get(term.getName())+1:1);
				if(!wordVector.keySet().contains(term.getName())){
					wordVector.put(term.getName(),new int[]{wordNum++,0});
				}
			}
			documents.add(terms);
		}
		
		for (String name : wordVector.keySet()) {
			for (Map<String, Integer> term : documents) {
				if (term.keySet().contains(name)) {
					(wordVector.get(name)) [1]+=1;
				}
			}
		}
		
		
	}
}
