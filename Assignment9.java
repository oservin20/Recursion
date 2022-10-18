
// Name: Osman Servin


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Assignment9 
{

    public static void main(String[] args) 
    {
    	// declare variables
    	int[] nums;
        char choice;
        String inputInfo = new String();
        String line = new String();
        int choiceSelected;
        
    		try
    		{
    			//InputStreamReader isr = new InputStreamReader(System.in);
    			//BufferedReader in = new BufferedReader(isr);
    			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    			
    			do 
    			{
    				// PRINTS THE MENU
    				printMenu();
    				// read the choice from the user
    				// read the line and get the charAt(0)
                    inputInfo = in.readLine().trim();
    				choiceSelected = Integer.parseInt(inputInfo);
                    
                    if (choiceSelected > 5 || choiceSelected < 1)
                    {
                    	System.out.print("\nPlease choose a number between 1 and 5.\n");
                    }
                    
                    choice = inputInfo.charAt(0);
                    // choice = Character.toUpperCase(inputInfo); // NOT NEEDED SINCE CHOICES ARE NUMBERS
                    
                    switch(choice)
    				{
    					case '1':
    						nums = parseInts(in); // sets up our array “nums” , “in” of type BufferedReader
    						System.out.print("The largest number in the array is: ");
    						System.out.print(findLargest(nums, 0, nums.length - 1 ));  // calls the recursive method
    						System.out.print("\n");
    						break;

    						
    					case '2':
    						nums = parseInts(in); // sets up our array “nums” , “in” of type BufferedReader
    						System.out.print("The product of all prime numbers in the array is: ");
    						System.out.print(primeProduct(nums, 0, nums.length -1 ));
    						System.out.print("\n");
    						break;
    					
    						
    					case '3':
    						nums = parseInts(in); // sets up our array “nums” , “in” of type BufferedReader
    						System.out.print("The largest sum of digits in the array is: ");
    						System.out.print(sumOfDigits(nums, 0, nums.length -1, 0));
    						System.out.print("\n");
    						break;

    						
    					case '4':
    						
    						break;

    						
    					case '5':
    						break;
    				}
    				
    			} while (choice != '5');
    		}
    		
    		catch (IOException e)
    		{
    			System.out.println("IO Exception");
    		}
    }


    // Utility method for printing the menu 
    public static void printMenu() 
    {
        System.out.print("\nWhat would you like to do?\n\n");
        System.out.print("1: Find the largest number in an array of integers\n");
        System.out.print("2: Calculate the product of all prime numbers in an array of integers\n");
        System.out.print("3: Find the element with the largest sum of digits in an array of integers\n");
        System.out.print("4: Remove adjacent duplicate characters in a String\n");
        System.out.print("5: Quit\n");
    }

    
    // utility method for parsing integers from standard input
    public static int[] parseInts(BufferedReader reader) 
    {
        String line = "";
        ArrayList<Integer> container = new ArrayList<>();
        try 
        {
            System.out.print("\nPlease enter integers:\n");
            line = reader.readLine();
            int num = Integer.parseInt(line);

            while (num > 0) 
            {
                container.add(num);
                line = reader.readLine();
                num = Integer.parseInt(line);
            }
        }
        catch (IOException ex) 
        {
            System.out.println("IO Exception");
        }

        int[] result = new int[container.size()];
         for(int i = 0; i < container.size(); i++)
         {
             result[i] = container.get(i);
         }
        return result;
    }

    
    // RECURSIVE METHOD TO FIND THE LARGEST VALUE IN THE ARRAY
    public static int findLargest(int[] list, int lowerIndex, int upperIndex)
    {
       int max;
       
       if (lowerIndex == upperIndex) 	// CHECKS IF ALL VALUES IN ARRAY HAVE BEEN CHECKED
       { 
    	   return list[lowerIndex];		// BASE CASE 	
       }
       else
       {
    	   max = findLargest(list, lowerIndex + 1, upperIndex); // CALLS ITSELF TO CHECK FOR NEXT VALUE
           if (list[lowerIndex] >= max)							// COMPARES THE PREVIOUS LARGEST NUMBER TO NEW ONE
        	   return list[lowerIndex];
           else
        	   return max;
       }
    }
    
    
    // RECURSIVE METHOD THAT CALCULATES THE PRODUCT OF ALL PRIME NUMBERS IN ARRAY]
    public static int primeProduct(int[] list, int lowerIndex, int upperIndex)
    {
    	int product = 1;												// WILL STORE THE PRODUCT OF PRIME NUMBERS IN THE ARRAY
    	int factor = 2;													//WILL BE USED TO CHECK IF NUMBER IS PRIME
    	ArrayList<Integer> primeNumbersList = new ArrayList<>();		// WILL STORE ALL PRIME NUMBERS
    	boolean primeNumber; 
    	
    	
    	// BASE CASE
    	if (lowerIndex == upperIndex + 1)		// IF ALL VALUES IN ARRAY ARE CHECKED RETURN 1		
    	{
    		return 1;							// BASE CASE
    	}
    	// RECURSIVE CASE
    	else
    	{
    		primeNumber = isPrime(list[lowerIndex], factor); // CALLS THE UTILITY METHOD TO CHECK IF NUMBER IS PRIME
    		if (primeNumber == true)						// IF NUMBER IS PRIME THEN EXECUTE FOLLWING CODE
    		{
    			product = list[lowerIndex];					// CHANGES PRODUCT TO THE VALUE AT INDEX
    			primeNumbersList.add(list[lowerIndex]);		// ADDS THE PRIME NUMBERS TO AN ARRAYLIST
    		}
    		
    		if (primeNumbersList.size() != 0 || product == 1)		// IF THERE ARE PRIME NUMBERS THEN EXECUTE
    		{
    			product =  product * primeProduct(list, lowerIndex + 1, upperIndex);	// CALLS ITSELF TO CHECK FOR NEXT VALUE IN ARRAY
    			return product;
    		}
    		else
    		{
    			return 1;
    		}
    	}
    }
    // RECURSIVE METHOD THAT WILL CALCULATE IF A NUMBER IS PRIME, HELPER METHOD FOR primeProduct METHOD
    public static boolean isPrime(int numberToCheck, int factor)
    {
    	boolean isPrime = true;		// WILL BE USED TO RETURN IF THE VALUE IS A PRIME OR NOT
    	
    	if (factor <= numberToCheck / 2)	// ONLY WANT TO CHECK FOR HALF OF THE NUMBERS SINCE RESULTS WILL JUST BE DUPLICATED
        {
    		if (numberToCheck % factor == 0)		// CHECK IF THE NUMBER IS NOT PRIME
    		{
    			isPrime = false;					// IF NUMBER IS NOT PRIME IT RETURNS FALSE
    			return isPrime;
    		}
    		else 
    		{
    			isPrime = isPrime(numberToCheck, factor + 1);	// IF THE NUMBER IS PRIME IT CALLS THE METHOD AGAIN TO KEEP CHECKING
    			return isPrime;									// EITHER RETURNS TRUE OR FALSE DEPENDING IF NUMBER PASSES THE CASES
    		}
        }
    	else
    	{
    		if (numberToCheck <= 1)		// IF THE NUMBER IS LESS THAN OR EQUAL TO ONE THE NUMBER ISNT PRIME
    		{
    			return false;
    		}
    		else
    		{
    			return true;		// RETURNS TRUE
    		}
    	}
    }
    

    // RECURSIVE METHOD THAT WILL COMPARE ALL OF THE 
    public static int sumOfDigits(int[] list, int lowerIndex, int upperIndex, int maxSum)
    {
    	int maxDigitSum = maxSum;
    	
    	if (lowerIndex == upperIndex)
    	{
    		return maxDigitSum;
    	}
    	else
    	{
    		int digitSum = summationOfDigits(list[lowerIndex]);	// PASSES THE VALUE AT A CERTAIN INDEX OF THE ARRAY TO UTILITY METHOD
    	
    		if (digitSum > maxDigitSum)
    		{
    			maxDigitSum = digitSum;
    		}
    		
    		return sumOfDigits(list, lowerIndex + 1, upperIndex, maxDigitSum);
    	}
		    			
    }
    // HELPER METHOD FOR sumOfDigits THAT WILL BE ADDING THE DIGITS
    public static int summationOfDigits(int numberToSum)
    {
    	int sum = 0;
    	int digit = numberToSum;
    	
    	if (numberToSum == 0)
    	{
    		return sum;			// BASE CASE FOR THIS RECURSIVE METHOD
    	}
    	else 
    	{
    		sum = digit % 10 + summationOfDigits(numberToSum / 10);		// sum = previous sum + the digits
    		//numberToSum = numberToSum / 10;		// REMOVES THE FIRST DIGIT IN ORDER TO GET THE NEXT DIGIT IN VALUE
    		//summationOfDigits(numberToSum / 10);	// FUNCTIONS THE SAME AS THE LINE ABOVE!! AND CALLS FUNCTION AGAIN
    		
    		return sum;							// RETURNS THE SUM
    	}
    }
    
    
    
    
}








