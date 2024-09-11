package business.entity;

import business.feature.impl.AddressFeatureImpl;

import java.io.Serializable;
import java.util.Scanner;

public class Address implements Serializable {
    private int address_id;
    private int userId;
    private String address;
    private String phone;
    private String receiveName;

    public Address(int address_id, String receiveName, String phone, String address, int userId) {
        this.address_id = address_id;
        this.receiveName = receiveName;
        this.phone = phone;
        this.address = address;
        this.userId = userId;
    }

    public Address() {
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void inputData(Scanner sc) {
        this.address_id = inputAddressId();
        this.userId = inputUserId();
        this.address = inputAddess(sc);
        this.phone = inputPhone(sc);
        this.receiveName = inputReceiveName(sc);
    }

    public void inputUpdata(Scanner sc) {
        this.address = inputAddess(sc);
        this.phone = inputPhone(sc);
        this.receiveName = inputReceiveName(sc);
    }

    private int inputAddressId() {
        int maxId = AddressFeatureImpl.addressList.stream().map(Address::getAddress_id).max(Integer::compareTo).orElse(0);
        return maxId + 1;
    }


    private int inputUserId() {
        User currentUser = User.getCurrentUser();
        return currentUser.getUserId();

    }
    private String inputPhone(Scanner sc) {
        System.out.println("hãy nhập số điện thoại");
        String regex ="^[0-9]{10}$";
        do {
            String phoneNumber = sc.nextLine();
            if (phoneNumber.matches(regex)) {
                return phoneNumber;
            }else  {
                System.out.println("số điện thoại không hợp lệ ");
            }
        }while (true);
    }

    private String inputAddess(Scanner sc) {
        System.out.println("nhập địa chỉ ");
        do {
            String address = sc.nextLine();

            if (address.isBlank()) {
                System.err.println("hãy nhập địa chỉ ");
            }else  {
                return address;
            }
        }while (true);
    }
    private String inputReceiveName(Scanner sc) {
        System.out.println("hãy nhập tên người đặt ");
        do {
            String receiveName = sc.nextLine();
            if (receiveName.isBlank()) {
                System.err.println("đừng để userName trống ");
            } else  {
                return receiveName;
            }

        }while (true);
    }

    public void displayAddress() {
        System.out.printf("ID: %d | User ID: %d | Receive Name: %s | Phone: %s | Address: %s |\n",
                address_id, userId, receiveName, phone, address);
    }
}
