package com.brs;

import com.brs.idm.api.RoleConstant;
import com.brs.order.application.BrsOrderApplication;
import com.brs.order.service.ProcessHandlerServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tiny lin
 * @date 2019/2/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BrsOrderApplication.class)
public class OrderProcessServicetTest {

    @Autowired
    private ProcessHandlerServiceImpl orderProcessService;

    @Test
    public void test(){
        String userId1 =  orderProcessService.getUserIdFromList(RoleConstant.ROLE_MANAGER);
        System.out.println("manger"+userId1);
        String userId12=  orderProcessService.getUserIdFromList(RoleConstant.ROLE_EDITOR_DIRECTOR);
        System.out.println("dr"+userId12);
        String userId13 =  orderProcessService.getUserIdFromList(RoleConstant.ROLE_DATA_DIRECTOR);
        System.out.println("dd"+userId13);
        String userId14 =  orderProcessService.getUserIdFromList(RoleConstant.ROLE_SUBMITTER);
        System.out.println("sub"+userId14);

    }


}
