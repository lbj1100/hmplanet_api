package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.AbstractLoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

@EqualsAndHashCode(callSuper = false)
@Data // Lombok注解，自动生成getter、setter等
public class LoginUser extends AbstractLoginUser {
    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 用户信息
     */
    private SysUser user;

    public LoginUser() {
    }

    public LoginUser(SysUser user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }


    public LoginUser(Long userId, Long deptId, SysUser user, Set<String> permissions)
    {
        this.setUserId(userId);
        this.deptId = deptId;
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 这里可以返回权限集合
        return null; // 可根据实际需求实现
    }
}
