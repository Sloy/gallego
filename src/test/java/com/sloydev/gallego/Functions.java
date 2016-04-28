package com.sloydev.gallego;


import javax.annotation.Nullable;

import static com.chernobyl.Chernobyl.checkNotNull;


class Functions {
    public static <E> Function<E, E> identity() {
        return (Function<E, E>) Functions.IdentityFunction.INSTANCE;
    }

    public static Function<Object, String> toStringFunction() {
        return Functions.ToStringFunction.INSTANCE;
    }

    // enum singleton pattern
    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Override
        @Nullable
        public Object apply(@Nullable Object o) {
            return o;
        }

        @Override
        public String toString() {
            return "identity";
        }
    }

    // enum singleton pattern
    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        @Override
        public String apply(Object o) {
            checkNotNull(o);
            return o.toString();
        }

        @Override
        public String toString() {
            return "toString";
        }
    }
}
