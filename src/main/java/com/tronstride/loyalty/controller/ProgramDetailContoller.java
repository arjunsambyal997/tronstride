package com.tronstride.loyalty.controller;

import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.service.ProgramDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProgramDetailContoller {

    @Autowired
    private ProgramDetailService programDetailService;

    @PostMapping("/addProgramDetail")
    public ProgramDetail postDetails(@RequestBody ProgramDetail programDetail){

        return programDetailService.saveDetails(programDetail);
    }
}
