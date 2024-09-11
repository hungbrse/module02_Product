package presentation;

import business.entity.Category;
import business.feature.ICategoryFeature;
import business.feature.impl.CategoryFeatureImpl;

import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;

public class CategoryController {

    CategoryFeatureImpl categoryFeature = new CategoryFeatureImpl();

    public void menuCate(Scanner sc) {
        boolean flag = true;
        while(flag) {
            System.out.println("1. Hiển thị danh mục  :");
            System.out.println("2. thêm mới danh mục  ");
            System.out.println("3. sửa thông tin danh mục  ");
            System.out.println("4. xóa danh mục   ");
            System.out.println("5. tìm kiếm danh mục   ");
            System.out.println("6. sắp xếp sản phẩm theo tên ");
            System.out.println("7 quay lại ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showCategory();
                    break;
                    case 2:
                        addNewCate(sc);
                        break;
                case 3:
                    updateCate(sc);
                    break;
                    case 4:
                        deleteCate(sc);
                        break;
                        case 5:
                      findCategory(sc);
                      break;
                      case 6:
                          sortCategory();
                          break;
                          case 7:
                              flag = false;
                              break;
                default:
                    System.err.println("hãy nhập từ 1 đến 7 !");
                    break;
            }
        }
    }

    private void showCategory() {
        categoryFeature.categoryList.forEach(category -> category.displayCategory());
    }


    private void addNewCate(Scanner sc) {
        System.out.println("Nhập số lượng muốn thêm: ");
        int number = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < number; i++) {
            Category newCate = new Category();
            newCate.inputData(sc);
            categoryFeature.addOrUpdate(newCate);
        }
    }


    private void updateCate(Scanner sc) {
        System.out.println("Nhập mã danh mục cần update: ");
        int idUpdate = Integer.parseInt(sc.nextLine());

        Optional<Category> cateOpt = CategoryFeatureImpl.categoryList.stream()
                .filter(e->e.getCategoryId() == idUpdate).findFirst();

        if(cateOpt.isPresent()) {
            Category catUpdate = cateOpt.get();
            catUpdate.inputDataCateUpdate(sc);
            categoryFeature.addOrUpdate(catUpdate);
            System.out.println("Cập nhật thành công");
        } else {
            System.err.println("Không tồn tại danh mục");
        }
    }


    private void deleteCate(Scanner sc) {
        System.out.println("Nhập mã danh mục cần xóa: ");
        int idDelete = Integer.parseInt(sc.nextLine());

        Optional<Category> cateOpt = CategoryFeatureImpl.categoryList.stream()
                .filter(e->e.getCategoryId() == idDelete).findFirst();

        if(cateOpt.isPresent()) {
            categoryFeature.delete(idDelete);
            System.out.println("Xóa danh mục thành công.");
        } else {
            System.err.println("Không tồn tại danh mục");
        }
    }
    private void findCategory(Scanner sc) {
        System.out.println("nhập tên danh mục muốn tìm : ");
        String name = sc.nextLine();
       CategoryFeatureImpl.categoryList.stream().filter(cate ->cate.getCategoryName().contains(name)).forEach(cate -> cate.displayCategory());
    }
    private void sortCategory() {
        Collections.sort(CategoryFeatureImpl.categoryList);
        CategoryFeatureImpl.categoryList.forEach(cate -> cate.displayCategory());
    }
}

