package cn.zhku.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * 使用md5的算法进行加密，md5是缺失精度的加密算法，是不可逆的，这里使用md5把接受的任何长度字符串转换为32位字符串
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			//调用java信息加密类，取得md5算法，执行加密，因为md5算法加密类型是byte，把字符串plainText转换成byte类型加密
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		//把加密得到的byte数组类型的secretBytes再转换成16进制数字
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		System.out.println(md5("123"));
	}

}
