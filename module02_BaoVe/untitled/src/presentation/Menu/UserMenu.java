package presentation.Menu;

import java.util.Scanner;

public class UserMenu {

    public static void userMenuController(Scanner sc) {
        boolean isLoop = true;
        do {
            System.out.println("1. hiển thị danh sách sản phẩm đang bán :");
            System.out.println("2. xem thông tin cá nhân : ");
            System.out.println("3. Quản lý giỏ hàng :");
            System.out.println("4. Quản lý đơn hàng :");
            System.out.println("5. Quản lý danh sách yêu thích : ");
            System.out.println("6. Đăng xuất : ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:

                    break;
                case 2:


            }
        }while(isLoop);
    }
}
