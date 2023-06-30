package com.example.demo.model;
import org.apache.commons.codec.DecoderException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Hex;
import javax.websocket.DecodeException;
@Component
@Primary
public class Hexadecimal implements convert<String ,String>{
    @Override
    public String encode(String text) {
        byte[] inBytes = text.getBytes();
        String hexadecimalString = Hex.encodeHexString(inBytes);
        return hexadecimalString;
    }
    @Override
    public String decode(String encode) throws DecodeException {
        try {
            byte[] outBytes = Hex.decodeHex(encode);
            return new String(outBytes);
        } catch (DecoderException e) {
            throw new DecodeException("Error decoding hex string", String.valueOf(e));
        }
    }
}
