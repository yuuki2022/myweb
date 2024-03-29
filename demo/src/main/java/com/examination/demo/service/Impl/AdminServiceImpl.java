package com.examination.demo.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.demo.dao.AdminDAO;
import com.examination.demo.dataobject.AdminDO;
import com.examination.demo.model.Admin;
import com.examination.demo.service.AdminService;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin login(String adminName, String saltPassword) {
        AdminDO adminDO =  adminDAO.login(adminName, saltPassword);
        //AdminDO adminDO = adminDAO.selectOne(Wrappers.<AdminDO>lambdaQuery().eq(AdminDO::getAdminName, adminName).eq(AdminDO::getSaltPassword, saltPassword));
        if(adminDO == null){
            return null;
        }
        Admin admin = new Admin();
        admin.setAdminName(adminDO.getAdminName());
        admin.setSaltPassword(adminDO.getSaltPassword());
        admin.setRole(adminDO.getRole());
        return admin;
    }

    @Override
    public void register(String adminName, String saltPassword, String role) {
        adminDAO.register(adminName, saltPassword, role);
        //adminDAO.insert(new AdminDO(adminName, saltPassword, role));
    }

    @Override
    public Admin selectByAdminName(String adminName) {
        AdminDO adminDO = adminDAO.selectByAdminName(adminName);
       // AdminDO adminDO = adminDAO.selectOne(Wrappers.<AdminDO>lambdaQuery().eq(AdminDO::getAdminName, adminName));
       if(adminDO == null){
            return null;
       }

       Admin admin = new Admin();
       admin.setAdminName(adminDO.getAdminName());
       admin.setSaltPassword(adminDO.getSaltPassword());
       admin.setRole(adminDO.getRole());
       
       return admin;
    }

    @Override
    public void updatePassword(String adminName, String saltPassword) {
        adminDAO.updatePassword(adminName, saltPassword);
        //adminDAO.update(null, Wrappers.<AdminDO>lambdaUpdate().eq(AdminDO::getAdminName, adminName).set(AdminDO::getSaltPassword, saltPassword));
    }

    @Override
    public void deleteStudent(String adminName) {
        adminDAO.deleteStudent(adminName);
        //adminDAO.delete(Wrappers.<AdminDO>lambdaQuery().eq(AdminDO::getAdminName, adminName));
    }

    @Override
    public List<Admin> getAllAdmin() {
        List<AdminDO> adminDOList = adminDAO.getAllAdmin();
        List<Admin> adminList = new ArrayList<>();
        for (AdminDO adminDO : adminDOList) {
            Admin admin = new Admin();
            admin.setAdminName(adminDO.getAdminName());
            admin.setRole(adminDO.getRole());
            adminList.add(admin);
        }
        return adminList;
    }

}
