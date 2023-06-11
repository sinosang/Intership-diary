package com.mp.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.Dto.StuDto;
import com.mp.Entity.Question;
import com.mp.Entity.Stu;
import com.mp.Mapper.QuestionMapper;
import com.mp.Mapper.StuMapper;
import com.mp.Service.QuestionService;
import com.mp.Service.StuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zzy
 * @date : 2023/5/5 16:57
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private StuService stuService;

    @Autowired
    private QuestionMapper questionMapper;

//    不要在实现类里再次调用questionService，否则会报错，用 this. 来代替questionService
//    @Autowired
//    private QuestionService questionService;

    @Override
    public StuDto getPaper (String id) {

        StuDto stuDto = new StuDto();
        Stu StuRes = stuService.getById(id);
        String getStuRes = String.valueOf(StuRes);
        System.out.println(getStuRes);
        String[] Strs = getStuRes.split("[=,)]");
        String subject = Strs[13];
        String paper = Strs[15];
        System.out.println("subject:"+subject);
        System.out.println("paper:"+paper);

//        将读取到的id学生结果录入到dto中
        BeanUtils.copyProperties(StuRes,stuDto);

        LambdaQueryWrapper<Question> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Question::getSubject,subject);
        queryWrapper.eq(Question::getPaper,paper);
        List<Question> questions = this.list(queryWrapper);
        stuDto.setStudentsExam(questions);
        return stuDto;
    }

    @Override
    public List<Question> ChangeQuestion (String QuestionId,String subject,String paper,String value,String TeaId){
        UpdateWrapper updateQuestion = new UpdateWrapper();
        updateQuestion.eq("id",QuestionId);
        updateQuestion.set("subject",subject);
        updateQuestion.set("paper",paper);
        updateQuestion.set("value",value);
        updateQuestion.set("update_by",TeaId);
        baseMapper.update(null,updateQuestion);

        QueryWrapper<Question > selectQuestion = new QueryWrapper<>();
        selectQuestion.eq("id",QuestionId);
        List <Question> list = questionMapper.selectList(selectQuestion);
        return list;
    }
}
