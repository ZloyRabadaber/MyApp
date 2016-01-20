package ru.zr;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<IPlugin> plugins = PluginFactory.getPlugins();
        for (IPlugin p : plugins){
            p.execute();
        }
    }
}
