Kathleen Delsener and Justine Lo
CS5004: Object Oriented Design
Professor Amit Shesh
20 April 2018

Assignment 9: The Easy Animator: Part 3: Let there be Actual Motion!

In this final assignment, we added the additional feature of creating a GUI to present a visual animation. Along with the previous View classes that create a textual representation of the animation as a text file or SVG file, we now have a View class, AnimationView, that produces a visual output of the actual animation. 

When the user input calls for the visual representation, a GUI will appear. The GUI also contains scroll bars, allowing the user to scroll to view the entire animation if the current window size is too small.

To exit out of the animation, the user can simply "X" out of the GUI.

The animation is created using Java Swing Timers, JPanels, and Graphics. 

EXTRA CREDIT:
The GUI also allows the user to:
	1.) Play, Pause/Resume, Restart
	2.) Increase and Decrease speed of the animation
	3.) Save animation into a SVG file using appropriate file path name entered by user

When the GUI opens, a window appears. When the "Play" button is pressed, the animation begins. While the animation is running, the user can pause and resume the animation with the "Pause/Resume" button. The user can also restart the animation using the "Restart" button.

The "Increase" and "Decrease" speed buttons allow the user to control the speed of the animation. Clicking "Increase" instantly increases the speed by 10 ticks, and clicking "Decrease" instantly decreases the speed by 10 ticks. When the speed is less than 10 ticks, it decreases by 1 tick until it reaches 0 ticks.

At the bottom of the GUI, the user can input a file path and click "Save" to save the animation into a SVG file that is named and located according to the file path the user inputted.