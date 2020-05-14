package ShoppingCenter.Services;

import ShoppingCenter.Exceptions.CouldNotWriteUsersException;
import ShoppingCenter.Exceptions.StoreAlreadyExistsException;
import ShoppingCenter.Exceptions.UsernameAlreadyExistsException;

import ShoppingCenter.Model.Client;
import ShoppingCenter.Model.Manager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

public class UserService {

    public static List<Client> clients;
    public static List<Manager> managers;
    private static String current_manager;
    private static String current_client;
    private static final Path CLIENTS_PATH = FileSystemService.getPathToFile("config", "clients.json");
    private static final Path Managers_PATH = FileSystemService.getPathToFile("config", "managers.json");


    public static void addClient(String username, String password, String name, String number, String address) throws UsernameAlreadyExistsException {
        checkClientDoesNotAlreadyExist(username);
        clients.add(new Client(username, encodePassword(username, password), name, number, address));
        persistClients();
    }

    public static void addManager(String username, String password, String name, String number, String store) throws UsernameAlreadyExistsException, StoreAlreadyExistsException {
        checkManagerDoesNotAlreadyExist(username);
        checkStoreDoesNotAlreadyExist(store);
        managers.add(new Manager(username, encodePassword(username, password), name, number, store));
        persistManagers();
    }

    public static void modifyManager(String username, String password, String name, String number, String store) throws UsernameAlreadyExistsException, StoreAlreadyExistsException {
        // verify if the username is available
        if(!username.equals(current_manager)) {
            for (Manager manager : managers) {
                if (Objects.equals(username, manager.getUsername())) {
                    checkManagerDoesNotAlreadyExist(username);
                }
            }
        }
        //make the modification
        for (Manager manager : managers) {
            if (Objects.equals(current_manager, manager.getUsername())) {
                if(!username.isEmpty())
                {
                    manager.setUsername(username);
                    current_manager = username;
                }
                if(!password.isEmpty())
                {
                    manager.setPassword(encodePassword(username, password));
                }
                if(!name.isEmpty())
                {
                    manager.setName(name);
                }
                if(!number.isEmpty())
                {
                    manager.setNumber(number);
                }
                if(!store.isEmpty() && !store.equals(manager.getStore_name()))
                {
                    checkStoreDoesNotAlreadyExist(store);
                    manager.setStore_name(store);
                }
            }
        }
        persistManagers();
    }
    public static void modifyClient(String username, String password, String name, String number, String address) throws UsernameAlreadyExistsException {
        // verify if the username is available
        if(!username.equals(current_client)) {
            for (Client client : clients) {
                if (Objects.equals(username, client.getUsername())) {
                    checkClientDoesNotAlreadyExist(username);
                }
            }
        }
        //make the modification
        for (Client client : clients) {
            if (Objects.equals(current_client, client.getUsername())) {
                if(!username.isEmpty())
                {
                    client.setUsername(username);
                    current_client = username;
                }
                if(!password.isEmpty())
                {
                    client.setPassword(encodePassword(username, password));
                }
                if(!name.isEmpty())
                {
                    client.setName(name);
                }
                if(!number.isEmpty())
                {
                    client.setNumber(number);
                }
                if(!address.isEmpty())
                {
                    client.setAddress(address);
                }
            }
        }
        persistClients();
    }



    public static void loadClientsFromFile() throws IOException {

        if (!Files.exists(CLIENTS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("clients.json")), CLIENTS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        clients = objectMapper.readValue(CLIENTS_PATH.toFile(), new TypeReference<List<Client>>() {
        });
    }

    public static void loadManagersFromFile() throws IOException {

        if (!Files.exists(Managers_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("managers.json")), Managers_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();

        managers = objectMapper.readValue(Managers_PATH.toFile(), new TypeReference<List<Manager>>() {
        });
    }

    private static void checkClientDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (Client client : clients) {
            if (Objects.equals(username, client.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static void checkManagerDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (Manager manager : managers) {
            if (Objects.equals(username, manager.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    public static void checkStoreDoesNotAlreadyExist(String store) throws StoreAlreadyExistsException {
        for (Manager manager : managers) {
            if (Objects.equals(store, manager.getStore_name()))
                throw new StoreAlreadyExistsException(store);
        }
    }


    private static void persistClients() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(CLIENTS_PATH.toFile(), clients);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }

    private static void persistManagers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(Managers_PATH.toFile(), managers);
        } catch (IOException e) {
            throw new CouldNotWriteUsersException();
        }
    }


    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", "");
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static String getCurrent_manager() {
        return current_manager;
    }

    public static String getCurrent_client() {
        return current_client;
    }

    public static boolean verifyClient(String username, String password) {
        for (Client client : clients) {
            if (username.equals(client.getUsername()) && Objects.equals(encodePassword(username, password), client.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    public static boolean verifyManager(String username, String password){
        for (Manager manager : managers) {
            if (Objects.equals(username, manager.getUsername()) && Objects.equals(encodePassword(username,password), manager.getPassword()))
            {
                return true;
            }
        }
        return false;
    }

    public static void setCurrent_manager(String name) {
        current_manager = name;
    }
    public static void setCurrent_client(String name){
        current_client = name;
    }
}
