package com.sandiplayek.mupload.through;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

public class UploadFileWithData {
    private int serverResponseCode;
    JSONObject jsonObjectResponse = new JSONObject();
    String fileKeyName = "";
    String file = "";
    public String uploadFile(String UPLOAD_URL, JSONObject jsonObject, JSONObject jsonObjectFileData) {
        try{
            Iterator<String> iterator = jsonObjectFileData.keys();
            if(iterator.hasNext()){
                fileKeyName = iterator.next();
                file = jsonObjectFileData.get(fileKeyName).toString();
            }
        }catch (Exception e){e.printStackTrace();}
        String fileName = file;
        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;

        File sourceFile = new File(file);
        if (!sourceFile.isFile()) {
            return null;
        }

        try {
            FileInputStream fileInputStream = new FileInputStream(sourceFile);
            URL url = new URL(UPLOAD_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data");
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty(fileKeyName, fileName);

            dos = new DataOutputStream(conn.getOutputStream());
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\""+fileKeyName+"\";filename=\"" + fileName + "\"" + lineEnd);

            dos.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();

            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            }
            dos.writeBytes("Content-Disposition: form-data; name=\""+"KYE"+"\"" + lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("VALUE");
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);
            // Added To Send Parameters
            Iterator<String> iter = jsonObject.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                Object value = "";
                try {
                    value = jsonObject.get(key);
                    value = URLEncoder.encode((String) value, "UTF-8");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dos.writeBytes("Content-Disposition: form-data; name=\""+key+"\"" + lineEnd);
                dos.writeBytes(lineEnd);
                dos.writeBytes(value.toString());
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + lineEnd);
            }

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            serverResponseCode = conn.getResponseCode();

            fileInputStream.close();
            dos.flush();
            dos.close();
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
}