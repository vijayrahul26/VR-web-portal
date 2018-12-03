package com.niit.model;

public class ErrorClazz 
{
	public int errorcode;
	public String errormessage;
	
	public ErrorClazz(int errorcode, String errormessage)
	{
		super();
		this.errorcode = errorcode;
		this.errormessage = errormessage;
		
	}

	public int getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(int errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormessage() {
		return errormessage;
	}

	public void setErrormessage(String errormessage) {
		this.errormessage = errormessage;
	}
	
}
