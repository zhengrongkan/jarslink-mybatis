package com.zhengrongkan.api.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.jarslink.api.Module;
import com.alipay.jarslink.api.ModuleManager;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Author: ryan
 * @Description: 人员controller层
 * @Date: Created in 下午1:48 2018/4/13
 * @Modified By:
 */
@RestController
public class PersonController {
    @Autowired
    private ModuleManager manager;

    @ApiOperation(value = "获取Module信息")
    @GetMapping("/info")
    public String getInfo() {
        return JSON.toJSONString(manager.getModules());
    }

    /**
     * 查询人员接口
     *
     * @param module 模块名称
     * @param action action名称
     * @param uuid   人员UUID
     * @return
     */
    @ApiOperation(value = "获取人员信息")
    @GetMapping("/{module}/{action}/{uuid}")
    public String getPerson(@PathVariable String module, @PathVariable String action, @PathVariable String uuid) {
        Module moduleInstance = manager.find(module);
        Assert.assertNotNull(moduleInstance);
        Object result = "";
        switch (action) {
            case "getPerson":
                result = moduleInstance.doAction(action, uuid);
                break;
            default:
                break;
        }
        return JSON.toJSONString(result);
    }


    /**
     * 添加人员信息
     * （该接口将HTTP转发给module处理）
     *
     * @param request
     * @param response
     */
    @ApiOperation(value = "添加人员")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(paramType = "path", name = "moduleName", value = "moduleName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "path", name = "actionName", value = "actionName", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "name", value = "姓名", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "age", value = "年龄", required = true, dataType = "String"),
                    @ApiImplicitParam(paramType = "query", name = "gender", value = "性别0:未知,1:男,2:女", required = true, dataType = "String")
            }

    )
    @RequestMapping(value = "/{module}/{action}", method = {RequestMethod.POST})
    public void process(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> pathVariables = resolvePathVariables(request);
        String module = pathVariables.get("module");
        String action = pathVariables.get("action");
        Module moduleInstance = manager.find(module);
        Assert.assertNotNull(moduleInstance);
        Object result = "";
        switch (action) {
            case "addPerson":
                result = moduleInstance.doAction(action, request);
                break;
            default:
                break;
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().append(JSON.toJSONString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取PATH
     *
     * @param request
     * @return
     */
    private Map<String, String> resolvePathVariables(HttpServletRequest request) {
        return (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
    }

    /**
     * curl 'localhost:8080/remove/person-service'
     *
     * @param module
     */
    @GetMapping("/remove/{module}")
    public void remove(@PathVariable String module) {
        manager.remove(module);
    }

}
