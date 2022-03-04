package com.example.test_geo.service;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;

@Component("NewReader")
public class Reader extends HttpServlet {
    @Autowired
    Method FileMethod;

    public byte[] ReadLines(Path FilePath) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(FilePath)));
        StringBuilder content = new StringBuilder();
        String line;
        ByteArrayOutputStream ByteResponse = new ByteArrayOutputStream();
        FileWriter writer = new FileWriter(String.valueOf(FilePath));
        BufferedWriter buffer = new BufferedWriter(writer);

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
            String FileResponse = FileMethod.GetLocation(content.toString());
            LonLocLocator FileLocator = new LonLocLocator();
            try {
                String LonLocResponse = "\"lon\"" + FileLocator.LonLocString(FileResponse, "\"lon\"", "\"formatted\"");
                buffer.write(LonLocResponse);
                ByteResponse.write(LonLocResponse.getBytes());
            } catch (StringIndexOutOfBoundsException e) {
                return null;
            }
            ByteResponse.flush();
            ByteResponse.close();
            buffer.close();
        }

        return ByteResponse.toByteArray();
    }
}
