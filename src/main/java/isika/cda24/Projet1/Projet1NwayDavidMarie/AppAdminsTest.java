package isika.cda24.Projet1.Projet1NwayDavidMarie;

import javafx.stage.Stage;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import model.Stagiaire2;

public class AppAdminsTest extends Application {

	private Label messageLabel;

	@Override
	public void start(Stage primaryStage) {
		// Création de la grille principale
		GridPane gridPane = new GridPane();
		gridPane.setStyle("-fx-font-family: 'serif'");
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(20);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(10));

		// Ajout d'un dégradé en arrière-plan
		Stop[] stops = new Stop[] { new Stop(0, Color.rgb(27, 92, 102)), new Stop(1, Color.WHITE) };
		LinearGradient gradient = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);
		gridPane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

		HBox logoBox = new HBox();
		logoBox.setPadding(new Insets(0, 0, 0, 0));

		Image logoImage = new Image("Logo-Isika.png");
		ImageView logoView = new ImageView(logoImage);
		logoView.setFitWidth(240);// set the size of the logo
		logoView.setPreserveRatio(true);
		logoView.setSmooth(true);
		logoView.setCache(true);
		logoView.setBlendMode(BlendMode.SCREEN);
		logoBox.setAlignment(Pos.TOP_LEFT);// blend the color
		logoBox.getChildren().add(logoView);

		// Création du formulaire pour ajouter un stagiaire
		VBox formBox = new VBox();
		formBox.setSpacing(10);
		formBox.setAlignment(Pos.CENTER_LEFT);
		formBox.setPadding(new Insets(10));
		Label messageLabel = new Label("Ajouter un stagiaire");
		messageLabel.setFont(new Font("Arial", 16));
		TextField nomField = new TextField();
		nomField.setPromptText("Nom");
		TextField prenomField = new TextField();
		prenomField.setPromptText("Prénom");
		TextField departementField = new TextField();
		departementField.setPromptText("Département");
		TextField anneeField = new TextField();
		anneeField.setPromptText("Année d'entrée en formation");
		TextField promotionField = new TextField();
		promotionField.setPromptText("Promotion");
		formBox.getChildren().addAll(messageLabel, nomField, prenomField, departementField, anneeField, promotionField);

		// Création du bottomBox avec les 4 boutons
		HBox buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER_LEFT);
		buttonBox.setSpacing(20);
		buttonBox.setPadding(new Insets(5));

		// CREATION DU BOUTON AJOUTER + ACTION
		Button addButton = new Button("Ajouter");
		addButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (nomField.getText().isEmpty() || prenomField.getText().isEmpty()
						|| departementField.getText().isEmpty() || anneeField.getText().isEmpty()
						|| promotionField.getText().isEmpty()) {
					messageLabel.setText("Veuillez remplir tous les champs.");
				} else {
					Stagiaire2 nouveauStagiaire = new Stagiaire2(nomField.getText(), prenomField.getText(),
							departementField.getText(), anneeField.getText(), promotionField.getText());
					// Vérification si le stagiaire existe déjà dans la liste
					if (Stagiaire2.contains(nouveauStagiaire)) {
						messageLabel.setText("Erreur : ce stagiaire existe déjà dans l'annuaire.");
					} else {
						Stagiaire2.add(nouveauStagiaire);
						messageLabel.setText("Stagiaire ajouté avec succès.");
					}

					// Effacement des champs de texte
					clearFields();
				}
			}
		});

		// CREATION DU BOUTON RAFRAICHIR + ACTION

		Image imageDecline = new Image(getClass().getResourceAsStream("refresh.png"));
		Button refreshButton = new Button("Rafraîchir");
		refreshButton.setGraphic(new ImageView(imageDecline));
		StackPane layout = new StackPane();
		layout.getChildren().add(refreshButton);
		
		refreshButton.setOnAction(new EventHandler<ActionEvent>() {
		
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		Button returnButton = new Button("Retour");
		returnButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

			}
		});

		// CREATION DU BOUTON IMPRIMER + ACTION

//		Button printButton = new Button("Imprimer");
//		printButton.setOnAction(event -> {
//		    Printer printer = Printer.getDefaultPrinter();
//		    PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
//		    double scaleX = pageLayout.getPrintableWidth() / Stagiaire.getBoundsInParent().getWidth();
//		    double scaleY = pageLayout.getPrintableHeight() / stagiaireTable.getBoundsInParent().getHeight();
//		    table.getTransforms().add(new Scale(scaleX, scaleY));
//		    PrinterJob job = PrinterJob.createPrinterJob();
//		    if (job != null) {
//		        boolean printed = job.printPage(table);
//		        if (printed) {
//		            job.endJob();
//		        } else {
//		            messageLabel.setText("Erreur lors de l'impression");
//		        }
//		        table.getTransforms().remove(table.getTransforms().size() - 1);
//		    } else {
//		        messageLabel.setText("Impossible de créer la tâche d'impression");
//		    }
//		});

		// IL FAUT AJOUTER LE BOUTON PRINT !!!

		buttonBox.getChildren().addAll(addButton, refreshButton, returnButton);

		// Création du BorderPane pour aligner 3 boxes a gauche
		BorderPane borderpane = new BorderPane();
		borderpane.setPrefSize(250, 500);
		borderpane.setPadding(new Insets(30));

		borderpane.setTop(logoBox);
		borderpane.setAlignment(logoBox, Pos.TOP_LEFT);

		borderpane.setCenter(formBox);
		borderpane.setAlignment(formBox, Pos.CENTER_LEFT);

		borderpane.setBottom(buttonBox);
		borderpane.setAlignment(buttonBox, Pos.CENTER_LEFT);
		// ajoute borderpane a gridpane
		gridPane.add(borderpane, 0, 0);

		// Ajouter le formulaire pour ajouter un stagiaire dans la GridPane à gauche

		// Créer une HBox pour les boutons de recherche, suppression et modification
		HBox topBox = new HBox();
		topBox.setAlignment(Pos.CENTER);
		topBox.setSpacing(5);

		// Créer le TableView pour afficher les stagiaires
		TableView<Stagiaire2> stagiaireTable = new TableView<>();
		stagiaireTable.setEditable(false);

		// Créer les colonnes du tableau
		TableColumn<Stagiaire2, String> nomCol = new TableColumn<>("Nom");
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));

		TableColumn<Stagiaire2, String> prenomCol = new TableColumn<>("Prenom");
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));

		TableColumn<Stagiaire2, String> departementCol = new TableColumn<>("Département");
		departementCol.setCellValueFactory(new PropertyValueFactory<>("departement"));

		TableColumn<Stagiaire2, Integer> anneeEntreeCol = new TableColumn<>("Année");
		anneeEntreeCol.setCellValueFactory(new PropertyValueFactory<>("anneeEntree"));

		TableColumn<Stagiaire2, Integer> promotionCol = new TableColumn<>("Promotion");
		promotionCol.setCellValueFactory(new PropertyValueFactory<>("promotion"));

		// Ajouter les colonnes au TableView
		stagiaireTable.getColumns().addAll(nomCol, prenomCol, departementCol, anneeEntreeCol, promotionCol);

		// Créer une VBox pour le TableView
		VBox centerLeftBox = new VBox();
		centerLeftBox.setAlignment(Pos.CENTER);
		centerLeftBox.setSpacing(5);

		// Ajouter la HBox contenant les boutons au-dessus du TableView
		centerLeftBox.getChildren().addAll(topBox, stagiaireTable);

		// Ajouter la VBox centrée dans la GridPane
		gridPane.add(centerLeftBox, 1, 0);

		// Ajout du bottomPane à la grille
		gridPane.add(buttonBox, 0, 1);

		// CREATION DU BOUTON DE RECHERCHE

		Button searchButton = new Button("Rechercher");
		searchButton.setOnAction(e -> {
			// Action de recherche à implémenter
		});

		// CREATION DU BOUTON DE SUPPRESSION + ACTION

		Button deleteButton = new Button("Supprimer");
		deleteButton.setOnAction(new EventHandler<ActionEvent>() {

			// Créer la méthode pour supprimer
			@Override
			public void handle(ActionEvent event) {

				// Récupération du stagiaire sélectionné dans la table

				Stagiaire2 stagiaireSelectionne = stagiaireTable.getSelectionModel().getSelectedItem();

				if (stagiaireSelectionne != null) {
					int index = stagiaireTable.getSelectionModel().getSelectedIndex();
					if (index != -1) {
						stagiaireSelectionne.remove(index);
						messageLabel.setText("Stagiaire supprimé avec succès.");
					} else {
						messageLabel.setText("Veuillez sélectionner un stagiaire à supprimer.");
					}
				}
			}
		});

		// CREATION DU BOUTON DE MODIFICATION + ACTION

		Button modifyButton = new Button("Modifier");
		modifyButton.setOnAction(new EventHandler<ActionEvent>() {

			// Créer la méthode pour modifier
			@Override
			public void handle(ActionEvent event) {
				// Récupération des stagiaires dans la liste
				Stagiaire2 stagiaireSelectionne = stagiaireTable.getSelectionModel().getSelectedItem();

				if (stagiaireSelectionne == null) {
					messageLabel.setText("Erreur : veuillez sélectionner un stagiaire.");
				} else if (stagiaireSelectionne != null) {
					// MAJ des propriétés du stagiaire sélectionné
					stagiaireSelectionne.setNom(nomField.getText());
					stagiaireSelectionne.setPrenom(prenomField.getText());
					stagiaireSelectionne.setDepartement(departementField.getText());
					stagiaireSelectionne.setPromo(promotionField.getText());
					stagiaireSelectionne.setAnnee(anneeField.getText());
					stagiaireTable.refresh();
					messageLabel.setText("Stagiaire modifié avec succès");
				}

			}
		});

		// CREATION DU BOUTON LOGOUT + ACTION

		Button logOutButton = new Button("LogOut");
		logOutButton.setOnAction(event -> {
			// On revient à la page d'accueil
			AppAccueil accueilPage = new AppAccueil();
			Scene accueilScene = accueilPage.buildScene();
			primaryStage.setScene(accueilScene);
			// On affiche un message de confirmation
			messageLabel.setText("Vous avez été déconnecté.");

		});

		// Ajouter les boutons à la HBox
		topBox.getChildren().addAll(searchButton, deleteButton, modifyButton, logOutButton);

		// Création de la scène et affichage de la fenêtre
		Scene scene = new Scene(gridPane, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Page Administrateur");
		primaryStage.show();
	}

	protected void clearFields() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		launch(args);
	}
}