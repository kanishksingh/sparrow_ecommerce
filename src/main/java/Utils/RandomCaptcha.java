/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Random;

/**
 *
 * @author KANISHK
 */
public class RandomCaptcha {
 
    public static String fastestRandomStringWithMixedCase(int length) {
    Random random = new Random();
    final int alphabetLength = 'Z' - 'A' + 1;
    StringBuilder result = new StringBuilder(length);
    while (result.length() < length) {
        final char charCaseBit = (char) (random.nextInt(2) << 5);
        result.append((char) (charCaseBit | ('A' + random.nextInt(alphabetLength))));
    }
    return result.toString();
}
//    public static void main(String[] args) {
//        System.out.println(fastestRandomStringWithMixedCase(10));
//    }
  
}
