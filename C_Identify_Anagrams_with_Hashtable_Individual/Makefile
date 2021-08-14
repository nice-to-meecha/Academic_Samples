#  CS5008: Spr 2021
#  Northeastern University, Seattle
#  Assignment 6: Hashtables
#
#  Author: Stamesha Bello

all: run

run: compile
	./main words.txt

compile: main.c my_hashtable.c my_hashtable.h my_linkedlist.c my_linkedlist.h
	gcc -g main.c my_hashtable.c my_linkedlist.c -o main


# Points to the root of Google Test, relative to where this file is.
# Remember to tweak this if you move this file.
GTEST_DIR_A = ${HOME}/src/googletest-release-1.8.0/googletest
GOOGLE_TEST_LIB = ${HOME}/lib/gtest/libgtest.a
GOOGLE_TEST_INCLUDE = ${HOME}/src/googletest-release-1.8.0/googletest/include/

GTEST_DOWNLOAD = ${HOME}/src/googletest-release-1.8.0/

# Where to find user code.
USER_DIR = .

# Flags passed to the preprocessor.
# Set Google Test's header directory as a system directory, such that
# the compiler doesn't generate warnings in Google Test headers.
CPPFLAGS += -isystem $(GTEST_DIR_A)/include

# Flags passed to the C++ compiler.
CXXFLAGS += -g -Wall -Wextra -pthread

# All Google Test headers.  Usually you shouldn't change this
# definition.
GTEST_HEADERS = $(GTEST_DIR_A)/include/gtest/*.h \
                $(GTEST_DIR_A)/include/gtest/internal/*.h

# Builds gtest.a

# Usually you shouldn't tweak such internal variables, indicated by a
# trailing _.
GTEST_SRCS_ = $(GTEST_DIR_A)/src/*.cc $(GTEST_DIR_A)/src/*.h $(GTEST_HEADERS)

# For simplicity and to avoid depending on Google Test's
# implementation details, the dependencies specified below are
# conservative and not optimized.  This is fine as Google Test
# compiles fast and for ordinary users its source rarely changes.

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

### This builds the hashtable library. Change the filenames accordingly.
Hashtable.o: my_hashtable.c my_hashtable.h LinkedList.o
	gcc -c -Wall -g my_hashtable.c -o Hashtable.o

## Builds the LinkedList library, if you're using it.
LinkedList.o: my_linkedlist.c my_linkedlist.h
	gcc -c -Wall -g my_linkedlist.c -o LinkedList.o


## Compiles your hashtable tests.
test_hashtable.o : test_hashtable.cc
	g++ -c -Wall -I $(GOOGLE_TEST_INCLUDE) test_hashtable.cc \
		-o test_hashtable.o

## Puts it all together.
test_suite: $(GOOGLE_TEST_LIB) test_hashtable.o Hashtable.o LinkedList.o
	g++ -o test_suite test_hashtable.o Hashtable.o LinkedList.o \
		 -L${HOME}/lib/gtest -lgtest -lpthread
	@echo -e "\033[38;5;198m===================================="
	@echo Run tests by running ./test_suite
	@echo -e "====================================\033[0m"

clean:
	rm -rf *~ main
