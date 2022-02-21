package com.example.test_geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
@Component("NewReader")
public class Reader {
    @Autowired
    Method FileMethod;
    public String ReadLines (Path FilePath) throws IOException, URISyntaxException {
        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(FilePath)));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
           System.out.println(line);
            content.append(line);
            content.append(System.lineSeparator());
            String FileResponse = FileMethod.GetLocation(content.toString());
            LonLocLocator FileLocator = new LonLocLocator();
            String LonLocResponse = "\"lon\"" + FileLocator.LonLocString(FileResponse, "\"lon\"", "\"formatted\"");
            System.out.println(LonLocResponse);
            FileWriter fw = new FileWriter(String.valueOf(FilePath), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.newLine();
            bw.write(LonLocResponse);
            bw.newLine();
            bw.close();
        }

        return null;
    }
}
