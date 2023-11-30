package com.solvd.laba.block1.universityEnrollment.interfaces;

import com.solvd.laba.block1.universityEnrollment.enums.Specialization;

@FunctionalInterface
public interface IProvide<T extends Specialization> {
    T provide();
}
