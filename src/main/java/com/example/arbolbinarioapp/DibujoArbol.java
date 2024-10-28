package com.example.arbolbinarioapp;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DibujoArbol extends Canvas {
    private ABB arbol;

    public DibujoArbol(ABB arbol) {
        this.arbol = arbol;
        this.setWidth(800);
        this.setHeight(1000);
    }

    public void dibujarArbol() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        if (arbol.getRaiz() != null) {
            dibujarNodo(gc, arbol.getRaiz(), getWidth() / 2, 30, 100);
        }
    }

    private void dibujarNodo(GraphicsContext gc, Nodo nodo, double x, double y, double xOffset) {
        if (nodo == null) return;

        Color colorNodo = obtenerColorNodo(nodo);
        gc.setFill(colorNodo);
        gc.fillOval(x - 15, y - 15, 30, 30);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(nodo.getValor()), x - 5, y + 5);

        double nuevoXOffset = xOffset * 1.5;
        double yOffset = 70;

        if (nodo.getIzquierdo() != null) {
            gc.strokeLine(x, y, x - nuevoXOffset, y + yOffset);
            dibujarNodo(gc, nodo.getIzquierdo(), x - nuevoXOffset, y + yOffset, nuevoXOffset / 2);
        }

        if (nodo.getDerecho() != null) {
            gc.strokeLine(x, y, x + nuevoXOffset, y + yOffset);
            dibujarNodo(gc, nodo.getDerecho(), x + nuevoXOffset, y + yOffset, nuevoXOffset / 2);
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
