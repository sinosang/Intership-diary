package com.mp.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.Entity.Stu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author : zzy
 * @date : 2023/4/11 10:07
 */
@Mapper
public interface StuMapper extends BaseMapper<Stu> {

    @Select("select * from yhlm_test.stu where username = #{username}")
    Stu SearchName(String username);

    @Select("select del_flag from yhlm_test.stu where id = #{id}")
    int findFlag(String id);
}
