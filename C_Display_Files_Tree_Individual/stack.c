/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 * Author: Stamesha Bello (03/21/2021)
 */

#include <stdio.h>
#include <stdlib.h>

#include "stack.h"


Stack* CreateStack() {
  Stack* new_stack = (Stack*) malloc(sizeof(Stack));
  if (new_stack == NULL) {
    printf("Failed memory allocation.\n");
    exit(1);
  }

  new_stack->top = NULL;
  return new_stack;
}

int Push(Stack* stack, TreeNode* node) {
  if (stack == NULL || node == NULL) {
    return 1;
  }

  if (stack->top == NULL) {
    stack->top = node;
  } else {
    node->next = stack->top;
    stack->top = node;
  }

  return 0;
}


TreeNode* Pop(Stack* stack) {
  if (stack == NULL) {
    return NULL;
  }

  if (stack->top == NULL) {
    return NULL;
  }

  TreeNode* top = stack->top;
  stack->top = stack->top->next;
  top->next = NULL;
  return top;
}


void DestroyStack(Stack* stack) {
  if (stack == NULL) {
    return;
  }

  free(stack);
}
