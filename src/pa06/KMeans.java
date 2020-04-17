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
	
	public void readFile(File file){
		Scanner scan=new Scanner(file);
		while(scan.hasNextLine()){
			while(scan.hasNextDouble()){
				
			}
		}
	}
	
	public static void Main(String[] args) throws FileNotFoundException{
		Scanner scanning=new Scanner(System.in);
		System.out.println("enter file's name");
		String fileName=scanning.nextLine();
		System.out.println("How many clusters?");
		int k=scanning.nextInt();
		clusters=new Cluster[k];
		File file= new File(fileName);
		Scanner scan=new Scanner(file);
		
		
	}

}
