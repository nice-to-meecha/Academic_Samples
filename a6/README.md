## Assignment 6: Finding Anagrams with Hashtables

This directory contains 3 source files (main.c, my_hashtable.c, and my_linkedlist.c), 2 header files (my_hashtable.h
 and my_linkedlist.h), a testing file (test_hashtable.cc), a text file (words.txt), and a Makefile. These are utilized
 to sort all 423,334 words within the text file, grouping existing anagrams together; they are then printed to the console.


The source code can either be run using the "make" or "make run" commands (or compiled using the "make compile" command).
 If it is desired to manually compile the code, the following command would accomplish that goal:
 "gcc -g main.c my_hashtable.c my_linkedlist.c -o main". To attain proper results when running the executable manually,
 one must include words.txt as a second argument, as shown: "./main words.txt".


To obtain the anagrams belonging to a certain combination of letters, utilize the grep command in the format displayed:
 "./main words.txt | grep 'word/key'". This may provide additional anagrams, given that the sheer number of words within
 the text file allows for many words to share prefixes, suffixes, root words, etc.


In order to run the tests for this program, one must use the "make test_suite" command followed by "./test_suite".


#### my_hashtable.c

This file provides all functions (and any accessory functions) required to produce a hashtable and fill it, grouping anagrams together.
This functionality can be described with the following:


Create() produces a hash table, utilizing its size as the sole parameter. It provides the memory location of the new hash table as output.

    Create(100):
    
    new_hash->lists == array of node* pointers (all indices start at NULL)
    new_hash->size == 100
    new_hash->num_of_elements == 0
    
    returns new_hash, which has the capacity to hold 100 anagram lists (linked lists)


Destroy() frees a hash table and all associated data members previously m'alloced from the heap. It accepts the hash table to be destroyed
 as the only parameter. It does not return anything.

    Destroy(hash):
    
    ***removes the hash table, array for linked lists, stored nodes, node values, and node keys from the heap***


FNVHash64() determines the hash value for the provided string. It requires 2 parameters -- the string for which the hash value will be
 determined as well as the length of the aformentioned string. It provides the calculated hash value as output.

    FNVHash64("eipppsu", 7):
    
    returns 547023283193 (this is not the actual numerical result; it's a random value)


AvoidDifferentKeyCollisions() adds nodes in the middle or at the end of an established linked list, for the sake of providing manageable
 order for nodes with different keys that end up with the same hash value (as produced by FNVHash64). It serves this purpose for accurate
 printing of anagrams, following hash table completion. It takes 3 parameters: the hash table in which the node will be placed, the node
 to be added to the aforementioned linked list, and the index value where the linked list can be located. It does not return anything.

    **Assume a linked list at index 1108 has the following order: 'smiled', 'slimed', 'misled'.
    **Also, assume the hash value of 'meat' modded by the size of the hash table also yields 1108.

    AvoidDifferentKeyCollisions(hash, meat_node, 1108):

    'smiled' -> 'slimed'-> 'misled' -> 'unabashed' -> 'meat' -> NULL
    
    
    AvoidDifferentKeyCollisions(hash, team_node, 1108):

    'smiled' -> 'slimed' -> 'misled' -> 'unabashed' -> 'team' -> 'meat' -> NULL


Insert() creates a new node, based on the key-value pair provided and places it within the appropriate linked list of the provided hash table.
 It takes the hash table -- within which the key-value pair will be inserted -- as well as the key and value strings as parameters. It returns 0
 if execution is successful or 1 if there is early termination.

    **Assume a linked list at index 214 has the following order: 'meat', 'mate'.
    
    Insert(hash, 'aemt', 'team'):

    'team' -> 'meat' -> 'mate' -> NULL
    return 0


Get() returns the head node of the linked list stored within the provided index of the hash table. It takes the hash table and the key
 belonging to a node stored within the linked list of interest as parameters. It provides the memory location of the head node as output.

    Get(hash, 'aetm') :
    
    **during execution, assume the 'aetm' key produces a hash value of 5**
    
    hash->lists[5] = 'team', 'meat', 'mate'
    ***returns memory location of team_node***


Print() prints all anagrams of the provided hash table, each on a separate line. It takes the hash table of interest as its lone parameter.
 It does not officially provide output.

    Print(hash) :
    
    Algorithm 1: team, meat
    Algorithm 2: god, dog
    ...
    Algorithm 379999: freight, fighter, refight
    Algorithm 380000: smiled, slimed, missle


Quicksort() sorts a character array according to the first element (pivot), positioning letters with (ASCII) values lower than the pivot to its
right and any higher values to its left. It continues sorting small subproblems of the original character array until it is completely sorted.
It takes a character array as well as its lowest and highest indices as parameters. It does not return anything.

    Quicksort(['l', 'a', 'b', 'e', 'l'], 0, 4):

    ['a', 'b', 'e', 'l', 'l']


Partition() sorts a character array according to the ASCII value of the first element, positioning ASCII values of subsequent elements lower than
it to its right and any higher values to its left. It takes a character array, the lowest index of the array, and its highest index as parameters.
It provides the index of the left wall of the partition as output.

    Partition(['f', 'r', 'e', 'i', 'g', 'h', 't'], 0, 6):

    ['e', 'f', 'r', 'i', 'g', 'h', 't']
    returns 1


Swap() takes a character array and 2 indices as parameters, switching the values at the indices. There is no official output for Swap().
    
    Swap(['l', 'a', 'b', 'e', 'l'], 1, 4):
    
    ['l', 'l', 'b', 'e', 'a']


LoadFactor() determines the load factor of the provided hash table. It takes the hash table of interest as its parameter. It provides the
 hash table's current load factor as output.

    LoadFactor(hash) :
    
    **Assume hash has the capacity to hold 100 linked lists and currently retains 40 elements**
    
    return 0.4


ResizeHashTable() increases the size of the provided hash table, copying all contents from the original hash table and then destroying
 the original. It takes the original hash table as its parameter. It provides the memory location of the new hash table as output.

    ResizeHashTable(hash) :
    
    **Assume hash has the capacity to hold 100 linked lists**
    
    return new hash table with the capacity to hold 200 linked lists & all elements in the original hash table



#### my_linkedlist.c

This file provides all functionality for the linked lists utilized to retain anagrams of the words.txt file.

CreateNode() takes a word (value) and its associated key (the sorted version of the word) as parameters, assigning them to their respective
 data members within a new node struct. For the sake of further manipulation, the new node is allocated to the heap. The memory location of
 the new node is returned, following successful execution of the function.

    CreateNode("aemt", "team"):

    new_node->key == "aemt"
    new_node->value == "team"
    new_node->next == NULL
    new_node->prev == NULL
    returns new_node


DestroyNode() takes a node pointer as its sole parameter. The contents of the provided node--its key and value--are freed from the heap.
 Lastly, the node, itself, is then freed from the heap. This function does not return a value.

    DestroyNode(node):
    
    ***removes node as well as its key and value from the heap***


DestroyLinkedList() requires a node pointer as a parameter. It is utilized by the Destroy() function within my_hashtable.c in order to
 free all nodes within a linked list (specified by providing the head node of the list) stored within a bucket of the hash table.
 It does not produce any output.

    DestroyLinkedList(head_node):
    
    ***removes all nodes, including their keys and values, of the provided linked list (starting with head_node) from the heap***

    
#### main.c

This file pulls the former two together, scanning the words from the words.txt file, sorting them to form keys, placing them within the hash table, and
 printing all existing anagrams. It only retains the main() function, which allows the executable to be run. The main function takes 2 parameters,
 the number of arguments provided in the command line and an array retaining the arguments, themselves. It produces a list containing all anagrams
 identified within the hash table, basing identical letter combinations on shared keys. It returns 0 following successful execution or 1 when an error
 triggers early termination.

    main(2, [./sorted, ['w', 'o', 'r', 'd', 's', '.', 't', 'x', 't']]):
    
    Algorithm 1: team, meat
    Algorithm 2: god, dog
    ...
    Algorithm 379999: freight, fighter, refight
    Algorithm 380000: smiled, slimed, missle


#### External Libraries

This directory does not utilize external libraries, however the .c files rely heavily on standard .h files, including: stdio.h, stdlib.h,
 stdint.h, and string.h.


#### Assumptions

I assumed that the provided text file would be words.txt. I did not provide protections against using the wrong file.


#### References
- Testing with Google Framework:
  - https://stackoverflow.com/questions/47583352/how-to-catch-segmentation-fault-with-google-test
  - https://google.github.io/googletest/primer.html
- Reading a file in C: http://www.cs.yale.edu/homes/aspnes/classes/223/notes.html#File_IO
- Comparing strings: https://www.programiz.com/c-programming/library-function/string.h/strcmp
- Avoiding integer division: https://stackoverflow.com/questions/2345902/division-result-is-always-zero
- Using scanf: https://www.geeksforgeeks.org/scanf-and-fscanf-in-c-simple-yet-poweful/
- Using strdup:
  - https://www.geeksforgeeks.org/strdup-strdndup-functions-c/
  - https://stackoverflow.com/questions/38497209/fscanf-usage-in-c-values-not-saving-properly
- Using continue: https://www.tutorialspoint.com/cprogramming/c_continue_statement.htm#:~:text=The%20continue%20statement%20in%20C,of%20the%20loop%20to%20execute.
- Using exponents: https://stackoverflow.com/questions/18733675/to-the-power-of-in-c/18733766
- Must use -lm flag for pow() function: https://www.includehelp.com/c-programming-questions/error-undefined-reference-to-pow-in-linux.aspx


#### What went well?

Setting up and utilizing the linked list(s) went fairly well, since I've done that a number of times before.


#### What was hard or tricky?

Handling memory usage, dealing with collisions of words with different keys, and resizing the hash table (within the Insert function) without returning
 the memory location of a local variable proved difficult.


#### How much time did you spend?

I've spent at least 23 hours on this...


### Summary

The source files of this directory ultimately scan all words from the words.txt file, sort them to form keys, place them within a hash table, and
 printing all existing anagrams identified.
