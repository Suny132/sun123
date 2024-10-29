package com.demo2.entity;

import com.demo2.controller.dto.ItemDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemA {

    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private int aa;
    private int bb;
    private int cc;
    private int dd;
    private int ee;

    public ItemA(ItemDTO dto){
        this.setA(dto.getA());
        this.setB(dto.getB());
        this.setC(dto.getC());
        this.setD(dto.getD());
        this.setE(dto.getE());
        this.setF(dto.getF());
        this.setAa(dto.getAa());
        this.setBb(dto.getBb());
        this.setCc(dto.getCc());
        this.setDd(dto.getDd());
        this.setEe(dto.getEe());
    }
}