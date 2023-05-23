package com.triplan.plan.model.mapper;

import com.triplan.plan.model.PlanDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlanMapper {

    // 1. 플랜 생성
    void registPaln(PlanDto palnDto) throws Exception;
}
