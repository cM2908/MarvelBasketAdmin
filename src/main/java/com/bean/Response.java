package com.bean;

public class Response<T> {
	
	private T data;
	private String message;
	private Integer code;
	
	public Response(){
		//Do nothing
	}
	
	public Response(T data, String message, Integer code) {
		super();
		this.data = data;
		this.message = message;
		this.code = code;
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}
