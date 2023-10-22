package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model Instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance(){
        return Instance;
    }

    private Model(){
        model = new HashMap<>();
        model.put(1, new User("Alex", "Zabulonov", 10010));
        model.put(2, new User("Jonh", "Stasitski", 100));
        model.put(3, new User("Jack", "Black", 9999999));
    }

    public void Add(User user, int id){
        model.put(id, user);
    }

    public Map<Integer, User> getFromList()
    {
        return model;
    }

    public void Delete(int id, User user) throws Exception {
        boolean isDelete = model.remove(id, user);
        if (!isDelete)
            throw new Exception("User is not exist.");
    }

    public void Put(User newUser, int id)
    {
        User oldUser = model.get(id);
        model.replace(id, oldUser, newUser);
    }

    public boolean validateId(int id) {
        if(id <= 0 || id > this.getFromList().size())
            return false;
        return true;
    }

    public boolean validateIdWithZero(int id) {
        if(id < 0 || id > this.getFromList().size())
            return false;
        return true;
    }

}
