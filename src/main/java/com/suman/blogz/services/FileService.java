package com.suman.blogz.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadFile(String destinationPath, MultipartFile file) throws IOException;
    InputStream getResourceFile(String sourcePath, String fileName) throws FileNotFoundException;
    String getContentType(String sourcePath, String fileName) throws IOException;
}
