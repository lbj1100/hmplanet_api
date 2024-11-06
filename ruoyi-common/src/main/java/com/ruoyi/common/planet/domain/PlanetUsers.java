package com.ruoyi.common.planet.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 用户信息对象 planet_users
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlanetUsers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户唯一ID */
    private Long userId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 用户邮箱，必须唯一 */
    @Excel(name = "用户邮箱，必须唯一")
    private String email;

    /** 用户密码的哈希值 */
    @Excel(name = "用户密码的哈希值")
    private String password;

    /** 用户头像的URL地址 */
    @Excel(name = "用户头像的URL地址")
    private String avatarUrl;

    /** 用户个人简介 */
    @Excel(name = "用户个人简介")
    private String bio;

    /** 用户手机号 */
    @Excel(name = "用户手机号")
    private String phoneNumber;

    /** 逻辑删除标记（0: 未删除, 1: 已删除） */
    private Integer delFlag;

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("username", getUsername())
            .append("email", getEmail())
            .append("password", getPassword())
            .append("avatarUrl", getAvatarUrl())
            .append("bio", getBio())
            .append("phoneNumber", getPhoneNumber())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
