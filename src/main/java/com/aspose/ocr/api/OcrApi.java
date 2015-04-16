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

  public OcrApi(String basePath, String apiKey, String appSid) {
    this.basePath = basePath;
    apiInvoker.addDefaultHeader(apiInvoker.API_KEY, apiKey);
    apiInvoker.addDefaultHeader(apiInvoker.APP_SID, appSid);
  }

  public OcrApi(String apiKey, String appSid) {
    apiInvoker.addDefaultHeader(apiInvoker.API_KEY, apiKey);
    apiInvoker.addDefaultHeader(apiInvoker.APP_SID, appSid);
  }

  public ApiInvoker getInvoker() {
    return apiInvoker;
  }
  
  public void setBasePath(String basePath) {
    this.basePath = basePath;
  }
  
  public String getBasePath() {
    return basePath;
  }

  /**
        * GetRecognizeDocument
        * Recognize image text, language and text region can be selected, default dictionaries can be used for correction.
        * @param name   String  Name of the file to recognize.
        * @param language       String  Language of the document.
        * @param rectX  Integer Top left point X coordinate of  to recognize text inside.
        * @param rectY  Integer Top left point Y coordinate of  to recognize text inside.
        * @param rectWidth      Integer Width of  to recognize text inside.
        * @param rectHeight     Integer Height of  to recognize text inside.
        * @param useDefaultDictionaries Boolean Use default dictionaries for result correction.
        * @param storage        String  Image's storage.
        * @param folder String  Image's folder.
        * @return OCRResponse
        */

  public OCRResponse GetRecognizeDocument (String name, String language, Integer rectX, Integer rectY, Integer rectWidth, Integer rectHeight, Boolean useDefaultDictionaries, String storage, String folder) {
    Object postBody = null;
    // verify required params are set
    if(name == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String resourcePath = "/ocr/{name}/recognize/?appSid={appSid}&amp;language={language}&amp;rectX={rectX}&amp;rectY={rectY}&amp;rectWidth={rectWidth}&amp;rectHeight={rectHeight}&amp;useDefaultDictionaries={useDefaultDictionaries}&amp;storage={storage}&amp;folder={folder}";
        resourcePath = resourcePath.replaceAll("\\*", "").replace("&amp;", "&").replace("/?", "?").replace("toFormat={toFormat}", "format={format}");
    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    if(name!=null)
          resourcePath = resourcePath.replace("{" + "name" + "}" , apiInvoker.toPathValue(name));
          else
          resourcePath = resourcePath.replaceAll("[&?]name.*?(?=&|\\?|$)", "");
    if(language!=null)
          resourcePath = resourcePath.replace("{" + "language" + "}" , apiInvoker.toPathValue(language));
          else
          resourcePath = resourcePath.replaceAll("[&?]language.*?(?=&|\\?|$)", "");
    if(rectX!=null)
          resourcePath = resourcePath.replace("{" + "rectX" + "}" , apiInvoker.toPathValue(rectX));
          else
          resourcePath = resourcePath.replaceAll("[&?]rectX.*?(?=&|\\?|$)", "");
    if(rectY!=null)
          resourcePath = resourcePath.replace("{" + "rectY" + "}" , apiInvoker.toPathValue(rectY));
          else
          resourcePath = resourcePath.replaceAll("[&?]rectY.*?(?=&|\\?|$)", "");
    if(rectWidth!=null)
          resourcePath = resourcePath.replace("{" + "rectWidth" + "}" , apiInvoker.toPathValue(rectWidth));
          else
          resourcePath = resourcePath.replaceAll("[&?]rectWidth.*?(?=&|\\?|$)", "");
    if(rectHeight!=null)
          resourcePath = resourcePath.replace("{" + "rectHeight" + "}" , apiInvoker.toPathValue(rectHeight));
          else
          resourcePath = resourcePath.replaceAll("[&?]rectHeight.*?(?=&|\\?|$)", "");
    if(useDefaultDictionaries!=null)
          resourcePath = resourcePath.replace("{" + "useDefaultDictionaries" + "}" , apiInvoker.toPathValue(useDefaultDictionaries));
          else
          resourcePath = resourcePath.replaceAll("[&?]useDefaultDictionaries.*?(?=&|\\?|$)", "");
    if(storage!=null)
          resourcePath = resourcePath.replace("{" + "storage" + "}" , apiInvoker.toPathValue(storage));
          else
          resourcePath = resourcePath.replaceAll("[&?]storage.*?(?=&|\\?|$)", "");
    if(folder!=null)
          resourcePath = resourcePath.replace("{" + "folder" + "}" , apiInvoker.toPathValue(folder));
          else
          resourcePath = resourcePath.replaceAll("[&?]folder.*?(?=&|\\?|$)", "");
    String[] contentTypes = {
      "application/json"};

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

try {
                response = apiInvoker.invokeAPI(basePath, resourcePath, "GET", queryParams, postBody, headerParams, formParams, contentType);
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
  /**
        * PostOcrFromUrlOrContent
        * Recognize image text from some url if provided or from the request body content, language can be selected, default dictionaries can be used for correction.
        * @param url    String  The image file url.
        * @param language       String  Language of the document.
        * @param useDefaultDictionaries Boolean Use default dictionaries for result correction.
        * @param file   File    
        * @return OCRResponse
        */

  public OCRResponse PostOcrFromUrlOrContent (String url, String language, Boolean useDefaultDictionaries, File file) {
    Object postBody = null;
    // verify required params are set
    if(file == null ) {
       throw new ApiException(400, "missing required params");
    }
    // create path and map variables
    String resourcePath = "/ocr/recognize/?appSid={appSid}&amp;url={url}&amp;language={language}&amp;useDefaultDictionaries={useDefaultDictionaries}";
        resourcePath = resourcePath.replaceAll("\\*", "").replace("&amp;", "&").replace("/?", "?").replace("toFormat={toFormat}", "format={format}");
    // query params
    Map<String, String> queryParams = new HashMap<String, String>();
    Map<String, String> headerParams = new HashMap<String, String>();
    Map<String, String> formParams = new HashMap<String, String>();

    if(url!=null)
          resourcePath = resourcePath.replace("{" + "url" + "}" , apiInvoker.toPathValue(url));
          else
          resourcePath = resourcePath.replaceAll("[&?]url.*?(?=&|\\?|$)", "");
    if(language!=null)
          resourcePath = resourcePath.replace("{" + "language" + "}" , apiInvoker.toPathValue(language));
          else
          resourcePath = resourcePath.replaceAll("[&?]language.*?(?=&|\\?|$)", "");
    if(useDefaultDictionaries!=null)
          resourcePath = resourcePath.replace("{" + "useDefaultDictionaries" + "}" , apiInvoker.toPathValue(useDefaultDictionaries));
          else
          resourcePath = resourcePath.replaceAll("[&?]useDefaultDictionaries.*?(?=&|\\?|$)", "");
    String[] contentTypes = {
      "multipart/form-data"};

    String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

if(contentType.startsWith("multipart/form-data")) {      
      FormDataMultiPart mp = new FormDataMultiPart();
      mp.field("file", file, MediaType.MULTIPART_FORM_DATA_TYPE);
        postBody = mp;
    }
try {
                response = apiInvoker.invokeAPI(basePath, resourcePath, "POST", queryParams, postBody, headerParams, formParams, contentType);
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

