package com.kyudevelop.gson_example.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyudevelop.gson_example.R;
import com.kyudevelop.gson_example.data.JsonParseData;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class DataAdapter extends ArrayAdapter<JsonParseData> {
	private LayoutInflater mInflater;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;
	
	public DataAdapter(Context context, ArrayList<JsonParseData> object) {
		super(context, 0, object);
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		options = new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.displayer(new RoundedBitmapDisplayer(20)) // 둥그렇게 출력한다.
		.build();
		this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
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
			ImageLoader.getInstance().displayImage(data.getPhoto(), userPicture, options);
		}
		
		return v;
	}
}