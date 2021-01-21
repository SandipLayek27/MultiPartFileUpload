package com.sandiplayek.multipartupload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sandiplayek.mupload.MultiPartUploadData;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String header = "dasjahdjahdjhdahdjhadhakdhahdahdkhadhkad";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);

        //TESTING PURPOSE JSON OBJECT //////////////////////////////////////////////////////////////////////////
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("FNAME","Sandip");
            jsonObject.put("LNAME","Layek");
        }catch (Exception e){
            e.printStackTrace();
        }

        //TESTING PURPOSE FILE DATA JSON OBJECT FORMAT ////////////////////////////////////////////////////////////////////
        JSONObject jsonObjectFileData = new JSONObject();
        try{
            jsonObjectFileData.put("myFile","/storage/emulated/0/DCIM/Camera/20210121_180443.jpg");
        }catch (Exception e){
            e.printStackTrace();
        }

        //UPLOAD ONLY FILE ///////////////////////////////////////////////////////////////////////////////////////////////////
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObjectFileData,"FILE");
        multiPartUploadData.uploadFile(URLListing.fileUpload, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });*/

        //UPLOAD FILE WITH DATA
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject,jsonObjectFileData);
        multiPartUploadData.uploadFile(URLListing.fileUploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });*/


        //UPLOAD ONLY DATA /////////////////////////////////////////////////////////////////////////////////////////////////
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject);
        multiPartUploadData.uploadFile(URLListing.uploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });*/

        //UPLOAD FILE, DATA WITH HEADER /////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject,jsonObjectFileData,header);
        multiPartUploadData.uploadFile(URLListing.fileUploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });
        */

        //USING LAMBDA EXPRESSION UPLOAD ONLY DATA //////////////////////////////////////////////////////////////////////////
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject);
        multiPartUploadData.uploadFile(URLListing.uploadData,(String msg)->{
            //RESPONSE FETCH FROM msg
        });*/

        //USING LAMBDA EXPRESSION UPLOAD ONLY FILE //////////////////////////////////////////////////////////////////////////
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObjectFileData,"FILE");
        multiPartUploadData.uploadFile(URLListing.fileUpload,(String msg)->{
            //RESPONSE FETCH FROM msg
        });*/

        //USING LAMBDA EXPRESSION UPLOAD FILE WITH DATA /////////////////////////////////////////////////////////////////////
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject,jsonObjectFileData);
        multiPartUploadData.uploadFile(URLListing.fileUploadData,(String msg)->{
            //RESPONSE FETCH FROM msg
        });*/
    }
}
