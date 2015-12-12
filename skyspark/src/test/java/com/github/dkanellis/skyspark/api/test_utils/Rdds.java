package com.github.dkanellis.skyspark.api.test_utils;

import org.apache.spark.api.java.JavaRDD;

import java.util.List;

public final class Rdds {

    private Rdds() {
        throw new AssertionError("No instances.");
    }

    public static <T> boolean areEqual(JavaRDD<T> expected, JavaRDD<T> actual) {
        List<T> difference = expected.subtract(actual).collect();

        return difference.isEmpty();
    }
}
