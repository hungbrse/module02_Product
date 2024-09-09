package business.feature.impl;

import business.entity.Category;
import business.entity.User;
import business.feature.ICategoryFeature;
import business.untils.IOFile;

import java.util.List;

public class CategoryFeatureImpl implements ICategoryFeature {

    public static List<Category>  categoryList = IOFile.readObjectFromFile(IOFile.PATH_CATEGORIES);

    @Override
    public void addOrUpdate(Category category) {
        int index =findIndexByID(category.getCategoryId());
        if(index != -1) {
            categoryList.set(index, category);
        }else  {
            categoryList.add(category);
        }
        IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORIES);

    }

    @Override
    public void delete(Integer id) {
        categoryList.remove(findIndexByID(id));
        IOFile.writeObjectToFile(categoryList, IOFile.PATH_CATEGORIES);
    }

    @Override
    public int findIndexByID(Integer id) {
        return categoryList.stream()
                .map(Category::getCategoryId).toList().indexOf(id);
    }
}
