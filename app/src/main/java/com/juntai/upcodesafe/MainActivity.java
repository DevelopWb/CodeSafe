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
import android.widget.PopupWindow;
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
import com.juntai.upcodesafe.home_page.baseinspect.BaseInspectionInfoActivity;
import com.juntai.upcodesafe.home_page.inspect.HomePageEnterpriseFragment;
import com.juntai.upcodesafe.home_page.HomePageMornitorFragment;
import com.juntai.upcodesafe.home_page.QRScanActivity;
import com.juntai.upcodesafe.home_page.inspect.selfcheck.UnitInfoActivity;
import com.juntai.upcodesafe.mine.MyCenterFragment;
import com.juntai.upcodesafe.utils.UserInfoManager;

public class MainActivity extends BaseAppActivity<MainPagePresent> implements ViewPager.OnPageChangeListener,
        View.OnClickListener, MainPageContract.IMainPageView {
    private MainPagerAdapter adapter;
    private CustomViewPager mainViewpager;

    private TabLayout mainTablayout;
    private String[] title = new String[]{"首页", "", "我的"};
    private int[] tabDrawables = new int[]{R.drawable.home_index, R.drawable.empty_drawable, R.drawable.home_msg};
    private SparseArray<Fragment> mFragments = new SparseArray<>();
    //
    CGBroadcastReceiver broadcastReceiver = new CGBroadcastReceiver();

    PopupWindow popupWindow;
    private ImageView mAaa;


    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

       int accountType = UserInfoManager.getAccountTypeId();
        if (0==accountType||4==accountType) {
            mFragments.append(0, new HomePageEnterpriseFragment());
        }else {
            mFragments.append(0, new HomePageMornitorFragment());//
        }
        mFragments.append(1, new EmptyFragment());//
        mFragments.append(2, new MyCenterFragment());//
        //
        initToolbarAndStatusBar(false);

        initTab();
        //注册广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ActionConfig.BROAD_LOGIN);
        intentFilter.addAction(ActionConfig.BROAD_CASE_DETAILS);
        registerReceiver(broadcastReceiver, intentFilter);
        mAaa = (ImageView) findViewById(R.id.scan_home_iv);
        mAaa.setOnClickListener(this);
    }

    public void  initHomePageFragment(){
        mFragments.remove(0);
        int accountType = UserInfoManager.getAccountTypeId();
        if (4==accountType) {
            mFragments.append(0, new HomePageEnterpriseFragment());
        }else {
            mFragments.append(0, new HomePageMornitorFragment());//
        }
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
                Intent intent = new Intent();
                int id = 0;
                if (!TextUtils.isEmpty(result) && result.contains("=")) {
                    String str = result.substring(result.lastIndexOf("=") + 1, result.length());
                    intent.putExtra(BaseInspectionInfoActivity.BASE_ID, str);
                    intent.setClass(mContext,UnitInfoActivity.class);
                    startActivity(intent);
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
//        //这个绑定一定在添加tab之前 要不tab自定义布局不生效
//        mainTablayout.setupWithViewPager(mainViewpager);
        mainViewpager.setOffscreenPageLimit(title.length);
        /*viewpager切换监听，包含滑动点击两种*/
        mainViewpager.addOnPageChangeListener(this);

        for (int i = 0; i < title.length; i++) {
            TabLayout.Tab tab = mainTablayout.newTab();
            if (tab != null) {
                //绑定布局
                if (i == title.length - 1) {
                    tab.setCustomView(adapter.getTabView(i, true));
                } else {
                    tab.setCustomView(adapter.getTabView(i, false));
                }
                //设置默认选中项
                if (2== UserInfoManager.getAccountStatus()) {
                    mainTablayout.addTab(tab);
                }else {
                    if (2==i) {
                        mainTablayout.addTab(tab,i,true);
                    }else {
                        mainTablayout.addTab(tab,i,false);
                    }

                }

            }
        }

        mainTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (2== UserInfoManager.getAccountStatus()) {
                    mainViewpager.setCurrentItem(tab.getPosition());
                }else {

                    mainTablayout.getTabAt(2).select();
                    if (1== UserInfoManager.getAccountStatus()) {
                        Toast.makeText(mContext, "资料还未审核通过", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "资料还未完善", Toast.LENGTH_SHORT).show();
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
        if (2== UserInfoManager.getAccountStatus()) {
            mainViewpager.setCurrentItem(0);
        }else {
            mainViewpager.setCurrentItem(2);
        }

    }
    private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag();
            if (2!= UserInfoManager.getAccountStatus()) {
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
        if (0==i) {
            mImmersionBar.reset().statusBarDarkFont(false).init();
        }else {
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
     * 登录被顶
     */
    public class CGBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ActionConfig.BROAD_LOGIN.equals(intent.getAction())) {
                //登录信息设置为空
                String error = intent.getStringExtra("error");
                ToastUtils.info(MyApp.app, error);
                //                SPTools.saveString(mContext, "login", "");
                startActivity(new Intent(mContext, LoginActivity.class));
                //重置界面
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
                .setMessage("请选择退出方式")
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyApp.app.isFinish = true;
                        finish();
                    }
                })
                .setNegativeButton("挂起", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //模拟home键,发送广播
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
