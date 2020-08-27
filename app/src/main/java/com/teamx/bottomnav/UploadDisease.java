package com.teamx.bottomnav;

public class UploadDisease {
    String ImageUri;

    public UploadDisease(String imageUri) {
        ImageUri = imageUri;
    }

    public UploadDisease() {
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }
}

