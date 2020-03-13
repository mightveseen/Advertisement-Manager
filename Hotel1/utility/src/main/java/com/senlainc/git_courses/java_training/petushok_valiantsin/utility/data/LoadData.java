package com.senlainc.git_courses.java_training.petushok_valiantsin.utility.data;

import com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.DependencyController;

public class LoadData {
    private LoadData() {
        throw new IllegalStateException("Utility class");
    }

    public static void execute(Class<?> clazz) {
        DependencyController.getInstance().setDependency(clazz);
    }
}
