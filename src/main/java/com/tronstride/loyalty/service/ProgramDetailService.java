package com.tronstride.loyalty.service;

import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.repository.ProgramDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramDetailService {

    @Autowired
    private ProgramDetailRepo programDetailRepo;

    public ProgramDetail saveDetails(ProgramDetail programDetail){

        return programDetailRepo.save(programDetail);
    }
}
