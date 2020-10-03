package com.chandalala.random_access_file_class;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dev on 8/12/2015.
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
 * The Location class below has 3 fields, an int, String and a Map
 * an int and String are primitive types and a Map is an Object, so will a Map be serialised when a location object is written to a file?
 * in this case it will be because, LinkedHashMap implements the serializable interface so the entire contents o the exits field will also be serialised
 * if LinkedHashMap did not implement serializable, we would be responsible for writing the code to store the contents of the file
 * When we want an object to be serializable, all of its fields will also be serializable
 * so you have to keep that in mind when writing your own classes, if a class we want to serialise will have fields from other classes that you write
 * then you need to make sure that those other classes are serializable as well
 */

public class Location implements Serializable {

    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    private long serialVersionUID=1L;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null) {
            this.exits = new LinkedHashMap<>(exits);
        }
        else {
            this.exits = new LinkedHashMap<>();
        }
        this.exits.put("Q", 0);
    }

    public void addExit(String direction, int location) {
        exits.put(direction, location);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return new HashMap<>(exits);
    }

}
