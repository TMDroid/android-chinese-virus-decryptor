package ro.adlabs;

import java.io.IOException;

/**
 * 0 - 9 == 48 - 57
 * <p>
 * HG1 = BGFGDF
 * <p>
 * BGFGDF = 165635
 * <p>
 * HG2 = CDBJAF
 * <p>
 * CDBJAF = 231905
 * <p>
 * BGBIGI = 161868
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {
        if(args.length == 0) {
            System.out.println("Usage: java Main <stage 1 int> <stage 2 int> <stage 3 int> <stage 4 int>");
        } else {
            (new Main()).decrypt(args);
        }
    }

    public void decrypt(String[] s) throws InterruptedException {
        String value = "";
        int backspaces = 0;

        if (s.length > 0) {
            int HG1 = Integer.valueOf(dehuanguang(s[0])) + 1;
            System.out.println("HG1: " + HG1);

            value = String.valueOf(HG1);
        }

        if (s.length > 1) {
            int HG2 = Integer.valueOf(dehuanguang(s[1])) + 10;
            System.out.println("HG2: " + HG2);

            value = String.valueOf(HG2);
            backspaces = 6;
        }

        if (s.length > 2) {
            int HG3 = Integer.valueOf(dehuanguang(s[2])) + 100;
            String hash = MD5er.GetMD5Code(String.valueOf(HG3));
            System.out.println("HG3: " + hash);
            value = String.valueOf(hash);

            backspaces = 6;
        }

        if (s.length > 3) {
            int HG4 = Integer.valueOf(dehuanguang(s[3])) + 1100;
            String hash = MD5er.GetMD5Code(String.valueOf(HG4));
            System.out.println("HG4: " + hash);

            value = String.valueOf(hash);
            backspaces =32;
        }

        if(!value.equals("")) {
            String command = "adb shell input keyevent 67";
            for (int i = 0; i < backspaces; i++) {
                runAdb(command);
                Thread.sleep(100);
            }

            Thread.sleep(1000);

            command = String.format("adb shell input text %s", value);
            runAdb(command);
        }

    }

    private void runAdb(String command) {
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String dehuanguang(String str) {
        String hexString = "0123456789";
        String charString = "ABCDEFGHIJ";

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);

            result.append(hexString.charAt(charString.indexOf(current)));
        }

        return result.toString();
    }
}
