package com.example.arbolbinarioapp;

import java.util.Stack;

public class ExpresionParser {
    public Nodo construirArbol(String expresion) {
        Stack<Nodo> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

        for (int i = 0; i < expresion.length(); i++) {
            char ch = expresion.charAt(i);

            if (ch == ' ') continue;

            if (ch == '(') {
                operadores.push(ch);
            }

            else if (Character.isLetterOrDigit(ch)) {
                operandos.push(new Nodo(ch));
            }

            else if (ch == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    Nodo operandoDerecho = operandos.pop();
                    Nodo operandoIzquierdo = operandos.pop();
                    char operador = operadores.pop();
                    Nodo nodo = new Nodo(operador);
                    nodo.setIzquierdo(operandoIzquierdo);
                    nodo.setDerecho(operandoDerecho);
                    operandos.push(nodo);
                }
                operadores.pop();
            }
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(ch)) {
                    Nodo operandoDerecho = operandos.pop();
                    Nodo operandoIzquierdo = operandos.pop();
                    char operador = operadores.pop();
                    Nodo nodo = new Nodo(operador);
                    nodo.setIzquierdo(operandoIzquierdo);
                    nodo.setDerecho(operandoDerecho);
                    operandos.push(nodo);
                }
                operadores.push(ch);
            }
        }

        while (!operadores.isEmpty()) {
            Nodo operandoDerecho = operandos.pop();
            Nodo operandoIzquierdo = operandos.pop();
            char operador = operadores.pop();
            Nodo nodo = new Nodo(operador);
            nodo.setIzquierdo(operandoIzquierdo);
            nodo.setDerecho(operandoDerecho);
            operandos.push(nodo);
        }


        return operandos.pop();
    }

    private int precedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
}
