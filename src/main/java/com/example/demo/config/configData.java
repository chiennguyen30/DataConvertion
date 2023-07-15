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

    public static byte[] convertBinaryToByteArray(String binaryString) {
        // Tính số lượng byte cần thiết để lưu trữ chuỗi binary
        int numBytes = (int) Math.ceil(binaryString.length() / 8.0);

        // Khởi tạo mảng byte để lưu trữ kết quả
        byte[] byteArray = new byte[numBytes];

        // Duyệt qua từng byte trong mảng byte
        for (int i = 0; i < numBytes; i++) {
            // Tính chỉ mục của bit cuối cùng trong byte hiện tại
            int endIndex = Math.min((i + 1) * 8, binaryString.length());

            // Lấy chuỗi binary tương ứng với byte hiện tại
            String byteString = binaryString.substring(i * 8, endIndex);

            // Chuyển đổi chuỗi binary thành giá trị byte và lưu vào mảng byte
            byte byteValue = (byte) Integer.parseInt(byteString, 2);
            byteArray[i] = byteValue;
        }

        // Trả về mảng byte kết quả
        return byteArray;
    }
public static String convertHexDigitToBinary(char hexDigit) {
    String binaryValue = "";
    switch (hexDigit) {
        case '0':
            binaryValue = "0000";
            break;
        case '1':
            binaryValue = "0001";
            break;
        case '2':
            binaryValue = "0010";
            break;
        case '3':
            binaryValue = "0011";
            break;
        case '4':
            binaryValue = "0100";
            break;
        case '5':
            binaryValue = "0101";
            break;
        case '6':
            binaryValue = "0110";
            break;
        case '7':
            binaryValue = "0111";
            break;
        case '8':
            binaryValue = "1000";
            break;
        case '9':
            binaryValue = "1001";
            break;
        case 'A':
        case 'a':
            binaryValue = "1010";
            break;
        case 'B':
        case 'b':
            binaryValue = "1011";
            break;
        case 'C':
        case 'c':
            binaryValue = "1100";
            break;
        case 'D':
        case 'd':
            binaryValue = "1101";
            break;
        case 'E':
        case 'e':
            binaryValue = "1110";
            break;
        case 'F':
        case 'f':
            binaryValue = "1111";
            break;
        default:
            // xử lý trường hợp ký tự không hợp lệ
            break;
    }
    return binaryValue;
}
}
