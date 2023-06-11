package com.mp.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.Entity.Question;
import com.mp.Entity.Stu;
import com.mp.Entity.Tea;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author : zzy
 * @date : 2023/5/5 16:44
 */
@Mapper
public interface QuestionMapper extends BaseMapper <Question> {

}
