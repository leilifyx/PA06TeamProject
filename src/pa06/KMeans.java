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
			
			while(tempLine.length()>0){
				if((int)tempLine.charAt(0)>=48 && (int)tempLine.charAt(0)<=57)//ascii between 48 and 57 are digits 0-9
				{
					int isNum=1;
					while(isNum<tempLine.length() && ((int)tempLine.charAt(isNum)>=48 &&(int)tempLine.charAt(isNum)<=57)){
						isNum++;
					}
					String tempDoubleString=(tempLine.substring(0,isNum));
					System.out.println("IsNum= " + isNum);
					int tempDouble=0;
					for (int t=0; t<isNum; t++){
						tempDouble+=((int)tempDoubleString.charAt(t)-48)*Math.pow(10, isNum-t-1);
					}
					temp.add((double)(tempDouble));
					for(int l=0; l<temp.size(); l++){
						if(l==0){System.out.println("printing temp");}
						System.out.print(temp.get(l)+" ");
					}
					
					
					tempLine=tempLine.substring(isNum,tempLine.length());
					System.out.println("now tempLine is "+ tempLine);
				}
				else{
					tempLine=tempLine.substring(1,tempLine.length());
				}
			}
			System.out.println("in if");
			Sample tempSample=new Sample(temp);
			System.out.println("Sample= " + tempSample);
			originalData.addSample(tempSample);
			}
		
	}

	public static void readFile(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		System.out.println("in method");
		while(scan.hasNextLine()){
			String tempLine=scan.nextLine();
			System.out.println(tempLine);
			System.out.println("in first while");
			ArrayList<Double> temp=new ArrayList<Double>();
			for(int l=0; l<temp.size(); l++){
				System.out.println("printing temp");
				System.out.print(temp.get(l)+" ");
			}

			while(scan.hasNextDouble()){
				Double td = scan.nextDouble(); //td stands for tempDouble
				System.out.println("next double " + td);
				temp.add(td);
				for(int l=0; l<temp.size(); l++){
					if(l==0){System.out.println("printing temp");}
					System.out.print(temp.get(l)+" ");
				}
				if(scan.next().charAt(0)=='\n'){
					System.out.println("in if");
					System.out.println("new line read.");
					Sample tempSample=new Sample(temp);
					System.out.println(tempSample);
					originalData.addSample(tempSample);
					break;
			}



			}
		}
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
				System.out.println("IsNum= " + isNum);
				int tempDouble=0;
				for (int t=0; t<isNum; t++){
					tempDouble+=((int)tempDoubleString.charAt(t)-48)*Math.pow(10, isNum-t-1);
				}
				temp.add((double)(tempDouble));
				for(int l=0; l<temp.size(); l++){
					if(l==0){System.out.println("printing temp");}
					System.out.print(temp.get(l)+" ");
				}
				
				
				tempLine=tempLine.substring(isNum,tempLine.length());
				System.out.println("now tempLine is "+ tempLine);
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
			for(int z=0;z<originArray.size(); z++){
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



	}

}
