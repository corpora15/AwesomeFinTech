package com.techclutch.finassist.banktypes;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techclutch.finassist.R;
import com.techclutch.finassist.callbacks.OnBankTypeCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Arman on 7/30/2017.
 */

public class BankTypeAdapter extends RecyclerView.Adapter<BankTypeAdapter.ViewHolder> {

    private Context context;
    private OnBankTypeCallback callback;
    List<BankTypeItem> items;

    public BankTypeAdapter(Context context, List<BankTypeItem> items, OnBankTypeCallback callback) {
        this.context = context;
        this.callback = callback;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_bank_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = items.get(position);
        holder.ivBankLogo.setImageDrawable(ContextCompat.getDrawable(context, holder.mItem.getImageResource()));
        holder.tvBankName.setText(holder.mItem.getName());
        holder.tvInterestRate.setText(holder.mItem.getInterestRate());
        holder.tvMonthlyPayment.setText(holder.mItem.getMonthlyPayment());
        holder.tvTotalPayment.setText(holder.mItem.getTotalPayment());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != callback) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    callback.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_bank_logo)
        ImageView ivBankLogo;
        @BindView(R.id.tv_bank_name)
        TextView tvBankName;
        @BindView(R.id.tv_interest_rate)
        TextView tvInterestRate;
        @BindView(R.id.tv_monthly_payment)
        TextView tvMonthlyPayment;
        @BindView(R.id.tv_total_payment)
        TextView tvTotalPayment;

        public View mView;
        public BankTypeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}
