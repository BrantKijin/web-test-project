package com.example.webtestproject.common.exception;

public class InternalServerErrorException extends RuntimeException {

    private String uniqueId;

    public String getUniqueId(){
        return uniqueId;
    }

    public void setUniqueId(String uniqueId){
        this.uniqueId = uniqueId;
    }
    public InternalServerErrorException() {
        super("서버 오류가 발생하였습니다. 관리자에게 연락하시기 바랍니다.");
    }


    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

}
