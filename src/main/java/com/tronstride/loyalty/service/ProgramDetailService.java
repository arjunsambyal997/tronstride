package com.tronstride.loyalty.service;

import com.tronstride.loyalty.DTO.DiscountDetailDTO;
import com.tronstride.loyalty.model.DiscountTypeEntity;
import com.tronstride.loyalty.model.OrderDiscountTypeEntity;
import com.tronstride.loyalty.model.ProductDiscountTypeEntity;
import com.tronstride.loyalty.model.ProgramDetail;
import com.tronstride.loyalty.repository.DiscountTypeRepo;
import com.tronstride.loyalty.repository.OrderDiscountTypeRepo;
import com.tronstride.loyalty.repository.ProductDiscountTypeRepo;
import com.tronstride.loyalty.repository.ProgramDetailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProgramDetailService {

    @Autowired
    private ProgramDetailRepo programDetailRepo;
    @Autowired
    private DiscountTypeRepo discountTypeRepo;
    @Autowired
    private ProductDiscountTypeRepo productDiscountTypeRepo;
    @Autowired
    private OrderDiscountTypeRepo orderDiscountTypeRepo;

    public ProgramDetail saveDetail(ProgramDetail programDetail) {

        return programDetailRepo.save(programDetail);
    }

    public List<ProgramDetail> saveListDetail(List<ProgramDetail> programDetails){

        return programDetailRepo.saveAll(programDetails);
    }

    public ProgramDetail updateDetail(ProgramDetail programDetail) {

        ProgramDetail updateProgramDetail = programDetailRepo.findById(programDetail.getId()).orElse(null);
        if (updateProgramDetail != null) {

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


    public List<ProgramDetail> getAllDetail() {
        return programDetailRepo.findAll();
    }

    public ProgramDetail getProgramDetailById(Integer id) {
        return programDetailRepo.getReferenceById(id);
    }

    public Integer updateProgramDetailById(DiscountDetailDTO detailDto, Integer id) {
        ProgramDetail updateDetail = programDetailRepo.findById(id).orElse(null);

        if (updateDetail != null) {
            if (detailDto.getDiscountType() != null) {
                DiscountTypeEntity discountType = discountTypeRepo.getReferenceById(detailDto.getDiscountType().getId());
                updateDetail.setDiscountType(discountType);
            } else {
                updateDetail.setOrderDiscountType(null);
            }
            if (detailDto.getOrderDiscountType().getId() == null) {
                updateDetail.setOrderDiscountType(null);
                if (detailDto.getProductDiscountType().getId() != null) {
                    ProductDiscountTypeEntity productDiscountTypeEntity = productDiscountTypeRepo.getReferenceById(detailDto.getProductDiscountType().getId());
                    updateDetail.setProductDiscountType(productDiscountTypeEntity);
                }
            } else {
                updateDetail.setProductDiscountType(null);
                OrderDiscountTypeEntity orderDiscountType = orderDiscountTypeRepo.getReferenceById(detailDto.getOrderDiscountType().getId());
                updateDetail.setOrderDiscountType(orderDiscountType);
            }
            programDetailRepo.save(updateDetail);
        }
        return Objects.requireNonNull(updateDetail).getId();
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
