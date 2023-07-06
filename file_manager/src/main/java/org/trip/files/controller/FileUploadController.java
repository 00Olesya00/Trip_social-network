package org.trip.files.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.trip.files.service.FileUploadService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;

@RestController
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    @GetMapping("/upload")
    public @ResponseBody String provideUploadInfo(){
        return "Вы можете загружать файл с использование этой ссылки";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public @ResponseBody String handleFileUpload(HttpServletRequest request) throws ServletException, IOException {
        fileUploadService.uploadFile(request);
        return "success";
    }


}
