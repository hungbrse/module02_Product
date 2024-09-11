package presentation.Menu;

import presentation.CategoryController;
import presentation.OderController;
import presentation.ProductController;

import java.util.Scanner;

public class AdminMenu {


    public static void adminMenuController(Scanner sc) {
        CategoryController categoryController = new CategoryController();
        ProductController productController = new ProductController();

        boolean isLoop = true;


        do {
            System.out.println("1. Quản lý danh mục ");
            System.out.println("2. Quản lý sản phẩm ");
            System.out.println("3. Quản lý đơn hàng");
            System.out.println("4. Quản lý người dùng ");
            System.out.println("5. Thông kê");
            System.out.println("6 Đăng xuất ");
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        categoryController.menuCate(sc);
                        break;
                        case 2:
                            productController.menuProduct(sc);
                            break;

                    case 6:
                        isLoop = false;
                    default:
                        System.err.println("hãy nhập từ 1 đến 7 !");
                }

            } catch (NumberFormatException e) {
                System.err.println("hãy nhập thực hiện chức năng là 1 số : ");
            }

        } while (isLoop);
    }
}
