package cn.javaxxw.utils;

import java.security.MessageDigest;


/**
 * @desc md5工具类
 * @author Administrator
 * @since 2017/6/16
 * @version 1.0
 */
public class MD5Util {

    public final static String MD5(String content) {
        //用于加密的字符
        char md5String[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = content.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    public static String signature(String orgin) {
        return DigestUtils.md5ToHex(orgin);
    }

    /**
     * 签名
     * @param orgin 待签名的原文
     * @param key 通信双方约定的秘钥
     * @return
     */
    public static String signature(String orgin, String key) {
        return DigestUtils.md5ToHex(orgin, key);
    }

    public static void main(String[] args){
         System.out.println(MD5("http://www.baidu.com/213212131?a=e123213123123131321313213131312312321312"));
    }

}
