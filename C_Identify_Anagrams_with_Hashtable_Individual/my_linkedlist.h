/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 6: Hashtables
 *
 *  Author: Stamesha Bello (03/13/2021)
 */

#include <stdio.h>
#include <stdlib.h>


#ifndef MY_LINKEDLIST_H
#define MY_LINKEDLIST_H

// STRUCT OF A DOUBLY-LINKED LIST
/*
 * Represents a node within a linked list, which carries anagrams
 * of a hash table. Due to its association with a hash table, it carries
 * both a value (anagram) as well as a key (sorted form of the anagram)
 * for storage. Due to membership within a linked list, it also maintains
 * links to the next and previous nodes of the list.
 *
 * MEMBER: key - the sorted version of the value to be stored within the node
 * MEMBER: value - the word to be stored within the node
 * MEMBER: next - a reference to the following node within a linked list
 * MEMBER: prev - a reference to the previous node within a linked list
 */
typedef struct node {
  char* key;
  char* value;
  struct node* next;
  struct node* prev;
} node;


// FUNCTIONS

/*
 * Creates a new node with its key (to be used for storage within
 * a hash table) and its value (anagram). As a new node, the references
 * to the previous and following nodes are initially NULL.
 *
 * INPUT: key - the key utilized to store values within
 * a hash table appropriately
 * INPUT: value - the anagram to be stored within the hash table
 *
 * Returns the memory address of the newly created node.
 */
node* CreateNode(char* key, char* value);

/*
 * Frees a node from the heap, for the sake of avoiding memory leaks.
 *
 * INPUT: node - the node reference to be removed from the heap
 *
 * Returns nothing.
 */
void DestroyNode(node* node);

/*
 * Removes an entire list of linked nodes from the heap.
 *
 * node - the head node of the linked list to be removed
          from the heap
 *
 * Returns nothing.
 */
void DestroyLinkedList(node* node);

#endif
