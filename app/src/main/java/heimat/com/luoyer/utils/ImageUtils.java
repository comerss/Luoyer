package heimat.com.luoyer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by yukuoyuan on 2017/3/27.
 */

public class ImageUtils {

    public ImageUtils() {
    }
    /**
     * 这是一个保存bitmap为一个文件的方法
     *
     * @param bitmap
     * @return
     */
    public static File saveBitmapFile(Bitmap bitmap) {
        File file = new File("/mnt/sdcard/" + System.currentTimeMillis()
                + ".jpg");// 将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static Bitmap decodeScaleImage(String path, int width, int height) {
        BitmapFactory.Options options = getBitmapOptions(path);
        int var4 = calculateInSampleSize(options, width, height);
        options.inSampleSize = var4;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        int degree = readPictureDegree(path);
        Bitmap result = null;
        if (bitmap != null && degree != 0) {
            result = rotaingImageView(degree, bitmap);
            bitmap.recycle();
            bitmap = null;
            return result;
        } else {
            return bitmap;
        }
    }

    public static Bitmap decodeScaleImage(Context context, int id, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(context.getResources(), id, options);
        int var6 = calculateInSampleSize(options, width, height);
        options.inSampleSize = var6;
        options.inJustDecodeBounds = false;
        Bitmap var4 = BitmapFactory.decodeResource(context.getResources(), id, options);
        return var4;
    }

    public static int calculateInSampleSize(BitmapFactory.Options option, int width, int height) {
        int outHeight = option.outHeight;
        int outWidth = option.outWidth;
        int var5 = 1;
        if (outHeight > height || outWidth > width) {
            int var6 = Math.round((float) outHeight / (float) height);
            int var7 = Math.round((float) outWidth / (float) width);
            var5 = var6 > var7 ? var6 : var7;
        }

        return var5;
    }

    public static String getScaledImage(Context context, String path, int var2) {
        File file = new File(path);
        if (file.exists()) {
            long length = file.length();
            if (length > 102400L) {
                Bitmap bitmap = decodeScaleImage(path, 640, 960);

                try {
                    File result = new File(context.getExternalCacheDir(), "eaemobTemp" + var2 + ".jpg");
                    FileOutputStream outputStream = new FileOutputStream(result);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream);
                    outputStream.close();
                    return result.getAbsolutePath();
                } catch (Exception var9) {
                    var9.printStackTrace();
                }
            }
        }

        return path;
    }

    public static int readPictureDegree(String path) {
        short degree = 0;

        try {
            ExifInterface var2 = new ExifInterface(path);
            int var3 = var2.getAttributeInt("Orientation", 1);
            switch (var3) {
                case 3:
                    degree = 180;
                case 4:
                case 5:
                case 7:
                default:
                    break;
                case 6:
                    degree = 90;
                    break;
                case 8:
                    degree = 270;
            }
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        return degree;
    }

    public static Bitmap rotaingImageView(int degree, Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.postRotate((float) degree);
        Bitmap result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return result;
    }

    public static BitmapFactory.Options getBitmapOptions(String pathName) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        return options;
    }
}
