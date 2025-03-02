package com.example.arbolbinarioapp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ABB {
    private Nodo raiz;

    public ABB() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }
        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(insertarRecursivo(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(insertarRecursivo(nodo.getDerecho(), valor));
        }
        return nodo;
    }

    public void eliminar(int valor) {
        raiz = eliminarRecursivo(raiz, valor);
    }

    private Nodo eliminarRecursivo(Nodo nodo, int valor) {
        if (nodo == null) {
            return null;
        }

        if (valor < nodo.getValor()) {
            nodo.setIzquierdo(eliminarRecursivo(nodo.getIzquierdo(), valor));
        } else if (valor > nodo.getValor()) {
            nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), valor));
        } else {
            if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) {
                return null;
            } else if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();
            } else if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo();
            } else {
                Nodo sucesor = encontrarSucesor(nodo.getDerecho());
                nodo.setValor(sucesor.getValor());
                nodo.setDerecho(eliminarRecursivo(nodo.getDerecho(), sucesor.getValor()));
            }
        }
        return nodo;
    }

    private Nodo encontrarSucesor(Nodo nodo) {
        while (nodo.getIzquierdo() != null) {
            nodo = nodo.getIzquierdo();
        }
        return nodo;
    }

    public int obtenerAltura() {
        return obtenerAlturaRecursivo(raiz);
    }

    private int obtenerAlturaRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        int alturaIzquierda = obtenerAlturaRecursivo(nodo.getIzquierdo());
        int alturaDerecha = obtenerAlturaRecursivo(nodo.getDerecho());
        return 1 + Math.max(alturaIzquierda, alturaDerecha);
    }

    public int obtenerNivel(int valor) {
        return obtenerNivelRecursivo(raiz, valor, 1);
    }

    private int obtenerNivelRecursivo(Nodo nodo, int valor, int nivel) {
        if (nodo == null) return -1;
        if (nodo.getValor() == valor) return nivel;
        int nivelIzq = obtenerNivelRecursivo(nodo.getIzquierdo(), valor, nivel + 1);
        return nivelIzq != -1 ? nivelIzq : obtenerNivelRecursivo(nodo.getDerecho(), valor, nivel + 1);
    }

    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) return 0;
        if (nodo.getIzquierdo() == null && nodo.getDerecho() == null) return 1;
        return contarHojasRecursivo(nodo.getIzquierdo()) + contarHojasRecursivo(nodo.getDerecho());
    }
    public boolean sonIdenticos(Nodo otroRaiz) {
        return sonIdenticosRecursivo(this.raiz, otroRaiz);
    }

    private boolean sonIdenticosRecursivo(Nodo nodo1, Nodo nodo2) {
        if (nodo1 == null && nodo2 == null) return true;
        if (nodo1 == null || nodo2 == null) return false;
        return (nodo1.getValor() == nodo2.getValor()) &&
                sonIdenticosRecursivo(nodo1.getIzquierdo(), nodo2.getIzquierdo()) &&
                sonIdenticosRecursivo(nodo1.getDerecho(), nodo2.getDerecho());
    }


    public int obtenerMinIterativo() {
        Nodo actual = raiz;
        while (actual != null && actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual != null ? actual.getValor() : -1;
    }

    public String recorridoAmplitud() {
        if (raiz == null) return "Árbol vacío";
        StringBuilder resultado = new StringBuilder();
        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);
        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            resultado.append(actual.getValor()).append(" ");
            if (actual.getIzquierdo() != null) cola.add(actual.getIzquierdo());
            if (actual.getDerecho() != null) cola.add(actual.getDerecho());
        }
        return resultado.toString().trim();
    }

    public void construirDesdeExpresion(String expresion) {
        ExpresionParser parser = new ExpresionParser();
        raiz = parser.construirArbol(expresion);
    }

    public void construirArbolEjemplo() {
        Nodo mult = new Nodo('*');
        mult.setIzquierdo(new Nodo('a'));
        mult.setDerecho(new Nodo('b'));

        Nodo div = new Nodo('/');
        div.setIzquierdo(new Nodo('c'));
        div.setDerecho(new Nodo('d'));

        Nodo suma = new Nodo('+');
        suma.setIzquierdo(mult);
        suma.setDerecho(div);

        raiz = suma;
    }
}