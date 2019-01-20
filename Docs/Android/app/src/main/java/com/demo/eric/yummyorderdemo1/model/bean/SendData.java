package com.demo.eric.yummyorderdemo1.model.bean;

public class SendData {
    public String address;
    public String reqbody;
    public int ContentType;
    public int RequestType;
//    public SendData(int ContentType){
//        this.ContentType=ContentType;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReqbody() {
        return reqbody;
    }

    public void setReqbody(String reqbody) {
        this.reqbody = reqbody;
    }

    public int getContentType() {
        return ContentType;
    }

    public void setContentType(int contentType) {
        ContentType = contentType;
    }

    public int getRequestType() {
        return RequestType;
    }

    public void setRequestType(int requestType) {
        RequestType = requestType;
    }
}
