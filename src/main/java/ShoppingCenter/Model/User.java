package ShoppingCenter.Model;

public class User {

    private String username;
    private String password;
    private String role;
    private String number;
    private String address;
    private String name;
    private String store;

    public User() {
    }

    public User(String username, String password, String role,String name,String number,String address,String store) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.number = number;
        this.address = address;
        this.store = store;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number){
        this.number=number;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getStore() {
        return this.store;
    }
    public void setStore(String store) {
        this.store=store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!password.equals(user.password)) return false;
        return role.equals(user.role);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + role.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name=" + name + '\'' +
                ", number=" + number + '\'' +
                ", address=" + address + '\'' +
                ", store=" + store + '\'' +
                "}";
    }
}