package pkjfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Date;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;

public class ClockFX extends Application{
	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage primarystage) throws Exception {
	
		
		Line lnsec = new Line();
		lnsec.setStrokeWidth(2);
		lnsec.setStroke(Color.RED);
		
		Line lnmin = new Line();
		lnmin.setStrokeWidth(3);
		lnmin.setStroke(Color.TEAL);
		
		Line lnhr = new Line();
		lnhr.setStrokeWidth(4);
		lnhr.setStroke(Color.BLACK);
		
		
		lnsec.setStartX(250);lnsec.setStartY(250);
		lnsec.setEndX(250); lnsec.setEndY(75);
		
		lnmin.setStartX(250);lnmin.setStartY(250);
		lnmin.setEndX(250); lnmin.setEndY(85);
		
		lnhr.setStartX(250);lnhr.setStartY(250);
		lnhr.setEndX(250); lnhr.setEndY(130);
		
		
		Circle circ1 = new Circle(200);
		circ1.setCenterX(250);
		circ1.setCenterY(250);
		
		circ1.setFill(Color.WHITE);
		circ1.setStroke(Color.BLACK);
		
		Circle circ2 = new Circle(180);
		circ2.setCenterX(250);
		circ2.setCenterY(250);
		
		circ2.setFill(Color.WHITE);
		circ2.setStroke(Color.WHITE);
		
		Rotate rotatesec = new Rotate();
		rotatesec.pivotXProperty().bind(lnsec.startXProperty());
		rotatesec.pivotYProperty().bind(lnsec.startYProperty());
		
		Rotate rotatemin = new Rotate();
		rotatemin.pivotXProperty().bind(lnmin.startXProperty());
		rotatemin.pivotYProperty().bind(lnmin.startYProperty());
		
		Rotate rotatehr = new Rotate();
		rotatehr.pivotXProperty().bind(lnhr.startXProperty());
		rotatehr.pivotYProperty().bind(lnhr.startYProperty());
		
		
		lnsec.getTransforms().add(rotatesec);
		lnmin.getTransforms().add(rotatemin);
		lnhr.getTransforms().add(rotatehr);
		
		

		EventHandler<ActionEvent> sectime = e->{rotatesec.setAngle(new Date().getSeconds()*360/60.0);};
		EventHandler<ActionEvent> mintime = e->{rotatemin.setAngle(new Date().getMinutes()*360/60.0);};
		EventHandler<ActionEvent> hrtime = e->{rotatehr.setAngle(new Date().getHours()*360/12.0);};


		KeyFrame keysec = new KeyFrame(Duration.seconds(0.1),sectime);		
		KeyFrame keymin = new KeyFrame(Duration.seconds(0.1),mintime);	
		KeyFrame keyhr = new KeyFrame(Duration.seconds(0.1),hrtime);	
		
		
		Timeline timesec = new Timeline(keysec);
		timesec.setCycleCount(Timeline.INDEFINITE);
		timesec.play();
		
		Timeline timemin = new Timeline(keymin);
		timemin.setCycleCount(Timeline.INDEFINITE);
		timemin.play();
		
		Timeline timehr = new Timeline(keyhr);
		timehr.setCycleCount(Timeline.INDEFINITE);
		timehr.play();
		
		
	
		
		Rotate one = new Rotate();
		one.pivotXProperty().bind(circ1.centerXProperty());
		one.pivotYProperty().bind(circ1.centerYProperty());
		double seconds = 6;
		seconds = Math.toRadians(seconds);
		
		Button stop = new Button("Stop");
		stop.setOnMouseClicked(e->{
			timesec.pause();
			timemin.pause();
			timehr.pause();
		});
		stop.setLayoutX(200);stop.setLayoutY(470);
		
		
		Button start = new Button("start");
		start.setOnMouseClicked(e->{
			timesec.play();
			timemin.play();
			timehr.play();
		});
		start.setLayoutX(270);start.setLayoutY(470);
		
		Pane pane = new Pane();
		pane.getChildren().add(circ1);
		double hours = 30;
		hours = Math.toRadians(hours);
		for(int i = 1 ; i<61 ; i++)
		{
			Line ln1 = new Line(250,250,250 + (Math.sin(seconds)*200),250 - (Math.cos(seconds)*200));
			if(i%5==0) {
				ln1.setStrokeWidth(3);
				Label lbl1 = new Label((i/5) + "");
				lbl1.setLayoutX(250 + (Math.sin(hours)*200));lbl1.setLayoutY(250 - (Math.cos(hours)*200));
				hours= Math.toDegrees(hours);
				hours +=30;
				hours = Math.toRadians(hours);
				pane.getChildren().add(lbl1);
			}
			pane.getChildren().add(ln1);
			seconds= Math.toDegrees(seconds);
			seconds +=6;
			seconds = Math.toRadians(seconds);
		}
		pane.getChildren().addAll(circ2,lnhr,lnmin,lnsec,stop,start);
		
		
		
		Scene scene = new Scene(pane);
		pane.setMinSize(500, 500);
		
		primarystage.setScene(scene);
		primarystage.show();
	}
	public static void main(String[] args) {
		Application.launch();
	}

}
