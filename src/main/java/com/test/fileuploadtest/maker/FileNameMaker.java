package com.test.fileuploadtest.maker;

import java.util.UUID;

public final class FileNameMaker {

    private static final String LAST_DOT = ".";

    public static String getName(String path, String fileName) {
        return path + UUID.randomUUID() + extractExtension(fileName);
    }

    private static String extractExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(LAST_DOT));
        return extension;
    }
}
