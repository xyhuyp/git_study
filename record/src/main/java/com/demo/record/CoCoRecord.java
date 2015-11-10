package com.demo.record;

import com.jfinal.core.Controller;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huyanpeng on 15-11-10.
 */
public class CoCoRecord  extends Controller{


    public void formatData(){
        int year = getParaToInt("year");
        int month = getParaToInt("month");
        LocalDate firstDate = LocalDate.of(year,month,1);

        int beforeMinus = 1-firstDate.getDayOfWeek().getValue();

        List<Object> dateMonth = new ArrayList<>();
        List<Record> weeks = new ArrayList<>();

        Record record ;
        int countIndex = 42 + beforeMinus;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (beforeMinus < countIndex){
            record = new Record();
            LocalDate nextDate = firstDate.plusDays(beforeMinus++);
            record.setLocalDate(nextDate.format(dtf));
            record.setRemarks("这是一个测试数据，日期是：" + nextDate.format(dtf));
            weeks.add(record);
            if(nextDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
                dateMonth.add(weeks);
                weeks = new ArrayList<>();
            }
        }
        if(!weeks.isEmpty())  dateMonth.add(weeks);
        renderJson(dateMonth);
    }

}
