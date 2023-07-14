package com.example.demo.model;

import org.apache.commons.codec.DecoderException;

import javax.websocket.DecodeException;
import java.awt.*;
import java.nio.file.Path;
//interface chung de ma hoa va giai ma cac loai du lieu khac nhau
public interface convert <P, H> {
    //2 PT truu tg la encode va decode
    // ma hoa dau vao
    P encode(H text);

    // ma hoa dau ra
    P decode(H encode) throws DecodeException, DecoderException;
//mang ra hai ngoai le (decodeException,decoderException) de bao hieu giai ma khong thanh cong
}

