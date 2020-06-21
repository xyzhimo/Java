package com.xyz.read;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.StringUtils;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class WriteExcel {

    public static void main(String[] args) throws Exception {

        List<Model> list = Lists.newArrayList();

        List<Model> api09 = parseLog("2019-06-09");
        list.addAll(api09);

        List<Model> api10 = parseLog("2019-06-10");
        list.addAll(api10);

        List<Model> api11 = parseLog("2019-06-11");
        list.addAll(api11);

        List<Model> api12 = parseLog("2019-06-12");
        list.addAll(api12);

        List<Model> api13 = parseLog("2019-06-13");
        list.addAll(api13);


        OutputStream out = new FileOutputStream("/Users/xiaoyizhi/Downloads/api.xlsx");
        try {
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
            //写第一个sheet,  有模型映射关系
            Class t = list.get(0).getClass();
            com.alibaba.excel.metadata.Sheet sheet = new com.alibaba.excel.metadata.Sheet(1, 0, t);
            writer.write(list, sheet);
            writer.finish();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Model> parseLog(String dateStr) throws IOException {
        List<String> api09 = Files.readLines(new File(
                "/Users/xiaoyizhi/Downloads/others/wsop-api-monitor." + dateStr + ".log"), Charsets.UTF_8);
        return api09.stream().map(x -> {
            Model model = new Model();
            List<String> info = Splitter.on(" ").splitToList(x);
            model.setDateStr(dateStr + " "+info.get(0));
            String apiInfo = info.get(6);

            List<String> apiInfoList = Splitter.on(";").splitToList(apiInfo);
            model.setApiName(apiInfoList.get(0));
            model.setVersion(Double.parseDouble(StringUtils.isEmpty(apiInfoList.get(1)) ? "0" : apiInfoList.get(1)));
            model.setTime(Integer.parseInt(apiInfoList.get(2)));

            return model;
        }).collect(Collectors.toList());
    }
}

class Model extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String dateStr;

    @ExcelProperty(index = 1)
    private String apiName;

    @ExcelProperty(index = 2)
    private Double version;

    @ExcelProperty(index = 3)
    private Integer time;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public Double getVersion() {
        return version;
    }

    public void setVersion(Double version) {
        this.version = version;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
