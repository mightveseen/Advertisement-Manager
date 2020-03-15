package com.senlainc.git_courses.java_training.petushok_valiantsin.dependency.injection.utility;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassReader {

    private static final Logger LOGGER = LogManager.getLogger(ClassReader.class.getName());

    private ClassReader() {
        throw new IllegalStateException("Utility class");
    }

    public static List<Class<?>> getClasses(String pathToJar) {
        final List<Class<?>> classesList = new ArrayList<>();
        try (JarFile jarFile = new JarFile(pathToJar)) {
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
            LOGGER.log(Level.WARN, e.getMessage(), e);
        }
        return classesList;
    }

    public static List<Class<?>> getAllClasses(String packageName) {
        final List<Class<?>> classes = new ArrayList<>();
        try {
            final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
            final String path = packageName.replace('.', '/');
            final Enumeration<URL> resources = classLoader.getResources(path);
            final List<File> dirs = new ArrayList<>();
            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        } catch (ClassNotFoundException | IOException e) {
            LOGGER.log(Level.WARN, "Error while scanning all project", e);
        }
        return classes;
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        final List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        final File[] files = directory.listFiles();
        if (files == null) {
            return classes;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                String nextPackage = packageName.equals("") ? file.getName() : packageName + "." + file.getName();
                classes.addAll(findClasses(file, nextPackage));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
