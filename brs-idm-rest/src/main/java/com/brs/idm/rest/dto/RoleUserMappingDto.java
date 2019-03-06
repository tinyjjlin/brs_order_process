package com.brs.idm.rest.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class RoleUserMappingDto {
    private String roleId;
    private List<String> userIds;
}
