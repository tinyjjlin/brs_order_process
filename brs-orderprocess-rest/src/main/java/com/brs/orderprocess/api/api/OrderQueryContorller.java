package com.brs.orderprocess.api.api;


import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.model.RestResult;

import com.brs.orderprcoess.api.OrderProcessQueryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2019/2/27
 */
@RestController
public class OrderQueryContorller {

    @Autowired
    private OrderProcessQueryService orderProcessQueryService;

    @ApiOperation("获取个人订单列表2")
    @GetMapping("/api/order/task/query")
    @Authentication
    public RestResult orderTaskRecordList(@RequestHeader("Authorization")String token){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        List<Object> tasks = orderProcessQueryService.getTaskOrderRecordList(assignee);
        return new RestResult(2000,"获取订单列表",tasks );
    }

    @ApiOperation("分页获取个人订单列表2")
    @GetMapping("/api/order/task/query/paging")
    @Authentication
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    public RestResult orderTaskRecordPaging(@RequestHeader("Authorization")String token,@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        Map<String,Object> map = orderProcessQueryService.getTaskOrderRecordPaging(assignee,page ,size );
        return new RestResult(2000,"分页获取个人订单列表",map );
    }

    @ApiOperation("获取订单历史记录2")
    @GetMapping("/api/order/history/query")
    @Authentication
    public RestResult historicOrderRecordList(@RequestHeader("Authorization")String token){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        List<Object> orderHistoryRecords = orderProcessQueryService.getHistoricOrderRecordList(assignee);
        return new RestResult(2000,"获取订单历史记录",orderHistoryRecords );
    }

    @ApiOperation("分页获取订单历史记录2")
    @GetMapping("/api/order/history/query/paging")
    @Authentication
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    public RestResult historicOrderRecordPaging(@RequestHeader("Authorization")String token,@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        Map<String,Object> map = orderProcessQueryService.getHistoricOrderRecordPaging(assignee,page ,size );
        return new RestResult(2000,"分页获取订单历史记录",map );
    }

    @ApiOperation("分页获取订单历史记录2")
    @GetMapping("/api/order/client/history/query/paging")
    @Authentication
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    public RestResult clientHistoricOrderRecordPaging(@RequestHeader("Authorization")String token,@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String assignee = empNo+"";
        Map<String,Object> map = orderProcessQueryService.getHistoricOrderRecordPaging("client",assignee,page ,size );
        return new RestResult(2000,"分页获取订单历史记录",map );
    }


}
