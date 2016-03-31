import java.util.List;

import model.Document;

import org.ansj.domain.Term;
import org.ansj.util.FilterModifWord;

import until.FileHelper;

import Service.AccurateSplit;
import Service.WordSpliter;

public class test {
	public static void main(String[] args) {
		WordSpliter wordSpliter = new AccurateSplit();

		List<String> texts = null;

		// 去停用词
		List<String> stopWords = null;
		stopWords = FileHelper.readFileByRow(Const.STOP_WORD_DIR_STRING);
		FilterModifWord.insertStopWords(stopWords);
		stopWords = null;

		// 读取微博数据
		texts = FileHelper.readFileByRow("/Users/sqtdnvrh/Desktop/biaozhu/negativie_1");
		for (String text : texts) {
			text=text.replaceAll("[\\pP‘'“”]", "");
			Document document = new Document();
			List<Term> dTerms = wordSpliter.splieWord(text);
			dTerms = FilterModifWord.modifResult(dTerms);
			System.out.println(dTerms);
		}
	}
}
