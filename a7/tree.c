/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Stamesha Bello (03/20/2021)
 */


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "tree.h"


TreeNode* CreateTreeNode(char* path, char* name) {
  if (path == NULL || name == NULL) {
    printf("Incompatible argument(s)\n");
    exit(1);
  }

  TreeNode* node = (TreeNode*) malloc(sizeof(TreeNode));
  if (node == NULL) {
    printf("Failed memory allocation.\n");
    exit(1);
  }

  node->path = path;
  node->name = name;
  node->children = NULL;
  node->siblings = NULL;
  node->next = NULL;
  int no_established_level = 0;
  node->level = no_established_level;
  return node;
}


int AddChild(TreeNode* root, char* path, char* name) {
  if (root == NULL || path == NULL || name == NULL) {
    printf("Incompatible argument(s)\n");
    return 1;
  }

  TreeNode* new_child = CreateTreeNode(path, name);
  if (root->children == NULL) {
    root->children = new_child;
  } else {
    new_child->siblings = root->children;
    root->children = new_child;
  }

  int next_level = 1;
  new_child->level = root->level + next_level;
  return 0;
}


void DestroyTreeNode(TreeNode* root) {
  if (root == NULL) {
    return;
  }

  DestroyTreeNode(root->children);
  DestroyTreeNode(root->siblings);
  free(root->path);
  free(root->name);
  free(root);
}
