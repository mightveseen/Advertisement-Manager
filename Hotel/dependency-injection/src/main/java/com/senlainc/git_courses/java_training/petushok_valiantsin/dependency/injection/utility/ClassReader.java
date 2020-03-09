package com.senlainc.git_courses.java_training.petushok_valiantsin.injection.utility;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassReader {
    private static final Logger LOGGER = Logger.getLogger(ClassReader.class.getName());

    private ClassReader() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Class<?>> getClasses(String pathToJar) {
        final List<Class<?>> classesList = new ArrayList<>();
        try (final JarFile jarFile = new JarFile(pathToJar)) {
            final URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
            final URLClassLoader classLoader = URLClassLoader.newInstance(urls);
            final Enumeration<JarEntry> entriesEnum = jarFile.entries();
            while (entriesEnum.hasMoreElements()) {
                JarEntry je = entriesEnum.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                String className = je.getName().substring(0, je.getName().length() - ".class".length());
                className = className.replace('/', '.');
                classesList.add(classLoader.loadClass(className));
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getMessage(), e);
        }
        return classesList;
    }
}
