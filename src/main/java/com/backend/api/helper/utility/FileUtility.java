package com.backend.api.helper.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtility {

    public static File convertMultiPartToFile(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(convFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return convFile;
    }
}
