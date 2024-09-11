package business.untils;

import business.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static final String PATH_ADDRESS = "C:\\Users\\Admin\\Desktop\\module02_BaoVe\\untitled\\src\\business\\data\\address.txt";
    public static final String PATH_ODERS = "C:\\Users\\Admin\\Desktop\\module02_BaoVe\\untitled\\src\\business\\data\\oders.txt";
    public static final String PATH_PRODUCTS = "C:\\Users\\Admin\\Desktop\\module02_BaoVe\\untitled\\src\\business\\data\\product.txt";
    public static final String PATH_USERS = "C:\\Users\\Admin\\Desktop\\module02_BaoVe\\untitled\\src\\business\\data\\users.txt";
    public static final String PATH_CATEGORIES = "C:\\Users\\Admin\\Desktop\\module02_BaoVe\\untitled\\src\\business\\data\\categories.txt";
    public static final String PATH_SHOPINGCART = "C:\\Users\\Admin\\Desktop\\module02_BaoVe\\untitled\\src\\business\\data\\shopingCart.txt";
    public static <T> List<T> readObjectFromFile(String path) {
        List<T> list = new ArrayList<>();
        File file = new File(path);
        if (file.length() == 0) {
            return list;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            list = (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> boolean writeObjectToFile(List<T> list, String path)
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path)))
        {
            oos.writeObject(list);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
