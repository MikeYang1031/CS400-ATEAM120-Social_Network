package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
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
    public Set<String> getFriends(String person) {
      List<String> friendsList = graph.getAdjacentVerticesOf(person);
      Set<String> friendSet = new HashSet<String>();
      
      for (String friend: friendsList) {
        friendSet.add(friend);
    }
        return friendSet;
    }

    @Override
    public Set<Person> getMutualFriends(String person1, String person2) {
        List<String> friend1 = graph.getAdjacentVerticesOf(person1);
        List<String> friend2 = graph.getAdjacentVerticesOf(person2);
        
        for (int i = 0 ; i < friend1.size(); i++) {
            for (int j = 0; j < friend2.size(); j++){
               
            }
        }
        intersection(friend1,friend2);
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
    public boolean setCentral(String person) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public void loadFromFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] args = line.split(" ");
                if(args.length == 2) {
                    switch(args[0]) {
                        case "a":
                            addUser(args[1]);
                        case "r":
                            removeUser(args[1]);
                        case "s":
                            setCentral(args[1]);
                    }
                }else if(args.length == 3) {
                    switch(args[0]) {
                        case "a":
                            addFriends(args[1],args[2]);
                        case "r":
                            removeFriends(args[1],args[2]);
                    }
                }else {
                    // TODO exception
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } 
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
