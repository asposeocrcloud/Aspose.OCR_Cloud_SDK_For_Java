package com.aspose.client;


import com.google.gson.Gson;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.multipart.FormDataMultiPart;

import javax.ws.rs.core.Response.Status.Family;

import java.util.Map;
import java.util.HashMap;

import java.io.UnsupportedEncodingException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.aspose.ocr.model.ResponseMessage;

public class ApiInvoker {
  private static ApiInvoker INSTANCE = new ApiInvoker();
  private Map<String, Client> hostMap = new HashMap<String, Client>();
  private Map<String, String> defaultHeaderMap = new HashMap<String, String>();
  private boolean isDebug = false;
  private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

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

	public static String Sign(String data, String AppKey, String AppSID, Map<String, String> queryParams, Map<String, String> headerParams, Map<String, String> formParams) {
		try {
			
			if (queryParams.get("Path") != null) {
				data = queryParams.get("Path").isEmpty() ? data.replace("/{path}/", "/") : data.replace("{path}", queryParams.get("Path"));
			} else if (formParams.get("Path") != null) {
				data = formParams.get("Path").isEmpty() ? data.replace("/{path}/", "/") : data.replace("{path}", formParams.get("Path"));
			}
			
			if (queryParams.get("src") != null) {
				data = queryParams.get("src").isEmpty() ? data.replace("/{src}/", "/") : data.replace("{src}", queryParams.get("src"));
			} else if (formParams.get("src") != null) {
				data = formParams.get("src").isEmpty() ? data.replace("/{src}/", "/") : data.replace("{src}", formParams.get("src"));
			}
			
			data = data.replace("/?", "?");
			data = data.replace(" ", "%20");
			data = data.replace("&amp;", "&");
			data = data.replace("{appSid}", AppSID);
			
			data = queryParams.get("storage") == null || queryParams.get("storage").isEmpty() ? data.replace("&storage={storage}", "") : data.replace("{storage}", queryParams.get("storage"));
			data = queryParams.get("dest") == null || queryParams.get("dest").isEmpty() ? data.replace("&dest={dest}", "") : data.replace("{dest}", queryParams.get("dest"));
			data = queryParams.get("versionId") == null || queryParams.get("versionId").isEmpty() ? data.replace("&versionId={versionId}", "") : data.replace("{versionId}", queryParams.get("versionId"));

			data = queryParams.get("newdest") == null || queryParams.get("newdest").isEmpty() ? data.replace("&newdest={newdest}", "") : data.replace("{newdest}", queryParams.get("newdest"));
			data = queryParams.get("destStorage") == null || queryParams.get("destStorage").isEmpty() ? data.replace("&destStorage={destStorage}", "") : data.replace("{destStorage}", queryParams.get("destStorage"));
			
			data = queryParams.get("name") == null || queryParams.get("name").isEmpty() ? data.replace("&name={name}", "") : data.replace("{name}", queryParams.get("name"));
			data = queryParams.get("hyperlinkIndex") == null || queryParams.get("hyperlinkIndex").isEmpty() ? data.replace("&hyperlinkIndex={hyperlinkIndex}", "") : data.replace("{hyperlinkIndex}", queryParams.get("hyperlinkIndex"));
			data = queryParams.get("withRegions") == null || queryParams.get("withRegions").isEmpty() ? data.replace("&withRegions={withRegions}", "") : data.replace("{withRegions}", queryParams.get("withRegions"));
			data = queryParams.get("bookmarkName") == null || queryParams.get("bookmarkName").isEmpty() ? data.replace("&bookmarkName={bookmarkName}", "") : data.replace("{bookmarkName}", queryParams.get("bookmarkName"));
			data = queryParams.get("objectIndex") == null || queryParams.get("objectIndex").isEmpty() ? data.replace("&objectIndex={objectIndex}", "") : data.replace("{objectIndex}", queryParams.get("objectIndex"));
			data = queryParams.get("folder") == null || queryParams.get("folder").isEmpty() ? data.replace("&folder={folder}", "") : data.replace("{folder}", queryParams.get("folder"));
			data = queryParams.get("outPath") == null || queryParams.get("outPath").isEmpty() ? data.replace("&outPath={outPath}", "") : data.replace("{outPath}", queryParams.get("outPath"));
			data = queryParams.get("password") == null || queryParams.get("password").isEmpty() ? data.replace("&password={password}", "") : data.replace("{password}", queryParams.get("password"));
			data = queryParams.get("url") == null || queryParams.get("url").isEmpty() ? data.replace("&url={url}", "") : data.replace("{url}", queryParams.get("url"));
			data = queryParams.get("withEmpty") == null || queryParams.get("withEmpty").isEmpty() ? data.replace("&withEmpty={withEmpty}", "") : data.replace("{withEmpty}", queryParams.get("withEmpty"));
			data = queryParams.get("format") == null || queryParams.get("format").isEmpty() ? data.replace("&toFormat={toFormat}", "") : data.replace("{toFormat}", queryParams.get("format")).replace("&toFormat=", "&format=");

			data = queryParams.get("rectX") == null || queryParams.get("rectX").isEmpty() ? data.replace("&rectX={rectX}", "") : data.replace("{rectX}", queryParams.get("rectX"));
			data = queryParams.get("rectY") == null || queryParams.get("rectY").isEmpty() ? data.replace("&rectY={rectY}", "") : data.replace("{rectY}", queryParams.get("rectY"));
			data = queryParams.get("rectWidth") == null || queryParams.get("rectWidth").isEmpty() ? data.replace("&rectWidth={rectWidth}", "") : data.replace("{rectWidth}", queryParams.get("rectWidth"));
			data = queryParams.get("rectHeight") == null || queryParams.get("rectHeight").isEmpty() ? data.replace("&rectHeight={rectHeight}", "") : data.replace("{rectHeight}", queryParams.get("rectHeight"));
			data = queryParams.get("useDefaultDictionaries") == null || queryParams.get("useDefaultDictionaries").isEmpty() ? data.replace("&useDefaultDictionaries={useDefaultDictionaries}", "") : data.replace("{useDefaultDictionaries}", queryParams.get("useDefaultDictionaries"));
			data = queryParams.get("language") == null || queryParams.get("language").isEmpty() ? data.replace("&language={language}", "") : data.replace("{language}", queryParams.get("language"));

			data = queryParams.get("text") == null || queryParams.get("text").isEmpty() ? data.replace("&text={text}", "") : data.replace("{text}", queryParams.get("text"));
			data = queryParams.get("type") == null || queryParams.get("type").isEmpty() ? data.replace("&type={type}", "") : data.replace("{type}", queryParams.get("type"));
			data = queryParams.get("format") == null || queryParams.get("format").isEmpty() ? data.replace("&format={format}", "") : data.replace("{format}", queryParams.get("format"));
			data = queryParams.get("resolutionX") == null || queryParams.get("resolutionX").isEmpty() ? data.replace("&resolutionX={resolutionX}", "") : data.replace("{resolutionX}", queryParams.get("resolutionX"));
			data = queryParams.get("resolutionY") == null || queryParams.get("resolutionY").isEmpty() ? data.replace("&resolutionY={resolutionY}", "") : data.replace("{resolutionY}", queryParams.get("resolutionY"));
			data = queryParams.get("dimensionX") == null || queryParams.get("dimensionX").isEmpty() ? data.replace("&dimensionX={dimensionX}", "") : data.replace("{dimensionX}", queryParams.get("dimensionX"));
			data = queryParams.get("dimensionY") == null || queryParams.get("dimensionY").isEmpty() ? data.replace("&dimensionY={dimensionY}", "") : data.replace("{dimensionY}", queryParams.get("dimensionY"));
			data = queryParams.get("enableChecksum") == null || queryParams.get("enableChecksum").isEmpty() ? data.replace("&enableChecksum={enableChecksum}", "") : data.replace("{enableChecksum}", queryParams.get("enableChecksum"));			
			data = queryParams.get("checksumValidation") == null || queryParams.get("checksumValidation").isEmpty() ? data.replace("&checksumValidation={checksumValidation}", "") : data.replace("{checksumValidation}", queryParams.get("checksumValidation"));
			data = queryParams.get("stripFnc") == null || queryParams.get("stripFnc").isEmpty() ? data.replace("&stripFnc={stripFnc}", "") : data.replace("{stripFnc}", queryParams.get("stripFnc"));
			data = queryParams.get("rotationAngle") == null || queryParams.get("rotationAngle").isEmpty() ? data.replace("&rotationAngle={rotationAngle}", "") : data.replace("{rotationAngle}", queryParams.get("rotationAngle"));
			data = queryParams.get("barcodesCount") == null || queryParams.get("barcodesCount").isEmpty() ? data.replace("&barcodesCount={barcodesCount}", "") : data.replace("{barcodesCount}", queryParams.get("barcodesCount"));
			data = queryParams.get("codeLocation") == null || queryParams.get("codeLocation").isEmpty() ? data.replace("&codeLocation={codeLocation}", "") : data.replace("{codeLocation}", queryParams.get("codeLocation"));
			data = queryParams.get("grUnit") == null || queryParams.get("grUnit").isEmpty() ? data.replace("&grUnit={grUnit}", "") : data.replace("{grUnit}", queryParams.get("grUnit"));
			data = queryParams.get("autoSize") == null || queryParams.get("autoSize").isEmpty() ? data.replace("&autoSize={autoSize}", "") : data.replace("{autoSize}", queryParams.get("autoSize"));
			data = queryParams.get("barHeight") == null || queryParams.get("barHeight").isEmpty() ? data.replace("&barHeight={barHeight}", "") : data.replace("{barHeight}", queryParams.get("barHeight"));
			data = queryParams.get("imageHeight") == null || queryParams.get("imageHeight").isEmpty() ? data.replace("&imageHeight={imageHeight}", "") : data.replace("{imageHeight}", queryParams.get("imageHeight"));
			data = queryParams.get("imageWidth") == null || queryParams.get("imageWidth").isEmpty() ? data.replace("&imageWidth={imageWidth}", "") : data.replace("{imageWidth}", queryParams.get("imageWidth"));
			data = queryParams.get("imageQuality") == null || queryParams.get("imageQuality").isEmpty() ? data.replace("&imageQuality={imageQuality}", "") : data.replace("{imageQuality}", queryParams.get("imageQuality"));
			data = queryParams.get("rotAngle") == null || queryParams.get("rotAngle").isEmpty() ? data.replace("&rotAngle={rotAngle}", "") : data.replace("{rotAngle}", queryParams.get("rotAngle"));
			data = queryParams.get("topMargin") == null || queryParams.get("topMargin").isEmpty() ? data.replace("&topMargin={topMargin}", "") : data.replace("{topMargin}", queryParams.get("topMargin"));
			data = queryParams.get("bottomMargin") == null || queryParams.get("bottomMargin").isEmpty() ? data.replace("&bottomMargin={bottomMargin}", "") : data.replace("{bottomMargin}", queryParams.get("bottomMargin"));
			data = queryParams.get("leftMargin") == null || queryParams.get("leftMargin").isEmpty() ? data.replace("&leftMargin={leftMargin}", "") : data.replace("{leftMargin}", queryParams.get("leftMargin"));
			data = queryParams.get("rightMargin") == null || queryParams.get("rightMargin").isEmpty() ? data.replace("&rightMargin={rightMargin}", "") : data.replace("{rightMargin}", queryParams.get("rightMargin"));
			
			data = queryParams.get("recursive") == null || queryParams.get("recursive").isEmpty() ? data.replace("&recursive={recursive}", "") : data.replace("{recursive}", queryParams.get("recursive"));

			Map<String, String> query = new HashMap<String, String>(1); // UriBuilder
			
			groovyx.net.http.URIBuilder uri = new groovyx.net.http.URIBuilder(data);

			String url = uri.toURI().getPath();
			url = url.replace(" ", "%20");
			System.out.println("uri:" + url);
			uri.setPath(url);
			
			uri.addQueryParams(query);

			url = uri.toURI().getPath();

			if (url.charAt(url.length() - 1) == '/') {
				String tempUrl = url.substring(0, url.length() - 1);
				uri.setPath(tempUrl);
			}
			url = uri.toURI().toString();
			url = url.replace("%2C", ",");

			SecretKeySpec signingKey = new SecretKeySpec(AppKey.getBytes(), HMAC_SHA1_ALGORITHM);

			Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
			mac.init(signingKey);

			byte[] rawHmac = mac.doFinal(url.getBytes());
			byte newresult[] = Base64.encodeBase64(rawHmac);

			String result = new String(newresult);
			result = result.substring(0, result.length() - 1);

			String encodedUrl = URLEncoder.encode(result, "UTF-8");

			return uri.toURI().toString() + "&signature=" + encodedUrl;
		} catch (java.net.URISyntaxException exURL) {
			throw new ApiException(500, exURL.getMessage());
		} catch (Exception e) {
			throw new ApiException(500, e.getMessage());
		}

	}  

  public ApiInvokerResponse invokeAPI(String host, String path, String method, Map<String, String> queryParams, Object body, Map<String, String> headerParams, Map<String, String> formParams, String contentType) throws ApiException {
    Client client = getClient(host);
	//IA: Added class ApiInvokerResponse to get both String and InputStream responses
		ApiInvokerResponse aiResponse = new ApiInvokerResponse("", null);

		String signedURL = Sign(host + path, this.defaultHeaderMap.get("apiKey"), this.defaultHeaderMap.get("appSID"), queryParams, headerParams, formParams);
	
		System.out.println("signedURL: " + signedURL);
	
	Builder builder = client.resource(signedURL).accept("application/json");
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
        response = builder.put(ClientResponse.class, serialize(body));
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
        response = builder.delete(ClientResponse.class, serialize(body));
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
			if (isDebug) {
        client.addFilter(new LoggingFilter());
			}
      hostMap.put(host, client);
    }
    return hostMap.get(host);
  }
}

