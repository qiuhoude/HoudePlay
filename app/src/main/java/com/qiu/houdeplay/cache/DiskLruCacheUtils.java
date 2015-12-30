package com.qiu.houdeplay.cache;

import android.content.Context;
import android.os.Environment;

import com.qiu.houdeplay.tools.IoUtils;
import com.qiu.houdeplay.tools.MiscUtils;
import com.qiu.houdeplay.tools.UiUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 缓存工具类
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @version 创建时间：2014年12月26日 下午4:53:13
 */

public class DiskLruCacheUtils {

    private static int appVersion = MiscUtils.getAppVersionCode(UiUtils.getContext()); //获取版本号

    private static int valueCount = 1;// 同一个key可以对应多少个缓存文件

    private static int maxSize = 10 * 1024 * 1024;// 一个缓存文件最大可以缓存10M

    public static final String CACHE_OBJECT = "object";// 对象缓存目录
    public static final String CACHE_JSON = "json";// json缓存目录

    /**
     * 保存对象缓存
     *
     * @param context
     * @param ser
     * @param key
     */
    public static void saveObject(Context context, Serializable ser, String key) {
        ObjectOutputStream oos = null;
        try {
            DiskLruCache mDiskLruCache = getDiskLurCache(context, CACHE_OBJECT);
            DiskLruCache.Editor editor = getDiskLruCacheOutputStream(mDiskLruCache, key);
            if (editor != null) {
                oos = new ObjectOutputStream(editor.newOutputStream(0));
                oos.writeObject(ser);
                oos.flush();
                editor.commit();
            } else {
                editor.abort();//放弃此次写入
            }
            mDiskLruCache.flush();//必须要加入
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(oos);
        }
    }

    /**
     * 读取对象缓存
     *
     * @param context
     * @param key
     * @return
     */
    public static Serializable readObject(Context context, String key) {
        ObjectInputStream ois = null;
        try {
            DiskLruCache diskLruCache = getDiskLurCache(context, CACHE_OBJECT);
            DiskLruCache.Snapshot snapshot = diskLruCache.get(hashKeyForDisk(key));
            if (snapshot != null) {
                ois = new ObjectInputStream(snapshot.getInputStream(0));
                return (Serializable) ois.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(ois);
        }
        return null;
    }


    public static void saveStr(Context context, String json, String key) {
        OutputStreamWriter osw = null;
        try {
            DiskLruCache mDiskLruCache = getDiskLurCache(context, CACHE_JSON);
            DiskLruCache.Editor editor = getDiskLruCacheOutputStream(mDiskLruCache, key);
            if (editor != null) {
                osw = new OutputStreamWriter(editor.newOutputStream(0), "utf-8");
                osw.write(json);
                osw.flush();
                editor.commit();
            } else {
                editor.abort();//放弃此次写入
            }
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(osw);
        }
    }

    public static String readStr(Context context, String key) {
        InputStreamReader isr = null;
        BufferedReader bf = null;
        StringWriter sw = null;
        try {
            DiskLruCache mDiskLruCache = getDiskLurCache(context, CACHE_JSON);
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(hashKeyForDisk(key));
            if (snapshot != null) {
                isr = new InputStreamReader(snapshot.getInputStream(0), "utf-8");
                bf = new BufferedReader(isr);
                sw = new StringWriter();
                String str;
                while ((str = bf.readLine()) != null) {
                    sw.write(str);
                }
                return sw.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtils.close(isr);
            IoUtils.close(bf);
            IoUtils.close(sw);
        }
        return null;
    }





    /**
     * 获取DiskLruCache的editor
     *
     * @param context
     * @param key
     * @return
     * @throws IOException
     */
    public static DiskLruCache.Editor getDiskLruCacheOutputStream(
            Context context, String uniqueName, String key) throws IOException {
        DiskLruCache mDiskLruCache = getDiskLurCache(context, uniqueName);
        DiskLruCache.Editor editor = mDiskLruCache.edit(hashKeyForDisk(key));
        return editor;
    }

    /**
     * 获取DiskLruCache的editor
     *
     * @param mDiskLruCache
     * @param key
     * @return
     * @throws IOException
     */
    public static DiskLruCache.Editor getDiskLruCacheOutputStream(
            DiskLruCache mDiskLruCache, String key) throws IOException {
        DiskLruCache.Editor editor = mDiskLruCache.edit(hashKeyForDisk(key));
        return editor;
    }


    /**
     * 获取DiskLruCache对象
     *
     * @param context
     * @param uniqueName
     * @return
     * @throws IOException
     */
    private static DiskLruCache getDiskLurCache(Context context, String uniqueName) throws IOException {
        //第一个参数指定的是数据的缓存地址，第二个参数指定当前应用程序的版本号，
        //第三个参数指定同一个key可以对应多少个缓存文件，基本都是传1，第四个参数指定最多可以缓存多少字节的数据。
        return DiskLruCache.open(
                getDiskCacheDir(context, uniqueName), appVersion, valueCount,
                maxSize);
    }





    /**
     * 这个方法会返回当前缓存路径下所有缓存数据的总字节数，以byte为单位
     *
     * @param context
     * @param uniqueName
     * @return
     * @throws IOException
     */
    public long getCacheSize(Context context, String uniqueName) throws IOException {
        return getDiskLurCache(context, uniqueName).size();
    }





    /**
     * 这个方法用于将所有的缓存数据全部删除
     *
     * @param context
     * @param uniqueName
     * @throws IOException
     */
    public void cleanCacheDir(Context context, String uniqueName) throws IOException {
        getDiskLurCache(context, uniqueName).delete();
    }


    /**
     * 获取相应的缓存目录
     * /sdcard/Android/data/<application package>/cache
     * <br/>
     * /data/data/<application package>/cache
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    /**
     * 传入缓存的key值，以得到相应的MD5值
     *
     * @param key
     * @return
     */
    public static String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
