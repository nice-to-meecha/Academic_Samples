/**
 *  CS5008: Spr 2021
 *  Northeastern University, Seattle
 *  Assignment 7: Implementing a 'Tree'
 *
 *  Author: Adrienne Slaughter (date unknown)
 *  Modified by:
 *   Stamesha Bello (03/20/2021)
 */

#include <stdio.h>
#include <dirent.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <string.h>
#include <locale.h>

#include "files.h"


void BuildTree(Queue* queue, TreeNode* root) {
  Enqueue(queue, root);
  TreeNode* node = Dequeue(queue);
  while (node != NULL) {
    AddFiles(queue, node, node->path);
    node = Dequeue(queue);
  }
}


void AddFiles(Queue* queue, TreeNode* root, char* dir) {
  struct stat s;
  struct dirent **namelist;
  int num_files, no_files = 0;
  num_files = scandir(dir, &namelist, 0, alphasort);
  if (num_files < no_files) {
    perror("scandir");
    printf("dir: %s\n", dir);
    return;
  } else {
    int i = 0, first_element = 0, extra_space = 3, confirmed_directory = 0;
    while (i < num_files) {
      // Ignore dotfiles, such as .emacs
      if (namelist[i]->d_name[first_element] == '.') {
        free(namelist[i]);
        i++;
        continue;
      }
      char *directory;
      int length = strlen(dir) + strlen(namelist[i]->d_name);
      // Included size_variable to clear clint sizeof error
      char size_variable = ' ';
      directory = (char*) malloc(((length + extra_space)
                                  *sizeof(size_variable)));
      if (directory == NULL) {
        printf("Couldn't malloc for filecrawler.directory\n");
        return;
      }
      // Copy the path and file name into the "directory" string
      snprintf(directory, length + extra_space,
               "%s/%s", dir, namelist[i]->d_name);
      char* name = strdup(namelist[i]->d_name);

      // Use stat() to get info about the given directory (or file)
      if (confirmed_directory == stat(directory, &s)) {
        AddChild(root, directory, name);
        TreeNode* new_child = root->children;
        // Tells us if the given directory/file is a directory or not
        if (S_ISDIR(s.st_mode)) {
          Enqueue(queue, new_child);
        }
      } else {
        printf("no stat; %s\n", directory);
      }
      free(namelist[i]);
      i++;
    }
  }
  free(namelist);
}


void PrintTree(Stack* stack, TreeNode* root) {
  Push(stack, root);
  TreeNode* node = Pop(stack);
  int root_threshold = 0;
  while (node != NULL) {
    printf("\n");
    if (node->level > root_threshold) {
      FileSpacing(node);
    }
    TreeNode* temp_child = node->children;

    if (temp_child) {
      printf("\e[38;5;214m%s\e[0m", node->name);
    } else {
      printf("%s", node->name);
    }

    while (temp_child != NULL) {
      Push(stack, temp_child);
      temp_child = temp_child->siblings;
    }
    node = Pop(stack);
  }
  printf("\n\n");
}


void FileSpacing(TreeNode* node) {
  int space, loop_start = 0, proper_spacing = 1;
  for (space = loop_start; space < node->level - proper_spacing; space++) {
    printf("%lc", 0x2503);
    printf("%s", "  ");
  }
  printf("%lc%lc", 0x2523, 0x2501);
}
