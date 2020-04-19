package pa06;
import java.util.ArrayList;

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

  public String toString(){
	  String clusterString="cluster point is: " + clusterPoint.toString();
	  clusterString+="\nsamples are:\n";
	  for(int i=0;i<samples.size();i++){
		  clusterString+=samples.get(i).toString()+"\n";
	  }
	  return clusterString;
  }

}
