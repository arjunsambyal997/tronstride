package com.tronstride.loyalty.service;

import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.repository.ProgramDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramDetailService {

    @Autowired
    private ProgramDetailRepo programDetailRepo;

    public ProgramDetail saveDetail(ProgramDetail programDetail){

        return programDetailRepo.save(programDetail);
    }

    public List<ProgramDetail> saveListDetail(List<ProgramDetail> programDetails){

        return programDetailRepo.saveAll(programDetails);
    }

    public ProgramDetail updateDetail(ProgramDetail programDetail){

        ProgramDetail updateProgramDetail=programDetailRepo.findById(programDetail.getId()).orElse(null);
        if(updateProgramDetail!=null)
        {

            updateProgramDetail.setProgramName(programDetail.getProgramName());
            updateProgramDetail.setCategory(programDetail.getCategory());
            updateProgramDetail.setCodeCount(programDetail.getCodeCount());
            updateProgramDetail.setDescription(programDetail.getDescription());
            updateProgramDetail.setPattern(programDetail.getPattern());
            updateProgramDetail.setCodeLength(programDetail.getCodeLength());
            updateProgramDetail.setPrefix(programDetail.getPrefix());
            updateProgramDetail.setPostfix(programDetail.getPostfix());
            updateProgramDetail.setAdditionalInformation(programDetail.getAdditionalInformation());
            updateProgramDetail.setCodeRedemptionLimit(programDetail.getCodeRedemptionLimit());
            updateProgramDetail.setCodeType(programDetail.getCodeType());
            updateProgramDetail.setWillJoinOnce(programDetail.getWillJoinOnce());
            updateProgramDetail.setCustomCode(programDetail.getCustomCode());
            programDetailRepo.save(updateProgramDetail);
            return updateProgramDetail;
        }

    return null;
    }


    public List<ProgramDetail> getAllDetail()
    {
        return programDetailRepo.findAll();
    }

    public String deleteDetail(int id) {
        if (programDetailRepo.existsById(id)) {
            programDetailRepo.deleteById(id);
            return "deleted " + id;
        } else {
            return "id not present";
        }

    }
}
