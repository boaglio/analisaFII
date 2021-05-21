package com.boaglio.analisaFII;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {

    static String RESOURCES = System.getProperty("user.dir") + File.separator + "analises" + File.separator;

    static public void saveFile(String fileName, String line) {

        try {
            final Path pathFilename = Paths.get(RESOURCES + File.separator + fileName);
            Files.write(pathFilename, (line + "\n").getBytes(StandardCharsets.UTF_8),
                    Files.exists(pathFilename, LinkOption.NOFOLLOW_LINKS) ? StandardOpenOption.APPEND
                            : StandardOpenOption.CREATE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
