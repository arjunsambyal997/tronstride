package com.tronstride.loyalty.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "ProgramDetail")
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDetail {

    @Id
    @Column(name = "ID")
    @GeneratedValue()//type
    private Integer Id;

    @Column(name = "ProgramName")
    private String programName;

    @Column(name = "WillJoinOnce")
    private Boolean willJoinOnce;

    @Column(name = "Category")
    private String category;

    @Column(name = "Description")
    private String description;

    @Column(name = "CodeRedemptionLimit")
    private String codeRedemptionLimit;

    @Column(name = "CodeType")
    private String codeType;

    @Column(name = "CodeCount")
    private String codeCount;

//    @Column(name = "CharSet")
//    private String charset;

    @Column(name = "CodeLength")
    private Integer codeLength;

    @Column(name = "Pattern")
    private String pattern;

    @Column(name = "Prefix")
    private String prefix;

    @Column(name = "Postfix")
    private String postfix;

    @Column(name = "AdditionalInformation")
    private String additionalInformation;

    @Column(name = "CustomCode")
    private String customCode;
}
