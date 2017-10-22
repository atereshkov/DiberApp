package com.github.handioq.diberapp.base;

public interface BaseMvp {

    interface Model {

    }

    interface View {

    }

    interface Presenter<V> {

        void setView(V view);

    }
}
