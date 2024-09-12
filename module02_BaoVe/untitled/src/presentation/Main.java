package presentation;

import business.entity.Oders;
import business.entity.RoleName;
import business.entity.User;
import business.feature.impl.OderDetailFeatureImpl;
import business.feature.impl.OdersFeatureImpl;
import business.feature.impl.UserFeatureImpl;
import presentation.Menu.AdminMenu;
import presentation.Menu.UserMenu;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    UserFeatureImpl userFeature = new UserFeatureImpl();


    public static void main(String[] args) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        UserController userController = new UserController();
        ProductController productController = new ProductController();
        boolean isLoop = true;
        do {
            System.out.println("1. đăng ký ");
            System.out.println("2. đăng nhập ");
            System.out.println("3. Hiển thị sản phẩm được bán ");
            System.out.println("4. quên mật khẩu");
            System.out.println("5. thoát");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    main.register(sc);
                    break;
                case 2:
                    main.login(sc);
                    break;
                case 3:
                    productController.showProduct();
                    break;
                    case 4:
                        main.forgotPassword(sc);
                        break;
                case 5:
                    System.exit(1000);
                    break;
            }


        }while (isLoop);
    }


    public void register(Scanner sc) {
        User newUser = new User();
        newUser.inputData(sc);
        userFeature.addOrUpdate(newUser);
    }



    public void login(Scanner sc) {
        User user = new User();
        user.inputLogin(sc);
        // fix cứng tài khoản admin
        if (user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("123456")) {
            AdminMenu.adminMenuController(sc);
        } else {
            user = user.userLogin(user);
            if (user != null) {
                if (user.getRoleName().equals(RoleName.USER)) {
                    User.setCurrentUser(user);
                    UserMenu.userMenuController(sc,user);

                }
            } else {
                System.err.println("email hoặc password sai ");
            }
        }
    }

    public void forgotPassword(Scanner sc) {
        System.out.println("nhập email tài khoản quên mật khẩu ");
        String email = sc.nextLine();

        Optional<User> optionalUser = UserFeatureImpl.userList.stream().filter(user -> user.getEmail().equals(email)).findFirst();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println("passWord của " + email + "là " + user.getPassword());
        } else  {
            System.out.println("email không chính xác : ");
        }
    }
}