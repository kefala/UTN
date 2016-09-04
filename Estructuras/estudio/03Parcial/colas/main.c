#include <stdio.h>
#include <stdlib.h>

typedef struct elemento {
	int dato;
	struct elemento *sig;
} nodo;


void cargar(nodo** , int);
void mostrar(nodo*);
void borrar(nodo** );
nodo *nuevonodo(int);

int main () {
	nodo * lista;
	int cant, i, n;

	lista = NULL;
	printf("\n Ingrese Dato: ");
	scanf("%d", &n);
	cargar(&lista,n);
	
	

	printf("\n Llega acá");
	mostrar(lista);

	return 0;
}

void cargar(nodo** l, int x)
{
	if (*l == NULL) {
		nodo* nuevo;
		nuevo = nuevonodo(x);
		*l=nuevo;
	}
	else
		cargar(&(*l)->sig,x);

}

void mostrar(nodo* l)
{
	if(l) {
		printf("%3d",l->dato);
		mostrar(l->sig);
	}
}

void borrar(nodo** l)
{
	nodo *aux;
	aux=*l;
	if(*l)
	{
		printf("\nNodo a eliminar: %d",aux->dato);

		*l = aux->sig;
		free(aux);

		borrar(l);
	}
}

nodo *nuevonodo(int x)
{
	nodo *a;
	a=(nodo*)malloc(sizeof(nodo));

	a->dato = x;
	a->sig = NULL;
	return (a);
}