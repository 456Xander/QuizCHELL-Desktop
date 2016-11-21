package at.neonartworks.quizchell.data;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;

public class DataInterface {	
	
	public DataInterface() {
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
	}

}
