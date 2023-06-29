package com.example.demo.model;
import org.apache.commons.codec.binary.Base64;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ImageToBase64Converter {

    public static String convertImageToBase64(File file) throws IOException {
        byte[] bytes = Files.readAllBytes(file.toPath());
        return Base64.encodeBase64String(bytes);
    }
}
