package ru.geekbrains.winter.market.api;



public class AppError {

    private int statusCode;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public AppError(int statusCode, String message){
        this.statusCode = statusCode;
        this.message = message;

    }
}
