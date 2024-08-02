Martian Land Map: File Handling

Martian Land Map

A Martian Land Map is shown below. The map can be initialized in three ways –

Load a file given in command line arguments.

Load from a default file

Load from a saved file

##############################
#.....P.............E....E...#
#..CC........ZX.........TT...#
#..GD...................LR...#
##############################
Command Line Args
While running the program we can provide a command line argument that loads the martian map. Eg –

javac *.java
java MarsHabitatApplication --f resources/someInputFile
Note that unix/linux systems can handle filename with any (.txt, .doc) or no extension as well. As long as you have valid content in the file, Java will be able to read it without worrying about the file extension. There are four possible scenarios in this case.

File is not found – Java will throw a FileNotFoundException. You must catch it, print the appropriate error and terminate the program.

$java MarsHabitatApplication --f tests/abc
         __
 _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ /\ )_ / _\
       /\__/\ \__O (_COMP90041
      (--/\--)    \__/
      _)(  )(_
     `---''---`
 /$$      /$$ /$$                    /$$                           /$$      /$$                              
| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              
| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$
| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/
| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \ $$| $$  \ $$      | $$  $$$| $$  /$$$$$$$| $$  \__/|  $$$$$$ 
| $$\  $ | $$| $$ \____  $$\____  $$| $$| $$  | $$| $$  | $$      | $$\  $ | $$ /$$__  $$| $$       \____  $$
| $$ \/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \/  | $$|  $$$$$$$| $$       /$$$$$$$/
|__/     |__/|__/|_______/|_______/ |__/ \______/ |__/  |__/      |__/     |__/ \_______/|__/      |_______/ 

File Not Found, aborting mission.
$

InvalidFileException - If all the rows in the file don't match in length or the boundary is not defined with ‘#’, throw an error message and terminate the program.

$java MarsHabitatApplication --f resources/default.in 
         __
 _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ /\ )_ / _\
       /\__/\ \__O (_COMP90041
      (--/\--)    \__/
      _)(  )(_
     `---''---`
 /$$      /$$ /$$                    /$$                           /$$      /$$                              
| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              
| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$
| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/
| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \ $$| $$  \ $$      | $$  $$$| $$  /$$$$$$$| $$  \__/|  $$$$$$ 
| $$\  $ | $$| $$ \____  $$\____  $$| $$| $$  | $$| $$  | $$      | $$\  $ | $$ /$$__  $$| $$       \____  $$
| $$ \/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \/  | $$|  $$$$$$$| $$       /$$$$$$$/
|__/     |__/|__/|_______/|_______/ |__/ \______/ |__/  |__/      |__/     |__/ \_______/|__/      |_______/ 

Invalid file content, Aborting mission.
$

UnknownEntityException - In the file, there exists a symbol that doesn't match our specified symbols. You should throw an error message and terminate the program.

$java MarsHabitatApplication --f resources/default.in
         __
 _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ /\ )_ / _\
       /\__/\ \__O (_COMP90041
      (--/\--)    \__/
      _)(  )(_
     `---''---`
 /$$      /$$ /$$                    /$$                           /$$      /$$                              
| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              
| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$
| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/
| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \ $$| $$  \ $$      | $$  $$$| $$  /$$$$$$$| $$  \__/|  $$$$$$ 
| $$\  $ | $$| $$ \____  $$\____  $$| $$| $$  | $$| $$  | $$      | $$\  $ | $$ /$$__  $$| $$       \____  $$
| $$ \/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \/  | $$|  $$$$$$$| $$       /$$$$$$$/
|__/     |__/|__/|_______/|_______/ |__/ \______/ |__/  |__/      |__/     |__/ \_______/|__/      |_______/ 

An unknown item found in Martian land. Aborting mission.
$


You have successfully loaded the file. In this case, you must print 

The layout of the Martian Land Map

the HabitabilityStatus - The HabitabilityStatus contains a list of all the entities and their count (excluding the Space Robots, Space Rovers, Martian Animals) and a total HabitabilityScore. Look at the HabitabilitySection to see more about how to calculate the score.

and then the main menu. 

$java MarsHabitatApplication --f resources/v1.in
         __
 _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ /\ )_ / _\
       /\__/\ \__O (_COMP90041
      (--/\--)    \__/
      _)(  )(_
     `---''---`
 /$$      /$$ /$$                    /$$                           /$$      /$$                              
| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              
| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$
| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/
| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \ $$| $$  \ $$      | $$  $$$| $$  /$$$$$$$| $$  \__/|  $$$$$$ 
| $$\  $ | $$| $$ \____  $$\____  $$| $$| $$  | $$| $$  | $$      | $$\  $ | $$ /$$__  $$| $$       \____  $$
| $$ \/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \/  | $$|  $$$$$$$| $$       /$$$$$$$/
|__/     |__/|__/|_______/|_______/ |__/ \______/ |__/  |__/      |__/     |__/ \_______/|__/      |_______/ 

Here is a layout of Martian land.

##############################
#.....P.............E....E...#
#..CC........ZX.........TT...#
#..GD...................LR...#
##############################

Habitability Status
======================
POTATO = 1
EUCALYPTUS = 2
LILY = 1
ROSE = 1
COW = 2
GOAT = 1
DOG = 1
TOMATO = 2

Total Habitability Score: 37
Please enter
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 


Load from files
If the file is not given as command line arguments then you will need to print a prompt for the user to either start with a default file or provide a file path.

         __
 _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ /\ )_ / _\
       /\__/\ \__O (_COMP90041
      (--/\--)    \__/
      _)(  )(_
     `---''---`
 /$$      /$$ /$$                    /$$                           /$$      /$$                              
| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              
| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$
| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/
| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \ $$| $$  \ $$      | $$  $$$| $$  /$$$$$$$| $$  \__/|  $$$$$$ 
| $$\  $ | $$| $$ \____  $$\____  $$| $$| $$  | $$| $$  | $$      | $$\  $ | $$ /$$__  $$| $$       \____  $$
| $$ \/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \/  | $$|  $$$$$$$| $$       /$$$$$$$/
|__/     |__/|__/|_______/|_______/ |__/ \______/ |__/  |__/      |__/     |__/ \_______/|__/      |_______/ 

Please enter
[1] to load Martian map from a file
[2] to load default Martian map
> 


If you select 1 then ask the user to provide a file path. Once a path is provided one of the 4 scenarios mentioned can happen - FileNotFoundException, InvalidFileException, UnknownEntityException or a successful file is loaded as shown below where you print Habitability stats followed by the main menu.

Please enter
[1] to load Martian map from a file
[2] to load default Martian map
> 1
Enter a file name to setup Martian Land Map
> resources/v1.in
Here is a layout of Martian land.

##############################
#............................#
#............ZX..............#
#............................#
##############################

Habitability Status
======================
No Record found.
Please enter
[1] to move Space Robot
[2] to move Space Rover
[3] to move Martian animals
[4] to print the current habitability stats
[5] to print the old habitability stats
[6] to exit
>


For all the other exceptions you can refer to the previous section for the error message. Here is a sample output in case of file not found.

Please enter
[1] to load Martian map from a file
[2] to load default Martian map
> 1
Enter a file name to setup Martian Land Map
> abcdef
File Not Found, aborting mission.


Incase, you select option 2, a default file has been given to you already that needs to be loaded.

         __
 _(\    |@@|
(__/\__ \--/ __
   \___|----|  |   __
       \ }{ /\ )_ / _\
       /\__/\ \__O (_COMP90041
      (--/\--)    \__/
      _)(  )(_
     `---''---`
 /$$      /$$ /$$                    /$$                           /$$      /$$                              
| $$$    /$$$|__/                   |__/                          | $$$    /$$$                              
| $$$$  /$$$$ /$$  /$$$$$$$ /$$$$$$$ /$$  /$$$$$$  /$$$$$$$       | $$$$  /$$$$  /$$$$$$   /$$$$$$   /$$$$$$$
| $$ $$/$$ $$| $$ /$$_____//$$_____/| $$ /$$__  $$| $$__  $$      | $$ $$/$$ $$ |____  $$ /$$__  $$ /$$_____/
| $$  $$$| $$| $$|  $$$$$$|  $$$$$$ | $$| $$  \ $$| $$  \ $$      | $$  $$$| $$  /$$$$$$$| $$  \__/|  $$$$$$ 
| $$\  $ | $$| $$ \____  $$\____  $$| $$| $$  | $$| $$  | $$      | $$\  $ | $$ /$$__  $$| $$       \____  $$
| $$ \/  | $$| $$ /$$$$$$$//$$$$$$$/| $$|  $$$$$$/| $$  | $$      | $$ \/  | $$|  $$$$$$$| $$       /$$$$$$$/
|__/     |__/|__/|_______/|_______/ |__/ \______/ |__/  |__/      |__/     |__/ \_______/|__/      |_______/ 

Please enter
[1] to load Martian map from a file
[2] to load default Martian map
> 2
Here is a layout of Martian land.

##############################
#.....P.............E....E...#
#..CC........ZX.........TT...#
#..GD...................LR...#
##############################

Habitability Status
======================
POTATO = 1
EUCALYPTUS = 2
LILY = 1
ROSE = 1
COW = 2
GOAT = 1
DOG = 1
TOMATO = 2

Total Habitability Score: 37
Please enter
[1] to move Space Robot
[2] to move Space Rover
[3] to move Martian animals
[4] to print the current habitability stats
[5] to print the old habitability stats
[6] to exit
> 

Main Menu

Main Menu

The main menu is printed after the file is loaded successfully.

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
>  

The menu items are discussed in a different order so that you can gather a better understanding.

Menu Items 1 (Move Space Robot)  & 2 (Move Space Rover)
There will always be at least 1 Space Robot and 1 Space Rover be present in all the maps loaded.

On selecting any of these we can make the space robot or space rover move. This will lead to the move menu. The move menu is discussed in another section in detail. However, there could be more than one space robot or space rover present. You need to select one to move one. See the sample output here. 

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 1 
There are 2 Space Robot found. Select 
[1] for Space Robot at position (13, 2) 
[2] for Space Robot at position (9, 3) 

>  

The positions are (x,y) coordinates. The top left corner is specified as (0,0).  

Tip: This is similar to specifying array indices. The array indices start from 0,0. The first index specifies a y because it represents rows that goes vertically, and the second index specify an x coordinate representing column growing horizontally. 

 With the default map loaded, you can see there are two Space Robots (Z)  

############################## 
#.....P.............E....E...# 
#..CC........ZX.........TT...# 
#..GD....Z..............LR...# 
############################## 
in the third row (i.e. y coordinate is 2) and 14th column (i.e. x coordinate 13) 

In the fourth row (i.e. y coordinate is 3) and 10th column (i.e. x coordinate 9) 

In this case you can see there is only one Rover and no Martian animal. If we select menu item 2 to move the Space Rover below sample output (without grammatical consideration for ease of implementation) should be shown  

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 2 
There are 1 Space Rover found. Select 
[1] for Space Rover at position (14, 2) 
>  

Once you have selected a number for space robot/space rover/martian animal in menu 3, you can show the move menu like this. 

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 2 
There are 1 Space Rover found. Select 
[1] for Space Rover at position (14, 2) 
> 1 
SpaceRover can move in the following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

Menu item 3  (Move Martian Animal)
In this case, assume that at least one Space Robot and one Space Rover are present in the various maps loaded but there may or may not be a Martian animal present in those maps. In case no Martian Animal Found and option 3 is selected then you must print an error and return to the main menu.

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 3 
No martial animal found to move. 
Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
>  

In case a martian animal is found you can further load the move menu and make the martian animal move. Move menu is discussed in a separate section 

Menu item 4 (Print Habitability Status of current map loaded)
Once the map is loaded you may or may not get some habitability stats based on the entities in the map. This will also lead you to a score. Also, when your space entities perform some actions, it will increase or decrease your habitability score as well. You must keep track of it and update the HabitabilityStatus for various actions performed. For menu item 4, you must print the current updated habitability status.  

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 4 
Habitability Status 
====================== 
POTATO = 1 
EUCALYPTUS = 2 
LILY = 1 
ROSE = 1 
COW = 2 
GOAT = 1 
DOG = 1 
TOMATO = 2 
 
Total Habitability Score: 37 
Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
>

Menu Item 6 (Exit the Program)
Exit the program gracefully without using any System.exit(0). Before exiting the program you must  

ask the user to enter a filename where you can save the Martian land map. If the file is not found, you must create one by yourself. If the file is found, then you must overwrite the file. 

Save the habitability status of the current Martian land map as a log entry in a file name habitability.log. If this file is not created earlier, your Java code should automatically create it. When you write into the file, append the status to the file. 

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 6 
Enter a filename for saving Martian Land Map 
> resources/v1.out 
Terminating the mission for now. See you next time. 

Output of v1.out   

############################## 
#.....P.............E....E...# 
#..CC........ZX.........TT...# 
#..GD...................LR...# 
############################## 
 The output of the habitability log file is up to you to maintain. You must keep appending the log and then show the output in menu item 5. 

 In case you cannot create a file you must print these errors and exit- 

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 6 
Enter a filename for saving Martian Land Map 
> 
Cannot create file for Martian Land Map. 
Terminating the mission for now. See you next time. 

Or in case of habitability log the file 

Cannot create file for Habitability Status Log. 
 In case you found the file but are unable to write to the file, print these errors and exit 

Cannot write Martian Land Map to the file. 
 Or  

Cannot write Habitability Status Log in a file.
Menu Item 5 (Print Habitability Status from the logs)
Since you can load and update the same Martian Map or different Martian Maps with each run of the program, we want to save the habitability status each time just to record our log of work. This file can be loaded either  

A command line argument is passed 

$ javac *.java 
$ java MarsHabitatApplication --f resources/v1.in --l resources/habitability.log 
A default file named habitability.log is found in the resources folder.  

 Once that is found you can print the logs with the number of runs mentioned. Here is a sample output. 

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 5 
Program Run :1 
Habitability Status 
====================== 
EUCALYPTUS = 2 
LILY = 1 
ROSE = 1 
COW = 2 
GOAT = 1 
DOG = 1 
TOMATO = 2 
 
Total Habitability Score: 35 
Program Run :2 
Habitability Status 
====================== 
POTATO = 1 
EUCALYPTUS = 2 
LILY = 1 
ROSE = 1 
COW = 2 
GOAT = 1 
DOG = 1 
TOMATO = 2 
 
Total Habitability Score: 37 
Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
>  

Move Menu
Move Menu
For menu item 1,2,3 we can move the different entities. Move Menu is shown again below -

There are 2 Space Robot found. Select 
[1] for Space Robot at position (13, 2) 
[2] for Space Robot at position (9, 3) 
> 1 
SpaceRobot can move in the following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 

Once you select a direction one of these things will happen  

You can move to the next location given it is empty and perform several actions and then print the layout now. 

You cannot move to the next location because it is not empty but you can perform several actions 

You may have reached the boundary and trying to move to the boundary location in that case you shall raise an exception and print the appropriate error message 

SpaceRobot can move in the following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 1 
Invalid Location, Boundary reached. 

This is the same for all three space entities. 

1. Space Robot Move 
Case 1: Space to move is empty 
If the Space Robot can move to the selected location and it is empty, then they will move. After moving, if the left location is still empty then they can perform the below actions  

Plant a tree 

Rear a cattle 

Go back to the previous menu 

If not empty return to the move menu. An example is given below. 

For a map like this look at the third row X.Z. Space Robot Z can move to the west (left direction) and the map will become XZ, but then the left of the new position is not empty, so it simply moves to the new location, prints the layout and then prints the move menu again 

############################## 
#.....P.............E....E...# 
#..CC.........X.Z.......TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC.........XZ........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  


Sub Menu 
In case the left of the new position is empty, the Space robot can perform the actions. The sample output is shown here 

SpaceRobot can move in the following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 1 
Please select 
[1] to plant a tree 
[2] to rear cattle 
[0] to go back to previous menu 
>  

Plant something 
If the user select to plant a tree, you need to prompt them to plant a plant/vegetable type. Here is a sample output 

SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 1 
Please select 
[1] to plant a tree 
[2] to rear cattle 
[0] to go back to previous menu 
> 0 
Here is a layout of Martian land. 
 
############################## 
#.....P......Z......E....E...# 
#..CC.........X.........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Please select 
[1] to plant a tree 
[2] to rear cattle 
[0] to go back to previous menu 
> 1 
Let's Plant something 
[1] to plant a potato 
[2] to plant a tomato 
[3] to plant an onion 
[4] to plant an apple tree 
[5] to plant a banana tree 
[6] to plant a lily 
[7] to plant a rose 
[8] to plant a eucalyptus tree 
[0] to go back to previous menu 
1 
A Potato has been planted. 
Please select 
[1] to plant a tree 
[2] to rear cattle 
[0] to go back to previous menu 
> 0 
Here is a layout of Martian land. 
 
############################## 
#.....P....PZ.......E....E...# 
#..CC.........X.........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

Once a plant is planted, you can assume the next input will be to go back to main menu.

Add an animal 
 If the user select to rear cattle, you need to prompt them to add an animal. Here is a sample output 

Here is a layout of Martian land. 
 
############################## 
#.....P....P........E....E...# 
#..CC...Z.....X.........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Please select 
[1] to plant a tree 
[2] to rear cattle 
[0] to go back to previous menu 
> 2 
Let's add some cattle 
[1] to add a goat 
[2] to add a sheep 
[3] to add cow 
[4] to add a dog 
[0] to go back to previous menu 
2 
A Sheep has been added. 
Please select 
[1] to plant a tree 
[2] to rear cattle 
[0] to go back to previous menu 
> 0 
Here is a layout of Martian land. 
 
############################## 
#.....P....P........E....E...# 
#..CC.SZ......X.........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>

Once a animal is added, you can assume the next input will be to go back to main menu.

Case 2: Space to move is occupied 
If it is not then there are three scenarios - 

If it is a plant then you can water a plant. Watering the plant increases habitability status by 1  

Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC.........X.........TT...# 
#..GD....Z.............ZLR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 3 
Do you want to water the plant?Enter Y for yes, N for No 
> abc 
Invalid Command 
Do you want to water the plant?Enter Y for yes, N for No 
> Y 
You watered a plant. It will grow 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC.........X.........TT...# 
#..GD....Z.............ZLR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

 If it is an earth animal then you can feed the animal. Feeding the cattle increases the habitability status by 2  by 1. 

Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC.........X.........TT...# 
#..GDZ.................ZLR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Do you want to feed the animals?Enter Y for yes, N for No 
> N 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC.........X.........TT...# 
#..GDZ.................ZLR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Do you want to feed the animals?Enter Y for yes, N for No 
> Y 
You have fed the cattle. It will grow 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC.........X.........TT...# 
#..GDZ.................ZLR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>

If it is something else then you can show an error message and go back to previous menu  

Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........ZX.........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 3 
You cannot move to this location. 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........ZX.........TT...# 
#..GD....Z..............LR...# 
############################## 
 
SpaceRobot can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

Space Rover Move 
Case 1: Space to move is empty 
If the space is empty then space rover will move there. 

Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z.X..@@....TT...# 
#..GD....Z........*.....LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 3 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z..X.@@....TT...# 
#..GD....Z........*.....LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

Case 2: Space to move is occupied 
If the space is occupied by a mineral or a plain rock then it will collect it or destroy it. See the sample output there 

Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z..X.@@....TT...# 
#..GD....Z........*.....LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 3 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z...X@@....TT...# 
#..GD....Z........*.....LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 3 
We found a plain rock, Rover will destroy it now. 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z....X@....TT...# 
#..GD....Z........*.....LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 4 
We found a mineral, Rover will collect it now. 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z.....@....TT...# 
#..GD....Z........X.....LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 


If the space is occupied by something else then it will print an error message and go back to move menu. 

Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z.....@....TT...# 
#..GD....ZX.............LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
You cannot move to this location. 
Here is a layout of Martian land. 
 
############################## 
#.....P.............E....E...# 
#..CC........Z.....@....TT...# 
#..GD....ZX.............LR...# 
############################## 
 
SpaceRover can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

Martian Animal Move 
Case 1: Space to move is empty 
If the space is empty then Martian Animals named Heebie, Jeebie can move around. 

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 3 
There are 3 Martian animal found. Select 
[1] for HEEBIE at position (12, 1) 
[2] for JEEBIE at position (13, 3) 
[3] for JEEBIE at position (21, 3) 
> 2 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z..J.....*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z.J......*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 


Case 2: Space to move is occupied 
If the space is occupied with a plant - The plant will be eaten by the Martian Animal and then the plant is removed from the layout. The habitability score decreases and the martian Animal has taken the plant’s place 

Here is a layout of Martian land. 
 
############################## 
#.....P.J...H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Here is a layout of Martian land. 
 
############################## 
#.....PJ....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
The plantation are eaten by the martian animals. 
Here is a layout of Martian land. 
 
############################## 
#.....J.....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

If a space is occupied with an animal other than dog 

Here is a layout of Martian land. 
 
############################## 
#....J......H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 4 
Here is a layout of Martian land. 
 
############################## 
#...........H.......E....E...# 
#..CCJ.......ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
The cattle are killed by the martian animals. 
Here is a layout of Martian land. 
 
############################## 
#...........H.......E....E...# 
#..CJ........ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  


If a space is occupied with dog - Martian Animal fights with the dog. The dog is initialised with a health of 10. The martian animal is initialised with a health of 15. When they fight their health goes down by 2. When a dog is fed by Space Robot, its health goes up by 1. When a Martian Animal kills a cattle or eats a plant its health goes up by 2. During the fight, when one of them has health less than equal to 0, their fight ceases. Once the fight is stopped, one of the entity is killed and is removed from the Martian Land. The habitability score drops by 5 if a dog is killed and increases by 7 if a martian animal is killed. See the sample output below.

Please enter 
[1] to move Space Robot 
[2] to move Space Rover 
[3] to move Martian animals 
[4] to print the current habitability stats 
[5] to print the old habitability stats 
[6] to exit 
> 3 
There are 4 Martian animal found. Select 
[1] for HEEBIE at position (12, 1) 
[2] for HEEBIE at position (7, 2) 
[3] for JEEBIE at position (13, 3) 
[4] for JEEBIE at position (21, 3) 
> 2 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CCDH......ZX...@@....TT...# 
#..GD....Z...J....*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Martian animal and Dog has entered a fight 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 8 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 13 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 6 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 11 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 4 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 9 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 2 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 7 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 0 
Dog died 
 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CC.H......ZX...@@....TT...# 
#..GD....Z...J....*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 4 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD.H..Z...J....*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GDH...Z...J....*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 2 
Martian animal and Dog has entered a fight 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 8 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 5 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 6 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 3 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 4 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: 1 
Martian Animal attacked dog. Health of dog reduced by 2, Present Health: 2 
Dog attacked Martian Animal. Martian Animal's health reduced by 2, Present Health: -1 
Martian Animal died 
 
Here is a layout of Martian land. 
 
############################## 
#.....P.....H.......E....E...# 
#..CC........ZX...@@....TT...# 
#..GD....Z...J....*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

If a space is occupied with anything else  

Here is a layout of Martian land. 
 
############################## 
#...........H.......E....E...# 
#..C.....J...ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
> 4 
You cannot move to this location. 
Here is a layout of Martian land. 
 
############################## 
#...........H.......E....E...# 
#..C.....J...ZX...@@....TT...# 
#..GD....Z........*..J..LR...# 
############################## 
 
MartianAnimal can move in following directions 
[1] to move north. 
[2] to move west. 
[3] to move east. 
[4] to move south. 
[5] to move north-west. 
[6] to move south-west. 
[7] to move north-east. 
[8] to move south-east. 
[0] to go back to main menu 
Please enter a direction. 
>  

Note: You only print the layout once during the initial startup of the program and then after everytime below actions happen

you move 

have eaten a plant or animal 

collected mineral/destroyed rock


Habitability Statistics
Habitability Statistics
Habitability Stats maintains how habitable you have made Mars. Habitability Stats comprises of two things -  

A list of entities on the Martian Land Map and their corresponding count as shown below  

A total habitability score 

Habitability Status 
====================== 
POTATO = 1 
MINERAL = 1 
EUCALYPTUS = 2 
LILY = 1 
ROSE = 1 
COW = 2 
GOAT = 1 
DOG = 2 
TOMATO = 2 
 
Total Habitability Score: 41 

Habitability Score: Potato + Mineral + Eucalyptus + Lily + Rose + Cow + Goat + Dog + Tomato = 1x2 + 1x2 + 2x2 + 1x2 + 1x2 + 2x5 + 1x5 + 2x5 + 2x2 = 41.

Note that if there is a mineral or rock present on the Martian land map, they don’t add to the score until the Space Rover takes some action on it. During initial map load, the habitability score then should be 39 ie no score for mineral added. However, if you are loading a habitability.log file or if the space rover has already collected the mineral then it should be 41.

The name of the entities are mentioned under the Symbols. Habitability score changes as you perform some actions.


How the habitability score changes -  
When the map is loaded and if it has some entities like vegetation/cattle, it means it was previously set up to be habitable. You will add a habitability score. Otherwise, with some actions that Space Robot/Rover/Martian Animals take, you will update the habitability score. 

When a plant/veg like a Lily/ Rose/Eucalyptus/Tomato/Potato/Apple/Banana/Onion is planted, increase the score by 2. 

When an earth animal is added including the dog, increase the score by 5. 

When a Space Robot waters a plant or feeds a cattle, the habitability score goes up by 1 

When a Space Rover destroys a rock score increases by 1. When a Space rover collects a mineral, the score goes up by 2. Note that if there is a mineral or rock present on the Martian land map, they don’t add to the score until the Space Rover takes some action on it. 

When a Martian animal eats a plant, the score decreases by 2 

When a Martian Animal kills and eats a cattle (Sheep/Goat/Cow) then the score decreases by 5. 

If a Martian Animal kills a dog, the score decreases by 5. 

If a martian Animal is killed by a dog, the score increases by 7. 

Guidelines
Some guidelines for your code 

Enums- When you have different types of an entity, you might want to create a single class and represent different types using Enums.

​​Inheritance - Entities that have similar types can be divided into parent and child classes using inheritance. You should write down the common functionality in the parent class and override or add different functionality as per specifications in child classes.

Interfaces - Interfaces help you define the contracts/functionality that a class should follow. When you want to use same method in different classes which are unrelated, use interfaces. When you have same functionality in similar classes use inheritance.

Polymorphism - Sometimes same method may have different functionality based on different parameters/data. You must use polymorphism in that case.

Generics <Optional> - You may or may not use Generics to handle some common functionality between different classes.

Exception Handing - There are various kinds of exceptions present in the specifications. Try and implement the exceptions the right way.

File Handling - You may want to create different classes or interfaces for classes handling file read/write. Make sure to catch the exceptions that occurs with File Handling.

Collections - Collections are great way to store data in a compact manner. Use ArrayList instead of Arrays. Make use of HashMaps if necessary.

Encapsulation - It is important you write the right method in the right classes. This gains you marks. Encapsulate the data properties and action taken on them in a class.

Make things private where necessary. If inheritance is used, make things protected. Only getters/setters and methods should be public wherever needed.

Packages - similar classes are packaged together. For eg - you can package interfaces together or enums together in a package.

Documentation
Always make sure to document your code in general. For this project you need to provide two types of documentation for your program: a UML diagram depicting your overall architecture and make sure to use JavaDoc syntax so that you can create an automatic Java code documentation in HTML. Your UML diagram needs to be submitted as a PDF with the filename architecture.pdf along with your code. You do not need to submit your documentation created through JavaDoc. This will be created automatically.

UML Diagram
Prepare a UML diagram describing your entire program containing all classes, their attributes (including modifiers), methods, associations, and dependencies. For each class, you need to identify all its instance variables and methods (including modifiers) along with their corresponding data types and a list of parameters. You should also identify relationships between classes, including associations, multiplicity, and dependencies. Static classes must be included in the UML. You can leave out any helper function that you added (i.e., minor classes that don't play a major role other than supporting others). 

Make sure to save your UML diagram in PDF format and add it to your code in the root folder as architecture.pdf.

Javadoc
Make sure all your classes indicate their author and general description. For each constructor and method specified in the final project description, you must provide at least the following tags: 

@param tags

@returns tag

@throws

You can leave out minor helper functions that you may have added. Make sure to test the correct generation of your documentation using javadoc.

For the submission, you do not need to generate and add the javadoc to edstem. For marking, we will create the javadoc programmatically from your code.