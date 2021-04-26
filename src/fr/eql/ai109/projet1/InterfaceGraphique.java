package fr.eql.ai109.projet1;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterfaceGraphique extends Application {

	BorderPane root = new BorderPane();
	StagiaireDAO dao = new StagiaireDAO();

	private TextField affichage = new TextField("");



	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		AnchorPane anchorPane = new AnchorPane(); 
		
		anchorPane.setStyle("-fx-background-color: powderblue;");
		root.setStyle("-fx-background-color: steelblue;");
	
		
		anchorPane.getChildren().add(root);
		AnchorPane.setTopAnchor(root, 10.);
		AnchorPane.setBottomAnchor(root, 10.);
		AnchorPane.setLeftAnchor(root, 10.);
		AnchorPane.setRightAnchor(root, 10.);

		
		Scene scene = new Scene(anchorPane, 1500, 800);
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Table de stagiaires");
		primaryStage.show();
		
		
//-----------------------------------------------------------------------------------------------------------
//												TableView
//----------------------------------------------------------------------------------------------------------	
		

		ObservableList<Stagiaire> observableSeries = FXCollections.observableArrayList(dao.getAll());

		TableView<Stagiaire> tableView = new TableView<Stagiaire>(observableSeries);
		
		TableColumn<Stagiaire, String> colNom = new TableColumn<Stagiaire, String>("Nom");
		colNom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("nom")); 

		TableColumn<Stagiaire, String> colPrenom = new TableColumn<Stagiaire, String>("Prénom");
		colPrenom.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("prenom"));

		TableColumn<Stagiaire, String> colDep = new TableColumn<Stagiaire, String>("Département");
		colDep.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("dep"));

		TableColumn<Stagiaire, String> colFormation= new TableColumn<Stagiaire, String>("Formation");
		colFormation.setCellValueFactory(new PropertyValueFactory<Stagiaire, String>("formation"));

		TableColumn<Stagiaire, Integer> colAnnee = new TableColumn<Stagiaire, Integer>("Année");
		colAnnee.setCellValueFactory(new PropertyValueFactory<Stagiaire, Integer>("annee"));

		//Donner la colonne à notre tableview
		tableView.getColumns().addAll(colNom, colPrenom, colDep, colFormation, colAnnee); 
		//Ajuster la taille du tableau à son contenu
		
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setMinSize(800, 500);
		//tableView.setMinWidth(500);
		tableView.setStyle("-fx-alignment: BASELINE_CENTER;");
		tableView.setStyle("-fx-background-color: powderblue;");
		//tableView.setPadding(new Insets(5.));
		
		root.setLeft(tableView);
		
		

		//Evenement
//		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Stagiaire> observable, Stagiaire oldValue,
//					Stagiaire newValue) {
//				lbl2.setText(newValue.toString()); //on redefinit le texte du deuxième label 	
//			}
//		});


//-----------------------------------------------------------------------------------------------------------
//												HBOX --> bouton delete 
//----------------------------------------------------------------------------------------------------------

		HBox box = new HBox();
		box.setPadding(new Insets(10.));
		box.setPrefSize(800, 100);
		box.setAlignment(Pos.CENTER);
		root.setTop(box);
		
		
		
		
//		Label lbl1 = new Label("Recherche Avancée ");
//		box.getChildren().addAll(lbl1);
//		lbl1.setPadding(new Insets(0, 100, 0, 0)); 
//-----------------------------------------------------------------------------------------------------------
//											VBOX
//----------------------------------------------------------------------------------------------------------	
	
		VBox vbox = new VBox(50);
		vbox.setPrefSize(200, 700);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setStyle("-fx-background-color: steelblue;");

		Button download = new Button ("Télécharger la doc");
		affichage.setPrefSize(50, 50);
		affichage.setEditable(false);
		download.setPrefSize(150, 80);
		download.setStyle("-fx-background-color: ghostwhite; -fx-border-color:turquoise");
		
		Button export = new Button ("Exporter");
		affichage.setPrefSize(50, 50);
		affichage.setEditable(false);
		export.setPrefSize(150, 80);
		export.setStyle("-fx-background-color: ghostwhite; -fx-border-color:purple");
		
		vbox.getChildren().addAll(download, export);
		root.setRight(vbox);
		
		
//		VBox vbox = new VBox();
//		Label lbl1 = new Label("Recherche avancée");
//		
//		vbox.getChildren().addAll(lbl1);
//		lbl1.setPadding(new Insets(0, 200, 0, 0)); 
//		vbox.setAlignment(Pos.CENTER);
//		vbox.setPrefWidth(50);
//		vbox.setStyle("-fx-background-color: cyan");
//		root.setCenter(vbox);
		
//-----------------------------------------------------------------------------------------------------------
//											GridPane
//----------------------------------------------------------------------------------------------------------
		GridPane grille= new GridPane(); 
		Label lblNom = new Label("Nom: ");
		TextField tfNom = new TextField();
		Label lblPrenom = new Label("Prenom: ");
		TextField tfPrenom = new TextField();
		Label lblDep = new Label("Département: ");
		TextField tfDep= new TextField();
		Label lblFormation = new Label("Formation: ");
		TextField tfFormation = new TextField();
		Label lblAnnee = new Label("Annee: ");
		TextField tfAnnee = new TextField();
		Button btn = new Button("Valider");

		grille.addRow(0, lblNom, tfNom);
		grille.addRow(1, lblPrenom, tfPrenom);
		grille.addRow(2, lblDep, tfDep);
		grille.addRow(3, lblFormation, tfFormation);
		grille.addRow(4, lblAnnee, tfAnnee);
		grille.addRow(5, btn);
		grille.setHgap(80);
		grille.setVgap(10);
		grille.setPadding(new Insets(100));
		btn.setAlignment(Pos.CENTER);
		

		
		root.setCenter(grille);
		

		tfNom.setPromptText("Nom");
		tfNom.setMaxWidth(colNom.getPrefWidth());
		tfPrenom.setPromptText("Prenom");
		tfPrenom.setMaxWidth(colPrenom.getPrefWidth());
		tfDep.setPromptText("Département");
		tfDep.setMaxWidth(colDep.getPrefWidth());
		tfFormation.setPromptText("Formation");
		tfFormation.setMaxWidth(colFormation.getPrefWidth());
		tfAnnee.setPromptText("Année");
		tfAnnee.setMaxWidth(colAnnee.getPrefWidth());

		btn.setOnAction((ActionEvent e)
				-> {
					observableSeries.add(new Stagiaire(tfNom.getText(), tfPrenom.getText(), tfDep.getText(), tfDep.getText(), Integer.parseInt(tfAnnee.getText())));  

					tfNom.clear();
					tfPrenom.clear();
					tfDep.clear();
					tfFormation.clear();
					tfAnnee.clear();
				}   
				);
		


	}
	
	
	public static void main(String[] args) {
		launch();
	}

	
}	


















