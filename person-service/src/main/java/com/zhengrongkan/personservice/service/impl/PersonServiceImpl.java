package com.zhengrongkan.personservice.service.impl;

import com.zhengrongkan.personservice.dao.PersonMapper;
import com.zhengrongkan.personservice.entity.Person;
import com.zhengrongkan.personservice.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * 人员接口实现类
 *
 * @Author: ryan
 * @Description:
 * @Date: Created in 下午4:08 2018/4/16
 * @Modified By:
 */
public class PersonServiceImpl implements IPersonService {
    @Autowired
    PersonMapper personMapper;

    @Override
    public Person selectByUuid(String str) {
        return personMapper.selectByPrimaryKey(str);
    }

    @Override
    public String addPerson(Person person) {
        String uuid = UUID.randomUUID().toString();
        person.setUuid(uuid);
        personMapper.insertSelective(person);
        return uuid;
    }
}
