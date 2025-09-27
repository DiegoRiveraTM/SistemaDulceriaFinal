package com.example.sistemadulceria.controller;

import com.example.sistemadulceria.dao.ProductoDAO;
import com.example.sistemadulceria.estructuras.ArbolBinarioProducto;
import com.example.sistemadulceria.model.Producto;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import com.example.sistemadulceria.estructuras.ArbolBinarioProducto;

import java.util.List;
import java.util.PriorityQueue;

public class PantallaPrincipalController {

    @FXML
    private ImageView imgPlus;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private ScrollPane scrollProductos;

    @FXML
    private VBox contenedorProductos;

    private List<Producto> productos; //cargar la lista desde la base de datos

    @FXML
    private Button btnCerrar;

    @FXML
    private javafx.scene.control.Label lblModo;

    @FXML
    private Button btnVista;

    private boolean modoArbol = false; //Falso cola, true arbol

    @FXML
    public void initialize()
    {

        btnAgregarProducto.setOnAction(e -> abrirVentanaNuevoProducto());
        ProductoDAO productoDAO = new ProductoDAO();
        btnVista.setOnAction(e -> toogleVista());

        productos = productoDAO.obtenerTodos();
        mostrarColaPrioridad();

        btnEliminarProducto.setOnAction(e -> eliminarProducto());
        lblModo.setText("Modo Cola de Prioridad");
    }

    @FXML
    private void abrirVentanaNuevoProducto()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nuevoProducto.fxml"));
            Parent root = loader.load();

            //Crear nueva ventana(stage)
            Stage stage = new Stage();
            stage.setTitle("Agregar Nuevo Producto");
            stage.setScene(new Scene(root));

            //Hacerla modal (bloquea la ventana principal hasta cerrar)
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            //Mostrar la Ventana
            stage.showAndWait();

            //Muestra los pruductos al cerrar la ventana de agregar
            ProductoDAO productoDAO = new ProductoDAO();
            productos = productoDAO.obtenerTodos();
            mostrarColaPrioridad();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void mostrarColaPrioridad()
    {
        //Crear la cola de prioridad
        PriorityQueue<Producto> cola = new PriorityQueue<>(productos);

        //Limpiar el contenedor
        contenedorProductos.getChildren().clear();

        //Sacar en orden y mostrar en la vista
        while(!cola.isEmpty())
        {
            Producto p = cola.poll();
            contenedorProductos.getChildren().add(crearCardProducto(p));
        }
    }

    @FXML
    private StackPane crearCardProducto(Producto producto)
    {
        StackPane card = new StackPane();
        card.setPrefSize(200,200);

        //Frente osea la imagen del producto
        final ImageView frontImage = new ImageView();
        frontImage.setFitWidth(180);
        frontImage.setFitHeight(180);
        frontImage.setPreserveRatio(true);

        try {
            String projectDir = System.getProperty("user.dir");
            String ruta = "file:" + projectDir + "/imagenes_dulces/" + producto.getProductos_url();
            frontImage.setImage(new Image(ruta));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se encontró la imagen: " + producto.getProductos_url());
        }

        //Reverso de la imagen
        final VBox backInfo =  new VBox(5);
        backInfo.setAlignment(javafx.geometry.Pos.CENTER);
        backInfo.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-padding: 10;");
        backInfo.getChildren().addAll(
                new javafx.scene.control.Label("Nombre: " + producto.getNombre()),
                new javafx.scene.control.Label("Tipo: " + producto.getTipoDulce().getNombre()),
                new javafx.scene.control.Label("Precio: $" + producto.getPrecio()),
                new javafx.scene.control.Label(producto.getDescripcion())
        );
        card.getChildren().addAll(frontImage, backInfo);

        //Efecto de que gira la tarjeta
        card.setOnMouseClicked(e -> {
            if(frontImage.isVisible())
            {
                girar(card, frontImage, backInfo);
            } else
            {
                girar(card, backInfo, frontImage);
            }
        });
        return card;
    }

    private void girar(StackPane card, Node visible, Node hiddenNode)
    {
        RotateTransition rotOut = new RotateTransition(Duration.millis(300), card);
        rotOut.setFromAngle(0);
        rotOut.setToAngle(90);

        RotateTransition rotIn = new RotateTransition(Duration.millis(300), card);
        rotIn.setFromAngle(-90);
        rotIn.setToAngle(0);

        rotOut.setOnFinished(e -> {
            visible.setVisible(false);
            hiddenNode.setVisible(true);
            rotIn.play();
        });
        rotOut.play();
    }

    @FXML
    private void eliminarProducto()
    {
        if(productos == null || productos.isEmpty())
        {
            System.out.println("No existe el producto");
            return;
        }

        //Crear la cola con las prioridades
        PriorityQueue<Producto> cola = new PriorityQueue<>(productos);

        //Sacar el de mayor prioridad
        Producto eliminado = cola.poll();

        if(eliminado != null)
        {
            System.out.println("Producto eliminado: " + eliminado.getNombre());

            ProductoDAO dao = new ProductoDAO();
            dao.eliminar(eliminado.getIdProducto());

            productos.remove(eliminado);

            mostrarColaPrioridad();
        }

    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void mostrarVistaArbol()
    {
        contenedorProductos.getChildren().clear();
        ArbolBinarioProducto arbol = new ArbolBinarioProducto();
        for(Producto producto : productos)
        {
            arbol.insertar(producto);
        }
        for(Producto p : arbol.inorden())
        {
            contenedorProductos.getChildren().add(crearCardProducto(p));
        }
    }

    @FXML
    private void toogleVista()
    {
        if(modoArbol)
        {
            mostrarColaPrioridad();
            btnVista.setText("Vista de Arbol");
            lblModo.setText("Modo Cola de prioridad");
            modoArbol = false;
        }
        else
        {
            mostrarVistaArbol();
            btnVista.setText("Vista de Cola");
            lblModo.setText("Modo Arbol Binario (inorden)");
            modoArbol = true;
        }
    }


}