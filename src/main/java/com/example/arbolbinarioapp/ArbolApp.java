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
    private ABB segundoArbol;
    private DibujoArbol dibujoArbol;

    @Override
    public void start(Stage primaryStage) {
        arbol = new ABB();
        segundoArbol = new ABB();
        dibujoArbol = new DibujoArbol(arbol);


        TextField inputValor = new TextField();
        inputValor.setPromptText("Inserte un valor");
        inputValor.setMaxWidth(150);

        TextField inputExpresion = new TextField();
        inputExpresion.setPromptText("Inserte expresión algebraica");
        inputExpresion.setMaxWidth(200);

        TextField inputExpresionSegundoArbol = new TextField();
        inputExpresionSegundoArbol.setPromptText("Inserte segunda expresión");
        inputExpresionSegundoArbol.setMaxWidth(200);


        TextField inputValorSegundoArbol = new TextField();
        inputValorSegundoArbol.setPromptText("Inserte valor en segundo árbol");
        inputValorSegundoArbol.setMaxWidth(150);

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
        Button btnInsertarSegundoArbol = new Button("Insertar en Segundo Árbol");
        btnInsertarSegundoArbol.setOnAction(e -> {
            try {
                int valor = Integer.parseInt(inputValorSegundoArbol.getText());
                segundoArbol.insertar(valor);  // Insertar valor en el segundo árbol
                dibujoArbol.dibujarArbol();  // Opcional: podrías dibujar ambos árboles
                inputValorSegundoArbol.clear();
            } catch (NumberFormatException ex) {
                inputValorSegundoArbol.clear();
                inputValorSegundoArbol.setPromptText("Valor inválido");
            }
        });

        Button btnConstruirExpresion = new Button("Construir Árbol de Expresión");
        btnConstruirExpresion.setOnAction(e -> {
            String expresion = inputExpresion.getText();
            arbol.construirDesdeExpresion(expresion);
            dibujoArbol.dibujarArbol();
            inputExpresion.clear();
        });
        Button btnConstruirSegundoArbol = new Button("Construir Segundo Árbol");
        btnConstruirSegundoArbol.setOnAction(e -> {
            String expresion = inputExpresionSegundoArbol.getText();
            segundoArbol.construirDesdeExpresion(expresion);
            dibujoArbol.dibujarArbol();
            inputExpresionSegundoArbol.clear();
        });

        Button btnVerificarIdenticos = new Button("Verificar Si Son Idénticos");
        Label resultadoIdenticosLabel = new Label();
        btnVerificarIdenticos.setOnAction(e -> {
            boolean sonIdenticos = arbol.sonIdenticos(segundoArbol.getRaiz());
            resultadoIdenticosLabel.setText(sonIdenticos ? "Los árboles son idénticos" : "Los árboles no son idénticos");
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


        VBox controles = new VBox(10,inputValor, btnInsertar, btnEliminar, btnAltura, btnMinimo, btnHojas,  btnNivel, btnRecorridoAmplitud, inputExpresion, btnConstruirExpresion, alturaLabel, minimoLabel, hojasLabel, nivelLabel, recorridoAmplitudLabel, btnVerificarIdenticos,btnInsertarSegundoArbol,inputValorSegundoArbol,resultadoIdenticosLabel);
        controles.setPrefWidth(300);


        StackPane dibujoPane = new StackPane(dibujoArbol);


        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(controles, dibujoPane);
        splitPane.setDividerPositions(0.3);


        Scene scene = new Scene(splitPane, 1300, 800);
        primaryStage.setTitle("Visualización de Árbol Binario de Búsqueda");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}