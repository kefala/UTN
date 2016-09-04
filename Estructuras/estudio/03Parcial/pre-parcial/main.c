#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct element {
	int code;
	char description[50];
	int stock;
	float price;
	struct element *next;
} node;

void showList(node*);
void sort(node**);
void mergeList(node**, node**);
void load(node**, node*);
node *newNode(int, char [50], int, float);

void load(node** loadedProducts, node* product) {
    if (*loadedProducts == NULL) {
        *loadedProducts = product;
    }
    else {
        product->next = *loadedProducts;
        *loadedProducts = product;
    }
}

void sort(node** list) {
	printf("asdas\n");
}

void mergeList(node** list, node** list2) {
	node * first = *list;
	node * aux = NULL;
	node * new = NULL;
	int flag = 1;
	while (*list2) {
		while (*list) {
			if ((*list)->code == (*list2)->code) {
				(*list)->stock += (*list2)->stock;
			} 
			if (flag && (*list)->next && (*list)->next->code < (*list2)->code) {
				flag = 0;
				aux = (*list)->next;
				new = newNode((*list2)->code, (*list2)->description, (*list2)->stock, (*list2)->price);
				(*list)->next = new;
				new->next = aux;
				(*list) = aux;
			} 
			*list = (*list)->next;
		}
		flag = 1;
		*list = first;
		*list2 = (*list2)->next;
	}

}

node *newNode(int code, char description[50], int stock, float price) {
	node *a;
	a = (node*)malloc(sizeof(node));

	a->code = code;
	strcpy(a->description, description);
	a->stock = stock;
	a->price = price;
	a->next = NULL;
	return (a);
}

void showList(node* list) {
	if (list) {
		printf("%d \t\t %s \t\t %d \t\t %f\n", list->code, list->description, list->stock, list->price);
      	showList(list->next);
	}
}

int main() {
	int i = 0;
	node* loadedProducts = NULL;
	node* bougthProducts = NULL;
	node* product = NULL;
	printf("----------------------------------\n");
	printf("              asfas\n");
	printf("----------------------------------\n\n\n\n");

	/* loaded products */ 	
	product = newNode(1, "salchecheta", 50, 15.25);
	load(&loadedProducts, product);
	product = newNode(8, "oooooooooo", 10, 15.25);
	load(&loadedProducts, product);
	product = newNode(6, "aaaaaaaaaa", 30, 15.25);
	load(&loadedProducts, product);
	product = newNode(5, "salchecheee", 10, 15.25);
	load(&loadedProducts, product);
	showList(loadedProducts);

	//sort(&loadedProducts);

	printf("\n\n---------------\n\n\n");

	/* bougth list*/
	product = newNode(1, "salchecheta", 30, 15.25);
	load(&bougthProducts, product);
	product = newNode(4, "eeeeeeeeee", 10, 15.25);
	load(&bougthProducts, product);
	product = newNode(5, "eeeeeeeeee", 1000, 15.25);
	load(&bougthProducts, product);
	showList(bougthProducts);
	//sort(&bougthProducts);

	mergeList(&loadedProducts, &bougthProducts);
	/*printf("\n\n---------------\n\n\n");
	printf("\n\n---------------\n\n\n");
	printf("\n\n---------------\n\n\n");
	showList(loadedProducts);*/


	return 0;
}

