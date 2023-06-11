package com.mp.Service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.Entity.Stu;
import com.mp.Service.StuService;
import com.mp.Mapper.StuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/11 10:09
 */

@Service
public class StuServiceImpl extends ServiceImpl<StuMapper, Stu> implements StuService {
    @Autowired
    private StuMapper stuMapper;

    @Override
    public Stu SearchName(String username) {
        return stuMapper.SearchName(username);
    }


    @Override
    public List<Stu> setExam(String id, String subject, String paper) {
//        根据tea_id，修改subject和paper字段的内容，分发试卷
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("tea_iD", id);
        updateWrapper.set("del_flag", 0);
        updateWrapper.set("exam_subject", subject);
        updateWrapper.set("exam_paper", paper);
        baseMapper.update(null, updateWrapper);

//        将修改后的结果进行返回
        QueryWrapper <Stu> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_id",id);
        List <Stu> list = stuMapper.selectList(wrapper);
        return list;
    }

    @Override
    public int findFlag(String id) {
        return stuMapper.findFlag(id);
    }

    @Override
    public List<Stu> updateMsg(String id){
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",id);
        updateWrapper.set("exam_subject","null");
        updateWrapper.set("exam_paper","null");
        updateWrapper.set("del_flag",1);
        baseMapper.update(null,updateWrapper);

        QueryWrapper <Stu> wrapper = new QueryWrapper<>();
        wrapper.eq("tea_id",id);
        List <Stu> list = stuMapper.selectList(wrapper);
        return list;
    }



}
