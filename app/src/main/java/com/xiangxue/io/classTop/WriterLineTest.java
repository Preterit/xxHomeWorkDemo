package com.xiangxue.io.classTop;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 * @author :  lwb
 * Date: 2019/10/14
 * Desc:
 */
public class WriterLineTest {

    public static final String path_r = "app/src/main/java/com/xiangxue/io/testtxt/moreline_read.txt";
    public static final String path_w = "app/src/main/java/com/xiangxue/io/testtxt/moreline_write.txt";

    /**
     * 字节转字符
     *
     * @read :
     * FileOutputStream : 作用将文件转换为字节输出流,
     * InputStreamReader : 作用是将字节输出流替换为字符输出流
     * BufferedReader : 作用是将流以缓存的方式,减少性能消耗
     */
    public static void method() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(
                                    new File(path_r)), "UTF-8"));

//            BufferedWriter bw =
//                    new BufferedWriter(
//                            new OutputStreamWriter(
//                                    new FileOutputStream(
//                                            new File(path_w), true)));

            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path_w), true));

            String tx;
//            while ((tx=br.readLine())!=null){
//                bw.write(tx);
//                bw.newLine();
//                System.out.println("行内容 : " + tx);
//            }

            char[] chars = new char[1024];
            int len= -1;
            while ((len = br.read(chars)) != -1) {
                bw.write(chars,0,len);
            }

            br.close();
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        method();
//        method2();
//        method3();
    }

    /**
     * 字符流
     */
    public static void method2() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path_w), true));
            BufferedReader br = new BufferedReader(new FileReader(new File(path_r)));

            char[] chars = new char[1024];

            String tx;

            while ((tx = br.readLine()) != null) {
                System.out.println("BufferedReader : " + tx);
            }

            while ((br.read(chars)) != -1) {
                bw.write(chars);
            }

            br.close();
            bw.flush();
            bw.close();

        } catch (Exception e
        ) {
            e.printStackTrace();
        }
    }

    /**
     * 字节流
     */
    public static void method3() {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path_r)));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path_w), false));

            byte[] bytes = new byte[1024];
            while (bis.read(bytes) != -1) {
                bos.write(bytes);
            }
            bis.close();
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
