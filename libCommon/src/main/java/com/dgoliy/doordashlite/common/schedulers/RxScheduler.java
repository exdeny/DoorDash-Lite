package com.dgoliy.doordashlite.common.schedulers;

import io.reactivex.Scheduler;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface RxScheduler {
    Scheduler main();

    Scheduler io();

    Scheduler computation();
}
