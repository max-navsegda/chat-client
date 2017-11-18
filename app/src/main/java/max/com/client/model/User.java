package max.com.client.model;

/**
 * Created by Maxim on 9/28/2017.
 */

public class User {
    private String phone;
    private String password;
    private String rate;
    private int balance;

    public User() {
    }

    public User(String phone, String password, String rate, int balance) {
        this.phone = phone;
        this.password = password;
        this.rate = rate;
        this.balance = balance;
    }

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
