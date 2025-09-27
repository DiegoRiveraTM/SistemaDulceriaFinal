package com.example.sistemadulceria.estructuras;

import com.example.sistemadulceria.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ArbolBinarioProducto
{

    private static class Nodo
    {
        Producto producto;
        Nodo izquierda, derecha;

        Nodo(Producto producto)
        {
            this.producto = producto;
        }
    }

    private Nodo raiz;

        //Insertar ordenado por nombre
        public void insertar(Producto producto)
        {
            raiz = insertarRec(raiz, producto);
        }

        private Nodo insertarRec(Nodo actual, Producto producto)
        {
            if(actual == null)
            {
                return new Nodo(producto);
            }
            if(producto.getNombre().compareToIgnoreCase(actual.producto.getNombre()) < 0)
            {
                actual.izquierda = insertarRec(actual.izquierda, producto);
            }else
            {
                actual.derecha = insertarRec(actual.derecha, producto);
            }
            return actual;
        }

        public List<Producto> inorden()
        {
            List<Producto> lista = new ArrayList<>();
            inordenRec(raiz, lista);
            return lista;
        }

        private void inordenRec(Nodo nodo, List<Producto> lista)
        {
            if(nodo != null)
            {
                inordenRec(nodo.izquierda, lista);
                lista.add(nodo.producto);
                inordenRec(nodo.derecha, lista);
            }
        }
}
