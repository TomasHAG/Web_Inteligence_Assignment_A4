package application;

import java.util.List;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import weka.core.Instances;

public class grhapics {
	private final NumberAxis xAxis = new NumberAxis(1, -1, 0.01);
    private final NumberAxis yAxis = new NumberAxis(1, -1, 0.01); 
	private final ScatterChart<Number,Number> sc = new ScatterChart<Number,Number>(xAxis,yAxis);
	
	grhapics(){		
	}
	
	public void generateChart(List<spMemo> data) {
		XYChart.Series points1 = new XYChart.Series();
		points1.setName("A");
		XYChart.Series points2 = new XYChart.Series();
		points2.setName("B");
		XYChart.Series points3 = new XYChart.Series();
		points3.setName("C");
		
		
		
		for(spMemo m : data) {
			if(m.getT() == 0.0) {
				points1.getData().add(new XYChart.Data(m.getX(), m.getY()));
			}else if(m.getT() == 1.0) {
				points2.getData().add(new XYChart.Data(m.getX(), m.getY()));
			}else if(m.getT() == 2.0) {
				points3.getData().add(new XYChart.Data(m.getX(), m.getY()));
			}else {
				System.out.print("error!");
			}
		}
		sc.getData().addAll(points1, points2, points3);
		sc.setPrefSize(700, 700);
		sc.setMinSize(700, 700);
		sc.setMaxSize(700, 700);
	}
	
	public ScatterChart<Number,Number> getGhraph(){
		
		return sc;
	}
}
