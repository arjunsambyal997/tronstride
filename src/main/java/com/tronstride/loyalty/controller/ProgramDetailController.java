package com.tronstride.loyalty.controller;

import com.tronstride.loyalty.DTO.DiscountDetailDTO;
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
    public ProgramDetail postProgramDetail(@RequestBody ProgramDetail programDetail) {
        return programDetailService.saveDetail(programDetail);
    }

    @PostMapping("/addProgramDetailList")
    public List<ProgramDetail> postProgramDetail(@RequestBody List<ProgramDetail> programDetails){
        return programDetailService.saveListDetail(programDetails);
    }

    @PutMapping("/updateProgramDetail")
    public ProgramDetail updateProgramDetail(@RequestBody ProgramDetail programDetail) {
        return programDetailService.updateDetail(programDetail);
    }

    @GetMapping("/getProgramDetail")
    public List<ProgramDetail> getDetails() {
        return programDetailService.getAllDetail();
    }

    @PutMapping("/updateProgramDetail/{id}")
    public Integer updateDetail(@PathVariable Integer id, @RequestBody DiscountDetailDTO detailDTO) {
        return programDetailService.updateProgramDetailById(detailDTO, id);
    }

    @DeleteMapping("/deleteProgramDetail/{id}")
    public String deleteProgramDetail(@PathVariable int id){

        return programDetailService.deleteDetail(id);
    }
}
