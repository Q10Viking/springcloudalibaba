package org.hzz;

import org.junit.Test;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

public class AntPathTest {

    @Test
    public void test(){
        PathMatcher pathMatcher = new AntPathMatcher();
        // true
        System.out.println(pathMatcher.match("/content/view/**","/content/view/1"));
    }
}
