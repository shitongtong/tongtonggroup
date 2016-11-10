package cn.stt.utils;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by stt on 2016/11/10.
 */
public class CheckUtil {

    private static final String tonken = "stt";

    public static boolean checkSignature(String signature,String timestamp,String nonce){
        String[] arr = new String[]{tonken,timestamp,nonce};
        //×ÖµäÐòÅÅÐò
        Arrays.sort(arr);
        //Éú³É×Ö·û´®
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        //sha1¼ÓÃÜ
        String sha1 = getSha1(sb.toString());
        return sha1.equals(signature);
    }

    private static String getSha1(String str){

        if (str == null || str.length() == 0){
            return null;
        }
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        }catch (Exception e){
            return null;
        }
    }
}
