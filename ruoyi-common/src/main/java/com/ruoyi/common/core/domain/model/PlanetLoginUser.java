package com.ruoyi.common.core.domain.model;

import com.ruoyi.common.planet.domain.PlanetUsers;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@EqualsAndHashCode(callSuper = false)
@Data // Lombok注解，自动生成getter、setter等
public class PlanetLoginUser extends AbstractLoginUser {
    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     */
    private PlanetUsers user;

    public PlanetLoginUser() {
    }

    public PlanetLoginUser(PlanetUsers user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 这里可以返回权限集合
        return null; // 可根据实际需求实现
    }
}
