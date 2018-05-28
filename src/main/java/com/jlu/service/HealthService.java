package com.jlu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jlu.annotation.Standard;
import com.jlu.model.HealthForm;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/5/28.
 */
public class HealthService {

    // 入口
    public HealthForm parseJsonStrToForm(String jsonStr) {
        try {
            return parse(toHealthFormString(jsonStr));
        } catch (IllegalAccessException e) {
            return new HealthForm();
        }
    }

    // 预处理 json字符串 为 连续的字符串文本
    public String toHealthFormString(String str) {
        StringBuilder formBuilder = new StringBuilder();
        JSONObject jsonObject = JSON.parseObject(str);
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject wordsJSONObject = jsonArray.getJSONObject(i);
            String words = (String) wordsJSONObject.get("words");
            formBuilder.append(words);
        }
        return formBuilder.toString();
    }

    // 解析字符串文本
    public HealthForm parse(String formString) throws IllegalAccessException {
        HealthForm healthForm = new HealthForm();
        Field[] fields = healthForm.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //  System.out.println("==================");
            Field field = fields[i];
            if (field.isAnnotationPresent(Standard.class)) {
                Standard standard = field.getAnnotation(Standard.class);
                // 当前数据的关键字
                String keyWord = standard.keyWord();
                // 下一条数据的关键字
                String nextKeyWord = "";
                //  System.out.println("keyWord=" + keyWord);
                int start = formString.indexOf(keyWord);
                if (start == -1) {
                    // 未匹配到关键字,则直接返回
                    continue;
                }
                int end;
                if (i != fields.length - 1) {
                    Field nextField = fields[i + 1];
                    if (nextField.isAnnotationPresent(Standard.class)) {
                        Standard nextAnnotation = nextField.getAnnotation(Standard.class);
                        nextKeyWord = nextAnnotation.keyWord();
                    }
                    //  System.out.println("keyWord=" + keyWord + ";nextKeyWord=" + nextKeyWord);

                    int nextIndex = formString.indexOf(nextKeyWord);
                    if (nextIndex == -1) {
                        end = start + 10;
                    } else {
                        end = nextIndex;
                    }
                } else {
                    end = formString.length();
                }

                //  System.out.println("start=" + start + ";end=" + end);
                // 截取当前数据的匹配范围
                String currentString = formString.substring(start, end);
                //  System.out.println(currentString);
                // 查找匹配数据
                String dataRegex = standard.dataRegex();
                //  System.out.println("pattern=" + dataRegex);
                Pattern pattern = Pattern.compile(dataRegex);
                Matcher matcher = pattern.matcher(currentString);
                if (matcher.find()) {
                    field.setAccessible(true);
                    String data = matcher.group();
                    //  System.out.println("data=" + data);
                    field.set(healthForm, data);
                }
            }
        }
        System.out.println(healthForm);
        return healthForm;
    }

}
