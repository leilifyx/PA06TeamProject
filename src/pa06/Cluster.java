package pa06;
import java.util.ArrayList;
import java.util.Random;

/**
 * A cluster is a cluster point (which is itself a sample)
 * and a list of Samples (the one's closest to that sample point, ideally).
 * @author presenting
 *
 */
public class Cluster {

  Sample clusterPoint;
  ArrayList<Sample> samples;

  //constructor, clusterPoint starts as null, samples is an ArrayList of Samples
  public Cluster(){
	  clusterPoint=null;
	  samples=new ArrayList<Sample>();
  }

  //given a Sample, adds it to ArrayList samples
  public void addSample(Sample sample){
	  samples.add(sample);
  }

  //returns samples
  public ArrayList<Sample> getSamples(){
	  return samples;
  }

  //given a sample, allows user to set clusterPoint
  public void setClusterPoint(Sample sample){
	  clusterPoint=sample;
  }

  //this method returns clusterPoint
  public Sample getClusterPoint(){
	  return clusterPoint;
  }

  //empties arraylist samples when necessary
  public void resetSamples(){
	  samples.clear();
  }
  
//this method chooses a random Sample from an arraylist within a given cluster and sets it as the clusterPoint
  public void chooseClusterPoint(Cluster data){
    Random rand=new Random();
    int num=rand.nextInt(data.samples.size());
    this.clusterPoint=data.samples.get(num);
  }
  
  public boolean equals(Cluster other){
	  //must have same clusterPoint and samples to be equal
	  if(!(this.clusterPoint.equals(other.getClusterPoint()))){
		  return false;
	  }
	  if(this.samples.size()!=other.samples.size()){
		  return false;
	  }
	  for (int i=0; i<this.samples.size(); i++){
		  if(this.samples.get(i)!=other.samples.get(i)){
			  return false;
		  }  
	  }
	  return true;
  }


  public String toString(){
	 String clusterString="\n\ncluster point is: " + clusterPoint.toString();
	 clusterString+="\nsamples are:\n";
	  for(int i=0;i<samples.size();i++){
		  clusterString+=samples.get(i).toString()+"\n";
	  }
	  return clusterString;
  }

  
}
