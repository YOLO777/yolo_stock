package com.yolo.stock.biz;

import com.yolo.stock.common.biz.BaseBiz;
import com.yolo.stock.common.exception.auth.BusinessException;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.util.excel.ImportExcel;
import com.yolo.stock.entity.Industry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.yolo.stock.entity.Category;
import com.yolo.stock.mapper.CategoryMapper;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


/**
 * 门类表
 *
 * @author zlj
 * @date 2021-03-09 15:54:09
 */
@Service
@Slf4j
public class CategoryBiz extends BaseBiz<CategoryMapper, Category> {
    public ObjectRestResponse importCategory(MultipartFile file) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            ImportExcel ei = new ImportExcel(file, 0, 0);
            List<Category> list = ei.getDataList(Category.class);
            for (Category category : list) {
                Category cate = new Category();
                cate.setCategoryName(category.getCategoryNameAndCode().split("\\(")[0]);
                cate.setCategoryCode(category.getCategoryNameAndCode().split("\\(")[1].substring(0, 1));
                if (!isExist(cate)) {
                    insert(cate);
                }

            }
        } catch (Exception e) {
            log.error("{} execute importFile end.", e);
            throw new BusinessException("文件导入失败");
        }
        return objectRestResponse;
    }

    private boolean isExist(Category cate) {
        Example example =new Example(Category.class);
        Example.Criteria criteria =example.createCriteria();
        criteria.andEqualTo("categoryCode",cate.getCategoryCode());
        criteria.andEqualTo("categoryName",cate.getCategoryName());
        if(mapper.selectByExample(example).size()>0){
            return true;
        }else{
            return false;
        }
    }
}