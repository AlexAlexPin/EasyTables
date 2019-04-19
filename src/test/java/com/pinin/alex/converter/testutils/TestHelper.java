package com.pinin.alex.converter.testutils;

import org.mockito.internal.verification.Times;

public final class TestHelper {

    public static Times once() {
        return new Times(1);
    }
}
