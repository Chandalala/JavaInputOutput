package com.chandalala.object_input_output_serialisation;

import java.io.*;
import java.util.*;

//This class pretty much behaves like a map

//The above using Serialisation
/**
 * The above has a disadvantage in writing objects for example if the object has too many fields and that each field will have to be written
 * one by one, as a result the ObjectInputStream and ObjectOutputStream classes can then be used to write objects as single units
 * The process of translating a data structure or object into a file and back is called serialisation
 * In Java when we want to use an object String to write and read the objects of a class, we have to make the class serializable
 * This is done by implementing the serializable interface and it has no methods
 * When you make a class serializable, its recommended that you declare a long field called serialVersionUID, failure to do so will result in the
 * compiler giving us a warning. The field should be private
 * serialVersionUID can be thought of as a version number for the class
 *
 */

public class Locations implements Map<Integer, Location> {

    private static Map<Integer, Location> locations=new HashMap<>();

    public static void main(String[] args) {

        /**
         * FileWriter class writes text to character files using a default buffer size.
         * Some platforms, in particular, allow a file to be opened for writing by only one FileWriter (or other file-writing object) at a time.
         * In such situations the constructors in this class will fail if the file involved is already open.
         * The FileWriter is meant for writing streams of characters. For writing streams of raw bytes, consider using a FileOutputStream.
         */

/*
        FileWriter localFile=null;
        try {
            localFile=new FileWriter("locations.txt");
            for (Location location: locations.values()){
                localFile.write(location.getLocationID()+","+location.getDescription()+"\n");
            }
            localFile.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (localFile != null){
                    localFile.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
*/

        //The above using try with resources
/*
            try ( FileWriter localFile=new FileWriter("locations.txt");
              FileWriter dirFile=new FileWriter("directions.txt") ){

            for (Location location: locations.values()){
                localFile.write(location.getLocationID()+","+location.getDescription()+"\n");
                //System.out.println(location.getLocationID()+","+location.getDescription()+"\n");

                for (String direction:location.getExits().keySet()){
                    dirFile.write(location.getLocationID()+","+direction+","+location.getExits().get(direction)+"\n");
                    //System.out.println(location.getLocationID()+","+direction+","+location.getExits().get(direction)+"\n");
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
*/
        //The above Using bye streams
/*
            try (DataOutputStream locationFile=new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
            for (Location location:locations.values()){
                locationFile.writeInt(location.getLocationID());
                locationFile.writeUTF(location.getDescription());
                System.out.println("Writing location "+location.getLocationID()+" : "+location.getDescription());
                System.out.println("Writing "+(location.getExits().size()-1)+" exits.");
                locationFile.writeInt(location.getExits().size()-1);

                for (String direction:location.getExits().keySet()){
                    if (!direction.equalsIgnoreCase("Q")){
                        System.out.println("\t\t"+direction+","+location.getExits().get(direction));
                        locationFile.writeUTF(direction);
                        locationFile.writeInt(location.getExits().get(direction));
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
*/
        //The above using Serialisation
        /**
         * The above has a disadvantage in writing objects for example if the object has too many fields and that each field will have to be written
         * one by one, as a result the ObjectInputStream and ObjectOutputStream classes can then be used to write objects as single units
         * The process of translating a data structure or object into a file and back is called serialisation
         * In Java when we want to use an object String to write and read the objects of a class, we have to make the class serializable
         * This is done by implementing the serializable interface and it has no methods
         * When you make a class serializable, its recommended that you declare a long field called serialVersionUID, failure to do so will result in the
         * compiler giving us a warning. The field should be private
         * serialVersionUID can be thought of as a version number for the class
         *
         */

        try (ObjectOutputStream locationFile=new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))) {

            for (Location location:locations.values()){
                locationFile.writeObject(location);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    static {

        try (ObjectInputStream locFile=new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            boolean eof=false;
            while (!eof){
                try {
                    Location location= (Location) locFile.readObject();
                    System.out.println("Read location "+location.getLocationID()+":"+location.getDescription());
                    System.out.println("Found "+location.getExits().size()+" exits");
                    locations.put(location.getLocationID(),location);
                }
                catch (EOFException e){
                    eof=true;
                    e.printStackTrace();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }

/*
            try (DataInputStream locFile=new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            boolean eof=false;
            while (!eof){
                try {
                    Map<String,Integer> exits=new LinkedHashMap<>();
                    int locID=locFile.readInt();
                    String description=locFile.readUTF();//read the string
                    int numExits=locFile.readInt();
                    System.out.println("Read location "+locID+" : "+description);
                    System.out.println("Found "+numExits+" exits");

                    for (int i=0; i < numExits; i++){
                        String direction=locFile.readUTF();
                        int destination=locFile.readInt();
                        exits.put(direction,destination);
                        System.out.println("\t\t"+direction+","+destination);
                    }

                    locations.put(locID,new Location(locID,description,exits));
                }
                catch (EOFException e){
                    eof=true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
*/


        //All the below can be done by the above

/*
            try (Scanner scanner=new Scanner(new FileReader("locations.txt"))) {
            scanner.useDelimiter(",");//comma separates number from strings
            while (scanner.hasNextLine()){
                int loc=scanner.nextInt();//read a number from the line
                scanner.skip(scanner.delimiter());//skip the comma
                String description=scanner.nextLine();//read the string
                System.out.println("Imported loc: "+loc+": "+description);
                Map<String, Integer> tempExit=new HashMap<>();
                locations.put(loc,new Location(loc,description,tempExit));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try (FileReader dirFile=new FileReader("directions.txt");
             BufferedReader bufferedReader=new BufferedReader(dirFile);
             Scanner scanner=new Scanner(bufferedReader)){

            scanner.useDelimiter(",");

            while (scanner.hasNextLine()){

//                int loc=scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String dir=scanner.next();
//                scanner.skip(scanner.delimiter());
//                String dest=scanner.nextLine();
//                int destination=Integer.parseInt(dest);

                //The above can be done by the below
                String input=scanner.nextLine();
                String[] data=input.split(",");
                int loc=Integer.parseInt(data[0]);
                String dir=data[1];
                int destination=Integer.parseInt(data[2]);
                /////////////////////////////////////////
                Location location=locations.get(loc);
                location.addExit(dir,destination);
                System.out.println(loc+": "+dir+": "+destination);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
*/

        //The data below we used it to write files the above we will use to read from the files
/*
        Map<String, Integer> tempExit;
        locations.put(0, new Location(0, "You are sitting in front of a computer learning Java",null));

        tempExit = new HashMap<>();
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);
        locations.put(1, new Location(1, "You are standing at the end of a road before a small brick building",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 5);
        locations.put(2, new Location(2, "You are at the top of a hill",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("W", 1);
        locations.put(3, new Location(3, "You are inside a building, a well house for a small spring",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("N", 1);
        tempExit.put("W", 2);
        locations.put(4, new Location(4, "You are in a valley beside a stream",tempExit));

        tempExit = new HashMap<>();
        tempExit.put("S", 1);
        tempExit.put("W", 2);
        locations.put(5, new Location(5, "You are in the forest",tempExit));

*/
    }

    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {

        if (!locations.isEmpty()){
            return true;
        }
        else {
            return false;
        }

//return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {

        if (locations.containsKey(key)){
            return true;
        }
        else {
            return false;
        }

//return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key,value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
