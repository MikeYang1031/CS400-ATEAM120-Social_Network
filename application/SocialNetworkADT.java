package application;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * This interface defines the operations required of SocialNetwork class.
 * 
 * @author ATEAM 120
 */

public interface SocialNetworkADT {
  /**
   * Returns a boolean value indicating if friendship is added successfully
   * 
   * @return true if friendship is added successfully, false otherwise
   */
  public boolean addFriends(String person1, String person2);

  /**
   * Returns a boolean value indicating if friendship is removed successfully
   * 
   * @return true if friendship is removed successfully, false otherwise
   */
  public boolean removeFriends(String person1, String person2);

  /**
   * Returns a boolean value indicating if user(vertex) is added successfully
   * to the graph
   * 
   * @return true if user is added successfully, false otherwise
   */
  public boolean addUser(String person);

  /**
   * Returns a boolean value indicating if user(vertex) is removed successfully
   * from the graph
   * 
   * @return true if user is removed successfully, false otherwise
   */
  public boolean removeUser(String person);

  /**
   * Returns a set containing all the friends of a certain user
   * 
   * @return a set containing Person type
   */
  public Set<Person> getFriends(String person);

  /**
   * Returns a set containing the mutual friend between two users
   * 
   * @return a set containing String type
   */
  public Set<String> getMutualFriends(String person1, String person2);

  /**
   * Returns a list containing the shortest path from person1 to person2 in 
   * the graph
   * 
   * @return a List containing the vertices visited on the shortest path
   */
  public List<String> getShortestPath(String person1, String person2);

  /**
   * Returns the number of un-connected groups in the network
   * 
   * @return a int of number of un-connected group
   */
  public int getConnectedComponents();

  /**
   * Update the current social network with the information in the file to be loaded
   * 
   * @param file, the name of the file to be loaded
   */
  public void loadFromFile(File file);

  /**
   * Saving the log data (recordOperation) to a the specified file 
   * 
   * @param file, the name of file in which we want to save the log to 
   */
  public void saveToFile(File file) throws IOException;

  /**
   * setting the central user 
   * 
   * @param centralUser, the string in which we wanted to set as central user
   */
  void setCentral(String centralUser);

  /**
   * Return the central user 
   * 
   */
  public String getCentralUser();
}
