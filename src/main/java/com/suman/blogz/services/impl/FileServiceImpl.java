package com.suman.blogz.services.impl;

import com.suman.blogz.exceptions.UnsupportedMediaTypeException;
import com.suman.blogz.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String destinationPath, MultipartFile file) throws IOException {

        // Create the destination directory if not exists
        File f = new File(destinationPath);
        if(!f.exists()) {
            f.mkdir();
        }

        // Get the original name of the file
        String originalFilename = file.getOriginalFilename();
        System.out.println("originalFilename = " + originalFilename);

        // Check whether the file is of correct format or not
        if(originalFilename == null || (!originalFilename.endsWith(".png") && !originalFilename.endsWith(".jpg") && !originalFilename.endsWith(".jpeg"))) {
            throw new UnsupportedMediaTypeException("Only PNG, JPG and JPEG files are supported.");
        }

        // Encode the original filename to replace any special character including whitespace with their corresponding percent codes (e.g: whitespace -> %20)
        String randomUUID = UUID.randomUUID().toString();
        String encodedFileName = randomUUID.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        System.out.println("encodedFileName = " + encodedFileName);

        // Create a Path object, which will be needed to copy given MultipartFile input stream to the filePath
        Path filePath = Paths.get(destinationPath, encodedFileName);

        // Finally upload the file to the filePath
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return encodedFileName;
    }

    @Override
    public InputStream getResourceFile(String sourcePath, String fileName) throws FileNotFoundException {
        String filePath = sourcePath + File.separator + fileName;
        return new FileInputStream(filePath);
    }

    // method to get the content type of the file
    @Override
    public String getContentType(String sourcePath, String fileName) throws IOException {
        Path filePath = Paths.get(sourcePath, fileName);
        return Files.probeContentType(filePath);
    }
}
