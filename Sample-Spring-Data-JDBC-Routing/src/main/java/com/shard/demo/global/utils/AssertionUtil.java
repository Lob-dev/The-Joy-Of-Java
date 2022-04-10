package com.shard.demo.global.utils;

import java.util.Objects;
import java.util.function.Supplier;

public class AssertionUtil {

    public static boolean isNotNull(Object object) {
        return !Objects.isNull(object);
    }

    public static void isNullThrowBy(boolean condition, Supplier<RuntimeException> supplier) {
        if (condition) {
            throw supplier.get();
        }
    }
}
