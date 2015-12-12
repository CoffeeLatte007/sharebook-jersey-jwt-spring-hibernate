package com.lclizhao.sharebook;/**
 * Created by lizhaoz on 2015/11/26.
 */

import com.lclizhao.sharebook.daomain.Book;
import com.lclizhao.sharebook.daomain.User;
import com.lclizhao.sharebook.service.BookService;
import com.lclizhao.sharebook.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.Logger;

/**
 * @Name:
 * @Author: lizhao（作者）
 * @Version: V1.00 （版本号）
 * @Create Date: 2015-11-26（创建日期）
 * @Description:测试Service和Dao
 */
public class BookServiceTest {
    private final org.apache.logging.log4j.Logger logger= LogManager.getLogger(BookServiceTest.class.getName());
    @Test
    public void testService1(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService bookService= (BookService) context.getBean("bookService");
        Book book=new Book();
        book.setBookName("深入理解Java虚拟机");
        book.setAuthor("周志明");
        book.setiSBN("232183217832");
        bookService.save(book);
    }
    @Test
    public void testService2(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=(UserService)context.getBean("userService");
        User user =userService.findUserByTel("1842836864");
        if(user!=null){
        logger.debug(user.getTelphone());
        }

    }
    @Test
    public void testService3(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService=(UserService)context.getBean("userService");
        User user =userService.findUserByTel("1842836864");
        if(user!=null){
            logger.debug(user.getTelphone());
        }

    }
    @Test
    public void test(){
        System.out.println("1");
    }
}
