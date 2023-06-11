package com.mp.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mp.Entity.Question;
import com.mp.Entity.Tea;
import com.mp.Dto.TeaDto;
/**
 * @author : zzy
 * @date : 2023/4/17 14:51
 */
public interface TeaService extends IService<Tea> {
    public TeaDto UnitSearch(String id);

}
