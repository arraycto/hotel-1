package cn.ajiehome.management.file.controller;

import cn.ajiehome.common.utils.BaseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Jie
 * @Date: 2020/12/27
 */
@RestController
@RequestMapping("/file")
public class FIleController {
    @Autowired
    private BaseUtils baseUtils;

    @PostMapping("/put")
    @CrossOrigin
    public String putFile(@RequestParam("file")MultipartFile multipartFile){
        return baseUtils.insertImage(multipartFile);
    }
}
