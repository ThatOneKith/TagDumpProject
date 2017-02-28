//Det här programmet ska fungera som en utförskare för filer. Man kan skapa länkar till filer och sätta taggar på dessa länkar samt söka efter taggar och öppna filer
package gui;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application
{
	static {
		try {
			if(!Files.isDirectory(Paths.get("data")))
				Files.createDirectory(Paths.get("data"));
			if(!Files.isDirectory(Paths.get("ulib")))
				Files.createDirectory(Paths.get("ulib"));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e + "\n33");
		}
	}

	public static void main(String[] args) {launch(args);}

	public void start(Stage window)
	{
		//		Här initieras en gridpane och värden värden på hur det ska se ut sätts
		GridPane gpMain = new GridPane();
		gpMain.setAlignment(Pos.CENTER_RIGHT);
		gpMain.setVgap(7);
		gpMain.setHgap(30);
		gpMain.setPadding(new Insets(15, 15, 15, 15));

		VBox vSearchField = new VBox();
		vSearchField.setAlignment(Pos.CENTER_LEFT);

		//		Textfält för inmatning initieras och höjd på dem sätts
		TextField tfNewFile = new TextField();
		tfNewFile.setMinHeight(25);
		TextField tfFilePath = new TextField();
		tfFilePath.setMinHeight(25);
		TextField tfAddTag = new TextField();
		tfAddTag.setMinHeight(25);

		//		Labels för textfälten initieras och font samt storlek bestämms
		Label lbNewFile = new Label("File name:");
		lbNewFile.setFont(Font.font("Comic Sans MS", 12));
		Label lbFilePath = new Label("File path:");
		lbFilePath.setFont(Font.font("Comic Sans MS", 12));
		Label lbTag = new Label("Tag name:");
		lbTag.setFont(Font.font("Comic Sans MS", 12));
		Label lbRemoveFile = new Label("Does not remove the original file");
		lbRemoveFile.setFont(Font.font("Comic Sans MS", 12));

		//		De här fyra är blanka labels för att ge mellanrum mellan de olika funktionerna i programmet.
		Label lbBlank = new Label("");
		Label lbBlank2 = new Label("");
		Label lbBlank3 = new Label("");
		Label lbBlank4 = new Label("");

		//		Knappar initieras och font samt storlek på knapparna bestämms
		Button btCreateFile = new Button("Create new file");
		btCreateFile.setFont(Font.font("Comic Sans MS", 12)); btCreateFile.setMinHeight(30); btCreateFile.setMinWidth(150);
		Button btLinkFile = new Button("Import exsisting file");
		btLinkFile.setFont(Font.font("Comic Sans MS", 12)); btLinkFile.setMinHeight(30); btLinkFile.setMinWidth(150);
		Button btAddTag = new Button("Add tag to a file");
		btAddTag.setFont(Font.font("Comic Sans MS", 12)); btAddTag.setMinHeight(30); btAddTag.setMinWidth(150);
		Button btRemoveFile = new Button("Remove file link");
		btRemoveFile.setFont(Font.font("Comic Sans MS", 12)); btRemoveFile.setMinHeight(30); btRemoveFile.setMinWidth(150);

		//		Här placeras allting ut i en GridPane
		gpMain.add(btCreateFile, 0, 1);
		gpMain.add(lbNewFile, 1, 0);
		gpMain.add(tfNewFile, 1, 1);
		gpMain.add(lbBlank, 0, 2);
		gpMain.add(btLinkFile, 0, 4);
		gpMain.add(lbFilePath, 1, 3);
		gpMain.add(tfFilePath, 1, 4);
		gpMain.add(lbBlank2, 0, 5);
		gpMain.add(btAddTag, 0, 7);
		gpMain.add(lbTag, 1, 6);
		gpMain.add(tfAddTag, 1, 7);
		gpMain.add(lbBlank3, 0, 8);
		gpMain.add(lbBlank4, 0, 9);
		gpMain.add(btRemoveFile, 0, 10);
		gpMain.add(lbRemoveFile, 1, 10);

		vSearchField.getChildren().addAll();

		//		Scen initieras
		Scene scMain = new Scene(gpMain, 720, 500);

		//		Utseende på fönster ändras
		window.setScene(scMain);
		window.setResizable(false);
		window.setTitle("Tag Dump");

		//		Fönster startas
		window.show();

		btCreateFile.setOnAction(Event -> {
			if(tfNewFile.getText().length() > 0)
				this.createFile(tfNewFile.getText());
			tfNewFile.setText("");
		});

	}

	private static ArrayList<Link> fileLink = new ArrayList<Link>();

	static {
		try {
			if(Files.list(Paths.get("data\\")).count() > 0)
				for(Path entry : Files.list(Paths.get("data\\")).iterator().next())
					if(!entry.toString().equals("data"))
						fileLink.add(new Link(entry.getFileName().toString()));
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e + "\n131");
		}
	}

	private void linkFile(Path target) {
		try {

			String dirID;
			while(true) {
				dirID = Integer.toString((int) (Math.random() * Math.pow(10, 8)));
				if(Files.notExists(Paths.get("data\\" + dirID)))
					break;
			}
			Files.createDirectory(Paths.get("data\\" + dirID));

			String fileID = target.getFileName().toString();

			Files.createFile(Paths.get("data\\" + dirID + "\\id.txt"));
			Files.write(Paths.get("data\\" + dirID + "\\id.txt"), (fileID + "\n"
					+ fileID.substring(0, fileID.lastIndexOf(".")) + "\n"
					+ fileID.substring(fileID.lastIndexOf("."), fileID.length())).getBytes());

			Files.createFile(Paths.get("data\\" + dirID + "\\tags.txt"));

			Files.createLink(Paths.get("data\\" + dirID + "\\" + fileID), target);

			fileLink.add(new Link(dirID));

		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e + "\n161");
		}
	}

	private void createFile(String fileID) {
		try {
			if(Files.exists(Paths.get("ulib\\" + fileID)))
				JOptionPane.showMessageDialog(null, fileID + " finns redan i " + Paths.get("ulib\\").toString());
			else {
				Files.createFile(Paths.get("ulib\\" + fileID));
				this.linkFile(Paths.get("ulib\\" + fileID));
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e + "\n175");
		}
	}

	private void addTag(String tag, String dirID) {
		for(Link entry : fileLink)
			if(entry.getDirID().equals(dirID)) {
				entry.getTags().add(tag);
				break;
			}
	}

}
