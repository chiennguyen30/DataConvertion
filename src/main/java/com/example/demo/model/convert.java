package com.example.demo.model;

import org.apache.commons.codec.DecoderException;

import javax.websocket.DecodeException;
import java.awt.*;
import java.nio.file.Path;

public interface convert <P, H> {
    P encode(H text);

    P decode(H encode) throws DecodeException, DecoderException;

}

