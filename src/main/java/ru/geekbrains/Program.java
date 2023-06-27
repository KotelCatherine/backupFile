package ru.geekbrains;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Program {

    /**
     * Вызывает функцию copyFile() для создания копирования файлов в другую директорию.
     * Также вызывает функцию print() класса Tree, для отрисовки дерева директории, со всеми поддиректориями и их файлами
     */
    public static void main(String[] args) {
        copyFile();
        Tree.print(new File("."), "  ", true);
    }

    /**
     * Создает массив из файлов в директории
     */
    private static void copyFile() {
        File pathCopyFile = new File("./src");
        File[] files = pathCopyFile.listFiles();

        copyFileInBackupDir(files);
    }

    /**
     * Создает директорию для резервного копирования, если еще не создана. В цикле делает копирование
     * переданных файлов.
     * @param files массив файлов для резервного копирования
     */
    private static void copyFileInBackupDir(File[] files) {
        File backupDir = new File("./backup");
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        try {
            for (File file : files) {
                backupDir = new File("./backup");//нужно пересоздавать, чтобы имена путей не склеивались в одно
                String copyFileName = backupDir + "/";

                if (file.isFile()) {
                    copyFileName += file.getName();
                    backupDir = new File(copyFileName);

                    Files.copy(file.toPath(), backupDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}