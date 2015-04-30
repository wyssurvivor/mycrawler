package com.wys;
import java.io.InputStream;

import org.jsoup.Connection.KeyVal;
public class MyKeyVal implements KeyVal{
	private String mkey;
	private String mvalue;
	public MyKeyVal(String mKey,String mValue){
		this.mkey=mKey;
		this.mvalue=mValue;
	}
	public boolean hasInputStream() {
		// TODO Auto-generated method stub
		return false;
	}

	public InputStream inputStream() {
		// TODO Auto-generated method stub
		return null;
	}

	public KeyVal inputStream(InputStream arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String key() {
		// TODO Auto-generated method stub
		return mkey;
	}

	public KeyVal key(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String value() {
		// TODO Auto-generated method stub
		return mvalue;
	}

	public KeyVal value(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
