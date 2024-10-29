package com.demo2.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo2.controller.dto.ItemDTO;
import com.demo2.entity.*;
import com.demo2.service.DataService;
import com.demo2.util.ExcelUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    DataService dataService;

    @RequestMapping("/addFormA")
    public String addFormA(@RequestBody ItemDTO dtoA){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(dtoA==null||dtoA.getA()==null){
            json.put("msg","failed");
            return json.toString();
        }
        ItemA a = new ItemA(dtoA);
        if(dataService.AExists(a)){
            json.put("msg","exist");
            json.put("data",a);
            return json.toString();
        }
        dataService.insert(a);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",a);
        return json.toString();
    }

    @RequestMapping("/updateFormA")
    public String updateFormA(@RequestBody(required = false) ItemDTO dtoA){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(dtoA==null||dtoA.getA()==null){
            json.put("msg","failed");
            return json.toString();
        }
        ItemA a = new ItemA(dtoA);
        if(dataService.AExists(a)==false){
            json.put("msg","does not exist");
            json.put("data",a);
            return json.toString();
        }

        dataService.update(a);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",a);
        return json.toString();
    }


    @RequestMapping("/deleteFormA")
    public String deleteFormA(@RequestBody(required = false) ItemDTO dtoA){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(dtoA==null||dtoA.getA()==null){
            json.put("msg","failed");
            return json.toString();
        }
        ItemA a = new ItemA(dtoA);

        if(dataService.AExists(a)==false){
            json.put("msg","does not exist");
            json.put("data",a);
            return json.toString();
        }

        dataService.deleteA(a);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",a);
        return json.toString();
    }

    @RequestMapping("/selectFormA")
    public String selectFormA(@RequestBody(required = false) ItemA A){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(A==null||A.getA()==null){
            json.put("msg","failed");
            json.put("date",A);
            return json.toString();
        }

        if(dataService.selectA(A).size()==0){
            json.put("msg","does not exist");
            return json.toString();
        }

        json.put("code","1");
        json.put("msg","success");
        json.put("data",dataService.selectA(A));
        return json.toString();
    }

    @RequestMapping("/addFormB")
    public String addFormB(@RequestBody(required = false) ItemDTO dtoB){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(dtoB==null||dtoB.getA()==null){
            json.put("msg","failed");
            return json.toString();
        }
        ItemB b = new ItemB(dtoB);


        if(dataService.selectOneB(b)!=null){
            json.put("msg","exist");
            json.put("data",b);
            return json.toString();
        }
        dataService.insert(b);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",b);
        return json.toString();
    }

    @RequestMapping("/updateFormB")
    public String updateFormB(@RequestBody(required = false) ItemDTO dtoB){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(dtoB==null||dtoB.getA()==null){
            json.put("msg","failed");
            return json.toString();
        }
        ItemB b = new ItemB(dtoB);
        if(dataService.selectOneB(b)==null){
            json.put("msg","does not exist");
            json.put("data",b);
            return json.toString();
        }

        dataService.update(b);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",b);
        return json.toString();
    }

    @RequestMapping("/deleteFormB")
    public String deleteFormB(@RequestBody(required = false) ItemDTO dtoB){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(dtoB==null||dtoB.getA()==null){
            json.put("msg","failed");
            return json.toString();
        }

        ItemB b = new ItemB(dtoB);

        if(dataService.selectOneB(b)==null){
            json.put("msg","does not exist");
            json.put("data",b);
            return json.toString();
        }

        dataService.deleteOneB(b.getA(),b.getC());
        json.put("code","1");
        json.put("msg","success");
        json.put("data",b);
        return json.toString();
    }

    @RequestMapping("/deleteFormBByC")
    public String deleteFormB(@RequestBody(required = false) ItemB itemB){
        JSONObject json = new JSONObject();
        json.put("code","0");
        json.put("data",itemB);

        if (itemB==null||itemB.getC()==null){
            json.put("msg","null");
            return json.toString();
        }
        if(dataService.selectBByC(itemB).size()==0){
            json.put("msg","does not exist");
            return json.toString();
        }

        dataService.deleteBByC(itemB);
        json.put("code","1");
        json.put("msg","success");
        return json.toString();
    }
    @RequestMapping("/generateFormC")
    public String generate(){
        List<ItemC> formC =  dataService.generate();
        JSONObject json = new JSONObject();
        json.put("code","1");
        json.put("msg","success");
        json.put("data",formC);
        return json.toString();
    }

    @RequestMapping("/selectFormC")
    public String selectFormC(@RequestBody(required = false) ItemC itemC){
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(itemC==null||itemC.getB()==null){
            json.put("msg","failed");
            return json.toString();
        }

        if(dataService.selectC(itemC).size()==0){
            json.put("msg","does not exist");
            return json.toString();
        }

        json.put("code","1");
        json.put("msg","success");
        json.put("data",dataService.selectC(itemC));
        return json.toString();
    }


    @RequestMapping(value = "/readExcelA")
    public String readExcelA(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(file==null){
            json.put("msg", "no file");
            return json.toString();
        }
        List<ItemAExcel> list = ExcelUtils.readExcel("", ItemAExcel.class, 0, file);
        List<ItemA> listA = new ArrayList();
        list.forEach(e -> {
            ItemA a = new ItemA();
            BeanUtils.copyProperties(e, a);
            listA.add(a);
        });
        dataService.saveA(listA);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",listA);
        return json.toString();
    }

    @RequestMapping(value = "/readExcelB")
    public String readExcelB(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        JSONObject json = new JSONObject();
        json.put("code","0");
        if(file==null){
            json.put("msg", "no file");
            return json.toString();
        }
        List<ItemBExcel> list = ExcelUtils.readExcel("", ItemBExcel.class, 0, file);
        List<ItemB> listB = new ArrayList();
        list.forEach(e -> {
            ItemB b = new ItemB();
            BeanUtils.copyProperties(e, b);
            listB.add(b);
        });
        dataService.saveB(listB);
        json.put("code","1");
        json.put("msg","success");
        json.put("data",listB);
        return json.toString();
    }
    @RequestMapping(value = "/exportFormA", method = RequestMethod.GET)
    public void exportFormA(HttpServletResponse response) {
        dataService.exportFormA(response);
    }
    @RequestMapping(value = "/exportFormB")
    public void exportB(HttpServletResponse response){
        dataService.exportFormB(response);
    }
    @RequestMapping(value = "/exportFormC")
    public void exportC(HttpServletResponse response){
        dataService.exportFormC(response);
    }

}
