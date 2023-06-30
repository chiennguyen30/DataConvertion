package com.example.demo.controller;
import com.example.demo.load.TransRequest;
import com.example.demo.model.ConvertAdapter;
import com.example.demo.model.Hexadecimal;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
public class ConvertController {
    private final ConvertAdapter convertAdapter;


    public ConvertController(ConvertAdapter convertAdapter) {
        this.convertAdapter = convertAdapter;

    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{from}/{to}")
    ResponseEntity<Object> convert(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to,
                                   @RequestBody  TransRequest transRequest) throws DecoderException {
        String textData = transRequest.getData();
        String result = null;
        if (from.equals("String")) {
            if (to.equals("Base64")) {
                result = convertAdapter.convertStringToBase64(textData);
            } else if (to.equals("Hex")) {
                result = convertAdapter.convertStringToHexa(textData);
            } else if (to.equals("Byte")) {
                result = Arrays.toString(convertAdapter.convertStringToByte(textData));
            } else {
                throw new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
            }
        } else if (from.equals("Base64")) {
            if (to.equals("Hex")) {
                result = convertAdapter.convertBase64ToHexa(textData);
            } else if (to.equals("String")) {
                result = convertAdapter.convertBase64ToString(textData);
            } else if (to.equals("Byte")) {
                result = Arrays.toString(convertAdapter.convertBase64ToByteArray(textData));
            } else {
                throw new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
            }
        } else if (from.equals("Hex")) {
            if (to.equals("Base64")) {
                result = convertAdapter.convertHexaToBase64(textData);
            } else if (to.equals("String")) {
                result = convertAdapter.convertHexadecimalToString(textData);
            } else if (to.equals("Byte")) {
                result = Arrays.toString(convertAdapter.convertHexadecimalToByteArray(textData));
            } else {
                throw new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
            }
        } else if (from.equals("Byte")) {
            if (to.equals("Hex")) {
                result = convertAdapter.convertStringToHexa(textData);
            } else if (to.equals("String")) {
                result = convertAdapter.convertBase64ToString(textData);
            } else {
                throw new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
            }
        } else {
            throw new DecoderException("Loại dữ liệu cần chuyển đổi, hiện tại không hợp lệ !");
        }

        if (from.equals(to)) {
            throw new DecoderException("Không thể chọn hai phương thức giống nhau!");
        }


        return ResponseEntity.ok().body(Map.of("result", result));
    }
    @PostMapping("/image-to-base64")
    public String convertImageToBase64(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String encodedString = Base64.getEncoder().encodeToString(bytes);
        return encodedString;
    }
    @PostMapping("/base64-to-image")
    public ResponseEntity<byte[]> convertBase64ToImage(@RequestParam("base64") String base64) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(decodedBytes, headers, HttpStatus.OK);
    }
}
