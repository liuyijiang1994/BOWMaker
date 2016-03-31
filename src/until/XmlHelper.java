package until;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.BufferedReader;
import java.io.IOException;
import org.nlpcn.commons.lang.util.IOUtil;

public class XmlHelper {
	public static final String TAG_START_CONTENT = "<article>";
	public static final String TAG_END_CONTENT = "</article>";

	public static List<String> parseXML(String dir) {
		List<String> texts=new ArrayList<String>();
		String temp = null;
		BufferedReader reader = null;
		try {
			reader = IOUtil.getReader(dir, "UTF-8");
			long start = System.currentTimeMillis();
			Set<String> set = new HashSet<String>();
			while ((temp = reader.readLine()) != null) {
				temp = temp.trim();
				if (temp.startsWith(TAG_START_CONTENT)) {
					int end = temp.indexOf(TAG_END_CONTENT);
					while(end==-1){
						temp+=reader.readLine();
						end = temp.indexOf(TAG_END_CONTENT);
					}
					String content = temp.substring(TAG_START_CONTENT.length(),
							end);
					// System.out.println(content);
					if (content.length() > 0) {
						texts.add(content);
					}
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("共获取 "+texts.size()+" 条文本，用时 "+(end-start)+" 毫秒");
			return texts;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		List<String> s=parseXML("./doc/weibo.xml");
		for (int i = 0; i < 10;	i++) {
			System.out.println(s.get(i));
		}
	}
	
}