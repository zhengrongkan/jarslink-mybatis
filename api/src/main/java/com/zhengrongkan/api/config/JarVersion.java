package com.zhengrongkan.api.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: ryan
 * @Description: jar version管理jar版本，配置发生变化时跟新版本
 * @Date: Created in 下午1:58 2018/4/13
 * @Modified By:
 */
public class JarVersion {
    /**
     * jar包配置文件名称
     */
    final static String FILE_NAME = "version.config";
    /**
     * 文件最后修改时间
     */
    private volatile long lastModified = 0L;
    /**
     * jar包配置文件
     */
    private File configFile = new File(this.getClass().getResource("/").getPath() + FILE_NAME);
    /**
     * 存放jar包版本信息
     */
    private Map<String, String> versionMap = new HashMap<>();

    private static JarVersion jarVersion = new JarVersion();

    private JarVersion() {
        readVersion();
    }

    public static JarVersion getJarVersion() {
        return jarVersion;
    }

    /**
     * 读取配置文件版本信息
     */
    private void readVersion() {
        if (lastModified != configFile.lastModified()) {
            synchronized (versionMap) {
                if (lastModified == configFile.lastModified()) {
                    return;
                }
                lastModified = configFile.lastModified();
                StringBuilder sb = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
                    String str = null;
                    while ((str = br.readLine()) != null) {
                        sb.append(System.lineSeparator() + str);
                        String[] array = str.split("=");
                        versionMap.put(array[0], array[1]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取jar包版本信息
     *
     * @param key
     * @return
     */
    public String getVersion(String key) {
        readVersion();
        return versionMap.get(key);
    }
}
