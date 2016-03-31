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
import until.XmlHelper;

import model.Document;
import model.Word;

public class SentimentAnalyzer {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		// 存储用的数据结构
		int wordNum = 0;
		List<String> texts = null;
		List<Map<String, Integer>> documents = new ArrayList<Map<String, Integer>>();
		Map<String, int[]> wordVector = new TreeMap<String, int[]>();
		WordSpliter wordSpliter = new AccurateSplit();

		// 去停用词
		List<String> stopWords = null;
		stopWords = FileHelper.readFileByRow(Const.STOP_WORD_DIR_STRING);
		FilterModifWord.insertStopWords(stopWords);
		stopWords = null;

		// 读取微博数据
		// texts = FileHelper.readFileByRow(Const.WEIBO_TEXT_DIR_STRING);
		int i = 0;
		float percent = 0.1f;
		String reg = "[^\u4e00-\u9fa5]";
		texts = XmlHelper.parseXML("./doc/weibo.xml");
		for (String text : texts) {
			text = text.replaceAll("[\\pP‘'“”]", "");
			text = text.replaceAll(reg, "");
			List<Term> dTerms = wordSpliter.splieWord(text);
			dTerms = FilterModifWord.modifResult(dTerms);
			Map<String, Integer> terms = new HashMap<String, Integer>();
			for (Term term : dTerms) {
				terms.put(
						term.getName(),
						terms.keySet().contains(term.getName()) ? terms
								.get(term.getName()) + 1 : 1);
				if (term.getNatureStr().startsWith("a")) {
					if (!wordVector.keySet().contains(term.getName())) {
						wordVector.put(term.getName(), new int[] { wordNum++,0, 1 });
					} else {
						int []a=wordVector.get(term.getName());
						wordVector.put(term.getName(), new int[] { a[0], 0, a[2]+1 });
					}
				}
			}
			documents.add(terms);
			if (i++ >= texts.size() * percent) {
				System.out.println(percent * 100 + "%");
				percent += 0.1f;
			}
		}
		System.out.println("TF over, begin to deal IDF");

		//去掉出现太少的词语 减少稀疏性
		Set<String> names=new HashSet<String>();
		names.addAll(wordVector.keySet());
		for (String name : names) {
			System.out.println(name+" "+ (wordVector.get(name))[2]);	
		}
		names=null;
		
		long end = System.currentTimeMillis();
		System.out.println("共有 " + wordVector.size() + " 个词语纬度，用时 " + (end - start) + " 毫秒");
	}
}