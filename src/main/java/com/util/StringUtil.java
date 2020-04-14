package com.util;

public class StringUtil {
	public static String truncateEmailDomain(String email)
	{
		StringBuilder result = new StringBuilder();
		for(char c : email.toCharArray())
		{
			if(c == 64)
			{
				break;
			}
			result.append(c);
		}
		return result.toString();
	}
}