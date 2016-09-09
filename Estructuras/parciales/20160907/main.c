#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct number {
	int number;
	struct number *next;
} node;

int convertToNumber(char);//convierte un char a un int
void agregarAUnaPila(node**, char[71]); //genera una serie de numeros enteros, para asignar uno por uno a una Pila, en base a una string
void sumarALaPila(node**, int); //genera el numero en una cola, genero una cola ya que voy a acceder a los ultimos numeros para sumar
node *newNode(int);
void sumar(node*, node*, node**, int);

int main() {
	char stringNumber1[71] = {"1234"};
	char stringNumber2[71] = {"987456"};
	node * number1 = NULL;//pila
	node * number2 = NULL;//pila
	node * result = NULL;//pila

	printf("-----------------------------------------------------\n");
	printf("                      PARCIAL\n");
	printf("             sumar dos números enteros\n");
	printf("                de máximo 70 dígitos\n");
	printf("-----------------------------------------------------\n");
	
	printf("\nIngrese un número a sumar: ");
	scanf("%s", stringNumber1);
	printf("El número ingresado fué %s\n", stringNumber1);
	printf("\nIngrese otro número a sumar: ");
	scanf("%s", stringNumber2);
	printf("El otro número ingresado fué %s\n", stringNumber2);
	
	if (stringNumber1[0] == '0' && !stringNumber1[1] && stringNumber2[0] == '0' && !stringNumber2[1]) {
		printf("\n\nResultado: 0");
	} else {
		agregarAUnaPila(&number1, stringNumber1);
		agregarAUnaPila(&number2, stringNumber2);
		sumar(number1, number2, &result, 0);
		printf("\n\nResultado: ");
		while (result) {
			printf("%d", result->number);
			result = result->next;
		}
	}
	return 0;
}

void agregarAUnaPila(node** pila, char string[71]) {
	int i = 0, tmp = 0;
	while (string[i]) {
		tmp = convertToNumber(string[i++]);
		sumarALaPila(&(*pila), tmp);
	}
}
void sumar(node * number1, node *  number2, node ** result, int accareo){
	int num1 = 0, num2 = 0, parcial = 0;
	
	if (number2 != NULL) {
		num2 = number2->number;
		number2 = number2->next;
	} else {
		number2 = newNode(0);
	}
	if (number1 != NULL) {
		num1 = number1->number;
		number1 = number1->next;
	} else {
		number1 = newNode(0);
	}

	parcial = num2 + num1;
	
	if (accareo) {
		parcial++;
		accareo--;
	}
	if (parcial > 9) {	
		parcial -= 10;
		accareo = accareo + 1;
	}


	if (num1 != 0 || num2 != 0 || accareo != 0 || parcial != 0) {
		sumarALaPila(&(*result), parcial);
		sumar(number1, number2, &(*result), accareo);
	}
	
	return;
}

void sumarALaPila(node** pila, int number) {
	node *new = NULL;
	new = newNode(number);
	if (*pila == NULL) {
        *pila = new;
    }
    else {
        new->next = *pila;
        *pila = new;
    }
}

int convertToNumber(char letter) {
	int dig = 0;
	dig = (int)(letter - 48);
	return dig;	
}

node *newNode(int number) {
	node *numberNode;
	numberNode = (node*)malloc(sizeof(node));

	numberNode->number = number;
	numberNode->next = NULL;
	return (numberNode);
}