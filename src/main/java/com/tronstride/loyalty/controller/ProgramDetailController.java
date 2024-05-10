package com.tronstride.loyalty.controller;

import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.service.ProgramDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProgramDetailController {

    @Autowired
    private ProgramDetailService programDetailService;

    @PostMapping("/addProgramDetail")
    public ProgramDetail postProgramDetail(@RequestBody ProgramDetail programDetail){
        return programDetailService.saveDetail(programDetail);
    }

    @PutMapping("/updateProgramDetail")
    public ProgramDetail updateProgramDetail(@RequestBody ProgramDetail programDetail){
        return programDetailService.updateDetail(programDetail);
    }

    @GetMapping("/getProgramDetail")
    public List<ProgramDetail> getDetails()
    {
        return programDetailService.getAllDetail();
    }
}
