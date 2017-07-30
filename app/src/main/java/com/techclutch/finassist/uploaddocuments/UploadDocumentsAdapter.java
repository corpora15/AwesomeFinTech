package com.techclutch.finassist.uploaddocuments;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnDocumentTypeCallback;
import com.techclutch.finassist.loantype.LoanTypeFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadDocumentsAdapter extends RecyclerView.Adapter<UploadDocumentsAdapter.ViewHolder> {

    private final List<DocumentItem> mValues;
    private final OnDocumentTypeCallback mCallback;
    private Context context;

    public UploadDocumentsAdapter(Context context, List<DocumentItem> items, OnDocumentTypeCallback callback) {
        this.context = context;
        mValues = items;
        mCallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_upload_document, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvDocumentName.setText(holder.mItem.getName());
        // todo: handle mouse click
        holder.ivDocumentStatus.setImageDrawable(ContextCompat.getDrawable(context, holder.mItem.getIsCompleted() ?  R.drawable.ic_doc_completed : R.drawable.ic_doc_missing));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mCallback) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mCallback.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_doc_name)
        TextView tvDocumentName;
        @BindView(R.id.iv_doc_status)
        ImageView ivDocumentStatus;

        public View mView;
        public DocumentItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
