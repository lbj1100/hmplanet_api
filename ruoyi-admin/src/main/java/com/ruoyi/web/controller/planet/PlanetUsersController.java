package com.ruoyi.web.controller.planet;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.planet.domain.PlanetUsers;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IPlanetUsersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
@RestController
@RequestMapping("/planet/users")
public class PlanetUsersController extends BaseController
{
    @Autowired
    private IPlanetUsersService planetUsersService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('planet:users:list')")
    @GetMapping("/list")
    public TableDataInfo list(PlanetUsers planetUsers)
    {
        startPage();
        List<PlanetUsers> list = planetUsersService.selectPlanetUsersList(planetUsers);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('planet:users:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PlanetUsers planetUsers)
    {
        List<PlanetUsers> list = planetUsersService.selectPlanetUsersList(planetUsers);
        ExcelUtil<PlanetUsers> util = new ExcelUtil<PlanetUsers>(PlanetUsers.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('planet:users:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(planetUsersService.selectPlanetUsersByUserId(userId));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('planet:users:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PlanetUsers planetUsers)
    {
        // 验证用户名是否唯一
        if (!planetUsersService.checkUserNameUnique(planetUsers))
        {
            return error("新增用户'" + planetUsers.getUsername() + "'失败，登录账号已存在");
        }
        // 验证手机号码是否唯一
        else if (StringUtils.isNotEmpty(planetUsers.getPhoneNumber()) && !planetUsersService.checkPhoneUnique(planetUsers))
        {
            return error("新增用户'" + planetUsers.getUsername() + "'失败，手机号码已存在");
        }
        // 验证邮箱账号是否唯一
        else if (StringUtils.isNotEmpty(planetUsers.getEmail()) && !planetUsersService.checkEmailUnique(planetUsers))
        {
            return error("新增用户'" + planetUsers.getUsername() + "'失败，邮箱账号已存在");
        }
        // 密码加密
        planetUsers.setCreateBy(getUsername());
        planetUsers.setPassword(SecurityUtils.encryptPassword(planetUsers.getPassword()));
        return toAjax(planetUsersService.insertPlanetUsers(planetUsers));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('planet:users:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PlanetUsers planetUsers)
    {
        return toAjax(planetUsersService.updatePlanetUsers(planetUsers));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('planet:users:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(planetUsersService.deletePlanetUsersByUserIds(userIds));
    }
}
