package com.sandiplayek.multipartupload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.sandiplayek.mupload.MultiPartUploadData;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    String filePath = "/storage/emulated/0/DCIM/Camera/20190405_170246.jpg";
    String UPLOAD_URL= "http://sudipchatterjee.com/multipartFileUpload/api/uploadFile.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);

        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("FNAME","Sandip");
            jsonObject.put("LNAME","Layek");
        }catch (Exception e){
            e.printStackTrace();
        }

        //UPLOAD ONLY DATA
        MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject);
        multiPartUploadData.uploadFile(URLListing.uploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });

        //UPLOAD ONLY FILE
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,filePath);
        multiPartUploadData.uploadFile(URLListing.fileUpload, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });*/

        //UPLOAD FILE WITH DATA
        /*MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject, filePath);
        multiPartUploadData.uploadFile(URLListing.fileUploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });*/
    }
}
