package application;

import weka.core.Instance;
import weka.core.Instances;

public class test {

	public static void main(String[] args) {
		Instances testdata;
		
		load DA = new load("C:\\Users\\TomasHägg\\Desktop\\spiral\\spiral.arff");
		
		testdata = DA.getData();
		
		//System.out.print(testdata);
		//System.out.println(testdata.numInstances());
		//System.out.println(testdata.get(1).toString().split(",")[0]);
		//System.out.println(testdata.get(1).toString().split(",")[1]);
		//System.out.print(testdata);
		//System.out.println(testdata.get(10).classValue());
		
		//multiPerceptron m = new multiPerceptron(DA.getData(), 1000);
		//System.out.println(m.getAccuracy());
		
		logistic l = new logistic(testdata, 500);
		
		System.out.println(l.getAccuracy());
		
		for(spMemo g : l.createList()) {
			System.out.println(g.getX() + " " + g.getY() + " " + g.getT());
		}
		
	}	

}
