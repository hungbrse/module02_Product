package presentation.Menu;

import business.entity.User;
import presentation.AddressController;
import presentation.OderController;
import presentation.ProductController;
import presentation.ShopingCartController;

import java.util.Scanner;

public class UserMenu {

    public static void userMenuController(Scanner sc, User currentUser) {
        UserMenu userMenu = new UserMenu();
        ProductController productController = new ProductController();
        ShopingCartController shopingCartController = new ShopingCartController();
        AddressController addressController = new AddressController();
        OderController oderController = new OderController();
        boolean isLoop = true;
        do {
            System.out.println("1. hiển thị danh sách sản phẩm đang bán :");
            System.out.println("2. xem thông tin cá nhân : ");
            System.out.println("3. Quản lý giỏ hàng :");
            System.out.println("4. Quản lý đơn hàng :");
            System.out.println("5. Quản lý danh sách yêu thích : ");
            System.out.println("6. thêm địa chỉ Oder ");
            System.out.println("7. Đăng xuất : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
               productController.showProduct();
                    break;
                case 2:
              userMenu.viewPersonalInfo(currentUser);
              break;
              case 3:
                  shopingCartController.shopingCartMenu();
                  break;
                case 4:
                    oderController.oderMenu(sc);
                    break;

                case 6:
                    addressController.adrressMenu(sc);
                    break;
                case  7 :
                    isLoop = false;
                    break;
            }
        }while(isLoop);
    }

    public void viewPersonalInfo(User user) {
        System.out.println("Thông tin cá nhân:");
        System.out.println("ID: " + user.getUserId());
        System.out.println("Họ và tên: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Tên đăng nhập: " + user.getUserName());
        System.out.println("Địa chỉ: " + user.getAddress());
        System.out.println("Số điện thoại: " + user.getPhoneNumber());
        System.out.println("Ngày tạo: " + user.getCreated_at());
        System.out.println("Ngày cập nhật: " + user.getUpdated_at());
        System.out.println("Trạng thái: " + (user.getStatus() ? "Hoạt động" : "Không hoạt động"));
        System.out.println("Vai trò: " + user.getRoleName());
    }

}
