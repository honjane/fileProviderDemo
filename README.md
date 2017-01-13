# fileProviderDemo
android 7.0访问文件，打开相机功能

解决android 7.0访问相机crash
解决android 7.0通过intent打开文件crash
<br>
详细介绍
http://blog.csdn.net/honjane/article/details/54411820

使用方法：

1.打开文件：

 try {
      FileUtils.startActionFile(this,path,mContentType);
    }catch (ActivityNotFoundException e){

 }

2.调用相机：

 FileUtils.startActionCapture(this, file, requestCode);
