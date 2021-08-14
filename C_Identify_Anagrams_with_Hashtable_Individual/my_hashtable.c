/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 6: Hashtables
 *
 *  Author: Stamesha Bello (03/13/2021)
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>

#include "my_linkedlist.h"
#include "my_hashtable.h"


hash_table* Create(int table_size) {
  hash_table* new_hash = (hash_table*) malloc(sizeof(hash_table));
  if (new_hash == NULL) {
    printf("Failed memory allocation.\n");
    exit(1);
  }

  new_hash->lists = (node**) malloc(sizeof(node*) * table_size);
  if (new_hash->lists == NULL) {
    printf("Failed memory allocation.\n");
    exit(1);
  }

  new_hash->num_of_lists = table_size;
  int no_elements = 0;
  new_hash->num_of_stored_elements = no_elements;

// Sets all empty list elements to NULL to avoid dereferencing "bad pointers"
  int index, loop_start = 0;
  for (index = loop_start; index < table_size; index++) {
    new_hash->lists[index] = NULL;
  }

  return new_hash;
}

void Destroy(hash_table* hash_table) {
  if (hash_table == NULL) {
    return;
  }

int index, loop_start = 0;
  for (index = loop_start; index < hash_table->num_of_lists; index++) {
    DestroyLinkedList(hash_table->lists[index]);
  }

  free(hash_table->lists);
  free(hash_table);
  return;
}

uint64_t FNVHash64(unsigned char *buffer, unsigned int len) {
  // This code is adapted from code by Landon Curt Noll
  // and Bonelli Nicola:
  //
  // http://code.google.com/p/nicola-bonelli-repo/
  static const uint64_t FNV1_64_INIT = 0xcbf29ce484222325ULL;
  static const uint64_t FNV_64_PRIME = 0x100000001b3ULL;
  unsigned char *bp = (unsigned char *) buffer;
  unsigned char *be = bp + len;
  uint64_t hval = FNV1_64_INIT;
  /*
   * FNV-1a hash each octet of the buffer
   */
  while (bp < be) {
    /* xor the bottom with the current octet */
    hval ^= (uint64_t) * bp++;
    /* multiply by the 64 bit FNV magic prime mod 2^64 */
    hval *= FNV_64_PRIME;
  }
  /* return our new hash value */
  return hval;
}

void AvoidDifferentKeyCollisions(hash_table* hash, node* new_node, int index) {
  if (hash == NULL || new_node == NULL) {
    printf("Invalid input\n");
    exit(1);
  }

  node* temp_node = hash->lists[index];
  while ((temp_node->next != NULL) &&
         (temp_node->key != new_node->key)) {
    temp_node = temp_node->next;
  }

  if (temp_node->next != NULL) {
    temp_node->prev->next = new_node;
    new_node->prev = temp_node->prev;
    new_node->next = temp_node;
    temp_node->prev = new_node;

  } else {
    temp_node->next = new_node;
    new_node->prev = temp_node;
  }
}


int Insert(hash_table* hash, char* key, char* value) {
  if (hash == NULL || key == NULL || value == NULL) {
    printf("Invalid input\n");
    return 1;
  }

  uint64_t index = FNVHash64((unsigned char*) key, strlen(key)) %
                   hash->num_of_lists;
  node* new_node = CreateNode(key, value);
  int match = 0;

  if (hash->lists[index] == NULL) {
    hash->lists[index] = new_node;

  } else if (strcmp(hash->lists[index]->key, key) == match) {
    hash->lists[index]->prev = new_node;
    new_node->next = hash->lists[index];
    hash->lists[index] = new_node;

  } else {
    AvoidDifferentKeyCollisions(hash, new_node, index);
  }

  hash->num_of_stored_elements++;

  return 0;
}

node* Get(hash_table* hash, char* key) {
  if (hash == NULL || key == NULL) {
    return NULL;
  }

  int index = FNVHash64((unsigned char*) key, strlen(key)) % hash->num_of_lists;
  return hash->lists[index];
}

void Print(hash_table* hash) {
  if (hash == NULL) {
    return;
  }

  int index, loop_start = 0, count = 1;
  for (index = loop_start; index < hash->num_of_lists; index++) {
    node* node = hash->lists[index];
    // Avoid segmentation faults with empty indices
    if (node == NULL) {
      continue;
    }

    int match = 0;
    printf("Anagram %d (%s): ", count, node->key);
    while (node->next != NULL) {
      if (strcmp(node->next -> key, node->key) == match) {
        printf("%s, ", node->value);
      } else {
        printf("%s\n", node->value);
        count++;
        printf("Anagram %d (%s): ", count, node->next->key);
      }
        node = node->next;
    }
    printf("%s\n", node->value);
    count++;
  }
}

void Quicksort(char* key, int low, int high) {
  int pivot_upper_bound = 1;
  if (low < high) {
    int pivot = Partition(key, low, high);
    Quicksort(key, low, pivot);
    Quicksort(key, pivot + pivot_upper_bound, high);
  }
}

int Partition(char* key, int low, int high) {
  int pivot = (int) key[low];
  int index, next_element = 1, left = low;
  for (index = low + next_element; index <= high; index++) {
    if ((int) key[index] < pivot) {
      left++;
      Swap(key, left, index);
    }
  }
  Swap(key, left, low);
  return left;
}

void Swap(char* key, int swap1, int swap2) {
  int temp = key[swap1];
  key[swap1] = key[swap2];
  key[swap2] = temp;
}

double LoadFactor(hash_table* hash) {
  return ((double) hash->num_of_stored_elements/
          (double) hash->num_of_lists);
}

hash_table* ResizeHashTable(hash_table* hash) {
  int double_size = 2, index, loop_start = 0;
  int new_size = (hash->num_of_lists) * double_size;
  hash_table* new_hash = Create(new_size);

  for (index = loop_start; index < hash->num_of_lists; index++) {
    node* node = hash->lists[index];
    while (node != NULL) {
      char* new_key = strdup(node->key);
      char* new_value = strdup(node->value);
      Insert(new_hash, new_key, new_value);
      node = node->next;
    }
  }

  Destroy(hash);
  return new_hash;
}
