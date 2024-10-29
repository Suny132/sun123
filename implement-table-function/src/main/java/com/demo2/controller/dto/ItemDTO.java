package com.demo2.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    @JsonProperty(value = "A")
    private String A;
    @JsonProperty(value = "B")
    private String B;
    @JsonProperty(value = "C")
    private String C;
    @JsonProperty(value = "D")
    private String D;
    @JsonProperty(value = "E")
    private String E;
    @JsonProperty(value = "F")
    private String F;
    @JsonProperty(value = "aa")
    private int aa;
    @JsonProperty(value = "bb")
    private int bb;
    @JsonProperty(value = "cc")
    private int cc;
    @JsonProperty(value = "dd")
    private int dd;
    @JsonProperty(value = "ee")
    private int ee;
    @JsonProperty(value = "correct_aa")
    private int correct_aa;
    @JsonProperty(value = "correct_bb")
    private int correct_bb;
    @JsonProperty(value = "correct_cc")
    private int correct_cc;
    @JsonProperty(value = "correct_dd")
    private int correct_dd;
    @JsonProperty(value = "correct_ee")
    private int correct_ee;
}
