package e2e.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Viktoriia1", "Panarina1", "Berlin1"});
        list.add(new Object[]{"Viktoriia2", "Panarina2", "Berlin2"});
        list.add(new Object[]{"Viktoriia3", "Panarina3", "Berlin3"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/9_02_classWork.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");//регулярки
            list.add(new Object[]{split[0], split[1], split[2]});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> changeLastNameAndDescription() { // Бкжнт идти, пока не проверят все записи в методе
        List<Object[]> list = new ArrayList<>(); // переменная не где не использеться и тут пустой масив
        list.add(new Object[]{"Pan", "Best A"});// внизу написано в @TEST
        list.add(new Object[]{"Pan1", "Best AAA"});// внизу написано в @TEST
        list.add(new Object[]{"Pan2", "Best B"});// внизу написано в @TEST
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> editContactInfoWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/hw_csv.csv")));
        String line = reader.readLine();
        while (line != null) { //then we have a rows
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1]});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
