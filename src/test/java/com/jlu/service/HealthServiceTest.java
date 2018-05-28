package com.jlu.service;

import com.jlu.model.HealthForm;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/5/28.
 */
public class HealthServiceTest {

    private HealthService healthService = new HealthService();
    private String str = "{\"log_id\": 4484843672437305350, \"direction\": 0, \"words_result_num\": 17, \"words_result\": [{\"words\": \"体检」表\"}, {\"words\": \"日期2018年5月28日身高」155厘米\"}, {\"words\": \"体重|52」千克腰围89厘米\"}, {\"words\": \"收编压u7n7x0\"}, {\"words\": \"舒张压86mnHg\"}, {\"words\": \"血红蛋日154g/L\"}, {\"words\": \"血小板166x103L\"}, {\"words\": \"尿常规\"}, {\"words\": \"尿蛋白\"}, {\"words\": \"阳性\"}, {\"words\": \"尿糖\"}, {\"words\": \"阳性\"}, {\"words\": \"尿酮体\"}, {\"words\": \"阳性\"}, {\"words\": \"其他\"}, {\"words\": \"腹血糖\"}, {\"words\": \"8 mmo1/L\"}]}\n";
    private String str2 = "{\"log_id\": 77026534354870111, \"direction\": 0, \"words_result_num\": 21, \"words_result\": [{\"words\": \"体检表\"}, {\"words\": \"日期2018年5月28日|身高155|厘米\"}, {\"words\": \"体重\"}, {\"words\": \"千克\"}, {\"words\": \"腰围|89|厘米\"}, {\"words\": \"收缩压|137mnHg\"}, {\"words\": \"舒张压|86|mmHg\"}, {\"words\": \"血常规\"}, {\"words\": \"血红蛋白154|g/L\"}, {\"words\": \"白细胞\"}, {\"words\": \"x109/L\"}, {\"words\": \"血小板|166|x109/L\"}, {\"words\": \"尿常规\"}, {\"words\": \"尿蛋白\"}, {\"words\": \"阳性\"}, {\"words\": \"尿糖\"}, {\"words\": \"阳性\"}, {\"words\": \"尿酮体\"}, {\"words\": \"阳性\"}, {\"words\": \"其他\"}, {\"words\": \"空腹血糖\"}]}";
    private String str3 = "{\"log_id\": 7414481348767108382, \"direction\": 0, \"words_result_num\": 17, \"words_result\": [{\"words\": \"体检表\"}, {\"words\": \"日期|2018年5月28日身高|155厘米\"}, {\"words\": \"体重|52千克克腰围|89厘米\"}, {\"words\": \"收缩压|137mnHg\"}, {\"words\": \"舒张压86mHg\"}, {\"words\": \"血常规\"}, {\"words\": \"血红蛋白154g/L|白细胞7x103几L\"}, {\"words\": \"血小板|166x103几L\"}, {\"words\": \"尿常规\"}, {\"words\": \"尿蛋日\"}, {\"words\": \"阳性\"}, {\"words\": \"尿糖\"}, {\"words\": \"阳性\"}, {\"words\": \"尿酮体\"}, {\"words\": \"阳性\"}, {\"words\": \"其他\"}, {\"words\": \"空腹血糖\"}]}\n";
    private String str4 = "{\"log_id\": 1130803772444368235, \"direction\": 0, \"words_result_num\": 19, \"words_result\": [{\"words\": \"体检表\"}, {\"words\": \"日期2018年5月28日|身高155|厘米\"}, {\"words\": \"体重52千克\"}, {\"words\": \"腰围89厘米\"}, {\"words\": \"收缩压137mHg舒张压|86\"}, {\"words\": \"血常规\"}, {\"words\": \"血红蛋白|154|g/L白细胞\"}, {\"words\": \"x10/L\"}, {\"words\": \"血小板166|x109几L\"}, {\"words\": \"尿常规\"}, {\"words\": \"尿蛋白\"}, {\"words\": \"阳性\"}, {\"words\": \"尿糖\"}, {\"words\": \"阳性\"}, {\"words\": \"尿酮体\"}, {\"words\": \"阳性\"}, {\"words\": \"其他\"}, {\"words\": \"空腹血糖\"}, {\"words\": \"5.8 mmol/L\"}]}\n";
    private String str5 = "{\"log_id\": 7797851094687189965, \"direction\": 0, \"words_result_num\": 17, \"words_result\": [{\"words\": \"体检表\"}, {\"words\": \"日期|2018年5月28日身高|155厘米\"}, {\"words\": \"体重|52千克腰围|89厘米\"}, {\"words\": \"收缩压|137mnHg|舒张压|86mnHg\"}, {\"words\": \"血常规\"}, {\"words\": \"血红蛋白154g/L|白细胞7x109/L\"}, {\"words\": \"血小板|166x103/L\"}, {\"words\": \"尿常规\"}, {\"words\": \"尿蛋白\"}, {\"words\": \"阳性\"}, {\"words\": \"尿糖\"}, {\"words\": \"阳性\"}, {\"words\": \"尿酮体\"}, {\"words\": \"阳性\"}, {\"words\": \"其他\"}, {\"words\": \"空腹血糖\"}, {\"words\": \"5.8 mmo1/L\"}]}\n";
    private String str6 = "{\"log_id\": 1130803772444368235, \"direction\": 0, \"words_result_num\": 19, \"words_result\": [{\"words\": \"体检表\"}, {\"words\": \"日期2018年5月28日|身高155|厘米\"}, {\"words\": \"体重52千克\"}, {\"words\": \"腰围89厘米\"}, {\"words\": \"收缩压137mHg舒张压|86\"}, {\"words\": \"血常规\"}, {\"words\": \"血红蛋白|154|g/L白细胞\"}, {\"words\": \"x10/L\"}, {\"words\": \"血小板166|x109几L\"}, {\"words\": \"尿常规\"}, {\"words\": \"尿蛋白\"}, {\"words\": \"阳性\"}, {\"words\": \"尿糖\"}, {\"words\": \"阳性\"}, {\"words\": \"尿酮体\"}, {\"words\": \"阳性\"}, {\"words\": \"其他\"}, {\"words\": \"空腹血糖\"}, {\"words\": \"5.8 mmol/L\"}]}";

    @Test
    public void testParseJsonStr() throws Exception {
        healthService.parseJsonStrToForm(str);
        healthService.parseJsonStrToForm(str2);
        healthService.parseJsonStrToForm(str3);
        healthService.parseJsonStrToForm(str4);
        healthService.parseJsonStrToForm(str5);
        healthService.parseJsonStrToForm(str6);
    }

    @Test
    public void testToHealthFormString() throws Exception {
        String res = healthService.toHealthFormString(str);
        System.out.println(res);
    }

    @Test
    public void testParse() throws Exception {
        String res = healthService.toHealthFormString(str);
        HealthForm healthForm = healthService.parse(res);
        System.out.println(healthForm);
    }
}