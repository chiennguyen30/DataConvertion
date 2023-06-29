package com.example.demo.model;

public class ImageRequest {
    private String operation;
    private byte[] file;
    private String base64;

    // Constructors, getters, and setters

    public ImageRequest(String operation, byte[] file, String base64) {
        this.operation = operation;
        this.file = file;
        this.base64 = base64;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }
}
