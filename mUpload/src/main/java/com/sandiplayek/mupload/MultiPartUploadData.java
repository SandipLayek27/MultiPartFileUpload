package com.sandiplayek.mupload;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.sandiplayek.mupload.through.UploadFileWithData;
import com.sandiplayek.mupload.through.UploadOnlyData;
import com.sandiplayek.mupload.through.UploadOnlyFile;

import org.json.JSONException;
import org.json.JSONObject;

public class MultiPartUploadData {
    Context context;
    JSONObject jsonObject;
    String filePath = "";
    String setFlagDesc = "";
    int setFlag = 0;

    public MultiPartUploadData(Context context,  JSONObject jsonObject) {
        this.context = context;
        this.jsonObject = jsonObject;
        setFlagDesc = "INSERT ONLY DATA USING MULTIPART";
        setFlag = 2;
    }

    public MultiPartUploadData(Context context,  String filePath) {
        this.context = context;
        this.filePath = filePath;
        setFlagDesc = "UPLOAD FILE WITH OUT DATA USING MULTIPART";
        setFlag = 3;
    }

    public MultiPartUploadData(Context context, JSONObject jsonObject, String filePath) {
        this.context = context;
        this.jsonObject = jsonObject;
        this.filePath = filePath;
        setFlagDesc = "UPLOAD FILE WITH DATA USING MULTIPART";
        setFlag = 1;
    }

    public void uploadFile(final String url, final OnMultiPartResponseListener onMultiPartResponseListener) {

        class UploadFile extends AsyncTask<Void, Void, String> {

            ProgressDialog uploading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                uploading = ProgressDialog.show(context, setFlagDesc, "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                onMultiPartResponseListener.onPostResponse(s);
                uploading.dismiss();
            }

            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                if(setFlag == 1){
                    UploadFileWithData u = new UploadFileWithData();
                    msg = u.uploadFile(url,filePath,jsonObject);
                }else if(setFlag == 2){
                    UploadOnlyData u = new UploadOnlyData();
                    msg = u.uploadFile(url,jsonObject);
                }else if(setFlag == 3){
                    UploadOnlyFile u = new UploadOnlyFile();
                    msg = u.uploadFile(url,filePath);
                }else{
                    JSONObject jsonObjectResponse = new JSONObject();
                    try {
                        jsonObjectResponse.put("responseCode","204");
                        jsonObjectResponse.put("responseMessage","Could not upload");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    msg = jsonObjectResponse.toString();
                }
                return msg;
            }
        }
        UploadFile uv = new UploadFile();
        uv.execute();
    }

    public interface OnMultiPartResponseListener {
        void onPostResponse(String msg);
    }
}
