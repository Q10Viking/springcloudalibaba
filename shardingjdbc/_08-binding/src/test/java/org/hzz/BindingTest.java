package org.hzz;

import org.hzz.entity.Dict;
import org.hzz.entity.User;
import org.hzz.mapper.DictMapper;
import org.hzz.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BindingTest {

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private UserMapper userMapper;
    @Test
    public void insertDict(){
        Dict dict = new Dict();
        dict.setUstatus("1");
        dict.setUvalue("正常");
        dictMapper.insert(dict);

        Dict dict2 = new Dict();
        dict2.setUstatus("2");
        dict2.setUvalue("异常");
        dictMapper.insert(dict2);
    }


    @Test
    public void insertUser(){
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("user_"+i);
            user.setUstatus(String.valueOf(i%2+1));
            userMapper.insert(user);
        }
    }

}
