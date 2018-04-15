# Robot Controller in a Maze
The problem poses the situation: given a maze which is represented as a 2d array of integers and an instruction set, the Robot will attempt to navigate to the finish line.

The maze is represented as a 2d array of integers, with different environment types represented by integers as follows:
 - 0 = wall 
 - 1 = begin 
 - 2 = route
 - 3 = end
 
 # For Example:
 
| begin | route | wall | wall | wall | wall |
| wall, route, wall, wall, wall, wall |
| wall, route, route, wall, wall, wall |
| wall, wall, route, wall, wall, wall |
| wall, wall, route, wall, wall, wall |
| end, route, route, wall, wall, wall | 

The route represent the correct route through the maze, walls can't be navigated through. You can follow the routes visually to find the correct path through the maze.
 
 Our aim is to find out a fitness score for a path; it is this score that the genetic algorithm will optimize.

## Contributors
- Anish Surti
- Akash Jagtap

## References
- Genetic Algorithms in Java Basics by Jacobson, Lee, Kanber, Burak
