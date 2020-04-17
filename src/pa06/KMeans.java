package pa06;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class KMeans {
	static Cluster[] clusters;
	Cluster originalData;
	
	
	
	public KMeans() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void Main(String[] args) throws FileNotFoundException{
		Scanner scan=new Scanner(System.in);
		System.out.println("enter file's name");
		String fileName=scan.nextLine();
		System.out.println("How many clusters?");
		int k=scan.nextInt();
		clusters=new Cluster[k];
		File file= new File(fileName);
		
		
	}

}
