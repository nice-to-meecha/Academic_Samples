#  CS5008: Spr 2021
#  Northeastern University, Seattle
#  Assignment 7: Implementing a 'Tree'
#  Author: Stamesha Bello

path ?= .

all: run

run: compile
	./files $(path)

compile: main.c files.c files.h tree.c tree.h queue.c queue.h stack.c stack.h
	gcc -g main.c files.c tree.c queue.c stack.c -o files

clean: files
	rm ./files


### Testing ###

GTEST_DIR_A = ${HOME}/src/googletest-release-1.8.0/googletest
GOOGLE_TEST_LIB = ${HOME}/lib/gtest/libgtest.a
GOOGLE_TEST_INCLUDE = ${HOME}/src/googletest-release-1.8.0/googletest/include/

GTEST_DOWNLOAD = ${HOME}/src/googletest-release-1.8.0/
USER_DIR = .

CPPFLAGS += -isystem $(GTEST_DIR_A)/include

CXXFLAGS += -g -Wall -Wextra -pthread

GTEST_HEADERS = $(GTEST_DIR_A)/include/gtest/*.h \
                $(GTEST_DIR_A)/include/gtest/internal/*.h

GTEST_SRCS_ = $(GTEST_DIR_A)/src/*.cc $(GTEST_DIR_A)/src/*.h $(GTEST_HEADERS)


release-1.8.0.tar.gz :
	@echo -e "\033[1;93m ----------------"
	@echo NEED GTEST ZIP
	@echo -e "----------------\033[0m"

${GTEST_DIR_A}/src/gtest-all.cc: release-1.8.0.tar.gz
	@echo -e "\033[1;93m==========================="
	@echo Extracting gtest source
	@echo -e "===========================\033[0m"
	mkdir -p ${HOME}/src
	cp release-1.8.0.tar.gz ${HOME}/src/
	tar -xzmf ${HOME}/src/release-1.8.0.tar.gz -C ${HOME}/src/

files.o: files.c files.h tree.o queue.o stack.o
	gcc -c -Wall -g files.c -o files.o

stack.o: stack.c stack.h tree.o
	gcc -c -Wall -g stack.c -o stack.o

queue.o: queue.c queue.h tree.o
	gcc -c -Wall -g queue.c -o queue.o

tree.o: tree.c tree.h
	gcc -c -Wall -g tree.c -o tree.o


## Compiles the hashtable tests.
test_files.o : test_files.cc
	g++ -c -Wall -I $(GOOGLE_TEST_INCLUDE) test_files.cc \
		-o test_files.o

## Puts it all together.
test_suite: $(GOOGLE_TEST_LIB) test_files.o files.o stack.o queue.o tree.o
	g++ -o test_suite test_files.o files.o stack.o queue.o tree.o \
		 -L${HOME}/lib/gtest -lgtest -lpthread
	@echo -e "\033[38;5;198m===================================="
	@echo Run tests by running ./test_suite
	@echo -e "====================================\033[0m"
