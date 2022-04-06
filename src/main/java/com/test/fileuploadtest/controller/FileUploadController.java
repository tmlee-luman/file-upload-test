package com.test.fileuploadtest.controller;

import com.test.fileuploadtest.maker.FileNameMaker;
import com.test.fileuploadtest.uploadpath.UploadPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
public class FileUploadController {

    @PostMapping("/api/v1/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String fileName = FileNameMaker.getName(UploadPath.value, multipartFile.getOriginalFilename());
        multipartFile.transferTo(new File(fileName));

        return ResponseEntity.ok()
                .body(fileName);
    }

    @PostMapping("/api/v2/upload")
    public ResponseEntity<String> upload(HttpServletRequest request, @RequestBody MultipartFile multipartFile) {
        String contentRange = request.getHeader("Conetent-Range");
        int rangeSeparator= contentRange.indexOf("-");
        int sizeSeparator= contentRange.indexOf("/");

        String rangeStart = contentRange.substring(0, rangeSeparator);
        String rangeEnd = contentRange.substring(rangeSeparator, sizeSeparator);
        String size = contentRange.substring(sizeSeparator);

        log.trace("range start: {}", rangeStart);
        log.trace("range end: {}", rangeEnd);
        log.trace("range size: {}", size);
        log.trace("file: {}", multipartFile.getOriginalFilename());
        return ResponseEntity.ok()
                .build();
    }
}
