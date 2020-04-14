package com.util;

import java.util.List;

import com.google.gson.Gson;

public class JsonUtil {
	
	private static Gson gson = new Gson();
	
	public static String listToJSON(List list)
	{
		String jsonStr = gson.toJson(list);
		return jsonStr;
	}
}
