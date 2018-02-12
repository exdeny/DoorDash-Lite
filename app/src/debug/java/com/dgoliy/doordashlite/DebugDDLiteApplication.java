package com.dgoliy.doordashlite;

import android.os.StrictMode;

import com.dgoliy.doordashlite.common.DDLog;
import com.dgoliy.doordashlite.data.dagger.DataModule;
import com.dgoliy.doordashlite.data.dagger.DebugDataModule;

/**
 * Created by dgoliy on 2/11/18.
 */

public class DebugDDLiteApplication extends DDLiteApplication {
    private final String TAG = "DebugApplication";

    @Override
    public void onCreate() {
        DDLog.enable();
        super.onCreate();
        initStrictMode();
    }

    private void initStrictMode() {
        DDLog.d(TAG, "initStrictMode()");
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
    }

    @Override
    protected DataModule createDataModule() {
        return new DebugDataModule();
    }
}
