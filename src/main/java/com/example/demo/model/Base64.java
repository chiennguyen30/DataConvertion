package com.example.demo.model;

import javax.websocket.DecodeException;
import org.springframework.stereotype.Component;
public class Base64 implements convert<String,String>{
    @Override
    public String encode(String text) {
        byte[] inBytes = text.getBytes();
        String hexadecimalString = org.apache.commons.codec.binary.Base64.encodeBase64String(inBytes);
        return hexadecimalString;
    }

    @Override
    public String decode(String encode) throws DecodeException {
        byte[] outByte = org.apache.commons.codec.binary.Base64.decodeBase64(encode);
        return new String(outByte);
    }
}
