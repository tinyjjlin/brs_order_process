package com.brs.orderprocess.service;

import com.brs.orderprcoess.api.AbstractCandidateUserHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据负责人候选用户
 * @author tiny lin
 * @date 2019/3/5
 */
public class DataDirectorCandidateUser extends AbstractCandidateUserHandler {
    @Override
    public List<String> getUserList() {
        List<String>users = new ArrayList<>();
        users.add("pick");
        users.add("month");
        return users;
    }
}
