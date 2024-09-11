package business.entity;

public class OderDetail {
    private int orderId;
    private int productId;
    private String productName;
    private int unitPrice;
    private int productQuantity;

    public OderDetail(int orderId, int productQuantity, int unitPrice, int productId, String productName) {
        this.orderId = orderId;
        this.productQuantity = productQuantity;
        this.unitPrice = unitPrice;
        this.productId = productId;
        this.productName = productName;
    }

    public OderDetail() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
