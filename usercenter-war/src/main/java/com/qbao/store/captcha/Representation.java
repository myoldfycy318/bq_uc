package com.qbao.store.captcha;

import javax.servlet.http.HttpServletResponse;

public abstract class Representation {
	private MediaType mediaType;
	
	public MediaType getMediaType() {
		return mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	public void outPut(HttpServletResponse response){
		
	}
}
