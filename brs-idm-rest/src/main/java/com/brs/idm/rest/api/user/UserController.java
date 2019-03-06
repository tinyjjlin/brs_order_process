package com.brs.idm.rest.api.user;

import com.brs.idm.api.dto.UserRoleDTO;
import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.common.model.RestResult;
import com.brs.idm.api.IdmService;
import com.brs.idm.api.dto.LoginUserRepresentation;
import com.brs.idm.persistence.entity.UserEntity;

import com.brs.idm.rest.dto.UserBindRoleDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/20
 */
@RestController
@Api
public class UserController {

    @Autowired
    private IdmService identityService;

    /**
     * get all user list
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "get all user list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    @GetMapping("/api/idm/user")
    public RestResult getUserAll(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size );
        List<LoginUserRepresentation> list = identityService.getUserAll();
        PageInfo pageInfo = new PageInfo(list);
        return new RestResult(2000, "get all user list success", pageInfo);
    }

    /**
     * 分页查看用户角色信息列表
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "分页查看用户角色信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userId", value = "员工号", dataType = "string"),
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    @GetMapping("/api/idm/user&role")
    @Authentication
    public RestResult userRoleInfo(   @RequestParam(value = "userId",required = false) String  userId,@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size );
        List<UserRoleDTO> userRoles = identityService.getUserRoleList(userId);
        PageInfo pageInfo = new PageInfo(userRoles);
        return new RestResult(2000, "分页查看用户角色信息列表操作成功！", pageInfo);
    }



    /**
     * add new user
     * @param userEntity
     * @return
     */
    @ApiOperation("add new user")
    @PostMapping("/api/idm/user")
    public RestResult  createNewUser(@RequestBody UserEntity userEntity){
        identityService.saveUser(userEntity);
        return new RestResult(2000, "add new user",null);
    }

    /**
     * 用户添加角色
     */
    @ApiOperation(value = "用户添加角色")
    @PostMapping("/api/idm/bindRole")
    public RestResult bindRole(@RequestBody UserBindRoleDTO bindRoleDto){
        if(bindRoleDto.getRoleIds()!= null){
            identityService.bindUserRole(bindRoleDto.getUserId(),bindRoleDto.getRoleIds() );
        }
        return new RestResult(2000, "用户添加角色成功！");
    }



}
