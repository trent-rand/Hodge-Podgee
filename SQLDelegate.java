package com.TrentRand.coe848;

import java.sql.*;
import java.util.ArrayList;

public class SQLDelegate {

    Connection conn = null;



    public boolean establishSQLConnection() { //Useful to establish an initial connection, and double check our connection is still valid.
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:/home/Noctem.db");

                if (conn == null) {
                    throw new Exception("Databse connection returned null after initial attempt..."); //Whoops! Our connection wasn't able to establish properly.
                }

            } catch (Exception e) {
                System.out.println("Error encountered while opening SQLite connection...");
                e.printStackTrace();
                return false;
            }

            return true;

        } else {

            return true; // Skip to here if connection is already live.
        }
    }


    public Object[] fetchVenues(String[] vibes, int adventure, SQLDelegate self) {

        ArrayList<Venue> toReturn = new ArrayList<>();
        if(self.establishSQLConnection()) { //Let's double check our connection.
            try {
                String request = "SELECT * FROM Venues WHERE Vibes LIKE ";
                for (String vibe : vibes) {
                    request = request + "'%" + vibe + "%' OR "; //Iterate across the vibes the user requested and begin creating our SQL request statement.
                }
                request = request.substring(0, (request.length() - 4)); //Remove the final "OR" statement we included from above.
                request = request + " AND Adventure > " + adventure + ";"; //Finalize the user definition of the request.

                //System.out.println(request); //Useful during debugging to ensure that the request was properly constructed.

                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(request);

                while( results.next() ) {
                    System.out.println(results.getRow()); //Debug use only. Let's see what kinda garbage our database decided to return...
                    Venue temp = new Venue();
                    try {
                        temp.setName(results.getString("Name"));
                        temp.setAddress(results.getString("Address"));
                        temp.setHOO(results.getString("HoursOfOperation"));
                        temp.setVibes(results.getString("Vibes"));
                        temp.setAdventurous(results.getInt("Adventure"));
                        temp.setPrice(results.getInt("Price"));

                        toReturn.add(temp);
                    } catch (Exception e) { continue; } //Maybe out data is tainted... This doesn't have to ruin the request, just omit the offending result.
                }

                return toReturn.toArray();
            } catch (Exception e) { return null; }
        } else {
            return null;
        }
    }

    public Object[] fetchEvents(SQLDelegate self) {
        ArrayList<Events> toReturn = new ArrayList<>();
        if(establishSQLConnection()) {




        } else {
            return null; //We should hopefully never get here.
        }

        return toReturn.toArray();
    }

    public void addUser(String username, int age, SQLDelegate self) {
        if(self.establishSQLConnection()) { //Let's double check our connection.
            try {
                String request = "INSERT INTO Users(Username, Age) VALUES (" + username + "," + age + ");";
                Statement statement = conn.createStatement();
                statement.executeUpdate(request);




            } catch (Exception e) {
                System.out.println("Error adding new user to database.");
                e.printStackTrace();
                return;
            }
        } else {
            return;
        }

    }


    public void dumpTable(String tableName, SQLDelegate self) {
        ArrayList<Object> toReturn = new ArrayList<>();
        if(self.establishSQLConnection()) { //Let's double check our connection.
            try {
                String request = "SELECT * FROM " + tableName + ";";
                Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery(request);

                while(results.next()) {
                    //This is bad manners, but we're just gonna print each row returned as a String.
                    //No need to do more when we're just dumping content into the shell/terminal.
                    System.out.println(results.getRow());

                }


            } catch (Exception e) {
                System.out.println("Error adding new user to database.");
                e.printStackTrace();

            }
        } else {
            return null;
        }


        return toReturn.toArray();
    }

}
