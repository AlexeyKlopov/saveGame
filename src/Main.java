import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress game1 = new GameProgress(10, 10, 1, 10.1);
        GameProgress game2 = new GameProgress(20, 20, 2, 20.2);
        GameProgress game3 = new GameProgress(30, 30, 3, 30.3);
        saveGame("E://Нетология//Games//savegames//save1.dat", game1);
        saveGame("E://Нетология//Games//savegames//save2.dat", game2);
        saveGame("E://Нетология//Games//savegames//save3.dat", game3);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("E://Нетология//Games//savegames//save1.dat");
        arrayList.add("E://Нетология//Games//savegames//save2.dat");
        arrayList.add("E://Нетология//Games//savegames//save3.dat");
        zipFiles("E://Нетология//Games//savegames//zip.zip", arrayList);
        File save1Dat = new File("E://Нетология//Games//savegames//save1.dat");
        File save2Dat = new File("E://Нетология//Games//savegames//save2.dat");
        File save3Dat = new File("E://Нетология//Games//savegames//save3.dat");
        if (save1Dat.delete()) System.out.println("Файл \"save1.dat\" удален");
        if (save2Dat.delete()) System.out.println("Файл \"save2.dat\" удален");
        if (save3Dat.delete()) System.out.println("Файл \"save3.dat\" удален");
    }

    private static void saveGame(String zip, GameProgress game) {
        try (FileOutputStream fos = new FileOutputStream(zip);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String path, List<String> arrayList) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
            for (String arr : arrayList) {
                try (FileInputStream fis = new FileInputStream(arr)) {
                    ZipEntry entry = new ZipEntry(arr);
                    zout.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zout.write(fis.read());
                    }
                    zout.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
