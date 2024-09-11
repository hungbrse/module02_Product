package presentation;

import business.entity.User;
import business.feature.impl.OdersFeatureImpl;

import java.util.Scanner;

public class OderController {

    public void oderMenu(Scanner sc) {

        boolean isLoop = true;

        do {
            System.out.println("1. hiển thị toàn bộ đơn hàng ");
            System.out.println("2. xem chi tiết đơn hàng ");
            System.out.println("3. hủy đơn hàng");
            System.out.println("4. tìm kiếm đơn hàng theo ngày a - b");
            System.out.println("5. tìm kiếm đơn hàng theo trạng thái ");
            System.out.println("6. tìm kiếm mã đơn hàng theo đơn ");
            System.out.println("7. quay lại ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showOder();
                    break;
                    case 2:


            }

        }while (isLoop);

    }
    public void showOder() {
        OdersFeatureImpl.odersList.stream().filter(oder -> oder.getUserId() == User.getCurrentUser().getUserId()).forEach(oders -> oders.display());
    }

    public void showOderdetail() {

    }

}
