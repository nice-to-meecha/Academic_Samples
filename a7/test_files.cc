/**
 *  Author: Adrienne Slaughter
 *  Modified by:
 *    Stamesha Bello (03/20/2021)
 *
 *  5008 Spr 2021
 *
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  It is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  See <http://www.gnu.org/licenses/>.
 */
#include "gtest/gtest.h"
extern "C" {
    #include "files.h"
    #include "tree.h"
    #include "queue.h"
    #include "stack.h"
}
const char* first = "first";
const char* second="second";
const char* third = "third";
const char* fourth = "fourth";

#define MAX_VALUE_LEN 75


TEST(Tree, CreateTreeNode) {
  char* path = strdup("no path");
  char* name = strdup("stray");
  TreeNode* root = CreateTreeNode(path, name);
  EXPECT_STREQ(root->path, "no path");
  EXPECT_STREQ(root->name, "stray");
  EXPECT_TRUE(root->children == NULL);
  EXPECT_TRUE(root->siblings == NULL);
  EXPECT_TRUE(root->next == NULL);
  EXPECT_EQ(root->level, 0);

  DestroyTreeNode(root);
}


TEST(Tree, AddOneChild) {
  char* root_path = strdup("no path");
  char* root_name = strdup("stray");
  TreeNode* root = CreateTreeNode(root_path, root_name);

  char* child_path = strdup("squiggly path");
  char* child_name = strdup("dizzy");
  AddChild(root, child_path, child_name);
  ASSERT_TRUE(root->children);
  EXPECT_STREQ(root->children->path, "squiggly path");
  EXPECT_STREQ(root->children->name, "dizzy");
  EXPECT_TRUE(root->children->children == NULL);
  EXPECT_TRUE(root->children->siblings == NULL);
  EXPECT_TRUE(root->children->next == NULL);
  EXPECT_EQ(root->children->level, 1);

  DestroyTreeNode(root);
  }


TEST(Tree, AddMultipleChildren) {
  char* root_path = strdup("no path");
  char* root_name = strdup("stray");
  TreeNode* root = CreateTreeNode(root_path, root_name);

  char* child1_path = strdup("squiggly path");
  char* child1_name = strdup("dizzy");
  AddChild(root, child1_path, child1_name);
  ASSERT_TRUE(root->children);
  EXPECT_STREQ(root->children->path, "squiggly path");
  EXPECT_STREQ(root->children->name, "dizzy");
  EXPECT_TRUE(root->children->children == NULL);
  EXPECT_TRUE(root->children->siblings == NULL);
  EXPECT_TRUE(root->children->next == NULL);
  EXPECT_EQ(root->children->level, 1);

  char* child2_path = strdup("straight path");
  char* child2_name = strdup("line");
  AddChild(root, child2_path, child2_name);
  EXPECT_STREQ(root->children->path, "straight path");
  EXPECT_STREQ(root->children->name, "line");
  EXPECT_TRUE(root->children->children == NULL);
  EXPECT_STREQ(root->children->siblings->name, "dizzy");
  EXPECT_EQ(root->children->level, 1);
  EXPECT_TRUE(root->children->level == root->children->siblings->level);

  char* child3_path = strdup("tricky path");
  char* child3_name = strdup("clown");
  AddChild(root, child3_path, child3_name);
  EXPECT_STREQ(root->children->path, "tricky path");
  EXPECT_STREQ(root->children->name, "clown");
  EXPECT_TRUE(root->children->children == NULL);
  EXPECT_STREQ(root->children->siblings->name, "line");
  EXPECT_STREQ(root->children->siblings->siblings->name, "dizzy");
  EXPECT_EQ(root->children->level, 1);
  EXPECT_TRUE(root->children->level == root->children->siblings->level);
  EXPECT_TRUE(root->children->level == root->children->siblings->\
              siblings->level);

  char* grandchild_path = strdup("hidden path");
  char* grandchild_name = strdup("treasure");
  AddChild(root->children, grandchild_path, grandchild_name);
  EXPECT_STREQ(root->children->children->path, "hidden path");
  EXPECT_STREQ(root->children->children->name, "treasure");
  EXPECT_TRUE(root->children->children->children == NULL);
  EXPECT_TRUE(root->children->children->siblings == NULL);
  EXPECT_EQ(root->children->children->level, 2);

  DestroyTreeNode(root);
}


TEST(Queue, CreateQueue) {
  Queue* queue = CreateQueue();
  EXPECT_TRUE(queue->front == NULL);
  EXPECT_TRUE(queue->back == NULL);

  DestroyQueue(queue);
}


TEST(Queue, EnqueueOne) {
  Queue* queue = CreateQueue();
  char* path = strdup("fake path");
  char* name = strdup("q1");
  TreeNode* q1 = CreateTreeNode(path, name);
  Enqueue(queue, q1);
  ASSERT_TRUE(queue->front && queue->back);
  EXPECT_STREQ(queue->front->path, "fake path");
  EXPECT_STREQ(queue->front->name, "q1");
  EXPECT_STREQ(queue->back->path, "fake path");
  EXPECT_STREQ(queue->back->name, "q1");

  DestroyTreeNode(q1);
  DestroyQueue(queue);
}


TEST(Queue, EnqueueMultiple) {
  Queue* queue = CreateQueue();
  char* q1_path = strdup("fake path");
  char* q1_name = strdup("q1");
  TreeNode* q1 = CreateTreeNode(q1_path, q1_name);
  Enqueue(queue, q1);
  ASSERT_TRUE(queue->front && queue->back);
  EXPECT_STREQ(queue->front->path, "fake path");
  EXPECT_STREQ(queue->front->name, "q1");
  EXPECT_STREQ(queue->back->path, "fake path");
  EXPECT_STREQ(queue->back->name, "q1");

  char* q2_path = strdup("ugh");
  char* q2_name = strdup("q2");
  TreeNode* q2 = CreateTreeNode(q2_path, q2_name);
  Enqueue(queue, q2);
  EXPECT_STREQ(queue->front->path, "fake path");
  EXPECT_STREQ(queue->front->name, "q1");
  EXPECT_STREQ(queue->back->path, "ugh");
  EXPECT_STREQ(queue->back->name, "q2");

  char* q3_path = strdup("fakity fake");
  char* q3_name = strdup("q3");
  TreeNode* q3 = CreateTreeNode(q3_path, q3_name);
  Enqueue(queue, q3);
  EXPECT_STREQ(queue->front->path, "fake path");
  EXPECT_STREQ(queue->front->name, "q1");
  EXPECT_STREQ(queue->front->next->path, "ugh");
  EXPECT_STREQ(queue->front->next->name, "q2");
  EXPECT_STREQ(queue->back->path, "fakity fake");
  EXPECT_STREQ(queue->back->name, "q3");

  DestroyTreeNode(q3);
  DestroyTreeNode(q2);
  DestroyTreeNode(q1);
  DestroyQueue(queue);
}


TEST(Queue, Dequeue) {
  Queue* queue = CreateQueue();
  char* q1_path = strdup("fake path");
  char* q1_name = strdup("q1");
  TreeNode* q1 = CreateTreeNode(q1_path, q1_name);
  Enqueue(queue, q1);
  ASSERT_TRUE(queue->front && queue->back);
  EXPECT_STREQ(queue->front->path, "fake path");
  EXPECT_STREQ(queue->front->name, "q1");
  EXPECT_STREQ(queue->back->path, "fake path");
  EXPECT_STREQ(queue->back->name, "q1");

  char* q2_path = strdup("ugh");
  char* q2_name = strdup("q2");
  TreeNode* q2 = CreateTreeNode(q2_path, q2_name);
  Enqueue(queue, q2);
  EXPECT_STREQ(queue->front->path, "fake path");
  EXPECT_STREQ(queue->front->name, "q1");
  EXPECT_STREQ(queue->back->path, "ugh");
  EXPECT_STREQ(queue->back->name, "q2");

  TreeNode* deq1 = Dequeue(queue);
  EXPECT_STREQ(deq1->path, "fake path");
  EXPECT_STREQ(deq1->name, "q1");
  EXPECT_STREQ(queue->front->path, "ugh");
  EXPECT_STREQ(queue->front->name, "q2");
  EXPECT_STREQ(queue->back->path, "ugh");
  EXPECT_STREQ(queue->back->name, "q2");

  TreeNode* deq2 = Dequeue(queue);
  EXPECT_STREQ(deq2->path, "ugh");
  EXPECT_STREQ(deq2->name, "q2");
  EXPECT_TRUE(queue->front == NULL);
  EXPECT_TRUE(queue->back == NULL);

  char* q3_path = strdup("fakity fake");
  char* q3_name = strdup("q3");
  TreeNode* q3 = CreateTreeNode(q3_path, q3_name);
  Enqueue(queue, q3);
  EXPECT_STREQ(queue->front->path, "fakity fake");
  EXPECT_STREQ(queue->front->name, "q3");
  EXPECT_STREQ(queue->back->path, "fakity fake");
  EXPECT_STREQ(queue->back->name, "q3");

  DestroyTreeNode(q3);
  DestroyTreeNode(q2);
  DestroyTreeNode(q1);
  DestroyQueue(queue);
}


TEST(Stack, CreateStack) {
  Stack* stack = CreateStack();
  ASSERT_TRUE(stack->top == NULL);
  DestroyStack(stack);
}


TEST(Stack, PushOne) {
  Stack* stack = CreateStack();
  char* s1_path = strdup("fake path");
  char* s1_name = strdup("s1");
  TreeNode* s1 = CreateTreeNode(s1_path, s1_name);
  Push(stack, s1);
  EXPECT_STREQ(stack->top->path, "fake path");
  EXPECT_STREQ(stack->top->name, "s1");
  EXPECT_TRUE(stack->top->next == NULL);

  DestroyTreeNode(s1);
  DestroyStack(stack);
}


TEST(Stack, PushMultiple) {
  Stack* stack = CreateStack();
  char* s1_path = strdup("fake path");
  char* s1_name = strdup("s1");
  TreeNode* s1 = CreateTreeNode(s1_path, s1_name);
  Push(stack, s1);
  EXPECT_STREQ(stack->top->path, "fake path");
  EXPECT_STREQ(stack->top->name, "s1");
  EXPECT_TRUE(stack->top->next == NULL);

  char* s2_path = strdup("ugh");
  char* s2_name = strdup("s2");
  TreeNode* s2 = CreateTreeNode(s2_path, s2_name);
  Push(stack, s2);
  EXPECT_STREQ(stack->top->path, "ugh");
  EXPECT_STREQ(stack->top->name, "s2");
  EXPECT_STREQ(stack->top->next->path, "fake path");
  EXPECT_STREQ(stack->top->next->name, "s1");

  char* s3_path = strdup("fakity fake");
  char* s3_name = strdup("s3");
  TreeNode* s3 = CreateTreeNode(s3_path, s3_name);
  Push(stack, s3);
  EXPECT_STREQ(stack->top->path, "fakity fake");
  EXPECT_STREQ(stack->top->name, "s3");
  EXPECT_STREQ(stack->top->next->path, "ugh");
  EXPECT_STREQ(stack->top->next->name, "s2");
  EXPECT_STREQ(stack->top->next->next->path, "fake path");
  EXPECT_STREQ(stack->top->next->next->name, "s1");

  DestroyTreeNode(s1);
  DestroyTreeNode(s2);
  DestroyTreeNode(s3);
  DestroyStack(stack);
}


TEST(Stack, Pop) {
  Stack* stack = CreateStack();
  char* s1_path = strdup("fake path");
  char* s1_name = strdup("s1");
  TreeNode* s1 = CreateTreeNode(s1_path, s1_name);
  Push(stack, s1);
  EXPECT_STREQ(stack->top->path, "fake path");
  EXPECT_STREQ(stack->top->name, "s1");
  EXPECT_TRUE(stack->top->next == NULL);

  char* s2_path = strdup("ugh");
  char* s2_name = strdup("s2");
  TreeNode* s2 = CreateTreeNode(s2_path, s2_name);
  Push(stack, s2);
  EXPECT_STREQ(stack->top->path, "ugh");
  EXPECT_STREQ(stack->top->name, "s2");
  EXPECT_STREQ(stack->top->next->path, "fake path");
  EXPECT_STREQ(stack->top->next->name, "s1");

  char* s3_path = strdup("fakity fake");
  char* s3_name = strdup("s3");
  TreeNode* s3 = CreateTreeNode(s3_path, s3_name);
  Push(stack, s3);
  EXPECT_STREQ(stack->top->path, "fakity fake");
  EXPECT_STREQ(stack->top->name, "s3");
  EXPECT_STREQ(stack->top->next->path, "ugh");
  EXPECT_STREQ(stack->top->next->name, "s2");
  EXPECT_STREQ(stack->top->next->next->path, "fake path");
  EXPECT_STREQ(stack->top->next->next->name, "s1");

  TreeNode* pop1 = Pop(stack);
  EXPECT_STREQ(pop1->path, "fakity fake");
  EXPECT_STREQ(pop1->name, "s3");
  EXPECT_STREQ(stack->top->path, "ugh");
  EXPECT_STREQ(stack->top->name, "s2");
  EXPECT_STREQ(stack->top->next->path, "fake path");
  EXPECT_STREQ(stack->top->next->name, "s1");

  TreeNode* pop2 = Pop(stack);
  EXPECT_STREQ(pop2->path, "ugh");
  EXPECT_STREQ(pop2->name, "s2");
  EXPECT_STREQ(stack->top->path, "fake path");
  EXPECT_STREQ(stack->top->name, "s1");

  TreeNode* pop3 = Pop(stack);
  EXPECT_STREQ(pop3->path, "fake path");
  EXPECT_STREQ(pop3->name, "s1");
  EXPECT_TRUE(stack->top == NULL);

  TreeNode* pop4 = Pop(stack);
  EXPECT_TRUE(pop4 == NULL);
  EXPECT_TRUE(stack->top == NULL);

  DestroyTreeNode(s1);
  DestroyTreeNode(s2);
  DestroyTreeNode(s3);
  DestroyStack(stack);
}

/* Not testing files.c extensively to avoid possible conflicts with
 * different local computer contents.
 */

TEST(Files, BuildTreeQueue) {
  Queue* queue = CreateQueue();
  char* dir = strdup(".");
  char* name = strdup(dir);
  TreeNode* root = CreateTreeNode(dir, name);
  BuildTree(queue, root);
  // Ensures queue is empty
  ASSERT_TRUE(queue->front == NULL);
  ASSERT_TRUE(queue->back == NULL);
  // Ensures root node has children
  if (root->children) {
    SUCCEED();
  }

  DestroyQueue(queue);
  DestroyTreeNode(root);
}

TEST(Files, PrintTreeStack) {
  Queue* queue = CreateQueue();
  Stack* stack = CreateStack();
  char* dir = strdup(".");
  char* name = strdup(dir);
  TreeNode* root = CreateTreeNode(dir, name);
  BuildTree(queue, root);
  // Ensures queue is empty
  ASSERT_TRUE(queue->front == NULL);
  ASSERT_TRUE(queue->back == NULL);
  // Ensures root node has children
  if (root->children) {
    SUCCEED();
  }

  PrintTree(stack, root);
  // Ensures stack is empty
  ASSERT_TRUE(stack->top == NULL);

  DestroyStack(stack);
  DestroyQueue(queue);
  DestroyTreeNode(root);
}

int main(int argc, char **argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
