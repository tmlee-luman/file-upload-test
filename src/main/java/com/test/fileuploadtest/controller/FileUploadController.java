package com.test.fileuploadtest.controller;

import com.test.fileuploadtest.maker.FileNameMaker;
import com.test.fileuploadtest.uploadpath.UploadPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<String> upload(HttpServletRequest request, @RequestPart("file") String fileData) {
        String contentRange = request.getHeader("Content-Range");
        int rangeSeparator= contentRange.indexOf("-");
        int sizeSeparator= contentRange.indexOf("/");

        String rangeStart = contentRange.substring(0, rangeSeparator);
        String rangeEnd = contentRange.substring(rangeSeparator + 1, sizeSeparator);
        String size = contentRange.substring(sizeSeparator + 1);

        log.info("range start: {}", rangeStart);
        log.info("range end: {}", rangeEnd);
        log.info("range size: {}", size);
        log.info("file: {}", fileData);
        return ResponseEntity.ok()
                .build();
    }
}
