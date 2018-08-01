package com.group1.core.entity.admin.dao.impl;

import com.group1.core.entity.admin.dao.AdminRepository;
import com.group1.core.entity.admin.model.Admin;
import com.group1.core.utils.base.impl.JPARepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepositoryImpl extends JPARepositoryImpl<Admin,String> implements AdminRepository {
//    @Override
//    public String getTableName() {
//        return Admin.class.getName();
//    }
}