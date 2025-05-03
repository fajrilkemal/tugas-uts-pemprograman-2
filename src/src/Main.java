package src;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
    private PatientQueue patientQueue = new PatientQueue();
    private ListView<Patient> patientListView = new ListView<>();
    private TextField nameField, ageField, conditionField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Manajemen Antrian Pasien");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        Label nameLabel = new Label("Nama:");
        grid.add(nameLabel, 0, 0);
        nameField = new TextField();
        grid.add(nameField, 1, 0);

        Label ageLabel = new Label("Usia:");
        grid.add(ageLabel, 0, 1);
        ageField = new TextField();
        grid.add(ageField, 1, 1);

        Label conditionLabel = new Label("Kondisi:");
        grid.add(conditionLabel, 0, 2);
        conditionField = new TextField();
        grid.add(conditionField, 1, 2);

        Button addButton = new Button("Tambah Pasien");
        grid.add(addButton, 1, 3);
        addButton.setOnAction(e -> addPatient());

        Button viewButton = new Button("Lihat Pasien Selanjutnya");
        grid.add(viewButton, 0, 4);
        viewButton.setOnAction(e -> viewNextPatient());

        patientListView.setPrefWidth(300);
        grid.add(patientListView, 0, 5, 2, 1);

        Scene scene = new Scene(grid, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void addPatient() {
        String name = nameField.getText();
        String ageText = ageField.getText();
        String condition = conditionField.getText();

        if (name.isEmpty() || ageText.isEmpty() || condition.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Semua kolom harus diisi.");
            alert.showAndWait();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Usia harus berupa angka.");
            alert.showAndWait();
            return;
        }

        Patient patient = new Patient(name, age, condition);
        patientQueue.addPatient(patient);
        updatePatientList();

        nameField.clear();
        ageField.clear();
        conditionField.clear();
    }

    private void viewNextPatient() {
        Patient nextPatient = patientQueue.viewNextPatient();
        if (nextPatient != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Pasien Selanjutnya: " + nextPatient.getName());
            alert.showAndWait();
            updatePatientList();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Tidak ada pasien dalam antrian.");
            alert.showAndWait();
        }
    }

    private void updatePatientList() {
        ObservableList<Patient> patients = FXCollections.observableArrayList(patientQueue.getAllPatients());
        patientListView.setItems(patients);
    }
}
