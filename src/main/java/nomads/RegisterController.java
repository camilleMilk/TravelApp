package nomads;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static nomads.MainApp.changeScene;
import static nomads.MainApp.countries;

public class RegisterController implements Initializable {
    @FXML
    private Button registerButton;
    @FXML
    private Button loginButton;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private ComboBox nationalityComboBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordPasswordField;
    @FXML
    private CheckBox outdoorsCheckBox;
    @FXML
    private CheckBox urbanCheckBox;
    @FXML
    private CheckBox culturalCheckBox;
    @FXML
    private CheckBox foodCheckBox;

    @FXML
    protected void onRegisterButtonClicked(ActionEvent e) throws SQLException, IOException {
        if ((!usernameTextField.getText().isBlank()) && (!passwordPasswordField.getText().isBlank()) && (!nationalityComboBox.getSelectionModel().isEmpty())
                || outdoorsCheckBox.isSelected() || urbanCheckBox.isSelected() || culturalCheckBox.isSelected() || foodCheckBox.isSelected()
                && (!firstNameTextField.getText().isBlank()) && (!lastNameTextField.getText().isBlank())) {
            User.getInstance().setUsername(usernameTextField.getText());
            User.getInstance().setFirstName(firstNameTextField.getText());
            User.getInstance().setLastName(lastNameTextField.getText());
            User.getInstance().setPassword(passwordPasswordField.getText());
            warningLabel.setText("All fields are filled");
            System.out.println(User.getInstance());
            registerUser();
            changeScene(registerButton, "destination-generator-view.fxml");
        } else {
            warningLabel.setText("Please fill out all the required fields!");
        }

    }

    @FXML
    protected void getNationalityComboBoxSelected(ActionEvent e) {
        User.getInstance().setNationality((String) nationalityComboBox.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    protected void onLoginButtonClicked(ActionEvent e) throws IOException {
        changeScene(registerButton, "login-view.fxml");
    }

    @FXML
    protected void onOutdoorsCheckBoxClicked(ActionEvent e) {
        User.getInstance().setOutdoors(outdoorsCheckBox.isSelected());
    }

    @FXML
    protected void onUrbanCheckBoxClicked(ActionEvent e) {
        User.getInstance().setUrban(urbanCheckBox.isSelected());
    }

    @FXML
    protected void onCulturalCheckBoxClicked(ActionEvent e) {
        User.getInstance().setCultural(culturalCheckBox.isSelected());
    }

    @FXML
    protected void onFoodCheckBoxClicked(ActionEvent e) {
        User.getInstance().setFood(foodCheckBox.isSelected());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nationalityComboBox.getItems().removeAll(nationalityComboBox.getItems());
        nationalityComboBox.getItems().addAll(countries);
    }

    private void registerUser() throws SQLException {
        UserModel userModel = new UserModel();
        userModel.registerUser();
    }
}