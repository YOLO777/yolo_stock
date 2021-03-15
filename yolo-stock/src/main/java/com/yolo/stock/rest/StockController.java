package com.yolo.stock.rest;

import com.yolo.stock.biz.StockBiz;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.rest.BaseController;
import com.yolo.stock.entity.Stock;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 股票表
 *
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@RestController
@RequestMapping("stock")
public class StockController extends BaseController<StockBiz,Stock> {

    /**
     * 导入行业大类
     * @param file
     * @return
     */
    @PostMapping("import")
    public ObjectRestResponse importFile(@RequestParam("file") MultipartFile file){
        return baseBiz.importStock(file);
    }
}