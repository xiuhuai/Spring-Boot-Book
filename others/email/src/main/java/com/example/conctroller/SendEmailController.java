/*
package com.example.conctroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * Copyright (C), 2019-2019, XXX有限公司
 * FileName: SendEmailController
 * Author:   longzhonghua
 * Date:     2019/5/10 9:20
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 *//*

public class SendEmailController {
    long l = System.currentTimeMillis();
    //new日期对象
    Date date = new Date(l);
    //转换提日期输出格式
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    String nyr = dateFormat.format(date);

    private static String UPLOADED_FOLDER = "/UPLOAD/img/";

    //    private static String UPLOADED_FOLDER = "I://UPLOAD//img//";
    @PostMapping("/upload") // //new annotation since 4.3
    @ResponseBody
    //特别注意ckeditor上传的是upload字段
    public Map<String, Object> imgFileUpload(@RequestParam("upload") MultipartFile file,
                                                RedirectAttributes redirectAttributes) {

        Map<String, Object> map = new HashMap<String, Object>();

        if (file.getOriginalFilename().endsWith(".jpg") || file.getOriginalFilename().endsWith(".png") || file.getOriginalFilename().endsWith(".gif")) {

            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();

                String S = nyr + Math.random() + file.getOriginalFilename();

                Path path = Paths.get(UPLOADED_FOLDER + S);
                Files.write(path, bytes);
                map.put("uploaded", 1);
                map.put("fileName", S);
                map.put("url", "/UPLOAD/img/" + S);
                map.put("message", "上传成功");
                return map;

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }


}*/
