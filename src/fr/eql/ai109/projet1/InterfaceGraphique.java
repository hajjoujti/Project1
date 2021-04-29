package fr.eql.ai109.projet1;


import java.awt.Desktop;
import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.CoreFont;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class InterfaceGraphique extends Application implements Parametre {


	BorderPane root = new BorderPane();
	StagiaireDAO dao = new StagiaireDAO();
	Desktop desktop;
	private TextField affichage = new TextField("");
	Label message = new Label();
	private boolean activeMessage = false;


	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane anchorPane = new AnchorPane(); 
		anchorPane.setStyle("-fx-background-color: DARKGREY;");
		root.setStyle("-fx-background-color: LIGHTGRAY;");

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
		ObservableList<Stagiaire> observableStagiaire = FXCollections.observableArrayList(dao.getAll());

		TableView<Stagiaire> tableView = new TableView<Stagiaire>(observableStagiaire);

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
		tableView.setPrefSize(850, 575);
		tableView.setStyle("-fx-alignment: BASELINE_CENTER;");
		tableView.setStyle("-fx-background-color: black;");

		GridPane paneTableView = new GridPane();
		
		Button btnAjout = new Button("");
		btnAjout.setGraphic(new ImageView(new Image(cheminIconAjout)));
		paneTableView.add(btnAjout, 0, 0);
		
		paneTableView.add(tableView, 0, 1);
		paneTableView.setVgap(5);
		paneTableView.setHgap(15);
		paneTableView.setAlignment(Pos.CENTER);

		root.setCenter(paneTableView);
		//		BorderPane.setAlignment(paneTableView, Pos.CENTER_LEFT);


		//-----------------------------------------------------------------------------------------------------------
		//												HBOX --> bouton delete 
		//----------------------------------------------------------------------------------------------------------

		HBox box = new HBox();
		box.setPadding(new Insets(10, 0, 0, 0)); //haut/ bas/ / gauche 
		//box.setPrefSize(100, 50);
		box.setAlignment(Pos.TOP_LEFT);
		root.setTop(box);
		Button admin = new Button ("Session Administrateur");
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



		box.getChildren().addAll(admin, download, export);
		box.setSpacing(60);
		box.setAlignment(Pos.CENTER);

		Label lbl2 = new Label("Session utilisateurs");
		lbl2.setUnderline(true);
		lbl2.setTextFill(Color.web("#00122E"));

		lbl2.setPadding(new Insets(30, 140, 0, 220)); 
		box.getChildren().add(lbl2);
		Font f6 = Font.font(STYLESHEET_CASPIAN, FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 30);
		lbl2.setFont(f6);

		//-----------------------------------------------------------------------------------------------------------
		//											GridPane
		//----------------------------------------------------------------------------------------------------------	
		GridPane grille= new GridPane(); 

		Label lblNom = new Label("Nom: ");
		TextField tfNom = new TextField();
		tfNom.setMinWidth(200);

		Label lblPrenom = new Label("Prenom: ");
		TextField tfPrenom = new TextField();
		tfPrenom.setMinWidth(200);

		Label lblDep = new Label("Département: ");
		TextField tfDep= new TextField();
		tfDep.setMinWidth(200);

		Label lblFormation = new Label("Formation: ");
		TextField tfFormation = new TextField();
		tfFormation.setMinWidth(200);

		Label lblAnnee = new Label("Annee: ");
		TextField tfAnnee = new TextField();
		tfAnnee.setMinWidth(200);

		grille.add(lblNom, 0, 0);
		grille.add(tfNom, 1, 0);
		grille.add(lblPrenom, 0, 1);
		grille.add(tfPrenom, 1, 1);
		grille.add(lblDep, 0, 2);
		grille.add(tfDep, 1, 2);
		grille.add(lblFormation, 0, 3);
		grille.add(tfFormation, 1, 3);
		grille.add(lblAnnee, 0, 4);
		grille.add(tfAnnee, 1, 4);

		grille.setVgap(20);

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
		Font f4 = Font.font("Cambria", FontWeight.THIN, FontPosture.REGULAR, 20 );
		rA.setFont(f4);

		//GridPane3
		Button btnRecherche = new Button("Rechercher");
		HBox hbox= new HBox(20);
		Button btnReini = new Button("Réinitialiser");
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(0,20,0,0));
		hbox.getChildren().addAll(btnRecherche, btnReini);



		
		message.setTextFill(Color.web("#FF0000"));
		Image image = new Image(new FileInputStream(chemingLogoEQL));  
		//Setting the image view 
		ImageView imageView = new ImageView(image); 
		//setting the fit height and width of the image view 
		imageView.setFitHeight(350); 
		imageView.setFitWidth(400); 
		//Setting the preserve ratio of the image view 
		imageView.setPreserveRatio(true);  
		//Creating a Group object  
		Group group = new Group(imageView);

		GridPane grille2= new GridPane();
		grille2.addRow(0);
		grille2.addRow(1);
		grille2.addRow(2, grille3);
		grille2.addRow(3, grille);
		grille2.addRow(4, hbox);
		grille2.addRow(5, message);
		grille2.addRow(6, group);


		grille2.setHgap(0);
		grille2.setVgap(30);
		root.setRight(grille2);
		grille2.setPadding(new Insets(30, 100, 50, 100));	
		BorderPane.setAlignment(grille2, Pos.CENTER);

		btnRecherche.setOnAction((ActionEvent e)
				-> {
					if(verifierAnneeEstNombreAQuatreChiffre(tfAnnee, message)) {
						List<Stagiaire> listStagiaireRecherche = dao.rechercheAvancee(tfNom.getText(), tfPrenom.getText(), tfDep.getText(), 
								tfFormation.getText(), tfAnnee.getText());
						observableStagiaire.setAll(listStagiaireRecherche);
						tfNom.clear();
						tfPrenom.clear();
						tfDep.clear();
						tfFormation.clear();
						tfAnnee.clear();
					}
				}
				);

		btnReini.setOnAction((ActionEvent e)
				-> {  
					message.setText("");
					tfNom.clear();
					tfPrenom.clear();
					tfDep.clear();
					tfFormation.clear();
					tfAnnee.clear();
					observableStagiaire.setAll(dao.getAll());
				}   
				);

		export.setOnAction(actionEvent -> {
			importation(message);
		});

		download.setOnAction((ActionEvent e)
				-> {
					ouvrirFichierDoc(message);
				});

		sceneBoutonAdmin(primaryStage, scene, observableStagiaire, admin, group);	
	}
	private void sceneBoutonAdmin(Stage primaryStage, Scene scene, ObservableList<Stagiaire> observableStagiaire,
			Button admin, Group group) {
		admin.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent event) {
				Stage primaryStage2 = new Stage();
				AnchorPane anchorPane2 = new AnchorPane(); 
				anchorPane2.setStyle("-fx-background-color: LIGHTGRAY;");

				GridPane grille4= new GridPane(); 
				Label lblId = new Label("Identifiant: ");
				TextField tfId = new TextField();
				Label lblMdp = new Label("Mdp: ");
				PasswordField tfMdp = new PasswordField();
				Button valider = new Button ("Valider");

				Label lbl1 = new Label();
				lbl1.setWrapText(true);
				lbl1.setMaxWidth(325);

				tfId.setPromptText("Identifiant");
				tfMdp.setPromptText("Mot de passe");


				grille4.add(lblId, 0, 0);
				grille4.add(tfId, 1, 0);
				grille4.add(lblMdp, 0, 1 );
				grille4.add(tfMdp, 1, 1);
				grille4.setHgap(20);
				grille4.setVgap(10);
				grille4.setPadding(new Insets(10));


				GridPane grille5 = new GridPane();

				grille5.add(grille4, 0, 0);
				grille5.add(lbl1, 0 , 1);
				grille5.add(valider, 0, 2);
				grille5.setVgap(10);

				GridPane.setHalignment(valider, HPos.CENTER);
				GridPane.setHalignment(lbl1, HPos.CENTER);
				AnchorPane.setTopAnchor(grille5, 10.);
				AnchorPane.setBottomAnchor(grille5, 10.);
				AnchorPane.setLeftAnchor(grille5, 10.);
				AnchorPane.setRightAnchor(grille5, 10.);

				anchorPane2.getChildren().addAll(grille5);

				Scene scene2 = new Scene(anchorPane2, 325, 200);
				primaryStage2.setScene(scene2);
				primaryStage2.sizeToScene();
				primaryStage2.setTitle("Identification");
				primaryStage2.show(); 

				valider.setOnAction(new EventHandler<ActionEvent>(){

					public void handle(ActionEvent event) {
						//						Stage primaryStage3 = new Stage();
						String id = "groupe4";
						String mdp = "bln";
						//String message = "Identifiant incorrect";

						if (tfId.getText().equals(id) && tfMdp.getText().equals(mdp)) {
							sceneAdmin(primaryStage, scene, observableStagiaire, group, primaryStage2);
						}else {
							lbl1.setText("Identifiant ou mot de passe incorrect");
							lbl1.setTextFill(Color.RED);
							Font f3=new Font("Calibri",  15);
							lbl1.setFont(f3);
						}	
					}

				});
			}
		});
	}

	@SuppressWarnings("unchecked")
	private void sceneAdmin(Stage primaryStage5, Scene scene,
			ObservableList<Stagiaire> observableStagiaire, Group group, Stage primaryStage2) {
		primaryStage2.close();
		BorderPane root = new BorderPane();
		//						StagiaireDAO dao = new StagiaireDAO();
		TextField affichage = new TextField("");

		AnchorPane anchorPane = new AnchorPane(); 

		anchorPane.setStyle("-fx-background-color: DARKGREY;");
		root.setStyle("-fx-background-color: LIGHTGRAY;");

		GridPane paneTableView = new GridPane();

		Button btnAjout = new Button("");
		btnAjout.setGraphic(new ImageView(new Image(cheminIconAjout)));

		anchorPane.getChildren().add(root);
		AnchorPane.setTopAnchor(root, 10.);
		AnchorPane.setBottomAnchor(root, 10.);
		AnchorPane.setLeftAnchor(root, 10.);
		AnchorPane.setRightAnchor(root, 10.);


		Scene scene5 = new Scene(anchorPane, 1500, 800);
		primaryStage5.setScene(scene5);
		primaryStage5.sizeToScene();
		primaryStage5.setTitle("Table de stagiaires");
		primaryStage5.show();


		//-----------------------------------------------------------------------------------------------------------
		//																	TableView
		//----------------------------------------------------------------------------------------------------------	


		//							ObservableList<Stagiaire>
		observableStagiaire.setAll(dao.getAll());

		TableView<Stagiaire> tableView = new TableView<Stagiaire>(observableStagiaire);

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

		TableColumn<Stagiaire, Void> colAction = new TableColumn<Stagiaire, Void>("Action");
		Callback<TableColumn<Stagiaire, Void>, TableCell<Stagiaire, Void>> cellFactory = 
				new Callback<TableColumn<Stagiaire, Void>, TableCell<Stagiaire, Void>>() {
			@Override
			public TableCell<Stagiaire, Void> call(final TableColumn<Stagiaire, Void> param) {
				final TableCell<Stagiaire, Void> cell = boutonsSuppessionEtModification(observableStagiaire, colNom,
						colPrenom, colDep, colFormation, colAnnee);
				return cell;
			}


		};
		colAction.setCellFactory(cellFactory);
		//Donner la colonne à notre tableview
		tableView.getColumns().addAll(colNom, colPrenom, colDep, colFormation, colAnnee, colAction); 
		//Ajuster la taille du tableau à son contenu
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		tableView.setPrefSize(850, 575);
		tableView.setStyle("-fx-alignment: BASELINE_CENTER;");
		tableView.setStyle("-fx-background-color: black;");

		root.setCenter(paneTableView);
		paneTableView.add(tableView, 0, 1);
		paneTableView.add(btnAjout, 0, 0);
		paneTableView.setVgap(5);
		paneTableView.setHgap(15);
		GridPane.setHalignment(btnAjout, HPos.RIGHT);
		paneTableView.setAlignment(Pos.CENTER);

		root.setCenter(paneTableView);

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



		box.getChildren().addAll(invit, download, export);
		box.setSpacing(60);
		box.setAlignment(Pos.CENTER);

		Label lbl2 = new Label("Session Administrateur");
		lbl2.setUnderline(true);
		box.getChildren().add(lbl2); 

		lbl2.setPadding(new Insets(30, 120, 0, 200));

		lbl2.setTextFill(Color.web("#00122E"));
		Font f6 = Font.font(STYLESHEET_CASPIAN, FontWeight.EXTRA_LIGHT, FontPosture.ITALIC, 28);
		lbl2.setFont(f6);


		//-----------------------------------------------------------------------------------------------------------
		//																GridPane
		//----------------------------------------------------------------------------------------------------------	
		GridPane grille= new GridPane(); 

		Label lblNom = new Label("Nom: ");
		TextField tfNom = new TextField();
		tfNom.setMinWidth(200);

		Label lblPrenom = new Label("Prenom: ");
		TextField tfPrenom = new TextField();
		tfPrenom.setMinWidth(200);

		Label lblDep = new Label("Département: ");
		TextField tfDep= new TextField();
		tfDep.setMinWidth(200);

		Label lblFormation = new Label("Formation: ");
		TextField tfFormation = new TextField();
		tfFormation.setMinWidth(200);

		Label lblAnnee = new Label("Annee: ");
		TextField tfAnnee = new TextField();
		tfAnnee.setMinWidth(200);


		grille.add(lblNom, 0, 0);
		grille.add(tfNom, 1, 0);
		grille.add(lblPrenom, 0, 1);
		grille.add(tfPrenom, 1, 1);
		grille.add(lblDep, 0, 2);
		grille.add(tfDep, 1, 2);
		grille.add(lblFormation, 0, 3);
		grille.add(tfFormation, 1, 3);
		grille.add(lblAnnee, 0, 4);
		grille.add(tfAnnee, 1, 4);

		grille.setVgap(20);

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
		rA.setPadding(new Insets(0, 0, 0, 150));
		Font f4 = Font.font("Cambria", FontWeight.THIN, FontPosture.REGULAR, 20 );
		rA.setFont(f4);


		//GridPane3

		Button btnRecherche = new Button("Rechercher");
		HBox hbox= new HBox(10);

		Button btnReini = new Button("Réinitialiser");

		hbox.getChildren().addAll(btnRecherche, btnReini);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(0,20,0,0));
		message.setTextFill(Color.web("#FF0000"));

		Image image2;
		Group group2;
		ImageView imageView2 = new ImageView();
		try {
			image2 = new Image(new FileInputStream(chemingLogoEQL));
			imageView2 = new ImageView(image2); 
			imageView2.setFitHeight(350); 
			imageView2.setFitWidth(400); 
			//Setting the preserve ratio of the image view 
			imageView2.setPreserveRatio(true); 
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
		group2 = new Group(imageView2);

		GridPane grille2 = new GridPane(); 
		grille2.addRow(0);
		grille2.addRow(1);
		grille2.addRow(2, grille3);
		grille2.addRow(3, grille);
		grille2.addRow(4, hbox);
		grille2.addRow(5, message);
		grille2.addRow(6, group2);


		grille2.setHgap(0);
		grille2.setVgap(30);
		root.setRight(grille2);
		grille2.setPadding(new Insets(30, 100, 50, 100));
		BorderPane.setAlignment(grille2, Pos.CENTER);


		export.setOnAction(actionEvent -> {
			importation(message);
		});

		invit.setOnAction((ActionEvent e)
				-> {
					primaryStage5.setScene(scene);
				});

		download.setOnAction((ActionEvent e)
				-> {
					ouvrirFichierDoc(message);
				});

		btnRecherche.setOnAction((ActionEvent e)
				-> {  
					if(verifierAnneeEstNombreAQuatreChiffre(tfAnnee, message)) {
						List<Stagiaire> listStagiaireRecherche = dao.rechercheAvancee(tfNom.getText(), 
								tfPrenom.getText(), tfDep.getText(), tfFormation.getText(), tfAnnee.getText());
						observableStagiaire.setAll(listStagiaireRecherche);
						tfNom.clear();
						tfPrenom.clear();
						tfDep.clear();
						tfFormation.clear();
						tfAnnee.clear();
					}
				}   
				);

		btnReini.setOnAction((ActionEvent e)
				-> {  
					message.setText("");
					tfNom.clear();
					tfPrenom.clear();
					tfDep.clear();
					tfFormation.clear();
					tfAnnee.clear();
					observableStagiaire.setAll(dao.getAll());
					activeMessage = false;
				}   
				);

		sceneBoutonAjout(observableStagiaire, btnAjout, tfNom, tfPrenom, tfDep, tfFormation, tfAnnee, message);

		tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Stagiaire>() {

			@Override
			public void changed(ObservableValue<? extends Stagiaire> observable, Stagiaire oldValue,
					Stagiaire newValue) {
				if(newValue != null) {
					tfNom.setText(newValue.getNom());
					tfPrenom.setText(newValue.getPrenom());
					tfDep.setText(newValue.getDep());
					tfFormation.setText(newValue.getFormation());
					tfAnnee.setText(Integer.toString(newValue.getAnnee()));
				}
			}
		});
	}

	private void sceneBoutonAjout(ObservableList<Stagiaire> observableStagiaire, Button btnAjout, TextField tfNom,
			TextField tfPrenom, TextField tfDep, TextField tfFormation, TextField tfAnnee, Label message) {
		btnAjout.setOnAction((ActionEvent e)
				-> {
					

					Stage primaryStage4 = new Stage();
					AnchorPane anchorPane4 = new AnchorPane(); 
					anchorPane4.setStyle("-fx-background-color: LIGHTGRAY;");

					GridPane grille= new GridPane(); 
					GridPane grilleMere = new GridPane();

					Label lblNom = new Label("Nom: ");
					TextField tfNomAjout = new TextField();
					tfNomAjout.setMinWidth(200);

					Label lblPrenom = new Label("Prenom: ");
					TextField tfPrenomAjout = new TextField();
					tfPrenomAjout.setMinWidth(200);

					Label lblDep = new Label("Département: ");
					TextField tfDepAjout = new TextField();
					tfDepAjout.setMinWidth(200);

					Label lblFormation = new Label("Formation: ");
					TextField tfFormationAjout = new TextField();
					tfFormationAjout.setMinWidth(200);

					Label lblAnnee = new Label("Annee: ");
					TextField tfAnneeAjout = new TextField();
					tfAnneeAjout.setMinWidth(200);

					Button ajouter = new Button ("Ajouter");

					Label messageAjout = new Label();
					messageAjout.setTextFill(Color.web("#FF0000"));
					messageAjout.setWrapText(true);
					messageAjout.setMaxWidth(350);

					grille.add(lblNom, 0, 0);
					grille.add(tfNomAjout, 1, 0);
					grille.add(lblPrenom, 0, 1);
					grille.add(tfPrenomAjout, 1, 1);
					grille.add(lblDep, 0, 2);
					grille.add(tfDepAjout, 1, 2);
					grille.add(lblFormation, 0, 3);
					grille.add(tfFormationAjout, 1, 3);
					grille.add(lblAnnee, 0, 4);
					grille.add(tfAnneeAjout, 1, 4);
					grilleMere.add(grille, 0, 0);
					grilleMere.add(ajouter, 0, 3);
					grilleMere.add(messageAjout, 0, 1);

					grille.setVgap(20);
					grilleMere.setVgap(20);
					GridPane.setHalignment(ajouter, HPos.CENTER);
					GridPane.setHalignment(messageAjout, HPos.CENTER);

					tfNomAjout.setPromptText("Nom");
					tfPrenomAjout.setPromptText("Prenom");
					tfDepAjout.setPromptText("Département");
					tfFormationAjout.setPromptText("Formation");
					tfAnneeAjout.setPromptText("Année");

					anchorPane4.getChildren().addAll(grilleMere);
					AnchorPane.setTopAnchor(grilleMere, 10.);
					AnchorPane.setBottomAnchor(grilleMere, 10.);
					AnchorPane.setLeftAnchor(grilleMere, 20.);
					AnchorPane.setRightAnchor(grilleMere, 20.);

					Scene scene4 = new Scene(anchorPane4, 350, 400);
					primaryStage4.setScene(scene4);
					primaryStage4.sizeToScene();
					primaryStage4.setTitle("Ajout");
					primaryStage4.show();

					ajouter.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							if(verifierAnneeEstNombreAQuatreChiffre(tfAnneeAjout, messageAjout)) {
								if(verifierTailleChamps(tfNomAjout.getText(), tfPrenomAjout.getText(), tfDepAjout.getText(), 
										tfFormationAjout.getText(), messageAjout)) {
									Stagiaire nouveauStagiaire = new Stagiaire(tfNomAjout.getText(), tfPrenomAjout.getText(), tfDepAjout.getText(), 
											tfFormationAjout.getText(), Integer.parseInt(tfAnneeAjout.getText()));
									dao.ajoutNouveauStagiaire(nouveauStagiaire);
									observableStagiaire.setAll(dao.getAll());
									tfNomAjout.clear();
									tfPrenomAjout.clear();
									tfDepAjout.clear();
									tfFormationAjout.clear();
									tfAnneeAjout.clear();
									primaryStage4.close();
									activeMessage = true;
									if(activeMessage){
										message.setText("Stagiaire créé.");	
									}
								}
							}
						}
					});
					
				
				}  
				);
	}

	private void ouvrirFichierDoc(Label message) {
		try {  
			//constructor of file class having file as argument
			if(!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not  
			{
				message.setText(messageDocumentationErreur);
				return;  
			}
			Desktop desktop = Desktop.getDesktop();  
			if(fichierDoc.exists())         //checks file exists or not  
				desktop.open(fichierDoc);              //opens the specified file  
		}  
		catch(Exception e) {  
			e.printStackTrace();  
		}  
	} 

	private TableCell<Stagiaire, Void> boutonsSuppessionEtModification(
			ObservableList<Stagiaire> observableStagiaire, TableColumn<Stagiaire, String> colNom,
			TableColumn<Stagiaire, String> colPrenom, TableColumn<Stagiaire, String> colDep,
			TableColumn<Stagiaire, String> colFormation, TableColumn<Stagiaire, Integer> colAnnee) {
		final TableCell<Stagiaire, Void> cell = new TableCell<Stagiaire, Void>() {
			Button supp = new Button("");
			Button modif = new Button("");
			public void updateItem(Void stagiaire, boolean empty) {
				super.updateItem(stagiaire, empty);
				if (empty) {
					setGraphic(null);
					setText(null);
				} else {
					supp.setGraphic(new ImageView(new Image(cheminIconSupp)));
					supp.setOnAction((ActionEvent event) -> {
						Stagiaire stagiaireASupprimer = getTableView().getItems().get(getIndex());
						dao.supprimerStagiaire(stagiaireASupprimer);
						observableStagiaire.setAll(dao.getAll());
						activeMessage = true;
						if(activeMessage){
							message.setText("Stagiaire supprimé.");	
						}
					});
					modif.setGraphic(new ImageView(new Image(cheminIconModif)));
					modif.setOnAction((ActionEvent event) -> {
						Stagiaire stagiaireAModifier = getTableView().getItems().get(getIndex());

						Stage primaryStage3 = new Stage();
						AnchorPane anchorPane3 = new AnchorPane(); 
						anchorPane3.setStyle("-fx-background-color: LIGHTGRAY;");

						GridPane grille= new GridPane(); 
						GridPane grilleMere = new GridPane();

						Label lblNom = new Label("Nom: ");
						TextField tfNom = new TextField();
						tfNom.setMinWidth(200);

						Label lblPrenom = new Label("Prenom: ");
						TextField tfPrenom = new TextField();
						tfPrenom.setMinWidth(200);

						Label lblDep = new Label("Département: ");
						TextField tfDep= new TextField();
						tfDep.setMinWidth(200);

						Label lblFormation = new Label("Formation: ");
						TextField tfFormation = new TextField();
						tfFormation.setMinWidth(200);

						Label lblAnnee = new Label("Annee: ");
						TextField tfAnnee = new TextField();
						tfAnnee.setMinWidth(200);

						Button modifier = new Button ("Modifier");

						Label messageModifier = new Label();
						messageModifier.setTextFill(Color.web("#FF0000"));
						messageModifier.setWrapText(true);
						messageModifier.setMaxWidth(350);

						grille.add(lblNom, 0, 0);
						grille.add(tfNom, 1, 0);
						grille.add(lblPrenom, 0, 1);
						grille.add(tfPrenom, 1, 1);
						grille.add(lblDep, 0, 2);
						grille.add(tfDep, 1, 2);
						grille.add(lblFormation, 0, 3);
						grille.add(tfFormation, 1, 3);
						grille.add(lblAnnee, 0, 4);
						grille.add(tfAnnee, 1, 4);
						grilleMere.add(grille, 0, 0);
						grilleMere.add(modifier, 0, 3);
						grilleMere.add(messageModifier, 0, 1);

						grille.setVgap(20);
						grilleMere.setVgap(20);
						GridPane.setHalignment(modifier, HPos.CENTER);
						GridPane.setHalignment(messageModifier, HPos.CENTER);

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

						anchorPane3.getChildren().addAll(grilleMere);
						AnchorPane.setTopAnchor(grilleMere, 10.);
						AnchorPane.setBottomAnchor(grilleMere, 10.);
						AnchorPane.setLeftAnchor(grilleMere, 20.);
						AnchorPane.setRightAnchor(grilleMere, 20.);

						Scene scene3 = new Scene(anchorPane3, 350, 400);
						primaryStage3.setScene(scene3);
						primaryStage3.sizeToScene();
						primaryStage3.setTitle("Modification");
						primaryStage3.show();

						tfNom.setText(stagiaireAModifier.getNom());
						tfPrenom.setText(stagiaireAModifier.getPrenom());
						tfDep.setText(stagiaireAModifier.getDep());
						tfFormation.setText(stagiaireAModifier.getFormation());
						tfAnnee.setText(Integer.toString(stagiaireAModifier.getAnnee()));

						modifier.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								if(verifierAnneeEstNombreAQuatreChiffre(tfAnnee, messageModifier)) {
									if(verifierTailleChamps(tfNom.getText(), tfPrenom.getText(), tfDep.getText(), 
											tfFormation.getText(), messageModifier)) {
										Stagiaire stagiaireRemplacant = new Stagiaire(tfNom.getText(), tfPrenom.getText(), 
												tfDep.getText(), 
												tfFormation.getText(), Integer.parseInt(tfAnnee.getText()));
										dao.modifierStagiaire(stagiaireAModifier, stagiaireRemplacant);
										observableStagiaire.setAll(dao.getAll());
										tfNom.clear();
										tfPrenom.clear();
										tfDep.clear();
										tfFormation.clear();
										tfAnnee.clear();
										primaryStage3.close();
										activeMessage = true;
										if(activeMessage) {
											message.setText("Stagiaire modifié.");
										}
									}
								}
							}
						});
					});
					HBox pane = new HBox(supp, modif);
					setGraphic(pane);
				}
			}
		};
		return cell;
	}

	public static void main(String[] args) {
		launch();
	}

	private boolean verifierAnneeEstNombreAQuatreChiffre(TextField tfAnnee, Label message) {
		try {
			if(!tfAnnee.getText().isEmpty()) {
				System.out.println("ok");
				System.out.println(tfAnnee.getText());
				if(tfAnnee.getLength() != 4) {
					throw new StringIndexOutOfBoundsException();
				}
				Integer.parseInt(tfAnnee.getText());
				return true;
			} else {
				return true;
			}

		} catch (StringIndexOutOfBoundsException e) {
			message.setText(messageErreurAnneQuatreChiffre);
		} catch (NumberFormatException e1) {
			message.setText(messageErreurFormatNumbero);
		}
		return false; 
	}

	private boolean verifierTailleChamps(String nom, String prenom, String dep, String formation, Label message) {
		if(nom.length() <= tailleChampMax) {
			if(prenom.length() <= tailleChampMax) {
				if(dep.length() <= tailleChampMax) {
					if(formation.length() <= tailleChampMax) {
						return true;
					} else {
						message.setText(messageFormationTailleGrande);
					}
				} else {
					message.setText(messageDepTailleGrande);
				}
			} else {
				message.setText(messagePrenomTailleGrande);
			}
		} else {
			message.setText(messageNomTailleGrande);
		}
		return false;
	}

	private void importation(Label message) {

		File out = new File(cheminExportPdf + UUID.randomUUID() + ".pdf");
		FileOutputStream fos;

		try {
			fos = new FileOutputStream(out);
			PDF pdf = new PDF(fos);
			Page page = new Page(pdf, A4.PORTRAIT);

			// police de l'entête de la table
			com.pdfjet.Font f1 = new com.pdfjet.Font(pdf, CoreFont.HELVETICA_BOLD);

			// font pour le pdf table data
			com.pdfjet.Font ff2 = new com.pdfjet.Font(pdf, CoreFont.HELVETICA);

			// pdf table 
			Table table = new Table();
			List<List<Cell>> tableData = new ArrayList<List<Cell>>();

			// table row
			List<Cell> tableRow = new ArrayList<Cell>();

			// Create the headers and ad them to the table row
			Cell cell = new Cell(f1, "Nom");
			tableRow.add(cell);

			cell = new Cell(f1, "Prénom");
			tableRow.add(cell);

			cell = new Cell(f1, "Departement");
			tableRow.add(cell);

			cell = new Cell(f1, "Formation");
			tableRow.add(cell);

			cell = new Cell(f1, "Année");
			tableRow.add(cell);

			tableData.add(tableRow);


			List<Stagiaire> stagiaires = dao.getStagiairesList();

			for (Stagiaire stagiaire : stagiaires) {


				Cell name = new Cell(ff2, stagiaire.getNom());
				Cell pren = new Cell(ff2, stagiaire.getPrenom());
				Cell dep = new Cell(ff2, stagiaire.getDep());
				Cell form = new Cell(ff2, stagiaire.getFormation());
				Cell year = new Cell(ff2, String.valueOf(stagiaire.getAnnee()));

				tableRow = new ArrayList<Cell>();
				tableRow.add(name);
				tableRow.add(pren);
				tableRow.add(dep);
				tableRow.add(form);
				tableRow.add(year);

				tableData.add(tableRow);

			}

			table.setData(tableData);
			table.setPosition(20f, 60f);
			table.setColumnWidth(0, 150f);
			table.setColumnWidth(1, 150f);
			table.setColumnWidth(2, 80f);
			table.setColumnWidth(3, 100f);
			table.setColumnWidth(4, 80f);

			while(true) {
				table.drawOn(page);
				if(!table.hasMoreData()) {
					table.resetRenderedPagesCount();
					break;
				}
				page = new Page(pdf, A4.PORTRAIT);
			}

			pdf.flush();
			fos.close();

			message.setText("Fichier créé avec succès !");

		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


















