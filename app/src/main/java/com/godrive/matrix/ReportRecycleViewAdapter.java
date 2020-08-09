package com.godrive.matrix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static com.godrive.matrix.R.layout.recyclerview_item;

public class ReportRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Item> mItems;
    private LayoutInflater mInflater;

    private OnClickListener mClickListener;

    public interface OnClickListener{
        public void setItem(String item);
    }

    public void setClickListener(ReportRecycleViewAdapter.OnClickListener callback) {
        mClickListener = callback;
    }


    public ReportRecycleViewAdapter(Context context, List<Item> items) {
        this.mInflater = LayoutInflater.from(context);
        this.mItems = items;
    }

    /**
     * Step 2: create holder prepare listview to show
     * @param parent the listview
     * @param viewType mView type
     * @return created mView holder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(recyclerview_item, parent, false);
        RecyclerView.ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     * Step 3: render mView holder on screen
     * @param holder mView holder created by onCreateViewHolder
     * @param position corresponding position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.mTextView.setText(mItems.get(position).getDrawable_label());
        viewHolder.mImageView.setImageResource(mItems.get(position).getDrawable_id());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mClickListener != null) {
                    mClickListener.setItem(mItems.get(position).getDrawable_label());
                }
            }
        });
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * Step1 : declare the mView holder structure
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;
        View mView;

        ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mTextView = (TextView) itemView.findViewById(R.id.info_text);
            mImageView = (ImageView) itemView.findViewById(R.id.info_img);
        }
    }
}
