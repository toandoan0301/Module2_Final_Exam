package manager;

import model.Product;
import util.ReadAndWriteFile;
import java.util.List;

public class ProductManager implements IManager<Product> {
    private List<Product> list;

    public ProductManager() {
        this.list = ReadAndWriteFile.readFile();
    }

    @Override
    public void add(Product product) {
        list.add(product);
        ReadAndWriteFile.writeFile(list);
    }

    @Override
    public void edit(int id, Product product) {
        list.set(findIndexById(id), product);
        ReadAndWriteFile.writeFile(list);
    }

    @Override
    public void delete(int id) {
        list.remove(findIndexById(id));
        ReadAndWriteFile.writeFile(list);
    }

    public int findIndexById(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Product findMaxPrice() {
        Product result = list.get(0);
        for (Product product : list) {
            if (product.getPrice() > result.getPrice()) {
                result = product;
            }
        }
        return result;
    }

    @Override
    public List<Product> findAll() {
        return list;
    }
}
