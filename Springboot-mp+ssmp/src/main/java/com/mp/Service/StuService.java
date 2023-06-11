package com.mp.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.Dto.StuDto;
import com.mp.Entity.Stu;

import java.util.List;

/**
 * @author : zzy
 * @date : 2023/4/11 10:08
 */
public interface StuService extends IService<Stu> {
    Stu SearchName(String username);
    List<Stu> setExam(String id, String subject, String paper);
    int findFlag(String id);
    List<Stu> updateMsg(String id);
}
