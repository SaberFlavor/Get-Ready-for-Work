// add a function that counts if you took the eggs out of the fridge.
// add a function that turns "use fridge" into 'look in fridge'.
// why doesn't "use the coffee maker work?"
// why doesn't "use stove" work?
// add function to front door
// add work
// add keys
// add functions for bathroom and living room
// add tv remote
// add tv station
// add tracker if you put on clothes to remember that you have to take them off to put on clean clothes
// add backpack
// add reminder on your medicine cabinet to take your medicine
// add dynamic hints

import java.util.Scanner;


public class AdventureGame {
    private static boolean hasCatFood = false;
    private static boolean hasClothes = false;
    private static boolean hasCoffee = false;
    private static boolean hasCleanClothes = false;
    private static boolean coffeeInProgress = false;
    private static boolean coffeeReady = false;
    private static boolean hasEggs = false;
    private static boolean hasKey = false;
    private static String currentLocation = "bedroom";
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        intro(scanner);
    
        String choice;
    
        System.out.println("Welcome to the Get Ready for Adventure Game!");
        System.out.println("To play the game, type 'go to [location]', 'look', 'pick up', or 'use'. Type 'quit' to end the game.");
        System.out.println("You are woken up by your phone's alarm. It is 7:30 AM and you need to get ready for work.");
        System.out.println("You get out of bed. You can see your ensuite bathroom and the hallway leading to your living room and kitchen.");
    
        while (true) {
            System.out.println("What do you want to do?");
    
            choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("quit")) {
                System.out.println("Thanks for playing! Goodbye.");
                break;
            }
    
            if (!processCommand(choice, scanner)) {
                continue;
            }
        }
    
        scanner.close();
    }
    
    private static void intro(Scanner scanner) {
        System.out.println("================================================");
        System.out.println("        WELCOME TO THE GET READY GAME!         ");
        System.out.println("================================================");
        System.out.println("It's a beautiful morning, but you overslept!    ");
        System.out.println("Your boss is expecting you at work, and you're  ");
        System.out.println("rushing to get yourself ready to go. Make sure  ");
        System.out.println("to take care of everything before you leave!    ");
        System.out.println("------------------------------------------------");
        System.out.println("Type 'help' for a list of commands at any time.");
        System.out.println("Ready to start the adventure? Press Enter to begin.");
        System.out.println("================================================");
    
        scanner.nextLine(); 
    }
    
    public static boolean processCommand(String command, Scanner scanner) {
        String[] parts = command.toLowerCase().split(" ", 4);
        String action = parts[0];
        String preposition = parts.length > 1 ? parts[1] : "";
        String maybeThe = parts.length > 2 ? parts[2] : ""; 
        String location = parts.length > 3 ? parts[3] : (parts.length > 2 ? parts[2] : ""); 
    
        String object = ""; 
    
        if (parts.length > 4) {
            object = parts[2] + " " + parts[3] + " " + parts[4]; 
        } else if (parts.length > 3) {
            object = parts[2] + " " + parts[3]; 
        } else if (parts.length > 2) {
            object = parts[2]; 
        }
    
        if (action.equals("look") && preposition.equals("at")) {
            if (object.equals("the coffee table") || object.equals("coffee tale")) {
                lookAtCoffeeTable();
                return true;
            } else {
                System.out.println("You can't look at that.");
                return false;
            }
        }
    
        if (action.equals("help")) {
            displayHelp();
            return true;
        } 
        
        if (action.equals("hint")) {
            displayHint();
            return true;
        } 
    
        if (action.equals("pick") && preposition.equals("up")) {
            if (location.isEmpty()) {
                System.out.println("You need to specify what to pick up.");
                return false;
            } else {
                pickUpItem(location);
                return true;
            }
        } else if (action.equals("go") && preposition.equals("to")) {
            if (maybeThe.equals("the")) {
                moveTo(location); 
            } else {
                moveTo(maybeThe); 
            }
            return true;
        } else if (action.equals("open")) {
            if (location.isEmpty()) {
                System.out.println("You need to specify what to open.");
                return false;
            } else {
                openItem(location);
                return true;
            }
        } else {
            switch (action) {
                case "look":
                    look();
                    break;
                case "use":
                    useItem(location);
                    break;
                default:
                    System.out.println("For some reason, you feel lost in your own home. You go back to bed to sleep off the confusion. You wake up to your phone ringing. " +
                            "You answer the phone. UH OH! It's your boss and they don't sound too happy. You should get ready for work!");
            }
        }
        return true;
    }

        private static void openItem(String item) {
            if (item.equals("cabinet") && currentLocation.equals("kitchen")) {
                boolean isCabinetOpen = false;
                                if (!isCabinetOpen) {
                    isCabinetOpen = true;
                    System.out.println("You open the cabinet. Inside, you see a bag of cat food.");
                } else {
                    System.out.println("The cabinet is already open.");
                }
            } else {
                System.out.println("You can't open " + item + " here.");
            }
        
        
    }
    
    private static void displayHelp() {
        System.out.println("===== HELP MENU =====");
        System.out.println("Here are some commands you can use:");
        System.out.println("- 'go to [location]' or 'go to the [location]': Move to a specific location.");
        System.out.println("- 'pick up [item]': Pick up an item from your current location.");
        System.out.println("- 'open [item]': Open an object.");
        System.out.println("- 'look': Look around your current location.");
        System.out.println("- 'use [item]': Use an item you've picked up.");
        System.out.println("- 'quit': Exit the game.");
        System.out.println("=====================");
    }
    
    private static void displayHint() {
        System.out.println("===== HINT =====");
        switch (currentLocation) {
            case "kitchen":
                System.out.println("Hint: King Algi and I could use some food.");
                break;
            case "bedroom":
                System.out.println("Hint: Clean clothes might be helpful for work.");
                break;
            case "living room":
                System.out.println("Hint: ");
                break;
            case "bathroom":
                System.out.println("Hint: Personal hygiene is key before leaving for work.");
                break;
            default:
                System.out.println("Hint: Explore your surroundings to get ready for work.");
        }
        System.out.println("=================");
    }
    
    public static void moveTo(String location) {
        switch (location.toLowerCase()) {
            case "bedroom":
                System.out.println("You walk into your bedroom.");
                currentLocation = "bedroom";
                break;
            case "bathroom":
                System.out.println("You walk into the bathroom.");
                currentLocation = "bathroom";
                break;
            case "hallway":
                System.out.println("You walk into the hallway.");
                currentLocation = "hallway";
                break;
            case "kitchen":
                System.out.println("You walk into the kitchen.");
                currentLocation = "kitchen";
                break;
            case "living room":
                System.out.println("You walk into the living room.");
                currentLocation = "living room";
                break;
            case "front door":
                System.out.println("You approach the front door. Are you ready to leave?");
                currentLocation = "front door";
                break;
            default:
                System.out.println("You can't go to " + location + ".");
        }
    }

    private static void lookAtCoffeeTable() {
        if (currentLocation.equals("living room")) {
            System.out.println("The coffee table is a bit cluttered. On it, you see the house key and a mug ring from yesterday's coffee.");
        } else {
            System.out.println("There is no coffee table here to look at.");
        }
    }    

    public static void pickUpItem(String item) {
        switch (item.toLowerCase()) {
            case "clothes":
                if (!hasClothes) {
                    hasClothes = true;
                    System.out.println("You pick up your old dirty clothes. They smell a bit.");
                } else {
                    System.out.println("You are already holding your old dirty clothes.");
                }
                break;
            case "coffee":
                if (coffeeReady) {
                    hasCoffee = true;
                    coffeeReady = false; // Coffee is now picked up
                    System.out.println("You pick up the cup of coffee. It's hot and fresh.");
                } else {
                    System.out.println("The coffee is not ready yet.");
                }
                break;
            case "clean clothes":
                if (!hasCleanClothes) {
                    hasCleanClothes = true;
                    System.out.println("You pick up your clean clothes from the dresser. They are fresh and ready to wear.");
                } else {
                    System.out.println("You already have the clean clothes.");
                }
                break;
            case "cat food":
                if (currentLocation.equals("kitchen")) {
                    hasCatFood = true;
                    System.out.println("You pick up Algi's cat food. The bag crinkles when you pick it up. You can hear Algi trotting down the hallway to find you.");
                } else {
                    System.out.println("There is no cat food here to pick up.");
                }
                break;
                case "eggs":
                if (currentLocation.equals("kitchen")) {
                    hasEggs = true;
                    System.out.println("You pick up some eggs from the fridge. They're ready to be cooked.");
                } else {
                    System.out.println("There are no eggs here to pick up.");
                }
                break;
            case "key":
                if (currentLocation.equals("living room")) {
                 if (!hasKey) {
                    hasKey = true;
                    System.out.println("You pick up the key from the coffee table. It might be useful!");
                  } else {
                    System.out.println("You already have the key.");
                 }
                } else {
                    System.out.println("There is no key here to pick up.");
                 }
                break;
            default:
                System.out.println("There is no " + item + " to pick up.");
        }
    }

    public static void useItem(String target) {
        switch (target.toLowerCase()) {
            case "clothes":
                if (hasClothes) {
                    System.out.println("You put on your old dirty clothes. Surely no one will notice.");
                } else {
                    System.out.println("You don't have any clothes to use.");
                }
                break;
            case "coffee":
                if (hasCoffee) {
                    System.out.println("You drink the coffee and feel more awake.");
                } else {
                    System.out.println("You don't have any coffee to use.");
                }
                break;
            case "clean clothes":
                if (hasCleanClothes) {
                    System.out.println("You put on your clean clothes. You feel fresh and ready for the day.");
                } else {
                    System.out.println("You don't have any clean clothes to use.");
                }
                break;
                case "cat food":
                if (hasCatFood && currentLocation.equals("kitchen")) {
                    System.out.println("You feed Algi the cat. Algi purrs happily.");
                    hasCatFood = false; 
                } else if (!hasCatFood) {
                    System.out.println("You don't have any cat food to feed Algi.");
                } else {
                    System.out.println("Algi isn't here to be fed.");
                }
                break;
            
            case "coffee maker":
                useCoffeeMaker();
                break;
            case "stove":
                if (hasEggs) {
                    System.out.println("You cook the eggs on the stove. They smell delicious and are ready to eat.");
                    hasEggs = false; // Eggs are used up
                } else {
                    System.out.println("You don't have any eggs to cook.");
                }
                break;
            case "sink":
                System.out.println("You wash your hands in the sink. They feel clean and refreshed.");
                break;
            case "fridge": // kitchen uses are borken for some reason
                useFridge();
                break;
            case "dresser":
                useDresser();
                break;
            case "nightstand":
                useNightstand();
                break;
            default:
                System.out.println("You can't use the " + target + " here.");
        }
    }

    public static void useCoffeeMaker() {
        if (currentLocation.equals("kitchen")) {
            if (!coffeeInProgress && !coffeeReady) {
                coffeeInProgress = true;
                System.out.println("You start the coffee maker. It will take a few minutes to brew.");
                // Not actually timed
                coffeeInProgress = false;
                coffeeReady = true;
                System.out.println("The coffee is ready. You can pick it up now.");
            } else if (coffeeInProgress) {
                System.out.println("The coffee is still brewing. Please wait.");
            } else {
                System.out.println("The coffee is already ready. You can pick it up.");
            }
        } else {
            System.out.println("There is no coffee maker here.");
        }
    }

    public static void useFridge() {
        if (currentLocation.equals("kitchen")) {
            System.out.println("You look inside the fridge and see some eggs. Your stomach starts to growl. Better make breakfast.");
        } else {
            System.out.println("There is no fridge here.");
        }
    }

    public static void useDresser() {
        System.out.println("You open the dresser and see your neatly folded clean clothes. They look fresh and ready to wear.");
    }

    public static void useNightstand() {
        System.out.println("You open the drawer on your nightstand. It's empty.");
    }


    public static void look() {
        switch (currentLocation) {
            case "bedroom":
                System.out.println("You take a moment to look around your bedroom. You took great pride in decorating your apartment when you first moved in. "+
                                    "Many of your friends have said it reminds them of their grandparents house.");
                System.out.println("The sunlight peeks through the blinds, letting you know it's morning. You see your nightstand beside your bed,");
                System.out.println("your dresser across the room, " +
                                   "and the clothes you wore yesterday scattered across the floor. Your cat Algi is still fast asleep on your pillow. There is nothing out of the ordinary. " +
                                   "You need to get ready.");
                break;
            case "bathroom":
                System.out.println("You take a look around your bathroom. It's small but functional. You see a sink, a mirror, and a shower."+
                "There is a small yellow sticky note on your mirror");
                break;
            case "hallway":
                System.out.println("You look around the hallway. It's dimly lit and leads to the rest of the apartment. You have a few photos framed");
                break;
            case "kitchen":
                System.out.println("You look around the kitchen. It's equipped with a sink, stove, refrigerator, cabinet, and a coffee maker. " +
                                   "There's a bowl of fruit on the counter. There is a small table in the corner with two chairs." +
                                   "Algi's food dish is on the ground next to the table so you can eat together.");
                System.out.println("You and Algi could use some breakfast.");
                break;
            case "living room":
                System.out.println("You look around the living room. The room is well lit by the morning sun shining through the window. There's a comfortable couch that you found on a neighbor's curb, "+
                                   "a coffee table that you found at an estate sale, and a TV you bought with your first paycheck."+
                                   "You can see the front door from here.");
                break;
            case "front door":
                System.out.println("You are standing by the front door. It's your gateway to the outside world, but are you ready to leave?");
                 break;
            default:
                System.out.println("You look around but don't see anything of interest.");
        }
    }
}

