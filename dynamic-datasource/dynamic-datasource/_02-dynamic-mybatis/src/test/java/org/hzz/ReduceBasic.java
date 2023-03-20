package org.hzz;

import java.util.Arrays;
import java.util.List;

public class ReduceBasic {
    public static void main(String[] args) {
        String[] strArray = {"abc", "mno", "xyz"};
        List<String> strList = Arrays.asList(strArray);

        System.out.println("stream test");
        int streamResult = strList.stream().reduce(
                0,
                (total,s) -> { System.out.println("accumulator: total[" + total + "] s[" + s + "] s.codePointAt(0)[" + s.codePointAt(0) + "]"); return total + s.codePointAt(0); },
                (a,b) -> { System.out.println("combiner: a[" + a + "] b[" + b + "]"); return 1000000;}
        );
        System.out.println("streamResult: " + streamResult);

        System.out.println("parallelStream test");
        int parallelStreamResult = strList.parallelStream().reduce(
                0,
                (total,s) -> { System.out.println("accumulator: total[" + total + "] s[" + s + "] s.codePointAt(0)[" + s.codePointAt(0) + "]"); return total + s.codePointAt(0); },
                (a,b) -> { System.out.println("combiner: a[" + a + "] b[" + b + "]"); return 1000000;}
        );
        System.out.println("parallelStreamResult: " + parallelStreamResult);

        System.out.println("parallelStream test2");
        int parallelStreamResult2 = strList.parallelStream().reduce(
                0,
                (total,s) -> { System.out.println("accumulator: total[" + total + "] s[" + s + "] s.codePointAt(0)[" + s.codePointAt(0) + "]"); return total + s.codePointAt(0); },
                (a,b) -> { System.out.println("combiner: a[" + a + "] b[" + b + "] a+b[" + (a+b) + "]"); return a+b;}
        );
        System.out.println("parallelStreamResult2: " + parallelStreamResult2);
    }
}
/**
 * stream test
 * accumulator: total[0] s[abc] s.codePointAt(0)[97]
 * accumulator: total[97] s[mno] s.codePointAt(0)[109]
 * accumulator: total[206] s[xyz] s.codePointAt(0)[120]
 * streamResult: 326
 * parallelStream test
 * accumulator: total[0] s[mno] s.codePointAt(0)[109]
 * accumulator: total[0] s[xyz] s.codePointAt(0)[120]
 * combiner: a[109] b[120]
 * accumulator: total[0] s[abc] s.codePointAt(0)[97]
 * combiner: a[97] b[1000000]
 * parallelStreamResult: 1000000
 * parallelStream test2
 * accumulator: total[0] s[mno] s.codePointAt(0)[109]
 * accumulator: total[0] s[xyz] s.codePointAt(0)[120]
 * combiner: a[109] b[120] a+b[229]
 * accumulator: total[0] s[abc] s.codePointAt(0)[97]
 * combiner: a[97] b[229] a+b[326]
 * parallelStreamResult2: 326
 */