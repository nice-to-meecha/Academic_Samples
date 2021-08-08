/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Adrienne Slaughter (date unknown)
 *  Modified by:
 *   Stamesha Bello (03/20/2021)
 */

#include "tree.h"
#include "queue.h"
#include "stack.h"

#ifndef FILES_H
#define FILES_H


/*
 * Establishes all parent-child relationships between files
 * within the provided path (via Breadth-First Search),
 * effectively building a tree of files.
 *
 * INPUT: queue - the queue with which the file tree is produced
 * INPUT: root - the root node of the tree to be produced
 *
 * Returns nothing.
 */
void BuildTree(Queue* queue, TreeNode* root);


/*
 * Adds all files within the provided directory as children
 * of the given parent node.
 *
 * INPUT: queue - the queue to which the aforementioned child directories
 *                will be added for the sake of tree generation
 * INPUT: root - the parent node to which all files within the directory
 *               will be added as children
 * INPUT: dir - the path to which the root node belongs
 *
 * Returns nothing.
 */
void AddFiles(Queue* queue, TreeNode* root, char* dir);


/*
 * Prints all files and folders (via Depth-First Search) with spacing and
 * decoration meant to clearly indicate parent-child relationships.
 *
 * INPUT: stack - the stack used to traverse the file tree via pre-order
 * INPUT: root - the root node of the tree to be traversed
 *
 * Returns nothing.
 */
void PrintTree(Stack* stack, TreeNode* root);

/*
 * Aids in printing the file tree by providing clarity with spacing
 * files according to their levels within the tree as well as
 * utilizing box characters to further indicate parent-child relationships.
 *
 * INPUT: node - the node of the file currently being printed to the console;
 *               used to indicate spacing of the file of interest.
 *
 * Returns nothing.
 */
void FileSpacing(TreeNode* node);

#endif
