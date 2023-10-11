package view;

import manager.ProductManager;
import model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {
    ProductManager productManager = new ProductManager();
    Scanner input = new Scanner(System.in);

    public void showMenu() {
        int choice;
        do {
            System.out.println("Quản Lý Sản Phẩm");
            System.out.println("1. Xem danh sách" +
                    "\n2. Thêm mới" +
                    "\n3. Cập nhập" +
                    "\n4. Xoá" +
                    "\n5. Săp Xếp" +
                    "\n6. Tìm Sản phẩm đắt nhất" +
                    "\n9. Thoát");
            choice = Validate.inputChoice();
            switch (choice) {
                case 1:
                    showListProduct(productManager.findAll());
                    break;
                case 2:
                    add();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    sortPrice();
                    break;
                case 6:
                    findMaxPrice();
                    break;
                case 9:
                    System.exit(0);
                default:
                    System.out.println("Nhập sai lựa chọn");
            }
        } while (true);
    }

    public void showListProduct(List<Product> list) {
        if (list.isEmpty()) {
            System.out.println("Không có sản phẩm!");
        } else {
            int count = 0;
            for (Product product : list) {
                System.out.println(product);
                count++;
                if (count == 5) {
                    System.out.println("Nhấn Enter để tiếp tục xem");
                    count = 0;
                    input.nextLine();
                }
            }
        }
        menuUpdate();
    }

    public void menuUpdate() {
        int choice;
        do {
            System.out.println("1. thêm mới" +
                    "\n2. sửa sản phẩm" +
                    "\n3. xoá sản phẩm" +
                    "\n0. Quay lại menu chính");
            choice = Validate.inputChoice();
            switch (choice) {
                case 1:
                    add();
                    break;
                case 2:
                    update();
                    break;
                case 3:
                    delete();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nhập sai lựa chọn");
            }
        } while (choice != 0);

    }

    public void add() {
        int id;
        do {
            id = Validate.inputNumber("Nhập id Sản phẩm: ");
            if (productManager.findIndexById(id) == -1) {
                break;
            }
            System.out.println("Id đã tồn tại!");
        } while (true);
        String name = Validate.inputString("Nhập tên sản phẩm: ");
        int price = Validate.inputNumber("Nhập giá: ");
        int quantity = Validate.inputNumber("Nhập số lương: ");
        String description = Validate.inputString("Mô tả sản phẩm: ");
        productManager.add(new Product(id, name, price, quantity, description));
    }

    public void update() {
        int id = Validate.inputNumber("Nhập id sản phẩm muốn sửa: ");
        if (productManager.findIndexById(id) == -1) {
            System.out.println("Không có sản này phẩm để sửa!");
        } else {
            int newId;
            do {
                newId = Validate.inputNumber("Nhập id mới: ");
                if (productManager.findIndexById(newId) == -1) {
                    break;
                }
                System.out.println("Id đã tồn tại!");
            } while (true);
            String name = Validate.inputString("Nhập tên sản phẩm: ");
            int price = Validate.inputNumber("Nhập giá: ");
            int quantity = Validate.inputNumber("Nhập số lương: ");
            String description = Validate.inputString("Mô tả sản phẩm: ");
            productManager.edit(id, new Product(newId, name, price, quantity, description));
            System.out.println("Đã sửa thành công!");
        }
    }

    public void delete() {
        int id = Validate.inputNumber("Nhập id sản phẩm muốn xoá: ");
        if (productManager.findIndexById(id) == -1) {
            System.out.println("Không có sản phẩm này để xoá!");
        } else {
            do {
                System.out.println("Nhập 1 để xoá, 2 để bỏ qua");
                int choice = Validate.inputChoice();
                if (choice==1) {
                    productManager.delete(id);
                    System.out.println("Đã xoá!");
                    break;
                }else if (choice==2) {
                    System.out.println("bỏ qua");
                    break;
                }else System.out.println("Nhập sai vui lòng nhập lại");
            }while (true);

        }
    }

    public void findMaxPrice() {
        System.out.println("Sản Phẩm có giá đắt nhât là: ");
        if (productManager.findAll().isEmpty()) {
            System.out.println("Không có sản phẩm nào");
        } else {
            System.out.println(productManager.findMaxPrice());
        }
    }

    public void sortPrice() {
        int choice;
        do {
            System.out.println("1. Theo giá tăng dần" +
                    "\n2. Theo giá giảm dần");
            choice = Validate.inputChoice();
            List<Product> list= productManager.findAll();
            if (choice == 1) {
                list.sort((o1, o2) -> {
                    if(o1.getPrice()>o2.getPrice()){
                        return 1;
                    }else if(o1.getPrice()<o2.getPrice()){
                        return -1;
                    }
                    return 0;
                });
                showListProduct(list);
                break;
            } else if (choice == 2) {
                list.sort((o1, o2) -> {
                    if(o1.getPrice()>o2.getPrice()){
                        return -1;
                    }else if(o1.getPrice()<o2.getPrice()){
                        return 1;
                    }
                    return 0;
                });
                showListProduct(list);
                break;
            }
        } while (true);

    }
}
