package com.aspose.client;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.aspose.ocr.model.ResponseMessage;


import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.multipart.FormDataMultiPart;

public class ApiInvoker {

  private static ApiInvoker INSTANCE = new ApiInvoker();
  private Map<String, Client> hostMap = new HashMap<String, Client>();
  private Map<String, String> defaultHeaderMap = new HashMap<String, String>();
  private boolean isDebug = true;
  private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	public final String APP_SID = "appSid";
	public final String API_KEY = "apiKey";
	
  public void enableDebug() {
    isDebug = true;
  }

  public static ApiInvoker getInstance() {
    return INSTANCE;
  }

  public void addDefaultHeader(String key, String value) {
     defaultHeaderMap.put(key, value);
  }

  public static String escapeString(String str) {
		try {
      return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException e) {
      return str;
    }
  }


  public static Object deserialize(ApiInvokerResponse aiResponse, String containerType, Class cls) throws ApiException {
	String json = "";
	  try {
		  if (cls.isInstance(new ResponseMessage())) {
			  ResponseMessage rm = new ResponseMessage();
				rm.setStatus("OK");
			  rm.setCode(200);
			  if (aiResponse.getInputStream() != null) {
				  rm.setInputStream(aiResponse.getInputStream());
			  }
			  return rm;		  
		  }
			json = (String)IOUtils.toString(aiResponse.getInputStream());			
			
			if (String.class.equals(cls)) {
			  if (json != null && json.startsWith("\"") && json.endsWith("\"") && json.length() > 1) {
          return json.substring(1, json.length() - 2);
			  } else {
          return json;
      }
		  } else {		  
				Gson gson = new Gson();
				return gson.fromJson(json, cls);
      }
		} catch (IOException e) {
		
      throw new ApiException(500, e.getMessage());
    }
  }

  public static String serialize(Object obj) throws ApiException {
    try {
			if (obj != null) {
				Gson gson = new Gson();
 
				// convert java object to JSON format,
				// and returned as JSON formatted string
				return gson.toJson(obj);
			} else {
				throw new ApiException(500, "No object found");
    }
		} catch (Exception e) {
      throw new ApiException(500, e.getMessage());
    }
  }

         public boolean SaveStreamToFile(String FileNameWithPath, InputStream inputStream) {

           try {
              // write the inputStream to a FileOutputStream
              OutputStream out = new FileOutputStream(new File(FileNameWithPath));

              int read = 0;
              byte[] bytes = new byte[8192];

              while ((read = inputStream.read(bytes)) != -1) {
                 out.write(bytes, 0, read);
				}

              inputStream.close();
              out.flush();
              out.close();
              return true;

          } catch (IOException e) {
              e.printStackTrace();
              return false;
    }
       }	   

	public static String Sign(String unsignedURL, String privateKey ) {
        
		try {
			
			
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(new SecretKeySpec(privateKey.getBytes(), HMAC_SHA1_ALGORITHM));
			
            String signature = new String(Base64.encodeBase64(mac.doFinal(unsignedURL.getBytes())));
			
            if(signature.endsWith("=")){
                signature = signature.substring(0, signature.length() - 1);
            }

            return URLEncoder.encode(signature, "UTF-8");
			
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return null;
			}


	public ApiInvokerResponse invokeAPI(String host, String resourceURL, String method, Map<String, String> queryParams, Object body, Map<String, String> headerParams, Map<String, String> formParams, String contentType) throws ApiException {
		Client client = getClient(host);

		//IA: Added class ApiInvokerResponse to get both String and InputStream responses
		ApiInvokerResponse aiResponse = new ApiInvokerResponse("", null);

		resourceURL = resourceURL.replace("{appSid}", this.defaultHeaderMap.get(APP_SID));

		resourceURL = resourceURL.replaceAll("\\{\\w*\\}", "");


		StringBuilder resourceURLBuilder = new StringBuilder(host).append(resourceURL);

	    String signature = Sign(resourceURLBuilder.toString(), this.defaultHeaderMap.get(API_KEY));
	
		resourceURLBuilder.append("&signature=").append(signature);
	
		Builder builder = client.resource(resourceURLBuilder.toString()).accept("application/json");
		for (String key : headerParams.keySet()) {
      builder.header(key, headerParams.get(key));
    }

		for (String key : defaultHeaderMap.keySet()) {
			if (!headerParams.containsKey(key)) {
        builder.header(key, defaultHeaderMap.get(key));
      }
    }
    ClientResponse response = null;

		if ("GET".equals(method)) {
      response = (ClientResponse) builder.get(ClientResponse.class);
		} else if ("POST".equals(method)) {
			if (body == null) {
        response = builder.post(ClientResponse.class, null);
			} else if (body instanceof FormDataMultiPart) {
        response = builder.type(contentType).post(ClientResponse.class, body);
			} else {
        response = builder.type(contentType).post(ClientResponse.class, serialize(body));
    }
		} else if ("PUT".equals(method)) {
			if (body == null) {
				response = (ClientResponse) builder.put(ClientResponse.class);
			} else {
				if ("application/x-www-form-urlencoded".equals(contentType)) {
          StringBuilder formParamBuilder = new StringBuilder();

          // encode the form params
					for (String key : formParams.keySet()) {
            String value = formParams.get(key);
						if (value != null && !"".equals(value.trim())) {
							if (formParamBuilder.length() > 0) {
                formParamBuilder.append("&");
              }
              try {
                formParamBuilder.append(URLEncoder.encode(key, "utf8")).append("=").append(URLEncoder.encode(value, "utf8"));
							} catch (Exception e) {
                // move on to next
              }
            }
          }
          response = builder.type(contentType).put(ClientResponse.class, formParamBuilder.toString());
				} else if (body instanceof FormDataMultiPart) {
					FormDataMultiPart form = (FormDataMultiPart) body;
					File file = (File) form.getField("file").getEntity();
					try {
					InputStream fileInStream = new FileInputStream(file);
						String sContentDisposition = "attachment; filename=\"" + file.getName() + "\"";
					response = builder.type(MediaType.APPLICATION_OCTET_STREAM).header("Content-Disposition", sContentDisposition).put(ClientResponse.class, fileInStream);
					} catch (java.io.FileNotFoundException ex) {
					}
				} else {
          response = builder.type(contentType).put(ClientResponse.class, serialize(body));
      }
    }
		} else if ("DELETE".equals(method)) {
			if (body == null) {
        response = builder.delete(ClientResponse.class);
			} else {
        response = builder.type(contentType).delete(ClientResponse.class, serialize(body));
    }
		} else {
      throw new ApiException(500, "unknown method type " + method);
    }
		if (response.getClientResponseStatus() == ClientResponse.Status.NO_CONTENT) {
      throw new ApiException(500, "No contents found");
	} else if (response.getClientResponseStatus().getFamily() == Family.SUCCESSFUL) {
		// IA: Check added about Stream OR Json
			if (response.getEntityInputStream() != null) {
			aiResponse.setInputStream(response.getEntityInputStream());
			} else {
			aiResponse.setJson((String) response.getEntity(String.class));
    }
		return aiResponse;
	} else {
      throw new ApiException(
                response.getClientResponseStatus().getStatusCode(),
                response.getEntity(String.class));
    }
  }

  private Client getClient(String host) {
		if (!hostMap.containsKey(host)) {
      Client client = Client.create();
			if (isDebug){ 
        client.addFilter(new LoggingFilter());
			}
      hostMap.put(host, client);
    }
    return hostMap.get(host);
  }
	
	/**
     * Overloaded method for returning the path value
     * For a string value an empty value is returned if the value is null
     * @param value
     * @return
     */
    public String toPathValue(String value) {
        return (value == null) ? "" : value;
}
    
    public String toPathValue(Integer value) {
        return value.toString();
    }
    
    public String toPathValue(Float value) {
        return value.toString();
    }
    
    public String toPathValue(Long value) {
        return value.toString();
    }
    
    public String toPathValue(Boolean value) {
        return value.toString();
    }

    public String toPathValue(Double value) {
        return value.toString();
    }

     public String toPathValue(java.util.Date value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return  format.format(value);
    }
}

