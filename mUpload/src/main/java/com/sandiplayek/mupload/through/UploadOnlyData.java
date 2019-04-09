package com.sandiplayek.mupload.through;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class UploadOnlyData {

    private int serverResponseCode;
    JSONObject jsonObjectResponse = new JSONObject();

    public String uploadFile(String UPLOAD_URL, JSONObject dataJson) {

        HttpURLConnection conn = null;
        try {
            URL url = new URL(UPLOAD_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(dataJson));
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            serverResponseCode = conn.getResponseCode();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (serverResponseCode == 200) {
            StringBuilder sb = new StringBuilder();

            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
            } catch (IOException ioex) {
            }
            return sb.toString();
        }else {
            try {
                jsonObjectResponse.put("responseCode","204");
                jsonObjectResponse.put("responseMessage","Could not upload");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObjectResponse.toString();
        }
    }

    private String getQuery(JSONObject params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        Iterator<String> iter = params.keys();
        while (iter.hasNext()) {
            String key = iter.next();
            if (first)
                first = false;
            else
                result.append("&");
            try {
                result.append(URLEncoder.encode(String.valueOf(key), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(String.valueOf(params.getString(key)), "UTF-8"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}