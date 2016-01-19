package ru.zr;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        File pluginDir = new File("plugins");
        if(pluginDir != null) {
            File[] jars = pluginDir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isFile() && file.getName().endsWith(".jar");
                }
            });

            if(jars != null) {
                Class[] pluginClasses = new Class[jars.length];
                for (int i = 0; i < jars.length; i++) {
                    try {
                        URL jarURL = jars[i].toURI().toURL();
                        URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL});
                        pluginClasses[i] = classLoader.loadClass("ru.zr.pluginOne");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                for (Class clazz : pluginClasses) {
                    try {
                        IPlugin instance = (IPlugin) clazz.newInstance();
                        instance.execute();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
