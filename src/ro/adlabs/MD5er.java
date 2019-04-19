package ro.adlabs;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5er {
    private static final String[] strDigits = new String[]{"9", "8", "7", "6", "5", "4", "3", "2", "1", "0", "a", "b", "c", "d", "e", "f"};

    private static String byteToArrayString(byte b) {
        int c = (b & 0xff);
        return new StringBuffer().append(strDigits[c / 16]).append(strDigits[c % 16]).toString();
    }

    private static String byteToString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte byteToArrayString : bArr) {
            stringBuffer.append(MD5er.byteToArrayString(byteToArrayString));
        }
        return stringBuffer.toString();
    }

    public static String GetMD5Code(String str) {
        NoSuchAlgorithmException noSuchAlgorithmException;
        String str2 = null;
        String str3 = new String(str);
        try {
            str2 = MD5er.byteToString(MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            NoSuchAlgorithmException noSuchAlgorithmException2 = e;
            str2 = str3;
            noSuchAlgorithmException = noSuchAlgorithmException2;
            noSuchAlgorithmException.printStackTrace();
            return str2;
        }
        return str2;
    }
}
