package application;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;    
    private ArrayList<String> FriendShip;
    boolean visited;
    Person predecessor;
    int weight;
    int totalWeight;
    List<Person> list_of_user_friends; 
    
    
    public Person(String name) {
        this.name = name;
        this.weight = 1; // each person has a weight of 1
        this.totalWeight = 100; // each has a total weight of 100 to start
        this.predecessor = null;
        FriendShip = new ArrayList<String>();
        list_of_user_friends = new ArrayList<Person>();
    }
   
    
    public Person(String name, ArrayList<String> FriendShip) {
        this.name = name;
        this.FriendShip = FriendShip;
        list_of_user_friends = new ArrayList<Person>();
    }
    
    public Person() {
        
    }


    public String getName() {
        return this.name;
    }
    
    public ArrayList<String> getFriendShip() {
        return this.FriendShip;
    }
    
    public void setName(String name) {
        this.name = name;
    }
 
//    public void setFriendShip(String[] FriendShip) {
//        this.FriendShip = FriendShip;
//    }  
    public void addFriendShip(String friend, Person person5) {
         FriendShip.add(friend);
         list_of_user_friends.add(person5);
    }  

    public List<Person> getListOfUsersFriends() {
        return this.list_of_user_friends;
    }
    
//    /**
//     * Sets users center position to true or false to determine if user is in center position
//     * @param isUserCenter
//     */
//    public void setUserCenter(boolean isUserCenter) {
//        this.is_User_Center = isUserCenter;
//    }
//    
//    /**
//     * Returns true or false to determine if a user is in the center position
//     * @return is_User_Center
//     */
//    public boolean getUserCenter() {
//        return this.is_User_Center;
//    }


}
