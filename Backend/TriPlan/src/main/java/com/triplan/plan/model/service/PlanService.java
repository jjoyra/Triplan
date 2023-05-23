package com.triplan.plan.model.service;

import com.triplan.plan.model.PlanDto;

public interface PlanService {
    // 1. 플랜 생성
    void registPaln(PlanDto palnDto) throws Exception;
}
