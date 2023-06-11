package com.mp.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.Dto.StuDto;
import com.mp.Entity.Question;

import java.util.List;

/**
 * @author : zzy
 * @date : 2023/5/5 16:43
 */
public interface QuestionService extends IService<Question> {
   public StuDto getPaper(String id);
   List<Question> ChangeQuestion(String QuestionId, String subject, String paper, String value,String TeaId);
}
