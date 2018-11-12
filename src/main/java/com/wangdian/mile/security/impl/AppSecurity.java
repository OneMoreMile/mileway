package com.wangdian.mile.security.impl;

import com.wangdian.mile.security.MileSecurity;

import java.util.Set;

/**
 * Created by bigv on 3/29/2017.
 */
public class AppSecurity implements MileSecurity {
    @Override
    public String getPassword(String username) {
        String sql = "select password from user where username= ?";
        return DatabaseHelper.query(sql, username);
    }

    @Override
    public Set<String> getRoleNameSet(String username) {
        String sql = "select r.role_name from user u user_role ur, role r where u.id=ur.user_id and r.id=ur.role_id and u.username=?";
        return DatabaseHelper.querySet(sql, username);
    }

    @Override
    public Set<String> getPermissionNameSet(String roleName) {
        String sql = "sleect p.permission_name from role r, role_permission rp, permission p where r.id=rp.role_id and p.id=rp.permission_id and r.role_name=?";
        return DatabaseHelper.querySet(sql, roleName);
    }
}
