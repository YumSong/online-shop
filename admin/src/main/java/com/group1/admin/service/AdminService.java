package com.group1.admin.service;

import com.group1.core.entity.admin.Admin;

public interface AdminService {
    Admin register(Admin admin);

    Admin login(Admin admin);
}
