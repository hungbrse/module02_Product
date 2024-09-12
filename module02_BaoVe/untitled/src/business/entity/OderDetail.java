package business.entity;

import business.feature.impl.ProductFeatureImpl;

import java.io.Serializable;
import java.util.Optional;

public class OderDetail implements Serializable {
    private int orderId;
    private int productId;
    private String productName;
    private double unitPrice;
    private int productQuantity;

    public OderDetail(int orderId, int productQuantity, double unitPrice, int productId, String productName) {
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void inputdata(Oders oders, ShopingCart shopingCart) {
        orderId = oders.getOderId();
        productId = shopingCart.getProductInd();
        productName =  findNameProduct(shopingCart.getProductInd());
        unitPrice = findPriceProduct(shopingCart.getProductInd());
        productQuantity = shopingCart.getOderQuantity();


    }

    public String findNameProduct(int productId) {
        Optional<Product> optionalProduct = ProductFeatureImpl.productList.stream().filter(product -> product.getProductId() == productId).findFirst();
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getProductName();
        }
        return null;
    }

    public double findPriceProduct(int productId) {
        Optional<Product> optionalProduct = ProductFeatureImpl.productList.stream().filter(product -> product.getProductId() == productId).findFirst();
        if (optionalProduct.isPresent()) {
            return optionalProduct.get().getProductPrice();
        }
        return 0;
    }
    public void display() {
        System.out.printf("Order ID: %d | Product ID: %d | Product Name: %s | Unit Price: %.2f | Quantity: %d%n",
                orderId, productId, productName, unitPrice, productQuantity);
    }


}
