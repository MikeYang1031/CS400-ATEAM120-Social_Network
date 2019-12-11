package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * @author ateam120
 *
 */
public class SocialNetwork implements SocialNetworkADT {
	ArrayList<String> users = new ArrayList<String>();
	private Graph graph;
	private List<String> tempStore;
	private ArrayList<Person> personList;
	private Person centralUser;
	private Queue<Person> queue;

	// constructor
	public SocialNetwork() {

		graph = new Graph();
		recordOperations = new ArrayList<String>();
        personList =  new ArrayList<Person>();
        
	}

//record every operation made by user
	private ArrayList<String> recordOperations;

	//update the network information
	public String updateInfo() {
		String info = "User: "+graph.order()+", Friendship: "+graph.size()
			+", Connected Components: "+ getConnectedComponents();
		return info;
	}
	
	@Override
	public boolean addFriends(String person1, String person2) {
		if(person1 == null || person2 == null) {
			return false;
		}
		if (!users.contains(person1)) {
			addUser(person1);
		}
		if (!users.contains(person2)) {
			addUser(person2);
		}
		Person personone = new Person();
		Person persontwo = new Person();
         for(int i = 0; i < personList.size(); i ++) {
        	 if(personList.get(i).getName().equals(person1)) {
        		 personone = personList.get(i);
        	 }
        	 if(personList.get(i).getName().equals(person2)) {
        		 persontwo = personList.get(i);
        	 }
         }
         personone.addFriendShip(person2, persontwo);
         persontwo.addFriendShip(person1, personone);
         
		graph.addEdge(person1, person2);
        
		recordOperations.add("a " + person1 + " " + person2);

		return true;
	}

	@Override
	public boolean removeFriends(String person1, String person2) {


		if (!graph.getAdjacentVerticesOf(person1).contains(person2)) {
			return false;
		}
		graph.removeEdge(person1, person2);
		recordOperations.add("r " + person1 + " " + person2);
		return true;
	}

	@Override
	public boolean addUser(String person) {
		if(person == null) {
			return false;
		}
		if (users.contains(person)) {

			return false;
		}
		Person user = new Person(person);
		
		personList.add(user);
		users.add(person);
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
	public Set<Person> getFriends(String person) {

	

		List<String> friendsList = graph.getAdjacentVerticesOf(person);
		Set<Person> friendSet = new HashSet<Person>();

		for (String friend : friendsList) {
			//friendSet.add(friend);
		}
		return friendSet;
	}

	@Override
	public Set<String> getMutualFriends(String person1, String person2) {



		List<String> friend1 = graph.getAdjacentVerticesOf(person1);
		System.out.println(friend1);
		List<String> friend2 = graph.getAdjacentVerticesOf(person2);
		System.out.println(friend2);
		if (friend1 == null || friend2 == null) {
			return null;
		}
		Set<String> mFriend = new HashSet<String>();

		for (int i = 0; i < friend1.size(); i++) {
			for (int j = 0; j < friend2.size(); j++) {

				if (friend1.get(i).equals(friend2.get(j))) {
					mFriend.add(friend1.get(i));
				}
			}
		}
		return mFriend;
	}

	@Override
	public List<String> getShortestPath(String person1, String person2) {
        if(users.contains(person1) && users.contains(person2)) {
        	
        }else {
        	return null;
        }
        for(int i = 0; i < personList.size(); i ++) {
        	personList.get(i).visited = false;
        }
		Person Person1 = personList.get(users.indexOf(person1));
		Person Person2  = personList.get(users.indexOf(person2));
		 

		try {
			tempStore = findPathAlgorithm(Person1, Person2);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return tempStore;
	}

//	private Person GetPerson(String person) {
//
//		return graph.getPerson(person);
//	}

	/**
	 * the Dijkstra's Algorithm to find shortest path
	 * 
	 * @param person1
	 * @param person2
	 * @return shortest path
	 * @throws Exception
	 */
	public List<String> findPathAlgorithm(Person person1, Person person2) throws Exception {

		if (person1 == null || person2 == null) {
			throw new NullPointerException("no input,please input person name");
		} else {

			tempStore = new ArrayList<String>(); // initializes list to store shortest path
			Person node = person1; // stores person1 to start
			person1.totalWeight = 0; // sets start total weight to 0

			queue = new LinkedList<Person>(); // initializes queue
			queue.add(person1); // adds person1 to queue

			// checks that person2 exists in graph, otherwise throws exception
			List<String> vertexList = graph.getVertexList();
			if (!vertexList.contains(person2.getName()))
				throw new Exception(person2 + " not exist");

			while (!queue.isEmpty()) {
				node = queue.remove(); // remove first item from queue
				node.visited = true; // item has now been visited

				// if the person2 has been reached break from loop
				if (node.getName().equals(person2.getName())) {
					break;
				}
				dhelper(node); // call helper
			}

			node = person2;

			// copies predecessors of node into list
			while (node.predecessor != null) {
				tempStore.add(node.getName());
				node = node.predecessor;
			}
			tempStore.add(person1.getName()); // adds person1 to store
			Collections.reverse(tempStore); // reverses list so it appears starting at person1, ending at person2

			// if store doesn't contain person2 throw exception
			if (!tempStore.contains(person2.getName())) {
				throw new Exception("Can not find path.");
			}
			
			return tempStore;
		}
	}

	/**
	 * Helper method for dijkstra's algorithm
	 * 
	 * @param person1
	 */
	private void dhelper(Person person1) {

		Person node = null;
		int edgeDistance = -1;
		int newDistance = -1;

		// iterates through list of friends for given person
		for (int i = 0; i < person1.getListOfUsersFriends().size(); i++) {
			node = person1.getListOfUsersFriends().get(i);

			// if person has not been visited yet, check if total weight can be reduced
			if (!node.visited) {
				edgeDistance = node.weight;
				newDistance = person1.totalWeight + edgeDistance;

				// if weight can be reduced do it and add person to queue
				if (newDistance < node.totalWeight) {
					node.totalWeight = newDistance;
					node.predecessor = person1;
					queue.add(node);
				}
			}
		}
	}

	// This class serves to get the number of disconnected groups in the network
	@Override
	   public int getConnectedComponents() {
	      
	      int groupNum =0;
	      ArrayList<String> visitedVertices = new ArrayList<String>();
	      List<String> allNetWorkUsers = getAllUsers();
	      if (graph.order() > 0) {
	        for (String user: graph.getAllVertices()) {
	         
	          if (!visitedVertices.contains(user)) {
	            connectedCalculator(user, visitedVertices);
	            groupNum++;
	          }
	        }
	      }
	      return groupNum;
	    }
	    
	    private void connectedCalculator (String user, ArrayList<String> visitedVertices) {
	      visitedVertices.add(user);
	      for (String userName: graph.getAdjacentVerticesOf(user)) {
	        if (!visitedVertices.contains(userName)) {
	         this.connectedCalculator(userName, visitedVertices);
	        }
	      }
	      
	    }
	    
	    
	  // this is a private user class to get all the user in the graph and 
	  // then store them in the list
	  private List<String> getAllUsers () {
	   
	    Set<String> allUserSet = graph.getAllVertices();
	    ArrayList<String> usersList = new ArrayList<String>();
	    
	    for (String eachUser:allUserSet) {    
	      usersList.add(eachUser);
	   }
	    return usersList;
	  }

	@Override
	public Person getCentralUser() {
		return centralUser;
	}

	@Override
	public void setCentral(String setUser) {

		centralUser.setName(setUser);
		recordOperations.add("s " + setUser);
	}

	@Override
	public void loadFromFile(File file) {
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] args = line.split(" ");
				if (args.length == 2) {
					switch (args[0]) {
					case "a":
						addUser(args[1]);
						System.out.println("added a user " + args[1]);
						break;
					case "r":
						removeUser(args[1]);
						System.out.println("removed a user " + args[1]);
						break;
					case "s":
						setCentral(args[1]);
						System.out.println("Set central: " + args[1]);
						break;
					}
				} else if (args.length == 3) {
					switch (args[0]) {
					case "a":
						addFriends(args[1], args[2]);
						System.out.println("added a friendship between " + args[1] + " and " + args[2]);
						break;
					case "r":
						removeFriends(args[1], args[2]);
						System.out.println("added a friendship between " + args[1] + " and " + args[2]);
						break;
					}
				} else {
					// TODO exception
					System.out.println("This is not a valid command: " + line);
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveToFile(File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			System.out.println(recordOperations.size());
			for (int i = 0; i < recordOperations.size(); i++) {
				String log = recordOperations.get(i);
				// System.out.println(log);
				fileWriter.write(log);
				fileWriter.write("\n");
			}
			fileWriter.close();
		}

		catch (Exception e) {
			System.out.println("Caution: IOException!");
		}

	}
   public void removeAll() {
	   graph = new Graph();
		recordOperations = new ArrayList<String>();
       personList =  new ArrayList<Person>();
   }
}
