package com.example.demo.model;
import java.util.Base64;
import javax.websocket.DecodeException;
import java.nio.charset.StandardCharsets;

public class Bytes implements convert<byte[] , String>{
    @Override
    public byte[] encode(String text) {
       byte[] output = text.getBytes(StandardCharsets.UTF_8);
       String base64encoded = Base64.getEncoder().encodeToString(output);
       return output;
    }

    @Override
    public byte[] decode(String encode) throws DecodeException {
        byte[] output = encode.getBytes(StandardCharsets.UTF_8);
        byte[] base64encoded = Base64.getDecoder().decode(encode);
        return output;
    }
}
