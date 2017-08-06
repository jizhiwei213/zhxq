package com.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

public class FileUtil {

	public static byte[] fileToByteArray(File file) {
		if (file == null) {
			return null;
		}
		InputStream in = null;
		ByteArrayOutputStream out = null;
		byte b[] = new byte[1024];
		try {
			in = new FileInputStream(file);
			out = new ByteArrayOutputStream(1024);
			int n;
			while ((n = in.read(b)) != -1) {
				out.write(b, 0, n);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.toByteArray();
	}

	public static String percent(double p1, double p2) {
		String str;
		double p3 = p1 / p2;
		NumberFormat nf = NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits(2);
		str = nf.format(p3);
		return str;
	}

	/**
	 * 文件复制
	 * @param src 源文件
	 * @param dst 目标文件
	 */
	public static void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				if (!dst.getParentFile().getAbsoluteFile().exists()) {
					dst.getParentFile().mkdirs();
				}
				in = new BufferedInputStream(new FileInputStream(src), 10240);
				out = new BufferedOutputStream(new FileOutputStream(dst), 10240);
				byte[] buffer = new byte[10240];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	public static String getWebAppPath() {
		String path = FileUtil.class.getClassLoader().getResource("").getPath();
		return path;
	}
}
