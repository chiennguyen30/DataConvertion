package com.example.demo.model;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import javax.websocket.DecodeException;

// danh dau class hexa la 1 bean va la bean uu tien trg truong hop co nhieu thang cung loai
@Component
@Primary
public class Hexadecimal implements convert<String, String> {

    // ma hoa string thanh hex
    @Override
    public String encode(String text) {
        return Hex.encodeHexString(text.getBytes());
    }

    // giai mai chuoi hex thanh string
    @Override
    public String decode(String encode) throws DecodeException {
        try {
            return new String(Hex.decodeHex(encode));
        } catch (DecoderException e) {
            // neu erro chay decodeException
            throw new DecodeException("Error decoding hex string", e.getMessage());
        }
    }
}