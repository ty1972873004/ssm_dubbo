package cn.javaxxw.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


/** 
 * @author  tuyong: 
 * @date 创建时间：2016年12月14日 上午11:32:19 
 * @version 1.0  
 * desc
 *  消息摘要工具类（支持sha1、md5进行数字签名），
 * 签名完后，sha1生成一串40位的哈希值。md5生成一串32位的哈希值
 */
public class DigestUtils {
	
	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";
	
	private static final String ENCODING_CHARSET = "UTF-8";

	/**
	 * 按sha1算法转换成对应摘要的哈希值
	 * @param input
	 * @return
	 */
	public static String sha1ToHex(String input) {
		byte[] digestResult = digest(input, SHA1, ENCODING_CHARSET);
		return EncodeUtils.hexEncode(digestResult);
	}
	
	/**
	 * 按md5算法转换成对应摘要的哈希值
	 * @param input
	 * @return
	 */
	public static String md5ToHex(String input) {
		byte[] digestResult = digest(input, MD5, ENCODING_CHARSET);
		return EncodeUtils.hexEncode(digestResult);
	}
	
	/**
	 * 按md5算法转换成对应摘要的哈希值
	 * @param input
	 * @return
	 */
	public static String md5ToHexWithCharset(String input, String charset) {
		byte[] digestResult = digest(input, MD5, charset);
		return EncodeUtils.hexEncode(digestResult);
	}

	/**
	 * 按sha1算法转换成对应摘要的base64编码后的值
	 * @param input
	 * @return
	 */
	public static String sha1ToBase64(String input) {
		byte[] digestResult = digest(input, SHA1, ENCODING_CHARSET);
		return EncodeUtils.base64Encode(digestResult);
	}
	
	/**
	 * 按md5算法转换成对应摘要的base64编码后的值
	 * @param input
	 * @return
	 */
	public static String md5ToBase64(String input) {
		byte[] digestResult = digest(input, MD5, ENCODING_CHARSET);
		return EncodeUtils.base64Encode(digestResult);
	}

	public static String sha1ToBase64UrlSafe(String input) {
		byte[] digestResult = digest(input, SHA1, ENCODING_CHARSET);
		return EncodeUtils.base64UrlSafeEncode(digestResult);
	}

	private static byte[] digest(String input, String algorithm, String charset) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			return messageDigest.digest(input.getBytes(charset));
		} catch (Exception e) {
			throw new IllegalStateException("Security exception", e);
		}
	}

	/**
	 * 按md5算法转换成对应摘要的哈希值
	 * @param input 输入流
	 * @return
	 */
	public static String md5ToHex(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	/**
	 * 按sha1算法转换成对应摘要的哈希值
	 * @param input 输入流
	 * @return
	 */
	public static String sha1ToHex(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	/**
	 * 生成消息摘要
	 * @param input 输入流
	 * @param algorithm 算法
	 * @return
	 * @throws IOException
	 */
	private static String digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return EncodeUtils.hexEncode(messageDigest.digest());
		} catch (GeneralSecurityException e) {
			throw new IllegalStateException("Security exception", e);
		}
	}
	
	/**
	 * 按md5算法转换成对应摘要的哈希值
	 * @param aValue 待加密的字符串
	 * @param aKey 秘钥
	 * @return
	 */
	public static String md5ToHex(String aValue, String aKey) {
		return hmacSign(aValue, aKey);
	}
	
	/**
	 * @param aValue
	 * @param aKey
	 * @return
	 */
	private static String hmacSign(String aValue, String aKey) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(ENCODING_CHARSET);
			value = aValue.getBytes(ENCODING_CHARSET);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}

		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(MD5);
		} catch (NoSuchAlgorithmException e) {

			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return EncodeUtils.hexEncode(dg);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(md5ToHex("111111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
