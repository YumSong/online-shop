package com.group1.admin.dao;

import com.group1.core.entity.admin.Admin;
import com.group1.core.utils.base.model.Page;
import com.group1.core.utils.base.model.Pageable;
import com.group1.core.utils.base.model.Sort;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class AdminRepositoryTest {

    @Resource
    private AdminRepository adminRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void saveTest(){
        for (int i = 0; i < 10; i++) {
            Admin admin = new Admin();
            admin.setLoginName("song");
            admin.setPassword("123456");
            Assert.assertNotNull(adminRepository.save(admin));
        }
    }

    @Test
    public void deleteTest(){
        System.out.println(adminRepository.delete("8a5e9d1864f89fc20164f89fc8ef0003"));
    }

    @Test
    public void findAllByPage(){
        Sort sort = new Sort("loginName","DESC");
        Pageable pageable = new Pageable(2,2,sort);
        Page<Admin> adminPage = adminRepository.findAll(pageable);
        System.out.println(adminPage);
    }
}