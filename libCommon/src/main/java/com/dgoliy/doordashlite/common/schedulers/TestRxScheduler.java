package com.dgoliy.doordashlite.common.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dgoliy on 2/11/18.
 */

public class TestRxScheduler implements RxScheduler {
    @Override
    public Scheduler main() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }
}
