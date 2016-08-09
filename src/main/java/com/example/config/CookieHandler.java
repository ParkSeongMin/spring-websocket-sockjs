package com.example.config;

import org.springframework.http.HttpHeaders;


public abstract class CookieHandler {

	public static void addCookie(HttpHeaders httpHeaders) {
		
		httpHeaders.set("Set-Cookie", "JSESSIONID=11231451154321233451; path=/");
		
		
		// https://www.w3.org/TR/P3P11/#compact_policies
		// p3p policy. compact policy (state allow - iframe cross domain cookie)
		httpHeaders.set("P3P", "CP=\"STA\"");
		
		//httpHeaders.set("P3P", "CP=\"ALL CURa ADMa DEVa TAIa OUR BUS IND PHY ONL UNI PUR FIN COM NAV INT DEM CNT STA POL HEA PRE LOC OTC\"");
	}
	
}
