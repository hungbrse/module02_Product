package business.feature.impl;

import business.entity.Category;
import business.entity.ShopingCart;
import business.feature.IShopingCartFeature;
import business.untils.IOFile;

import java.util.List;

public class ShopingCartFeatureImpl implements IShopingCartFeature {
    public static List<ShopingCart> shopingCartsList = IOFile.readObjectFromFile(IOFile.PATH_SHOPINGCART);
    @Override
    public void addOrUpdate(ShopingCart shopingCart) {
        int index =findIndexByID(shopingCart.getShopingCartId());
        if(index != -1) {
            shopingCartsList.set(index, shopingCart);
        }else  {
            shopingCartsList.add(shopingCart);
        }
        IOFile.writeObjectToFile(shopingCartsList, IOFile.PATH_SHOPINGCART);
    }

    @Override
    public void delete(Integer id) {
        shopingCartsList.remove(findIndexByID(id));
        IOFile.writeObjectToFile(shopingCartsList, IOFile.PATH_SHOPINGCART);
    }

    public void clearCart(int id) {
        shopingCartsList.removeAll(shopingCartsList.stream().filter(cart -> cart.getUserId() == id).toList());
        IOFile.writeObjectToFile(shopingCartsList, IOFile.PATH_SHOPINGCART);
    }

    @Override
    public int findIndexByID(Integer id) {
        return shopingCartsList.stream().map(ShopingCart::getShopingCartId).toList().indexOf(id);
    }
}
