package net.ossrs.yasea.demo.Activity.Uitls;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;


//with(Context context) - 上下文环境
/// load(String imageUrl) - 需要加载图片的URL.
///  into(ImageView targetImageView) - 图片显示的ImageView.
/// 1.从资源文件中加载 2.加载网络图片 3.从文件中加载图片 4.从URI中加载图片 5.占位图，错误图，和淡入淡出效果
/// //图片下载器

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }

//    @Override
//    public ImageView createImageView(Context context) {
//        //圆角
//        return new RoundAngleImageView(context);
//    }
}
