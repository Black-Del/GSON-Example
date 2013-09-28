package com.kyudevelop.gson_example;

import com.google.gson.Gson;
import com.kyudevelop.gson_example.adapter.DataAdapter;
import com.kyudevelop.gson_example.data.DataList;
import com.kyudevelop.gson_example.data.Person;
import com.kyudevelop.gson_example.data.JsonParseData;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView listView;
	private DataAdapter adapter;
	private ArrayList<JsonParseData> dataList;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.listView);
		dataList = new ArrayList<JsonParseData>();
		adapter = new DataAdapter(this, dataList);
		listView.setAdapter(adapter);
		
		parseJSON();
	}
	
	private void parseJSON() {
		String jsonData = null;
		try {
			Log.d("jsonData", loadJsonData());
			jsonData = loadJsonData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DataList dataObject = new Gson().fromJson(jsonData, DataList.class);
		
		for (Person person : dataObject.getData()) {
			adapter.add(new JsonParseData(getApplicationContext(), person.getName(), person.getUrl(), person.getPictureUrl()));
		}
	}
	
	private String loadJsonData() throws IOException {
		InputStream textFile = getAssets().open("json_data.txt");
		int size = textFile.available();
		byte[] buffer = new byte[size];
		textFile.read(buffer);
		textFile.close();
		String jsonData = new String(buffer);
		return jsonData;
	}
}
