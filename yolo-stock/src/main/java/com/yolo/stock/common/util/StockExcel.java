package com.yolo.stock.common.util;

import com.yolo.stock.entity.EverydayData;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
    * 导出盘口信息行业
    * @author YOLO
    * @date 2021-02-25
    * @param
    * @return
*/
@Slf4j
public class StockExcel {
    public void generatorStockData(List<EverydayData> everydayDataList,String stockDataDirPath) throws IOException {
        Boolean generate = false;
        String pathName = new String((stockDataDirPath+File.separator+DateUtil.format(new Date(),"yyyy-MM-dd")+"每日数据.xlsx").getBytes("UTF-8"));
        File file = new File(pathName);

        if (!file.exists()) {
            //两级目录
            if (!(file.getParentFile().exists())) {
                file.getParentFile().mkdir();
                file.createNewFile();
            } else {
                file.createNewFile();
            }
            generate = true;
        }
        if (generate) {
            String[] columnName = {
                    //0
                    "公司名称",
                    //1
                    "股票代码",
                    //2
                    "现价",
                    //3
                    "昨日收盘价",
                    //4
                    "开盘价",
                    //5
                    "内盘",
                    //6
                    "外盘",
                    //7
                    "日期",
                    //8
                    "涨跌",
                    //9
                    "涨跌幅",
                    //10
                    "最高价",
                    //11
                    "最低价",
                    //12
                    "价格/成交量（手）/成交额",
                    //13
                    "换手率",
                    //14
                    "市盈率",
                    //15
                    "振幅",
                    //16
                    "流通市值（万）",
                    //17
                    "总市值（万）",
                    //18
                    "市净率",
                    //19
                    "量比",
                    //20
                    "均价",
                    //21
                    "市盈率（动）",
                    //22
                    "市盈率（静）",
                    //23
                    "成交额（万）",
                    //24
                    "当日涨停价",
                    //25
                    "当日跌停价"
            };
            String sheetName = "收盘数据";
            XSSFWorkbook wb = WorkBook.createWorkBook(columnName, sheetName);
            XSSFSheet sheet = wb.getSheet(sheetName);
            int rowIndex = 1;
            for (int i = 0; i <everydayDataList.size() ; i++) {
                XSSFRow  row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(everydayDataList.get(i).getStockName());
                row.createCell(1).setCellValue(everydayDataList.get(i).getStockCode());
                row.createCell(2).setCellValue(everydayDataList.get(i).getNowPrice()==null?"0":everydayDataList.get(i).getNowPrice().toString());
                row.createCell(3).setCellValue(everydayDataList.get(i).getYesterdayPrice()==null?"0":everydayDataList.get(i).getYesterdayPrice().toString());
                row.createCell(4).setCellValue(everydayDataList.get(i).getTodayOpenPrice()==null?"0":everydayDataList.get(i).getTodayOpenPrice().toString());
                row.createCell(5).setCellValue(everydayDataList.get(i).getInnerOrderCount()==null?"0":everydayDataList.get(i).getInnerOrderCount().toString());
                row.createCell(6).setCellValue(everydayDataList.get(i).getOutOrderCount()==null?"0":everydayDataList.get(i).getOutOrderCount().toString());
                row.createCell(7).setCellValue(everydayDataList.get(i).getTime());
                row.createCell(8).setCellValue(everydayDataList.get(i).getUpOrDown()==null?"0":everydayDataList.get(i).getUpOrDown().toString());
                row.createCell(9).setCellValue(everydayDataList.get(i).getQuoteChange()==null?"0":everydayDataList.get(i).getQuoteChange().toString());
                row.createCell(10).setCellValue(everydayDataList.get(i).getHighestPrice()==null?"0":everydayDataList.get(i).getHighestPrice().toString());
                row.createCell(11).setCellValue(everydayDataList.get(i).getLowestPrice()==null?"0":everydayDataList.get(i).getLowestPrice().toString());
                row.createCell(12).setCellValue(everydayDataList.get(i).getPriceInfo());
                row.createCell(13).setCellValue(everydayDataList.get(i).getTurnoverRate()==null?"0":everydayDataList.get(i).getTurnoverRate().toString());
                row.createCell(14).setCellValue(everydayDataList.get(i).getPeRatio()==null?"0":everydayDataList.get(i).getPeRatio().toString());
                row.createCell(15).setCellValue(everydayDataList.get(i).getAmplitude()==null?"0":everydayDataList.get(i).getAmplitude().toString());
                row.createCell(16).setCellValue(everydayDataList.get(i).getCirculationMarketValue()==null?"0":everydayDataList.get(i).getCirculationMarketValue().toString());
                row.createCell(17).setCellValue(everydayDataList.get(i).getCirculationMarketValueTotal()==null?"0":everydayDataList.get(i).getCirculationMarketValueTotal().toString());
                row.createCell(18).setCellValue(everydayDataList.get(i).getPbRatio()==null?"0":everydayDataList.get(i).getPbRatio().toString());
                row.createCell(19).setCellValue(everydayDataList.get(i).getQuantityRatio()==null?"0":everydayDataList.get(i).getQuantityRatio().toString());
                row.createCell(20).setCellValue(everydayDataList.get(i).getAveragePrice()==null?"0":everydayDataList.get(i).getAveragePrice().toString());
                row.createCell(21).setCellValue(everydayDataList.get(i).getPeRatioMove()==null?"0":everydayDataList.get(i).getPeRatioMove().toString());
                row.createCell(22).setCellValue(everydayDataList.get(i).getPeRatioStatic()==null?"0":everydayDataList.get(i).getPeRatioStatic().toString());
                row.createCell(23).setCellValue(everydayDataList.get(i).getVolume()==null?"0":everydayDataList.get(i).getVolume().toString());
                row.createCell(24).setCellValue(everydayDataList.get(i).getHighLimitPrice()==null?"0":everydayDataList.get(i).getHighLimitPrice().toString());
                row.createCell(25).setCellValue(everydayDataList.get(i).getLowLimitPrice()==null?"0":everydayDataList.get(i).getLowLimitPrice().toString());
            }
            try {
                FileOutputStream outputStream = new FileOutputStream(pathName);
                wb.write(outputStream);
                wb.close();
                outputStream.close();
                log.info("===============>导出盘口信息成功");
            } catch (IOException e) {
                log.info("===============>导出盘口信息失败"+e.getMessage());
                e.printStackTrace();
            }
        }
    }



}
