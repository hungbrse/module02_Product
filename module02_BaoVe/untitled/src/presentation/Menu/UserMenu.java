package presentation.Menu;

import business.entity.User;
import business.feature.impl.UserFeatureImpl;
import presentation.AddressController;
import presentation.OderController;
import presentation.ProductController;
import presentation.ShopingCartController;

import java.util.Optional;
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
              userMenu.personalInfo(sc);
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

    public void personalInfo(Scanner sc) {
        boolean isLoop = true;
        do {
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    viewPersonalInfo(User.getCurrentUser());
                    break;
                    case 2:
                        updataUser(sc);
                        break;
                        case 3:
                    changePassword(sc);
                    break;
                    case 4:
                        isLoop = false;
                        break;
                default:
                    System.out.println("nhập từ 1 đến 4 ");
                    break;
            }
        }while (isLoop);
    }

    public void viewPersonalInfo(User user) {
        System.out.printf("Thông tin cá nhân: ID: %s, Họ và tên: %s, Email: %s, Tên đăng nhập: %s, Địa chỉ: %s, Số điện thoại: %s, Ngày tạo: %s, Ngày cập nhật: %s, Trạng thái: %s, Vai trò: %s%n",
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getUserName(),
                user.getAddress(),
                user.getPhoneNumber(),
                user.getCreated_at(),
                user.getUpdated_at(),
                (user.getStatus() ? "Hoạt động" : "Không hoạt động"),
                user.getRoleName());
    }
    public void updataUser(Scanner sc) {
        User currentUser = User.getCurrentUser();
                 currentUser.updataUser(sc);
    }
    public void changePassword(Scanner sc) {
        User currentUser = User.getCurrentUser();
        System.out.println("nhập mật khẩu cũ : ");
        String oldPassword = sc.nextLine();
        Optional<User> optionalUser = UserFeatureImpl.userList.stream().filter(user -> user.getPassword().equals(oldPassword)).findFirst();
        if (optionalUser.isPresent()) {
            String newPassword = sc.nextLine();
            optionalUser.get().setPassword(newPassword);
        }
    }

}
