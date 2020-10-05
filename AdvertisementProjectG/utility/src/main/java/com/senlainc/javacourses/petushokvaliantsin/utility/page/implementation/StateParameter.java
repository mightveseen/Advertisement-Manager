package com.senlainc.javacourses.petushokvaliantsin.utility.page.implementation;

import com.senlainc.javacourses.petushokvaliantsin.utility.page.IStateParameter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public final class StateParameter implements IStateParameter {

    private final Object advertisementState;
    private final Object paymentState;

    @Override
    public Object getAdvertisementState() {
        return advertisementState;
    }

    @Override
    public Object getPaymentState() {
        return paymentState;
    }
}
