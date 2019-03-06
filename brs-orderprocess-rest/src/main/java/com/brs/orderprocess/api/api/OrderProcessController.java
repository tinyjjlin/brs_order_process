package com.brs.orderprocess.api.api;

import com.brs.idm.api.GenerateId;
import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.model.RestResult;

import com.brs.orderinfo.api.OrderService;
import com.brs.orderprcoess.api.OrderProcessHandlerService;
import com.brs.orderprocess.service.OrderProcessHandlerServiceImpl;
import com.brs.orderprocess.vo.NewOrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/2/14
 */
@RestController
@Api
public class OrderProcessController {

    @Autowired
    private OrderProcessHandlerServiceImpl processService;
    @Autowired
    private OrderService orderService;




    /**
     * 当客户创建新的订单后便开启了订单流程
     * @param newOrderVo
     * @return
     */
    @ApiOperation("启动订单流程")
    @PostMapping("/api/order/startProcess")
    @Authentication
    public RestResult startProcessInstance(@RequestHeader("Authorization")String token,@RequestBody NewOrderVo newOrderVo){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String creatorId = empNo+"";
        newOrderVo.setOrderId(GenerateId.generate());
        newOrderVo.setCreator(creatorId);
        orderService.createNewOrder(newOrderVo);
        Map<String,Object> variables = new HashMap<>(2);
        String processInstanceId = processService.startProcessWrapper(creatorId,newOrderVo.getOrderId());

        return new RestResult(2000,"start process success!" ,processInstanceId);
    }

    /**
     * 经理确认订单
     * @return
     */
    @ApiOperation("确认订单")
    @GetMapping("/api/order/confirmOrder")
    @Authentication
    public RestResult confirmOrder(@RequestHeader("Authorization")String token,@RequestParam("taskId")String taskId){
        processService.confirmOrder(taskId);
        return new RestResult(2000,"confirm order" );
    }



    /**
     * 编辑主管分派订单
     * @return
     */
    @ApiOperation("分派订单")
    @GetMapping("/api/order/dispatchOrder")
    @Authentication
    public RestResult dispatchOrder(@RequestParam("taskId")String taskId, @RequestParam("editorId")String editorId ){
        processService.dispatchOrder(taskId, editorId);
        return new RestResult(2000,"dispatch order" );
    }


    /**
     * 编辑出草稿
     * @return
     */
    @ApiOperation("编辑出草稿")
    @GetMapping("/api/writeDraft")
    @Authentication
    public RestResult writeDraft(@RequestParam("taskId")String taskId ){
        processService.writeDraftTask(taskId );
        return new RestResult(2000,"write a draft");
    }

    /**
     * 编辑完成文章
     * @return
     */
    @ApiOperation("编辑完成文章")
    @GetMapping("/api/completeArticle")
    @Authentication
    public RestResult completeArticle(@RequestParam("taskId")String taskId ){
        processService.completeArticle(taskId);
        return new RestResult(2000,"completeArticle");
    }


    /**
     * 实验室负责人分配任务
     * @return
     */
    @ApiOperation("分配实验")
    @GetMapping("/api/order/dispatchData")
    @Authentication
    public RestResult dispatchData(@RequestParam("taskId")String taskId, @RequestParam("dataProcessorId")String dataProcessorId ){
        processService.dispatchOrder(taskId, dataProcessorId);
        return new RestResult(2000,"dispatch order" );
    }
    /**
     * 实验人员处理数据
     * @param taskId
     * @return
     */
    @ApiOperation("实验人员处理数据")
    @GetMapping("/api/handleData")
    @Authentication
    public RestResult handleData(@RequestParam("taskId")String taskId){
        processService.handleDataTask(taskId);
        return new RestResult(2000,"handle data");
    }

}
