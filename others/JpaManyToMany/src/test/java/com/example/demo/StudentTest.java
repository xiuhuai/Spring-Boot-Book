package com.example.demo;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

/**
 * @author longzhonghua
 * @data 2019/01/28 08:32
 */
public class StudentTest {
@Autowired
private StudentRepository studentRepository;
    @Test
    public void teset() {
        Student student1= new Student();
        student1.setName("赵大伟");
        student1.setSex("male");
        try {
// Convert from Unicode to UTF-8
            String string = "abc\u5639\u563b";
            byte[] utf8 = string.getBytes("UTF-8");
// Convert from UTF-8 to Unicode
            string = new String(utf8, "UTF-8");
            System.out.println(string);
        } catch (UnsupportedEncodingException e) {
        }
        }
    @Test
    @Transactional
    public void tese2() {
      System.out.println("OK");
       int num=0;
       int snum;
       snum=100/num;
    }


}