package business.entity;

import business.feature.impl.CategoryFeatureImpl;
import business.feature.impl.ProductFeatureImpl;
import business.feature.impl.UserFeatureImpl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Product  implements Serializable , Comparable<Product> {


    private int productId;
    private String sku;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int stockQuantity;
    private int categoryId;
    private Date createDate;
    private Date updateDate;

    public Product(int productId, Date createDate, Date updateDate, int categoryId, double productPrice, int stockQuantity, String productDescription, String productName, String sku) {
        this.productId = productId;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.categoryId = categoryId;
        this.productPrice = productPrice;
        this.stockQuantity = stockQuantity;
        this.productDescription = productDescription;
        this.productName = productName;
        this.sku = sku;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void inputData(Scanner sc) {
        this.productId = inputProductId();
        this.productName = inputProductName(sc);
        this.sku = inputSku();
        this.productDescription = inputDescription(sc);
        this.productPrice = inputProductPrice(sc);
        this.stockQuantity = inputQuantity(sc);
        this.categoryId = inputCategory(sc);
        this.createDate = new Date();
        this.updateDate = new Date();
    }

    public void inputProductUpdate(Scanner sc) {
        this.productName = inputProductName(sc);
        this.sku = inputSku();
        this.productDescription = inputDescription(sc);
        this.productPrice = inputProductPrice(sc);
        this.stockQuantity = inputQuantity(sc);
        this.categoryId = inputCategory(sc);
        this.updateDate = new Date();
    }

    private int inputProductId() {
        int maxProductId = ProductFeatureImpl.productList.stream()
                .map(Product::getProductId)
                .max(Integer::compareTo)
                .orElse(0);
        return maxProductId + 1;
    }

    private String inputProductName(Scanner sc) {
        System.out.println("hãy nhập tên sản phẩm ");
        do {
            String productName = sc.nextLine();
            if (productName.isBlank()) {
                System.err.println("đừng để tên sản phẩm  trống ");
            } else {
                return productName;
            }

        } while (true);
    }

    private String inputSku() {

        String sku = String.valueOf(UUID.randomUUID());
        return sku;

    }

    private String inputDescription(Scanner scanner) {
        System.out.println("hãy nhập description");
        do {
            String description = scanner.nextLine();
            if (description.isBlank()) {
                System.err.println("đừng để depcription trống ");
            } else {
                return description;
            }

        } while (true);
    }

    private double inputProductPrice(Scanner scanner) {
        System.out.println("hãy nhập giá ");
        do {
            double price = Double.parseDouble(scanner.nextLine());

            try {

                if (price > 0) {
                    return price;
                } else {
                    System.out.println("giá phải lớn hơn 0 ");
                }
            } catch (NumberFormatException e) {
                System.err.println("nhập đinh dạng không đúng ");
            }

        } while (true);
    }

    private int inputQuantity(Scanner scanner) {

        System.out.println("hãy nhập số lượng  ");
        do {
            int quantity = Integer.parseInt(scanner.nextLine());

            try {

                if (quantity > 0) {
                    return quantity;
                } else {
                    System.out.println("số lương phải i lớn hơn 0 ");
                }
            } catch (NumberFormatException e) {
                System.err.println("nhập đinh dạng không đúng ");
            }

        } while (true);
    }


    private int inputCategory(Scanner scanner) {

        CategoryFeatureImpl.categoryList.forEach(e -> {
            System.out.printf("[ ID: %d | Name: %s ] \n", e.getCategoryId(), e.getCategoryName());
        });
        System.out.println("hãy chọn id cate ");
        do {
            try {
                int categoryId = Integer.parseInt(scanner.nextLine());


                Optional<Category> categoryOpt = CategoryFeatureImpl.categoryList.stream().filter(e -> e.getCategoryId() == categoryId).findFirst();
                if (categoryOpt.isPresent()) {
                    return categoryId;
                } else {
                    System.err.println("Không tồn tại danh mục ");
                }
            } catch (NumberFormatException e) {
                System.err.println("ID phải là một số nguyên. Hãy thử lại:");
            }
        } while (true);
    }


    public void displayProduct() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("[productId : %d | sku : %s | productName : %s | description : %s | unit_price : %.2f | stock_quantity : %d | categoryName : %s | created_at | updated_at \n",
                productId,
                sku,
                productName,
                productDescription,
                productPrice,
                stockQuantity,
                findCateById(this.categoryId).getCategoryName(),
                sdf.format(createDate),
                 sdf.format(updateDate)
        );
    }



    private Category findCateById(int catalogId) {
        for(Category cat : CategoryFeatureImpl.categoryList) {
            if(cat.getCategoryId()==catalogId) {
                return cat;
            }
        }
        return null;
    }


    @Override
    public int compareTo(Product o) {
        return Double.compare(this.productPrice,o.productPrice);
    }
}
