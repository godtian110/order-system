package com.godtian.controller;



import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.godtian.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload")
    public Result upload(@RequestParam MultipartFile file) throws IOException {
        log.info("上传文件");
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        File uploadFile = new File(fileUploadPath);
        if(!uploadFile.exists()){
            uploadFile.mkdirs();
        }
        String uuid = IdUtil.fastSimpleUUID();
        File file1 = new File(fileUploadPath + uuid + "." + type);
        String url = "http://localhost:8080/files/"+uuid + "." + type;
        file.transferTo(file1);
        return Result.success(url);
    }
}
