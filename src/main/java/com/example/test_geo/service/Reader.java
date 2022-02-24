package com.example.test_geo.service;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

@Component("NewReader")
public class Reader extends HttpServlet {
    @Autowired
    Method FileMethod;

    public Byte[] ReadLines(Path FilePath) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(FilePath)));
        StringBuilder content = new StringBuilder();
        String line;
        ByteArrayOutputStream ByteResponse = new ByteArrayOutputStream();
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
            String FileResponse = FileMethod.GetLocation(content.toString());
            LonLocLocator FileLocator = new LonLocLocator();
            try {
                String LonLocResponse = "\"lon\"" + FileLocator.LonLocString(FileResponse, "\"lon\"", "\"formatted\"");
                ByteResponse.write(LonLocResponse.getBytes());
                ByteResponse.flush();
                ByteResponse.close();
               // response.setContentType("text/utf-8");
               // ServletOutputStream out = response.getOutputStream();
                //byte b [] = ByteResponse.toByteArray();
                //for(int x = 0; x < b.length; x++) {
                   //ByteResponse.writeTo(out);
                   // out.flush();
                    //IOUtils.copy((char) b[x], response.getOutputStream());
                //System.out.print((char)b[x]  + "   ");
                     //   }

                //FileWriter fw = new FileWriter(String.valueOf(FilePath), true);
                //BufferedWriter bw = new BufferedWriter(fw);
                // bw.newLine();
                // bw.write(LonLocResponse);
                // bw.newLine();
                // bw.close();
            } catch (StringIndexOutOfBoundsException e) {
                return null;
            }
        }
        return null;
    }
    @GetMapping("/geocodeFile")
    public @ResponseBody
    void getReviewedFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream sis = request.getInputStream();
        byte[] data = sis.readAllBytes();
       response.setContentType("text/utf-8");
       for (int x = 0; x < data.length; x++) {
           System.out.println((char)data[x]);
           IOUtils.copy(sis, response.getOutputStream());
       }
   }
}
