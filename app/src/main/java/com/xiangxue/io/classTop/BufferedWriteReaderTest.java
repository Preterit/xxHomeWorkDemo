package com.xiangxue.io.classTop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriteReaderTest {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        File srcfile = new File("app/src/main/java/com/xiangxue/io/testtxt/BufferedReader.txt");
        File dstFile = new File("app/src/main/java/com/xiangxue/io/testtxt/BufferedWrite.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(dstFile));
        BufferedReader br = new BufferedReader(new FileReader(srcfile));



        char[] string = new char[1024]; // 请注意此处不是byte，而是char

        while ((br.read(string))!= -1) {
            bw.write(string);
        }
        br.close();
        bw.flush();
        bw.close();
    }

}
