package presentation;

import business.entity.*;
import business.feature.impl.OderDetailFeatureImpl;
import business.feature.impl.OdersFeatureImpl;
import business.feature.impl.ProductFeatureImpl;
import business.untils.IOFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
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
                        showOderdetail(sc);
                        break;
                        case 3:
                   cancelOder(sc);
                   break;
                   case 4:
                       findOrderByDateRange(sc);
                       break;
                       case 5:
                           findOderbyStatus(sc);
                           break;
                           case 6:
                               findOderbySerialNumber(sc);
                   break;

                case 7:
                    isLoop = false;
                    break;
                default:
                    System.err.println("hãy nhập từ 1 đến 7 : ");


            }

        }while (isLoop);

    }
    public void showOder() {
        OdersFeatureImpl.odersList.stream().filter(oder -> oder.getUserId() == User.getCurrentUser().getUserId()).forEach(oders -> oders.display());
    }

    public void showOderdetail(Scanner sc) {
        OdersFeatureImpl.odersList.stream().filter(oder -> oder.getUserId() == User.getCurrentUser().getUserId()).forEach(oders -> oders.display());
        System.out.println("nhập oderId muốn xem chi tiết ");
        int id = Integer.parseInt(sc.nextLine());
//        OderDetailFeatureImpl.oderDetailList.stream().filter(oderDetail -> oderDetail.getOrderId() == id).forEach(oderDetail -> oderDetail.display());
        OderDetailFeatureImpl.oderDetailList.stream().filter(item -> item.getOrderId()==id).forEach(OderDetail::display);

    }

    public void cancelOder(Scanner sc) {
        OdersFeatureImpl.odersList.stream()
                .filter(oder -> oder.getUserId() == User.getCurrentUser().getUserId())
                .forEach(Oders::display);

        System.out.println("nhập id đơn hàng muốn hủy : ");
        int id = Integer.parseInt(sc.nextLine());

        Optional<Oders> optionalOders = OdersFeatureImpl.odersList.stream()
                .filter(oder -> oder.getOderId() == id)
                .findFirst();

        if (optionalOders.isPresent()) {
            Oders orderToCancel = optionalOders.get();

            orderToCancel.setOrderStatus(OderStatus.CANCEL);

            OderDetailFeatureImpl.oderDetailList.stream()
                    .filter(oderDetail -> oderDetail.getOrderId() == id)
                    .forEach(oderDetail -> {
                        Optional<Product> productOptional = ProductFeatureImpl.productList.stream()
                                .filter(product -> product.getProductId() == oderDetail.getProductId())
                                .findFirst();

                        if (productOptional.isPresent()) {
                            Product product = productOptional.get();
                            product.setStockQuantity(product.getStockQuantity() + oderDetail.getProductQuantity());
                            IOFile.writeObjectToFile(ProductFeatureImpl.productList,IOFile.PATH_PRODUCTS);
                        }
                    });

            System.out.println("Đơn hàng đã được hủy và số lượng sản phẩm đã được trả lại kho.");
        } else {
            System.out.println("id không tồn tại : ");
        }

    }

    public void findOderbyStatus(Scanner sc) {
        System.out.println("nhập trạng thái đơn hàng : ");
        String status = sc.nextLine().toUpperCase();
        OderStatus oderStatus = OderStatus.valueOf(status);
        OdersFeatureImpl.odersList.stream().filter(oders -> oders.getOrderStatus().equals(oderStatus) && oders.getUserId() == User.getCurrentUser().getUserId()).forEach(oders -> oders.display());
    }

    public void findOderbySerialNumber(Scanner sc) {
        System.out.println("nhập mã đơn hàng : ");
        int id = Integer.parseInt(sc.nextLine());
        OdersFeatureImpl.odersList.stream().filter(oders -> oders.getOderId() == id &&oders.getUserId() == User.getCurrentUser().getUserId()).forEach(oders -> oders.display());
    }

    public void findOrderByDateRange(Scanner sc) {
        System.out.println("Nhập ngày bắt đầu (dd-MM-yyyy): ");
        String startDateStr = sc.nextLine();
        System.out.println("Nhập ngày kết thúc (dd-MM-yyyy): ");
        String endDateStr = sc.nextLine();

        // Chuyển đổi chuỗi ngày thành đối tượng Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);

            // Lọc và hiển thị các đơn hàng trong khoảng ngày đã chỉ định
            OdersFeatureImpl.odersList.stream()
                    .filter(oders -> !oders.getCreateDate().before(startDate) && !oders.getCreateDate().after(endDate))
                    .forEach(Oders::display);
        } catch (ParseException e) {
            System.err.println("Định dạng ngày không hợp lệ, hãy nhập lại theo định dạng dd-MM-yyyy.");
        }
    }

    public void adminOderMenu(Scanner sc) {
        boolean isLoop = true;
        do {
            System.out.println("1. hiển thị danh sách đơn hàng");
            System.out.println("2. tìm kiếm theo mã đơn hàng ");
            System.out.println("3. tìm kiếm theo trạng thái đơn hàng ");
            System.out.println("4. xem chi tiết theo mã đơn hàng");
            System.out.println("5. thay đổi trạng thái đơn hàng");
            System.out.println("6. thoát ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showOderbyAdmin();
                    break;
                case 2:
                    findOderByIdOfAdmin(sc);
                    break;
                    case 3:
                        findStatusbyAdmin(sc);
                        break;
                        case 4:
                            showOderDetailByAdmin(sc);
                            break;
                            case 5:
                                changeStatus(sc);
                                break;
                case 6 :
                    isLoop = false;
                    break;
                default:
                    System.out.println("hãy nhập từ 1 đến 6 ");
break;
            }
        }while (isLoop);

    }

    public void showOderbyAdmin() {
        OdersFeatureImpl.odersList.forEach(oders -> oders.display());
    }

    public void findOderByIdOfAdmin(Scanner sc) {
        System.out.println("nhập mã đơn hàng : ");
        int id = Integer.parseInt(sc.nextLine());
        OdersFeatureImpl.odersList.stream().filter(oders -> oders.getOderId() == id).forEach(oders -> oders.display());
    }

    public void findStatusbyAdmin(Scanner sc) {
        System.out.println("nhập trạng thái đơn hàng : ");
        String status = sc.nextLine().toUpperCase();
        OderStatus oderStatus = OderStatus.valueOf(status);
        OdersFeatureImpl.odersList.stream().filter(oders -> oders.getOrderStatus().equals(oderStatus) ).forEach(oders -> oders.display());
    }


    public void changeStatus(Scanner sc) {
        showOderbyAdmin();

        System.out.println("Nhập mã đơn hàng cần thay đổi trạng thái: ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Oders> optionalOders = OdersFeatureImpl.odersList.stream()
                .filter(oders -> oders.getOderId() == id).findFirst();
        if (optionalOders.isPresent()) {
            Oders orderToChange = optionalOders.get();
            System.out.println("Trạng thái hiện tại: " + orderToChange.getOrderStatus());
            System.out.println("Danh sách các trạng thái có thể chọn:");
            for (OderStatus status : OderStatus.values()) {
                System.out.println("- " + status);
            }
            System.out.println("Nhập trạng thái mới: ");
            String newStatusStr = sc.nextLine().toUpperCase();
            try {
                OderStatus newStatus = OderStatus.valueOf(newStatusStr);

                if (newStatus == OderStatus.CANCEL) {
                    OderDetailFeatureImpl.oderDetailList.stream()
                            .filter(oderDetail -> oderDetail.getOrderId() == orderToChange.getOderId())
                            .forEach(oderDetail -> {
                                Optional<Product> productOptional = ProductFeatureImpl.productList.stream()
                                        .filter(product -> product.getProductId() == oderDetail.getProductId())
                                        .findFirst();

                                if (productOptional.isPresent()) {
                                    Product product = productOptional.get();
                                    product.setStockQuantity(product.getStockQuantity() + oderDetail.getProductQuantity());
                                }
                            });
                    System.out.println("Số lượng sản phẩm đã được trả lại kho.");
                }

                orderToChange.setOrderStatus(newStatus);
                System.out.println("Đã thay đổi trạng thái đơn hàng thành: " + newStatus);
            } catch (IllegalArgumentException e) {
                System.err.println("Trạng thái không hợp lệ. Vui lòng nhập lại.");
            }
        } else {
            System.err.println("Mã đơn hàng không tồn tại.");
        }
    }

    public void showOderDetailByAdmin(Scanner sc) {
        OdersFeatureImpl.odersList.stream().forEach(oders -> oders.display());
        System.out.println("nhập oderId muốn xem chi tiết ");
        int id = Integer.parseInt(sc.nextLine());
//        OderDetailFeatureImpl.oderDetailList.stream().filter(oderDetail -> oderDetail.getOrderId() == id).forEach(oderDetail -> oderDetail.display());
        OderDetailFeatureImpl.oderDetailList.stream().filter(item -> item.getOrderId()==id).forEach(OderDetail::display);
    }


}
