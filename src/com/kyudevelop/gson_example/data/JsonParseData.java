package com.kyudevelop.gson_example.data;

import android.content.Context;

public class JsonParseData {

	private String name;
	private String url;
	private String photoUrl;

	public JsonParseData(Context context, String name, String url, String photoUrl) {
		this.name = name;
		this.url = url;
		this.photoUrl = photoUrl;
	}

	public String getName() {
		return name;
	}

	public String getURL() {
		return url;
	}

	public String getPhoto() {
		return photoUrl;
	}
}