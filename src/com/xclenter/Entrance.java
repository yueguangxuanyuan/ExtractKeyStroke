package com.xclenter;


import com.xclenter.Business.KeyExtractor;
import com.xclenter.Module.KeyLatency;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Entrance {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream("resource/config.properties");

        Properties properties = new Properties();
        properties.load(inputStream);

        String sourceDir = properties.getProperty("sourceDir");
        String targetDir = properties.getProperty("targetDir");

        new KeyExtractor().extractKeyLatency(sourceDir,targetDir);

    }
}
