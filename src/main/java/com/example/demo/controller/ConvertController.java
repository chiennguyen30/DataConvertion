package com.example.demo.controller;

import com.example.demo.load.TransRequest;
import com.example.demo.model.ConvertAdapter;
import org.apache.commons.codec.DecoderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

import static com.example.demo.config.configData.convertByteArrayToHexString;

@RestController
@RequestMapping("/api/data")
public class ConvertController {
    private final ConvertAdapter convertAdapter;

    public ConvertController(ConvertAdapter convertAdapter) {
        this.convertAdapter = convertAdapter;

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value = "/{from}/{to}")
    ResponseEntity<Object> convert(@PathVariable(value = "from") String from, @PathVariable(value = "to") String to, @RequestBody TransRequest transRequest) throws DecoderException, UnsupportedEncodingException {
        String data = transRequest.getData();

        String result = null;

        // Kiểm tra đầu vào của người dùng
        if (from.equals("Binary")) {
            if (!isBinary(data)) {
                throw new IllegalArgumentException("Đầu vào không phải là chuỗi hợp lệ!");
            }
        } else if (from.equals("Base64")) {
            if(!isBase64(data)){
                throw new IllegalArgumentException("Đầu vào không phải là chuỗi hợp lệ!");
            }
        } else if (from.equals("Hex")) {
            if(!isHex(data)){
                throw new IllegalArgumentException("Đầu vào không phải là chuỗi hợp lệ!");
            }
        }


        // Thực hiện chuyển đổi
        if (from.equals("String")) {
            if (to.equals("Base64")) {
                result = convertAdapter.convertStringToBase64(data);
            } else if (to.equals("Hex")) {
                result = convertAdapter.convertStringToHex(data);
            } else if (to.equals("Byte")) {
                result = convertByteArrayToHexString(convertAdapter.convertStringToByte(data));
            } else if (to.equals("Binary")) {
                result = convertAdapter.convertStringToBinary(data);
            } else if (to.equals("String")) {
                result = convertAdapter.StringToString(data);
            }
        } else if (from.equals("Base64")) {
            if (to.equals("Hex")) {
                result = convertAdapter.convertBase64ToHexa(data);
            } else if (to.equals("String")) {
                result = convertAdapter.convertBase64ToString(data);
            } else if (to.equals("Byte")) {
                result = Arrays.toString(convertAdapter.convertBase64ToByteArray(data));
            } else if (to.equals("Binary")) {
                result = convertAdapter.convertBase64ToBinary(data);
            } else if (to.equals("Base64")) {
                result = ConvertAdapter.StringToString(data);
            }
        } else if (from.equals("Hex")) {
            if (to.equals("Base64")) {
                result = convertAdapter.convertHexToBase64(data);
            } else if (to.equals("String")) {
                result = convertAdapter.convertHexToString(data);
            } else if (to.equals("Binary")) {
                result = convertAdapter.convertHexToBinary(data);
            } else if (to.equals("Hex")) {
                result = convertAdapter.StringToString(data);
            }
        } else if (from.equals("Binary")) {
            if (to.equals("String")) {
                result = ConvertAdapter.convertBinaryToString(data);
            } else if (to.equals("Base64")) {
                result = convertAdapter.convertBinaryToBase64(data);
            } else if (to.equals("Hex")) {
                result = convertAdapter.convertBinaryToHex(data);
            } else if (to.equals("Binary")) {
                result = convertAdapter.StringToString(data);
            }
        } else {
            throw new DecoderException("Loại dữ liệu cần chuyển đổi hiện tại không hợp lệ!");
        }
        return ResponseEntity.ok().body(Map.of("result", result));
    }

    private boolean isBinary(String input) {
        // ktra chuoi dau vao co chua chuoi 0101 hay không
        return input.matches("[01]+");
    }
    private boolean isBase64(String input) {
        try {
            // Giải mã chuỗi đầu vào
            byte[] decoded = Base64.getDecoder().decode(input);
            // Nếu không có ngoại lệ, chuỗi đầu vào là chuỗi Base64 hợp lệ
            return true;
        } catch (IllegalArgumentException e) {
            // Nếu có ngoại lệ, chuỗi đầu vào không phải là chuỗi Base64 hợp lệ
            return false;
        }
    }
    private boolean isHex(String input) {
        // Kiểm tra xem chuỗi đầu vào chỉ chứa các ký tự hexa hợp lệ
        return input.matches("[0-9A-Fa-f]+");
    }
}
