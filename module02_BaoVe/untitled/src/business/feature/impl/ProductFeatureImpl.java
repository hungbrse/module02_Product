package business.feature.impl;

import business.entity.Category;
import business.entity.Product;
import business.feature.IProductFeature;
import business.untils.IOFile;

import java.util.List;

public class ProductFeatureImpl implements IProductFeature  {
    public static List<Product> productList = IOFile.readObjectFromFile(IOFile.PATH_PRODUCTS);
    @Override
    public void addOrUpdate(Product product) {
        int index =findIndexByID(product.getProductId());
        if(index != -1) {
            productList.set(index, product);
        }else  {
            productList.add(product);
        }
        IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCTS);
    }

    @Override
    public void delete(Integer id) {
        productList.remove(findIndexByID(id));
        IOFile.writeObjectToFile(productList, IOFile.PATH_PRODUCTS);
    }

    @Override
    public int findIndexByID(Integer id) {
        return productList.stream()
                .map(Product::getProductId).toList().indexOf(id);
    }
}
