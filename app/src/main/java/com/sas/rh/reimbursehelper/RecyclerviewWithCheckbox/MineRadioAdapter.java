package com.sas.rh.reimbursehelper.RecyclerviewWithCheckbox;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.sas.rh.reimbursehelper.Entity.BaoxiaoContentEntity;
import com.sas.rh.reimbursehelper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by dinghao on 2017/9/6.
 */

public class MineRadioAdapter extends RecyclerView.Adapter<MineRadioAdapter.ViewHolder> {

    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;

    private int secret = 0;
    private String title = "";
    private Context context;
    private List<BaoxiaoContentEntity> mMyLiveList;
    private OnItemClickListener mOnItemClickListener;

    public MineRadioAdapter(Context context) {
        this.context = context;
    }


    public void notifyAdapter(List<BaoxiaoContentEntity> myLiveList, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveList = myLiveList;
        } else {
            this.mMyLiveList.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }

    public List<BaoxiaoContentEntity> getMyLiveList() {
        if (mMyLiveList == null) {
            mMyLiveList = new ArrayList<>();
        }
        return mMyLiveList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bxcontent, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mMyLiveList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BaoxiaoContentEntity myBaoxiaoContentItem = mMyLiveList.get(holder.getAdapterPosition());
        holder.mTvBxtype.setText(myBaoxiaoContentItem.getBxtype());
        holder.mTvBxdate.setText(myBaoxiaoContentItem.getBxdate());
        holder.mTvBxnum.setText(myBaoxiaoContentItem.getBxnum());
        if(myBaoxiaoContentItem.getBxtype().equals("差旅补助")){
            GradientDrawable myGrad = (GradientDrawable)holder.bxitem_icon.getBackground();
            myGrad.setColor(Color.RED);
            holder.bxitem_text.setText("旅");
        }else if(myBaoxiaoContentItem.getBxtype().equals("房租水电")){
            GradientDrawable myGrad = (GradientDrawable)holder.bxitem_icon.getBackground();
            myGrad.setColor(Color.BLUE);
            holder.bxitem_text.setText("房");
        }else if(myBaoxiaoContentItem.getBxtype().equals("通讯费用")){
            GradientDrawable myGrad = (GradientDrawable)holder.bxitem_icon.getBackground();
            myGrad.setColor(Color.CYAN);
            holder.bxitem_text.setText("通");
        }else if(myBaoxiaoContentItem.getBxtype().equals("采购补贴")){
            GradientDrawable myGrad = (GradientDrawable)holder.bxitem_icon.getBackground();
            myGrad.setColor(Color.GREEN);
            holder.bxitem_text.setText("采");
        }

        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);

            if (myBaoxiaoContentItem.isSelect()) {
                holder.mCheckBox.setImageResource(R.mipmap.ic_checked);
            } else {
                holder.mCheckBox.setImageResource(R.mipmap.ic_uncheck);
            }
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), mMyLiveList);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<BaoxiaoContentEntity> myLiveList);
    }
    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
//        @InjectView(R.id.radio_img)
//        ImageView mRadioImg;
        @InjectView(R.id.tv_bxtype)
        TextView mTvBxtype;
        @InjectView(R.id.tv_bxdate)
        TextView mTvBxdate;
        @InjectView(R.id.tv_bxnum)
        TextView mTvBxnum;
        @InjectView(R.id.root_view)
        LinearLayout mRootView;
        @InjectView(R.id.check_box)
        ImageView mCheckBox;
        @InjectView(R.id.bxitem_icon)
        LinearLayout bxitem_icon;
        @InjectView(R.id.bxitem_text)
        TextView bxitem_text;



        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);

        }
    }


}
