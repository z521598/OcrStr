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
        healthForm.setTime(pickUp(formString, "日期", "身高", "\\d+年\\d+月\\d+日"));
        healthForm.setHeight(pickUp(formString, "身高", "体重", "\\d+"));
        healthForm.setWeight(pickUp(formString, "体重", "腰围", "\\d+"));
        healthForm.setWaistline(pickUp(formString, "腰围", "收缩压", "\\d+"));
        healthForm.setSystolicPressure(pickUp(formString, "收缩压", "舒张压", "\\d+"));
        healthForm.setDiastolicPressure(pickUp(formString, "舒张压", "血红蛋白", "\\d+"));
        healthForm.setHemoglobin(pickUp(formString, "血红蛋白", "白细胞", "\\d+"));
        healthForm.setWhiteBloodCell(pickUp(formString, "白细胞", "血小板", "\\d+"));
        healthForm.setPlatelet(pickUp(formString, "血小板", "尿蛋白", "\\d+"));
        healthForm.setUrineProtein(pickUp(formString, "尿蛋白", "尿糖", "阴性|阳性"));
        healthForm.setUrineSugar(pickUp(formString, "尿糖", "尿酮体", "阴性|阳性"));
        healthForm.setUrineKetoneBody(pickUp(formString, "尿酮体", "空腹血糖", "阴性|阳性"));
        healthForm.setFastingBloodGlucose(pickUp(formString, "空腹血糖", "", "\\d+\\.\\d+"));

        return healthForm;
    }

    public String pickUp(String formString, String keyWord, String nextKeyWord, String dataRegex) throws IllegalAccessException {
        //  System.out.println("keyWord=" + keyWord);
        int start = formString.indexOf(keyWord);
        if (start == -1) {
            // 未匹配到关键字,则直接返回
            return null;
        }
        int end;
        if (nextKeyWord == null || nextKeyWord.equals("")) {
            end = formString.length();
        } else {
            int nextIndex = formString.indexOf(nextKeyWord);
            if (nextIndex == -1) {
                end = start + 10;
            } else {
                end = nextIndex;
            }
        }
        //  System.out.println("start=" + start + ";end=" + end);
        // 截取当前数据的匹配范围
        String currentString = formString.substring(start, end);
        //  System.out.println(currentString);
        // 查找匹配数据
        //  System.out.println("pattern=" + dataRegex);
        Pattern pattern = Pattern.compile(dataRegex);
        Matcher matcher = pattern.matcher(currentString);
        if (matcher.find()) {
            String data = matcher.group();
            //  System.out.println("data=" + data);
            return data;
        }
        return null;
    }

    // 迷之安卓不支持
    @Deprecated
    public HealthForm parseByAnno(String formString) throws IllegalAccessException {
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
