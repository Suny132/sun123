package com.demo2.entity;

        import com.demo2.util.ExcelColumn;
        import lombok.Data;
@Data
public class ItemAExcel {
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
}