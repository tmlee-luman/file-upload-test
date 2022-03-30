package com.test.fileuploadtest.controller;

import com.test.fileuploadtest.maker.FileNameMaker;
import com.test.fileuploadtest.uploadpath.UploadPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = FileNameMaker.getName(UploadPath.value, multipartFile.getOriginalFilename());
        multipartFile.transferTo(new File(fileName));

        return ResponseEntity.ok()
                .body(fileName);
    }
}
