package com.example.test_geo.controllers;

import com.example.test_geo.service.Reader;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

@RestController
public class UploadController {
    @Autowired
    Reader NewReader;

    @PostMapping("/upload")
    @ResponseBody
    void uploadFile(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        try {
            byte[] data = NewReader.ReadLines(file.getInputStream());
            InputStream targetStream = new ByteArrayInputStream(data);
            response.setContentType("text/utf-8");
            response.addHeader("Content-Disposition", "attachment;filename=result.txt");
            IOUtils.copy(targetStream, response.getOutputStream());
            for (int x = 0; x < data.length; x++) {
                System.out.print((char)data[x] + " ");
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
