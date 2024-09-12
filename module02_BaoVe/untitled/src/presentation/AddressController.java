package presentation;

import business.entity.Address;
import business.entity.User;
import business.feature.impl.AddressFeatureImpl;
import business.feature.impl.ShopingCartFeatureImpl;

import java.util.Optional;
import java.util.Scanner;

public class AddressController {
    AddressFeatureImpl addressFeature = new AddressFeatureImpl();

    public void adrressMenu(Scanner sc) {
        boolean isLoop = true;

        do {
            System.out.println("1. hiển thị danh sách địa chỉ ");
            System.out.println("2. thêm địa chỉ User ");
            System.out.println("3. Xóa  địa chỉ User ");
            System.out.println("4. sửa địa chỉ User ");
            System.out.println("5. Xóa tất cả địa chỉ ");
            System.out.println("6. lấy địa chỉ theo id  ");
            System.out.println("7. Thoát");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showAddress();
                    break;
                    case 2:
                        addAddress(sc);
                        break;
                        case 3:
                            deleteAddress(sc);
                            break;
                            case 4:
                                updateAddress(sc);
                                break;
                                case 5:
                                    clearAll();
                                    break;
                case 6:
                    findAddress(sc);
                    break;
                                    case 7:
                                        isLoop = false;
                                        break;
                default:
                    System.err.println("hãy nhập từ 1 đến 6 ");
                    break;

            }


        }while (isLoop);
    }

    public void showAddress() {

        Optional<Address> optionalAddress = AddressFeatureImpl.addressList.stream().filter(address -> address.getAddress_id() == User.getCurrentUser().getUserId()).findFirst();
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            AddressFeatureImpl.addressList.stream().filter(ad -> ad.getUserId() == User.getCurrentUser().getUserId()).forEach(ad -> ad.displayAddress());
        }else  {
            System.err.println("địa chỉ adress trống : ");
        }

    }

    public void addAddress(Scanner sc) {
        Address add = new Address();
        add.inputData(sc);
        addressFeature.addOrUpdate(add);

    }
    public void updateAddress(Scanner sc) {
        System.out.println("nhập id Address muốn sửa : ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Address> optionalAddress = AddressFeatureImpl.addressList.stream().filter(ad -> ad.getAddress_id() == id).findFirst();

        if (optionalAddress.isPresent()) {
            Address ad = optionalAddress.get();
            ad.inputUpdata(sc);
            addressFeature.addOrUpdate(ad);
        }else  {
            System.out.println("id không tồn tại : ");
        }
    }

    public void deleteAddress(Scanner sc) {
        System.out.println("nhập id muốn xóa : ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Address> optionalAddress = AddressFeatureImpl.addressList.stream().filter(ad -> ad.getAddress_id() == id).findFirst();
        if (optionalAddress.isPresent()) {
            addressFeature.delete(id);
        }else  {
            System.err.println("id không tồn tại : ");
        }
    }

    public void clearAll() {
        addressFeature.clearCart(User.getCurrentUser().getUserId());
    }

    public void findAddress(Scanner sc) {
        System.out.println("nhập địa chỉ id muốn tìm ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Address> optionalAddress = AddressFeatureImpl.addressList.stream().filter(ad -> ad.getAddress_id() == id).findFirst();
        if (optionalAddress.isPresent()) {
            Address ad = optionalAddress.get();
            ad.displayAddress();
        }else  {
            System.err.println("id không tồn tại : ");
        }
    }
}
