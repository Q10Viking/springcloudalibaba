package org.hzz;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void generatePassword(){
        System.out.println(new BCryptPasswordEncoder().encode("Root.123456"));
    }

}
