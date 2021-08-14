/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 * Author: Stamesha Bello (03/21/2021)
 */

#include "tree.h"

#ifndef STACK_H
#define STACK_H

// Stack Struct


/*
 * Represents a stack data structure
 *
 * MEMBER: top - the top of the stack, where TreeNodes will be added and
 *               from which they will be removed.
 */
typedef struct Stack {
  TreeNode* top;
} Stack;


// Stack Function Declarations


/*
 * Generates an empty stack data structure
 *
 * Returns an empty stack data structure
 */
Stack* CreateStack();


/*
 * Adds a TreeNode to the top of the stack
 *
 * INPUT: stack - the stack to which the new node will be added
 * INPUT: node - the node to be added to the stack
 *
 * Returns 0 if the node was successfully added.
 * Returns 1 if the push process failed.
 */
int Push(Stack* stack, TreeNode* node);


/*
 * Removes a TreeNode from the top of the stack.
 *
 * INPUT: stack - the stack from which the top node will be removed
 *
 * Returns the TreeNode previously at the top of the stack
 */
TreeNode* Pop(Stack* stack);


/*
 * Frees the provided stack from the heap. All nodes previously stored
 * within the stack will be freed via DestroyTreeNode of the
 * tree.h/tree.c files.
 *
 * INPUT: stack - the stack to be freed
 *
 * Returns nothing.
 */
void DestroyStack(Stack* stack);

#endif
