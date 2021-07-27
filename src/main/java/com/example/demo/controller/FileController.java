package com.example.demo.controller;

import com.example.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin //跨域
@RestController
@RequestMapping("/ossService/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public Map upload(@RequestParam(value = "imageFile", required = false)MultipartFile file, String mimeType) {

        String uploadUrl = fileService.upload(file);
        Map map = new HashMap();
        map.put("url",uploadUrl);
        return map;
    }
}
