package com.example.sistemadulceria.controller;


import com.example.sistemadulceria.dao.UsuarioDAO;
import com.example.sistemadulceria.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import javafx.stage.Stage;


public class RegisterController {
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidoPat;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private Hyperlink linkLogin;

    @FXML
    public void onRegister(ActionEvent actionEvent) {

        // Validar campos obligatorios
        if (txtNombre.getText().isEmpty() || txtApellidoPat.getText().isEmpty() ||
                txtApellidoMaterno.getText().isEmpty() || txtEmail.getText().isEmpty() ||
                txtContrasena.getText().isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        //Crear objeto usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(txtNombre.getText());
        usuario.setApellidoPat(txtApellidoPat.getText());
        usuario.setApellidoMat(txtApellidoMaterno.getText());
        usuario.setEmail(txtEmail.getText());
        usuario.setContrasena(txtContrasena.getText());

        //Guardar objeto en la base de datos (cuando tenga los modelos)
        UsuarioDAO dao = new UsuarioDAO();
        boolean exito = dao.registrar(usuario);
        if (exito) {
            showAlert("Exito", "el usuario se registro correctamente", Alert.AlertType.INFORMATION);
            limpiarCampos();
            abrirLogin();
            cerrarVentana();
        } else {
            showAlert("Error", "el usuario no se pudo registrar", Alert.AlertType.ERROR);
        }

    }

    //metodo para mostrar alerta
    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidoPat.clear();
        txtApellidoMaterno.clear();
        txtEmail.clear();
        txtContrasena.clear();
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void abrirLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent root = loader.load();

            //Obtener ventana actual
            Stage stage = (Stage) linkLogin.getScene().getWindow();

            //Definir tamano igual a las otras pantallas
            Scene scene = new Scene(root, 430, 932);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Error", "No se pudo abrir la ventana de Login", Alert.AlertType.ERROR);
        }
    }
}