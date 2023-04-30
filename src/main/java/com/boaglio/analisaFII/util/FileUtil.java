package com.boaglio.analisaFII.util;

import com.boaglio.analisaFII.config.Config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {

    static String RESOURCES = System.getProperty("user.dir") + File.separator + "analises" + File.separator;

    static public void saveFile(String filename, String line) {

        try {
            final Path pathFilename = Paths.get(RESOURCES + File.separator + filename);
            Files.writeString(pathFilename, line + "\n",
                    Files.exists(pathFilename, LinkOption.NOFOLLOW_LINKS) ? StandardOpenOption.APPEND
                            : StandardOpenOption.CREATE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    static public void deleteOldFile() {

        try {
            final Path pathFilename = Paths.get(RESOURCES + File.separator + Config.ARQUIVO_COM_ANALISE);
            Files.deleteIfExists(pathFilename);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
