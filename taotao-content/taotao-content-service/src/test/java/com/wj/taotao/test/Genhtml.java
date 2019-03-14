package com.wj.taotao.test;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

public class Genhtml {

    /**
     * 生成静态页面的方法
     */
    @Test
    public void testFreemarker() throws Exception {

        // 模板 + 数据   输出到静态页面

        //1.创建configuration的对象
        Configuration  configuration = new Configuration(Configuration.getVersion());
        //2.设置模板文件所在的目录(可以是任意的目录)
        String path = "E:\\ws2\\idea\\taotao-parent\\taotao-content\\taotao-content-service\\src\\test\\java\\com\\wj\\taotao\\test";
        configuration.setDirectoryForTemplateLoading(new File(path));

        //3.设置字符集
        configuration.setDefaultEncoding("utf-8");
        //4.创建一个模板文件，官方提供的是.ftl，其实任意的后缀都可以

        //5.加载模板文件 获取模板对象 (相对路径的文件名)
        Template template = configuration.getTemplate("hello.ftl");
        //6.创建数据集，可以使用map，POJO
        Map<String,Object> map = new HashMap<>();
        map.put("hello","hello world");
        //pojo
        Person person = new Person(1100,"嬴政");
        Person person2 = new Person(12,"嬴稷");
        map.put("person",person);
        map.put("person2",person2);
        //7.创建writer 输出流
        Writer writer = new FileWriter("E:/test/hello.html");
        //8.通过调用模板对象中的process方法输出
        template.process(map,writer);
        //9.关闭流
        writer.close();
    }

    /**
     * 集合
     */
    /**
     * 生成静态页面的方法
     */
    @Test
    public void testFreemarker2() throws Exception {

        // 模板 + 数据   输出到静态页面

        //1.创建configuration的对象
        Configuration  configuration = new Configuration(Configuration.getVersion());
        //2.设置模板文件所在的目录(可以是任意的目录)
        String path = "E:\\ws2\\idea\\taotao-parent\\taotao-content\\taotao-content-service\\src\\test\\java\\com\\wj\\taotao\\test";
        configuration.setDirectoryForTemplateLoading(new File(path));

        //3.设置字符集
        configuration.setDefaultEncoding("utf-8");
        //4.创建一个模板文件，官方提供的是.ftl，其实任意的后缀都可以

        //5.加载模板文件 获取模板对象 (相对路径的文件名)
        Template template = configuration.getTemplate("hello.ftl");
        //6.创建数据集，可以使用map，POJO
        Map<String,Object> map = new HashMap<>();
        map.put("hello","hello world");
        //pojo
        Person person = new Person(1100,"嬴政");
        Person person2 = new Person(12,"嬴稷");
        map.put("person",person);
        map.put("person2",person2);

        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person2);

        map.put("list",list);

        Map map2 = new HashMap();
        map2.put("person",person);
        map2.put("person2",person2);
        map.put("map",map2);

        Date date = new Date();
        map.put("date",date);

//        map.put("test","hahhahaahha ");

        //7.创建writer 输出流
        Writer writer = new FileWriter("E:/test/hello.html");
        //8.通过调用模板对象中的process方法输出
        template.process(map,writer);
        //9.关闭流
        writer.close();
    }
}
