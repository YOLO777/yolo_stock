package com.yolo.stock.biz;

import com.yolo.stock.common.biz.BaseBiz;
import com.yolo.stock.common.exception.auth.BusinessException;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.util.excel.ImportExcel;
import com.yolo.stock.entity.Category;
import com.yolo.stock.entity.Industry;
import com.yolo.stock.mapper.CategoryMapper;
import com.yolo.stock.mapper.IndustryMapper;
import com.yolo.stock.vo.IndustryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * 行业大类表
 *
 * @author zlj
 * @date 2021-03-09 15:54:09
 */
@Service
@Slf4j
public class IndustryBiz extends BaseBiz<IndustryMapper, Industry> {

    @Autowired
    private CategoryMapper categoryMapper;

    public ObjectRestResponse importIndustry(MultipartFile file) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            ImportExcel ei = new ImportExcel(file, 0, 0);
            List<IndustryVo> list = ei.getDataList(IndustryVo.class);
            for (IndustryVo industryVo : list) {
                if (!isExist(industryVo)) {
                    Industry ind = new Industry();
                    Example example =new Example(Category.class);
                    Example.Criteria criteria =example.createCriteria();
                    criteria.andEqualTo("categoryName",industryVo.getCategoryNameAndCode().split("\\(")[0]);
                    List<Category> categoryList = categoryMapper.selectByExample(example);
                    if(categoryList.size()>0){
                        ind.setCateId(categoryList.get(0).getId());
                    }
                    ind.setIndCode(industryVo.getIndCode());
                    ind.setIndName(industryVo.getIndName());
                    insert(ind);
                }
            }

        } catch (Exception e) {
            log.error("{} execute importFile end.", e);
            throw new BusinessException("文件导入失败");
        }
        return objectRestResponse;
    }

    private boolean isExist(IndustryVo industry) {
        Example example = new Example(Industry.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indCode", industry.getIndCode());
        criteria.andEqualTo("indName", industry.getIndName());
        if (mapper.selectByExample(example).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Industry queryByCode(String indCode){
        Example example = new Example(Industry.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("indCode", indCode);
        List<Industry> industryList = mapper.selectByExample(example);
        if(industryList.size()>0){
            return industryList.get(0);
        }
        return null;
    }
}