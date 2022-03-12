package com.example.test_geo.controllers;
import com.example.test_geo.service.Reader;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;

@Controller
public class UploadController {
    @Autowired
    Reader NewReader;
    @PostMapping("/upload")
    @GetMapping("/geocodeFile.html")
    //public @ResponseBody
    String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes, HttpServletResponse response) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Выберите файл для загрузки");
            return "redirect:/";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            byte[] data = NewReader.ReadLines(file.getInputStream());
            InputStream targetStream = new ByteArrayInputStream(data);
            response.setContentType("text/utf-8");
            IOUtils.copy(targetStream, response.getOutputStream());
            for (int x = 0; x < data.length; x++) {
                System.out.print((char)data[x] + " ");
            }

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", "В загруженный файл добавлены координаты " + fileName + '!');

        return "redirect:/";
    }

}
