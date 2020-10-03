package com.chandalala.io1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * io1 can either be performed with a byte or a character data and the methods used are pretty much the same
 *
 * Serial or sequential files>> a stream of data that arrives at your program or is sent out from it in a defined order with
 * each piece of data following in sequence
 *
 * Random access file only really apples to files and allows you to jump about the file or within the file retrieving or overriding
 * any data in any location within that file you choose so this is more like how a database program would work in some sort of index showing you
 * nowhere a particular record is within a file so that it can be read without having read through a thousands of the earlier records first
 * for example so a compared sequential data to a stream.
 * Java uses various stream objects with it
 *
 *
 */

public class IOmain {

    private static Locations locations=new Locations();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, String> vocabulary = new HashMap<>();

        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while(true) {
            System.out.println(locations.get(loc).getDescription());

            if(loc == 0) {
                break;
            }

            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are ");
            for(String exit: exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1) {
                String[] words = direction.split(" ");
                for(String word: words) {
                    if(vocabulary.containsKey(word)) {
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(exits.containsKey(direction)) {
                loc = exits.get(direction);

            } else {
                System.out.println("You cannot go in that direction");
            }
        }

    }
}
