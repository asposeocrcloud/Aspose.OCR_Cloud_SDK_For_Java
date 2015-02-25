package com.aspose.ocr.model;

import com.aspose.ocr.model.PagesInfo;
import com.aspose.ocr.model.PartsInfo;
public class OCRResponse {
  private String Text = null;
  private PartsInfo PartsInfo = null;
  private PagesInfo PagesInfo = null;
  private String Code = null;
  private String Status = null;
  /**
	 * getText
	 * Gets String
	 * @return Text
	 */
  public String getText() {
    return Text;
  }

	/**
	 * setText
	 * Sets String
	 * @param Text String
	 */
  public void setText(String Text) {
    this.Text = Text;
  }

  /**
	 * getPartsInfo
	 * Gets PartsInfo
	 * @return PartsInfo
	 */
  public PartsInfo getPartsInfo() {
    return PartsInfo;
  }

	/**
	 * setPartsInfo
	 * Sets PartsInfo
	 * @param PartsInfo PartsInfo
	 */
  public void setPartsInfo(PartsInfo PartsInfo) {
    this.PartsInfo = PartsInfo;
  }

  /**
	 * getPagesInfo
	 * Gets PagesInfo
	 * @return PagesInfo
	 */
  public PagesInfo getPagesInfo() {
    return PagesInfo;
  }

	/**
	 * setPagesInfo
	 * Sets PagesInfo
	 * @param PagesInfo PagesInfo
	 */
  public void setPagesInfo(PagesInfo PagesInfo) {
    this.PagesInfo = PagesInfo;
  }

  /**
	 * getCode
	 * Gets String
	 * @return Code
	 */
  public String getCode() {
    return Code;
  }

	/**
	 * setCode
	 * Sets String
	 * @param Code String
	 */
  public void setCode(String Code) {
    this.Code = Code;
  }

  /**
	 * getStatus
	 * Gets String
	 * @return Status
	 */
  public String getStatus() {
    return Status;
  }

	/**
	 * setStatus
	 * Sets String
	 * @param Status String
	 */
  public void setStatus(String Status) {
    this.Status = Status;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class OCRResponse {\n");
    sb.append("  Text: ").append(Text).append("\n");
    sb.append("  PartsInfo: ").append(PartsInfo).append("\n");
    sb.append("  PagesInfo: ").append(PagesInfo).append("\n");
    sb.append("  Code: ").append(Code).append("\n");
    sb.append("  Status: ").append(Status).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}

