package com.mp.Mapper;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.Entity.Stu;
import com.mp.Entity.Tea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/17 14:46
 */
@Mapper
public interface TeaMapper extends BaseMapper<Tea> {

}
