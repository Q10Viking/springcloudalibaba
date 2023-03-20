package org.hzz.utils;

import java.io.File;

public class Utils {

    public static String getFtlFilePath() {
        String[] paths = {"src", "main", "resources", "ftl"};
        String path = String.join(File.separator, paths);
        return System.getProperty("user.dir") + File.separator + path;
    }

    public static String getOutputPath() {
        String[] paths = {"src", "main", "resources", "ftlout"};
        String path = String.join(File.separator, paths);
        return System.getProperty("user.dir") + File.separator + path;
    }

    public static String getPojoLocation(String pojoName) {
        return getOutputPath() + File.separator + pojoName + ".java";
    }
}
