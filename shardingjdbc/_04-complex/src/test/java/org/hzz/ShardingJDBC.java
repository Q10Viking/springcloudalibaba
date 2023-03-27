package org.hzz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.hzz.entity.Course;
import org.hzz.mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingJDBC {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setCname("java");
            course.setUserId(100l);
            course.setCstatus("1");
            courseMapper.insert(course);
        }
    }

    //--------------------------------standard策略-----------------------------------------------
    @Test
    public void queryIn(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.in("cid",846892506665713664L,846892507269693441L);

        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    @Test
    public void queryBetween(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.between("cid",846892506665713664L,846892507269693441L);

        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }
    //---------------------------以上是standard----------------------------------------------------



    //---------------------------complex策略----------------------------------------------------

    @Test
    public void queryByComplexStrategy(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.in("cid",846892506665713664L,846892507269693441L);
        wrapper.between("user_id",99L,100L);

        List<Course> courses = courseMapper.selectList(wrapper);
        courses.forEach(System.out::println);
    }

    //---------------------------以上是complex----------------------------------------------------
}
