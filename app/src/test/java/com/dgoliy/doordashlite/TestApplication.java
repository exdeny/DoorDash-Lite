package com.dgoliy.doordashlite;

import com.dgoliy.doordashlite.dagger.AppModule;
import com.dgoliy.doordashlite.data.dagger.DataModule;

/**
 * Created by dgoliy on 2/11/18.
 */

public class TestApplication extends DDLiteApplication {
    private TestDataModule testDataModule;

    public TestDataModule getTestDataModule() {
        return testDataModule;
    }

    @Override
    protected DataModule createDataModule() {
        return testDataModule = new TestDataModule();
    }

    @Override
    protected AppModule createAppModule() {
        return new TestAppModule(this);
    }
}
