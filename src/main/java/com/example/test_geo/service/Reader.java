package com.example.test_geo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.URISyntaxException;

@Component("NewReader")
public class Reader extends HttpServlet {
    @Autowired
    Method FileMethod;

    public byte[] ReadLines(InputStream FilePath) throws IOException, URISyntaxException {
        InputStreamReader isr = new InputStreamReader(FilePath);
        BufferedReader reader = new BufferedReader(isr);
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
            } catch (StringIndexOutOfBoundsException e) {
                return null;
            }
            ByteResponse.flush();
            ByteResponse.close();

        }
        return ByteResponse.toByteArray();
    }
}
