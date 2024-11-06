package com.ruoyi.system.mapper;

import com.ruoyi.common.planet.domain.PlanetUsers;

import java.util.List;

/**
 * 星球用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
public interface PlanetUsersMapper 
{

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public PlanetUsers selectPlanetUsersByUserId(Long userId);

    /**
     * 查询用户信息列表
     *
     * @param planetUsers 用户信息
     * @return 用户信息集合
     */
    public List<PlanetUsers> selectPlanetUsersList(PlanetUsers planetUsers);

    /**
     * 新增用户信息
     *
     * @param planetUsers 用户信息
     * @return 结果
     */
    public int insertPlanetUsers(PlanetUsers planetUsers);

    /**
     * 修改用户信息
     *
     * @param planetUsers 用户信息
     * @return 结果
     */
    public int updatePlanetUsers(PlanetUsers planetUsers);

    /**
     * 删除用户信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deletePlanetUsersByUserId(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePlanetUsersByUserIds(Long[] userIds);


    PlanetUsers checkUserNameUnique(String username);

    PlanetUsers checkEmailUnique(String email);

    PlanetUsers checkPhoneUnique(String phoneNumber);

    PlanetUsers selectUserByUserName(String username);
}
