package com.example.demo.config;

public class configData {
    public static String convertByteArrayToHexString(byte[] byteArray) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : byteArray) {
            // chuyen doi byte thanh chuoi hex
            hexStringBuilder.append(String.format("%02x ", b));
        }
        //loai bo khoang trang va return ve kq
        return hexStringBuilder.toString().trim();
    }

    public static String convertHexToString(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            //cat ra thanh 1 chuoi 2 ky tu
            String str = hex.substring(i, i + 2);
            //chuyen doi tu hex sang hexa
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }
}
