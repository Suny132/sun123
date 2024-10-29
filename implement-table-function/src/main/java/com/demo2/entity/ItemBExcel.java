package com.demo2.entity;

import com.demo2.util.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBExcel {
    @ExcelColumn(value = "A", col = 2)
    private String A;
    @ExcelColumn(value = "B", col = 3)
    private String B;
    @ExcelColumn(value = "C", col = 4)
    private String C;
    @ExcelColumn(value = "D", col = 5)
    private String D;
    @ExcelColumn(value = "E", col = 6)
    private String E;
    @ExcelColumn(value = "F", col = 7)
    private String F;
    @ExcelColumn(value = "aa", col = 8)
    private int aa;
    @ExcelColumn(value = "bb", col = 9)
    private int bb;
    @ExcelColumn(value = "cc", col = 10)
    private int cc;
    @ExcelColumn(value = "dd", col = 11)
    private int dd;
    @ExcelColumn(value = "ee", col = 12)
    private int ee;
    @ExcelColumn(value = "correct_aa", col = 13)
    private int correct_aa;
    @ExcelColumn(value = "correct_bb", col = 14)
    private int correct_bb;
    @ExcelColumn(value = "correct_cc", col = 15)
    private int correct_cc;
    @ExcelColumn(value = "correct_dd", col = 16)
    private int correct_dd;
    @ExcelColumn(value = "correct_ee", col = 17)
    private int correct_ee;
}
