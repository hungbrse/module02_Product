package business.entity;

import business.feature.impl.ProductFeatureImpl;
import business.feature.impl.ShopingCartFeatureImpl;
import business.untils.IOFile;

import java.io.Serializable;
import java.util.Optional;
import java.util.Scanner;

public class ShopingCart implements Serializable {
    private int shopingCartId;
    private int productInd;
    private int userId;
    private int oderQuantity;

    public ShopingCart(int shopingCartId, int oderQuantity, int productInd, int userId) {
        this.shopingCartId = shopingCartId;
        this.oderQuantity = oderQuantity;
        this.productInd = productInd;
        this.userId = userId;
    }

    public ShopingCart() {
    }

    public int getOderQuantity() {
        return oderQuantity;
    }

    public void setOderQuantity(int oderQuantity) {
        this.oderQuantity = oderQuantity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductInd() {
        return productInd;
    }

    public void setProductInd(int productInd) {
        this.productInd = productInd;
    }

    public int getShopingCartId() {
        return shopingCartId;
    }

    public void setShopingCartId(int shopingCartId) {
        this.shopingCartId = shopingCartId;
    }

    public void inputData(Scanner sc) {
        this.shopingCartId = inputShopingId();
        this.productInd = inputProductId(sc);
        if (productInd == -1) {
            System.err.println("Không thể thêm sản phẩm vào giỏ hàng.");
            return;
        }
        this.userId = inputUserId();
        this.oderQuantity = inputOderQuantity(sc);
    }
    public void changeOderQuantity(Scanner sc) {
        this.oderQuantity = inputOderQuantity(sc);
    }

    private int inputShopingId() {

      int maxId = ShopingCartFeatureImpl.shopingCartsList.stream().map(ShopingCart::getShopingCartId).max(Integer::compareTo).orElse(0);

      return maxId + 1;



    }
    private int inputUserId() {
        User curentUser = User.getCurrentUser();
        if (curentUser == null) {
            System.err.println("Người dùng hiện tại chưa được đăng nhập.");
            throw new IllegalStateException("Không thể thêm sản phẩm vào giỏ hàng vì người dùng chưa đăng nhập.");
        }
        return curentUser.getUserId();

    }
    private int inputProductId(Scanner sc) {
        ProductFeatureImpl.productList.forEach(product -> product.displayProduct());
        do {
            try {
                int productId = Integer.parseInt(sc.nextLine());
                Optional<Product> optionalProduct = ProductFeatureImpl.productList.stream().filter(product -> product.getProductId() == productId).findFirst();
                if (optionalProduct.isPresent()) {
                    boolean isHasProduct = ShopingCartFeatureImpl.shopingCartsList.stream().anyMatch(cart -> cart.getShopingCartId() == optionalProduct.get().getProductId() && cart.getUserId() == userId);
                    if (isHasProduct) {
                        System.out.println("sản phẩm đã có trong giỏ hàng : ");
                             return -1;
                    }
                    return optionalProduct.get().getProductId();
                }else  {
                    System.err.println("product Id không tồn tại : ");
                }
            }catch (NumberFormatException e) {
                System.err.println("hãy nhập product Id là 1 số : ");
            }


        }while (true);

    }
    private int inputOderQuantity(Scanner sc) {
        System.out.println("nhập số lượng muốn thêm vào giỏ : ");
        do {
            try {
                int orderQuantity = Integer.parseInt(sc.nextLine());
                Optional<Product> optionalProduct = ProductFeatureImpl.productList.stream().filter(product -> product.getProductId() == productInd).findFirst();
                if (optionalProduct.isPresent()) {
                    if (orderQuantity < optionalProduct.get().getStockQuantity()) {
                        optionalProduct.get().setStockQuantity(optionalProduct.get().getStockQuantity() - orderQuantity);
                        IOFile.writeObjectToFile(ProductFeatureImpl.productList,IOFile.PATH_PRODUCTS);
                        return orderQuantity;
                    } else  {
                        System.err.println("số lượng mua không được quá số lượng trong kho :");
                    }
                }

            }catch (NumberFormatException e) {

            }
        }while (true);
    }
    public void displayShopingCart() {
        System.out.printf("[ShopingCartId : %d | Product Id : %d | UserId : %d | quantity : %d]\n", shopingCartId, productInd, userId, oderQuantity);
    }
}
