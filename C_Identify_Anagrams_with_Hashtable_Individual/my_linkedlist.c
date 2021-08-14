/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 6: Hashtables
 *
 *  Author: Stamesha Bello (03/13/2021)
 */

#include <stdio.h>
#include <stdlib.h>

#include "my_linkedlist.h"


node* CreateNode(char* key, char* value) {
  node* new_node = (node*) malloc(sizeof(struct node));
  if (new_node == NULL) {
    printf("Failed memory allocation.\n");
    exit(1);
  }

  if (key == NULL || value == NULL) {
    printf("Invalid input\n");
    exit(1);
  }

  new_node->key = key;
  new_node->value = value;
  new_node->next = NULL;
  new_node->prev = NULL;
  return new_node;
}

void DestroyNode(node* node) {
  if (node == NULL) {
    return;
  }
  // Must free value and key, since strings placed on
  // heap by strdup()
  free(node->value);
  free(node->key);
  free(node);
}

void DestroyLinkedList(node* node) {
  if (node == NULL) {
    return;
  }

  while (node->next != NULL) {
    node = node->next;
    DestroyNode(node->prev);
  }
  DestroyNode(node);
}
