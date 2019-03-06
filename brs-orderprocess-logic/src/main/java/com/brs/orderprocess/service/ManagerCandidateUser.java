package com.brs.orderprocess.service;

import com.brs.orderprcoess.api.AbstractCandidateUserHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 总经理候选用户
 * @author tiny lin
 * @date 2019/3/5
 */
public class ManagerCandidateUser extends AbstractCandidateUserHandler {
    @Override
    public List<String> getUserList() {
        List<String>users = new ArrayList<>();
        users.add("tom");
        users.add("jack");
        return users;
    }
}
