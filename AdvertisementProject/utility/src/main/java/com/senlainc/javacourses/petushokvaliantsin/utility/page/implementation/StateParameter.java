package com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;

public final class StateParameter implements IStateParameter {

    private final Object advertisementState;
    private final Object paymentState;

    private StateParameter(Object advertisementState, Object paymentState) {
        this.advertisementState = advertisementState;
        this.paymentState = paymentState;
    }

    public static StateParameter of(Object advertisementState, Object paymentState) {
        return new StateParameter(advertisementState, paymentState);
    }

    @Override
    public Object getAdvertisementState() {
        return advertisementState;
    }

    @Override
    public Object getPaymentState() {
        return paymentState;
    }
}
