package com.zhengrongkan.personservice.action;


import com.alipay.jarslink.api.Action;
import com.zhengrongkan.personservice.entity.Person;
import com.zhengrongkan.personservice.service.IPersonService;

import javax.annotation.Resource;

/**
 * 获取人员信息
 *
 * @Author: ryan
 * @Description:
 * @Date: Created in 下午3:16 2018/4/13
 * @Modified By:
 */
public class GetPerson implements Action<String, Person> {
    @Resource
    IPersonService personService;

    @Override
    public Person execute(String uuid) {
        return personService.selectByUuid(uuid);
    }

    @Override
    public String getActionName() {
        return "getPerson";
    }
}
