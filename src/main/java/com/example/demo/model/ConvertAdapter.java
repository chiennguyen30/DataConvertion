package com.example.demo.model;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Configuration;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder.decode;

@Configuration
@Data
public class ConvertAdapter {

    public static String StringToString(String stringtostring){
        return stringtostring;
    }

    /*convert form String*/
    // String to Base64
    public static String convertStringToBase64(String stringToBase){
        byte[] bytes = stringToBase.getBytes(StandardCharsets.UTF_8);
        return Base64.encodeBase64String(bytes);
    }
    // String to Hex
    public static String convertStringToHex(String stringToHexa){
        byte[] bytes = stringToHexa.getBytes(StandardCharsets.UTF_8);
        return DatatypeConverter.printHexBinary(bytes);
    }
    // String to Byte
    public static byte[] convertStringToByte(String stringToByte)  {
        return stringToByte.getBytes();
    }
    // String to Binary
    public static String convertStringToBinary(String stringToBinary) {
        StringBuilder binaryStringBuilder = new StringBuilder();

        for (char c : stringToBinary.toCharArray()) {
            String binary = Integer.toBinaryString(c);
            binaryStringBuilder.append(String.format("%8s", binary).replace(' ', '0'));
        }

        return binaryStringBuilder.toString();
    }


    /*convert form Base64*/
    // base to string
    public String convertBase64ToString(String base64ToString)  {
        byte[] decodedBytes = Base64.decodeBase64(base64ToString.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
    // Base to Byte
    public byte[] convertBase64ToByteArray(String base64ToByte) {
        return Base64.decodeBase64(base64ToByte);
    }
    // Base to Hex
    public static String convertBase64ToHexa(String baseToHex)  {
        byte[] decodedBytes = Base64.decodeBase64(baseToHex);
        return DatatypeConverter.printHexBinary(decodedBytes);
    }

    // Base64 to binary
    public static String convertBase64ToBinary(String base64String) {
        // GIAI MA BASE64 THANH BYTE
        byte[] decode = DatatypeConverter.parseBase64Binary(base64String);

        // xay dung chuoi nhi phan
        StringBuilder bb = new StringBuilder();

        // duyet qua byte trong encode
        for (byte b : decode) {
            // chuyen byte la thanh binary
            String temp = Integer.toBinaryString(b);

            // Đảm bảo rằng chuỗi nhị phân có đúng 8 ký tự bằng cách thêm số 0 ở trước nếu cần
            bb.append(String.format("%8s", temp).replace(" ", "0"));
        }

        // Trả về chuỗi nhị phân kết quả
        return bb.toString();
    }


    /*convert form Hex*/
    // hex to byte
    public byte[] convertHexToByteArray(String hexToByte) {
        int len = hexToByte.length();
        byte[] bytes = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            // lay 1 cap ky tu
            String hex = hexToByte.substring(i, i + 2);

            // chuyen hex sang int
            int decimal = Integer.parseInt(hex, 16);

            // ep kieu thanh byte
            bytes[i / 2] = (byte) decimal;
        }

        return bytes;
    }
    // Hex to String
    public static String convertHexToString(String hexString) {
        // chuyen hexa thanh byte
        byte[] bytes = DatatypeConverter.parseHexBinary(hexString);
        // tao chuoi UTF-8 tu byte[] va sd constructor cua class string
        return new String(bytes, StandardCharsets.UTF_8);
    }
    // Hex to Base
    public static String convertHexToBase64(String hexString) {
        // Chuyen hexa thanh byte
        byte[] bytes = DatatypeConverter.parseHexBinary(hexString);
        // tao chuoi B64 tu byte[] sd phg thuc encodeBase64String của class Base64
        return Base64.encodeBase64String(bytes);
    }
    // Hex to binary
    public static String convertHexToBinary(String hexString) {
        String binaryString = "";
        String hexDigits = "0123456789ABCDEF";
        for (int i = 0; i < hexString.length(); i++) {
            char hexDigit = hexString.charAt(i); // lay hexa tai vi tri i
            // lay gtri thap phan tuong ung voi ky tu hexa dung indexOf
            int decimalValue = hexDigits.indexOf(hexDigit);
            // chuyen thap phan ve chuoi nhi phan
            String binaryValue = Integer.toBinaryString(decimalValue);
            // them  0 vao dau neu chuoi co it hon 4 so
            while (binaryValue.length() < 4) {
                binaryValue = "0" + binaryValue;
            }
            // gán binary vao ket qua
            binaryString += binaryValue;
        }
        // Trả về chuỗi ký tự nhị phân
        return binaryString;
    }

    /*convert form binary*/
    // binary to string
    public static String convertBinaryToString(String binaryToString) {
        StringBuilder text = new StringBuilder();
        // chia nho binary thanh cac byte (8)
        for (int i = 0; i < binaryToString.length(); i += 8) {
            String binaryByte = binaryToString.substring(i, Math.min(i + 8, binaryToString.length()));
            // chuyen doi byte nhi phan thanh gia tri decimal
            int decimalValue = Integer.parseInt(binaryByte, 2);
            // them gia tri decimal vao mang byte
            byte[] bytes = new byte[] { (byte) decimalValue };
            // chuyen mang byte thanh chuoi kieu UTF-8 va them vao ket qua
            text.append(new String(bytes, StandardCharsets.UTF_8));
        }
        return text.toString();
    }

    // binary to base64
    public static String convertBinaryToBase64(String binaryToBase64)  {
        return Base64.encodeBase64String(binaryToBase64.getBytes());
    }

    public static String convertBinaryToHex(String bin) {
        // chuyen tu binary thanh so hex va luu vao hex
        String hex = Long.toHexString(Long.parseLong(bin, 2));
        // format chuoi hex sao cho do dai bang do dai cua binary /4 va lam tron nen
        // so 0 o dau de chac chan do dai luon dung

        return String.format("%" + (int)(Math.ceil(bin.length() / 4.0)) + "s", hex).replace(' ', '0');
    }





    /*convert

    // Byte to String
    public static String convertByteToString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    // Byte to Base64
    public static String convertByteToBase64(byte[] bytes)  {
        String base64 = Base64.encodeBase64String(bytes);
        return base64;
    }

    // Byte to Hex
    public static String convertByteToHex(byte[] bytes)  {
        String hex = DatatypeConverter.printHexBinary(bytes);
        return hex;
    }

 form Byte*/
}