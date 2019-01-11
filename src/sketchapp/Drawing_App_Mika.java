package sketchapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author Michaël
 */
public class Drawing_App_Mika extends Application {
        private String couleur(Color c) {
        return "-fx-base: rgb(" + (c.getRed() * 255) + "," + (c.getGreen() * 255) + "," + (c.getBlue() * 255) + ");";
    }
                int state;
    @Override
    public void start(Stage primaryStage) {
        Color[] tabColor = {
            Color.WHITE, Color.ORANGE, Color.RED, Color.PINK, 
            Color.YELLOW, Color.YELLOWGREEN,Color.GREEN, 
            Color.BLUE, Color.CYAN, Color.AQUA, Color.BLUEVIOLET,
            Color.VIOLET, Color.CHOCOLATE, Color.BLACK,Color.DARKCYAN,
            Color.BEIGE, Color.OLDLACE, Color.GOLD
        }; 
        final double[] epaisseurs = {4.0, 8.0, 12.0, 16.0} ;
        state = 1;
        Button[] butab = new Button[tabColor.length];
        Group root = new Group();
        StackPane minibox = new StackPane();
        Scene scene = new Scene(root, 800, 600, Color.LAVENDERBLUSH);
        Canvas  canvas = new Canvas(802,500);
        canvas.setLayoutX(0);
        canvas.setLayoutY(0);
   
        canvas.setCursor(Cursor.CROSSHAIR);
        GraphicsContext ctx = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(
                (MouseEvent event)->{
                    double posX = event.getX()-10;
                    double posY = event.getY()-10;
                    ctx.fillOval(posX, posY, epaisseurs[state], epaisseurs[state]);
                    System.out.println("Position du Curseur   X : "+posX+"  Y : "+posY);
        });
        minibox.getChildren().add(canvas); //ON AJOUTE LE CANVAS
        minibox.setStyle("-fx-background-color: white");
        root.getChildren().add(minibox); //ON AJOUTE LE CONTENEUR
        for(int i=0; i < tabColor.length ; i++){
       butab[i] = new Button("");
        butab[i].setLayoutX(i*20*2.22);
        butab[i].setLayoutY(510);
        butab[i].setPrefSize(40,40);
        butab[i].setPickOnBounds(true);
        butab[i].setCursor(Cursor.HAND);
        Color tmp =  tabColor[i];
        butab[i].setStyle(this.couleur(tmp));
        butab[i].setOnAction((ActionEvent event)->{
        ctx.setFill( tmp);
        });
        root.getChildren().add(butab[i]);
        }        
  
Button pointe1 = new Button("Pointe Fine");
Button pointe2 = new Button("Pointe Normale");
Button pointe3 = new Button("Pointe Epaisse");
Button pointe4 = new Button("Pointe Très Epaisse");

        pointe1.setPrefSize(150, 40);
           pointe1.setLayoutX(0);
           pointe1.setLayoutY(550);
        pointe1.setOnAction((ActionEvent event)->{
            state = 0;
               pointe1.setStyle(couleur(Color.AQUA));
                pointe2.setStyle(couleur(Color.WHITE));
                pointe3.setStyle(couleur(Color.WHITE));
                pointe4.setStyle(couleur(Color.WHITE));
        });
        pointe2.setPrefSize(150,40);
        pointe2.setLayoutX(150);
        pointe2.setLayoutY(550);
            pointe2.setOnAction((ActionEvent event)->{
            state = 1;
                pointe1.setStyle(couleur(Color.WHITE));
                pointe2.setStyle(couleur(Color.AQUA));
                pointe3.setStyle(couleur(Color.WHITE));
                pointe4.setStyle(couleur(Color.WHITE));        
        });    
        pointe3.setPrefSize(150, 40);
           pointe3.setLayoutX(300);
           pointe3.setLayoutY(550);
        pointe3.setOnAction((ActionEvent event)->{
            state = 2;
                pointe1.setStyle(couleur(Color.WHITE));
                pointe2.setStyle(couleur(Color.WHITE));
                pointe3.setStyle(couleur(Color.AQUA));
                pointe4.setStyle(couleur(Color.WHITE));          
        });
        pointe4.setPrefSize(150,40);
        pointe4.setLayoutX(450);
        pointe4.setLayoutY(550);
            pointe4.setOnAction((ActionEvent event)->{
            state = 3;
                pointe1.setStyle(couleur(Color.WHITE));
                pointe2.setStyle(couleur(Color.WHITE));
                pointe3.setStyle(couleur(Color.WHITE));
                pointe4.setStyle(couleur(Color.AQUA));  
        });    

        Button clearAll = new Button("EFFACER TOUT");
        clearAll.setPrefSize(200, 40);
           clearAll.setLayoutX(600);
           clearAll.setLayoutY(550);
           clearAll.setStyle("-fx-background-color:orange; ");
        clearAll.setOnAction((ActionEvent event)->{
            ctx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
        root.getChildren().addAll(pointe1, pointe2, pointe3, pointe4, clearAll);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Drawing APP -  Afmika");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
