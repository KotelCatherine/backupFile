package ru.geekbrains;

import java.io.File;

public class Tree {
    /**
     * Отрисовка дерева директории, со всеми поддиректориями и их файлами
     * @param file директория/файл
     * @param indent отступ
     * @param isLast последняя директория/файл или нет
     */
    public static void print(File file, String indent, boolean isLast) {
        System.out.print(indent);// рисуем отступ
        if (isLast) {
            System.out.print("└─");
            indent += "  ";
        } else {
            System.out.print("├─");
            indent += "│ ";
        }
        System.out.println(file.getName());

        File[] files = file.listFiles();

        if (files == null) {
            return;
        }

        int subDirTotal = 0;
        int subFileTotal = 0;
        for (File f : files) {
            if (f.isDirectory()) {
                subDirTotal++;
            } else {
                subFileTotal++;
                subFileTotal += subDirTotal;
            }
        }

        int subDirCounter = 0;
        int subFileCounter = 0;
        for (File f : files) {
            if (f.isDirectory()) {
                print(f, indent, subDirCounter == subDirTotal - 1);
                subDirCounter++;
            } else {
                print(f, indent, subFileCounter == subFileTotal - 1);
                subFileCounter++;
            }
        }
    }

}
