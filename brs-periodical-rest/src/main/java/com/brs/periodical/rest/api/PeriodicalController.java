package com.brs.periodical.rest.api;

import com.brs.idm.common.model.RestResult;
import com.brs.periodical.api.PeriodicalService;
import com.brs.periodical.api.domain.PeriodicalDomain;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/3/1
 */
@RestController
@Api
public class PeriodicalController {

    @Autowired
    private PeriodicalService periodicalService;

    /**
     * get all user list
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查看期刊信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示行数", dataType = "int", defaultValue = "10")
    })
    @GetMapping("/api/periodical/list_paging")
    public RestResult periodicalListByPaging(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size );
        List<PeriodicalDomain> list = periodicalService.getList();
        PageInfo pageInfo = new PageInfo(list);
        return new RestResult(2000, "get periodical list success", pageInfo);
    }

}
