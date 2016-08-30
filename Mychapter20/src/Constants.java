import java.awt.Color;
import java.awt.Font;
import java.awt.color.*;
import java.awt.font.*;
import java.io.*;

public interface Constants {
	int LINE =101;
	int RECTANGLE = 102;
	int CIRCLE = 103;
	int CURVE = 104;
	int TEXT = 105;
	
	int pointSizeMin = 8;
	int pointSizeMax = 24;
	int pointSizeStep = 2;
	
	int DEFAULT_ELEMENT_TYPE = LINE;
	Color DEFAULT_ELEMENT_COLOR = Color.BLUE;
	Font DEFAULT_FONT = new Font("Times New Roman", Font.PLAIN, 12);
	
	File DEFAULT_DIRECTORY = new File("C:/Sketches");
	String DEFAULT_FILENAME = "Sketch.ske";
	
	int NORMAL = 0;
	int MOVE = 1;
	int ROTATE = 2;
}
