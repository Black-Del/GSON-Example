package com.kyudevelop.gson_example.adapter;

import java.io.BufferedInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyudevelop.gson_example.R;
import com.kyudevelop.gson_example.data.JsonParseData;

public class DataAdapter extends ArrayAdapter<JsonParseData> {
	private LayoutInflater mInflater;
	
	public DataAdapter(Context context, ArrayList<JsonParseData> object) {
		super(context, 0, object);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		JsonParseData data = this.getItem(position);
		v = mInflater.inflate(R.layout.list_item, null);
		TextView nameView = (TextView) v.findViewById(R.id.name);
		TextView urlView = (TextView) v.findViewById(R.id.url);
		ImageView userPicture = (ImageView) v.findViewById(R.id.picture);
		
		if (data != null) {
			nameView.setText(data.getName());
			urlView.setText(data.getURL());
			try {
			    URL imageUrl = new URL(data.getPhoto());
			    URLConnection imageConnection = imageUrl.openConnection();
			    imageConnection.connect();
			    BufferedInputStream bufferStream = new BufferedInputStream(imageConnection.getInputStream());
			    Bitmap profileImage = BitmapFactory.decodeStream(bufferStream);
			    bufferStream.close();
			    userPicture.setImageBitmap(profileImage);
			} catch (Exception e) {
			}
		}
		
		return v;
	}
}