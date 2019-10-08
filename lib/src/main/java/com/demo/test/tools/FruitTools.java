package com.demo.test.tools;

import com.demo.test.fruitAnnotation.FruitColor;
import com.demo.test.fruitAnnotation.FruitName;
import com.demo.test.fruitAnnotation.FruitProducerFactory;

import java.io.FileOutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import sun.misc.ProxyGenerator;

/**
 * 学无止境，让学习成为一种享受
 * TODO: 主讲Zero老师QQ 2124346685
 * TODO: 往期课程咨询芊芊老师QQ 2130753077
 * TODO: VIP课程咨询伊娜老师QQ 2133576719
 * 类说明:获取水果注解信息的工具类
 */
public class FruitTools {

    /**
     * 生成代理类文件
     */
    public static void generyProxyFile() {
        byte[] bytes = ProxyGenerator.generateProxyClass("fruitProxy0", FruitName.class.getInterfaces());
        String path = "./fruitProxy0.class";
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(bytes);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("写入出错类");
        }

    }

    public static void getFruitInfo(Class<?> clazz) {
        generyProxyFile();
        //TODO 使用反射获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FruitName.class)) {  //水果名称
                FruitName fruitName = field.getAnnotation(FruitName.class);
                System.out.println("水果名称: " + fruitName.value());
            } else if (field.isAnnotationPresent(FruitColor.class)) {
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                System.out.println("fruitColor: " + fruitColor.fruitColor());
            } else if (field.isAnnotationPresent(FruitProducerFactory.class)) {
                FruitProducerFactory producerFactory = field.getAnnotation(FruitProducerFactory.class);
                System.out.println("producerFactory  id:" + producerFactory.id() + "  name: " + producerFactory.name() + "  address: " + producerFactory.address());
            }
        }
        System.out.println("=====================");

        Annotation[] annotations = clazz.getAnnotations();
        for(Annotation annotation: annotations){
            System.out.println("annotation: " + annotation);
        }

    }
}
