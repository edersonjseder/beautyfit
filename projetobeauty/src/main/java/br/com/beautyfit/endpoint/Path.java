package br.com.beautyfit.endpoint;

public class Path {
	
	public final static String BASE_URL = "beautyfit/v1";
	public final static String OAUTH_URL = "http://192.168.1.101:8081";
	public final static String OAUTH_URL_LH = "http://localhost:8081";
	
	public static class urls{
		public  final static String CLIENTS =  BASE_URL + "/clients";
		public  final static String PRODUCTS =  BASE_URL + "/products";
		public  final static String SERVICES =  BASE_URL + "/services";
		public  final static String PROVIDERS =  BASE_URL + "/providers";
		public  final static String EMPLOYEES =  BASE_URL + "/employees";
		public  final static String BILLSTOPAY =  BASE_URL + "/billsToPay";
		public  final static String BILLSTORECEIVE =  BASE_URL + "/billsToReceive";
		public  final static String USERS =  BASE_URL + "/users";
	}
	
	public static class tokenUrl {
		public final static String TOKEN_PATH = OAUTH_URL + "/oauth/check_token";
	}
}
