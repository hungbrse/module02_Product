package presentation;

import business.entity.Category;
import business.entity.Product;
import business.feature.impl.CategoryFeatureImpl;
import business.feature.impl.ProductFeatureImpl;

import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class ProductController {
     ProductFeatureImpl productFeature = new ProductFeatureImpl();

     public void menuProduct(Scanner sc) {
         boolean isLoop = true;

         while (isLoop) {
             System.out.println("1. Hiển thị sản phẩm :");
             System.out.println("2. thêm mới sản phẩm ");
             System.out.println("3. sửa thông tin sản phẩm ");
             System.out.println("4. xóa sản phẩm  ");
             System.out.println("5. tìm kiếm sản phẩm  ");
             System.out.println("6. sắp xếp sản phẩm theo giá ");
             System.out.println("7 quay lại ");

             int choice = Integer.parseInt(sc.nextLine());
             switch (choice) {
                 case 1:
                     showProduct();
                     break;
                     case 2:
                         addNewProduct(sc);
                       break;
                       case 3:
                           updateProduct(sc);
                           break;
                           case 4:
                               deleteProduct(sc);
                               break;
                               case 5:
                                   findProduct(sc);
                                   break;
                                   case 6:
                                       sortProduct();
                                       break;
                                       case 7:
                                           isLoop = false;
                                           break;
                 default:
                     System.err.println("hãy nhập từ 1 đến 7 !");
                             break;
             }
         }

     }

    private void addNewProduct(Scanner sc) {
        System.out.println("Nhập số lượng muốn thêm: ");
        int number = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < number; i++) {
            Product newProduct = new Product();
            newProduct.inputData(sc);
            productFeature.addOrUpdate(newProduct);
        }
    }

    public void showProduct() {
         ProductFeatureImpl.productList.forEach(product -> product.displayProduct());
    }

    private void updateProduct(Scanner sc) {
        System.out.println("Nhập mã danh mục cần update: ");
        int idUpdate = Integer.parseInt(sc.nextLine());

        Optional<Product> proOpt = ProductFeatureImpl.productList.stream()
                .filter(e->e.getProductId() == idUpdate).findFirst();

        if(proOpt.isPresent()) {
             Product proUpdate = proOpt.get();
            proUpdate.inputProductUpdate(sc);
            productFeature.addOrUpdate(proUpdate);
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Không tồn tại sản phẩm ");
        }
    }


    private void deleteProduct(Scanner sc) {
        System.out.println("Nhập mã danh mục cần xóa: ");
        int idDelete = Integer.parseInt(sc.nextLine());

        Optional<Product> productOpt = ProductFeatureImpl.productList.stream()
                .filter(e->e.getProductId() == idDelete).findFirst();

        if(productOpt.isPresent()) {
            productFeature.delete(idDelete);
            System.out.println("Xóa sản phẩm  thành công.");
        } else {
            System.err.println("Không tồn tại sản phẩm ");
        }
    }
    private void findProduct(Scanner sc) {
        System.out.println("nhập id sản phẩm muốn tìm ");
        int id = Integer.parseInt(sc.nextLine());


        Optional<Product> optionalProduct = ProductFeatureImpl.productList.stream().filter(product -> product.getProductId() == id).findFirst();
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.displayProduct();
        }else  {
            System.err.println("id không tồn tại ");
        }

    }

    private void sortProduct() {
        Collections.sort(ProductFeatureImpl.productList);
        ProductFeatureImpl.productList.forEach(product -> product.displayProduct());
    }
}
