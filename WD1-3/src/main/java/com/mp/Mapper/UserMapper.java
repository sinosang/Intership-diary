package com.mp.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {

//    手动配置查找id的方法
    @Select("select * from jdbc_test.user where id = #{id}")
    User getIdHand(Integer id);

    /*
    1.在Mapper里定义实现该方法的具体SQL语句
    2.在Service的接口中定义方法的名称和所需要的参数
    3.在Service的implement中进行方法的继承和实现，定义return返回的参数
    4.在Controller中进行REST风格的接口编写
    5.使用R对象的方式来规范统一的参数
     */

    @Select("select * from jdbc_test.user where name = #{name}")
    User getNameHand(String name);

}
