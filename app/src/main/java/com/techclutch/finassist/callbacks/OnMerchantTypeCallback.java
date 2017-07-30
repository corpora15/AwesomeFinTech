package com.techclutch.finassist.callbacks;

import com.techclutch.finassist.banktypes.BankTypeItem;
import com.techclutch.finassist.merchants.MerchantTypeItem;

/**
 * Created by ArifH_AW17 on 7/30/2017.
 */

public interface OnMerchantTypeCallback {
    void onListFragmentInteraction(MerchantTypeItem item);
}
