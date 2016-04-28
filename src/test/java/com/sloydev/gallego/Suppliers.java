package com.sloydev.gallego;


import com.google.common.base.Objects;

import javax.annotation.Nullable;
import java.io.Serializable;

class Suppliers {

    static <T> Supplier<T> ofInstance(@Nullable T instance) {
        return new SupplierOfInstance<T>(instance);
    }

    private static class SupplierOfInstance<T>
      implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final T instance;

        SupplierOfInstance(@Nullable T instance) {
            this.instance = instance;
        }

        @Override
        public T get() {
            return instance;
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof SupplierOfInstance) {
                SupplierOfInstance<?> that = (SupplierOfInstance<?>) obj;
                return Objects.equal(instance, that.instance);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(instance);
        }

        @Override
        public String toString() {
            return "Suppliers.ofInstance(" + instance + ")";
        }
    }
}
