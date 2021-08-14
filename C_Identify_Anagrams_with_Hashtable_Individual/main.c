/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 6: Hashtables
 *
 *  Author: Stamesha Bello (03/13/2021)
 */

#include <stdio.h>
#include <string.h>

#include "my_linkedlist.h"
#include "my_hashtable.h"

const kStartingHashSize = 65000;
const kWordLengthLimit = 100;
const float kLoadFactorMax = 0.7;


int main(int argc, char** argv) {
  int required_number_of_arguments = 2;
  if (argc != required_number_of_arguments) {
    printf("There must be exactly 2 arguments -- the executable "
           "followed by the file name.\n");
    exit(1);
  }

  int file_argument_index = 1;
  FILE* word_file = fopen(argv[file_argument_index], "r");
  if (word_file == NULL) {
    printf("Failed to open %s\n", argv[file_argument_index]);
    exit(1);
  }

  hash_table* hash = Create(kStartingHashSize);
  char word[kWordLengthLimit], sorted_word[kWordLengthLimit];
  int first_element = 0, last_element = 1;

  while (fscanf(word_file, "%s", word) == 1) {
    char* value = strdup(word);
    char* key = strdup(word);
    Quicksort(key, first_element, strlen(key) - last_element);
    Insert(hash, key, value);

    if (kLoadFactorMax < LoadFactor(hash)) {
      hash = ResizeHashTable(hash);
    }
  }

  Print(hash);
  Destroy(hash);
  fclose(word_file);

  return 0;
}
