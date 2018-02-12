package com.dgoliy.doordashlite.ui;

import android.content.Context;

import com.dgoliy.doordashlite.R;
import com.dgoliy.doordashlite.common.Utils;

/**
 * Created by dgoliy on 2/11/18.
 */

public class UiHelper {
    public static String buildDeliveryFeeString(Context context, int feeInCents) {
        return context.getResources().getString(R.string.details_delivery_fee, Utils.centsToDollars(feeInCents));
    }
}
