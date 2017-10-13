package app.louiemok.uni.starchef.seledefine;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class ImageTools {

    /*
    * 图片大小压缩
    * */
    public static Bitmap compressScale(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        // 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
        if (baos.toByteArray().length / 1024 > 1024) {
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 80, baos);// 这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        Log.i("compressScale", w + "---------------" + h);
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        // float hh = 800f;// 这里设置高度为800f
        // float ww = 480f;// 这里设置宽度为480f
        float hh = 512f;
        float ww = 512f;
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) { // 如果高度高的话根据高度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be; // 设置缩放比例
        // newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565

        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

//        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩

        return bitmap;
    }

    /*
    * 图片质量压缩
    * */
    public static Bitmap compressImage ( Bitmap targetBitmap ) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        targetBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        //质量压缩方法，100表示不压缩，压缩后的数据保存在baos中
        int options = 10;
        targetBitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
//        while ( baos.toByteArray().length / 1024 > 100 ) {
//            //循环判断，如果压缩后的图片大小大于100Kb，继续压缩
//            baos.reset();
//            targetBitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
//            options -= 10;
//        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //把压缩后的数据baos存放到ByteArrayInputStream中
        //把ByteArrayInputStream数据生成图片
        return BitmapFactory.decodeStream(bais, null, null);
    }

    /*
    * Bitmap转base64字符串，用于图片上传
    * */
    public static String bitmapToBase64 ( Bitmap bitmap ) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try{
            if ( bitmap != null ) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes,0, bitmapBytes.length, Base64.DEFAULT);
            }
        }
        catch ( IOException ex ) {
            ex.printStackTrace();
            Log.e("ex", ex.getMessage());
        }
        finally {
            try{
                if ( baos != null ) {
                    baos.flush();
                    baos.close();
                }
            }
            catch ( IOException ex ) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /*
    * base64字符串转Bitmap
    * */
    public static Bitmap base64ToBitmap ( String base64Data ) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
