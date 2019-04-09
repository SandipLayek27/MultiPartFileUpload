# MultiPartFileUpload
Upload File Only, File Upload with Data Using Using  Multi-part.
It's a compact library which can provide us lot of features. and
We create this library for my working purpose.
## Developed
[![Sandip](https://avatars1.githubusercontent.com/u/31722942?v=4&u=18643bfaaba26114584d27693e9891db26bcb582&s=39) Sandip](https://github.com/SandipLayek27)  
# ★ Gradle Dependency
Add Gradle dependency in the build.gradle file of your application module (app in the most cases) :
First Tab:

```sh
allprojects{
    repositories{
        jcenter()
        maven {
            url 'https://jitpack.io'
        }
    }
}
```

AND

```sh
dependencies {
    implementation 'com.github.SandipLayek27:MultiPartFileUpload:1.0'
}
```

# ★ Features are
1. Upload File Using Multipart System.
2. Upload File With Data Using Multipart System. [Data Type POST PARAMS Types]
3. Upload Data [Data Type POST PARAMS Types]. 
 
# ★ Uses of above features
* 1. Upload File Using Multipart System..
```sh
❆ Set Static Variable.
       private static final int SELECT_FILE = 3;
       private String selectedPath = "";
       
❆ Create JSON Object(Params).
      JSONObject jsonObject = new JSONObject();
      try{
          jsonObject.put("FNAME","Sandip");
          jsonObject.put("LNAME","Layek");
      }catch (Exception e){
          e.printStackTrace();
      }   
      
❆ Choose File From Internel or External Storage.
       private void chooseFile() {
            Intent chooseFile;
            Intent intent;
            chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
            chooseFile.setType("*/*");
            intent = Intent.createChooser(chooseFile, "Choose a file");
            startActivityForResult(intent, SELECT_FILE);
        }   
    
❆ Get result to Activity Result Section
      @Override
      public void onActivityResult(int requestCode, int resultCode, Intent data) {
          if (resultCode == RESULT_OK) {
              if (requestCode == SELECT_FILE) {
                  Uri uri = data.getData();
                  selectedPath =GetRealPath.getPathFromUri(MainActivity.this,uri);  
                  //GetRealPath.getPathFromUri() is Pre-define function which can convert uri to real path
              }
          }
      }
          
```
* 2. Upload File With Data Using Multipart System. [Data Type POST PARAMS Types]..
```sh
❆ 
SET YOUR URL TO URLListing.fileUploadData Static Section.
❆ 
    MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject, filePath);
        multiPartUploadData.uploadFile(URLListing.fileUploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });
        
```
* 3. Upload Data [Data Type POST PARAMS Types]. 
```sh
❆ 
SET YOUR URL TO URLListing.uploadData Static Section.
❆ 
    MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,jsonObject);
        multiPartUploadData.uploadFile(URLListing.uploadData, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });
```
* 4. Upload File. 
```sh
❆ 
SET YOUR URL TO URLListing.fileUpload Static Section.
❆ 
    MultiPartUploadData multiPartUploadData = new MultiPartUploadData(MainActivity.this,filePath);
        multiPartUploadData.uploadFile(URLListing.fileUpload, new MultiPartUploadData.OnMultiPartResponseListener() {
            @Override
            public void onPostResponse(String msg) {
                tv.setText(msg);
            }
        });
```
* 4. PHP SECTION.
```sh
    Hold File From API Section:-
    $fileData = isset($_FILES['myFile'])?$_FILES['myFile']:'';  
    *myFile As a static key part of this case. So, don't change this key part as you want. 
      
    Hold Data From API Section:-
    $myData = isset($_POST['DATA'])?$_POST['DATA']:'';
    * DATA As a Dynamic key part of this case. So, you can change this key part as your requirement. 
```
