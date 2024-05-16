package com.tronstride.loyalty.service;

import com.tronstride.loyalty.DTO.DiscountDetailDTO;
import com.tronstride.loyalty.DTO.TimeSpanDTO;
import com.tronstride.loyalty.model.*;
import com.tronstride.loyalty.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
    @Autowired
    DiscountedCollectionsRepo discountedCollectionsRepo;
    @Autowired
    DiscountedProductsRepo discountedProductsRepo;
    @Autowired
    private CouponRepo couponRepo;

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
                updateDetail.setDiscountAmount(detailDto.getDiscountAmount());
            } else {
                updateDetail.setOrderDiscountType(null);
            }
            if (detailDto.getOrderDiscountType() == null) {
                updateDetail.setOrderDiscountType(null);
                if (detailDto.getProductDiscountType() != null) {
                    ProductDiscountTypeEntity productDiscountTypeEntity = productDiscountTypeRepo.getByProductDiscountType(detailDto.getProductDiscountType().getProductDiscountType());
                    updateDetail.setProductDiscountType(productDiscountTypeEntity);
                    updateDetail.setDiscountedProductsList(detailDto.getDiscountedProductsList());
                    updateDetail.setDiscountedCollectionsList(detailDto.getDiscountedCollectionsList());
                }
            } else {
                updateDetail.setProductDiscountType(null); // assuming we have the order present
                OrderDiscountTypeEntity orderDiscountType = orderDiscountTypeRepo.getByOrderDiscountType(detailDto.getOrderDiscountType().getOrderDiscountType());
                updateDetail.setOrderDiscountType(orderDiscountType);
                updateDetail.setDiscountedProductsList(null);
                updateDetail.setDiscountedCollectionsList(null);
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
        boolean flag = (Objects.nonNull(timeSpanDTO.getType()) && Objects.nonNull(timeSpanDTO.getValue()));
        if (!flag) {
            programDetail.getProductTimeframe().setValidFor(false);
        }
        if (Objects.nonNull(programDetail.getProductTimeframe())) {
            programDetail.getProductTimeframe().setTimeSpanEntity(spanEntity);
            programDetail.getProductTimeframe().setSpanValue(timeSpanDTO.getValue());
            if (timeSpanDTO.getExpiresOn() != null) {
                programDetail.getProductTimeframe().setExpiresOn(timeSpanDTO.getExpiresOn());
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
            if (flag) {
                timeframe.setValidFor(true);
            }
            if (timeSpanDTO.getExpiresOn() != null) {
                timeframe.setExpiresOn(timeSpanDTO.getExpiresOn());
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

  //  public boolean publishPromo(int id) {
//        ProgramDetail oldProgramDetailData = programDetailRepo.findById(id).orElse(null);
//        if (Objects.nonNull(oldProgramDetailData)) {
//            oldProgramDetailData.getProductTimeframe().setPublishedOn(LocalDateTime.now());
//            ProgramDetail savedProgramDetail = programDetailRepo.save(oldProgramDetailData);
//            return true;
//        }
//        return false;
        public boolean publishPromo(int id, String postfix, String prefix, String customCode, int codeLength, int codeCount) {
            ProgramDetail oldProgramDetailData = programDetailRepo.findById(id).orElse(null);

            if (Objects.nonNull(oldProgramDetailData)) {
                // Set publishedOn timestamp
                oldProgramDetailData.getProductTimeframe().setPublishedOn(LocalDateTime.now());
                ProgramDetail savedProgramDetail = programDetailRepo.save(oldProgramDetailData);

                // Check if publishedOn is true (assuming publishedOn is a boolean field)
                if (oldProgramDetailData.getProductTimeframe().isPublishedOn()) {
                    // Generate coupons based on provided parameters
                    generateCoupons(postfix, prefix, customCode, codeLength, codeCount);
                }

                return true;
            }

            return false;
        }

        public void generateCoupons(String postfix, String prefix, String customCode, int codeLength, int codeCount) {
            List<Coupon> coupons = new ArrayList<>();

            for (int i = 0; i < codeCount; i++) {
                String couponCode = generateCouponCode(postfix, prefix, customCode, codeLength);
                Coupon coupon = new Coupon();
                coupon.setPrefix(prefix);
                coupon.setPostfix(postfix);
                coupon.setCustomCode(customCode);
                coupon.setCodeLength(codeLength);

                // Save the generated coupon to the database
                couponRepo.save(coupon);
            }
        }

        public String generateCouponCode(String postfix, String prefix, String customCode, int codeLength) {
            // Implement logic to generate a coupon code based on provided parameters
            StringBuilder couponCodeBuilder = new StringBuilder();

            if (prefix != null) {
                couponCodeBuilder.append(prefix);
            }

            // Generate random part of the coupon code
            String randomPart = generateRandomCode(codeLength);
            couponCodeBuilder.append(randomPart);

            if (postfix != null) {
                couponCodeBuilder.append(postfix);
            }

            return couponCodeBuilder.toString();
        }

        public String generateRandomCode(int length) {
            // Generate a random code of specified length (using characters/digits)
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
            Random random = new Random();
            StringBuilder codeBuilder = new StringBuilder();

            for (int i = 0; i < length; i++) {
                char randomChar = characters.charAt(random.nextInt(characters.length()));
                codeBuilder.append(randomChar);
            }

            return codeBuilder.toString();
        }


    public List<DiscountedProducts> getAllDiscountedProducts() {
        return discountedProductsRepo.findAll();
    }

    public List<DiscountedCollections> getAllDiscountedProductsCollections() {
        return discountedCollectionsRepo.findAll();
    }

    public Integer createNewCollection(DiscountedCollections collections) {
        DiscountedCollections newCollection = new DiscountedCollections();
        if (Objects.nonNull(collections.getDiscountedProductsList())) {
            newCollection.setCollectionName(collections.getCollectionName());
            newCollection.setDiscountedProductsList(collections.getDiscountedProductsList());
            discountedCollectionsRepo.save(newCollection);
        } else {
            return -1;
        }
        return newCollection.getId();
    }

    public Integer createNewDiscountedProduct(DiscountedProducts product) {
        DiscountedProducts newProduct = new DiscountedProducts();
        if (Objects.nonNull(product)) {
            newProduct.setProductName(product.getProductName());
            newProduct.setPrice(product.getPrice());
            discountedProductsRepo.save(newProduct);
        } else {
            return -1;
        }
        return newProduct.getId();
    }

    public DiscountedCollections getCollectionById(Integer id) {
        boolean isPresent = discountedCollectionsRepo.findById(id).isPresent();
        if (isPresent) {
            return discountedCollectionsRepo.findById(id).get();
        }
        return null;
    }

    public DiscountedProducts getProductsById(Integer id) {
        boolean isPresent = discountedProductsRepo.findById(id).isPresent();
        if (isPresent) {
            return discountedProductsRepo.findById(id).get();
        }
        return null;
    }

    public String deleteCollection(Integer id) {
        boolean isPresent = discountedCollectionsRepo.findById(id).isPresent();
        if (isPresent) {
            discountedCollectionsRepo.deleteById(id);
            return "Success";
        }
        return "Id not Present";
    }

    public String deleteProduct(Integer id) {
        boolean isPresent = discountedProductsRepo.findById(id).isPresent();
        if (isPresent) {
            discountedProductsRepo.deleteById(id);
            return "Success";
        }
        return "Id not Present";
    }
}
