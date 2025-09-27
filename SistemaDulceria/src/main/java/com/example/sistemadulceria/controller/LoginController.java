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

public class LoginController
{
    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnEntrar;

    @FXML
    private Hyperlink linkRegistro;

    @FXML
    private Button btnCerrar;

    @FXML
    //Funcion para iniciar sesion de manera correcta sin que falte ningun campo
    public void onLogin(ActionEvent actionEvent)
    {
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();

        if(txtCorreo.getText().isEmpty() || txtPassword.getText().isEmpty())
        {
            showAlert("Error", "Los 2 campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.login(correo, password);

        //Para abrir una nueva ventana con excepcion (sirve porque aun no tengo la pantallaPrincipal)
        if(usuario != null)
        {
            try
            {
                //Intenta cargar
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pantallaPrincipal.fxml"));
                Parent root = loader.load();

                //Crear la nueva pantalla con tamano como de celular
                Scene scene = new Scene(root, 430,932);

                //Con esta descripcion
                Stage stage = new Stage();
                stage.setTitle("Sistema DulcerÃ­a - Principal");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

                //Cierra la ventana actual
                Stage currentStage = (Stage) txtCorreo.getScene().getWindow();
                currentStage.close();
            } catch (Exception e)
            {
                e.printStackTrace();
                showAlert("Error", "No se pudo abrir la pantalla principal", Alert.AlertType.ERROR);
            }
        } else
        {
            showAlert("Error", "Los 2 campos son obligatorios", Alert.AlertType.ERROR);
        }
    }

    //Funcion para mostrar alerta (reutilizable)
    private void showAlert(String titulo, String mensaje, Alert.AlertType tipo)
    {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void abrirRegistro()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent root = loader.load();

            //obtener ventana actual
            Stage stage = (Stage) linkRegistro.getScene().getWindow();

            //definir tamano fijo
            Scene scene = new Scene(root, 430, 932);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.sizeToScene(); //fuerza a la pantalla a quedarse de ese tamano
            stage.show();
        } catch(Exception ex)
        {
            ex.printStackTrace();
            showAlert("Error", "No se pudo abrir la ventana de Login", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}