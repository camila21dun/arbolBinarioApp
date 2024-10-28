package com.example.arbolbinarioapp;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DibujoArbol extends Canvas {
    private ABB arbol;

    public DibujoArbol(ABB arbol) {
        this.arbol = arbol;
        this.setWidth(1000);
        this.setHeight(800);
    }

    public void dibujarArbol() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        if (arbol.getRaiz() != null) {

            dibujarNodo(gc, arbol.getRaiz(), 30, getHeight() / 2, 70);
        }
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double yOffset) {
        if (nodo == null) return;

        Color colorNodo = obtenerColorNodo(nodo);
        gc.setFill(colorNodo);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nodo.getValor()), x - 5, y + 5);

        double nuevoXOffset = 80;
        double nuevoYOffset = yOffset * 0.7;

        if (nodo.getDerecho() != null) {

            gc.strokeLine(x, y, x + nuevoXOffset, y - nuevoYOffset);
            dibujarNodo(gc, nodo.getDerecho(), x + nuevoXOffset, y - nuevoYOffset, nuevoYOffset);
        }

        if (nodo.getIzquierdo() != null) {

            gc.strokeLine(x, y, x + nuevoXOffset, y + nuevoYOffset);
            dibujarNodo(gc, nodo.getIzquierdo(), x + nuevoXOffset, y + nuevoYOffset, nuevoYOffset);
        }
    }

    private Color obtenerColorNodo(Nodo nodo) {
        if (nodo == arbol.getRaiz()) {
            return Color.RED; // Color para el nodo raíz
        } else if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
            return Color.GREEN; // Color para las hojas
        } else {
            return Color.YELLOW; // Color para nodos intermedios
        }
    }
}
