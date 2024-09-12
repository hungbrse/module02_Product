package presentation;

import business.entity.*;
import business.entity.Items.CartItem;
import business.feature.impl.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShopingCartController {
    ShopingCartFeatureImpl shopingCartFeature = new ShopingCartFeatureImpl();
    OdersFeatureImpl odersFeature = new OdersFeatureImpl();
    OderDetailFeatureImpl oderDetailFeature = new OderDetailFeatureImpl();
    Scanner sc = new Scanner(System.in);

    public void shopingCartMenu() {

        while (true) {
            System.out.println("1. Hiển thị danh sách giỏ hàng");
            System.out.println("2. Thêm mới sản phẩm vào giỏ hàng");
            System.out.println("3. Thay đổi số lượng sản phẩm trong giỏ hàng");
            System.out.println("4. Xóa sản phẩm trong giỏ hàng");
            System.out.println("5. Xóa toàn bộ sản phẩm trong giỏ hàng");
            System.out.println("6. Đặt hàng");
            System.out.println("7. Quay lại");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 :
                    showShopingCart();
                    break;
                    case 2 :
                        addToCart(sc);
                        break;
                        case 3 :
                            changeQuantity(sc);
                            break;
                            case 4 :
                                deleteCartByid(sc);
                                break;
                                case 5 :
                                    deleteAllCart();
                                    break;
                                    case 6 :
                                        oder();
                                        break;
                case 7:
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }

    public void showShopingCart() {
        ShopingCartFeatureImpl.shopingCartsList.stream().filter(cart ->cart.getUserId() == User.getCurrentUser().getUserId()).forEach(cart ->cart.displayShopingCart());
    }
    public void addToCart(Scanner sc) {
        System.out.println("nhập số lượng sản phẩm muốn thêm :");
        int n = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < n; i++) {
            ShopingCart shopingCart = new ShopingCart();
            shopingCart.inputData(sc);
            shopingCartFeature.addOrUpdate(shopingCart);
        }
    }
    public void changeQuantity(Scanner sc) {
        ShopingCartFeatureImpl.shopingCartsList.stream().filter(cart ->cart.getUserId() == User.getCurrentUser().getUserId()).forEach(cart ->cart.displayShopingCart());
        System.out.println("nhập id cart muốn thay đổi số lượng : ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<ShopingCart> optionalShopingCart = ShopingCartFeatureImpl.shopingCartsList.stream().filter(cart -> cart.getShopingCartId() == id).findFirst();

        if (optionalShopingCart.isPresent()) {
            ShopingCart shopingCart = optionalShopingCart.get();
            shopingCart.changeOderQuantity(sc);
        }
    }
    public void deleteCartByid(Scanner sc) {
        ShopingCartFeatureImpl.shopingCartsList.stream().filter(cart ->cart.getUserId() == User.getCurrentUser().getUserId()).forEach(cart ->cart.displayShopingCart());
        System.out.println("hãy nhập cart Id muốn xóa : ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<ShopingCart> optionalShopingCart = ShopingCartFeatureImpl.shopingCartsList.stream().filter(cart -> cart.getShopingCartId() == id).findFirst();
        if (optionalShopingCart.isPresent()) {
            shopingCartFeature.delete(id);
        }else  {
            System.err.println("id không tồn tại : ");
        }
    }
    public void deleteAllCart() {
        shopingCartFeature.clearCart(User.getCurrentUser().getUserId());
    }
    public void oder() {

        Optional<ShopingCart> optionalShopingCart = ShopingCartFeatureImpl.shopingCartsList.stream().filter(cart ->cart.getUserId() == User.getCurrentUser().getUserId()).findFirst();
        if (optionalShopingCart.isEmpty()) {
            System.err.println("giỏ hàng trống :");
        } else  {
            Optional<Address> optionalAddress = AddressFeatureImpl.addressList.stream().filter(address -> address.getUserId() == User.getCurrentUser().getUserId()).findFirst();
            if (optionalAddress.isPresent()) {
                Oders oders = new Oders();
                oders.inputData(sc);
                odersFeature.addOrUpdate(oders);
                for (ShopingCart cart : ShopingCartFeatureImpl.shopingCartsList.stream().filter(item -> item.getUserId() == User.currentUser.getUserId()).toList()) {
                    OderDetail newOderDetail = new OderDetail();
                    newOderDetail.inputdata(oders,cart);
                    oderDetailFeature.addOrUpdate(newOderDetail);
                }
                shopingCartFeature.clearCart(User.getCurrentUser().getUserId());
            }else {
                System.err.println("hãy nhập địa chỉ trước khi mua ");
            }

        }

    }
}
