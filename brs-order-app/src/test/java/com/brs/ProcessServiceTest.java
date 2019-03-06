package com.brs;

import com.brs.order.application.BrsOrderApplication;
import com.brs.order.service.ProcessHandlerServiceImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BrsOrderApplication.class)
public class ProcessServiceTest {
    @Autowired
    private ProcessHandlerServiceImpl processService;



}
