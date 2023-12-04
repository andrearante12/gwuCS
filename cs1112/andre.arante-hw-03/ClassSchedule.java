/*--------------------------------------------------------------------------
GWU CSCI 1112 Fall 2023
author: <your name>

This class encapsulates the logic to model scheduling a set of classes for a university
--------------------------------------------------------------------------*/

public class ClassSchedule {
	/// Performs a deep copy of the input schedule and returns the deep copy.
	/// This operation might be used to make a permanent backup of the data
	/// as it would make a unique and unlinked copy of the data.
	/// @param schedule the schedule array to copy
	/// @return the deep copy of the schedule array that were copied
	public static String[][] clone(String[][] schedule) {
		// Allocate memory for the deep copy
		String[][] copy = new String[schedule.length][schedule[0].length];

		// Loop through each record (class info)
		for (int i = 0; i < schedule.length; i++) {
			// Allocate memory for a new class record
			String[] record = new String[schedule[0].length];
			// Loop through each string in the record 
			for (int j = 0; j < schedule[i].length; j++) {
				record[j] = schedule[i][j];
			}
			copy[i] = record;
		}
		return copy;
	}

	/// A referential copy (shallow copy of each row) and not an 
	/// element-wise copy (deep copy).  We are sorting elements with respect
	/// to the original data rather than generating a new set of data.
	/// @param schedule data containing the rows to reference
	/// @return an array containing a shallow copy of the input schedule 
	///         rows
	public static String[][] createView(String[][] schedule) {
		// Allocate memory for a new view
		String[][] view = new String[schedule.length][schedule[0].length];
		// Loop through each class record
		for (int i = 0; i < schedule.length; i++) {
			view[i] = schedule[i];
		}

		return view;
	}

	//--------------------------------------------------------------------- 
	/// Compute the differential between start time (index 3) and end time 
	/// (index 4). The differential is not maintained in the data but is
	/// a virtual field derived by the calculation performed here
	/// @param classInfo a record from the scheduling data
	/// @return the difference in time between the end time and start time
	///         in minutes
	public static int differential(String[] classInfo) {
		return duration(classInfo[4]) - duration(classInfo[3]);
	}

	//--------------------------------------------------------------------- 
	/// This utility function converts a time string from the "HH:mm:ss" 
	/// format into a value representing minutes
	/// @param time a string representing a time in "HH"mm:ss" format
	/// @return an integer representing the time converted to minutes
	private static int duration(String time) {
		String[] tokens = time.split(":");
		int h = Integer.parseInt(tokens[0]);
		int m = Integer.parseInt(tokens[1]);
		int t = h * 60 + m;
		return t;
	}

	//--------------------------------------------------------------------- 
	/// Performs a comparison between two classes that is equivalent to a 
	/// less than operation so that a sort can use this function to order 
	/// classes. The less than criteria is an evaluation between the 
	/// differentials of two classes.
	/// @param class1 a class record that is used as the "left" operand for
	///        a less than comparison 
	/// @param class2 a class record that is used as the "right" operand for
	///        a less than comparison 
	/// @return returns true if the computed differential for class1 is less
	///         than the computed differential for class2; otherwise, 
	///         returns false (false implies that differential for class1 is
	///         greater than or equal to class2)
	public static boolean lessThan(String[] class1, String[] class2) {
		int duration1 = differential(class1); 
		int duration2 = differential(class2);

		return duration1 < duration2;
	}
	//--------------------------------------------------------------------- 
	/// Swaps references to classes.  Note that this is a "shallow" swap and
	/// not a "deep" swap meaning we swap at a reference level (between rows
	/// in view) and not at the value level
	/// @param view A shallow copy of a set of classes 
	/// @param i the index of the first reference to swap
	/// @param j the index of the second reference to swap
	public static void swapClasses(String[][] view, int i, int j) {
		// Create a temp storage for the class at index i
		String[] temp = view[i];
		view[i] = view[j];
		view[j] = temp;
	}

	//--------------------------------------------------------------------- 
	/// Sorts (shallow) a set of references to classes in descending order 
	/// subject to the differential between ups and downs using selection 
	/// sort
	/// @param view A shallow copy of a set of classes 
	/// @return an array of profile information of 3 buckets with the 
	///         respective buckets containing a count of 0: allocations, 
	///         1:comparisons, and 2: swaps
	public static int[] sortSelection(String[][] view) {

		// profile[0:allocs (ignore profile), 1:comparisons, 2:swaps]
		int[] profile = new int[3];
		int best, index;
		profile[0]+=3;

		// Outer loop, responsible for indicating the start of the sorted list i
		for (int i = 0; i < view.length-1; i++) {
			// The start of the unsorted list is initialized as the "best"
			best = differential(view[i]);
			index = i;
			// Inner loop, responsible for moving through the unsorted list
			for (int j = i + 1; j < view.length; j++) {
				// Increment comparisons counter by one
				profile[1]++;

				// Compare each value in unsorted list to "best"
				if (differential(view[j]) > best) {
					best = differential(view[j]);
					index = j;
				}
			}

			// Swap the first item in unsorted list with best, unless they are the same
			if (index != i) {
				String[] temp = view[i];
				view[i] = view[index];
				view[index] = temp;

				// Increment swaps counter by one
				profile[2]++;
			}

			// Outer loop will iterate by one, marking the previously swapped value as "sorted"
		}

		return profile;
	}

	//--------------------------------------------------------------------- 
	/// Sorts (shallow) a set of references to classes in descending order 
	/// subject to the differential between ups and downs using bubble 
	/// sort
	/// @param view A shallow copy of a set of classes 
	/// @return an array of profile information of 3 buckets with the 
	///         respective buckets containing a count of 0: allocations, 
	///         1:comparisons, and 2: swaps
	public static int[] sortBubble(String[][] view) {

		// profile[0:allocs (ignore profile), 1:comparisons, 2:swaps]
		int[] profile = new int[3];

		// Swapped acts as our exit condition, if we ever move through the 
		//	array without swapping, array MUST be sorted
		profile[0]+=3; // for "swapped" boolean var on line 170 and i, j
		for (int i = 0; i < view.length; i++) {
			boolean swapped = false;

			// Bound for inner loop (-i): bc each iteration i items will be "sorted"
			// 						(-1): because we are doing a comparisson between
			//								item at j and j+1
			for (int j = 0; j < view.length - i - 1; j++) {
				// Increment comparision counter
				profile[1]++;

				// If first < second swap
				if (differential(view[j]) < differential(view[j + 1])) {
					String[] temp = view[j];

					view[j] = view[j+1];
					view[j+1] = temp;

					// Incrememnt swap counter
					profile[2]++;

					swapped = true;
				} 
			}

			// If no swap was conducted, we can assume that list is sorted already
			if (!swapped) { return profile; }
		}		

		return profile;
	}

	//--------------------------------------------------------------------- 
	/// Sorts (shallow) a set of references to classes in descending order 
	/// subject to the differential between ups and downs using insertion 
	/// sort
	/// @param view A shallow copy of a set of classes 
	/// @return an array of profile information of 3 buckets with the 
	///         respective buckets containing a count of 0: allocations, 
	///         1:comparisons, and 2: swaps
	public static int[] sortInsertion(String[][] view) {

		// profile[0:allocs (ignore profile), 1:comparisons, 2:swaps]
		int[] profile = new int[3];

		profile[0] += 3; // increment for i, j, and temp
		// Loop through view "inserting" as you go along
		for (int i = 0; i < view.length; i++) {
			// While the previous value is smaller than inserting value, swap
			//	so that the inserting value moves down one spot
			for (int j = i; ( (j > 0) &&  differential(view[j]) > differential(view[j-1]));  j--) {
				String[] temp = view[j];
				view[j] = view[j-1];
				view[j-1] = temp;

				// Increment comparison, swap
				profile[1]++;
				profile[2]++;
			}
		}

		return profile;
	}

	//--------------------------------------------------------------------- 
	/// Sorts (shallow) a set of references to classes in descending order 
	/// subject to the differential between ups and downs using a quicksort.
	/// @param view A shallow copy of a set of classes 
	/// @return an array of profile information of 3 buckets with the 
	///         respective buckets containing a count of 0: allocations, 
	///         1:comparisons, and 2: swaps
	public static int[] sortQuicksort(String[][] view) {

		// profile[0:allocs (ignore profile), 1:comparisons, 2:swaps]
		int[] profile = new int[3];

		quickSortRecursive(view, 0, view.length-1, profile);

		return profile;
	}

	//---------------------------------------------------------------------
	/// Recursively partition in order to find the right place for the left most
	/// element in class in the list
	/// @param	view	an array containing arrays that represent class records
	/// @param	left	the lower bound index of the subarray we are working with
	/// @param	right	the upper bound index of the subarray we are working with
	/// @param	profile	an int[] used for tracking the number of swaps, alloc, and comparrisons
	static void quickSortRecursive(String[][] view, int left, int right, int[] profile) {
		if (left < right) {
			int partition = quickSortPartition(view, left, right, profile);

			// Left Side
			profile[0]++;
			quickSortRecursive(view, left, partition-1, profile);

			// Right Side
			profile[0]++;
			quickSortRecursive(view, partition+1, right, profile);
		}
	}

	//---------------------------------------------------------------------
	/// Checks each element within [left, right] subarray and move it to the right of
	/// parition element if < partition element
	/// @param	view	an array containing arrays that represent class records
	/// @param	left	the lower bound index of the subarray we are working with
	/// @param	right	the upper bound index of the subarray we are working with
	/// @param	profile	an int[] used for tracking the number of swaps, alloc, and comparrisons
	/// @return an int that represents the index of partition

	static int quickSortPartition(String[][] view, int left, int right, int[] profile) {
		if (left == right) { return left; }

		// Select the partition element to be the rightmost element
		String[] partitionElement = view[right];
		int swapPosition = right;
		profile[0]++;

		// Examine each element between left and right-1
		for (int i = right-1; i >= left; i--) {
			// If view[i] > partitionElement, swap with swap position to move it to
			// the right position
			// Incremenet comparrisions
			profile[1]++;
			if (lessThan(view[i], partitionElement)) {
				swapPosition--;
				swapClasses(view, swapPosition, i);
				// Increment swaps
				profile[2]++;
			}	
		}
		// Last Swap
		swapClasses(view, swapPosition, right);
		profile[2]++;
		return swapPosition;
	}

}
