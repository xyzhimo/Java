package com.xyz.read;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ReadExcel {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = ReadExcel.class.getClassLoader().getResourceAsStream("file1.xlsx");
            ExcelReader reader = EasyExcelFactory.getReader(inputStream, new AnalysisEventListener<List<String>>() {
                @Override
                public void invoke(List<String> rowData, AnalysisContext context) {
                    if (context.getCurrentRowNum() == 0) {
                        return;
                    }
                    for (String cell : rowData) {
                        if (cell == null) {
                            return;
                        }
                        System.out.println(cell);
                    }

                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext context) {

                }
            });
            reader.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            inputStream.close();
        }
    }
}
