package com.zhengrongkan.personservice.action;

import com.alipay.jarslink.api.Action;
import com.zhengrongkan.personservice.entity.Person;
import com.zhengrongkan.personservice.service.IPersonService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 添加人员信息
 *
 * @Author: ryan
 * @Description:
 * @Date: Created in 下午3:40 2018/4/13
 * @Modified By:
 */
public class AddPerson implements Action<HttpServletRequest, String> {
    @Resource
    IPersonService personService;

    @Override
    public String execute(HttpServletRequest request) {
        String name;
        if ((name = request.getParameter("name")) == null) {
            return "name不能为空";
        }
        String age;
        if ((age = request.getParameter("age")) == null) {
            return "age不能为空";
        }
        String gender;
        if ((gender = request.getParameter("gender")) == null) {
            return "gender不能为空";
        }
        Person person = new Person();
        person.setName(name);
        person.setAge(Integer.parseInt(age));
        person.setGender(Integer.parseInt(gender));
        return personService.addPerson(person);

    }

    @Override
    public String getActionName() {
        return "addPerson";
    }
}
