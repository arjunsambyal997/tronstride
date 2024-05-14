package com.tronstride.loyalty.service;

import com.tronstride.loyalty.DTO.DiscountDetailDTO;
import com.tronstride.loyalty.DTO.TimeSpanDTO;
import com.tronstride.loyalty.model.*;
import com.tronstride.loyalty.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
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
    @Autowired
    private ProductTimeFrameRepo timeFrameRepo;
    @Autowired
    private TimeSpanRepo timeSpanRepo;

    public ProgramDetail saveDetail(ProgramDetail programDetail) {

        return programDetailRepo.save(programDetail);
    }

    public List<ProgramDetail> saveListDetail(List<ProgramDetail> programDetails) {

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
        boolean detail = programDetailRepo.findById(id).isPresent();
        if (detail) {
            return programDetailRepo.findById(id).get();
        }
        return null;
    }

    public Integer updateProgramDetailById(DiscountDetailDTO detailDto, Integer id) {
        ProgramDetail updateDetail = programDetailRepo.findById(id).orElse(null);

        if (updateDetail != null) {
            if (detailDto.getDiscountType() != null) {
                DiscountTypeEntity discountType = discountTypeRepo.getByDiscountType(detailDto.getDiscountType().getDiscountType());
                updateDetail.setDiscountType(discountType);
            } else {
                updateDetail.setOrderDiscountType(null);
            }
            if (detailDto.getOrderDiscountType() == null) {
                updateDetail.setOrderDiscountType(null);
                if (detailDto.getProductDiscountType() != null) {
                    ProductDiscountTypeEntity productDiscountTypeEntity = productDiscountTypeRepo.getByProductDiscountType(detailDto.getProductDiscountType().getProductDiscountType());
                    updateDetail.setProductDiscountType(productDiscountTypeEntity);
                }
            } else {
                updateDetail.setProductDiscountType(null); // assuming we have the order present
                OrderDiscountTypeEntity orderDiscountType = orderDiscountTypeRepo.getByOrderDiscountType(detailDto.getOrderDiscountType().getOrderDiscountType());
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

    public Integer updateProductTimeframe(Integer id, TimeSpanDTO timeSpanDTO) {
        ProgramDetail programDetail = programDetailRepo.getReferenceById(id);
        TimeSpanEntity spanEntity = timeSpanRepo.getByType(timeSpanDTO.getType());

        if (Objects.nonNull(programDetail.getProductTimeframe())) {
            programDetail.getProductTimeframe().setTimeSpanEntity(spanEntity);
            programDetail.getProductTimeframe().setSpanValue(timeSpanDTO.getValue());
            if (timeSpanDTO.getExpiredOn() != null) {
                programDetail.getProductTimeframe().setExpiresOn(timeSpanDTO.getExpiredOn());
            }
            if (timeSpanDTO.getStartsOn() != null) {
                programDetail.getProductTimeframe().setStartOn(timeSpanDTO.getStartsOn());
            } else {
                programDetail.getProductTimeframe().setStartOn(LocalDateTime.now(ZoneOffset.UTC));
            }
        } else {
            Timeframe timeframe = new Timeframe();
            timeframe.setTimeSpanEntity(spanEntity);
            timeframe.setSpanValue(timeSpanDTO.getValue());
            if (timeSpanDTO.getExpiredOn() != null) {
                timeframe.setExpiresOn(timeSpanDTO.getExpiredOn());
            }
            if (timeSpanDTO.getStartsOn() != null) {
                timeframe.setStartOn(timeSpanDTO.getStartsOn());
            } else {
                timeframe.setStartOn(LocalDateTime.now(ZoneOffset.UTC));
            }
            programDetail.setProductTimeframe(timeframe);
        }

        programDetailRepo.save(programDetail);

        return programDetail.getId();
    }

    public boolean publishPromo(int id) {
        ProgramDetail oldProgramDetailData = programDetailRepo.findById(id).orElse(null);
        if (Objects.nonNull(oldProgramDetailData)) {
            oldProgramDetailData.getProductTimeframe().setPublishedOn(LocalDateTime.now());
            ProgramDetail savedProgramDetail = programDetailRepo.save(oldProgramDetailData);
            if (Objects.nonNull(savedProgramDetail)) {
                return true;
            }
        }
        return false;
    }
}
