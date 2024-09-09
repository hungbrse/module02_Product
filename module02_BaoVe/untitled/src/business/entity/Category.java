package business.entity;

import business.feature.impl.CategoryFeatureImpl;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Category implements Serializable , Comparable<Category>{
    @Override
    public int compareTo(Category o) {
        return this.categoryName.compareTo(o.categoryName);
    }

    private  int categoryId;
    private String categoryName;
    private String description;
    private boolean status;

    public Category(int categoryId, boolean status, String description, String categoryName) {
        this.categoryId = categoryId;
        this.status = status;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Category() {
    }


    public void inputData(Scanner sc) {
        this.categoryId = inputCategoryId();
        this.categoryName = inputCategoryName(sc, CategoryFeatureImpl.categoryList);
        this.description = inputDescription(sc);
        this.status = inputCateStatus(sc);
    }
    public void inputDataCateUpdate(Scanner sc) {
        this.categoryName = inputCategoryName(sc,CategoryFeatureImpl.categoryList);
        this.description = inputDescription(sc);
        this.status = inputCateStatus(sc);
    }


    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private int inputCategoryId() {
        CategoryFeatureImpl.categoryList.stream().map(Category::getCategoryId).max(Integer::compareTo).ifPresent(cateId -> this.categoryId = categoryId +1);
        return categoryId;
    }

    private String inputCategoryName(Scanner sc, List<Category> categoryList) {
        System.out.println("nhập category Name");
        do {
            String categoryName = sc.nextLine();
            if (categoryName.isBlank()) {
                System.out.println("nhập category Name");
            } else  {
                return categoryName;
            }

        }while (true);
    }

    private String inputDescription(Scanner sc) {
        System.out.println("nhập category description");
        do {
            String categoryDescription = sc.nextLine();
            if (categoryDescription.isBlank()) {
                System.err.println("nhập category description");
            }else  {
                return categoryDescription;
            }

        }while (true);
    }

    private boolean inputCateStatus(Scanner sc) {
        System.out.println("nhập trạng thái category");
        do {
            String categoryStatus = sc.nextLine();
            if (categoryStatus.equals("true") || categoryStatus.equals("false")) {
                return Boolean.parseBoolean(categoryStatus);
            }else  {
                System.err.println("hãy nhập true or false ");
            }
        }while (true);
    }

    public void displayCategory() {
        System.out.printf("[ categoryId : %d | categoryName : %s | description : %s | status : %s]\n", categoryId, categoryName, description, status);
    }
}
