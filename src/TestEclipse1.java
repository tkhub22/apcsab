
public class TestEclipse1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("helllo");
		
		int [] array = new int [] {0,1,2,3,4,10,9,7};
		
		System.out.println(isHeap(array));
	}

	public static boolean isHeap(int[] array) {
		int len = array.length-1;
		boolean flag = false;
		for(int i = len; i >1; i--)
		{
			int parent = i/2;
			if(array[i] >= array[parent])
					flag = true;
			else
				return false;
					
		}
		return flag;
	}
	
	

}
