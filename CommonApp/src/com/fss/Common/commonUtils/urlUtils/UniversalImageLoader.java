package com.fss.Common.commonUtils.urlUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by cym on 14-4-30.
 */
public class UniversalImageLoader {
    public static ImageLoaderConfiguration getDefaultImageLoaderConfiguration(Context context) {
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
//                .discCacheExtraOptions(480, 800, Bitmap.CompressFormat.JPEG, 75, null)
                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .discCache(new UnlimitedDiscCache(cacheDir)) // default
//                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(1000)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(false)) // default
             //   .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .defaultDisplayImageOptions(getDefaultImageOptions())
                .writeDebugLogs()
                .build();
        return config;
    }

    public static DisplayImageOptions getDefaultImageOptions() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .resetViewBeforeLoading(false)  // default
                        //  .delayBeforeLoading(1000)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default
                .displayer(new SimpleBitmapDisplayer()) // default
                .handler(new Handler()) // default
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        return options;
    }
}
