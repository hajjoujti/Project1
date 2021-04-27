package fr.eql.ai109.projet1;


import java.io.File;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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
		//tableView.setMinSize(800, 500);
		tableView.setPrefSize(850, 1500);
		//tableView.setMinWidth(500);
		tableView.setStyle("-fx-alignment: BASELINE_CENTER;");
		tableView.setStyle("-fx-background-color: powderblue;");
		//tableView.setPadding(new Insets(5.));
		
		root.setLeft(tableView);
		
		



//-----------------------------------------------------------------------------------------------------------
//												HBOX --> bouton delete 
//----------------------------------------------------------------------------------------------------------

		HBox box = new HBox();
		box.setPadding(new Insets(10, 0, 0, 0)); //haut/ bas/ / gauche 
		//box.setPrefSize(100, 50);
		box.setAlignment(Pos.TOP_LEFT);
		root.setTop(box);
		Button admin = new Button ("Session administrateur");
		affichage.setPrefSize(50, 50);
		affichage.setEditable(false);
		admin.setPrefSize(200, 50);
		admin.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
		Font f3=new Font("Calibri",  15);
		admin.setFont(f3);
		
		
		
		Button download = new Button ("Télécharger la doc");
		affichage.setPrefSize(50, 50);
		affichage.setEditable(false);
		download.setPrefSize(200, 50);
		download.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
		Font f=new Font("Calibri",  15);
		download.setFont(f);
		
		
		
		Button export = new Button ("Exporter");
		affichage.setPrefSize(50, 50);
		affichage.setEditable(false);
		export.setPrefSize(200, 50);
		export.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
		Font f2=new Font("Calibri",  15);
		export.setFont(f2);
		export.setTextFill(Color.CORNFLOWERBLUE);
		
		
		box.getChildren().addAll(admin, download, export);
		box.setSpacing(60);
		box.getAlignment();
		
		Label lbl2 = new Label("Bienvenue dans la session invité");
		box.getChildren().add(lbl2);
		lbl2.setPadding(new Insets(50, 100, 50, 150)); 
		//lbl2.setAlignment(Pos.CENTER);
//		lbl2.setTextFill(Color.BLACK);
//		lbl2.setTextOverrun(OverrunStyle.CLIP);
//		Font f5=new Font("Calibri",  30);
		Font f6 = Font.font(STYLESHEET_CASPIAN, FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 30);
		lbl2.setFont(f6);
//-----------------------------------------------------------------------------------------------------------
//											VBOX
//----------------------------------------------------------------------------------------------------------	
//	
//		VBox vbox = new VBox(50);
//		vbox.setPrefSize(200, 700);
//		vbox.setAlignment(Pos.BOTTOM_CENTER);
//		vbox.setStyle("-fx-background-color: steelblue;");
//
//		Button download = new Button ("Télécharger la doc");
//		affichage.setPrefSize(50, 50);
//		affichage.setEditable(false);
//		download.setPrefSize(150, 50);
//		download.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
//		Font f=new Font("Calibri",  15);
//		download.setFont(f);
//		
//		
//		
//		Button export = new Button ("Exporter");
//		affichage.setPrefSize(50, 50);
//		affichage.setEditable(false);
//		export.setPrefSize(150, 50);
//		export.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
//		Font f2=new Font("Calibri",  15);
//		export.setFont(f2);
//		export.setTextFill(Color.CORNFLOWERBLUE);
		
		//vbox.getChildren().addAll(download, export);
//		root.setRight(vbox);
		
		
//		
		
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
		
		
		grille.addRow(0, lblNom, lblPrenom, lblDep, lblFormation, lblAnnee );
		grille.addRow(1, tfNom, tfPrenom, tfDep, tfFormation, tfAnnee);
		grille.addRow(2);
		grille.setHgap(20);
		grille.setVgap(10);
		grille.setPadding(new Insets(10));
		

		
		//GridPane grille6 = new GridPane();
//		Button btn = new Button("Rechercher");
//		Button btn1 = new Button("Ajouter");
//		Button btn2 = new Button("Modifier");
//		Button btn3 = new Button("Supprimer");
//		grille6.addRow(0);
//		grille6.addRow(1, btn);
//		btn.setAlignment(Pos.CENTER);
		
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

		
		
		//GridPane2
		GridPane grille3= new GridPane(); 
		Label rA = new Label("Stagiaire: ");
		grille3.addRow(0, rA );
		rA.setPadding(new Insets(0, 0, 20, 150));
		//rA.setTextFill(Color.RED);
		//Font f4=new Font("Cambria",  20);
//		
		Font f4 = Font.font("Cambria", FontWeight.THIN, FontPosture.REGULAR, 20 );
		rA.setFont(f4);
		
		
		//GridPane3
		
		Button btn = new Button("Rechercher");
		
		GridPane grille2= new GridPane(); 
		grille2.addRow(0);
		grille2.addRow(1);
		grille2.addRow(2, grille3);
		grille2.addRow(3, grille);
		grille2.addRow(4, btn);
		
		grille2.setHgap(0);
		grille2.setVgap(30);
		root.setCenter(grille2);
		grille2.setPadding(new Insets(0, 100, 50, 100));	
		btn.setAlignment(Pos.CENTER);
		
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
		
		
//--------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------------------------------------------------------------------------------------------
		
		admin.setOnAction(new EventHandler<ActionEvent>(){
			
				
			public void handle(ActionEvent event) {
				
				Stage primaryStage2 = new Stage();
				AnchorPane anchorPane2 = new AnchorPane(); 
				anchorPane2.setStyle("-fx-background-color: powderblue;");
				
				GridPane grille4= new GridPane(); 
				Label lblId = new Label("Identifiant: ");
				TextField tfId = new TextField();
				Label lblMdp = new Label("Mdp: ");
				TextField tfMdp = new TextField();
				Button valider = new Button ("Valider");
				
				tfId.setPromptText("Identifiant");
				tfMdp.setPromptText("Mot de passe");
				
				
				grille4.addRow(0, lblId, tfId);
				grille4.addRow(1, lblMdp, tfMdp );
				grille4.addRow(3, valider);
				grille4.setHgap(20);
				grille4.setVgap(10);
				grille4.setPadding(new Insets(10));
				valider.setAlignment(Pos.CENTER);
				
				GridPane grille5 = new GridPane();
				Label lbl1 = new Label();
				grille5.addRow(0);
				grille5.addRow(1, lbl1);
				grille5.setHgap(20);
				grille5.setVgap(10);
				grille5.setPadding(new Insets(10));
				lbl1.setAlignment(Pos.CENTER);
				
				
				
				anchorPane2.getChildren().addAll(grille4, grille5);
				AnchorPane.setTopAnchor(grille4, 10.);
				AnchorPane.setBottomAnchor(grille5, 10.);
				AnchorPane.setLeftAnchor(grille4, 10.);
				AnchorPane.setRightAnchor(grille4, 10.);
				
				Scene scene = new Scene(anchorPane2, 300, 200);
				primaryStage2.setScene(scene);
				primaryStage2.sizeToScene();
				primaryStage2.setTitle("Identification");
				primaryStage2.show(); 

				valider.setOnAction(new EventHandler<ActionEvent>(){
					
					public void handle(ActionEvent event) {
						Stage primaryStage3 = new Stage();
						String id = "groupe4";
						String mdp = "bln";
						//String message = "Identifiant incorrect";
						
						if (tfId.getText().equals(id) && tfMdp.getText().equals(mdp)) {
						
						
						
						BorderPane root = new BorderPane();
						StagiaireDAO dao = new StagiaireDAO();
						TextField affichage = new TextField("");
							
							AnchorPane anchorPane = new AnchorPane(); 
							
							anchorPane.setStyle("-fx-background-color: powderblue;");
							root.setStyle("-fx-background-color: steelblue;");
						
							
							anchorPane.getChildren().add(root);
							AnchorPane.setTopAnchor(root, 10.);
							AnchorPane.setBottomAnchor(root, 10.);
							AnchorPane.setLeftAnchor(root, 10.);
							AnchorPane.setRightAnchor(root, 10.);

							
							Scene scene2 = new Scene(anchorPane, 1500, 800);
							primaryStage3.setScene(scene2);
							primaryStage3.sizeToScene();
							primaryStage3.setTitle("Table de stagiaires");
							primaryStage3.show();
							
							
					//-----------------------------------------------------------------------------------------------------------
//																	TableView
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
							//tableView.setMinSize(800, 500);
							tableView.setPrefSize(850, 1500);
							//tableView.setMinWidth(500);
							tableView.setStyle("-fx-alignment: BASELINE_CENTER;");
							tableView.setStyle("-fx-background-color: powderblue;");
							//tableView.setPadding(new Insets(5.));
							
							root.setLeft(tableView);
							
							

							//Evenement
//							tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {
					//
//								@Override
//								public void changed(ObservableValue<? extends Stagiaire> observable, Stagiaire oldValue,
//										Stagiaire newValue) {
//									lbl2.setText(newValue.toString()); //on redefinit le texte du deuxième label 	
//								}
//							});


					//-----------------------------------------------------------------------------------------------------------
//																	HBOX --> bouton delete 
					//----------------------------------------------------------------------------------------------------------

							HBox box = new HBox();
							box.setPadding(new Insets(10, 0, 0, 0)); //haut/ bas/ / gauche 
							//box.setPrefSize(100, 50);
							box.setAlignment(Pos.TOP_LEFT);
							root.setTop(box);
							Button invit = new Button ("Session invité");
							affichage.setPrefSize(50, 50);
							affichage.setEditable(false);
							invit.setPrefSize(200, 50);
							invit.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
							Font f3=new Font("Calibri",  15);
							invit.setFont(f3);
							
							
							Button download = new Button ("Télécharger la doc");
							affichage.setPrefSize(50, 50);
							affichage.setEditable(false);
							download.setPrefSize(200, 50);
							download.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
							Font f=new Font("Calibri",  15);
							download.setFont(f);
							
							
							
							Button export = new Button ("Exporter");
							affichage.setPrefSize(50, 50);
							affichage.setEditable(false);
							export.setPrefSize(200, 50);
							export.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
							Font f2=new Font("Calibri",  15);
							export.setFont(f2);
							export.setTextFill(Color.CORNFLOWERBLUE);
							
							
							box.getChildren().addAll(invit, download, export);
							box.setSpacing(60);
							box.getAlignment();
							
							Label lbl2 = new Label("Bienvenue dans la session utilisateur");
							box.getChildren().add(lbl2);
							lbl2.setPadding(new Insets(50, 100, 50, 100)); 
							Font f6 = Font.font(STYLESHEET_CASPIAN, FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 30);
							lbl2.setFont(f6);
							
							
								
							
//							
					//-----------------------------------------------------------------------------------------------------------
//																VBOX
					//----------------------------------------------------------------------------------------------------------	
//						
//							VBox vbox = new VBox(50);
//							vbox.setPrefSize(200, 700);
//							vbox.setAlignment(Pos.BOTTOM_CENTER);
//							vbox.setStyle("-fx-background-color: steelblue;");
//
//							Button download = new Button ("Télécharger la doc");
//							affichage.setPrefSize(50, 50);
//							affichage.setEditable(false);
//							download.setPrefSize(150, 50);
//							download.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
//							Font f=new Font("Calibri",  15);
//							download.setFont(f);
//							
//							
//							
//							Button export = new Button ("Exporter");
//							affichage.setPrefSize(50, 50);
//							affichage.setEditable(false);
//							export.setPrefSize(150, 50);
//							export.setStyle("-fx-background-color: ghostwhite; -fx-border-color:black");
//							Font f2=new Font("Calibri",  15);
//							export.setFont(f2);
//							
//							vbox.getChildren().addAll(download, export);
//							root.setRight(vbox);
//							
							
//							
							
					//-----------------------------------------------------------------------------------------------------------
//																GridPane
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
							
							
							grille.addRow(0, lblNom, lblPrenom, lblDep, lblFormation, lblAnnee );
							grille.addRow(1, tfNom, tfPrenom, tfDep, tfFormation, tfAnnee);
							grille.addRow(2);
							grille.setHgap(20);
							grille.setVgap(10);
							grille.setPadding(new Insets(10));
							

							
//							GridPane grille6 = new GridPane();
//							Button btn = new Button("Rechercher");
//							Button btn1 = new Button("Ajouter");
//							Button btn2 = new Button("Modifier");
//							Button btn3 = new Button("Supprimer");
//							grille6.addRow(0);
//							grille6.addRow(1, btn);
//							grille6.addRow(2);
//							grille6.addRow(3, btn1);
//							grille6.addRow(4);
//							grille6.addRow(5, btn2);
//							grille6.addRow(6);
//							grille6.addRow(7, btn3);
							
							
							
							
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

							
							
							//GridPane2
							GridPane grille3= new GridPane(); 
							Label rA = new Label("Stagiaire: ");
							grille3.addRow(0, rA );
							rA.setPadding(new Insets(0, 0, 20, 150));
							//rA.setTextFill(Color.RED);
							//Font f4=new Font("Cambria",  20);
//							
							Font f4 = Font.font("Cambria", FontWeight.THIN, FontPosture.REGULAR, 20 );
							rA.setFont(f4);
							
							
							//GridPane3
							Button btn = new Button("Rechercher");
							Button btn1 = new Button("Ajouter");
							Button btn2 = new Button("Modifier");
							Button btn3 = new Button("Supprimer");
							
							GridPane grille2= new GridPane(); 
							grille2.addRow(0);
							grille2.addRow(1);
							grille2.addRow(2, grille3);
							grille2.addRow(3, grille);
							grille2.addRow(4, btn);
							grille2.addRow(5, btn1);
							grille2.addRow(6, btn2);
							grille2.addRow(7, btn3);
							
							grille2.setHgap(0);
							grille2.setVgap(30);
							root.setCenter(grille2);
							grille2.setPadding(new Insets(0, 100, 50, 100));	
							btn.setAlignment(Pos.CENTER);
							btn1.setAlignment(Pos.CENTER);
							btn2.setAlignment(Pos.CENTER);
							btn3.setAlignment(Pos.CENTER);
							
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
							
							invit.setOnAction(new EventHandler<ActionEvent>(){
								
								public void handle(ActionEvent event) {
									
									primaryStage3.close();
									primaryStage2.close();
								}
								 
								    
							
										
								    });
							
	
					}else {
						lbl1.setText("Identifiant incorrect");
						lbl1.setTextFill(Color.RED);
						Font f3=new Font("Calibri",  15);
						lbl1.setFont(f3);
					}	
					}
			
		});
				
					
				
			}
			
//-----------------------------------------------------------
//							IMAGE EQL
//------------------------------------------------------------------
//		 //Image Source
//		InputStream input= getClass().getResourceAsStream("/image/eql.png");
//		Image image = new Image(input);
//		ImageView imageView = new ImageView(image);
//		 
////		// Create a Label with label and Icon
//		Label label = new Label("JavaFX", imageView);
////		 
//		// Create a Label with label.
//		//Label label = new Label("JavaFX");
//		 
//		// Set Image Icon
//		label.setGraphic(imageView);
//		box.getChildren().add(label);
		
	


	
	
	


	
});	
	}
	
	public static void main(String[] args) {
		launch();
	}
}


















