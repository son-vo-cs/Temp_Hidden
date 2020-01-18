
/*
 * Given an array of positive integers, divide in two parts A and B such the sum of 
 * elements in A is strictly greater than the sum of elements in B, also A and B must
 *  be disjoint and A should be of minimum size possible. If there are multiple 
 *  possiblities for A, return the one with highest sum.
Example : Let given arr is [8,1,2,9,1] then A = [9,8].
 */
import java.util.*;
public class devide {
	
	static int sum_result = 0;
	static int size_result = Integer.MAX_VALUE;
	static String str_result = "";
	static String[][][] memo;
	public static void helper(int[] arr, Map<Integer,Integer> freq, int currentSum, int total, int prev_index, int index, int size, String str)
	{
		if (index >= arr.length)
			return;
		if (prev_index != -1 && memo[prev_index+1][index][0] != null && memo[prev_index+1][index][0].length()>0)
		{

			int temp_size = Integer.valueOf(memo[prev_index+1][index][0]);
			if (size >= size_result)
			{
				System.out.println("haha");
				return;
			}
//			System.out.println(str + " : " + arr[index] + " result " + str_result);
//			if (memo[prev_index+1][index][3].length()==0)
//			{
//				System.out.println("haha");
//				return;
//			}
//			size = Integer.valueOf(memo[prev_index+1][index][0]);
//			currentSum = Integer.valueOf(memo[prev_index+1][index][1]);
//			str = memo[prev_index+1][index][2];
		}
		int current_element = arr[index] * freq.get(arr[index]);
		int temp_sum = current_element + currentSum;
		int temp_size = size + freq.get(arr[index]);
		if (temp_sum > total - temp_sum)
		{
			if (temp_size < size_result || ((temp_size == size_result) && temp_sum > sum_result))
			{
				size_result = temp_size;
				str_result = str + "-" + arr[index] + "-";
				sum_result = temp_sum;
				memo[prev_index+1][index][0] = "" + size_result;
				memo[prev_index+1][index][1] = "" + sum_result;
				memo[prev_index+1][index][2] = str_result;
				memo[prev_index+1][index][3] = "1";
			}
		}
		// taken
		
		helper(arr, freq, currentSum + current_element, total, index,index+1, temp_size, str + "-"+ arr[index]);
		// not taken
		if (currentSum > total - currentSum)
		{
			if (size < size_result || ((size == size_result) && currentSum > sum_result))
			{
				size_result = size;
				str_result = str;
				sum_result = temp_sum;
				memo[prev_index+1][index][0] = "" + size_result;
				memo[prev_index+1][index][1] = "" + sum_result;
				memo[prev_index+1][index][2] = str_result;
				memo[prev_index+1][index][3] = "";
			}
		}
		
		helper(arr,freq, currentSum, total, prev_index,index+1, size, str);
	}
	
	public static void main(String[] args)
	{
		int[] arr = {1,6,6,6,9,9,3,2,1,3,3,4};
		Map<Integer,Integer> freq = new HashMap<Integer,Integer>();
		for (int num : arr)
			freq.put(num,freq.getOrDefault(num,0) + 1);
		int[] new_arr = new int[freq.size()];
		int i = 0;
		int sum = 0;
		for (int val : freq.keySet())
		{
			new_arr[i++] = val;
			sum += val * freq.get(val);
			System.out.println(val + " : " + freq.get(val));
		}

		memo = new String[arr.length+1][arr.length][4]; // size, sum, str
		helper(new_arr,freq,0,sum,-1,0,0, "");
		System.out.println(str_result);
		System.out.println(size_result);
		System.out.println(sum);

	}
}
