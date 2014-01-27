/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.ririn.imageloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.utils.L; 
import com.nostra13.example.universalimageloader.R;
import com.ririn.imageloader.Constants.Extra;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class HomeActivity extends BaseActivity {

	private static final String TEST_FILE_NAME = "Universal Image Loader @#&=+-_.,!()~'%20.png";
	//
	String[] GAMBAR;
	String[] JUDUL;
	String[] SOAL;
	String[] RESUME;
	String[] KODE;
	String[] MATERI;
	// url to make request
	private static String url = "http://ganjarsolutions.com/resep/rest.php";
	
	// JSON Node names
	private static final String TAG_RESEP = "resep";

	// recipe JSONArray
	JSONArray resep = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ac_home);

		File testImageOnSdCard = new File("/mnt/sdcard", TEST_FILE_NAME);
		if (!testImageOnSdCard.exists()) {
			copyTestImageToSdCard(testImageOnSdCard);
		}
		new DownloadTask().execute(url);
	}

	public void onImageListClick(View view) {
		Intent intent = new Intent(this, ImageListActivity.class);
		intent.putExtra(Extra.TAG_JUDUL, JUDUL);
		intent.putExtra(Extra.TAG_INTRO, MATERI);
		intent.putExtra(Extra.TAG_BAHAN, RESUME);
		intent.putExtra(Extra.TAG_PENYAJIAN, SOAL);
		intent.putExtra(Extra.TAG_GAMBAR, GAMBAR);
		startActivity(intent);
	}

	public void onImageGridClick(View view) {
		Intent intent = new Intent(this, ImageGridActivity.class);
		intent.putExtra(Extra.TAG_JUDUL, JUDUL);
		intent.putExtra(Extra.TAG_INTRO, MATERI);
		intent.putExtra(Extra.TAG_BAHAN, RESUME);
		intent.putExtra(Extra.TAG_PENYAJIAN, SOAL);
		intent.putExtra(Extra.TAG_GAMBAR, GAMBAR);
		startActivity(intent);
	}

	public void onImagePagerClick(View view) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		intent.putExtra(Extra.TAG_JUDUL, JUDUL);
		intent.putExtra(Extra.TAG_INTRO, MATERI);
		intent.putExtra(Extra.TAG_BAHAN, RESUME);
		intent.putExtra(Extra.TAG_PENYAJIAN, SOAL);
		intent.putExtra(Extra.TAG_GAMBAR, GAMBAR);
		startActivity(intent);
	}

	public void onImageGalleryClick(View view) {
		Intent intent = new Intent(this, ImageGalleryActivity.class);
		intent.putExtra(Extra.TAG_JUDUL, JUDUL);
		intent.putExtra(Extra.TAG_INTRO, MATERI);
		intent.putExtra(Extra.TAG_BAHAN, RESUME);
		intent.putExtra(Extra.TAG_PENYAJIAN, SOAL);
		intent.putExtra(Extra.TAG_GAMBAR, GAMBAR);
		startActivity(intent);
	}
	
	public void onHelpClick(View view) {
		Intent intent = new Intent(this, HelpActivity.class);
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		imageLoader.stop();
		super.onBackPressed();
	}

	private void copyTestImageToSdCard(final File testImageOnSdCard) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					InputStream is = getAssets().open(TEST_FILE_NAME);
					FileOutputStream fos = new FileOutputStream(testImageOnSdCard);
					byte[] buffer = new byte[8192];
					int read;
					try {
						while ((read = is.read(buffer)) != -1) {
							fos.write(buffer, 0, read);
						}
					} finally {
						fos.flush();
						fos.close();
						is.close();
					}
				} catch (IOException e) {
					L.w("Can't copy test image onto SD card");
				}
			}
		}).start();
	}
	
	private class DownloadTask extends AsyncTask <String, Integer, Long> {
		private String[] gam;
		private String[] jud;
		private String[] itr;
		private String[] kod;
		private String[] bah;
		private String[] pen;
		@Override
		protected Long doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			// Creating JSON Parser instance
			JSONParser jParser = new JSONParser();

			// getting JSON string from URL
			JSONObject json = jParser.getJSONFromUrl(arg0[0]);
			//
			try {
				// Getting Array of Contacts
				resep = json.getJSONArray(TAG_RESEP);
				gam = new String[resep.length()];
				jud = new String[resep.length()];
				itr = new String[resep.length()];
				kod = new String[resep.length()];
				pen = new String[resep.length()];
				bah = new String[resep.length()];
				// looping through All Contacts
				for(int i = 0; i < resep.length(); i++){
					JSONObject c = resep.getJSONObject(i);
					
					// Storing each json item in variable
					String kode = c.getString(Extra.TAG_KODE);
					kod[i] = kode;
					String judul = c.getString(Extra.TAG_JUDUL);
					jud[i] = judul;
					String intro = c.getString(Extra.TAG_INTRO);
					itr[i] = intro;
 					String gambar = c.getString(Extra.TAG_GAMBAR);
					gam[i] = gambar;
					String bahan = c.getString(Extra.TAG_BAHAN);
					bah[i] = bahan;
					String penyajian = c.getString(Extra.TAG_PENYAJIAN);
					pen[i] = penyajian;
					
					// Phone number is agin JSON Object
					
					// creating new HashMap
					HashMap<String, Object> map = new HashMap<String, Object>();
					
					// adding each child node to HashMap key => value
					map.put(Extra.TAG_KODE, kode);
					map.put(Extra.TAG_JUDUL, judul);
					map.put(Extra.TAG_INTRO, intro);
					map.put(Extra.TAG_GAMBAR, gambar);
					map.put(Extra.TAG_BAHAN, bahan);
					map.put(Extra.TAG_PENYAJIAN, penyajian);

					// adding HashList to ArrayList
					//resepList.add(map);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//
			return null;
		}

		@Override
		protected void onPostExecute(Long result) {
			Log.d("TEST", "onPostExecute");
			// TODO Auto-generated method stub
			//GAMBAR = new String[]{img};
			GAMBAR = gam;
			JUDUL = jud;
			MATERI = itr;
			KODE = kod;
			SOAL = pen;
			RESUME = bah;			
			//super.onPostExecute(result);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			//super.onProgressUpdate(values);
		}
		
	}
}