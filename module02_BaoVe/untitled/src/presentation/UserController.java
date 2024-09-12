package presentation;

import business.entity.User;
import business.feature.impl.UserFeatureImpl;

import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class UserController {
    UserFeatureImpl userFeature = new UserFeatureImpl();


 public void userMenu(Scanner scanner) {
     boolean isLoop = true;


     do {
         System.out.println("1. hiển thị người dùng");
         System.out.println("2 .sửa thay đổi trạng thái ");
         System.out.println("3. xóa người dùng ");
         System.out.println("4 .tìm kiếm người dùng ");
         System.out.println("5 .sắp xếp người dùng ");
         System.out.println("6. thoát ");

         int choice = Integer.parseInt(scanner.nextLine());
         switch (choice) {
             case 1:
                 showUser();
                 break;
                 case 2:
                     updataStatusUser(scanner);
                     break;
                     case 3:
                         deleteUser(scanner);
                         break;
                         case 4:
                             findUser(scanner);
                             break;
                             case 5:
                                 sortUser();
                                 break;
                                 case 6:
                                     isLoop = false;
                                     break;
             default:
                 System.out.println("hãy nhập từ 1 đến 6 :");
                 break;
         }


     }while (isLoop);
 }

 public void showUser() {
     UserFeatureImpl.userList.forEach(user -> user.display());
 }
 public void updataStatusUser(Scanner scanner) {
     UserFeatureImpl.userList.forEach(user -> user.display());
     System.out.println("nhập id người dùng muốn thay đổi trạng thái : ");
     int id = Integer.parseInt(scanner.nextLine());

     Optional<User> optionalUser = UserFeatureImpl.userList.stream().filter(user -> user.getUserId() == id).findFirst();
     if (optionalUser.isPresent()) {
         User user = optionalUser.get();
         user.setStatus(!user.getStatus());
     } else {
         System.err.println("id không tồn tại ");
     }
 }

 public void deleteUser(Scanner scanner) {
     System.out.println("nhập user id muốn xóa : ");
     int id = Integer.parseInt(scanner.nextLine());
     Optional<User> optionalUser = UserFeatureImpl.userList.stream().filter(user -> user.getUserId() == id).findFirst();
     if (optionalUser.isPresent()) {
         User user = optionalUser.get();
         userFeature.delete(id);
     }else  {
         System.err.println("id không tồn tại ");
     }

 }

 public void findUser(Scanner scanner) {
     System.out.println("nhập tên người dùng muốn tìm : ");
     String name = scanner.nextLine();

     UserFeatureImpl.userList.stream().filter(user -> user.getUserName().contains(name)).forEach(user -> user.display());
 }

 public void sortUser() {
     Collections.sort(UserFeatureImpl.userList);
     UserFeatureImpl.userList.forEach(user -> user.display());
 }

}
