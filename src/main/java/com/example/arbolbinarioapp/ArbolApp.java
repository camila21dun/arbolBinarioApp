package com.example.arbolbinarioapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;

public class ArbolApp extends Application {
    private ABB arbol;
    private DibujoArbol dibujoArbol;

    @Override
    public void start(Stage primaryStage) {
        arbol = new ABB();
        dibujoArbol = new DibujoArbol(arbol);

        // Controles para la parte izquierda de la ventana
        TextField inputValor = new TextField();
        inputValor.setPromptText("Inserte un valor");
        inputValor.setMaxWidth(150);

        TextField inputExpresion = new TextField();
        inputExpresion.setPromptText("Inserte expresión algebraica");
        inputExpresion.setMaxWidth(200);

        Label alturaLabel = new Label("Altura del árbol: ");
        Label nivelLabel = new Label("Nivel del nodo: ");
        Label minimoLabel = new Label("Valor mínimo: ");
        Label hojasLabel = new Label("Número de hojas: ");
        Label recorridoAmplitudLabel = new Label("Recorrido en amplitud: ");

        Button btnInsertar = new Button("Insertar");
        btnInsertar.setOnAction(e -> {
            try {
                int valor = Integer.parseInt(inputValor.getText());
                arbol.insertar(valor);
                dibujoArbol.dibujarArbol();
                inputValor.clear();
            } catch (NumberFormatException ex) {
                inputValor.clear();
                inputValor.setPromptText(" ");
            }
        });

        Button btnConstruirExpresion = new Button("Construir Árbol de Expresión");
        btnConstruirExpresion.setOnAction(e -> {
            String expresion = inputExpresion.getText();
            arbol.construirDesdeExpresion(expresion);
            dibujoArbol.dibujarArbol();
            inputExpresion.clear();
        });

       // TextField inputNivelValor = new TextField();
        //inputNivelValor.setPromptText("Valor para nivel");
        inputValor.setMaxWidth(150);
        Button btnNivel = new Button("Nivel del Nodo");
        btnNivel.setOnAction(e -> {
            try {
                int valor = Integer.parseInt(inputValor.getText());
                int nivel = arbol.obtenerNivel(valor);
                nivelLabel.setText("Nivel del nodo " + valor + ": " + (nivel != -1 ? nivel : "No encontrado"));
                inputValor.clear();
            } catch (NumberFormatException ex) {
                inputValor.clear();
                inputValor.setPromptText("Valor inválido");
            }
        });

        Button btnEliminar = new Button("Eliminar");
        btnEliminar.setOnAction(e -> {
            try {
                int valor = Integer.parseInt(inputValor.getText());
                arbol.eliminar(valor);
                dibujoArbol.dibujarArbol();
                inputValor.clear();
            } catch (NumberFormatException ex) {
                inputValor.clear();
                inputValor.setPromptText("Valor inválido");
            }
        });

        Button btnAltura = new Button("Obtener Altura");
        btnAltura.setOnAction(e -> {
            int altura = arbol.obtenerAltura();
            alturaLabel.setText("Altura del árbol: " + altura);
        });

        Button btnMinimo = new Button("Valor Mínimo");
        btnMinimo.setOnAction(e -> {
            int minimo = arbol.obtenerMinIterativo();
            minimoLabel.setText("Valor mínimo: " + minimo);
        });

        Button btnHojas = new Button("Número de Hojas");
        btnHojas.setOnAction(e -> {
            int hojas = arbol.contarHojas();
            hojasLabel.setText("Número de hojas: " + hojas);
        });

        Button btnRecorridoAmplitud = new Button("Recorrido en Amplitud");
        btnRecorridoAmplitud.setOnAction(e -> {
            String recorrido = arbol.recorridoAmplitud();
            recorridoAmplitudLabel.setText("Recorrido en amplitud: " + recorrido);
        });

        // Colocar los controles en el VBox
        VBox controles = new VBox(10, inputValor, btnInsertar, btnEliminar, btnAltura, btnMinimo, btnHojas,  btnNivel, btnRecorridoAmplitud, inputExpresion, btnConstruirExpresion, alturaLabel, minimoLabel, hojasLabel, nivelLabel, recorridoAmplitudLabel);
        controles.setPrefWidth(300);

        // Configuración del StackPane para el DibujoArbol
        StackPane dibujoPane = new StackPane(dibujoArbol);

        // Configuración del SplitPane
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(controles, dibujoPane);
        splitPane.setDividerPositions(0.3); // 30% de ancho para controles

        // Crear la escena
        Scene scene = new Scene(splitPane, 1300, 800);
        primaryStage.setTitle("Visualización de Árbol Binario de Búsqueda");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
