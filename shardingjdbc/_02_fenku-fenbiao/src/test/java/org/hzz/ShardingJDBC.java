package org.hzz;

import org.hzz.entity.Course;
import org.hzz.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingJDBC {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Course course = new Course();
            course.setCname("java");
            course.setUserId(100l);
            course.setCstatus("1");
            courseMapper.insert(course);
        }
    }
}
