package co.com.bancolombia.form.qr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta {
	
	@JsonProperty("_messageId")
	private String messageId;
	
	@JsonProperty("_version")
	private String version;
	
	@JsonProperty("_requestDate")
	private String requestDate;
	
	@JsonProperty("_responseSize")
	private String responseSize;
	
	@JsonProperty("_clientRequest")
	private String clientRequest;

	public String getMessageId() {
		return messageId;
	}
	public String getVersion() {
		return version;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public String getResponseSize() {
		return responseSize;
	}
	public String getClientRequest() {
		return clientRequest;
	}
	
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public void setResponseSize(String responseSize) {
		this.responseSize = responseSize;
	}
	public void setClientRequest(String clientRequest) {
		this.clientRequest = clientRequest;
	}
	@Override
	public String toString() {
		return String.format("Meta [messageId=%s, version=%s, requestDate=%s, responseSize=%s, clientRequest=%s]", getMessageId(), getVersion(), getRequestDate(), getResponseSize(), getClientRequest());
	}

}
