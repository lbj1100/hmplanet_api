package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.planet.domain.PlanetUsers;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.PlanetUsersMapper;
import com.ruoyi.system.service.IPlanetUsersService;

/**
 * 星球用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
// TODO: 测试数据源切换，这里更改为从从库读取数据，如果类和方法上都有注解，方法上的注解优先级更高！
@DataSource(DataSourceType.SLAVE)
@Service
public class PlanetUsersServiceImpl implements IPlanetUsersService 
{
    @Autowired
    private PlanetUsersMapper planetUsersMapper;

    /**
     * 查询星球用户信息
     * 
     * @param userId 星球用户信息主键
     * @return 星球用户信息
     */
    @Override
    public PlanetUsers selectPlanetUsersByUserId(Long userId)
    {
        return planetUsersMapper.selectPlanetUsersByUserId(userId);
    }

    /**
     * 查询星球用户信息列表
     * 
     * @param planetUsers 星球用户信息
     * @return 星球用户信息
     */
    // TODO: 高优先级，可判断是否成功切换数据源
    @DataSource(DataSourceType.SWITCH)
    @Override
    public List<PlanetUsers> selectPlanetUsersList(PlanetUsers planetUsers)
    {
        return planetUsersMapper.selectPlanetUsersList(planetUsers);
    }

    /**
     * 新增星球用户信息
     * 
     * @param planetUsers 星球用户信息
     * @return 结果
     */
    @Override
    public int insertPlanetUsers(PlanetUsers planetUsers)
    {
        return planetUsersMapper.insertPlanetUsers(planetUsers);
    }

    /**
     * 修改星球用户信息
     * 
     * @param planetUsers 星球用户信息
     * @return 结果
     */
    @Override
    public int updatePlanetUsers(PlanetUsers planetUsers)
    {
        return planetUsersMapper.updatePlanetUsers(planetUsers);
    }

    /**
     * 批量删除星球用户信息
     * 
     * @param userIds 需要删除的星球用户信息主键
     * @return 结果
     */
    @Override
    public int deletePlanetUsersByUserIds(Long[] userIds)
    {
        return planetUsersMapper.deletePlanetUsersByUserIds(userIds);
    }

    /**
     * 删除星球用户信息信息
     * 
     * @param userId 星球用户信息主键
     * @return 结果
     */
    @Override
    public int deletePlanetUsersByUserId(Long userId)
    {
        return planetUsersMapper.deletePlanetUsersByUserId(userId);
    }

    // 检查用户名是否唯一
    @Override
    public boolean checkUserNameUnique(PlanetUsers planetUsers) {
        Long userId = StringUtils.isNull(planetUsers.getUserId()) ? -1L : planetUsers.getUserId();
        PlanetUsers info = planetUsersMapper.checkUserNameUnique(planetUsers.getUsername());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;

    }


    // 检查用户名是否唯一
    @Override
    public boolean checkEmailUnique(PlanetUsers planetUsers) {
        Long userId = StringUtils.isNull(planetUsers.getUserId()) ? -1L : planetUsers.getUserId();
        PlanetUsers info = planetUsersMapper.checkEmailUnique(planetUsers.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;

    }

    // 检查手机号是否唯一
    @Override
    public boolean checkPhoneUnique(PlanetUsers planetUsers) {
        Long userId = StringUtils.isNull(planetUsers.getUserId()) ? -1L : planetUsers.getUserId();
        PlanetUsers info = planetUsersMapper.checkPhoneUnique(planetUsers.getPhoneNumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    @Override
    public PlanetUsers selectUserByUserName(String username) {
        return planetUsersMapper.selectUserByUserName(username);
    }
}
