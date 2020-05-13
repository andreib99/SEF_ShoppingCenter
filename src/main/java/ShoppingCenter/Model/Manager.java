package ShoppingCenter.Model;

public class Manager {
    private Store store;
    private String username;
    private String password;
    private String number;
    private String name;
    private String store_name;

    public Manager()
    {

    }

    public Manager(String username, String password, String name, String number, String store_name)
    {
        this. username = username;
        this. password = password;
        this. name = name;
        this. number = number;
        this.store_name = store_name;
        store = new Store(store_name);
    }


    public String getStore() {
        return this.store.getName();
    }


    public String getStore_name() {
        return store_name;
    }

    public String getUsername() {
        return username;
    }

    public String getNumber() {
        return number;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String toString()
    {
        return "ManagerDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name + '\'' +
                ", number=" + number + '\'' +
                ", address=" + store_name + '\''  +
                "}";
    }
}
