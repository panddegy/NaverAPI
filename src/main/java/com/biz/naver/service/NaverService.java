package com.biz.naver.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.biz.naver.config.NaverClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NaverService {
	
	String movieURL="https://openapi.naver.com/v1/search/movie.json";
	String bookURL="https://openapi.naver.com/v1/search/book.json";
	String newsURL="https://openapi.naver.com/v1/search/news.json";
	
	public JSONArray getObject(String jsonString) {
		
		JSONParser jp=new JSONParser();
		JSONObject jo=null;
		try {
			jo=(JSONObject) jp.parse(jsonString);
			
			JSONArray items=(JSONArray) jo.get("items");

			return items;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getString(String cate, String searchText) {
				
		String clientId=NaverClient.ID;
		String clientKey=NaverClient.KEY;
		
		try {
			String text=URLEncoder.encode(searchText, "UTF-8");
			String apiURL=bookURL;
			if(cate.equalsIgnoreCase("MOVIE")) apiURL=movieURL;
			if(cate.equalsIgnoreCase("NEWS")) apiURL=newsURL;
			apiURL+="?query="+text;
			URL url=new URL(apiURL);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("X-Naver-Client-Id", clientId);
			conn.setRequestProperty("X-Naver-Client-Secret", clientKey);
			
			int resCode=conn.getResponseCode();
			BufferedReader buffer;
			if(resCode==200) {
				InputStreamReader is=new InputStreamReader(conn.getInputStream());
				buffer=new BufferedReader(is);
			} else {
				InputStreamReader is=new InputStreamReader(conn.getErrorStream());
				buffer=new BufferedReader(is);
			}
			
			String reader="";
			String readStrings="";
			while(true) {
				reader=buffer.readLine();
				if(reader==null) break;
				readStrings+=reader;
			}
			
			buffer.close();
			return readStrings;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
