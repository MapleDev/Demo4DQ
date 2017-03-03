package com.xznn.temp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * @author MapleDev
 * @time 16/12/31  15:55
 * @desc ${TODD}
 */
public class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.DiffVh> {
    private List<Bean> mDatas;
    private Context mContext;

    public DiffAdapter(Context context, List<Bean> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public DiffAdapter.DiffVh onCreateViewHolder(ViewGroup parent, int viewType) {

        return new DiffVh(LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(DiffAdapter.DiffVh holder, int position) {
        holder.tvTitle.setText(mDatas.get(position).getId() + "");
        holder.tvDesc.setText(mDatas.get(position).getName());
    }

    @Override
    public void onBindViewHolder(DiffAdapter.DiffVh holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            holder.tvDesc.setText((CharSequence) payloads.get(0));
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public void setData(List<Bean> mDatas) {
        this.mDatas = mDatas;
    }

    class DiffVh extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDesc;

        DiffVh(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
        }
    }
}
