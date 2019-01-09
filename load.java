package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffLoader.ArffReader;

public class load {
	
	private Instances data;
	
	load(String path_data){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path_data));
			ArffReader arff = new ArffReader(reader);
			data = arff.getData();
			data.setClassIndex(data.numAttributes() - 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Instances getData() {
		return data;
	}
	
}
