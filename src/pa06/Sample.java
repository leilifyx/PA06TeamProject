package pa06;

import java.util.ArrayList;

/**
 * A Sample represents a vector of doubles to be used in a clustering algorithm...
 * @author presenting
 *
 */

public class Sample {
	ArrayList<Double> sample;
	
	public Sample(double[] values) {
		this.sample = new ArrayList<Double>();
		for (int i=0; i<values.length; i++) {
			sample.add(values[i]);
		}
		
	}
	
	public String toString(){
		String toPrint="{";
		for (int i=0; i<this.sample.size(); i++) {
			if(i==0){
				toPrint+=this.sample.get(i);
			}
			else{
				toPrint+=", "+this.sample.get(i);
			}
			}
		toPrint+="}";
		return toPrint;
	}
	
	
	public int getSize(){
		return this.sample.size();
	}
	
	public double getValueAt(int index){
		return this.sample.get(index);
	}
	
	public static double getDistanceBetween(Sample a, Sample b){
		int aSize=a.getSize();
		int bSize=b.getSize();
		if(aSize!=bSize){
			System.out.println("These samples aren't in the same dimension");
		}
		
		double squared=0;;
		for(int i=0; i<aSize; i++){
			squared+=Math.pow(a.getValueAt(i)-b.getValueAt(i), 2);
		}
		double distanced=Math.sqrt(squared);
		return distanced;
	}
	
	public static void main(String[] args) {
		System.out.println("testing for the Sample class.");
		double[] p1 = {1d, 2d, 3.14, 2.71};
		double[] p2 = {0.1, 1.1, 2.1, 3.1};
		Sample s1 = new Sample(p1);
		Sample s2 = new Sample(p2);
		System.out.println("s1="+s1);
		System.out.println("s2="+s2);
		System.out.println("distance between=" + getDistanceBetween(s1,s2));
		
	}

}
