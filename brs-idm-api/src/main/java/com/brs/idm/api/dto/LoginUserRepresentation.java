package com.brs.idm.api.dto;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class LoginUserRepresentation {
    private String userId;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String pictureId;
    private String userType;

}
