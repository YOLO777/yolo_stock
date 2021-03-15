package com.yolo.stock.scheduled;

import com.yolo.stock.common.util.DateUtil;
import com.yolo.stock.common.util.StockExcel;
import com.yolo.stock.entity.EverydayData;
import com.yolo.stock.entity.MoneyData;
import com.yolo.stock.mapper.EverydayDataMapper;
import com.yolo.stock.mapper.MoneyDataMapper;
import com.yolo.stock.common.util.DateUtils;
import com.yolo.stock.common.util.mail.Mail;
import com.yolo.stock.common.util.mail.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
@EnableScheduling
public class EveryDayPushScheduled {
    @Autowired
    private EverydayDataMapper everydayDataMapper;

    @Autowired
    private MoneyDataMapper moneyDataMapper;

    /**
     * 发送邮箱
     */
    @Value("${mail.sendMail}")
    private String sendMail;
    /**
     * 邮箱授权码
     */
    @Value("${mail.password}")
    private String password;

    @Value("${stockDataDirPath}")
    private String stockDataDirPath;

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");

    private SimpleDateFormat ff = new SimpleDateFormat("yyyy-M-d");

    @Scheduled(cron = "0 0 18 * * ?")
    public void updateActivityStatus() {
        if ("0".equals(DateUtils.isHoliday(ff.format(new Date())))) {
            String date = f.format(new Date());
            List<MoneyData> inList = moneyDataMapper.getTop20MoneyIn(date);
            List<MoneyData> outList = moneyDataMapper.getTop20MoneyOut(date);
            List<String> inString = new ArrayList<>();
            List<String> outString = new ArrayList<>();

            if (inList.size() > 0) {
                inList.forEach(item -> {
                    inString.add(item.getStockName() + "--" + item.getStockCode() + "主力净流入：" + item.getMainNetIn().toString());
                });
            }

            if (outList.size() > 0) {
                outList.forEach(it -> {
                    outString.add(it.getStockName() + "--" + it.getStockCode() + "主力净流出：" + it.getMainNetIn().toString());
                });
            }
            Mail mail = new Mail();
            mail.setSendMail(sendMail);
            mail.setReceiveMail("1272220612@qq.com");
            //授权码
            mail.setPassword(password);
            mail.setSubject("每日资金流向获取成功！");
            mail.setContent("<div>【每日资金】<br>" + StringUtil.join(inString.toArray(), "<br>") + "<div><br><br>" + StringUtil.join(outString.toArray(), "<br>"));
            try {
                SendMail.createSimpleMail(mail);
                log.info(format.format(new Date()) + "============每日数据定时发送成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Scheduled(cron = "0 40 16 * * ?")
    public void remind() {
        Mail mail = new Mail();
        mail.setSendMail(sendMail);
        mail.setReceiveMail("1272220612@qq.com");
        //授权码
        mail.setPassword(password);
        mail.setSubject("鼠标垫插头！");
        mail.setContent("<div>【拔鼠标垫插头】<br><div>");
        try {
            SendMail.createSimpleMail(mail);
            log.info(format.format(new Date()) + "============每日数据定时发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 50 16 * * ?")
    public void generator() throws IOException {
        try {
            if ("0".equals(DateUtils.isHoliday(ff.format(new Date())))) {
                Example example = new Example(EverydayData.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andLike("time", "%" + DateUtil.format(new Date(), "yyyyMMdd") + "%");
                List<EverydayData> list = everydayDataMapper.selectByExample(example);
                StockExcel stockExcel = new StockExcel();
                stockExcel.generatorStockData(list, stockDataDirPath);
                Mail mail = new Mail();
                mail.setSendMail(sendMail);
                mail.setReceiveMail("1272220612@qq.com");
                //授权码
                mail.setPassword(password);
                mail.setSubject("每日数据存储成功！");
                mail.setContent("<div>【每日数据存储成功】<br><div>");
                SendMail.createSimpleMail(mail);
                log.info(format.format(new Date()) + "============每日数据存储成功");
            }
        } catch (Exception e) {
            log.error(format.format(new Date()) + "============每日数据存储失败");
            e.printStackTrace();
        }

    }

}
