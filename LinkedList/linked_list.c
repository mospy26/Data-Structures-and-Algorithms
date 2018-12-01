#ifndef LIST_H
#define LIST_H

#include <stdio.h>
#include <stdlib.h>

#define INDEX_GREATER(index, size)	(index >= size) ? 1 : 0
#define DEFAULT_SIZE 10

#endif

struct node {
	void* element;
	struct node* next;
};

typedef struct node node_t;

struct list {
    node_t* head;
    size_t size;
};

typedef struct list list_t;

int FOUND_REMOVE = -10;
int OUT_OF_BOUNDS = -10;

// return the size of the list
size_t size(list_t* list) {
	return list->size;
}

// check if a list is empty
int is_empty(list_t* list) {
	if(list->size == 0) {
		return 0;
	}
	return 1;
}

// initialise a list
void list_init(list_t* list) {
	list->size = 0;
	return;
}

// add an element to the end of the list
int add(list_t* list, void* element) {
	if(list->size == 0) {
		node_t* new_node = (node_t*)malloc(sizeof(node_t));
		if(NULL == new_node) {
			return -1;
		}
		new_node->element = element;
		new_node->next = NULL;
		list->head = new_node;
		list->size++;
		return 0;
	}
	node_t* current_node = list->head;
	for(size_t i = 0; i < list->size-1; i++) {
		current_node = current_node->next;
	}
	node_t* new_node = (node_t*)malloc(sizeof(node_t));
	if(NULL == new_node) {
		return -1;
	}
	new_node->element = element;
	new_node->next = NULL;
	current_node->next = new_node;
	list->size++;
	return 0;
}

//remove an element at specified index
void* remove_element(list_t* list, size_t index) {
	node_t* current_node = list->head;
	void* removed_element = NULL;
	if(0 == index) {
		list->head = current_node->next;
		current_node->next = NULL;
		removed_element = current_node->element;
		free(current_node->element);
		free(current_node);
		list->size--;
		return removed_element;
	}
	else if(list->size-1 == index) {
		for(int i = 0; i < list->size - 1; i++) {
			current_node = current_node->next;
		}
		current_node->next = NULL;
		current_node = current_node->next;
		void* removed_element = current_node->element;
		current_node->next = NULL;
		free(current_node);
		list->size--;
		return removed_element;
	}
	for(size_t i = 0; i < index; i++) {
		current_node = current_node->next;
	}
	node_t* this_node = current_node->next;
	node_t* next_node = current_node->next->next;
	current_node->next = next_node;
	this_node->next = NULL;
	free(this_node);
	list->size--;
	return removed_element;
}

// checks if a list contains the specified element
int contains(list_t* list, void* element) {
	node_t* current_node = list->head;
	for(size_t i = 0; i < list->size; i++) {
		if(*((int*)current_node->element) == *((int*)element)) {
			return 1;
		}
	}
	return 0;
}

// retriving element at a particular index
void* get(list_t* list, size_t index) {
	if(list->size == 0) {
		return NULL;
	}
	node_t* current_node = list->head;
	for(size_t i = 0; i < index; i++) {
		current_node = current_node->next;
	}
	return current_node->element;
}

// clear a list
void clear(list_t* list) {
	node_t* current_node = list->head;
	if(list->size == 0 || current_node == NULL) {
		return;
	}
	node_t* next_node;
	while(list->size > 0) {
		next_node = current_node->next;
		remove_element(list, 0);
		current_node = next_node;
	}
}
