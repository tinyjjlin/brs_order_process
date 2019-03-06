package com.brs.idm.rest.api.privilege;

import com.brs.idm.common.model.RestResult;
import com.brs.idm.api.IdmService;
import com.brs.idm.api.dto.PrivilegeDTO;
import com.brs.idm.persistence.entity.PrivilegeEntity;
import com.brs.idm.rest.dto.PrivilegeRoleMappingDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@RestController
public class PrivilegeController {

    @Autowired
    private IdmService identityService;


    /**
     * get all privilege list
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "get all privilege list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "要查看的页数", dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "每页显示的数据行数", dataType = "int", defaultValue = "10")
    })
    @GetMapping("/api/idm/privilege")
    public RestResult getPrivilegeAll(@RequestParam(value = "page",required = false, defaultValue = "1") Integer page, @RequestParam(value = "limit",required = false, defaultValue = "10") Integer size) {
        PageHelper.startPage(page,size );
        List<PrivilegeDTO> list = identityService.getPrivilegeAll();
        PageInfo pageInfo = new PageInfo(list);
        return new RestResult(2000, "get all privilege list success", pageInfo);
    }

    /**
     * create new privilege
     * @param privilegeEntity
     * @return
     */
    @ApiOperation("create new privilege")
    @PostMapping("/api/idm/privilege")
    RestResult createNewPrivilege(@RequestBody PrivilegeEntity privilegeEntity){
        identityService.savePrivilege(privilegeEntity);
        return new RestResult(2000,"create new privilege success" );
    }

    /**
     * edit privilege
     * @param privilegeEntity
     * @return
     */
    @ApiOperation("edit privilege")
    @PutMapping("/api/idm/privilege")
    RestResult editPrivilege(@RequestBody PrivilegeEntity privilegeEntity){
        identityService.updatePrivilege(privilegeEntity);
        return new RestResult(2000,"edit privilege success" );
    }

    /**
     * delete privilege
     * @param privId
     * @return
     */
    @ApiOperation("delete privilege")
    @DeleteMapping("/api/idm/privilege")
    RestResult deletePrivilege(@RequestParam("privId")String privId){
        identityService.deletePrivilege(privId);
        return new RestResult(2000,"delete privilege success" );
    }

    /**
     * bind role array
     * @param privilegeRoleMappingDto
     * @return
     */
    @ApiOperation("bind roles")
    @PostMapping("/api/idm/privilegeBindRoles")
    RestResult bindRoles(@RequestBody PrivilegeRoleMappingDto privilegeRoleMappingDto){
        identityService.addRolePrivilegeMapping(privilegeRoleMappingDto.getRoleIds(), privilegeRoleMappingDto.getPrivId());
        return new RestResult(2000,"bind roles success" );
    }

}
