package com.zz;

import com.zz.dao.ExamDao;
import com.zz.dao.PapersDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PapersTest {
    @Autowired
    private PapersDao papersDao;
    @Test
   public void getPaperContent(){
    }
}
