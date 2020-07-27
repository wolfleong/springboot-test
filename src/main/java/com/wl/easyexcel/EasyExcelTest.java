package com.wl.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.event.SyncReadListener;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExcelTest {

    private static List<DemoData> data() {
        List<com.wl.easyexcel.DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            com.wl.easyexcel.DemoData data = new com.wl.easyexcel.DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        File file = Paths.get("/Users/wolfleong/Downloads/tmp", "simpleWrite" + System.currentTimeMillis() + ".xlsx").toFile();
        writeExcel(file);
        readExcel(file);
    }

    private static void writeExcel(File file){

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(file, com.wl.easyexcel.DemoData.class).sheet("操作记录").doWrite(data());
    }

    private static void readExcel(File file){
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        SyncReadListener readListener = new SyncReadListener();
        EasyExcel.read(file, com.wl.easyexcel.DemoData.class, readListener).sheet().doRead();
        System.out.println(readListener.getList().size());
    }

}
