package com.ruoyi.system.service;

import com.ruoyi.common.planet.domain.PlanetUsers;

import java.util.List;

/**
 * 星球用户信息Service接口
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
public interface IPlanetUsersService 
{
    /**
     * 查询星球用户信息
     * 
     * @param userId 星球用户信息主键
     * @return 星球用户信息
     */
    public PlanetUsers selectPlanetUsersByUserId(Long userId);

    /**
     * 查询星球用户信息列表
     * 
     * @param planetUsers 星球用户信息
     * @return 星球用户信息集合
     */
    public List<PlanetUsers> selectPlanetUsersList(PlanetUsers planetUsers);

    /**
     * 新增星球用户信息
     * 
     * @param planetUsers 星球用户信息
     * @return 结果
     */
    public int insertPlanetUsers(PlanetUsers planetUsers);

    /**
     * 修改星球用户信息
     * 
     * @param planetUsers 星球用户信息
     * @return 结果
     */
    public int updatePlanetUsers(PlanetUsers planetUsers);

    /**
     * 批量删除星球用户信息
     * 
     * @param userIds 需要删除的星球用户信息主键集合
     * @return 结果
     */
    public int deletePlanetUsersByUserIds(Long[] userIds);

    /**
     * 删除星球用户信息信息
     * 
     * @param userId 星球用户信息主键
     * @return 结果
     */
    public int deletePlanetUsersByUserId(Long userId);

    boolean checkUserNameUnique(PlanetUsers planetUsers);

    boolean checkEmailUnique(PlanetUsers planetUsers);

    boolean checkPhoneUnique(PlanetUsers planetUsers);

    PlanetUsers selectUserByUserName(String username);
}
