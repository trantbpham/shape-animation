Assignment 8

=================PURPOSE AND OVERVIEW=================

This application model aims to create simple animation using shapes,
focusing on designing the algorithm and shape behaviors. The design 
of the model allows to support various shape behaviors, with ovals
and rectangle being the baseline shapes.


The application model consists of:

	* Shape Interface: groups all the methods that are used by Abstract
	Shape class, Rectangle Shape, and Oval Shape. 
	
	* Abstract Shape: utilizing methods contracted by Shape interface,
	the abstract shape extends the methods to and will be utilized by both
	Rectangle and Oval shape. Each shape's fields stores data in an array, 
	where the array index represents the time of the where the shapes 
	in order to retrieve the information of the shape at a given time if 
	needed.
	
	* Rectangle Shape Class: inherits Abstract Shape methods as well as 
	overriding toString and getShape method according to Rectangle 
	needs. Besides inheriting Abstract Shape method, Rectangle Shape 
	also includes enum representing the shape type as Rectangle.
	
	* Ellipse Shape Class: inherits Abstract Shape methods as well as 
	overriding toString and getShape method according to Oval 
	needs. Besides inheriting Abstract Shape method, Oval Shape 
	also includes enum representing the shape type as Oval. 
	
	* Enum Shape Type: used to represent the shape type for all the shapes. 
	This can be expand to store other shape types when expanding to other 
	shapes. 
	
	* Animation Model Interface: groups all the methods that are used by
	Animation Model Impl class.
	
	* Animation Model Class: implementing all methods in the Animation
	Model Interface, this class represents the actual animation itself, 
	storing all shapes in a hashtable, utilizing the shape's name as the key.
	
The application views consist of:
    
    * VisualAnimationView - this class generates a visual animation of the 
        input by extending the JFrame class, adding a panel, and overriding the JPanel
        paint method in an internal class myPanel. myPanel method paint does the actual
        drawing by iterating over the shapes in the model and creating or updating rectangle
        and ellipse shapes from the graphics2D class. The view gets the shape parameters
        for a given time from the model via the getShapeParameters method. The class accounts
        for time by stepping forward the time parameter t, sleeping, then repainting. The 
        sleep length is a function of the speed parameter and determines the speed of the 
        visual animation. The panel will keep repainting as long as an object in the input
        file is 'alive' or has some animation remaining.
        
    * PlaybackAnimationView (Assignment 10) - This view functions very much like the VisualAnimation
        View, see above for operation details. This view is different from the  VisualAnimation
        View because Playback allows for user input and control of the animation as 
        it is playing. A Jpanel added to the Frame includes user instructions for how to use the 
        buttons layed out to control the animation. This allows users to play, pause, rewind, loop,
        and change the speed of the animation in real time. Furthermore, there is the option to 
        delete shapes from the view via the delete shape button, which lists all the shapes in the 
        animation and allows the users to type the name of the shape to delete. The shape will then 
        be removed from the model and thus the animation.
        
    * TextAnimationView - this class generates a text view of the animation by utilizing the 
        input of from the builder, adding those info in lists appropriate to its category, and 
        retrieve the data to print from those lists in shape. TextAnimation takes in the 
        AnimationModel as its field, and from there store the shapes in form of hashmap, loop 
        through each shape to get the data.
    
    * SVGAnimationView - this class generates a SVG view of the animation by generating a 
        String following the SVG format. Similarly to the approach of TextAnimation View, by 
        utilizing the input of from the builder, adding those info in lists appropriate to 
        Abstract Shape class, and retrieve the data to print from those lists in shape. 
        SVGAnimation takes in the AnimationModel as its field, and from there store the shapes 
        in form of hashmap, loop through each shape to get the data.

================== MODEL UPDATES ASSIGNMENT 8 ================================

    1. Added canvas getters functions to model so we can access canvas 
    parameters from the model in the views.

    2. Added a changeVisibility method to shape and make the model change
    the shape's visibility when it changed the shape's motions. This 
    accounted for the appearing stars in the Buildings.txt example but keeping
    shapes hidden even after they were declared until a motion was applied to them.
    
    3. Added an 'orderedKeys' ArrayList to the model. This was to account for drawing
    the shapes in the correct order every time we called repaint() in the visual view.
    Previously they were drawn from a set of keys so order wasn't guaranteed. 
    We needed this because the buildings were behind the background in the buildings.txt
    example when the model used keySet() but the layering was correct using the 
    orderedKeys.
    
    4. Changed OvalShape to EllipseShape since input calls them ellipses not ovals.
    
    5. Added getters and setters for all fields in AbstractShape. Due to time constraint on 
    the project and didn't have enough time to properly refactor the code, added List to store
    the input of of motion changes according to the builder in order to retrieve those numbers and 
    use for sgv and text view.
    
    6. Refactor each methods in AnimationModel, taking in extra arguments from the builder
    to store data to use for the Text and SVG view, and then assign those values to the lists
    that AbstractShape has. 
    
================== MODEL UPDATES ASSIGNMENT 10 ================================
    
    1. Added a removeShape method to the model so that we can remove a shape from the animation. 
        This in turn will result in the shape removing itself from the view since the shape will no
        longer return any values when view asks the model for all the shape parameters.
    
    2. Added a Stringbuilder method to the model to return a stringbuilder with all the names of the
        shapes currently in the model. This was used to test and validate the removeShape method 
        behaving correctly.






