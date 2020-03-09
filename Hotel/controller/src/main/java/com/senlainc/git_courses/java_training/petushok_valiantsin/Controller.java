package com.senlainc.git_courses.java_training.petushok_valiantsin;

import com.senlainc.git_courses.java_training.petushok_valiantsin.data.LoadData;
import com.senlainc.git_courses.java_training.petushok_valiantsin.logger.CustomLogger;

public class Controller {
    public static void main(String[] args) {
        CustomLogger.execute();
        LoadData.execute();
        new MenuController().run();
    }
}
