package com.atom.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atom.domain.Info;
import com.atom.domain.ToolMethod;
import com.facebook.drawee.view.SimpleDraweeView;
import com.test.administrator.atom.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/11.
 */
public class LocalListViewAdapter extends BaseAdapter {
    Context context;
    List infos;

    public LocalListViewAdapter(Context context, List infos) {
        this.context = context;
        this.infos = infos;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        Info info = (Info) infos.get(position);
        ViewHolder holder;
        if (v == null) {
            holder = new ViewHolder();
            v = LayoutInflater.from(context).inflate(R.layout.adapter_local, null);
            holder.iv = (SimpleDraweeView) v.findViewById(R.id.local_image);
            holder.tv1 = (TextView) v.findViewById(R.id.local_name);
            holder.tv2 = (TextView) v.findViewById(R.id.local_time);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (info.getImage() != null) {
            holder.iv.setImageURI(Uri.parse(info.getImage()));
        }
        holder.tv1.setText(info.getName());
        holder.tv2.setText(String.valueOf(ToolMethod.formatTime(info.getTime())));
        return v;
    }

    class ViewHolder {
        TextView tv1;
        TextView tv2;
        SimpleDraweeView iv;
    }


}
