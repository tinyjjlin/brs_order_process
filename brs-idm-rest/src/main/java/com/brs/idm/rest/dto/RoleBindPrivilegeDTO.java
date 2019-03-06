package com.brs.idm.rest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/1/15
 */
@Data
public class RoleBindPrivilegeDTO {
    private String roleId;
    private List<String>privilegeIds;
}
