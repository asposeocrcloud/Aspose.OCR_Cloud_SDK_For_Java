package com.aspose.ocr.model;

import java.io.InputStream;

/**
 *
 * @author Imran Anwar
 * This class is added for the response to be separated in Json and InputStream
 */
public class ResponseMessage {
  private Integer Code = null;
  private String Message = null;
  private String Status = null;
  private InputStream inputStream = null;
  public InputStream getInputStream() {
    return inputStream;
  }
  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }
  public Integer getCode() {
    return Code;
  }
  public void setCode(Integer Code) {
    this.Code = Code;
  }
  public String getMessage() {
    return Message;
  }
  public void setMessage(String Message) {
    this.Message = Message;
  }
  public String getStatus() {
    return Status;
  }
  public void setStatus(String Status) {
    this.Status = Status;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseMessage {\n");
    sb.append("  Code: ").append(Code).append("\n");
        sb.append("  Status: ").append(Status).append("\n");
    sb.append("  Message: ").append(Message).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}


