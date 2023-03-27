package org.hzz;

import org.hzz.entity.Dict;
import org.hzz.mapper.DictMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 广播表测试
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class BroadCastTest {

    @Autowired
    private DictMapper dictMapper;
    @Test
    public void insert(){
        Dict dict = new Dict();
        dict.setUstatus("1");
        dict.setUvalue("正常");
        dictMapper.insert(dict);

        Dict dict2 = new Dict();
        dict2.setUstatus("2");
        dict2.setUvalue("异常");
        dictMapper.insert(dict2);
    }

}
