/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Adrienne Slaughter (date unknown)
 *  Modified by:
 *   Stamesha Bello (03/20/2021)
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <locale.h>

#include "files.h"

int main(int argc, char** argv) {
  int included_path = 2, file_argument = 1;
  char* dir;

  if (argc == included_path) {
    dir = strdup(argv[file_argument]);
  } else {
    dir = strdup(".");
  }
  // Duplicate name to avoid errors when freeing nodes
  char* name = strdup(dir);

  TreeNode* root = CreateTreeNode(dir, name);
  Queue* queue = CreateQueue();
  Stack* stack = CreateStack();

  BuildTree(queue, root);

  setlocale(LC_ALL, "");
  PrintTree(stack, root);

  DestroyTreeNode(root);
  DestroyQueue(queue);
  DestroyStack(stack);

  return 0;
}
