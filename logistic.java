package application;

import java.util.ArrayList;
import java.util.List;

import weka.classifiers.functions.Logistic;
import weka.core.Instances;

public class logistic {
	Logistic L;
	Instances data;
	
	logistic(Instances data, int time){
		L = new Logistic();
		this.data = data;
		String[] opt = {"-M",String.valueOf(time)}; 
		
		try {
			L.setOptions(opt);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*for(int i = 0; i < L.getOptions().length;i++) {
			System.out.println(L.getOptions()[i]);
		}*/

		
		try {
			L.buildClassifier(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public double getAccuracy() {
		double out0,out1,out2;
		double errors = 0;
		
		for(int nr = 0; nr < data.numInstances(); nr++) {
			
			try {
				out0 = L.distributionForInstance(data.instance(nr))[0];
				out1 = L.distributionForInstance(data.instance(nr))[1];
				out2 = L.distributionForInstance(data.instance(nr))[2];
			
			
			if(out0 > out1 && out0 > out2){
				//System.out.println(nr + " A");
				if(data.instance(nr).classValue() != 0.0)
					errors++;
			}else if(out1 > out0 && out1 > out2) {
				//System.out.println(nr + " B");
				if(data.instance(nr).classValue() != 1.0)
					errors++;
			}else {
				//System.out.println(nr + " C");
				if(data.instance(nr).classValue() != 2.0)
					errors++;
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 100 - (errors/300) * 100;
	}
	
	public List<spMemo> createList(){
		List<spMemo> out = new ArrayList<spMemo>();
		double desition;
		double out0,out1,out2;
		
		for(int nr = 0; nr < data.numInstances(); nr++) {
			try {
				out0 = L.distributionForInstance(data.instance(nr))[0];
				out1 = L.distributionForInstance(data.instance(nr))[1];
				out2 = L.distributionForInstance(data.instance(nr))[2];
				if(out0 > out1 && out0 > out2){
					desition = 0.0;
				}else if(out1 > out0 && out1 > out2) {
					desition = 1.0;
				}else {
					desition = 2.0;
				}
				
				
				out.add(new spMemo(Double.parseDouble(data.get(nr).toString().split(",")[0]), Double.parseDouble(data.get(nr).toString().split(",")[1]), desition));
				//System.out.println(Double.parseDouble(data.get(nr).toString().split(",")[0]) + " " + Double.parseDouble(data.get(nr).toString().split(",")[1]));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return out;
	}
	
	public int[][] confusionMatrix(){
		int[][] matrix = new int[3][3];
		double desition,out0,out1,out2;
		
		for(int nr = 0; nr < data.numInstances(); nr++) {
			try {
				
				out0 = L.distributionForInstance(data.instance(nr))[0];
				out1 = L.distributionForInstance(data.instance(nr))[1];
				out2 = L.distributionForInstance(data.instance(nr))[2];
				if(out0 > out1 && out0 > out2){
					desition = 0.0;
				}else if(out1 > out0 && out1 > out2) {
					desition = 1.0;
				}else {
					desition = 2.0;
				}
				

				matrix[(int) desition][(int) data.instance(nr).classValue()]++;
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return matrix;
	}
	
	
}
