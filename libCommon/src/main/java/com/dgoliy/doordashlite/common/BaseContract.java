package com.dgoliy.doordashlite.common;

/**
 * Created by dgoliy on 2/11/18.
 */

public interface BaseContract {
    interface BaseView {

    }

    interface BasePresenter<T extends BaseView> {
        void setView(T view);

        void resume();

        void pause();

        void destroy();
    }
}
