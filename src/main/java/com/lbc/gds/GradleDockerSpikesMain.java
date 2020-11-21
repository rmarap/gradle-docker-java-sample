package com.lbc.gds;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GradleDockerSpikesMain {

    private static final String TARGET_FOLDERS_CONFIG = "config-sample.config";
    private static final List<String> targetFolders = new ArrayList<String>();

    public static void main(String[] args) throws IOException {

        readTargetFoldersConfig();

        if (targetFolders.size() > 0) {
            targetFolders.forEach(targetFolder -> {
                if (StringUtils.isEmpty(targetFolder)) {
                    System.out.println("Empty String");
                } else
                    System.out.println("Valid Target Folder: " + targetFolder);
            });


        }

    }

    private static void readTargetFoldersConfig() throws IOException {

        ClassLoader classLoader = GradleDockerSpikesMain.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(TARGET_FOLDERS_CONFIG);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(";");
                targetFolders.addAll(Arrays.asList(split));
            }


        } catch (IOException e) {
            System.out.println(String.format("Error Reading config File {0}, \n {1}", TARGET_FOLDERS_CONFIG, e));
            e.printStackTrace();
            throw e;
        }
    }

}

//java -classpath $CLASSPATH -jar gradle-docker-sample-1.0-SNAPSHOT.jar
//java -jar gradle-docker-sample-1.0-SNAPSHOT.jar