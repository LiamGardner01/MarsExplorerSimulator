import java.io.* ;
import java.util.Scanner;

public class MarsExplorerSimulatorDriver
{
	public static void main(String[] args)
	{
		MarsExplorerSimulatorDriver mesd = new MarsExplorerSimulatorDriver();		
		mesd.start();
	}

	public void start()
	{
		MarsExplorerSimulator apollo1 = new MarsExplorerSimulator();
		// Create a scanner to read in from a file titled "script.txt"
		try
		{
			File file = new File("scripts.txt");
			Scanner sc = new Scanner(file);

			int mission = 1;
			// While the file has more lines keep reading in
			while(sc.hasNextLine())
			{
				String getCommand = sc.nextLine();
				if(getCommand.equalsIgnoreCase("REPORT"))
				{
					System.out.println("\nMission " + mission + "\n-----------");
					report(apollo1);
					System.out.println("-----------");
					apollo1.resetPlace();
					apollo1.resetMove();
					mission++;
				}
				else
				{
					String[] inputArray = getCommand.split(" ");
					if(acceptableRange(inputArray[1]))
					{
						
						if(inputArray[0].equalsIgnoreCase("PLACE"))
						{
							place(apollo1, inputArray[1]);
						}
						else if(inputArray[0].equalsIgnoreCase("MOVE"))
						{
							move(apollo1, inputArray[1]);
						}
					}
				}
			}
			
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File doesn't exist");
		}
	}

	// If the place of the mars explorer is not an empty
	// string print out the toString method.

	public void report(MarsExplorerSimulator apollo1)
	{
		
		if(apollo1.getPlace() != "")
		{
			System.out.println(apollo1);
		}
		else
		{
			System.out.println("The Mars Explorer is not on the table and therefore no report can be made");
		}
	}

	// Check if the Mars Explorer has already been placed, and if so reset
	// the movement and place a new explorer.

	public void place(MarsExplorerSimulator apollo1, String xyCoordinates)
	{				
		apollo1.updatePlace(xyCoordinates);		
	}

	// Check if the place of the mars explorer is the desired move to space.
	// If it isn't, keep adding 1 to the x and y coordinates until
	// the destination is reached.

	public void move(MarsExplorerSimulator apollo1, String xyCoordinates)
	{
		if(!(apollo1.getPlace().equals(xyCoordinates)) && apollo1.getPlace() != "")
		{
			apollo1.updateMove(apollo1.getPlace());

			String[] coordinates = xyCoordinates.split(",");
			int desiredXValue = Integer.parseInt(coordinates[0]);
			int desiredYValue = Integer.parseInt(coordinates[1]);

			String[] originalCoordinates = apollo1.getPlace().split(",");
			int currentXValue = Integer.parseInt(originalCoordinates[0]);
			int currentYValue = Integer.parseInt(originalCoordinates[1]);

			while(desiredXValue != currentXValue && desiredYValue != currentYValue)
			{			
				if(desiredYValue != currentYValue)
				{
					currentYValue++;
					apollo1.updateMove(currentXValue + "," + currentYValue);
				}
				if(desiredXValue != currentXValue)
				{
					currentXValue++;
					apollo1.updateMove(currentXValue + "," + currentYValue);
				}
			}
			apollo1.updatePlace(xyCoordinates);
		}
	}

	// Get the x and y coordinates and check if they are within the
	// acceptable range.

	public boolean acceptableRange(String xyCoordinates)
	{		
		boolean acceptable = false;
		String[] coordinates = xyCoordinates.split(",");
		int xValue = Integer.parseInt(coordinates[0]);
		int yValue = Integer.parseInt(coordinates[1]);

		if(xValue <= 5 && xValue >= 0 && yValue <= 5 && yValue >= 0)
		{
			acceptable = true;
		}
		else
		{
			System.out.println("\nThe X and Y coordinates are not withing the acceptable range. Please make sure that they are no less than 0, and no greater than 5.");
		}

		return acceptable;
	}
}