package ShoppingCenter.Model;

public class Client{
    private String address;
    private String username;
    private String password;
    private String number;
    private String name;

    public Client()
    {

    }

    public Client(String username, String password, String name, String number, String address)
    {
        this. username = username;
        this. password = password;
        this. name = name;
        this. number = number;
        this.address = address;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address=address;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return username;
    }


    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString(){
        return "ClientDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name=" + name + '\'' +
                ", number=" + number + '\'' +
                ", address=" + address + '\''  +
                "}";
    }
}
