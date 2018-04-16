package com.zhengrongkan.personservice.service;

import com.zhengrongkan.personservice.entity.Person;

/**
 * 人员接口
 *
 * @Author: ryan
 * @Description:
 * @Date: Created in 下午4:07 2018/4/16
 * @Modified By:
 */
public interface IPersonService {
    /**
     * 主键查询人员
     *
     * @param str
     * @return
     */
    Person selectByUuid(String str);

    /**
     * 添加人员
     *
     * @param person
     * @return
     */
    String addPerson(Person person);

}
