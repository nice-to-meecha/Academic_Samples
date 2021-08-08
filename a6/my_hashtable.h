/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 6: Hashtables
 *
 *  Author: Stamesha Bello (03/13/2021)
 *
 *****************************************************
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  It is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  See <http://www.gnu.org/licenses/>.
 *****************************************************
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

#include "my_linkedlist.h"


#ifndef MY_HASHTABLE_H
#define MY_HASHTABLE_H

// STRUCT
/*
 * Represents a hash table that relies on chaining;
 * it retains pointers to linked lists, which carry anagrams
 * as values. This struct also retains the size of the
 * hash table as well as the number of elements stored
 * for the sake of maintaining a proper load factor.
 *
 * MEMBER: lists - the array within which pointers to nodes/linked lists
                   will be stored
 * MEMBER: num_of_lists - the total number of nodes/linked lists the lists array
                          can retain
 * MEMBER: num_of_stored_elements - the number of nodes (not linked lists)
                                    stored within the hash table
 */
typedef struct hash_table {
  node** lists;
  int num_of_lists;
  int num_of_stored_elements;
} hash_table;


// FUNCTIONS

/*
 * Generates a hash table, utilizing the number of lists it will contain.
 *
 * INPUT: table_size - the number of references to linked lists
 *                     the table will retain
 *
 * Returns the memory location of the new hash table.
 */
hash_table* Create(int table_size);

/*
 * Frees the hash table and all associated nodes from the heap.
 *
 * INPUT: hash_table - the hash table to be removed from
                       the heap
 *
 * Returns nothing.
 */
void Destroy(hash_table* hash);

/* Computes an int from a string, to be used for a key in a HTKeyValue.
 *
 * INPUT:
 *   buffer: a pointer to the array holding the string
 *   len: the length of the string
 *
 * Returns an int to be used as an input to FNVHashInt64 for the hash value.
 */
uint64_t FNVHash64(unsigned char *buffer, unsigned int len);

/*
 * Places new nodes in the middle or at the end of an established linked list,
 * whenever their hash values collide with those of nodes bearing
 * different keys.
 *
 * INPUT: hash - the hash table to which the new node will be added
 * INPUT: new_node - the node containing the word with a key value differing
                     from that of the starting node of the linked list
 * INPUT: index - the index of the hash table at which insertion
 *                within the linked list will take place
 *
 * Returns nothing.
 */
void AvoidDifferentKeyCollisions(hash_table* hash, node* new_node, int index);

/*
 * Generates a new node, utilizing the key and value provided,
 * and places it within the linked list matching the key.
 * The new node will replace the current head of the list.
 *
 * INPUT: hash - the hash table to which the new node will be added
 * INPUT: key - the key of the new node, which will determine the index
 *              where the node will be inserted as the new head
 * INPUT: value - the value of the new node to be added to the hash table
 *
 * Returns 0 if inserting the node was successful.
 * Returns 1 if the operation failed.
 */
int Insert(hash_table* hash, char* key, char* value);

/*
 * Returns the head node of the linked list belonging to the
 * specified index (based on the provided key) of the hash table.
 *
 * INPUT: hash - the hash table which will be perused for the
 *               linked list belonging to the provided key
 * INPUT: key - the key utilized to find the index to which the
 *              linked list of interest belongs
 *
 * Returns the linked list with nodes that retain the provided key
 */
node* Get(hash_table* hash, char* key);

/*
 * Prints all anagrams retained within the provided hash table,
 * ensuring all anagrams with unique letter combinations
 * are printed on separate lines.
 *
 * INPUT: hash - the hash table of anagrams, whose contents will be printed
 *
 * Returns nothing.
 */
void Print(hash_table* hash);

/*
 * Sorts the provided string alphabetically, utilizing
 * recursive calls to Partition in order to do so.
 *
 * INPUT: key - the string to be sorted alphabetically
 * INPUT: low - the lowest index of the provided string
 * INPUT: high - the highest index of the provided string
 *
 * Returns nothing.
 */
void Quicksort(char* key, int low, int high);

/*
 * Partially sorts the provided string, placing all letters with
 * ASCII values below that of the pivot (first letter) to its left
 * and all others to its right.
 *
 * INPUT: key - the string to be sorted according to the above pattern
 * INPUT: low - the lowest index of the provided string
 * INPUT: high - the highest index of the provided string
 *
 * Returns the index value where the pivot was moved
 */
int Partition(char* key, int low, int high);

/*
 * Swaps the values of the provided string, according to the provided indices
 *
 * INPUT: key - the string within which specified characters will be switched
 * INPUT: swap1 - one index at which a character of the provided string will
 *                be switched
 * INPUT: swap2 - the other index at which a character of the provided string
 *                will be switched
 *
 * Returns nothing
 */
void Swap(char* key, int swap1, int swap2);

/*
 * Determines the current load factor of the provided hash table
 *
 * INPUT: hash - the hash table for which the load factor will be determined
 *
 * Returns the current load factor
 */
double LoadFactor(hash_table* hash);

/*
 * Generates a larger hash table (to account for load factor) than that
 * provided, increasing the size and copying its contents into the new table.
 *
 * INPUT: hash - the current hash table, which will be resized accordingly
 *
 * Returns the memory location of the newly generated hash table
 */
hash_table* ResizeHashTable(hash_table* hash);

#endif
