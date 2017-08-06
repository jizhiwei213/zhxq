package com.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpSession;

public class VerificationCodeUtil {
	
	public static final String SESSION_VERIFICATION_CODE_NAME="ST_CODE";//验证码在session中的属性名
	
	/**
	 * 生成验证码
	 * 
	 * @param session
	 * @return
	 */
	public static ByteArrayInputStream buildVerificationCodeImage(HttpSession session) {
		int width = 61, height = 24;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(150, 200));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.drawString(rand, 13 * i + 6, 16);
		}
		session.setAttribute(SESSION_VERIFICATION_CODE_NAME, sRand);
		g.dispose();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageOutputStream imageOut;
		try {
			imageOut = ImageIO.createImageOutputStream(output);
			ImageIO.write(image, "PNG", imageOut);
			imageOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());
		return input;
	}

	/**
	 * 在给定的范围内生成颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static String buildResultCode() {
		String code = "";
		code = DateUtil.getDateTime("yyyyMMdd") + "001";
		return code;
	}

	public static String buildResultPassword() {
		String password = "";
		password = StringUtil.getUUID().substring(0, 6).toUpperCase();
		return password;
	}

	public static String buildFiled() {
		String a[] = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A",
				"S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V",
				"B", "N", "M" };
		Random random = new Random();
		StringBuffer str = new StringBuffer();
		str.append(a[random.nextInt(a.length - 1)]);
		str.append(a[random.nextInt(a.length - 1)]);
		str.append(a[random.nextInt(a.length - 1)]);
		str.append(a[random.nextInt(a.length - 1)]);
		str.append(StringUtil.getUUID().toUpperCase());
		return str.toString();
	}

	public static void main(String[] args) {
		System.out.println(buildFiled());
	}
}
