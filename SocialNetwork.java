package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * @author ateam120
 *
 */
public class SocialNetwork implements SocialNetworkADT {


    private Graph graph;

    // constructor
    public SocialNetwork() {

        graph = new Graph();
        recordOperations = new ArrayList<String>();

    }

    private ArrayList<String> recordOperations;

    @Override
    public boolean addFriends(String person1, String person2) {

        graph.addEdge(person1, person2);
        recordOperations.add("a" + person1 + person2);

        return true;
    }


    @Override
    public boolean removeFriends(String person1, String person2) {

        if (!graph.getAdjacentVerticesOf(person1).contains(person2)) {
            return false;
        }
        graph.removeEdge(person1, person2);
        recordOperations.add("r" + person1 + person2);
        return true;
    }

    @Override
    public boolean addUser(String person) {
        graph.addVertex(person);
        recordOperations.add("a " + person);
        return true;
    }

    @Override
    public boolean removeUser(String person) {

        if (!graph.getAllVertices().contains(person)) {
            return false;
        }
        graph.removeVertex(person);
        recordOperations.add("r " + person);
        return true;
    }

    @Override
    public List<String> getFriends(String person) {
        List<String> friend = graph.getAdjacentVerticesOf(person);
        // TODO Auto-generated method stub
        return friend;
    }

    @Override
    public Set<Person> getMutualFriends(String person1, String person2) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Person> getShortestPath(String person1, String person2) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Graph> getConnectedComponents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void loadFromFile(File file) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveToFile(File file) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < recordOperations.size(); i++) {
                String log = recordOperations.get(i);
                fileWriter.write(log);
                fileWriter.write("\n");
            }
        }

        catch (Exception e) {
            System.out.println("Caution: IOException!");
        }

    }

}
