package test.java8.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by shu on 2017/7/22.
 * Base64编码
 */
public class Base64s {
    public static void main(String[] args) {
        final String text = "Base64 finally in Java";
        final String encoded = Base64
                .getEncoder()
                .encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        final String decode = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decode);
    }
}
