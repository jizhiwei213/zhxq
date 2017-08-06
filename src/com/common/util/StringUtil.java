package com.common.util;

import java.util.Random;
import java.util.StringTokenizer;

import org.doomdark.uuid.UUIDGenerator;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class StringUtil {

	/**
	 * 连接字符串并且转换为大写
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static String concatStringtoUpperCase(String left, String right) {
		String newstr = left + right;

		return newstr.toUpperCase();
	}

	/**
	 * 给定指定的长度不足位数用0补全
	 * 
	 * @param oldstr
	 *            原始字符串
	 * @param length
	 *            总长度
	 * @return
	 */
	public static String completionStringtoUpperCase(String oldstr, int length) {
		String newstr = oldstr;
		String completion = "";
		if (newstr != null && !"".equals(newstr)) {
			for (int i = 0; i < length - newstr.length(); i++) {
				completion = "0" + completion;
			}
		}
		return completion + newstr.toUpperCase();
	}

	public static String getUUID() {
		String primaryKey = UUIDGenerator.getInstance().generateRandomBasedUUID().toString();
		primaryKey = primaryKey.replaceAll("-", "");
		return primaryKey;
	}

	public static String objectToJson(Object obj) {
		XStream xs = new XStream(new JsonHierarchicalStreamDriver());

		return xs.toXML(obj);
	}

	public static boolean isNullOrEmpty(String obj) {
		if (obj != null && !"".equals(obj.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static String buildRandomNumber() {
		Random rdm = new Random();
		long ct = Math.abs(rdm.nextLong());
		String nb = String.valueOf(ct);
		String nm = (nb.substring(nb.length() - 4, nb.length()));
		return DateUtil.getDateTime("yyyyMMdd") + nm;
	}

	public static boolean compareIDS(String ids, String delim, String id) {
		StringTokenizer st = new StringTokenizer(ids, delim);
		while (st.hasMoreElements()) {
			if (id.equals(st.nextElement())) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		// System.out.println(StringUtil.compareIDS("abc;abcd;abcdef",";","abc1"));
		// System.out.println(Base64Decoder.decode("MTIzNDU2"));
		// String aa="aa";
		// aa=+"bb";
		// aa+="bb";
		// System.out.println(aa);
		System.out.println(buildRandomNumber());
		String a = "4028f6813e9d56ef013e9d5cc6780004/4028f6813e9d56ef013e9d6233fe0008/402881f73ed41fe4013ed42dbd7c0002/";
		System.out.println(a.split("/").length);
	}
}
