/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Stamesha Bello (03/21/2021)
 */

#ifndef TREE_H
#define TREE_H

// Tree/Node Struct

/*
 * Establishes the basis of a tree, which has the capacity
 * to store more than 2 children.
 *
 * MEMBER: path - the path of the file currently stored within the tree
 * MEMBER: name - the name of the file stored within the tree
 * MEMBER: children - all sub-trees of which the current tree is the parent
 * MEMBER: siblings - all trees at the same level as the current tree
 * MEMBER: next - a reference to the following node, to be utilized for
 *                queue and stack data structures
 * MEMBER: int - the depth of the current tree
 */
typedef struct TreeNode TreeNode;

struct TreeNode {
  char* path;
  char* name;
  TreeNode* children;
  TreeNode* siblings;
  TreeNode* next;
  int level;
};


/*
 * Generates a tree node, utilizing the provided path and name
 * arguments in order to populate its data members.
 *
 * INPUT: path - the path of the file to be stored within the tree
 * INPUT: name - the name of the file to be stored within the tree
 *
 * Returns the memory location of the newly-generated tree.
 */
TreeNode* CreateTreeNode(char* path, char* name);

/*
 * Adds a new child to the specified tree, according to the
 * provided path and name arguments. Also updates the level of the tree
 * as well as the sibling list of the children of the root tree.
 *
 * INPUT: root - the tree to which the new child will be added
 * INPUT: path - the path of the file to be stored as a child of the root
 * INPUT: name - the name of the file to be stored as a child of the root
 *
 * Returns 0 if the addition was successful. Returns 1 if the process failed.
 */
int AddChild(TreeNode* root, char* path, char* name);


/*
 * Frees the provided TreeNode as well as all of its children
 * and associated siblings from the heap.
 *
 * INPUT: root - the tree of which itself and all sub-trees -- of which it is
 *               an ancestor -- will be freed
 *
 * Returns nothing.
 */
void DestroyTreeNode(TreeNode* root);


#endif
