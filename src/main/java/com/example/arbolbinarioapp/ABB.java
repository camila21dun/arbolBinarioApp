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
        Stack<Nodo> pila = new Stack<>();
        for (char ch : expresion.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                pila.push(new Nodo(ch));
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                Nodo operador = new Nodo(ch);
                operador.setDerecho(pila.pop());
                operador.setIzquierdo(pila.pop());
                pila.push(operador);
            }
        }
        raiz = pila.isEmpty() ? null : pila.pop();
    }
}