package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application
{
	public static void main(String[] args) {launch(args);}
	
	public void start(Stage window)
	{
		GridPane gpMain = new GridPane();
		gpMain.setAlignment(Pos.CENTER_RIGHT);
		gpMain.setVgap(7);
		gpMain.setHgap(10);
		gpMain.setPadding(new Insets(15, 15, 15, 15));
		
		TextField tfNewFile = new TextField();
		TextField tfFilePath = new TextField();
		TextField tfAddTag = new TextField();
		
		Label lbNewFile = new Label("File name:");
		Label lbFilePath = new Label("File path:");
		Label lbTag = new Label("Tag name:");
		Label lbRemoveFile = new Label("Does not remove the original file");
		Label lbBlank = new Label("");
		Label lbBlank2 = new Label("");
		Label lbBlank3 = new Label("");
		
		Button btCreateFile = new Button("Create new file");
		Button btLinkFile = new Button("Import exsisting file");
		Button btAddtag = new Button("Add tag to a file");
		Button btRemoveFile = new Button("Remove file link");
		
		gpMain.add(btCreateFile, 0, 1);
		gpMain.add(lbNewFile, 1, 0);
		gpMain.add(tfNewFile, 1, 1);
		gpMain.add(lbBlank, 0, 2);
		gpMain.add(btLinkFile, 0, 4);
		gpMain.add(lbFilePath, 1, 3);
		gpMain.add(tfFilePath, 1, 4);
		gpMain.add(lbBlank2, 0, 5);
		gpMain.add(btAddtag, 0, 7);
		gpMain.add(lbTag, 1, 6);
		gpMain.add(tfAddTag, 1, 7);
		gpMain.add(lbBlank3, 0, 8);
		gpMain.add(btRemoveFile, 0, 9);
		gpMain.add(lbRemoveFile, 1, 9);
		
		Scene scMain = new Scene(gpMain, 720, 500);
		
		window.setScene(scMain);
		window.show();
	}
}