/*
Written by: Ronan Smyth
Purpose: Extermination game. I chose to use the LinkedListQueue ABS since it allows a circular list approach
         through removal-from-front & addition-to-end (FIFO) order. This facilitated the elimination of an
         nth element.
Date: 26/02/2023
Filename: Extermination.java
*/

import java.util.*;
public class Extermination extends LinkedListQueue
{
   public static void main(String[] args)
   {
      /*Some critical variables declared here. Any variables declared later were done as such to facilitate 
      reading of the code.*/
      int selection, randomNum;
      boolean isDuplicate=false;
      String playerName;
      
      Scanner kb = new Scanner(System.in);
      
      //Random number generator object.
      Random rand = new Random();
      
      //LLQ for playing the game.
      LinkedListQueue playerList = new LinkedListQueue();
      //LLQ to keep a copy of the original.
      LinkedListQueue copyPlayerList = new LinkedListQueue();
      //LLQ to keep a copy of the shuffeled list.
      LinkedListQueue shuffeledList = new LinkedListQueue();
      //LLQ to keep a copy of the sorted list.
      LinkedListQueue sortedList = new LinkedListQueue();
      //A stack to pop off the runners up in order.
      Stack<String> s = new Stack<String>();
      
      do
      {
         System.out.println("    Exterminate! Exterminate!    ");
         System.out.println("===============================");
         System.out.println("    [1] Add player");
         System.out.println("    [2] Display player list");
         System.out.println("    [3] Shuffle player list");
         System.out.println("    [4] Sort player list");
         System.out.println("    [5] Play Exterminate Game");
         System.out.println("    [0] Quit");
         System.out.println("Select an option:"); 
         
         selection = kb.nextInt();
         
         
         switch (selection)
         {
            case 1:  do
                     {
                        isDuplicate = false;//variable checks if name already on list and resets here.
                        System.out.println("Please enter a name for player number " 
                                             + (playerList.size()+1) + " or enter number zero[0] to exit." );
                        
                        playerName = kb.next();
                        
                        //For loop checks otherlist elements to ensure names are unique.
                        for(int i = 0; i<playerList.size(); i++)
                        {
                           /*Error checking here makes sure zero(to exit) is not saved as a name allows 
                           entry of 1st element.*/
                           if(playerList.size()==0 && (!playerName.equals("0") ) )
                              playerList.add(playerName);
                           else if(playerName.compareTo((String)playerList.peek() )==0 )//Compare to rest of list.
                              isDuplicate = true;
                              
                           playerList.add(playerList.remove() );//Circular LLQ approach.
                        }
                        
                        //Add names if not duplicates.
                        if(isDuplicate==false && (!playerName.equals("0") ) )
                           playerList.add(playerName);
                        else //Or advise player and display LLQ again.
                        {
                           System.out.println("Name not entered. Please ensure names entered are unique. " +
                                                "See names list below.");
                           for(int i = 0; i<playerList.size(); i++)
                           {
                              String check = (String)playerList.remove();
                              System.out.println(check);
                              playerList.add(check);
                           }
                        }
                             
                     }while(!playerName.equals("0") ); //entering zero exits the name-entry functionality.
                     
                     //Saves a copy of the LLQ in the original order.
                     for(int i = 0; i<playerList.size(); i++)
                     {
                        String copyName = (String)playerList.remove();
                        playerList.add(copyName);
                        copyPlayerList.add(copyName);
                     }
                     
                     break;
                     
            case 2:  for(int i = 0; i<playerList.size(); i++) //Display names
                     {
                        String listPrint = (String)playerList.remove();
                        System.out.println(listPrint);
                        playerList.add(listPrint);
                     }
                     break;
                     
            case 3:  playerList.shuffle(); // Shuffle option and save copy of shuffled list.
                     String shuffleCopy;
                     for(int i=0; i<playerList.size(); i++)
                     {
                        shuffleCopy = (String)playerList.remove();
                        playerList.add(shuffleCopy);
                        shuffeledList.add(shuffleCopy);
                     }
                     /*I find this option confusing. Surely there would have to be a call to the shuffle 
                     method in the game to ensure it has been done.*/
                     break;
                     
            case 4:  playerList.sort();//Call to the sort method in LLQ class.
                     String sortCopy;
                     for(int i=0; i<playerList.size(); i++)//Saves a copy of the sorted list.
                     {
                        sortCopy = (String)playerList.remove();
                        playerList.add(sortCopy);
                        sortedList.add(sortCopy);
                     }
                     break;
                     
            case 5:  randomNum = rand.nextInt(playerList.size()-2);
                     randomNum += 2;//A random number between 2 and the LLQ size.
                     
                     playerList.shuffle();
                     
                     int round = 1;
                     
                     do
                     {
                        System.out.println("Round: " + round);//Prints round number.
                        System.out.println("**********************");
                           
                        for(int i=0; i<=randomNum; i++)//Iteration through the list.
                        {
                           String str;
                           if(i!=randomNum)
                           {
                              str = (String)playerList.remove();
                              playerList.add(str);//Element moved to the end of the list.
                           }
                           else
                           {
                              str = (String)playerList.remove();//nth(random number) eliminated.
                              System.out.println(str + " has been eliminated!");//Eliminated element displayed.
                              s.push(str);//Eliminated element added to top of "runners-up" stack.
                           }   
                        }
                        System.out.println("  Players remaining:  ");
                        System.out.println("**********************");
                        for(int j = 0; j<playerList.size(); j++)//Prints remaining players after each elimination.
                        {
                           String remaining = (String)playerList.remove();
                           System.out.println(remaining);
                           playerList.add(remaining);
                        }
                        
                        /*Took initiative here. Output was chaotic so I broke it up with a key signal. */
                        String play = "n";
                        while(!play.equalsIgnoreCase("y"))
                        {
                           if(playerList.size() == 1)
                              break;
                              
                           System.out.println("Enter \'y\' to continue to round " + (round+1) + ".");
                           play = kb.next();
                           round++;
                           System.out.println();
                        }
                     }while(playerList.size()!=1);
                     
                     System.out.println("**********************");
                     System.out.print("!!The Winner is: ");
                     System.out.println(playerList.peek() );//Display winner.
                     System.out.println("**********************");
                     
                     System.out.println();
                     System.out.println("Runners Up: ");
                     System.out.println("**********************");
                     
                     
                     int runnersUp = 1;//Keeps trackof 1st,2nd,3rd place, up to 20 places accurately.
                     while(!s.empty() )
                     { 
                        runnersUp++;
                        if(runnersUp==2)
                           System.out.println(runnersUp + "nd place: " + s.pop() );
                        else if(runnersUp==3)
                           System.out.println(runnersUp + "rd place: " + s.pop() );
                        else
                           System.out.println(runnersUp + "th place: " + s.pop() );
                     }
                     
                     System.out.println("Player number " + randomNum + " was eliminated each time.");//Displays elimination number.
                                          
                     break;
                     
            case 0:  System.out.println("Goodbye!");
                     break;
                     
            default: System.out.println("Please make a valid order."); 
                     break; 
         }             
      }while(selection != 0);
   
   }

}