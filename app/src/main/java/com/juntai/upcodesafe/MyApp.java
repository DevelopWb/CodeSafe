package com.juntai.upcodesafe;


import android.app.Activity;
import android.text.TextUtils;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.juntai.disabled.basecomponent.app.BaseApplication;
import com.juntai.disabled.basecomponent.utils.FileCacheUtils;
import com.juntai.disabled.video.ModuleVideo_Init;
import com.mob.MobSDK;
import com.orhanobut.hawk.Hawk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @aouther Ma
 * @date 2019/3/12
 */
public class MyApp extends BaseApplication {
    public static MyApp app;
    public boolean isFinish = false;
    private String BUGLY_APPID = "77648a6776";//bugly appid警小宝
    public LatLng myLocation;
    public BDLocation bdLocation;
    public static long lastClickTime;//上次点击按钮时间
    public static int timeLimit = 1000;
    private static final String DATA_BASE_NAME = "db_dgjxb";//数据库名称

    public static int BASE_REQUESR = 10086;
    public static int BASE_RESULT = 10087;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Hawk.init(this).build();
        MobSDK.init(this);
        //Video模块初始化
        ModuleVideo_Init.init();
        //百度地图初始化
        SDKInitializer.initialize(this);
        //        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        //创建压缩图片存放目录
        FileCacheUtils.creatFile(FileCacheUtils.getAppImagePath());
//        initBugly();
    }


    /**
     * 获取当前定位
     *
     * @return
     */
    public LatLng getMyLocation() {
        if (myLocation == null) {
            myLocation = new LatLng(0, 0);
        }
        return myLocation;
    }

    public void setMyLocation(LatLng myLocation) {
        this.myLocation = myLocation;
    }


    public BDLocation getBdLocation() {
        return bdLocation;
    }

    public void setBdLocation(BDLocation bdLocation) {
        this.bdLocation = bdLocation;
    }

    @Override
    public void appBackground(boolean isBackground, Activity activity) {
        if (isBackground && !isFinish) {
            //            NitoficationTool.sendNotif(activity,
            //                    11,
            //                    "挂起",
            //                    BaseAppUtils.getAppName() + "服务正在运行",
            //                    R.mipmap.app_icon,
            //                    true,
            //                    new Intent(activity, MainActivity.class));
        } else {
            //变为前台
            //            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //            manager.cancelAll();
        }
    }




    /**
     * 防止暴力点击
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < timeLimit) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}
