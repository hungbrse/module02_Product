package business.feature.impl;

import business.entity.Address;
import business.entity.User;
import business.feature.IAddressFeature;
import business.untils.IOFile;

import java.util.List;

public class AddressFeatureImpl implements IAddressFeature {
    public static List<Address> addressList = IOFile.readObjectFromFile(IOFile.PATH_ADDRESS);
    @Override
    public void addOrUpdate(Address address) {
        int index =findIndexByID(address.getAddress_id());
        if(index != -1) {
            addressList.set(index, address);
        }else  {
            addressList.add(address);
        }
        IOFile.writeObjectToFile(addressList, IOFile.PATH_ADDRESS);
    }

    @Override
    public void delete(Integer id) {
        addressList.remove(findIndexByID(id));
        IOFile.writeObjectToFile(addressList, IOFile.PATH_ADDRESS);
    }


    public void clearCart(int id) {
        addressList.removeAll(addressList.stream().filter(cart -> cart.getUserId() == id).toList());
        IOFile.writeObjectToFile(addressList, IOFile.PATH_ADDRESS);
    }

    @Override
    public int findIndexByID(Integer id) {

        return addressList.stream()
                .map(Address::getAddress_id).toList().indexOf(id);
    }
}
