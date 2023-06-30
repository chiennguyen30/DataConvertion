package com.example.demo.model;

import lombok.Data;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;
import org.apache.commons.codec.binary.Base64;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Configuration
@Data
public class ConvertAdapter {
    // Base64 -> Byte Array
    public byte[] convertBase64ToByteArray(String base64ToByte) {
        byte[] decodedBytes = Base64.decodeBase64(base64ToByte);
        return decodedBytes;
    }
    // hex -> byte
    public byte[] convertHexadecimalToByteArray(String hexToByte) {
        int len = hexToByte.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            String hex = hexToByte.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(hex, 16);
        }
        return bytes;
    }
    public String convertBase64ToString(String base64ToString) throws DecoderException {
        byte[] decodedBytes = Base64.decodeBase64(base64ToString.getBytes(StandardCharsets.UTF_8));
        String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);
        return decodedString;
    }

    // Hexadecimal -> String
    public static String convertHexadecimalToString(String hexaString) throws DecoderException {
        byte[] bytes = javax.xml.bind.DatatypeConverter.parseHexBinary(hexaString);
        String str = new String(bytes, StandardCharsets.UTF_8);
        return str;
    }

    // String -> Hexadecimal
    public static String convertStringToHexa(String stringToHexa){
        byte[] bytes = stringToHexa.getBytes(StandardCharsets.UTF_8);
        String hex = javax.xml.bind.DatatypeConverter.printHexBinary(bytes);
        return hex;
    }
    // String -> Base64
    public static String convertStringToBase64(String stringToBase){
        byte[] bytes = stringToBase.getBytes(StandardCharsets.UTF_8);
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }

    // Base64 -> Hexadecimal
    public static String convertBase64ToHexa(String baseToHex) throws DecoderException {
        byte[] decodedBytes = Base64.decodeBase64(baseToHex);
        String hex = DatatypeConverter.printHexBinary(decodedBytes);
        return hex;
    }

    // Hexadecimal -> Base64
    public static String convertHexaToBase64(String hexaToBase) throws DecoderException {
        byte[] bytes = DatatypeConverter.parseHexBinary(hexaToBase);
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }

    // String -> Byte
    public static byte[] convertStringToByte(String stringToByte) throws DecoderException {
        byte[] decodedBytes = Base64.decodeBase64(stringToByte);
        return decodedBytes;
    }
    // Hình ảnh -> Base64
    public static String convertImageToBase64(String imagePath) throws IOException {
        byte[] imageBytes = Files.readAllBytes(Path.of(imagePath));
        String base64 = Base64.encodeBase64String(imageBytes);
        return base64;
    }

}