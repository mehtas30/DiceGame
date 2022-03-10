/*Programmed by Samarth Mehta on 1/16/2018
The Dice Game
The computer generates a number between 1-6. The user guess that number. If the user guesses right they play a minigame. There are 6 mingames. If the user wins the minigame they win money, but if they loose money gets taken away.
*/
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Font;
import java.awt.geom.Rectangle2D;
// import java.awt libraries
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing lbiraries
public class CPT222 extends JPanel implements MouseListener, KeyListener
{
    Ball[] balls = new Ball [2]; // makes ball array of Ball class.
    Ball playerThree; //declares playerThree as Ball type
    Ball playerBall;
    int xPlay = 1700; //starting x pos for game 3 player
    int yPlay = 2000; //y pos for game 3 player
    Square[] squareThree = new Square [12]; //declares squareThree as Square array
    Square[] squareSix = new Square [7]; //declares squareSix as Square array
    Square[] squareFive = new Square [5]; //declares squareFive as Square array
    Square player = new Square (10, 500, 10); //declares player as Square type. parameters are x,y,size. used for game 4
    Ball[] ballBounce = new Ball [3]; //declares ballBounce as Ball array. used in game 4
    private Container container; //declares container
    private DrawCanvas canvas; // canvas is object from DrawCanvas class.
    private int canvasWidth; //private so its accessability is restricted
    private int canvasHeight;
    boolean gameOneOver = false;
    int keyX; // key location variable
    int keyY;
    JLabel totalLabel; //declares totalLabel as JLabel
    int rollNum;  //rollNum for computer generated number
    int guess;  // input for user guess .
    int gameNum;
    int xNet = 1095; // xpos of net for game six
    int yNet = (int) (665 * Math.random () + 10); //y position for net of game six
    int sum = 0; // sum for user winning

    public CPT222 (int width, int height)  //contructor
    {
	canvasWidth = width;
	canvasHeight = height;
	container = new Container (); //container is a new Container. holds and manages other components
	canvas = new DrawCanvas (); //canvas is a new DrawCanvas. this is used to paint on the screen
	this.setLayout (new BorderLayout ()); //sets borderLayout to canvas
	totalLabel = new JLabel (); //totalLabel is new JLabel

	canvas.setLayout (new BorderLayout ()); //sets borderLayout to canvas
	canvas.add (totalLabel, BorderLayout.CENTER); //adds totalLabel to canvas

	this.add (canvas, BorderLayout.CENTER); //sets cavas to center of screen
	this.addMouseListener (this); //adds mouse listener
	this.addKeyListener (this); //adds key listener
	this.setFocusable (true); //sets focusable to class
	start (); //calls start method
    }


    public void start ()  //start method
    {
	Thread t = new Thread ()  //t is new thread
	{
	    boolean gameRun = true;
	    public void run ()  //run method
	    {
		while (gameRun = true)
		{
		    repaint (); //paints (title screen)
		    try //waits 5 seconds
		    {
			Thread.sleep (5000);
		    }
		    catch (InterruptedException e)
		    {
			System.out.println ("Error: " + e.getMessage ());

		    }
		    int numTimes = Integer.parseInt (JOptionPane.showInputDialog ("Would you like to play 1/3/6 times?")); //gets how many time user wants to play
		    while (numTimes != 3) //error trap if user inputs anything other than 1, 3, or 6
		    {
			if (numTimes == 1) //if numTimes is 1 get out of loop
			{
			    break;
			} //if statement
			else if (numTimes == 6) // if numTimes is 6
			{
			    break; //get out of loop

			} //else if satement
			else //else statement
			{
			    numTimes = Integer.parseInt (JOptionPane.showInputDialog ("Would you like to play 1/3/6 times?"));  //reasks numTimes
			} //else statement
		    } //while loop
		    if (numTimes == 1 || numTimes == 3 || numTimes == 6) //if statement if user inputs values that are acceptable for number of times that the program runs
		    {
			for (int x = 0 ; x < numTimes ; x++) //for loop for running the game
			{
			    totalLabel.setText (""); //sets totalLabel to blank at the start of each loop
			    gameOneOver = false; //sets gameOneOver to false
			    rollNum = (int) (6 * Math.random () + 1);  //randomizes rollNum
			    //System.out.println (rollNum);  //testing
			    try
			    {
				guess = Integer.parseInt (JOptionPane.showInputDialog ("Enter your guess"));  //JOptionPane that converts string to Integer value for user guess input
			    }
			    catch (Exception e)
			    {
				System.out.println ("error:" + e.getMessage ());

			    }
			    while (guess < 1 || guess > 6)  //while loop for error trap if user inputs values bigger than 1-6
			    {
				try
				{
				    guess = Integer.parseInt (JOptionPane.showInputDialog ("Enter your guess between 1-6"));  //JOptionPane that converts string to Integer value for user guess input
				}
				catch (Exception e)
				{
				    System.out.println ("error:" + e.getMessage ());

				} //asks for valid guess
			    } // end while loop

			    gameNum = (int) (6 * Math.random () + 1); //sets gameOrder to have random number
			    canvas.setVisible (true); // sets canvas to visible
			    if (rollNum == guess) // if rollNum is equal to guess
			    {
				if (gameNum == 1) // if gameNum is 1
				{
				    Print ("Click the blue ball for $100. If you click anywhere else you lose $100"); //calls print method
				    canvas.setVisible (true); //sets canvas to visible
				    for (int count = 0 ; count < 2 ; count++)
				    {
					balls [count] = new Ball (); // makes ball[] a new Ball object
				    } // for loop
				    while (!gameOneOver) // while gameOneOver is false
				    {
					for (int count = 0 ; count < 2 ; count++)
					{
					    balls [count] = new Ball (); //ball[] is new Ball object as other got "removed" in removeall()
					} // for loop
					repaint (); // calls paint componenet
					try //delays for half a second
					{
					    Thread.sleep (500);
					}
					catch (InterruptedException e)
					{
					    System.out.println (e.getMessage ());
					}
					canvas.removeAll (); // removes objects
					try //delays for a second
					{
					    Thread.sleep (1000);
					}
					catch (InterruptedException e)
					{
					    System.out.println (e.getMessage ());
					}

				    } // while loop
				    canvas.removeAll (); // clear the balls
				    gameNum = 0; //sets gameNum back to 0 because the picture of previous game hangs if you dont.
				} // if statement
				else if (gameNum == 2)
				{
				    int gameTwoNum = ((int) (249 * Math.random () + 2)) * 2; // gameTwoNum is randomized and made even
				    int numChances = (int) (18 * Math.random () + 3); //numChances is randomized

				    int input = 0; //user input is 0
				    int total = 0; // total is 0
				    Print ("Time for some math. Win to get $50. Loose to get fined $100"); // calls Print method passes string
				    String instructions = "You must make " + gameTwoNum + " with at exactly " + numChances + " even numbers";
				    Print (instructions); // calls Print method passes instructions String
				    int won = 0; // int won is 0
				    while (numChances >= gameTwoNum / 2) //while numChance is more or equal to half of gameTwoNum re randomize as it wont work out mathimatically
				    {
					numChances = (int) (18 * Math.random () + 3); // random value for numChance
				    } // while loop
				    for (int u = 0 ; u < numChances && total < gameTwoNum ; u++) //for loop for gameTwo
				    {
					input = Integer.parseInt (JOptionPane.showInputDialog ("Enter your " + (u + 1) + " number to add to make " + gameTwoNum + " in " + numChances + " chances")); // input is JOption pane. parses string to integer
					while (input % 2 != 0 || input == gameTwoNum) // if input is odd or input is the same as gameTwoNum
					{
					    input = Integer.parseInt (JOptionPane.showInputDialog ("Enter your " + (u + 1) + " number. It must an even number.")); //asks user to reenter number
					} //while loop
					total = total + input; // total is equal to total plus input
					Print ("you have reached the Total of " + total); //calls print method to update user on situation
					if (u + 1 == numChances && total == gameTwoNum) // if user makes specifications
					{
					    sum += 50; //adds 50 to sum
					    Print ("you won $50"); // print method to inform user
					    won = 1; //won is equal to 1
					}
					else if (total == gameTwoNum && u + 1 != 100) // else if total is equal to gameTwoNum and u is not the same as numChance they lose
					{
					    sum -= 100; // 100 is taken from sum
					    Print ("you lost 100"); // Print method passes string to inform user
					    won = 1; //won is equal to one
					    break; //breaks from loop
					} // else if statement

				    } // for loop
				    if (won != 1) // if won is not 1
				    {
					sum -= 100; // takes away 100 from sum
					Print ("You lost $100"); // print method passes string to inform user
				    }
				    gameNum = 0; // resets gameNum
				}
				else if (gameNum == 3) // else if gameNum is 3
				{
				    keyX = 0; // keyX is 0 to start user at 0

				    for (int arrayIndex = 0 ; arrayIndex < squareThree.length ; arrayIndex++)
				    {
					int xSq = (int) Math.round (110 + Math.random () * 900); // attributes of squareThree
					int ySq = (int) Math.round (Math.random () * -200);
					squareThree [arrayIndex] = new Square (xSq, ySq, 10); // makes each squareThree a new Square passing x,y,size
				    }
				    playerThree = new Ball (); // makes playerThree a new Ball
				    Print ("Reach the other side. Win to earn $150. If you get hit, you lose $50."); // print method
				    canvas.setVisible (true); //setss canvas visible true
				    while (!IsGameThreeOver ()) // game three loop
				    {
					updateSquares (); //calls updateSquares
					try
					{
					    Thread.sleep (10); //delays so it is not impossible fast
					}
					catch (InterruptedException e)
					{
					    System.out.println (e.getMessage ());
					}
					repaint (); //calls paint component


				    } //while loop
				    gameNum = 0; //resets gameNum to 0

				} //else if statement
				else if (gameNum == 4) //else if statement
				{

				    for (int a = 0 ; a < 3 ; a++) //for loop
				    {
					ballBounce [a] = new Ball (); // each ballBounce is a new  Ball
				    }
				    player.xCordinate = 10; //makes xCordinate attribute of player to 10
				    player.yCordinate = 500; //make yCordinate attribute of player to 500
				    long time = System.currentTimeMillis () + 30000; //time is current time plus 30000ms
				    Print ("Dodge the ball for 30 seconds to win $200. Loose and $75 gets taken away."); // calls Print method
				    canvas.setVisible (true); // sets cavas to visible
				    while (!IsGameFourOver (player.xCordinate, player.yCordinate) && System.currentTimeMillis () <= time) //while loop for gameFour
				    {


					ballMove (); //ballMove method
					try
					{
					    Thread.sleep (10); //delays for 10 ms
					}
					catch (InterruptedException e)
					{
					    System.out.println (e.getMessage ());
					}

					repaint (); // calls paint component
					if (time == System.currentTimeMillis ()) //time is equal to 30s user wins
					{
					    sum += 200; // adds 200 to sum
					    Print ("You won $200"); // calls Print method
					    canvas.setVisible (true); //sets canvas to visible
					    break; // breaks out of loop
					} // if statement
				    } //for loop
				    gameNum = 0; //resets gameNum to 0

				} //else if statement
				else if (gameNum == 5)
				{
				    Print ("Get the blue ball to the blue goal. Dont go above it or hit the red obstacles"); //calls Print method
				    Print ("You can win $100 or lose $100"); // calls Print method
				    canvas.setVisible (true); // sets canvas visible to true
				    playerBall = new Ball (); // playerBall is new Ball object
				    playerBall.radius = 10; //attributes for playerBall
				    playerBall.x = 10;
				    playerBall.y = 675;
				    for (int g = 0 ; g < 5 ; g++) // for loop for making squareFive Square objects
				    {
					int xSqFive = 1085; //attributes for squareFive
					int ySqFive = (int) Math.round (Math.random () * -200);
					squareFive [g] = new Square (xSqFive, ySqFive, 5); // squareFive[] is new Square passing attributes
				    }
				    while (!gameFiveOver ()) // while loop calling gameFiveOver method for gameFive
				    {
					repaint (); //calls paint component
				    }
				    gameNum = 0; //gameNum is 0

				} // else if statement
				else if (gameNum == 6) // else if gameNum is 6
				{
				    Print ("Reach the top with out getting hit to win $125. If you die than you lose $100"); //calls Print method
				    canvas.setVisible (true); // sets cavas visible to true (you can see canvas)
				    playerBall = new Ball (); //playerBall is new player
				    playerBall.radius = 10; //attributes for playerBall
				    playerBall.x = 400;
				    playerBall.y = 675;
				    for (int a = 0 ; a < squareSix.length ; a++) // for loop
				    {
					int xCord = (int) 790; // sets random xCord
					int yCord = (int) (Math.random () * -1000); //attributes for squareSix. sets random y Cord
					squareSix [a] = new Square (xCord, yCord, 10); // every square in squareSix array is Square object
				    } // for loop
				    while (!GameSixOver ()) //while loop calling gameSixOver
				    {
					repaint (); //calls paint component
				    } //while loop
				    gameNum = 0; // gameNum is 0

				} // else if statement
				else // else gameNUM is 0
				{
				    gameNum = 0;
				} //else statement
			    } // if statement
			    else // else rollNum and guess are not the same
			    {
				Print ("guess was incorrect"); //calls Print method
			    }
			} //for loop
			Print ("This is the end. You have $" + sum); // end of game. calls Print method to tell user how much they have one
			canvas.setVisible (true); // sets canvas to visible
			gameNum = 0;
			repaint (); //prints title screen
			String gameCont = (JOptionPane.showInputDialog ("Do you want to coninue press anything for yes or n to exit game")); //prompt with JOptionPane
			if (gameCont == "y")
			{
			    gameRun = true; //gameRun is true the loop goes on

			}
			else
			{
			    gameRun = false; //passes 0 to system.exit (closes the program)
			}

		    } //if statement
		} //while loop

	    } //run method
	}


	;
	t.start (); //starts new thread

    } //start method


    public boolean gameFiveOver ()  // gameFiveOver method
    {
	for (int squareIndex = 0 ; squareIndex < squareFive.length ; squareIndex++) // for loop for targeting every Square in squareFive

	    {
		if (squareFive [squareIndex] != null) // if squareFive[] is not null
		{
		    double deltaX = playerBall.x - squareFive [squareIndex].xCordinate; //vector x calculation btwn square and player
		    double deltaY = playerBall.y - squareFive [squareIndex].yCordinate; //vector y calculation btwn square and player

		    double dist = Math.sqrt ((deltaX * deltaX) + (deltaY * deltaY)); //pythagoream theorem to find distance

		    if (playerBall.y < yNet - 10 || dist <= 20) // if distance means that they are touching
		    {
			sum -= 100; // takes away 100 from sum
			Print ("You loose $100"); // calls Print method
			canvas.setVisible (true); // canvas visibility is true
			return true; //returns true
		    } //if statement
		} //if statement
	    } // for loop


	double deltaX = playerBall.x - xNet; //calcutes distance btwn player and net
	double deltaY = playerBall.y - yNet;
	double dist = Math.sqrt ((deltaX * deltaX) + (deltaY * deltaY));
	if (dist <= 20) // if touching
	{
	    sum += 100; // 100 is added to sum
	    Print ("you win $100"); // calls Print method
	    canvas.setVisible (true); // sets cavas to visible
	    return true; //returns true
	}


	return false; //returns false unless specified
    } //gameFiveOver method


    public boolean GameSixOver ()
    {
	for (int squareIndex = 0 ; squareIndex < squareSix.length ; squareIndex++) //for loop to target every square in array

	    {
		if (squareSix [squareIndex] != null)
		{
		    double deltaX = playerBall.x - squareSix [squareIndex].xCordinate; // gets distance for squareSix [squareIndex] from player

		    double deltaY = playerBall.y - squareSix [squareIndex].yCordinate;

		    double dist = Math.sqrt ((deltaX * deltaX) + (deltaY * deltaY));

		    if (dist <= 20) // if distance is touching

			{
			    sum -= 100; //takes away 100 from sum
			    Print ("you lose $100"); // calls Print method

			    canvas.setVisible (true); //sets canvas to visible

			    return true; // returns true

			} // if statement
		} // if statement
	    } // for loop


	if (playerBall.y <= 10) // if playerBall.y is less or equal to 10
	{
	    sum += 100; // 100 is added to sum
	    Print ("You win $100"); // calls Print method
	    return true; //retunrs true
	} //if statement


	return false; //returns fasle
    } //GameFiveOver method


    public void Print (String str)  //Print method gets String
    {

	totalLabel.setForeground (Color.RED); //sets text to red color

	canvas.setVisible (false); //makes canvas visible off

	this.setBackground (Color.WHITE); // sets JFrame backround to white

	totalLabel.setHorizontalAlignment (JLabel.CENTER); //makes alignemnt centered instead of left

	totalLabel.setVerticalAlignment (JLabel.CENTER);

	add (totalLabel); // adds total label
	totalLabel.setFont (new Font ("SansSerif", Font.BOLD, 25)); //sets font of totalLabel

	totalLabel.setText (str); //totalLabel sets the text

	try // waits a second

	{

	    Thread.sleep (1000);

	}


	catch (InterruptedException e)

	{

	    System.out.println (e.getMessage ());

	}
    } // Print method



    public boolean IsGameThreeOver ()

    {

	for (int squareIndex = 0 ; squareIndex < squareThree.length ; squareIndex++) // for loop

	    {


		if (squareThree [squareIndex] != null)
		{
		    double deltaX = playerThree.x - squareThree [squareIndex].xCordinate; //gets distance from player to squareThree [squareIndex]

		    double deltaY = 675 - squareThree [squareIndex].yCordinate; //y cordinate of playerThree is always 675

		    double dist = Math.sqrt ((deltaX * deltaX) + (deltaY * deltaY));

		    if (dist <= 20.0) // if they touch

			{
			    sum -= 50; // take away 50
			    Print ("you lost $50"); //calls Print method
			    canvas.setVisible (true); //sets canvas visible to true

			    return true; // returns true

			} // if statement
		} // if statement
		if (playerThree.x >= 1000) // if playerThree.x is more or equal to 1000
		{
		    sum += 150; // add 150 to sum
		    Print ("you won"); // calls Print method
		    canvas.setVisible (true); // sets canvas to be visible
		    return true; // returns true to exit gameThree loop
		} // if statement


	    } //for loop


	return false; // returns false

    } // gameThreeOVer method


    public boolean IsGameOver (int eventX, int eventY)
    {

	int ballIndex = 0; // sets ballIndex to 0
	if (balls [ballIndex] != null) // if ball[ballIndex] is not null
	{

	    double deltaX = eventX - balls [0].x; // gets distance from clickX to ball[0]
	    double deltaY = eventY - balls [0].y;
	    double dist = Math.sqrt ((deltaX * deltaX) + (deltaY * deltaY));
	    if (dist <= balls [ballIndex].radius) // if distance is less or equal to ball radius
	    {

		sum += 100; //adds 100 to sum
		Print ("you won $100"); // calls Print method


	    } // if statment
	    else // if not than
	    {
		sum -= 100; // takes away 100 from sum
		Print ("Sorry you lost! $100 will be taken."); // calls Print method
	    } // else if statement
	} // if statement


	return true; // returns true

    } //isGameOver


    public boolean IsGameFourOver (int keyX, int keyY)

    {

	for (int ballIndex = 0 ; ballIndex < 3 ; ballIndex++) // for loop to check every item in array

	    {

		if (ballBounce [ballIndex] != null)

		    {
			double deltaX = keyX - ballBounce [ballIndex].x; //  distance from player and ballBounce

			double deltaY = keyY - ballBounce [ballIndex].y;

			double dist = Math.sqrt ((deltaX * deltaX) + (deltaY * deltaY));
			if (dist <= ballBounce [ballIndex].radius) // if they touch
			{
			    Print ("you lost $75"); //calls Print method
			    sum -= 75; // takes away 75 froms sum
			    canvas.setVisible (true); // sets canvas.setVisible to true
			    return true; //returns true
			} //if statement
		    } //if statement
	    } //for loop
	return false; // returns false unless specified
    } // IsGameFourOver


    public void ballMove ()
    {
	for (int c = 0 ; c < 3 ; c++) //for statement for arrayIndex
	{
	    if (ballBounce [c] != null) // if ballBounce[c] is not null
	    {
		ballBounce [c].flow (); // got to flow method in Ball class
	    } // if statement
	} //for loop
    } // ballMove method


    public void updateSquares ()  //updateSquares
    {
	for (int squareIndex = 0 ; squareIndex < squareThree.length ; squareIndex++) //for loop
	{
	    if (squareThree [squareIndex] != null) // if squareThree[squareIndex] is not null
	    {
		squareThree [squareIndex].move (); // calls move method from Square class to update squareThree positon
	    } // if statement
	} //for loop
    } //update squares


    public void mouseClicked (MouseEvent e)
    {
    } // abstract method


    public void mouseEntered (MouseEvent e)
    {

    } // abstract method


    public void mouseExited (MouseEvent e)
    {

    } // abstract method


    public void mousePressed (MouseEvent e)
    {
	gameOneOver = IsGameOver (e.getX (), e.getY ()); //boolean gameOneOver = gets x and y position of click and passes them to IsGameOver
    } //mousePressed method



    public void mouseReleased (MouseEvent e)
    {

    } // abstract method




    public void keyPressed (KeyEvent e)
    {
	int keyCode = e.getKeyCode (); //gets keyCode
	if (gameNum == 6) // if gameNum is 6
	{
	    if (keyCode == 39) //right arrow
	    {
		if (playerBall.x >= 800) // if it is at boundries dont update
		{
		    playerBall.x = 800;

		}
		else //else move ball
		{
		    playerBall.x += 400; //moves playerBall right
		    playerBall.y -= 50; // up by 50
		}
	    } // if statement


	    else if (keyCode == 37)  //left arrow
	    {
		if (playerBall.x <= 400) // if at left boundries
		{
		    playerBall.x = 400; // dont update

		}
		else //otherwise
		{
		    playerBall.x -= 400; // move playerBall to the left
		    playerBall.y -= 50; // move up
		}
	    } // else if statemtn


	} // if statement


	else if (gameNum == 3)
	{
	    if (keyCode == 39) //right
	    {
		keyX += 5; //adds 5 to keyX
	    } //if statement


	    else if (keyCode == 37 && keyX > 0) //left
	    {
		keyX -= 5; //takes away 5 from keyX
	    } //else if statement


	    if (keyX >= 1100)
	    {
		keyX -= 5; // if keyX at boundry take away 5 (Ball stays the same position)
	    } // if statement
	} // else if statement


	else if (gameNum == 4) // if gameNum =4
	{
	    if (keyCode == 39) //right
	    {
		player.xCordinate += 10; //add 10 to player's xCordinate
	    }


	    else if (keyCode == 37 && player.xCordinate > 0) //left
	    {
		player.xCordinate -= 10; //minus 10 to player's xCordinate
	    }


	    if (player.xCordinate >= 890) // if player is at boundry
	    {
		player.xCordinate -= 10; //take away 10
	    }


	    if (keyCode == 40) //up
	    {
		player.yCordinate += 10; // add 10 to player's y cordinate
	    }


	    else if (keyCode == 38) //down
	    {
		player.yCordinate -= 10; // take away 10 from player's y cordinate
	    }


	    if (player.yCordinate >= 690)
	    {
		player.yCordinate -= 10; // if at bottom boundry take away 10 from player's y cordinate
	    }


	    else if (player.yCordinate <= 0)
	    {
		player.yCordinate += 10; // if at top boundry take add 10 from player's y cordinate
	    }

	}


	else if (gameNum == 5)
	{
	    if (keyCode == 38) //up
	    {
		playerBall.y -= 10; // takes away 10 form playerBall's y cordinate
	    }
	    else if (playerBall.y == 0)
	    {
		playerBall.y += 10; // if they are at bottom dont go past boundry
	    }
	    if (keyCode == 39) //right
	    {
		playerBall.x += 10; // adds 10 to playerBall's x position
		if (playerBall.x >= 1100) // if playerBall.x is at boundry
		{
		    playerBall.x -= 10; // substract from postition (playerBall remains in same spot)
		} // if statement
	    } //if statement
	} //else if statement
    } // keyPressed method


    public void keyReleased (KeyEvent e)
    {

    } //abstract method


    public void keyTyped (KeyEvent e)
    {

    } // abstract method


    class DrawCanvas extends JPanel // DrawCanvas class extends JPanel
    {

	public void paintComponent (Graphics g)  // paintComponent
	{
	    super.paintComponent (g); //repaints backround so paint artifacts from before are not there. Invokes from JPanel super class... drawCanvas is subclass.
	    container.draw (g); // draws on container
	    if (gameNum == 0) // if gameNum is 0
	    {
		g.setFont (new Font ("SansSerif", Font.BOLD, 75)); //sets new font
		g.setColor (Color.black); // makes big black screen
		g.fillRect (0, 0, 1800, 1000);
		g.setColor (Color.white); //makes dice
		g.fillRect (700, 500, 150, 150);
		g.setColor (Color.blue); //dice numbers
		g.fillOval (725, 525, 50, 50);
		g.fillOval (775, 575, 50, 50);
		g.setColor (Color.yellow); // sets color to yellow
		g.drawString ("THE DICE GAME", 200, 200); // draws string with new font
		g.setFont (new Font ("SansSerif", Font.BOLD, 20)); // sets font
		g.setColor (Color.blue); // sets color to blue
		g.drawString ("How to play:", 10, 300); // instructions
		g.drawString ("- Guess a number from 1-6.", 10, 350);
		g.drawString ("- If it matches the dice roll, you get to play a mini-game", 10, 400);
		g.drawString ("- Win the game to win money, loose the game and you loose money", 10, 450);
	    } // if statement
	    else if (gameNum == 1) // else if gameNum is 1
	    {
		for (int arrayIndex = 0 ; arrayIndex < balls.length ; arrayIndex++) //for loop
		{
		    if (balls [arrayIndex] != null)
		    {
			balls [arrayIndex].currentIndex = arrayIndex; //makes currentIndex in Ball class equal to this arrayIndex
			balls [arrayIndex].draw (g); //draws balls
		    } // if statement if balls are not null
		} // for loop

	    } // else if statement
	    else if (gameNum == 3) // else if gameNum is 3
	    {
		g.setColor (Color.black); // sets color to black
		g.fillRect (0, 900, 1080, 1000); // fills black rectangle
		for (int arrayIndex = 0 ; arrayIndex < squareThree.length ; arrayIndex++) // for loop for drawing each square
		{
		    if (squareThree [arrayIndex] != null)
		    {
			squareThree [arrayIndex].draw (g); //draws each square
		    } // if squareThree[] is not null
		} // for loop
		if (playerThree != null)
		{
		    playerThree.drawThree (g, keyX); //draws player passes g and keyX.
		}
	    } // else if statement

	    else if (gameNum == 4) //else if gameNum is 4
	    {

		g.setColor (Color.black); //sets color to black as color
		g.fillRect (0, 0, 900, 700); // fills black rectangle
		player.draw (g); //draws player in Square class
		for (int f = 0 ; f < 3 ; f++) // to affect all in ballBounce array
		{
		    if (ballBounce != null)
		    {
			ballBounce [f].create (g); // calls create method in Ball class.
		    }
		} // for loop
	    } // else if statement
	    else if (gameNum == 5)
	    {
		g.setColor (Color.orange);
		for (int a = 0 ; a < 5 ; a++) // for loop for backround design
		{
		    int w = (int) (81 * Math.random () + 20);
		    int l = (int) (81 * Math.random () + 20);
		    int x = (int) (901 * Math.random () + 100);
		    int y = (int) (901 * Math.random () + 100);
		    g.fillOval (x, y, w, l);
		}
		g.setColor (Color.blue);
		g.fillRect (xNet, yNet, 5, 20); //fills rectangle as the goal
		for (int b = 0 ; b < squareFive.length ; b++) // to affect every object in squareFive array
		{
		    if (squareFive [b] != null)
		    {
			squareFive [b].move (); //calls move in Square class

			squareFive [b].draw (g); // calls draw in Square class
		    }
		}
		if (playerBall != null)
		{
		    playerBall.draw (g); // calls draw in Ball class
		}
	    } // else if statement
	    else if (gameNum == 6) // else if gameNum is 6
	    {
		for (int b = 0 ; b < squareSix.length ; b++)
		{
		    if (squareSix [b] != null)
		    {
			squareSix [b].move (); // calls for move method in Square to update squareSix[] pos

			squareSix [b].draw (g); //draw method in Square to draw squareSix
		    }
		}
		if (playerBall != null)
		{
		    playerBall.draw (g); //draw method in Ball to draw playerBall
		}
		g.setColor (Color.green); //sets color as green
		g.fillRect (0, 0, 390, 1100); //draws rectangle
		g.fillRect (810, 0, 300, 1100);

	    }
	} // paint componenet method


	public void removeAll ()
	{
	    for (int arrayIndex = 0 ; arrayIndex < balls.length ; arrayIndex++)
	    {
		if (balls [arrayIndex] != null)
		{
		    balls [arrayIndex].x = 0; // set balls x attribute to 0
		    balls [arrayIndex].y = 0; //sets y to 0
		    balls [arrayIndex].radius = 0; // sets radius to 0
		    repaint (); //calls paint component

		} // if balls [] is not null
	    } // for loop

	} // remove all


	public Dimension getPreferredSize ()  // overrides Jpanel to fit needs. W/o Swing layout managers wont work on positioning custom components.
	{

	    return (new Dimension (canvasWidth, canvasHeight)); //
	}
    } // drawCanvas class


    public static void main (String[] args)  // main method
    {
	javax.swing.SwingUtilities.invokeLater (new Runnable ()  //can invoke after thread.start() if system is busy at the time of command. Runnable used to create the thread.
	{
	    public void run ()  //run method to seperatly execute the thread
	    {
		JFrame frame = new JFrame ("THE DICE GAME"); // adds JFrame
		frame.setDefaultCloseOperation (frame.EXIT_ON_CLOSE); //sets close operation when x is closed
		frame.setContentPane (new CPT222 (1100, 1100)); // contains visible components of JFrame
		frame.pack (); // packs for resize
		frame.setVisible (true); // sets frame visibility to true
	    } // run method
	} // invokes later


	); //runnable
    } // main method


    public static class Ball //Ball class
    {

	public int random (int maxRange)  //random method which needs integer passed
	{
	    int coord = (int) Math.round ((0.1 + Math.random () * maxRange)); //coord is random number
	    return coord; //returns to caller
	} //random method


	int x = random (480); // (this is also starting pos) as well as x postion variable
	int y = random (250); // this is starting y pos as well as y psotion variable
	int velX = 10; //velocity of x for flow method
	int velY = 10; //velocity of x for flow method
	int speedX = 5; // speedX
	int speedY = 5; //speedY for most moving Ball objects
	int radius = 50; //radius is 50
	int currentIndex = 0; //index for ball for color
	public void flow ()  // flow method
	{
	    if (x < 10 || x > 800) // if X reaches x xisboundries
	    {
		velX = -velX; //velX is - velX
	    } //if statement
	    if (y < 10 || y > 600)
	    {
		velY = -velY; //if y reaches boundries velY = - velY
	    } //if statement
	    y += velY; // adds velY to y
	    x += velX; // adds velX to x
	} //flow method


	public void drawThree (Graphics g, int keyX)  //draw method for game 3. gets Graphics and keyX
	{
	    g.setColor (Color.red); //sets color to red
	    g.fillOval (keyX, 645, 50, 50); // fills red circle that moves according to keyX
	    x = keyX; // x is equal to keyX
	    y = 645; // y is 645 always
	} // drawThree method


	public void create (Graphics g)  //create method, gets Graphics g
	{
	    for (int a = 0 ; a < 2 ; a++) // for loop for assigning color
	    {
		if (a == 0)
		{
		    g.setColor (Color.red);
		} //if index is 0 make it red
		else
		{
		    g.setColor (Color.blue);
		} //else index is 1 so make it blue

	    } // for loop
	    g.fillOval (x, y, 100, 100); //draws circle
	}


	public void draw (Graphics g)  // draw method gets Graphics
	{

	    if (currentIndex == 0) // if current index is 0
	    {
		g.setColor (Color.BLUE); //set color to blue
	    } // if statement
	    else
	    {
		g.setColor (Color.RED); // otherwise set color to red
	    } // else statemetn
	    g.fillOval ((int) (x - radius), (int) (y - radius),  // draws circle
		    (int) (2 * radius), (int) (2 * radius));
	} // draw method


	public void move ()  // move method
	{
	    x += speedX; // add speedX to x
	    y += speedY; //add speedY to y

	    if (x - radius < 0) //if at boundries
	    {
		speedX = -speedX; // speed is negative
		x = radius; //x positiion stays same
	    }
	    else if (x + radius > 1000) // if x is other side
	    {
		speedX = -speedX; // speed is negative
		x = 1000 - radius; //x pos stays same
	    }

	    if (y - radius < 0) //if y is at boundry
	    {
		speedY = -speedY; //speed is negative
		y = radius;
	    }
	    else if (y + radius > 1000) //if y is at other boundry
	    {
		speedY = -speedY; // speed is negatice
		y = 1000 - radius;
	    } // else if statement
	} // move method
    } //Ball class


    public static class Container //container class
    {
	public void draw (Graphics g)  //draws container
	{
	    g.setColor (Color.white); //sets color to white
	    g.fillRect (0, 0, 1100, 1100); //draws 1100x1100 container to hold canvas
	}
    } //container class


    public class Square
    {
	public Rectangle2D.Float rect; // rect is 2D rectangle. Was forced to use it
	int xCordinate = 0; //xCordinate
	int yCordinate = 0; // yCordinate
	int side = 0; //side size of rect

	public Square (int x, int y, int s)  //constructor
	{
	    xCordinate = x;
	    yCordinate = y;
	    side = s;
	    rect = new Rectangle2D.Float (x, y, s, s); // drawing actual square
	} // Square contructor


	public int random (int maxRange)  //random method passing integer
	{
	    return (int) Math.round (Math.random () * maxRange); //generates and returns random number
	} //random method


	public void draw (Graphics g)
	{
	    Graphics2D g2d = (Graphics2D) g; // use casting to convert the type from graphic to graphic 2D. graphic is parent class.
	    if (gameNum == 3) // if gameNum is 3
	    {
		g2d.setColor (new Color (random (255), random (255), random (255))); //creates random colors
	    }
	    else // otherwise
	    {
		g2d.setColor (Color.red); //squares are red
	    }
	    g.fillRect (xCordinate, yCordinate, 20, 20); //creates Squares
	} //draw method



	public void move ()
	{
	    int speed; //speed variable
	    if (gameNum == 3) //if gameNum is 3
	    {
		speed = 5; //set speed to 5
	    } //if statement
	    else if (gameNum == 5)
	    {
		speed = 3; //speed is 3
	    } //else if statement
	    else if (gameNum == 6)
	    {
		speed = 1; //speed is 1
	    } //else if statement
	    else
	    {
		speed = 20; //speed is 20
	    } //else statement
	    yCordinate = yCordinate + speed; //yCordinate is equal to yCordinate + speed
	    if (yCordinate > 675) //if yCordinate is 675
	    {
		yCordinate = 0; //make yCordinate 0;
	    } //if statement
	} //move method
    } // Square class
} //CPT class


