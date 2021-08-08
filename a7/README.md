## Assignment 7: Implementing a 'Tree'

This directory contains 5 source files (main.c, files.c, tree.c, queue.c and stack.c), 4 header files (files.h, tree.h, queue.h
 and stack.c), a testing file (test_files.cc), and a Makefile. These are utilized to build a tree, comprised
 of all directories and files found within a provided path, and print them to the console neatly.


The source code can be run using the "make" or "make run" commands (or, it can be compiled using "make compile"). These commands
 would simply print the files within the current directory. To print files within different paths, however, use the following
 command: "make path=<path_name>" or "make run path=<path_name>" (such as "make path=../" or "make run path=../testing123/").
 If it is desired to manually compile the code, the following would accomplish that goal: "gcc -g main.c files.c tree.c queue.c stack.c -o files".
 When running the executable manually, the user can add one optional argument, which would be the path of which the file tree will
 be printed. It can be run as follows: "./files" or "./files ../testing123/".


In order to run the tests for this program, one must use the "make test_suite" command followed by "./test_suite".


#### tree.c

This file provides all functions required to produce a tree (consisting of smaller sub-trees), filling it with children of provided parent nodes.
This functionality can be described with the following:


CreateTreeNode() produces a TreeNode, utilizing the name of a file and its path as parameters. All other members of the TreeNode struct are set
 to NULL or 0 to avoid dereferencing non-existent members. This function provides the memory location of the new TreeNode as output.

    CreateTreeNode("../testing123/HelloWorld.c", "HelloWorld.c"):
    
    new_node->path == "../testing123/HelloWorld.c"
    new_node->name == "HelloWorld.c"
    new_node->children == NULL
    new_node->siblings == NULL
    new_node->next == NULL
    new_node->level == 0

    return new_node


AddChild() adds a new TreeNode as a child to the parent node provided. It takes the parent node as well as the name of the new child node and its
 path as parameters. As there is no technical limit to the number of children a parent node can have, each child node is added to a list designated
 for the parent node's children. The function returns 0 upon successful addition or 1 in the case of early termination.
 
    **Assume root already has a list of children (represented by their name members) as follows: near.out -> fake_folder1**
    AddChild(root, "../testing123/nearest_neighbors.c", "nearest_neighbors.c"):
    
    ***New child added to root list of children: nearest_neighbors.c -> near.out -> fake_folder1***
    return 0


DestroyTreeNode() frees the root of the file tree as well as all children, siblings, and associated data members previously m'alloced.
 It accepts the root node of the tree to be destroyed as the only parameter. It does not return anything.

    DestroyTreeNode(root):
    
    ***removes the root node, its path and file name, children, and siblings from the heap***


#### queue.c

This file provides all functionality for the queue utilized to build the file tree, utilizing Breadth-First Search.

CreateQueue() does not require any parameters. It m'allocs a new Queue struct to the heap, assigning its front and back members to NULL.
 The memory location of the new queue is returned, following successful execution of the function.

    CreateQueue():

    new_queue->front == NULL
    new_queue->back == NULL
    return new_queue


Enqueue() adds a node to the back end of a queue data structure. It requires a node and the queue to which it will be added as parameters.
 Enqueue() returns 0 if the addition is successful and 1 if not.
 
    **Assume queue appears as follows: (front) n1 -> n2 -> n3 -> NULL (back)**
    Enqueue(queue, n4):
    
    ***Added n4 to end/back of queue: (front) n1 -> n2 -> n3 -> n4 -> NULL (back)***
    return 0
    

Dequeue() requires a queue as its sole parameter. It removes a node from the front of the queue, effectively shortening the queue by
 1 node. The removed node is then returned by the function.

    **Assume queue appears as follows: (front) n1 -> n2 -> n3 -> n4 -> NULL (back)**
    Dequeue(queue):
    
    ***Removed n1 from front of queue: (front) n2 -> n3 -> n4 -> NULL (back)***
    return n1


DestroyQueue() frees the provided queue from the heap. It takes the pointer to the queue to be removed as its sole parameter.
 Only the Queue struct, itself, is freed from the heap. All nodes previously stored within it will be freed by DestroyTreeNode() described
 above. This function does not return a value.

    DestroyQueue(queue):
    
    ***removed queue from the heap***


#### stack.c

This file provides all functionality for the stack data structure utilized to print the contents of a tree file.

CreateStack() does not require any parameters. It m'allocs a new Stack struct to the heap, assigning its top member to NULL. The memory
 location of the new queue is returned, following successful execution of the function.

    CreateStack():

    new_stack->top == NULL
    return new_stack


Push() adds a node to the top of a stack data structure. It requires a node and the stack to which it will be added as parameters.
 Push() returns 0 if the addition is successful and 1 if not.
 
    **Assume stack appears as follows: (top) n3 -> n2 -> n1 -> NULL**
    Push(stack, n4):
    
    ***Added n4 to top of stack: (top) n4 -> n3 -> n2 -> n1 -> NULL***
    return 0
    

Pop() requires a stack as its sole parameter. It removes a node from the top of the stack, effectively shortening the stack by
 1 node. The removed node is then returned by the function.

    **Assume stack appears as follows: (top) n4 -> n3 -> n2 -> n1**
    Pop(stack):
    
    ***Removed n4 from top of stack: (top) n3 -> n2 -> n1***
    return n4


DestroyStack() frees the provided stack from the heap. It takes the pointer to the stack to be removed as its sole parameter.
 Only the Stack struct, itself, is freed from the heap. All nodes previously stored within it will be freed by the DestroyTreeNode()
 function described above. This function does not return a value.

    DestroyStack(stack):
    
    ***removed stack from the heap***


#### files.c

This file provides the functionality to build a tree from provided directories and print it in an easy-to-read format.

BuildTree() generates a tree of files of the provided directory, relying on the AddFiles() function (described below) to further populate the tree.
 Given that it builds via Bread-First Search, the function requires a queue as well as the root node of the tree-to-be as parameters. This function
 does not return a value.
 
    BuildTree(queue, root):
    
    ***Generates tree, starting with the provided root node***


AddFiles() scans the provided directory, adding all files/folders within it as children to the corresponding parent node. It also aids in building the
 file tree (produced by BuildTree() above) by adding any child directories to the queue in order to further build the tree via BFS. It requires a
 queue (to which child directories will be added), the parent node of the child directories, and the path of the parent node as parameters. It does
 not produce any official output. (The workings of this function were largely provided by Dr. Adrienne Slaughter.)
 
    **Assume the children list of the parent (fake_folder1) is empty; the queue is also empty**
    AddFiles(queue, fake_folder1, "../testing123/fake_folder1"):
    
    ***Adds all children to fake_folder2's children list: fake_file1c -> fake_file1b -> fake_file1a -> NULL***
    ***Also adds child directory to queue: (front) fake_folder2 -> NULL (back)***


PrintTree() prints the contents of the file tree to the console, utilizing Depth-First Search in order to clearly establish the parent-child and sibling
 relationships within the tree. It requires a stack (with which DFS can occur) and the root node of the file tree as parameters. It does not produce
 any output.
 
    PrintTree(stack, root):
    
    ../testing123/
    |-HelloWorld.c
    |-HelloWorld.c~
    |-HelloWorld.out
    |-fake_folder1
    |  |-fake_file1a
    |  |-fake_file1b
    |  |-fake_file1c
    |  |-fake_folder2
    |  |  |-fake_file2a
    |  |  |-fake_file2b
    |  |  |-fake_file2c
    |-near.out
    |-nearest_neighbors.c
    |-nearest_neighbors.c~
    |-struct.c
    |-struct.c~
    |-struct.out


FileSpacing() is a helper function for PrintTree(), providing for the added decoration and spacing of the tree shown above to ensure clear depiction
 of the parent-child and sibling relationships of the file tree. It only takes a node--whose file name will be soon be printed in PrintTree--as its
 sole parameter. It does not return anything.
 
    **Assumes n1's level is 1. (The root node has a level of 0.)**
    FileSpacing(n1):
    
    |-
    
    **Assumes n4's level is 4. (The root node has a level of 0.)**
    FileSpacing(n4):
    
    |  |  |-


#### main.c

This file pulls the former four together, building a tree from the directories and/or files of the current path and printing them in pre-order.

main() allows the executable to be run. The main function takes 2 parameters, the number of arguments provided in the command line and an array
 retaining the arguments, themselves. It produces the tree of files, utilizing the BuildTree() function and prints the tree with the PrintTree()
 function. It returns 0 following successful execution or 1 when an error triggers early termination.

    main(2, [./files, ['.', '.', '/', 't', 'e', 's', 't', 'i', 'n', 'g', '1', '2', '3', '/']]):
    
    ../testing123/
    |-HelloWorld.c
    |-HelloWorld.c~
    |-HelloWorld.out
    |-fake_folder1
    |  |-fake_file1a
    |  |-fake_file1b
    |  |-fake_file1c
    |  |-fake_folder2
    |  |  |-fake_file2a
    |  |  |-fake_file2b
    |  |  |-fake_file2c
    |-near.out
    |-nearest_neighbors.c
    |-nearest_neighbors.c~
    |-struct.c
    |-struct.c~
    |-struct.out
    

#### External Libraries

This directory does not utilize external libraries, however the .c files rely heavily on standard header files, including: stdio.h, dirent.h,
 stdlib.h, sys/stat.h, string.h., and locale.h.


#### Assumptions

I assumed that the provided path to be printed would be located on the user's local computer. I did not write my own countermeasures, should
 a file (not directory) or non-existent directory be used as the path. However, there were measures provided by Dr. Slaughter to avoid handle
 crashing of the program, when facing the latter issue.


#### References
- perror() functionality: https://www.tutorialspoint.com/c_standard_library/c_function_perror.htm
- Using Makefile variables: https://makefiletutorial.com/#command-line-arguments-and-override
- Google testing framework: https://google.github.io/googletest/primer.html
- Box characters: https://jrgraphix.net/r/Unicode/2500-257F
- Changing text colors: https://misc.flogisoft.com/bash/tip_colors_and_formatting#foreground_text


#### What went well?

Setting up the basis of the tree as well as the majority of the components of the queue and stack data structures worked fairly well.
 I did not run into many obstacles there.


#### What was hard or tricky?

Trying to locate the cause of an infinite printing loop was quite the challenge, as I spent 6.5 hours alone trying to uncover it. To my
 avail, my incorporation of a "next" member within the TreeNode struct became my undoing. When dequeing nodes from an existing queue,
 I did not originally set the "next" member to NULL. In absence of that, the effects of the previously-used queue haunted the stack I'd
 utilized to print the file system, creating a never-ending loop of files and folders. Luckily, that issue is no more.

#### How much time did you spend?

I've spent at about 20 hours on this, primarily thanks to the problem detailed above.


### Summary

The source files of this directory will scan all files and/or folders of a provided path, using the information obtained to build a tree
 of files and subsequently print them in an easy-to-read manner.
