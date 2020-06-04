package com.web.pucmm.practica10.Services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
    
    public String uploadFile( MultipartFile file, String uploadDirectory, String uploadName ) {

        String path;

        try {
            String staticDirectory = System.getProperty("user.dir") + "/src/main/resources/static";
            String fileName = uploadName + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            Path fileNamePath = Paths.get(staticDirectory + "/" + uploadDirectory, fileName);

            Files.write(fileNamePath, file.getBytes());
            path = String.format("/%s/%s", uploadDirectory, fileName);

        } catch ( Exception e ) {
            path = null;
        }

        return path;
    }
}