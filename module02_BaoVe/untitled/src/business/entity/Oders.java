package business.entity;

import business.feature.impl.AddressFeatureImpl;
import business.feature.impl.OdersFeatureImpl;
import business.feature.impl.ProductFeatureImpl;
import business.feature.impl.ShopingCartFeatureImpl;
import presentation.AddressController;

import java.io.Serializable;
import java.util.*;

public class Oders  implements Serializable {
    private int oderId;
    private String serialNumber;
    private int userId;
    private double totalPrice;
    private OderStatus orderStatus = OderStatus.WAITING;
    private String note;
    private String receiveName;
    private String receiveAddress;
    private String receivePhone;
    private Date createDate = new Date();
    private Date receivedDate;

    public int getOderId() {
        return oderId;
    }

    public void setOderId(int oderId) {
        this.oderId = oderId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Oders(int oderId, int userId, String serialNumber, double totalPrice, OderStatus orderStatus, String note, String receiveName, String receiveAddress, String receivePhone, Date createDate, Date receivedDate) {
        this.oderId = oderId;
        this.userId = userId;
        this.serialNumber = serialNumber;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.note = note;
        this.receiveName = receiveName;
        this.receiveAddress = receiveAddress;
        this.receivePhone = receivePhone;
        this.createDate = createDate;
        this.receivedDate = receivedDate;
    }

    public Oders() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate); // Đặt ngày ban đầu là createDate
        calendar.add(Calendar.DAY_OF_MONTH, 4); // Thêm 4 ngày vào createDate
        this.receivedDate = calendar.getTime(); // Khởi tạo receivedDate
    }

    public void inputData(Scanner sc) {
        this.oderId = inputOderId();
        this.serialNumber = inputSerialNumber();
        this.userId = inputUserId();
        this.totalPrice = inputPrice();
        this.note = inputOderNote(sc);
        AddressFeatureImpl.addressList.stream().filter(ad -> ad.getUserId() == User.currentUser.getUserId()).forEach(ad -> ad.displayAddress());
        System.out.println("nhập id address ");
        int id = Integer.parseInt(sc.nextLine());
        Optional<Address> optionalAddress = AddressFeatureImpl.addressList.stream().filter(ad -> ad.getAddress_id() == id).findFirst();
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            this.receiveAddress = address.getAddress();
                    this.receivePhone = address.getPhone();

        } else {
            System.out.println("id địa chỉ không tồn tại ");
            return;
        }

        this.createDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(createDate);
        calendar.add(Calendar.DAY_OF_MONTH, 4);
        this.receivedDate = calendar.getTime();
    }

    private String inputSerialNumber() {
        return UUID.randomUUID().toString();
    }

    private int inputUserId() {
        User currentUser = User.getCurrentUser();
        return currentUser.getUserId();
    }

    private double inputPrice() {

        double total = 0;

        for (ShopingCart cart : ShopingCartFeatureImpl.shopingCartsList) {
            if (cart.getUserId() == userId) {
                Optional<Product> optionalProduct = ProductFeatureImpl.productList
                        .stream()
                        .filter(product -> product.getProductId() == cart.getProductInd())
                        .findFirst();

                if (optionalProduct.isPresent()) {
                    Product product = optionalProduct.get();
                    total += product.getProductPrice() * cart.getOderQuantity();
                }
            }
        }

        return total;
    }

    private String inputOderNote(Scanner sc) {

        System.out.println("hãy nhập Note");
        do {
            String note = sc.nextLine();
            if (note.isBlank()) {
                System.err.println("đừng để ghi chú  trống ");
            } else {
                return note;
            }

        } while (true);

    }

    private int inputOderId() {
        int maxId = OdersFeatureImpl.odersList.stream().map(Oders::getOderId).max(Integer::compareTo).orElse(0);
        return maxId + 1;
    }

    public void display() {
        System.out.printf("Order ID: %d, Serial Number: %s, User ID: %d, Total Price: %.2f, Status: %s, Note: %s, Receiver: %s, Address: %s, Phone: %s, Created Date: %s, Received Date: %s%n",
                oderId, serialNumber, userId, totalPrice, orderStatus, note, receiveName, receiveAddress, receivePhone, createDate.toString(), receivedDate != null ? receivedDate.toString() : "N/A");
    }

}
