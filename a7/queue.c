/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Stamesha Bello (03/20/2021)
 */

#include <stdio.h>
#include <stdlib.h>

#include "queue.h"


Queue* CreateQueue() {
  Queue* new_queue = (Queue*) malloc(sizeof(Queue));
  new_queue->front = NULL;
  new_queue->back = NULL;
  return new_queue;
}

int Enqueue(Queue* queue, TreeNode* node) {
  if (queue == NULL || node == NULL) {
    return 1;
  }

  if (queue->front == NULL) {
    queue->front = node;
    queue->back = node;
  } else {
    queue->back->next = node;
    queue->back = queue->back->next;
  }
  return 0;
}

TreeNode* Dequeue(Queue* queue) {
  if (queue == NULL) {
    return NULL;
  }

  if (queue->front == NULL) {
    return NULL;
  }

  TreeNode* front = queue->front;
  queue->front = queue->front->next;
  if (queue->front == NULL) {
    queue->back = NULL;
  }

  front->next = NULL;
  return front;
}

void DestroyQueue(Queue* queue) {
  free(queue);
}
