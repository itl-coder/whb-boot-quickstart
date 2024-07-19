package com.example.whb.convert;

import com.example.whb.exception.CoderitlException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建字符串到日期的类型转换器【对表单的日期处理,键值对格式】
 */
@Slf4j
@Component
public class StringToDateConvert implements Converter<String, Date> {
    @Override
    public Date convert(String sourceDate) {
        log.error("into Date convert.................: {}", sourceDate);
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(sourceDate);
            return date;
        } catch (ParseException e) {
            log.error("Date convert error: {}", e.getMessage());
            throw new CoderitlException("日期转换异常");
        }
    }
}
