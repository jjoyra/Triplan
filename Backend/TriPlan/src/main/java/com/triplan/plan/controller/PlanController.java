package com.triplan.plan.controller;

import com.triplan.plan.model.PlanDto;
import com.triplan.plan.model.service.PlanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
@Api(tags = {"플랜"})
public class PlanController {

    @Autowired
    PlanService planService;

    @PostMapping()
    @ApiOperation(value = "플랜 작성", notes = "플랜을 작성합니다.")
    public ResponseEntity<?> registPlan(@RequestBody @ApiParam(value = "플랜 Dto")PlanDto planDto) {
        try {
            System.out.println(planDto);
            planService.registPlan(planDto);

            return new ResponseEntity<Void>(HttpStatus.CREATED);

        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/list/{memberId}")
    @ApiOperation(value = "사용자 플랜 목록 조회", notes = "사용자가 참여자로 들어간 플랜 목록을 조회합니다.")
    public ResponseEntity<?> getUserPlanList(@PathVariable("memberId") String memberId) {
        try {
            List<PlanDto> list = planService.getUserPlanList(memberId);
            return new ResponseEntity<List>(list, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{planId}")
    @ApiOperation(value = "플랜 상세보기", notes = "해당 플랜의 상세 정보를 조회합니다.")
    public ResponseEntity<?> getPlanDetail(@PathVariable("planId") int planId) {
        try {
            PlanDto planDto = planService.getPlanDetail(planId);

            return new ResponseEntity<PlanDto>(planDto, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{planId}")
    @ApiOperation(value = "플랜 삭제하기", notes = "해당 플랜을 삭제합니다.")
    public ResponseEntity<?> deletePlan(@PathVariable("planId") int planId) {
        try {
            planService.deletePlan(planId);

            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
