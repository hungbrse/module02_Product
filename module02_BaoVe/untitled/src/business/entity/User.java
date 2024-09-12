package business.entity;

import business.feature.impl.CategoryFeatureImpl;
import business.feature.impl.UserFeatureImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class User  implements Serializable ,Comparable<User>{
    @Override
    public int compareTo(User o) {
        return o.getUserName().compareTo(this.getUserName());
    }

    public static User currentUser;
    private int userId;
    private String userName;
    private String email;
    private String fullName;
    private Boolean status;
    private String password;
    private RoleName roleName = RoleName.USER;
    private int phoneNumber;
    private String address;
    private Date created_at;
    private Date updated_at;

    public User(int userId, Date created_at, Date updated_at, String address, int phoneNumber, RoleName roleName, Boolean status, String password, String fullName, String email, String userName) {
        this.userId = userId;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
        this.status = status;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.userName = userName;
    }

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }
    public void inputData(Scanner sc) {
        this.userId = inputUserId();
        this.email = inputEmail(sc);
        this.password = inputPassword(sc);
        this.userName = inputUserName(sc);
        this.fullName = inputFullName(sc);
        this.status = true;
        this.phoneNumber = inputPhone(sc);
        this.address = inputAddress(sc);
        this.created_at = new Date();
        this.updated_at = new Date();
    }

    public void updataUser(Scanner sc) {
        this.userName = inputUserName(sc);
        this.fullName = inputFullName(sc);
        this.phoneNumber = inputPhone(sc);
    }

    private int inputUserId() {
      UserFeatureImpl.userList.stream().map(User::getUserId).max(Integer::compareTo).ifPresent(userId -> this.userId = userId +1);
      return userId;
    }


    private String inputUserName(Scanner sc) {
        System.out.println("hãy nhập userName");
        do {
            String userName = sc.nextLine();
            if (userName.isBlank()) {
                System.err.println("đừng để userName trống ");
            } else  {
                return userName;
            }

        }while (true);
    }
    private String inputEmail(Scanner sc) {
        System.out.println("hãy nhập user email");
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

        do {
            String email = sc.nextLine();
            if (email.isBlank()) {
                System.err.println("hãy nhập email");
            }else  {
                if (email.matches(emailRegex)) {
                    Optional<User> isExist = UserFeatureImpl.userList.stream().filter(user -> user.email.equals(email)).findFirst();
                    if (isExist.isPresent()) {
                        System.err.println("email đã được sử dụng ");
                    } else  {
                        return email;
                    }
                }else  {
                    System.err.println("nhập email không hợp lệ");
                }
            }

        }while (true);
    }

    private String inputFullName(Scanner sc) {
        System.out.println("hãy nhập họ và tên User");
        do {
            String fullName = sc.nextLine();
            if (fullName.isBlank()) {
                System.err.println("đừng để họ và tên trống  ");
            } else  {
                return fullName;
            }

        }while (true);

    }


    private String inputPassword(Scanner sc) {
        System.out.println("hãy nhập password");

        do {
            String password = sc.nextLine();
            if (password.isBlank()) {
                System.out.println("đừng để password trống ");
            }else  {
                return password;
            }

        }while (true);
    }

    private int inputPhone(Scanner sc) {
        System.out.println("hãy nhập số điện thoại");
        String regex ="^[0-9]{10}$";
        do {
            String phoneNumber = sc.nextLine();
            if (phoneNumber.matches(regex)) {
                return Integer.parseInt(phoneNumber);
            }else  {
                System.out.println("số điện thoại không hợp lệ ");
            }
        }while (true);
    }

    private String inputAddress(Scanner sc) {
        System.out.println("nhập số addrees");
        do {
            String address = sc.nextLine();

            if (address.isBlank()) {
                System.err.println("hãy nhập địa chỉ ");
            }else  {
                return address;
            }
        }while (true);

    }

    public void inputLogin(Scanner sc) {
        System.out.println("hãy nhập email");
        this.email = sc.nextLine().trim();
        this.password = inputPassword(sc);
    }

    public User userLogin(User user) {
        return UserFeatureImpl.userList.stream().filter(u -> u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())).findFirst().orElse(null);
    }
    public void display() {
        System.out.printf("ID: %d, Username: %s, Email: %s, Full Name: %s, Status: %s, Role: %s, Phone: %d, Address: %s, Created At: %s, Updated At: %s%n",
                userId, userName, email, fullName, status ? "Active" : "Inactive", roleName, phoneNumber, address, created_at, updated_at);
    }

}
