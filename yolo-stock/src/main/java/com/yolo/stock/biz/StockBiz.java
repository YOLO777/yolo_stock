package com.yolo.stock.biz;

import com.yolo.stock.common.biz.BaseBiz;
import com.yolo.stock.common.exception.auth.BusinessException;
import com.yolo.stock.common.msg.ObjectRestResponse;
import com.yolo.stock.common.util.excel.ImportExcel;
import com.yolo.stock.entity.Industry;
import com.yolo.stock.entity.Stock;
import com.yolo.stock.mapper.StockMapper;
import com.yolo.stock.vo.IndustryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 股票表
 *
 * @author zlj
 * @date 2020-11-27 09:06:45
 */
@Service
@Slf4j
public class StockBiz extends BaseBiz<StockMapper,Stock> {

    @Autowired
    private IndustryBiz industryBiz;

    /**
     * 导入股票和行业关系
     * @param file
     * @return
     */
    public ObjectRestResponse importStock(MultipartFile file) {
        ObjectRestResponse objectRestResponse = new ObjectRestResponse();
        try {
            ImportExcel ei = new ImportExcel(file, 0, 0);
            List<IndustryVo> list = ei.getDataList(IndustryVo.class);
            for (IndustryVo industryVo : list) {
                Industry industry = industryBiz.queryByCode(String.format("%02d", Integer.valueOf(industryVo.getIndCode())));
                if(industry!=null){
                    updateIndByStockCode(String.format("%06d",Integer.valueOf(industryVo.getStockCode())),String.format("%02d", Integer.valueOf(industryVo.getIndCode())));
                }else{
                    log.error("出错"+industryVo.getStockCode());
                }
            }
        } catch (Exception e) {
            log.error("{} execute importFile end.", e);
            throw new BusinessException("文件导入失败");
        }
        return objectRestResponse;
    }

    private void updateIndByStockCode(String stockCode,String indCode) {
        mapper.updateIndByStockCode(stockCode,indCode);
    }

}