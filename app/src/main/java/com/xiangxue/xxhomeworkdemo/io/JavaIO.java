package com.xiangxue.xxhomeworkdemo.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Observable;

/**
 * Date:2019-09-17
 * author:lwb
 * Desc:
 */
public class JavaIO {
    static String filePath = "/Users/liwenbo/Desktop/oop";
    public static void main(String[] agrs) throws Exception {
        System.out.println("sssss");
//        File file = new File(filePath);
//        writeFile("你好sjljflsjflsjlfjlsfdsdf滚蛋吧", "1.txt");
    }

    private static void writeFile(String content, String fileName) throws Exception {
        byte[] bytes = content.getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath + "/"+fileName));

        int len = -1;
        byte[] buffer = new byte[1024 * 100];
        while ((len = byteArrayInputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        byteArrayInputStream.close();


        new String(buffer);
    }
}
