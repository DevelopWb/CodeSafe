package com.juntai.upcodesafe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.juntai.disabled.basecomponent.utils.ActionConfig;
import com.juntai.disabled.basecomponent.utils.AppUtils;
import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.disabled.bdmap.service.LocateAndUpload;
import com.juntai.upcodesafe.base.BaseAppActivity;
import com.juntai.upcodesafe.base.customview.CustomViewPager;
import com.juntai.upcodesafe.base.customview.MainPagerAdapter;
import com.juntai.upcodesafe.entrance.EmptyFragment;
import com.juntai.upcodesafe.entrance.LoginActivity;
import com.juntai.upcodesafe.home_page.homepage_fragment.HomePageFragment;
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.upcodesafe.home_page.QRScanActivity;
import com.juntai.upcodesafe.home_page.unit.UnitInfoActivity;
import com.juntai.upcodesafe.mine.MyCenterFragment;
import com.juntai.upcodesafe.utils.UserInfoManager;

public class MainActivity extends BaseAppActivity<MainPagePresent> implements ViewPager.OnPageChangeListener,
        View.OnClickListener, MainPageContract.IMainPageView {
    private MainPagerAdapter adapter;
    private CustomViewPager mainViewpager;

    private TabLayout mainTablayout;
    private String[] title = new String[]{"้ฆ้กต", "", "ๆ็"};
    private int[] tabDrawables = new int[]{R.drawable.home_index, R.drawable.empty_drawable, R.drawable.home_msg};
    private SparseArray<Fragment> mFragments = new SparseArray<>();
    //
    CGBroadcastReceiver broadcastReceiver = new CGBroadcastReceiver();

    private ImageView mAaa;


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        mFragments.append(0, new HomePageFragment());
        mFragments.append(1, new EmptyFragment());//
        mFragments.append(2, new MyCenterFragment());//
        //
        initToolbarAndStatusBar(false);

        initTab();
        //ๆณจๅๅนฟๆญ
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionConfig.BROAD_LOGIN);
        intentFilter.addAction(ActionConfig.BROAD_CASE_DETAILS);
        registerReceiver(broadcastReceiver, intentFilter);
        mAaa = (ImageView) findViewById(R.id.scan_home_iv);
        mAaa.setOnClickListener(this);
    }


    @Override
    public void initData() {
        update(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == AppUtils.QR_SCAN_NOMAL && resultCode == RESULT_OK) {
            if (data != null) {
                String result = data.getStringExtra("result");
                int id = 0;
                if (!TextUtils.isEmpty(result) && result.contains("=")) {
                    String str = result.substring(result.lastIndexOf("=") + 1, result.length());
                    startActivity(new Intent(mContext, UnitInfoActivity.class)
                            .putExtra(BaseInspectionInfoActivity.BASE_STRING, BaseInspectionInfoActivity.BASE_STRING_VALUE3)
                            .putExtra(BaseInspectionInfoActivity.BASE_ID, str));

                }

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void initTab() {
        mainViewpager = findViewById(R.id.main_viewpager);
        mainTablayout = findViewById(R.id.main_tablayout);
        mainViewpager.setScanScroll(false);
        mainViewpager.setOffscreenPageLimit(3);
        adapter = new MainPagerAdapter(getSupportFragmentManager(), getApplicationContext(), title, tabDrawables,
                mFragments);
        mainViewpager.setAdapter(adapter);
//        //่ฟไธช็ปๅฎไธๅฎๅจๆทปๅ?tabไนๅ ่ฆไธtab่ชๅฎไนๅธๅฑไธ็ๆ
//        mainTablayout.setupWithViewPager(mainViewpager);
        mainViewpager.setOffscreenPageLimit(title.length);
        /*viewpagerๅๆข็ๅฌ๏ผๅๅซๆปๅจ็นๅปไธค็ง*/
        mainViewpager.addOnPageChangeListener(this);

        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = mainTablayout.newTab();
            if (tab != null) {
                //็ปๅฎๅธๅฑ
                if (i == title.length - 1) {
                    tab.setCustomView(adapter.getTabView(i, true));
                } else {
                    tab.setCustomView(adapter.getTabView(i, false));
                }
                //่ฎพ็ฝฎ้ป่ฎค้ไธญ้กน
                if (2 == UserInfoManager.getAccountStatus()) {
                    mainTablayout.addTab(tab);
                } else {
                    if (2 == i) {
                        mainTablayout.addTab(tab, i, true);
                    } else {
                        mainTablayout.addTab(tab, i, false);
                    }

                }

            }
        }

        mainTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (2 == UserInfoManager.getAccountStatus()) {
                    mainViewpager.setCurrentItem(tab.getPosition());
                } else {

                    mainTablayout.getTabAt(2).select();
                    if (1 == UserInfoManager.getAccountStatus()) {
                        Toast.makeText(mContext, "่ตๆ่ฟๆชๅฎกๆ?ธ้่ฟ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mContext, "่ตๆ่ฟๆชๅฎๅ", Toast.LENGTH_SHORT).show();
                    }
                    mainViewpager.setCurrentItem(2);
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if (2 == UserInfoManager.getAccountStatus()) {
            mainViewpager.setCurrentItem(0);
        } else {
            mainViewpager.setCurrentItem(2);
        }

    }

    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            if (2 != UserInfoManager.getAccountStatus()) {
                if (pos == 0) {


                } else {
                    TabLayout.Tab tab = mainTablayout.getTabAt(pos);
                    if (tab != null) {
                        tab.select();
                    }
                }
            }

        }
    };

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        if (0 == i) {
            mImmersionBar.reset().statusBarDarkFont(false).init();
        } else {
            mImmersionBar.reset().statusBarDarkFont(true).init();

        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onSuccess(String tag, Object o) {
        switch (tag) {
            default:
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_home_iv:
                startActivityForResult(new Intent(mContext,
                        QRScanActivity.class), AppUtils.QR_SCAN_NOMAL);
                break;
            default:
                break;
        }
    }


    /**
     * ็ปๅฝ่ขซ้กถ
     */
    public class CGBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ActionConfig.BROAD_LOGIN.equals(intent.getAction())) {
                //็ปๅฝไฟกๆฏ่ฎพ็ฝฎไธบ็ฉบ
                String error = intent.getStringExtra("error");
                ToastUtils.info(MyApp.app, error);
                //                SPTools.saveString(mContext, "login", "");
                startActivity(new Intent(mContext, LoginActivity.class));
                //้็ฝฎ็้ข
                //                EventManager.sendStringMsg(ActionConfig.UN_READ_MESSAG_TAG);
            }
        }
    }

    @Override
    protected MainPagePresent createPresenter() {
        return new MainPagePresent();
    }

    @Override
    public void onLocationReceived(BDLocation bdLocation) {

    }

    @Override
    public boolean requestLocation() {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.e("EEEEEEEEEE", " = MainActivity  onDestroy");
        stopService(new Intent(MainActivity.this, LocateAndUpload.class));
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext)
                .setMessage("่ฏท้ๆฉ้ๅบๆนๅผ")
                .setPositiveButton("้ๅบ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.app.isFinish = true;
                        finish();
                    }
                })
                .setNegativeButton("ๆ่ตท", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ๆจกๆhome้ฎ,ๅ้ๅนฟๆญ
                        //sendBroadcast(new Intent().setAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                        // .putExtra("reason","homekey"));
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        startActivity(intent);
                    }
                }).show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}
