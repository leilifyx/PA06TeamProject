package pa06;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

public class KMeans{
	static Cluster[] clusters;
	static Cluster originalData;


	//default constructor
	public KMeans() {
		// TODO Auto-generated constructor stub


	}

	//given file, reads file and adds samples to originalData
	public static void readFile2(File file) throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		while(scan.hasNextLine()){
			String tempLine=scan.nextLine();
			//System.out.println("\n\ntempLine is " + tempLine);
			//System.out.println("\nin first while");
			ArrayList<Double> temp=new ArrayList<Double>();
			/*for(int l=0; l<temp.size(); l++){
				if(l==0){System.out.println("printing temp");}
				System.out.print(temp.get(l)+" ");
			}*/
			addDoublesToArray(tempLine, temp);

			//System.out.println("in if");
			Sample tempSample=new Sample(temp);
			//System.out.println("Sample= " + tempSample);
			originalData.addSample(tempSample);
			}

	}

	//returns dimensions of the samples
	public static int getNumDimensions(File file)throws FileNotFoundException{
		Scanner scan=new Scanner(file);
		ArrayList<Double> temp=new ArrayList<Double>();
		if(scan.hasNextLine()){
			String tempLine=scan.nextLine();
			addDoublesToArray(tempLine, temp);
		}
		return temp.size();
	}

	//adds the doubles from a string to an arraylist, used in readFile and createOrigin
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

	//creates sample of the zero vector, which is of the correct dimensions and is set as being originalData's clusterPoint
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

	//ensure that there are no duplicates when creating the clusterPoints for clusters
	public static void setClusterPoints(Cluster[] clusters, int k){
		ArrayList<Sample> usedPoints=new ArrayList<Sample>();
		for(int j=0; j<k; j++){
			clusters[j]=new Cluster();
			clusters[j].chooseClusterPoint(originalData);
			boolean hasDuplicate=true;
			while(hasDuplicate){
				int i=0;
				for(int l=0; l<usedPoints.size(); l++){
					if(clusters[j].clusterPoint.equals(usedPoints.get(l))){
						clusters[j].chooseClusterPoint(originalData);
						i=1;
					}
				}
				if(i!=1){
					hasDuplicate=false;
				}
			}
			usedPoints.add(clusters[j].clusterPoint);
		}
	}
	
	//searches array of clusters to find which has clusterPoint that is closest to given Sample
	public static int findNearestSamplePoint(Sample sample, Cluster[] clusters){
		//Cluster tempCluster=clusters[0];
		int tempClusterNum=0;
		double Distance=100;
		for(int i=0; i<clusters.length; i++){
			double tempDistance=sample.getDistanceBetween(clusters[i].getClusterPoint(), sample);
			if(tempDistance<Distance){
				Distance=tempDistance;
				//tempCluster=clusters[i];
				tempClusterNum=i;
			}
		}
		return tempClusterNum;
		//return tempCluster;
	}

	//finds the average value of all the samples, eventually replaces clusterPoint
	public static Sample averageValueOfPointsInCluster(Cluster cluster, int dim){
		ArrayList<Sample> origSamples=cluster.getSamples();
		double[] Averages= new double[dim];
		for(int j=0; j<dim; j++){
			double temp=0;
			for(int i=0; i<origSamples.size(); i++){
				Sample tempSamp1=origSamples.get(i);
				 temp+=tempSamp1.getSampleVal(j);
			}
			Averages[j]=temp/origSamples.size();
		}
		Sample averageSample=new Sample(Averages);
		return averageSample;
	}

	//resets arraylist samples of clusters and adds samples closest to each, reseting clusterPoint in each cluster to the average value of its samples
	public static void kmeans(Cluster[] clusters, int dimensions, int k){
		Cluster[] tempClusters=new Cluster[k];
		tempClusters=clusters;
		for(int l=0; l<k; l++){
			clusters[l].resetSamples();
		}
		for(int i=0; i<originalData.samples.size(); i++){
			int kWanted= findNearestSamplePoint(originalData.samples.get(i), tempClusters);
			clusters[kWanted].addSample(originalData.samples.get(i));
		}
		for(int j=0; j<k; j++){
			clusters[j].setClusterPoint(averageValueOfPointsInCluster(clusters[j], dimensions));	
		}
		
		
	}
	
	//takes in the name of a file and the preferred number of clusters,implements kmeans to put samples from file into seperate clusters 
	public static void main(String[] args) throws FileNotFoundException{
		/*
		 * we have created our own small file, trial.txt , which we use to test this main method
		 * it is in the PA06TeamProject already if you would like to use it (feel free to change the
		 * data). We found that with data sets that were thousands of digits long, when we printed it 
		 * out it was hard to tell whether or not it worked in putting them into clusters, so we made this
		 * smaller file as a way to easily visualize what was happening. 
		 */
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
		//System.out.println(originalData);

		int dataDimensions=(getNumDimensions(file));

		setClusterPoints(clusters, k);
		
		
		
		
		/*uncoment to see original cluster points
		 for(int j=0; j<k; j++){
			System.out.print(clusters[j]);;	
		}
		*/
		
		
		/*100 is an arbitrarily large number chosen in hope that
		 * after that many repetitions the samples will be properly split into clusters
		 */
		for(int i=0; i<100; i++){
			kmeans(clusters, dataDimensions, k);
			/*
			 * to see clusters[] go through each step, uncomment
			 * for(int j=0; j<k; j++){
				System.out.print(clusters[j]);;	
			}*/
		}
		
		
		
		System.out.println("\n\noriginalData is"+originalData+"\nclusters are:");
		for(int j=0; j<k; j++){
			System.out.print(clusters[j]);;	
		}
		
		
		
		

	}
}

