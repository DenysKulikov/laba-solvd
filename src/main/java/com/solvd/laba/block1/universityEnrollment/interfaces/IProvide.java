package com.solvd.laba.block1.universityEnrollment.interfaces;

@FunctionalInterface
public interface IProvide<T, V> {
    V provide(T t);
}
