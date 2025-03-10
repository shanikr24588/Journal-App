package com.shani.journalApp.service;

import com.shani.journalApp.Entity.Users;
import com.shani.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
         Users user = userRepository.findByUserName("ram");
         assertTrue(!user.getJournalEntries().isEmpty());

    }

    @ParameterizedTest
    @CsvSource({
            "ram",
            "shani",
            "ROHIT"
    })
    public void testfindByUserName(String name){

         assertNotNull(userRepository.findByUserName(name), "failed for :" + name);

    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a+b);
    }
}
