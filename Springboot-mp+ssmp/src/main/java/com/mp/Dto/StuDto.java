package com.mp.Dto;

import com.mp.Entity.Stu;
import com.mp.Entity.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zzy
 * @date : 2023/5/8 9:09
 */
@Data
public class StuDto extends Stu {
    private List<Question> StudentsExam = new ArrayList<>();
}

