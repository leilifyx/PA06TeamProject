package pa06;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class KMeans {
	static Cluster[] clusters;
	static Cluster originalData;




	public KMeans() {
		// TODO Auto-generated constructor stub

	}

	public static void readFile(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		while(scan.hasNextLine()){
			ArrayList<Double> temp=new ArrayList<Double>();

			while(scan.hasNextDouble()){
				Double td = scan.nextDouble();
				System.out.println("next double" + td);
				temp.add(td);
				if(scan.next().charAt(0)=='\n'){
					Syste.out.println("new line read.");
					Sample tempSample=new Sample(temp);
					System.out.println(tempSample);
					originalData.addSample(tempSample);
					break;
			}



			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanning=new Scanner(System.in);
		System.out.println("enter file's name");
		String fileName=scanning.nextLine();
		System.out.println("How many clusters?");
		int k=scanning.nextInt();
		clusters=new Cluster[k];
		File file= new File(fileName);
		readFile(file);
		System.out.println(originalData);



	}

}
