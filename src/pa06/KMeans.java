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

	public static void readFile2(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		while(scan.hasNextLine()){
			String tempLine=scan.nextLine();
			System.out.println("\n\ntempLine is " + tempLine);
			System.out.println("\nin first while");
			ArrayList<Double> temp=new ArrayList<Double>();
			for(int l=0; l<temp.size(); l++){
				if(l==0){System.out.println("printing temp");}
				System.out.print(temp.get(l)+" ");
			}
			addDoublesToArray(tempLine, temp);
			
			System.out.println("in if");
			Sample tempSample=new Sample(temp);
			System.out.println("Sample= " + tempSample);
			originalData.addSample(tempSample);
			}

	}

	

	public static int getNumDimensions(File file)throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		ArrayList<Double> temp=new ArrayList<Double>();
		if(scan.hasNextLine()){
			String tempLine=scan.nextLine();
			addDoublesToArray(tempLine, temp);
		}
		return temp.size();
	}

	public static void addDoublesToArray(String tempLine, ArrayList<Double> temp){
		while(tempLine.length()>0){

			if((int)tempLine.charAt(0)>=48 && (int)tempLine.charAt(0)<=57)//ascii between 48 and 57 are digits 0-9
			{
				int isNum=1;
				while(isNum<tempLine.length() && ((int)tempLine.charAt(isNum)>=48 &&(int)tempLine.charAt(isNum)<=57)){
					isNum++;
				}
				String tempDoubleString=(tempLine.substring(0,isNum));

				int tempDouble=0;
				for (int t=0; t<isNum; t++){
					tempDouble+=((int)tempDoubleString.charAt(t)-48)*Math.pow(10, isNum-t-1);
				}
				temp.add((double)(tempDouble));
				tempLine=tempLine.substring(isNum,tempLine.length());

			}
			else{
				tempLine=tempLine.substring(1,tempLine.length());
			}
		}
	}

	public static Sample createOrigin(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		ArrayList<Double> originArray=new ArrayList<Double>();
		if(scan.hasNextLine()){
			String tempLine=scan.nextLine();
			ArrayList<Double> temp=new ArrayList<Double>();
			addDoublesToArray(tempLine, temp);
			for(int z=0;z<temp.size(); z++){
				originArray.add((double)0);
			}
		}
		Sample originSample=new Sample(originArray);
		return originSample;

	}

	public static void main(String[] args) throws FileNotFoundException{
		Scanner scanning=new Scanner(System.in);
		System.out.println("enter file's name");
		String fileName=scanning.nextLine();
		System.out.println("How many clusters?");
		int k=scanning.nextInt();
		clusters=new Cluster[k];
		
		
		originalData=new Cluster();

		File file= new File(fileName);
		readFile2(file);
		
		

		Sample origin= createOrigin(file);
		originalData.setClusterPoint(origin);
		System.out.println(originalData);
		
		int dataDimensions=(getNumDimensions(file));
		
		for(int j=0; j<k; j++){
			clusters[j].createClusterPoint(dataDimensions);
			
		}
		
		System.out.println("\n\nclusters= "+ clusters);



	}

}
