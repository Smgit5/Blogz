package com.suman.blogz.controllers;

import com.suman.blogz.services.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/blogz/file/")
public class FileController {
    @Autowired
    private FileService fileService;

    @Value("${file.path}")
    private String filesPath;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileHandler(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.uploadFile(filesPath, file));
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<Void> serveFileHandler(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        response.setContentType(fileService.getContentType(filesPath, fileName));
        StreamUtils.copy(fileService.getResourceFile(filesPath, fileName), response.getOutputStream());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
