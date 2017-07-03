package ltd.cracks.core.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by macos on 2017/6/23.
 */
public class base64Util {
    public static String encodeStr(String string) {
        return new String(Base64.encodeBase64(string.getBytes()));
    }

    public static String decodeStr(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }
}
