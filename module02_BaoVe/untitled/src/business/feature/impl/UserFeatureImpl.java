package business.feature.impl;

import business.entity.User;
import business.feature.IUserFeature;
import business.untils.IOFile;

import java.util.List;

public class UserFeatureImpl implements IUserFeature {
    public static List<User> userList = IOFile.readObjectFromFile(IOFile.PATH_USERS);
    @Override
    public void addOrUpdate(User user) {
      int index =findIndexByID(user.getUserId());
      if(index != -1) {
       userList.set(index, user);
      }else  {
       userList.add(user);
      }
      IOFile.writeObjectToFile(userList, IOFile.PATH_USERS);
    }

    @Override
    public void delete(Integer id) {

   userList.remove(findIndexByID(id));
    IOFile.writeObjectToFile(userList, IOFile.PATH_USERS);

    }

    @Override
    public int findIndexByID(Integer id) {
        return userList.stream()
                .map(User::getUserId).toList().indexOf(id);
    }
}
