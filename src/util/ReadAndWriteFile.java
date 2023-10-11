package util;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteFile {
    private static final String PATCH_PRODUCT = "data/products.csv";

    public static void writeFile(List<Product> list) {
        try {
            FileWriter fileWriter = new FileWriter(PATCH_PRODUCT);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String data = "";
            for (Product product : list) {
                data += product.getData() + "\n";
            }
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Product> readFile() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(PATCH_PRODUCT);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                list.add(new Product(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4]));
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
