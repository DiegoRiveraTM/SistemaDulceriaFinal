package com.example.sistemadulceria.controller;

import com.example.sistemadulceria.dao.ProductoDAO;
import com.example.sistemadulceria.dao.TipoDulceDAO;
import com.example.sistemadulceria.model.Producto;
import com.example.sistemadulceria.model.TipoDulce;
import com.example.sistemadulceria.util.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.nio.file.StandardCopyOption;


public class NuevoProductoController {

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbTipoDulce;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Button btnBuscarImagen;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnCerrar;

    private String rutaImagen;

    @FXML
    public void initialize()
    {
        //Toda la informacion sobre los dulces, esta en la db, la prioridad, el tipo. Así que tenemos que traerla.

        TipoDulceDAO dao = new TipoDulceDAO();
        cbTipoDulce.getItems().addAll(dao.listarTipos());

        btnBuscarImagen.setOnAction(e -> seleccionarImagen());
        btnLimpiar.setOnAction(e -> limpiarFormulario());
        btnAgregar.setOnAction(e -> guardarProducto());
        btnCerrar.setOnAction(e -> cerrarVentana());
    }

    private int obtenerIdDulce(String nombre)
    {
        int id = -1;
        String sql = "SELECT id_dulce FROM TIPO_DULCE WHERE nombre = ? ";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_dulce");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @FXML
    private void guardarProducto()
    {
        try
        {
            String nombre = txtNombre.getText();
            String tipo = cbTipoDulce.getValue();
            String descripcion = txtDescripcion.getText();
            Double precio = Double.parseDouble(txtPrecio.getText());
            int stock =  Integer.parseInt(txtCantidad.getText());
            int idDulce = obtenerIdDulce(tipo);
            TipoDulce tipoDulce = new TipoDulce(idDulce, tipo);

            Producto producto = new Producto(nombre, descripcion, rutaImagen, stock, precio, tipoDulce);

            ProductoDAO dao = new ProductoDAO();
            if(dao.registrar(producto))
            {
                mostrarInfo("Producto Agregado con éxito");
                cerrarVentana();
            }
            else
            {
                mostrarError("Error al guardar en la base de datos");
            }
        } catch (Exception e)
        {
            mostrarError("Error: " + e.getMessage());
        }
    }

    @FXML
    private void limpiarFormulario()
    {
        txtNombre.clear();
        cbTipoDulce.getSelectionModel().clearSelection();
        txtDescripcion.clear();
        txtPrecio.clear();
        txtCantidad.clear();
        rutaImagen = null;
    }

    @FXML
    private void cerrarVentana()
    {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    private void mostrarError(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg);
        alert.showAndWait();
    }

    private void mostrarInfo(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.showAndWait();
    }

    @FXML
    private void seleccionarImagen()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen de producto");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Imágenes",
                        "*.png", "*.PNG",
                        "*.jpg", "*.JPG",
                        "*.jpeg", "*.JPEG")
        );

        File file = fileChooser.showOpenDialog(btnBuscarImagen.getScene().getWindow());
        if(file != null)
        try
        {
            String nombreImagen = file.getName();
            String projectDir = System.getProperty("user.dir");
            File destino = new File(projectDir + "/imagenes_dulces/" + nombreImagen);
            destino.getParentFile().mkdirs(); //crea la carpeta si no existe
            Files.copy(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

            //guarda solo el nombre de la imagen
            rutaImagen = nombreImagen;

            mostrarInfo("Imagen seleccionada y guardada en /imagenes_dulces/");
        } catch(Exception e)
        {
            mostrarError("Error al copiar la imagen: " + e.getMessage());
        }
    }
}

