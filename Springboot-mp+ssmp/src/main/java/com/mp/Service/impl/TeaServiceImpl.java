package com.mp.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mp.Dto.TeaDto;
import com.mp.Entity.Question;
import com.mp.Entity.Stu;
import com.mp.Entity.Tea;
import com.mp.Service.StuService;
import com.mp.Service.TeaService;
import com.mp.Mapper.StuMapper;
import com.mp.Mapper.TeaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/17 15:00
 */

@Service
public class TeaServiceImpl extends ServiceImpl<TeaMapper, Tea> implements TeaService {

    @Autowired
    private StuService stuService;


    @Override
    public TeaDto UnitSearch(String id) {
//        从教师表中查询教师id
        Tea tea = this.getById(id);
        TeaDto teaDto = new TeaDto();
        BeanUtils.copyProperties(tea,teaDto);

//        查询该教师id对应的stu信息，从stu表中查询
        LambdaQueryWrapper<Stu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Stu::getTeaId,tea.getId());
        List<Stu> Stus = stuService.list(queryWrapper);
        teaDto.setTeaStudents(Stus);
        return teaDto;
    }
}
