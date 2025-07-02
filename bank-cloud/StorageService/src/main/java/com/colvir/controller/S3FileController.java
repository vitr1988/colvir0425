package com.colvir.controller;

import com.amazonaws.services.s3.model.S3Object;
import com.colvir.config.S3Helper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class S3FileController {

    private final S3Helper s3Helper;

    @SneakyThrows(IOException.class)
    @PostMapping
    public void upload(@RequestParam MultipartFile file) {
        s3Helper.saveToS3(null, file.getBytes(), file.getOriginalFilename(), file.getContentType());
    }

    @SneakyThrows(IOException.class)
    @GetMapping
    public void download(@RequestParam String fileName, HttpServletResponse response) {
        Optional<S3Object> s3Object = s3Helper.getS3Object(null, fileName);
        if (s3Object.isPresent()) {
            OutputStream outputStream = response.getOutputStream();
            response.addHeader("Content-Disposition", "attachment; filename=%s".formatted(fileName));
            IOUtils.copy(s3Object.get().getObjectContent(), outputStream);
        }
    }

    @DeleteMapping
    public void delete(@RequestParam String fileName) {
        s3Helper.deleteObject(null, fileName);
    }
}
