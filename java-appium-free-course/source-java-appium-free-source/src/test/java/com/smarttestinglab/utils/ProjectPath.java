package com.smarttestinglab.utils;

import java.io.File;

/**
 * Utility class to handle project paths
 */
public class ProjectPath {
    
    // Project root directory
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    
    // Apps directory relative to project root
    private static final String APPS_DIR = "apps";
    
    /**
     * Get the absolute path to the apps directory
     * 
     * @return String representing the absolute path to the apps directory
     */
    public static String getAppsPath() {
        return PROJECT_ROOT + File.separator + APPS_DIR;
    }
    
    /**
     * Get the absolute path to an app file in the apps directory
     * 
     * @param appFileName the name of the app file (e.g., "mda-2.2.0-25.apk")
     * @return String representing the absolute path to the app file
     */
    public static String getAppPath(String appFileName) {
        return getAppsPath() + File.separator + appFileName;
    }
}