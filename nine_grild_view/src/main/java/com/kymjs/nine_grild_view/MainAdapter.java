package com.kymjs.nine_grild_view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.w4lle.library.NineGridAdapter;
import com.w4lle.library.NineGridlayout;

import java.util.List;
public class MainAdapter extends BaseAdapter {
    private Context context;
    private List<List<Image>> datalist;
    private NineGridAdapter adapter;

    public MainAdapter(Context context, List<List<Image>> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        List<Image> itemList = datalist.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_ninegridlayout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivMore = (NineGridlayout) convertView.findViewById(R.id.iv_ngrid_layout);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (itemList.isEmpty() || itemList.isEmpty()) {
            viewHolder.ivMore.setVisibility(View.GONE);
        } else {
            viewHolder.ivMore.setVisibility(View.VISIBLE);
            handlerOneImage(viewHolder, itemList);
        }

        return convertView;
    }

    private void handlerOneImage(ViewHolder viewHolder, final List<Image> image) {
        adapter = new Adapter(context, image);
        viewHolder.ivMore.setAdapter(adapter);
        viewHolder.ivMore.setOnItemClickListerner(new NineGridlayout.OnItemClickListerner() {
            @Override
            public void onItemClick(View view, int position) {
                //do some thing
                L.d("onItemClick : " + position);
//--------------------
                    /**
                     * 这里可以选择三参数(context, 显示第几张图片, 图片路径)
                     * 图片路径可以是单个String，也可以是一个String[]
                     */
                   com.kymjs.gallery.KJGalleryActivity.toGallery(context, "http://www.kymjs.com/image/kymjs.png");
//--------------------

            }
        });
    }


    class ViewHolder {
        public NineGridlayout ivMore;
    }

    class Adapter extends NineGridAdapter {

        public Adapter(Context context, List list) {
            super(context, list);
        }

        @Override
        public int getCount() {
            return (list == null) ? 0 : list.size();
        }

        @Override
        public String getUrl(int position) {
            return getItem(position) == null ? null : ((Image)getItem(position)).getUrl();
        }

        @Override
        public Object getItem(int position) {
            return (list == null) ? null : list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int i, View view) {
            ImageView iv = null;
            if (view != null && view instanceof ImageView) {
                iv = (ImageView) view;
            } else {
                iv = new ImageView(context);
            }
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setBackgroundColor(context.getResources().getColor((android.R.color.transparent)));
            String url = getUrl(i);
            Picasso.with(context).load(getUrl(i)).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).into(iv);
            if (!TextUtils.isEmpty(url)) {
                iv.setTag(url);
            }
            return iv;
        }
    }
}
