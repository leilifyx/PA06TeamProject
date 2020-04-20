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

  public Cluster(){
	  clusterPoint=null;
	  samples=new ArrayList<Sample>();
  }

  public void addSample(Sample sample){
	  samples.add(sample);
  }
  
  public ArrayList<Sample> getSamples(){
	  return samples;
  }

  public void setClusterPoint(Sample sample){
	  clusterPoint=sample;
  }
  
  public Sample getClusterPoint(){
	  return clusterPoint;
  }
  
  public void resetSamples(){
	  for(int i=0; i<samples.size(); i++){
		  samples.remove(0);
	  }
  }
  

  public String toString(){
	 String clusterString="\n\ncluster point is: " + clusterPoint.toString();
	 clusterString+="\nsamples are:\n";
	  for(int i=0;i<samples.size();i++){
		  clusterString+=samples.get(i).toString()+"\n";
	  }
	  return clusterString;
  }

  public void createClusterPoint(int dimensions){
	  System.out.println("hell0");
    Random rand=new Random();
    double[] point=new double[dimensions];
    for(int i=0; i<point.length; i++){
      point[i]=rand.nextDouble();
    }
    Sample actualpoint=new Sample(point);
    this.clusterPoint=actualpoint;
  }

}
