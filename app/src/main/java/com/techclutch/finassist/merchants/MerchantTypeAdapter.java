package com.techclutch.finassist.merchants;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techclutch.finassist.R;
import com.techclutch.finassist.banktypes.BankTypeItem;
import com.techclutch.finassist.callbacks.OnBankTypeCallback;
import com.techclutch.finassist.callbacks.OnMerchantTypeCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ArifH_AW17 on 7/30/2017.
 */


public class MerchantTypeAdapter extends RecyclerView.Adapter<com.techclutch.finassist.merchants.MerchantTypeAdapter.ViewHolder> {

    private Context context;
    private OnMerchantTypeCallback callback;
    List<MerchantTypeItem> items;

    public MerchantTypeAdapter(Context context, List<MerchantTypeItem> items, OnMerchantTypeCallback callback) {
        this.context = context;
        this.callback = callback;
        this.items = items;
    }

    @Override
    public com.techclutch.finassist.merchants.MerchantTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_merchant_type, parent, false);
        return new com.techclutch.finassist.merchants.MerchantTypeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.techclutch.finassist.merchants.MerchantTypeAdapter.ViewHolder holder, int position) {
        holder.mItem = items.get(position);
        holder.ivMerchantLogo.setImageDrawable(ContextCompat.getDrawable(context, holder.mItem.getImageResource()));
        holder.tvMerchantName.setText(holder.mItem.getName());
        holder.tvEligibleLoan.setText(holder.mItem.getEligibleLoan());
        holder.tvTotalRepayment.setText(holder.mItem.getTotalRepayment());
        holder.tvInterestRate.setText(holder.mItem.getInterestRate());
        holder.tvEligibleDiscount.setText(holder.mItem.getEligibleDiscount());

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
        @BindView(R.id.iv_logo)
        ImageView ivMerchantLogo;
        @BindView(R.id.tv_merchant_name)
        TextView tvMerchantName;
        @BindView(R.id.tv_eligible_loan)
        TextView tvEligibleLoan;
        @BindView(R.id.tv_total_repayment)
        TextView tvTotalRepayment;
        @BindView(R.id.tv_interest_rate)
        TextView tvInterestRate;
        @BindView(R.id.tv_eligible_discount)
        TextView tvEligibleDiscount;

        public View mView;
        public MerchantTypeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }
    }
}