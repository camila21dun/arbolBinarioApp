package com.example.arbolbinarioapp;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DibujoArbol extends Canvas {
    private ABB arbol;

    public DibujoArbol(ABB arbol) {
        this.arbol = arbol;
        this.setWidth(1000);  // Anchura ajustada para ver el árbol de manera horizontal
        this.setHeight(800);  // Altura ajustada para espacio vertical entre nodos
    }

    public void dibujarArbol() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        if (arbol.getRaiz() != null) {
            // Comenzar a dibujar desde la posición inicial en la izquierda
            dibujarNodo(gc, arbol.getRaiz(), 30, getHeight() / 2, 70); // Ajusta 70 para espacio entre nodos en X
        }
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double yOffset) {
        if (nodo == null) return;

        Color colorNodo = obtenerColorNodo(nodo);
        gc.setFill(colorNodo);
        gc.fillOval(x - 15, y - 15, 30, 30); // Dibuja el nodo como un círculo
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nodo.getValor()), x - 5, y + 5); // Escribe el valor del nodo

        double nuevoXOffset = 80; // Distancia horizontal entre nodos
        double nuevoYOffset = yOffset * 0.7; // Factor de ajuste vertical entre niveles

        if (nodo.getIzquierdo() != null) {
            // Línea hacia el nodo izquierdo abajo (hacia la izquierda)
            gc.strokeLine(x, y, x + nuevoXOffset, y - nuevoYOffset);
            dibujarNodo(gc, nodo.getIzquierdo(), x + nuevoXOffset, y - nuevoYOffset, nuevoYOffset);
        }

        if (nodo.getDerecho() != null) {
            // Línea hacia el nodo derecho arriba (hacia la derecha)
            gc.strokeLine(x, y, x + nuevoXOffset, y + nuevoYOffset);
            dibujarNodo(gc, nodo.getDerecho(), x + nuevoXOffset, y + nuevoYOffset, nuevoYOffset);
        }
    }

    private Color obtenerColorNodo(Nodo nodo) {
        if (nodo == arbol.getRaiz()) {
            return Color.RED;
        } else if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            return Color.GREEN;
        } else {
            return Color.YELLOW;
        }
    }
}
