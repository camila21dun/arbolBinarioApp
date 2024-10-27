package com.example.arbolbinarioapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArbolApp extends Application {
    private ABB arbol;
    private DibujoArbol dibujoArbol;

    @Override
    public void start(Stage primaryStage) {
        arbol = new ABB();
        dibujoArbol = new DibujoArbol(arbol);

        TextField inputValor = new TextField();
        inputValor.setPromptText("Inserte un valor");
        inputValor.setMaxWidth(150);

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
        TextField inputNivelValor = new TextField();
        inputNivelValor.setPromptText("Valor para nivel");
        inputNivelValor.setMaxWidth(150);
        Button btnNivel = new Button("Nivel del Nodo");
        btnNivel.setOnAction(e -> {
            try {
                int valor = Integer.parseInt(inputNivelValor.getText());
                int nivel = arbol.obtenerNivel(valor);
                nivelLabel.setText("Nivel del nodo " + valor + ": " + (nivel != -1 ? nivel : "No encontrado"));
                inputNivelValor.clear();
            } catch (NumberFormatException ex) {
                inputNivelValor.clear();
                inputNivelValor.setPromptText("Valor inválido");
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
            String recorrido = arbol.recorridoAmplitud(); // Modificado para devolver String
            recorridoAmplitudLabel.setText("Recorrido en amplitud: " + recorrido);
        });

        VBox root = new VBox(10, inputValor, btnInsertar, btnEliminar, btnAltura, btnMinimo, btnHojas, inputNivelValor, btnNivel, btnRecorridoAmplitud, alturaLabel, minimoLabel, hojasLabel, nivelLabel, recorridoAmplitudLabel, dibujoArbol);
        Scene scene = new Scene(root, 1300, 800);

        primaryStage.setTitle("Visualización de Árbol Binario de Búsqueda");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
