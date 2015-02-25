package com.aspose.ocr.api;

import com.aspose.client.ApiException;
import com.aspose.client.ApiInvoker;
import com.aspose.client.ApiInvokerResponse;

import com.aspose.ocr.model.OCRResponse;
import com.sun.jersey.multipart.FormDataMultiPart;

import javax.ws.rs.core.MediaType;

import java.io.File;
import java.util.*;

public class OcrApi {
  String basePath = "http://api.aspose.com/v1.1";
  ApiInvoker apiInvoker = ApiInvoker.getInstance();
  ApiInvokerResponse response = null;

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }
  
  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }
  
  public String getBasePath() {
    return basePath;
  }

  //error info- code: 404 reason: "no project found" model: <none>
  /**
	* GetRecognizeDocument
	* Recognize image text, language and text region can be selected, default dictionaries can be used for correction.
	* @param name	String	Name of the file to recognize.
	* @param language	String	Language of the document.
	* @param rectX	Integer	Top left point X coordinate of  to recognize text inside.
	* @param rectY	Integer	Top left point Y coordinate of  to recognize text inside.
	* @param rectWidth	Integer	Width of  to recognize text inside.
	* @param rectHeight	Integer	Height of  to recognize text inside.
	* @param useDefaultDictionaries	Boolean	Use default dictionaries for result correction.
	* @param storage	String	Image's storage.
	* @param folder	String	Image's folder.
	* @return OCRResponse
	*/

  public OCRResponse GetRecognizeDocument (String name, String language, Integer rectX, Integer rectY, Integer rectWidth, Integer rectHeight, Boolean useDefaultDictionaries, String storage, String folder) {
    Object postBody = null;
    // verify required params are set
    if(name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/ocr/{name}/recognize/?appSid={appSid}&amp;language={language}&amp;rectX={rectX}&amp;rectY={rectY}&amp;rectWidth={rectWidth}&amp;rectHeight={rectHeight}&amp;useDefaultDictionaries={useDefaultDictionaries}&amp;storage={storage}&amp;folder={folder}".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    if(name!=null)
      queryParams.put("name", String.valueOf(name));
    if(language!=null)
      queryParams.put("language", String.valueOf(language));
    if(rectX!=null)
      queryParams.put("rectX", String.valueOf(rectX));
    if(rectY!=null)
      queryParams.put("rectY", String.valueOf(rectY));
    if(rectWidth!=null)
      queryParams.put("rectWidth", String.valueOf(rectWidth));
    if(rectHeight!=null)
      queryParams.put("rectHeight", String.valueOf(rectHeight));
    if(useDefaultDictionaries!=null)
      queryParams.put("useDefaultDictionaries", String.valueOf(useDefaultDictionaries));
    if(storage!=null)
      queryParams.put("storage", String.valueOf(storage));
    if(folder!=null)
      queryParams.put("folder", String.valueOf(folder));
    String[] contentTypes = {
      "application/json"};

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      if(hasFields)
        postBody = mp;
    }
    try {
		response = apiInvoker.invokeAPI(basePath, path, "GET", queryParams, postBody, headerParams, formParams, contentType);
		return (OCRResponse) ApiInvoker.deserialize(response, "", OCRResponse.class);
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	throw new ApiException(404, "");
      }
      else {
        throw ex;
      }
    }
  }
  //error info- code: 404 reason: "no project found" model: <none>
  /**
	* PostOcrFromUrlOrContent
	* Recognize image text from some url if provided or from the request body content, language can be selected, default dictionaries can be used for correction.
	* @param url	String	The image file url.
	* @param language	String	Language of the document.
	* @param useDefaultDictionaries	Boolean	Use default dictionaries for result correction.
	* @param file	File	
	* @return OCRResponse
	*/

  public OCRResponse PostOcrFromUrlOrContent (String url, String language, Boolean useDefaultDictionaries, File file) {
    Object postBody = null;
    // verify required params are set
    if(file == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String path = "/ocr/recognize/?appSid={appSid}&amp;url={url}&amp;language={language}&amp;useDefaultDictionaries={useDefaultDictionaries}".replaceAll("\\{format\\}","json");

    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    if(url!=null)
      queryParams.put("url", String.valueOf(url));
    if(language!=null)
      queryParams.put("language", String.valueOf(language));
    if(useDefaultDictionaries!=null)
      queryParams.put("useDefaultDictionaries", String.valueOf(useDefaultDictionaries));
    String[] contentTypes = {
      "multipart/form-data"};

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

    if(contentType.startsWith("multipart/form-data")) {
      boolean hasFields = false;
      FormDataMultiPart mp = new FormDataMultiPart();
      hasFields = true;
      mp.field("file", file, MediaType.MULTIPART_FORM_DATA_TYPE);
      if(hasFields)
        postBody = mp;
    }
    try {
		response = apiInvoker.invokeAPI(basePath, path, "POST", queryParams, postBody, headerParams, formParams, contentType);
		return (OCRResponse) ApiInvoker.deserialize(response, "", OCRResponse.class);
    } catch (ApiException ex) {
      if(ex.getCode() == 404) {
      	throw new ApiException(404, "");
      }
      else {
        throw ex;
      }
    }
  }
  }

