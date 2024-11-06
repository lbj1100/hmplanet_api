package com.ruoyi.web.controller.planet;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.PlanetAjaxResult;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.planet.domain.PlanetUsers;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.service.PlanetLoginService;
import com.ruoyi.system.service.IPlanetUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2024-10-10
 */
@RestController
@RequestMapping("/planet")
public class PlanetLoginController extends BaseController
{
    @Autowired
    private IPlanetUsersService planetUsersService;

    @Autowired
    private PlanetLoginService loginService;


    /**
     * 用户登录
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody planetUsers)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(planetUsers.getUsername(), planetUsers.getPassword());
        ajax.put("token", token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public PlanetAjaxResult getInfo()
    {
        PlanetUsers user = SecurityUtils.getPlanetLoginUser().getUser();
//        // 角色集合
//        Set<String> roles = permissionService.getRolePermission(user);
//        // 权限集合
//        Set<String> permissions = permissionService.getMenuPermission(user);
        PlanetAjaxResult ajax = PlanetAjaxResult.success();
        ajax.put("user", user);
//        ajax.put("roles", roles);
//        ajax.put("permissions", permissions);
        return ajax;
    }

    @PostMapping("/saveImage")
    public AjaxResult saveImage(@RequestBody MultipartFile file) {
        System.out.println("file: " + file);
        if (file.isEmpty()) {
            return AjaxResult.error("No file uploaded");
        }
        try {
            // 保存文件到服务器目录
            String fileName = file.getOriginalFilename();
            String filePath = "D:/planet/images/" + fileName;
            File dest = new File(filePath);
            file.transferTo(dest); // 将文件写入目标位置

            return AjaxResult.success("File uploaded successfully", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("File upload failed");
        }
    }

}
