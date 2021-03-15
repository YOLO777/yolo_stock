package com.yolo.stock.rest;


import com.yolo.stock.biz.IndustryBiz;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.rest.BaseController;
import com.yolo.stock.entity.Industry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 行业大类表
 *
 * @author zlj
 * @date 2021-03-09 15:54:09
 */
@RestController
@RequestMapping("industry")
public class IndustryController extends BaseController<IndustryBiz,Industry> {
    /**
     * 导入行业大类
     * @param file
     * @return
     */
    @PostMapping("import")
    public ObjectRestResponse importFile(@RequestParam("file") MultipartFile file){
        return baseBiz.importIndustry(file);
    }

}