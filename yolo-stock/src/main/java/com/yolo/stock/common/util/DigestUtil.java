package com.yolo.stock.common.util;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @Author: HuPengCheng
 * @Date: 2020/7/15 10:28 上午
 */
public class DigestUtil {
    public static final String SECURITYKEY = "b5aa6369-2eb7-4289-9230-2ba87ee49747";
    public static void verify(HttpServletRequest request) {
        String nonce = request.getHeader("nonce");
        String created = request.getHeader("created");
        String systemId = request.getHeader("systemId");
        String digest = request.getHeader("digest");
        String sha1 = DigestUtil.getSha1(nonce + created + systemId + SECURITYKEY);
        String encoded = Base64.getEncoder().encodeToString(sha1.getBytes());
        if (!encoded.equals(digest)) {
            throw new RuntimeException("消息认证失败");
        }

    }

    public static String getSha1(String str) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
}
