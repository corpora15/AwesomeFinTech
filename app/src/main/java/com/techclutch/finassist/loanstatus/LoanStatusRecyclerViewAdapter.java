package com.techclutch.finassist.loanstatus;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techclutch.finassist.R;
import com.techclutch.finassist.dummy.DummyContent.DummyItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link LoanStatusFragment.OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class LoanStatusRecyclerViewAdapter extends RecyclerView.Adapter<LoanStatusRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final LoanStatusFragment.OnListFragmentInteractionListener mListener;

    public LoanStatusRecyclerViewAdapter(List<DummyItem> items, LoanStatusFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_loanstatus, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.tvDetails.setText(mValues.get(position).details);

        if(holder.tvDetails.getText().equals("SUCCESS")) {
            holder.tvDetails.setTextColor(Color.GREEN);
        } else if(holder.tvDetails.getText().equals("PENDING")) {
            holder.tvDetails.setTextColor(Color.parseColor("#d5b60a"));
        } else if(holder.tvDetails.getText().equals("FAILED")) {
            holder.tvDetails.setTextColor(Color.RED);
        } else {
            holder.tvDetails.setTextColor(Color.BLACK);
        }

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

        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;
        @BindView(R.id.details)
        TextView tvDetails;
        public final View mView;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
