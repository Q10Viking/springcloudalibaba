package org.hzz;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.Base64Codec;
import org.junit.Test;

import java.util.Date;

public class JwtdemoApplicationTests {
    private static final String SECRETKEY = "123123";

    @Test
    public void test(){
        JwtBuilder jwtBuilder = Jwts.builder()
                //声明的标识{"jti":"666"}
                .setId("666")
                //主体，用户{"sub":"hzz"}
                .setSubject("hzz")
                //创建日期{"ita":"xxxxxx"}
                .setIssuedAt(new Date())
                //设置过期时间   1分钟
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 1000))
                //直接传入map
                // .addClaims(map)
                .claim("roles", "admin")
                .claim("logo", "xxx.jpg")
                //签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256, SECRETKEY);

        String token = jwtBuilder.compact();
        System.out.println(token);

        //三部分的base64解密
        System.out.println("=========");
        String[] split = token.split("\\.");
        System.out.println(Base64Codec.BASE64.decodeToString(split[0]));
        System.out.println(Base64Codec.BASE64.decodeToString(split[1]));
        //无法解密
//        System.out.println(Base64Codec.BASE64.decodeToString(split[2]));
    }
}
/**
 * eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJoenoiLCJpYXQiOjE2Nzg4NTI0MzYsImV4cCI6MTY3ODg1MjQ5Niwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJ4eHguanBnIn0.LmGgOqi71YyhekY9pgbN1S3xCoeTNAI5jX3J0lwfXG8
 * =========
 * {"alg":"HS256"}
 * {"jti":"666","sub":"hzz","iat":1678852436,"exp":1678852496,"roles":"admin","logo":"xxx.jpg
 */