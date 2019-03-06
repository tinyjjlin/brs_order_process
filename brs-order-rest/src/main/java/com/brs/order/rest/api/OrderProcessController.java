package com.brs.order.rest.api;

import com.brs.idm.api.GenerateId;
import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.model.RestResult;
import com.brs.order.api.ProcessHandlerService;
import com.brs.order.api.vo.NewOrderDto;
import com.brs.order.api.OrderRecordQueryService;
import com.brs.order.api.OrderService;
import com.brs.order.api.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/14
 */
@RestController
@Api
public class OrderProcessController {

    @Autowired
    private OrderRecordQueryService orderRecordQueryService;
    @Autowired
    private ProcessHandlerService processService;
    @Autowired
    private OrderService orderService;




    @ApiOperation("获取个人订单列表")
    @GetMapping("/api/order/task")
    @Authentication
    public RestResult orderTaskRecordList(@RequestHeader("Authorization")String token){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        List<OrderTaskRepresentation> tasks = orderRecordQueryService.getOrderTaskRecordList(assignee);
        return new RestResult(2000,"获取订单列表",tasks );
    }

    @ApiOperation("分页获取个人订单列表")
    @GetMapping("/api/order/task/paging")
    @Authentication
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    public RestResult orderTaskRecordPaging(@RequestHeader("Authorization")String token,@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        OrderTaskRecordPaging orderTaskRecordPaging = orderRecordQueryService.getOrderTaskRecordPaging(assignee,page ,size );

        return new RestResult(2000,"分页获取个人订单列表",orderTaskRecordPaging );
    }

    @ApiOperation("获取订单历史记录")
    @GetMapping("/api/order/history")
    @Authentication
    public RestResult historicOrderRecordList(@RequestHeader("Authorization")String token,@RequestParam("variName")String variName){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        List<HistoricOrderRecord> orderHistoryRecords = orderRecordQueryService.getHistoricOrderRecordList(variName, assignee);
        return new RestResult(2000,"获取订单历史记录",orderHistoryRecords );
    }

    @ApiOperation("分页获取订单历史记录")
    @GetMapping("/api/order/history/paging")
    @Authentication
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    public RestResult historicOrderRecordPaging(@RequestHeader("Authorization")String token,@RequestParam("variName")String variName,@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        HistoricOrderRecordPaging historicOrderRecordPagings = orderRecordQueryService.getOrderHistoryListPaging(variName, assignee,page,size);
        return new RestResult(2000,"分页获取订单历史记录",historicOrderRecordPagings );
    }

    @ApiOperation("获取组订单列表")
    @GetMapping("/api/order/group/task")
    @Authentication
    public RestResult getGroupTaskList(@RequestParam("groupName") String groupName){
        List<OrderTaskRepresentation> tasks = orderRecordQueryService.getGroupOrderTaskList(groupName);
        return new RestResult(2000,"获取订单列表",tasks );
    }





























    /**
     * 当客户创建新的订单后便开启了订单流程
     * @param newOrderDto
     * @return
     */
    @ApiOperation("启动订单流程")
    @PostMapping("/api/order/startProcess")
    @Authentication
    public RestResult startProcessInstance(@RequestHeader("Authorization")String token,@RequestBody NewOrderDto newOrderDto){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String creatorId = empNo+"";
        newOrderDto.setOrderId(GenerateId.generate());
        newOrderDto.setCreator(creatorId);
        orderService.createNewOrder(newOrderDto);
        String processInstanceId = processService.startProcess(creatorId,newOrderDto.getOrderId());

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
        processService.writeDraft(taskId );
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
        processService.handleData(taskId);
        return new RestResult(2000,"handle data");
    }

}
