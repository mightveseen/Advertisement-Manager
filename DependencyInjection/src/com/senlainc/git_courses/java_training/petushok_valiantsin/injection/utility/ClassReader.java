package com.senlainc.git_courses.java_training.petushok_valiantsin.injection.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassReader {

    public static List<Class<?>> getClasses() throws IOException, ClassNotFoundException {
        return find("");
    }

    public static List<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
        return find(packageName);
    }

    private static List<Class<?>> find(String packageName) throws ClassNotFoundException, IOException {
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        final String path = packageName.replace('.', '/');
        final Enumeration<URL> resources = classLoader.getResources(path);
        final List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        final List<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
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
                final String nextPackage = packageName.equals("") ? file.getName() : packageName + "." + file.getName();
                classes.addAll(findClasses(file, nextPackage));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static List<Class<?>> getClassesNames(String pathToJar) {
        List<Class<?>> classesList = new ArrayList<>();
        try {
            JarFile jarFile = new JarFile(pathToJar);
            Enumeration<JarEntry> entriesEnum = jarFile.entries();
            URL[] urls = {new URL("jar:file:" + pathToJar + "!/")};
            URLClassLoader classLoader = URLClassLoader.newInstance(urls);
            while (entriesEnum.hasMoreElements()) {
                JarEntry je = entriesEnum.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) {
                    continue;
                }
                String className = je.getName().substring(0, je.getName().length() - 6);
                className = className.replace('/', '.');
                classesList.add(classLoader.loadClass(className));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classesList;
    }
}
