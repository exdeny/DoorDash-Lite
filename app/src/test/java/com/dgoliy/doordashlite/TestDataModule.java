package com.dgoliy.doordashlite;

import com.dgoliy.doordashlite.data.dagger.DataModule;
import com.dgoliy.doordashlite.data.remote.DoorDashAPI;

/**
 * Created by dgoliy on 2/11/18.
 */

public class TestDataModule extends DataModule {
    private DoorDashAPI api;

    public void setApi(DoorDashAPI api) {
        this.api = api;
    }

    @Override
    protected DoorDashAPI createDoorDashAPI() {
        return api == null ? api = super.createDoorDashAPI() : api;
    }
}
