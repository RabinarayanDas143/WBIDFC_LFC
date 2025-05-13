package com.emp.constant;

/**
 * @author int6346 vivek
 */
public class ResponseBean {

	private String Status;
	private String Message;
	private Object body;
	private Object tablebody;

	public ResponseBean() {

	}

	public ResponseBean(String message) {
		super();
	}

	public ResponseBean(String status, String message, Object body , Object tablebody) {
		super();
		Status = status;
		Message = message;
		this.body = body;
		this.tablebody = tablebody;
	}

	public ResponseBean(String status, Object body) {
		super();
		Status = status;
		this.body = body;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	

	public Object getTablebody() {
		return tablebody;
	}

	public void setTablebody(Object tablebody) {
		this.tablebody = tablebody;
	}

	public ResponseBean SendResponse(String Status, String Message) {
		ResponseBean rb = new ResponseBean(Status, Message);
		rb.setMessage(Message);
		rb.setStatus(Status);
		return rb;
	}

	public ResponseBean SendResponse(String Status, String Message, Object body , Object tablebody) {
		ResponseBean rb = new ResponseBean(Status, Message, body , tablebody);
		rb.setMessage(Message);
		rb.setStatus(Status);
		rb.setBody(body);
		rb.setTablebody(tablebody);
		return rb;
	}
}
