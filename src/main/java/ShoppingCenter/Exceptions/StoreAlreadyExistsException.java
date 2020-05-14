package ShoppingCenter.Exceptions;

public class StoreAlreadyExistsException extends Exception {

    private String store;

    public StoreAlreadyExistsException(String store) {
        super(String.format("There is already a store with the name %s!", store));
        this.store = store;
    }

    public String getStore() {
        return store;
    }
}