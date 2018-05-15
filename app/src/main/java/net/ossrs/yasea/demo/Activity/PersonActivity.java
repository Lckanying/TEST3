package net.ossrs.yasea.demo.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import net.ossrs.yasea.demo.Activity.View.SelectPicPopupWindow;
import net.ossrs.yasea.demo.R;

import java.io.IOException;

public class PersonActivity extends Activity
{
    private ImageView imageView;
    private SelectPicPopupWindow menuWindow;
    private static final int REQUEST_CODE_PICK_IMAGE = 0;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 1;
    private static final int RESIZE_REQUEST_CODE = 2;
    private static final String TAG = "ChooseImageMainActivity";
    private Uri userImageUri;//保存用户头像的uri
    private Context context = PersonActivity.this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ///设置全屏操作
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.person_layout);


        imageView = (ImageView) findViewById(R.id.choose_image_image);
        imageView.setOnClickListener(new View.OnClickListener() {//给ImageView设置点击监听
            @Override
            public void onClick(View v) {
                menuWindow = new SelectPicPopupWindow(context, new
                        View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menuWindow.dismiss();
                                switch (v.getId()){
                                    case R.id.takePhotoBtn: {
                                        String state = Environment.getExternalStorageState();

                                        if (state.equals(Environment.MEDIA_MOUNTED)) {
                                            Intent getImageByCamera = new
                                                    Intent("android.media.action.IMAGE_CAPTURE");
                                            startActivityForResult(getImageByCamera,
                                                    REQUEST_CODE_CAPTURE_CAMEIA);
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),
                                                    "请确认已经插入SD卡", Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    }
                                    case R.id.pickPhotoBtn:
//                                Intent intent = new Intent(Intent.ACTION_PICK);//从相册中选取图片
                                        Intent intent = new Intent("android.intent.action.GET_CONTENT");//从相册/文件管理中选取图片
                                        intent.setType("image/*");//相片类型
                                        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                                        break;
                                    case R.id.cancelBtn:{
                                        break;
                                    }
                                }
                            }
                        });
                menuWindow.showAtLocation(findViewById(R.id.mainLayout),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri imageUri = null;
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(context,"获取失败",Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(null);//预设的图片
        }else if(resultCode == RESULT_OK) {//选取成功后进行裁剪
            if (requestCode == REQUEST_CODE_PICK_IMAGE) {
                //从图库中选择图片作为头像
                imageUri = data.getData();
                Log.d(TAG, "onActivityResult: " + imageUri);
                reSizeImage(imageUri);
            } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
                //使用相机获取头像
                Log.d(TAG, "onActivityResult: from photo");
                imageUri = data.getData();
                Log.d(TAG, "onActivityResult: " + imageUri);
                if (imageUri == null) {
                    //use bundle to get data
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        Bitmap bitMap = (Bitmap) bundle.get("data"); //get bitmap
                        imageUri = Uri.parse(MediaStore.Images.Media.
                                insertImage(getContentResolver(), bitMap, null,null));
                        Log.d(TAG, "onActivityResult: bndle != null" + imageUri);
                        reSizeImage(imageUri);
                    } else {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }
            }else if(requestCode == RESIZE_REQUEST_CODE){
                Log.d(TAG, "onActivityResult: " + userImageUri);
                showImage(userImageUri);
            }
        }
    }

    private void reSizeImage(Uri uri) {//重新剪裁图片的大小
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");//可以裁剪
        intent.putExtra("aspectX", 1);//宽高比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 100);
        intent.putExtra("outputY", 100);
        /**
         * 此方法返回的图片只能是小图片（测试为高宽160px的图片）
         * 故将图片保存在Uri中，调用时将Uri转换为Bitmap，此方法还可解决miui系统不能return data的问题
         */
//        intent.putExtra("return-data", true);
//        intent.putExtra("output", Uri.fromFile(new File("/mnt/sdcard/temp")));//保存路径
        userImageUri = Uri.parse("file://"+ Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, userImageUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, RESIZE_REQUEST_CODE);
    }

    private void showImage(Uri uri) {
        Log.d(TAG, "showImage: ");
        try {
            Log.d(TAG, "showImage: " + uri.toString());
            SharedPreferences.Editor editor = getSharedPreferences("image",MODE_PRIVATE).edit();
            editor.putString("imageUri",uri.toString());
            editor.commit();//保存头像的uri
            Bitmap photo = MediaStore.Images.Media.getBitmap(context
                    .getContentResolver(), uri);
            imageView.setImageBitmap(photo);

            Toast.makeText(context,"头像设置成功",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(context,"头像设置失败",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
