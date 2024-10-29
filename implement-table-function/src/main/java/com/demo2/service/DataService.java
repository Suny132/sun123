package com.demo2.service;

import com.demo2.dao.DataDAO;
import com.demo2.entity.ItemA;
import com.demo2.entity.ItemB;
import com.demo2.entity.ItemC;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataDAO dataDAO;

    public boolean AExists(ItemA a){
        if (dataDAO.selectOneA(a.getA())==null){
            return false;
        };
        return true;
    }

    public void insert(ItemA a){
        dataDAO.insertA(a);
    }

    public void insert(ItemB b){
        dataDAO.insertB(b);
    }

    public void update(ItemA a){
        dataDAO.updateA(a);
    }

    public void update(ItemB b){
        dataDAO.updateB(b);
    }

    public void update(ItemC c){
        dataDAO.updateC(c);
    }

    public void deleteA(ItemA a){
        dataDAO.deleteA(a);
    }

    public void deleteBByC(ItemB itemB){
        dataDAO.deleteBByC(itemB);
    }

    public void deleteOneB(String A, String C){
        if (C == null){
            dataDAO.deleteOneBCIsNull(A);
        }else{
            dataDAO.deleteOneB(A, C);
        }

    }

    public List<ItemA> selectA(ItemA A){
        return dataDAO.selectA(A);
    }

    public ItemB selectOneB(ItemB itemB){
        if (itemB.getC()==null){
            return dataDAO.selectOneBCIsNull(itemB);
        }else{
            return dataDAO.selectOneB(itemB);
        }
    }

    public List<ItemB> selectBByC( ItemB itemB){
        return dataDAO.selectBByC(itemB);
    }

    public List<ItemC> selectC( ItemC itemC){
        return dataDAO.selectC(itemC);
    }

    public List<ItemC> generate(){

        List<ItemC> formC = new ArrayList<>();
        List<ItemA> formA= dataDAO.selectAllA();
        List<ItemB> formB =  dataDAO.selectALlB();
        int aa_A=0;
        int bb_A=0;
        int cc_A=0;
        int dd_A=0;
        int ee_A=0;
        int aa_B=0;
        int bb_B=0;
        int cc_B=0;
        int correct_aa=0;
        int correct_bb=0;
        int correct_cc=0;
        int correct_dd=0;
        int correct_ee=0;
        for (ItemA i: formA){
            if (i.getAa()==1){
                aa_A += 1;}
            if (i.getBb()==1){
                bb_A += 1;}
            if (i.getCc()==1){
                cc_A += 1;}
            if (i.getDd()==1){
                dd_A += 1;}
            if (i.getEe()==1){
                ee_A += 1;}
        }
        for (int i=0;i<formB.size();i++){
            if (i+1< formB.size() && formB.get(i).getB().equals(formB.get(i+1).getB())){

                ItemB b = formB.get(i);
                if(b.getCorrect_aa()==1){
                    correct_aa += 1;
                }
                if(b.getCorrect_bb()==1){
                    correct_bb += 1;
                }
                if(b.getCorrect_cc()==1){
                    correct_cc += 1;
                }
                if(b.getCorrect_dd()==1){
                    correct_dd += 1;
                }
                if(b.getCorrect_ee()==1){
                    correct_ee += 1;
                }
                if (b.getAa()==1){
                    aa_B += 1;}
                if (b.getBb()==1){
                    bb_B += 1;}
                if (b.getCc()==1){
                    cc_B += 1;}
            }else{
                ItemC itemC = new ItemC();
                itemC.setB(formB.get(i).getB());
                if (aa_A!=0){
                    itemC.setAaS(correct_aa/aa_A);
                }
                if (bb_A!=0){
                    itemC.setBbS(correct_bb/bb_A);
                }
                if (cc_A!=0){
                    itemC.setCcS(correct_cc/cc_A);
                }
                if (aa_B!=0){
                    itemC.setAaA(correct_aa/aa_B);
                }
                if (bb_B!=0){
                    itemC.setBbA(correct_bb /bb_B);
                }
                if (cc_B!=0){
                    itemC.setCcA(correct_cc/cc_B);
                }
                if (cc_B!=0){
                    itemC.setAaSS(correct_ee/ee_A);
                }if (cc_B!=0){
                    itemC.setAaC((correct_aa+correct_ee)/(aa_A+ee_A));
                }if (cc_B!=0){
                    itemC.setDdS(correct_dd/dd_A);
                }
                if(dataDAO.selectC(itemC).size()==0){
                    dataDAO.insertC(itemC);
                }else{
                    dataDAO.updateC(itemC);
                }

                formC.add(itemC);
            }
        }

        return formC;
    }


    public void saveA(List<ItemA> listA){
        for(ItemA i: listA){
            dataDAO.insertA(i);
        }
    }
    public void saveB(List<ItemB> listB){
        for(ItemB i: listB){
            dataDAO.insertB(i);
        }
    }
    public XSSFWorkbook exportFormA(HttpServletResponse response){
        List<ItemA> formA = dataDAO.selectALlA();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Table A");
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("A");
        titleRow.createCell(1).setCellValue("B");
        titleRow.createCell(2).setCellValue("C");
        titleRow.createCell(3).setCellValue("D");
        titleRow.createCell(4).setCellValue("E");
        titleRow.createCell(5).setCellValue("F");
        titleRow.createCell(6).setCellValue("aa");
        titleRow.createCell(8).setCellValue("bb");
        titleRow.createCell(10).setCellValue("cc");
        titleRow.createCell(12).setCellValue("dd");
        titleRow.createCell(14).setCellValue("ee");
        int cell = 1;
        for (ItemA i :formA){
            Row row = sheet.createRow(cell);
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(i.getA());
            row.createCell(2).setCellValue(i.getB());
            row.createCell(3).setCellValue(i.getC());
            row.createCell(4).setCellValue(i.getD());
            row.createCell(5).setCellValue(i.getE());
            row.createCell(6).setCellValue(i.getF());
            row.createCell(7).setCellValue(i.getAa());
            row.createCell(9).setCellValue(i.getBb());
            row.createCell(11).setCellValue(i.getCc());
            row.createCell(13).setCellValue(i.getDd());
            row.createCell(15).setCellValue(i.getEe());
            cell++;
        }
        String fileName = "Table A.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.flush();outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }
    public XSSFWorkbook exportFormB(HttpServletResponse response){
        List<ItemB> formB = dataDAO.selectALlB();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Table B");
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("A");
        titleRow.createCell(1).setCellValue("B");
        titleRow.createCell(2).setCellValue("C");
        titleRow.createCell(3).setCellValue("D");
        titleRow.createCell(4).setCellValue("E");
        titleRow.createCell(5).setCellValue("F");
        titleRow.createCell(6).setCellValue("aa");
        titleRow.createCell(7).setCellValue("correct_aa");
        titleRow.createCell(8).setCellValue("bb");
        titleRow.createCell(9).setCellValue("correct_bb");
        titleRow.createCell(10).setCellValue("cc");
        titleRow.createCell(11).setCellValue("correct_cc");
        titleRow.createCell(12).setCellValue("dd");
        titleRow.createCell(13).setCellValue("correct_dd");
        titleRow.createCell(14).setCellValue("ee");
        titleRow.createCell(15).setCellValue("correct_ee");
        int cell = 1;
        for (ItemB i :formB){
            Row row = sheet.createRow(cell);
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(i.getA());
            row.createCell(2).setCellValue(i.getB());
            row.createCell(3).setCellValue(i.getC());
            row.createCell(4).setCellValue(i.getD());
            row.createCell(5).setCellValue(i.getE());
            row.createCell(6).setCellValue(i.getF());
            row.createCell(7).setCellValue(i.getAa());
            row.createCell(8).setCellValue(i.getCorrect_aa());
            row.createCell(9).setCellValue(i.getBb());
            row.createCell(10).setCellValue(i.getCorrect_bb());
            row.createCell(11).setCellValue(i.getCc());
            row.createCell(12).setCellValue(i.getCorrect_cc());
            row.createCell(13).setCellValue(i.getDd());
            row.createCell(14).setCellValue(i.getCorrect_dd());
            row.createCell(15).setCellValue(i.getEe());
            row.createCell(16).setCellValue(i.getCorrect_ee());
            cell++;
        }
        String fileName = "Table B.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.flush();outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }

    public XSSFWorkbook exportFormC(HttpServletResponse response){
        List<ItemC> formC = dataDAO.selectAllC();
        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Table B");
        Row titleRow = sheet.createRow(0);
        titleRow.createCell(0).setCellValue("B");
        titleRow.createCell(1).setCellValue("aaS");
        titleRow.createCell(2).setCellValue("bbS");
        titleRow.createCell(3).setCellValue("ccS");
        titleRow.createCell(4).setCellValue("aaA");
        titleRow.createCell(5).setCellValue("bbA");
        titleRow.createCell(6).setCellValue("ccA");
        titleRow.createCell(7).setCellValue("aaSS");
        titleRow.createCell(8).setCellValue("aaC");
        titleRow.createCell(9).setCellValue("ddS");
        int cell = 1;
        for (ItemC i :formC){
            Row row = sheet.createRow(cell);
            row.createCell(0).setCellValue(cell);
            row.createCell(1).setCellValue(i.getB());
            row.createCell(2).setCellValue(i.getAaS());
            row.createCell(3).setCellValue(i.getBbS());
            row.createCell(4).setCellValue(i.getCcS());
            row.createCell(5).setCellValue(i.getAaA());
            row.createCell(6).setCellValue(i.getBbA());
            row.createCell(7).setCellValue(i.getCcA());
            row.createCell(8).setCellValue(i.getAaSS());
            row.createCell(9).setCellValue(i.getAaC());
            row.createCell(10).setCellValue(i.getDdS());
            cell++;
        }
        String fileName = "Table C.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.flush();outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wb;
    }


}
