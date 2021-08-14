/**
 *  Author: Adrienne Slaughter
 *  Modified by:
 *    Stamesha Bello (03/13/2021)
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
    #include "my_hashtable.h"
    #include "my_linkedlist.h"
}
const char* first = "first";
const char* second="second";
const char* third = "third";
const char* fourth = "fourth";

#define MAX_VALUE_LEN 75



TEST(MyLinkedList, CreateNode) {
  char* key = strdup("deilms");
  char* value = strdup("misled");
  node* new_node = CreateNode(key, value);
  ASSERT_FALSE(new_node == NULL);
  ASSERT_STREQ(new_node->key, "deilms");
  ASSERT_STREQ(new_node->value, "misled");

  // Since cannot use ASSERT_EQ to compare new_node->next/prev
  // with NULL, use conditional to ensure both are NULL)
  if (new_node->next || new_node->prev) {
    ADD_FAILURE_AT("my_linkedlist.c", 29);
    ADD_FAILURE_AT("my_linkedlist.c", 30);
  } else {
    SUCCEED();
  }

  DestroyNode(new_node);
}

TEST(MyLinkedList, CreateNodeNullKeyExit) {
  char* value = strdup("misled");
  node* new_node;
  ASSERT_EXIT((new_node = CreateNode(NULL, value), exit(1)),
              ::testing::ExitedWithCode(1), ".*");
  }

TEST(MyLinkedList, CreateNodeNullValueExit) {
  char* key = strdup("deilms");
  node* new_node;
  ASSERT_EXIT((new_node = CreateNode(key, NULL), exit(1)),
              ::testing::ExitedWithCode(1), ".*");
}

TEST(MyLinkedList, DestroyNullNode) {
  node* new_node = NULL;
  // DestroyNode() should not do anything, avoiding errors
  DestroyNode(new_node);
  if (new_node) {
    ADD_FAILURE_AT("my_linkedlist.c", 36);
  } else {
    SUCCEED();
  }
}

TEST(MyLinkedList, DestroyNullLinkedList) {
  node* new_node = NULL;
  // DestroyLinkedList() should not do anything, avoiding errors
  DestroyLinkedList(new_node);
  if (new_node) {
    ADD_FAILURE_AT("my_linkedlist.c", 47);
  } else {
    SUCCEED();
  }
}


TEST(MyHashtable, Create) {
  hash_table* ht = Create(5);
  EXPECT_FALSE(ht == NULL);
  EXPECT_FALSE(ht->lists == NULL);
  EXPECT_EQ(ht->num_of_lists, 5);
  EXPECT_EQ(ht->num_of_stored_elements, 0);
  EXPECT_TRUE(ht->lists[0] == NULL);
  EXPECT_TRUE(ht->lists[1] == NULL);
  EXPECT_TRUE(ht->lists[2] == NULL);
  EXPECT_TRUE(ht->lists[3] == NULL);
  EXPECT_TRUE(ht->lists[4] == NULL);
  Destroy(ht);
}

TEST(MyHashtable, AddOneElemDestroy) {
    hash_table* ht = Create(5);

    EXPECT_EQ(ht->num_of_stored_elements, 0);

    char* key = strdup("");
    char* value = strdup("");
    Insert(ht, key, value);

    EXPECT_EQ(ht->num_of_stored_elements, 1);

    Destroy(ht);
}

TEST(MyHashtable, DestroyNullHashTable) {
  hash_table* ht = NULL;
  // Destroy() shouldn't do anything, avoiding errors
  Destroy(ht);
  if (ht) {
    ADD_FAILURE();
  } else {
    SUCCEED();
  }
}

TEST(MyHashtable, FNVHash64GetSameHashValueWithSameKey) {
  char value1[] = {'m', 'i', 's', 'l', 'e', 'd'};
  char value2[] = {'s', 'm', 'i', 'l', 'e', 'd'};
  Quicksort(value1, 0, strlen(value1) - 1);
  Quicksort(value2, 0, strlen(value2) - 1);
  uint64_t result1 = FNVHash64((unsigned char*) value1, strlen(value1));
  uint64_t result2 = FNVHash64((unsigned char*) value2, strlen(value2));
  ASSERT_TRUE(result1 == result2);
}

TEST(MyHashtable, FNVHash64GetDifferentHashValueWithDifferentKey) {
  char value1[] = {'m', 'i', 's', 'l', 'e', 'd'};
  char value2[] = {'t', 'e', 'a', 'm'};
  Quicksort(value1, 0, strlen(value1) - 1);
  Quicksort(value2, 0, strlen(value2) - 1);
  uint64_t result1 = FNVHash64((unsigned char*) value1, strlen(value1));
  uint64_t result2 = FNVHash64((unsigned char*) value2, strlen(value2));
  ASSERT_FALSE(result1 == result2);
}

TEST(MyHashtable, AvoidDifferentKeyCollisions) {
  hash_table* ht = Create(5);
  //  sad & team;
    char* key = strdup("aemt");
    char* value = strdup("team");

    Insert(ht, key, value);

    ASSERT_EQ(ht->num_of_stored_elements, 1);

    node* result = Get(ht, key);
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    ASSERT_TRUE(result->next == NULL);

    char* key2 = strdup("aemt");
    char* value2 = strdup("meat");

    Insert(ht, key2, value2);

    ASSERT_EQ(ht->num_of_stored_elements, 2);

    result = Get(ht, key2);
    ASSERT_STREQ(result->key, key2);
    ASSERT_STREQ(result->value, value2);
    result = result->next;
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    ASSERT_TRUE(result->next == NULL);

    char* key3 = strdup("apple");
    char* value3 = strdup("aelpp");
    int apple_hash_index = FNVHash64((unsigned char*) key3, strlen(key3)) % 5;
    int team_hash_index = FNVHash64((unsigned char*) key, strlen(key)) % 5;
    ASSERT_TRUE(apple_hash_index == team_hash_index);
    node* apple_node = CreateNode(key3, value3);
    AvoidDifferentKeyCollisions(ht, apple_node, apple_hash_index);

    result = Get(ht, key3);
    ASSERT_STREQ(result->key, key2);
    ASSERT_STREQ(result->value, value2);
    result = result->next;
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    result = result->next;
    ASSERT_STREQ(result->key, key3);
    ASSERT_STREQ(result->value, value3);
    ASSERT_TRUE(result->next == NULL);

    Destroy(ht);
}

TEST(MyHashtable, InsertNullHashTable) {
  char key[] = {'d', 'e', 'i', 'l', 'm', 's'};
  char value[] = {'m', 'i', 's', 'l', 'e', 'd'};
  int result = Insert(NULL, key, value);
  ASSERT_EQ(result, 1);
}

TEST(MyHashtable, InsertNullKey) {
  hash_table* ht = Create(5);
  char* value = strdup("misled");
  int result = Insert(ht, NULL, value);
  ASSERT_EQ(result, 1);
  Destroy(ht);
}

TEST(MyHashtable, InsertNullValue) {
  hash_table* ht = Create(5);
  char* key = strdup("deilms");
  int result = Insert(ht, key, NULL);
  ASSERT_EQ(result, 1);
  Destroy(ht);
}

TEST(MyHashtable, InsertOneElement) {
  hash_table* ht = Create(5);
  char* key = strdup("aemt");
  char* value = strdup("meat");
  uint64_t hashvalue = FNVHash64((unsigned char*) key, strlen(key));
  int index = hashvalue % 5;
  ASSERT_TRUE(ht->lists[index] == NULL);

  Insert(ht, key, value);
  ASSERT_FALSE(ht->lists[index] == NULL);
  ASSERT_STREQ(ht->lists[index]->key, key);
  ASSERT_STREQ(ht->lists[index]->value, value);
  Destroy(ht);
}

TEST(MyHashtable, InsertTwoElemsWithSameKey) {
    hash_table* ht = Create(5);

    EXPECT_EQ(ht->num_of_stored_elements, 0);

    char* key = strdup("aemt");
    char* value = strdup("meat");

    int result = Insert(ht, key, value);

    EXPECT_EQ(result, 0);
    EXPECT_EQ(ht->num_of_stored_elements, 1);

    // Add different element with the same key (diff val)
    char* key2 = strdup("aemt");
    char* value2 = strdup("team");

    result = Insert(ht, key2, value2);
    EXPECT_EQ(result, 0);
    EXPECT_EQ(ht->num_of_stored_elements, 2);

    Destroy(ht);
}

TEST(MyHashtable, InsertMultipleItems) {
    hash_table* ht = Create(100);

    EXPECT_EQ(ht->num_of_stored_elements, 0);

    char* key = strdup("eilmss");
    char* value = strdup("missle");

    Insert(ht, key, value);

    ASSERT_EQ(ht->num_of_stored_elements, 1);

    node* result = Get(ht, key);
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    ASSERT_TRUE(result->next == NULL);

    char* key2 = strdup("eilmss");
    char* value2 = strdup("slimes");

    Insert(ht, key2, value2);

    ASSERT_EQ(ht->num_of_stored_elements, 2);

    result = Get(ht, key);
    ASSERT_STREQ(result->key, key2);
    ASSERT_STREQ(result->value, value2);
    ASSERT_FALSE(result->next == NULL);
    result = result->next;
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    ASSERT_TRUE(result->next == NULL);

    char* key3 = strdup("eilmss");
    char* value3 = strdup("smiles");

    Insert(ht, key3, value3);

    ASSERT_EQ(ht->num_of_stored_elements, 3);

    result = Get(ht, key);
    ASSERT_STREQ(result->key, key3);
    ASSERT_STREQ(result->value, value3);
    ASSERT_FALSE(result->next == NULL);
    result = result->next;
    ASSERT_STREQ(result->key, key2);
    ASSERT_STREQ(result->value, value2);
    ASSERT_FALSE(result->next == NULL);
    result = result->next;
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    ASSERT_TRUE(result->next == NULL);

    Destroy(ht);
}

TEST(MyHashtable, InsertMultipleItemsWithDifferentKeys) {
    hash_table* ht = Create(100);

    EXPECT_EQ(ht->num_of_stored_elements, 0);

    char* key = strdup("eilmss");
    char* value = strdup("missle");

    Insert(ht, key, value);

    ASSERT_EQ(ht->num_of_stored_elements, 1);

    node* result = Get(ht, key);
    ASSERT_STREQ(result->key, key);
    ASSERT_STREQ(result->value, value);
    ASSERT_TRUE(result->next == NULL);

    char* key2 = strdup("aemt");
    char* value2 = strdup("team");

    Insert(ht, key2, value2);

    ASSERT_EQ(ht->num_of_stored_elements, 2);

    result = Get(ht, key2);
    ASSERT_STREQ(result->key, key2);
    ASSERT_STREQ(result->value, value2);
    ASSERT_TRUE(result->next == NULL);

    char* key3 = strdup("depressed");
    char* value3 = strdup("ddeeeprss");

    Insert(ht, key3, value3);

    ASSERT_EQ(ht->num_of_stored_elements, 3);

    result = Get(ht, key3);
    ASSERT_STREQ(result->key, key3);
    ASSERT_STREQ(result->value, value3);
    ASSERT_TRUE(result->next == NULL);

    Destroy(ht);
}

TEST(MyHashtable, GetNullHashTable) {
  char key[] = "blah";
  node* null_list = Get(NULL, key);
  ASSERT_TRUE(null_list == NULL);
}

TEST(MyHashtable, GetNullKey) {
  hash_table* ht = Create(5);
  node* null_list = Get(ht, NULL);
  ASSERT_TRUE(null_list == NULL);
  Destroy(ht);
}

TEST(MyHashtable, Get) {
    hash_table* ht = Create(100);

    EXPECT_EQ(ht->num_of_stored_elements, 0);
    EXPECT_EQ(ht->num_of_lists, 100);

    char* key1 = strdup("eilmss");
    char* value1 = strdup("missle");
    Insert(ht, key1, value1);

    ASSERT_EQ(ht->num_of_stored_elements, 1);

    char* key2 = strdup("eilmss");
    char* value2 = strdup("slimes");
    Insert(ht, key2, value2);

    ASSERT_EQ(ht->num_of_stored_elements, 2);

    char* key3 = strdup("eilmss");
    char* value3 = strdup("smiles");
    Insert(ht, key3, value3);

    ASSERT_EQ(ht->num_of_stored_elements, 3);

    node* list = Get(ht, key1);
    EXPECT_STREQ(list->value, value3);
    list = list->next;
    EXPECT_STREQ(list->value, value2);
    list = list->next;
    EXPECT_STREQ(list->value, value1);

    // Test looking up something not in the table
    char random[] = {'r', 'a', 'n', 'd', 'o', 'm'};
    node* null_list = Get(ht, random);
    EXPECT_TRUE(null_list == NULL);

    Destroy(ht);
}

TEST(MyHashtable, Quicksort) {
  char word[] = {'m', 'i', 's', 'l', 'e', 'd'};
  Quicksort(word, 0, 5);
  ASSERT_STREQ(word, "deilms");
}

TEST(MyHashtable, Partition) {
  char word[] = {'m', 'i', 's', 'l', 'e', 'd'};
  int leftwall = Partition(word, 0, 5);
  ASSERT_EQ(leftwall, 4);
  ASSERT_STREQ(word, "dilems");
}

TEST(MyHashtable, Swap) {
  char word[] = {'m', 'i', 's', 'l', 'e', 'd'};
  Swap(word, 0, 5);
  ASSERT_STREQ(word, "dislem");
}

TEST(MyHashtable, ResizeHashTable) {
  hash_table* ht = Create(15);
  // Original size of the hash table -- prior to resizing
  ASSERT_EQ(ht->num_of_lists, 15);

  char word[20];
  double load_factor_max = 0.7;

  for (unsigned int i = 0; i < 60; i++) {
    // do the insert
    word[0] = (char) i;
    word[1] = (char) i;
    word[2] = (char) i;
    char* value = strdup(word);
    char* key = strdup(word);
    Quicksort(key, 0, strlen(key) - 1);
    Insert(ht, key, value);

    if (load_factor_max < LoadFactor(ht)) {
      ht = ResizeHashTable(ht);
    }
  }

  ASSERT_NE(ht->num_of_lists, 15);
  // New size of hash table after resizing 2 times
  ASSERT_EQ(ht->num_of_lists, 120);
  Destroy(ht);
}


int main(int argc, char **argv) {
    ::testing::InitGoogleTest(&argc, argv);
    return RUN_ALL_TESTS();
}
