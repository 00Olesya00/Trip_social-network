package org.trip.files.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


@RestController
@Slf4j
public class FileUploadController {

    @GetMapping("/upload")
    public @ResponseBody String provideUploadInfo(){
        return "Вы можете загружать файл с использование этой ссылки";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST,
        consumes = {"multipart/form-data"})
    public @ResponseBody String handleFileUpload(HttpServletRequest request) throws ServletException, IOException {
        for (Part part : request.getParts()){
            String contentType = part.getContentType();
            String parameterName = part.getName();
            String filename = part.getSubmittedFileName();
            log.info("Content type: " + contentType);
            log.info("Parameter name: " + parameterName);
            log.info("File name: " + filename);

            try (InputStream inputStream = part.getInputStream()){
                byte[] content = StreamUtils.copyToByteArray(inputStream);
                Files.write(Path.of("/upload/"),
                        content);
                log.info("file has been stored");
            }
        }
        return "success";

    }


}
