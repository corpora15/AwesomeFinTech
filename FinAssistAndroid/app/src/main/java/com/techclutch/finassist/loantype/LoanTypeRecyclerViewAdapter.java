package com.techclutch.finassist.loantype;

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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link LoanTypeFragment.OnListFragmentInteractionListener}.
 */
public class LoanTypeRecyclerViewAdapter extends RecyclerView.Adapter<LoanTypeRecyclerViewAdapter.ViewHolder> {

    private final List<LoanTypeItem> mValues;
    private final LoanTypeFragment.OnListFragmentInteractionListener mListener;
    private Context context;

    public LoanTypeRecyclerViewAdapter(Context context, List<LoanTypeItem> items, LoanTypeFragment.OnListFragmentInteractionListener listener) {
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
        holder.ivLoanImage.setImageDrawable(ContextCompat.getDrawable(context, holder.mItem.getImageResource()));
        holder.tvLoanName.setText(holder.mItem.getName());

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
        @BindView(R.id.iv_loan_image)
        ImageView ivLoanImage;
        @BindView(R.id.tv_loan_name)
        TextView tvLoanName;

        public View mView;
        public LoanTypeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
