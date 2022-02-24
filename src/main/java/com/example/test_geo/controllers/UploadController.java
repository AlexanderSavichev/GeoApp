package com.example.test_geo.controllers;
import com.example.test_geo.service.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
public class UploadController {
    @Autowired
    Reader NewReader;
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Выберите файл для загрузки");
            return "redirect:/";
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            Path tempDirectory = Files.createTempDirectory(null);
            Path tempFile = Files.createTempFile(tempDirectory, null, ".txt");
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            tempFile.toFile().deleteOnExit();
            tempDirectory.toFile().deleteOnExit();
            NewReader.ReadLines(tempFile);

        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        attributes.addFlashAttribute("message", "В загруженный файл добавлены координаты " + fileName + '!');

        return "redirect:/";
    }

}
