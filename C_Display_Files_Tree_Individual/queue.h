/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Stamesha Bello (03/21/2021)
 */

#include "tree.h"

#ifndef QUEUE_H
#define QUEUE_H


// Queue Struct

/*
 * Represents a queue data structure
 *
 * MEMBER: front - the end of the queue from which a TreeNode will be dequeued
 * MEMBER: back - the end of the queue onto which a TreeNode will be enqueued
 */
typedef struct Queue {
  TreeNode* front;
  TreeNode* back;
} Queue;

// Queue Function Declarations


/*
 * Generates an empty queue data structure
 *
 * Returns an empty queue data structure
 */
Queue* CreateQueue();


/*
 * Adds a TreeNode to the back of the queue.
 *
 * INPUT: queue - the queue onto which the node will be added
 * INPUT: node - the node to be added to the queue
 *
 * Returns 0 if the node was successfully added.
 * Returns 1 if the enqueue process failed.
 */
int Enqueue(Queue* queue, TreeNode* node);

/*
 * Removes a TreeNode from the front of the queue.
 *
 * INPUT: queue - the queue from which the node will be removed
 *
 * Returns the node originally at the front of the queue
 */
TreeNode* Dequeue(Queue* queue);


/*
 * Frees the queue from the heap. All nodes previously stored
 * within the queue will be freed via DestroyTreeNode of the
 * tree.h/tree.c files.
 *
 * INPUT: queue - the queue to be freed
 * Returns nothing.
 */
void DestroyQueue(Queue* queue);

#endif
