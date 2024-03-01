package com.examination.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.examination.demo.dataobject.AdminDO;

@Mapper
public interface AdminDAO {

    
    @Update("update admin set salt_password = #{saltPassword} where admin_name = #{adminName}")
    void updatePassword(String adminName, String saltPassword);

    @Select("select * from admin where admin_name = #{adminName} limit 1")
    AdminDO selectByAdminName(String adminName);

    @Insert("insert into admin(admin_name, salt_password, role) values(#{adminName}, #{saltPassword}, #{role})")
    void register(String adminName, String saltPassword, String role);

    @Select("select * from admin where admin_name = #{adminName} and salt_password = #{saltPassword}")
    AdminDO login(String adminName, String saltPassword);

    
}
