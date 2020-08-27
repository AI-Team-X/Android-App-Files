package com.teamx.bottomnav;

public class ExpertAnswer {
    String ImageUri,Question;

    public ExpertAnswer(String imageUri, String question) {
        ImageUri = imageUri;
        Question = question;
    }

    public ExpertAnswer() {
    }

    public String getImageUri() {
        return ImageUri;
    }

    public void setImageUri(String imageUri) {
        ImageUri = imageUri;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }
}
