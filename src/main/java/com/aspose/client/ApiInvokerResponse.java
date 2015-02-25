package com.aspose.client;

import java.io.InputStream;

/**
 *
 * @author Imran Anwar
 * This class is added for the response to be seperated in Json and InputStream
 */
public class ApiInvokerResponse {
		private String json = "";
		private InputStream inputStream = null;
		
		public ApiInvokerResponse(String json, InputStream inputStream){
			this.json = json;
			this.inputStream = inputStream;
		}
		public void setJson(String json) {
			this.json = json;
		}
		public String getJson() {
			return json;
		}
		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}
		public InputStream getInputStream() {
			return inputStream;
		}
}

