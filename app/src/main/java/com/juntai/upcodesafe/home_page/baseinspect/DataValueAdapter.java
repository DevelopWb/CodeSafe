package com.juntai.upcodesafe.home_page.baseinspect;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.juntai.disabled.basecomponent.utils.ToastUtils;
import com.juntai.upcodesafe.R;
import com.juntai.upcodesafe.bean.IdNameBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe: 部门输入下来提示列表
 * Create by zhangzhenlong
 * 2020-10-21
 * email:954101549@qq.com
 */
public class DataValueAdapter extends BaseAdapter implements Filterable {
    private List<IdNameBean.DataBean> allDatas = new ArrayList<>();
    private List<IdNameBean.DataBean> datas = new ArrayList<>();
    private Context mContext;
    private OnNoDataCallBack onNoDataCallBack;
    private ArrayFilter mFilter;

    public DataValueAdapter(Context context, List<IdNameBean.DataBean> allDatas) {
        this.mContext = context;
        this.allDatas = allDatas;
    }


    public void setOnNoDataCallBack(OnNoDataCallBack onNoDataCallBack) {
        this.onNoDataCallBack = onNoDataCallBack;
    }

    public List<IdNameBean.DataBean> getDatas() {
        return datas;
    }

    public void setDatas(List<IdNameBean.DataBean> datas) {
        this.datas = datas;
    }

    public List<IdNameBean.DataBean> getAllDatas() {
        return allDatas;
    }

    public void setAllDatas(List<IdNameBean.DataBean> allDatas) {
        this.allDatas = allDatas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_branch_mark, null);
            holder = new ViewHolder();
            holder.tv_name = (TextView) view.findViewById(R.id.title);
            holder.tv_tag = (TextView) view.findViewById(R.id.tag);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        IdNameBean.DataBean branchBean = datas.get(i);
        holder.tv_name.setText(branchBean.getName());
        holder.tv_tag.setText(branchBean.getName());
        return view;
    }

    static class ViewHolder {
        TextView tv_name;
        TextView tv_tag;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    /**
     * 数据过滤
     */
    private class ArrayFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {//constraint用户输入关键词
            FilterResults results = new FilterResults();
            if (allDatas == null) {
                allDatas = new ArrayList<>();
            }
            ArrayList<IdNameBean.DataBean> newData = new ArrayList<>();
            if (!TextUtils.isEmpty(constraint)) {
                String prefixString = constraint.toString().toLowerCase();
                for (IdNameBean.DataBean testBean : allDatas) {
                    if (testBean.getName().startsWith(prefixString)) {
                        newData.add(testBean);
                    }
                }
            } else {
                return null;
            }
            results.values = newData;
            results.count = newData.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null) {
                datas = (ArrayList) results.values;
                if (datas.isEmpty()) {
                    if (onNoDataCallBack != null) {
                        onNoDataCallBack.noSearchedData();
                        ToastUtils.warning(mContext, "暂无相关行业！");
                    }
                }

            }
            notifyDataSetChanged();
        }

        //给输入框返回的选择结果
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            IdNameBean.DataBean testBean = (IdNameBean.DataBean) resultValue;
            return testBean.getName();
        }
    }

    public interface OnNoDataCallBack {
        void noSearchedData();
    }
}
