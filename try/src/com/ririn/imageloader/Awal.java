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
import android.widget.Button;

import com.nostra13.universalimageloader.utils.L; 
import com.nostra13.example.universalimageloader.R;
import com.ririn.imageloader.Constants.Extra;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class Awal extends BaseActivity {

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.menu);
	 
	        Button go = (Button)findViewById(R.id.buton2);
	 
	        go.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View v) {
	                // TODO Auto-generated method stub
	                Intent intent = new Intent(Awal.this, Dua.class);
	                startActivity(intent);
	            }
	        });
	    }
	}