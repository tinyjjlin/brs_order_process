package com.brs.idm.api.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/28
 */
@Data
public class RolePrivilegeDTO {
    private String roleId;
    private String roleName;

    private List<PrivilegeDTO> privilegeList;
}
