package library.model;

import library.exeption.PublicationAlreadyExistException;
import library.exeption.UserAlreadyExistException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Library implements Serializable {

    private Map<String,Publication> publications = new HashMap<>();
    private Map<String,LibraryUser> users = new HashMap<>();

    public Map<String, Publication> getPublications() {
        return publications;
    }

    public Map<String, LibraryUser> getUsers() {
        return users;
    }

    public void addPublication(Publication publication) {
        if (publications.containsKey(publication.getTitle())){
            throw new PublicationAlreadyExistException(
                    "Publication already exist" + publication.getTitle()
            );
        }
        publications.put(publication.getTitle(),publication);
    }

    public void addUser(LibraryUser user){
        if (users.containsKey(user.getNin())){
            throw new UserAlreadyExistException("" +
                    "User already exist" + user.getNin());
        }
        users.put(user.getNin(),user);
    }

public boolean removePublication(Publication pub){
        if(publications.containsValue(pub)){
            publications.remove(pub.getTitle());
            return true;
        }
        return false;
}
}


