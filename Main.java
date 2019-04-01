package com.TrentRand.coe848;



public class Main {

    public int userProfile = -1;

    static SQLDelegate delegate = new SQLDelegate();
    static Main session = new Main();

    public static void main(String[] args) {

        switch (args[0]) {
            case "register":
                try { delegate.addUser(args[1], Integer.parseInt(args[2], delegate));
                } catch (Exception e) { System.out.println("Please supply a user id no. to signin."); }
                break;

            case "signin":
                try { session.setUserProfile(Integer.parseInt(args[1]));
                } catch (Exception e) { System.out.println("Please supply a user id no. to signin."); }
                break;

            case "venues":
                try {
                    String[] vibes = new String[args.length - 2];
                    for (int i = 1; i < args.length; i++) {
                        vibes[i - 1] = args[i];
                    }
                    Object[] venues = delegate.fetchVenues(vibes, Integer.parseInt(args[args.length]), delegate);

                    if(session.getUserProfile() != -1) {
                        session.withUserProfile(venues);

                    }

                } catch (Exception e) { System.out.println("Failure to parse 'venue' request. Vibes arguments must be supplied as Strings, adventure is supplied as an int (0-9)."); }
                break;

            case "events":
                try {
                    delegate.fetchEvents(delegate);
                } catch (Exception e) { System.out.print("Failure to parse 'events' request... Somehow... \nThis only has one argument, and to get to the try statement that argument must've been properly parsed..."); }

                break;

            case "dump":
                try {
                    delegate.dumpTable(args[1], delegate);
                } catch (Exception e) {

                }
                break;
            case "help":
                System.out.print("register - 'Register a new user. Requires 2 arguments, username (String), Age (int)'\n" +
                        "signin - 'Select a user profile to interact with the app taste profile customizations. Requires 1 argument. userid (int)'\n" +
                        "venues - 'Search the venue database. Requires minimum 2 arguments, vibe (String), vibe2(optional String), adventure (int 0-9)'\n" +
                        "events - 'List upcoming events currently registered in the database. No additional arguments.'");
                break;

            default:
                System.out.println("Unable to parse your request.");
                System.out.println("Available commands include; 'register', 'signin', 'venues', 'events', 'dump', 'help'");
                break;

        }

    }

    public void withUserProfile(Object[] results) {
        //I'm drastically stripping down some user profile settings.
        //In the real world, I would integrate with 'Firebase' which provides an API to add user customization behaviours.

        //I want to ensure any UserID can experience this customization.
        //Since I can't use real world data, (Privacy Policies yada yada) I'm just going to mod the Taste Profiles down to 4 cases.

        switch(session.getUserProfile() % 4) {
            case 0: //These will be late night party-goers who love edm & dance music.

                break;
            case 1: //Anywhere with live music, is where these users want to be.


                break;
            case 2: //We've got money, and we want to feel pampered. No grimy dives for us.


                break;
            case 3: //We won't stay out past 11, don't even try it.


                break;
            default:
                //This almost feels like a "catch" statement. "Help! Math stopped working!"
                break;
        }


    }


    public void printNoctemFeed(Object[] toPrint) {
        //This is used to output the results as a feed to the user.
        //Since we want to power our app through a RESTful JSON API, we're gonna save a .json file of the results as well.



    }





    public int getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(int profileID) {
        userProfile = profileID;
    }
}
