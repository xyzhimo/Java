package com.xyz.datamodel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.IOException;
import java.io.InputStream;

public class ReadExcel {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = ReadExcel.class.getClassLoader().getResourceAsStream("文件1.xlsx");
        ExcelReader excelReader = EasyExcel.read(inputStream, DataModel.class, new AnalysisEventListener<DataModel>() {

            @Override
            public void invoke(DataModel data, AnalysisContext context) {
                System.out.println(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
            }

        }).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
    }
}
