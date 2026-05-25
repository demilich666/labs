package org.example;

import java.lang.reflect.Method;

class ProcessorStep {
    private final Object target;
    private final Method method;
    private final int order;

    ProcessorStep(Object target, Method method, int order) {
        this.target = target;
        this.method = method;
        this.order = order;
    }

    public Object target() {
        return target;
    }

    public Method method() {
        return method;
    }

    public int order() {
        return order;
    }
}
