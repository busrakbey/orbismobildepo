package ToolLayer;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by Ömer YILDIRIM on 5.5.2016.
 */
public class FolderManager
{
    public static String baseMapServiceUrl ="";
    public static String orbisServiceUrl="";

    public static String DATA_FOLDER_NAME="orbisdata";

    public static String GEODB_NAME="geodbdata";
    public static String GEODB_FOLDERNAME="geodbdata";
    public static String GEODB_EXTENSION=".geodatabase";

    public static String BASEMAP_EXTENSION=".tpk";
    public static String BASEMAP_FOLDERNAME="basemapdata";
    public static String BASEMAP_NAME= "3045";//"basemaptpk";

    Activity activity;
    public FolderManager(Activity act)
    {
        activity=act;

        File folderBaseMap = new File(getCacheFilePath()+File.separator+BASEMAP_FOLDERNAME);
        boolean success = false;
        if (!folderBaseMap.exists())
        {
            success = folderBaseMap.mkdir();
        }

        File folderGeoDb = new File(getCacheFilePath()+File.separator+GEODB_FOLDERNAME);
        boolean succes = false;
        if (!folderGeoDb.exists())
        {
            succes = folderGeoDb.mkdir();
        }
        if (!folderGeoDb.exists())
        {
            succes = folderGeoDb.mkdir();
        }
    }

    public  boolean hasGeoDbLocal()
    {
        File file = new File(getCacheFilePath()+File.separator+GEODB_FOLDERNAME+File.separator+GEODB_NAME+GEODB_EXTENSION);
        Log.v("file=>",file.getAbsolutePath());
        return file.exists();
    }
    public  boolean hasBasemapLocal()
    {
        File file = new File(getCacheFilePath()+File.separator+BASEMAP_FOLDERNAME+File.separator+BASEMAP_NAME+BASEMAP_EXTENSION);
        return file.exists();
    }
    public Integer removeGeoDbDatas()
    {
        Integer count =0;
        File dir = new File(getGeodbFolder());
        File[] fileDelete = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return (pathname.getName().startsWith(GEODB_NAME));
            }
        });
        // delete all geodatabase files
        for(File file : fileDelete){
            if(!file.delete()){
                Log.e("FOL_MAN", "Can't remove " + file.getAbsolutePath());
            }else
            {
                count++;
            }
        }
        return count;
    }
    public Integer removeBaseMaps()
    {
        Integer count =0;
        File dir = new File(getBaseMapFolder());
        File[] fileDelete = dir.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                return (pathname.getName().endsWith(BASEMAP_EXTENSION));
            }

        });
        // delete all geodatabase files
        for(File file : fileDelete){
            if(!file.delete()){
                Log.e("FOL_MAN", "Can't remove " + file.getAbsolutePath());
            }else
            {
                count++;
            }
        }
        return count;
    }

    public  String getGeodbFilePath()
    {
        return  getCacheFilePath()+File.separator+GEODB_FOLDERNAME+File.separator+GEODB_NAME+GEODB_EXTENSION;
    }
    public  String getGeodbFolder()
    {
        return  getCacheFilePath()+File.separator+GEODB_FOLDERNAME;
    }

    public String getBaseMapFilePath()
    {
        return getCacheFilePath()+File.separator+BASEMAP_FOLDERNAME+File.separator+BASEMAP_NAME+BASEMAP_EXTENSION;
    }
    public String getBaseMapFolder()
    {
        return getCacheFilePath()+File.separator+BASEMAP_FOLDERNAME;
    }

    public  File getApplicationCacheFile()
    {

        Boolean sv=false;
        File fl_ext_storage_download = Environment.getExternalStorageDirectory();

        if (!fl_ext_storage_download.exists())
        {
            fl_ext_storage_download= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            if (!fl_ext_storage_download.exists())
            {
                fl_ext_storage_download=activity.getExternalCacheDir();
                if (!fl_ext_storage_download.exists())
                {
                    fl_ext_storage_download=activity.getCacheDir();
                }
            }
        }
        File fl_storage = new File(fl_ext_storage_download.getPath()+File.separator+DATA_FOLDER_NAME);
        Boolean val_;
        if (!fl_storage.exists())
        {
           val_= fl_storage.mkdir();
        }
        return fl_storage;
    }
    public  String getCacheFilePath()
    {
        String pht =getApplicationCacheFile().getPath();
        return pht;
    }

}
