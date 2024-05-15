package com.tronstride.loyalty.controller;

import com.tronstride.loyalty.DTO.DiscountDetailDTO;
import com.tronstride.loyalty.DTO.TimeSpanDTO;
import com.tronstride.loyalty.model.DiscountedCollections;
import com.tronstride.loyalty.model.DiscountedProducts;
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
    public List<ProgramDetail> postProgramDetail(@RequestBody List<ProgramDetail> programDetails) {
        return programDetailService.saveListDetail(programDetails);
    }

    @PutMapping("/updateProgramDetail/{id}")
    public ProgramDetail updateProgramDetail(@PathVariable Integer id, @RequestBody ProgramDetail programDetail) {
        programDetail.setId(id);
        return programDetailService.updateDetail(programDetail);
    }

    @GetMapping("/getProgramDetail")
    public List<ProgramDetail> getDetails() {
        return programDetailService.getAllDetail();
    }

    @GetMapping("/getDetailById/{id}")
    public ProgramDetail getDetail(@PathVariable Integer id) {
        return programDetailService.getProgramDetailById(id);
    }

    @PutMapping("/addDiscountDetail/{id}")
    public Integer addDiscountDetail(@PathVariable Integer id, @RequestBody DiscountDetailDTO detailDTO) {
        return programDetailService.updateProgramDetailById(detailDTO, id);
    }

    @DeleteMapping("/deleteProgramDetail/{id}")
    public String deleteProgramDetail(@PathVariable int id) {

        return programDetailService.deleteDetail(id);
    }

    @PutMapping("/publish/{id}")
    public boolean publishPromo(@PathVariable int id) {
        return programDetailService.publishPromo(id);
    }

    @PutMapping("/updateProductTimeframe/{id}")
    public Integer updateProductTimeFrame(@PathVariable Integer id, @RequestBody TimeSpanDTO timeSpanDTO) {
        return programDetailService.updateProductTimeframe(id, timeSpanDTO);
    }

    @GetMapping("/products/discountedProducts")
    public List<DiscountedProducts> getAllDiscountedProducts() {
        return programDetailService.getAllDiscountedProducts();
    }

    @GetMapping("/products/discountedCollections")
    public List<DiscountedCollections> getAllDiscountedCollections() {
        return programDetailService.getAllDiscountedProductsCollections();
    }

    @PostMapping("/newDiscountedProduct")
    public Integer newDiscountedProduct( @RequestBody DiscountedProducts product) {
        return programDetailService.createNewDiscountedProduct(product);
    }

    @PostMapping("/newDiscountedCollection")
    public Integer newDiscountedCollection(@RequestBody  DiscountedCollections collections) {
        return programDetailService.createNewCollection(collections);
    }

    @GetMapping("/products/getProduct/{id}")
    public DiscountedProducts getProductById(@PathVariable Integer id) {
        return programDetailService.getProductsById(id);
    }

    @GetMapping("/products/getCollection/{id}")
    public DiscountedCollections getCollectionById(@PathVariable Integer id) {
        return programDetailService.getCollectionById(id);
    }

    @DeleteMapping("/products/deleteProduct/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        return programDetailService.deleteProduct(id);
    }

    @DeleteMapping("/products/deleteCollection/{id}")
    public String deleteCollection(@PathVariable Integer id) {
        return programDetailService.deleteCollection(id);
    }
}
