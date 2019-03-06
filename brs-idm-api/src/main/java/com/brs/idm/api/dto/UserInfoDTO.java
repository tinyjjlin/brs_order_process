package com.brs.idm.api.dto;
import lombok.Data;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class UserInfoDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String pictureId;
    private String userType;
    /**
     * 角色列表
     */
    private List<String>roleIds;
}
