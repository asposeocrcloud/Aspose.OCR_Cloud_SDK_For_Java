/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspose.ocr.api;

import com.aspose.client.ApiInvoker;
import com.aspose.client.ApiException;
import com.aspose.ocr.model.OCRResponse;
import java.io.File;
import java.net.URISyntaxException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author SQL
 */
public class OcrApiTest {
	OcrApi ocrApi;
	String appSID = "b8d0bf27-62f0-4b5c-a9d7-83268ac53861";
	String apiKey = "76706419dc70d5a2c37421570d1405c8";
	
	public OcrApiTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		ocrApi = new OcrApi();
		ocrApi.setBasePath("http://api.aspose.com/v1.1");
		ocrApi.getInvoker().addDefaultHeader("apiKey", apiKey);
		ocrApi.getInvoker().addDefaultHeader("appSID", appSID);

	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of GetRecognizeDocument method, of class OcrApi.
	 */
	@Test
	public void testGetRecognizeDocument() {
		System.out.println("GetRecognizeDocument");
		String name = "Sampleocr.bmp";
		String language = "";
		Integer rectX = 0;
		Integer rectY = 0;
		Integer rectWidth = 100;
		Integer rectHeight = 100;
		Boolean useDefaultDictionaries = true;
		String storage = "";
		String folder = "";

		try{
		OCRResponse result = ocrApi.GetRecognizeDocument(name, language, rectX, rectY, rectWidth, rectHeight, useDefaultDictionaries, storage, folder);
		}catch(ApiException apiExp){
			System.out.println("exp:" + apiExp.getMessage());
			assertNull(apiExp);
		}

	}

	/**
	 * Test of PostOcrFromUrlOrContent method, of class OcrApi.
	 */
	@Test
	public void testPostOcrFromUrlOrContent() {
		System.out.println("PostOcrFromUrlOrContent");
		String url = "";
		String language = "";
		Boolean useDefaultDictionaries = true;
		File file;

		try{
			file = new File(getClass().getResource("/Sampleocr.bmp").toURI());
			OCRResponse result = ocrApi.PostOcrFromUrlOrContent("http://s017.radikal.ru/i406/1202/7b/70183bef7a09.jpg", language, useDefaultDictionaries, file);
		}catch(ApiException apiExp){
			System.out.println("exp:" + apiExp.getMessage());
			assertNull(apiExp);
		}catch(java.net.URISyntaxException uriExp){
			System.out.println("uri exp:"+uriExp);
		}

	}
}
