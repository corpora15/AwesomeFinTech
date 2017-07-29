package com.techclutch.finassist.uploaddocuments;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techclutch.finassist.R;
import com.techclutch.finassist.dummy.DummyContent.DummyItem;
import com.techclutch.finassist.loantype.LoanTypeFragment;
import com.techclutch.finassist.uploaddocuments.DocumentItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadDocumentsRecyclerViewAdapter extends RecyclerView.Adapter<UploadDocumentsRecyclerViewAdapter.ViewHolder> {

    private final List<DocumentItem> mValues;
    private final LoanTypeFragment.OnListFragmentInteractionListener mListener;
    private Context context;

    public UploadDocumentsRecyclerViewAdapter(Context context, List<DocumentItem> items, UploadDocumentActivity.OnListFragmentInteractionListener listener) {
        this.context = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_loantype, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.ivDocumentImage.setImageDrawable(ContextCompat.getDrawable(context, holder.mItem.getImageResource()));
        holder.tvDocumentName.setText(holder.mItem.getName());
        holder.ivDocumentStatus.setImageDrawable(ContextCompat.getDrawable(context, holder.mItem.getImageResource()));


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_document_image)
        ImageView ivDocumentImage;
        @BindView(R.id.tv_document_name)
        TextView tvDocumentName;
        @BindView(R.id.iv_document_status)
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
