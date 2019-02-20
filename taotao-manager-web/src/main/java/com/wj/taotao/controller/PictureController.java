package com.wj.taotao.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.wj.taotao.utils.FastDfsClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class PictureController {


//    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL = "http://10.0.0.118:8888/";

    @RequestMapping(value = "pic/upload",produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
    @ResponseBody
    public String fileUpload(MultipartFile uploadFile){
        String originalFilename = uploadFile.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        Map resultMap = new HashMap();
        try {
            FastDfsClientUtils fastDfsClientUtils = new FastDfsClientUtils("classpath:fdfs_client.conf");
            String path = fastDfsClientUtils.uploadFile(uploadFile.getBytes(), extName, null);
            String url = IMAGE_SERVER_URL + path;

            resultMap.put("error",0);
            resultMap.put("url",url);

        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error",1);
            resultMap.put("message","上传图片失败");
        }

        String json = JSONUtils.toJSONString(resultMap);

        return json;

    }
}
