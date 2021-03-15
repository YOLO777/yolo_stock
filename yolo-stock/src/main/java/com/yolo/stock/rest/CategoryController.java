package com.yolo.stock.rest;

import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.rest.BaseController;
import com.yolo.stock.biz.CategoryBiz;
import com.yolo.stock.entity.Category;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 门类表
 *
 * @author zlj
 * @date 2021-03-09 15:54:09
 */
@RestController
@RequestMapping("category")
public class CategoryController extends BaseController<CategoryBiz,Category> {
    @PostMapping(value = "import")
    public ObjectRestResponse importFile(@RequestParam("file") MultipartFile file){
        return baseBiz.importCategory(file);
    }
}