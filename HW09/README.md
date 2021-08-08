## Assignment 9: Todo Application

#### Patterns Utilized
- Iterator Pattern
  - We made use of the Iterator Pattern (within the controller package) to
    filter through collections of Todos, based on their completeness or category.     

- Factory Pattern
  - The Factory Pattern was implemented (within the controller package) to
    allow for selection of the Iterator most fitting for the specified aspect
    by which Todos were to be filtered.
    
 - Command Pattern
   - The command pattern was used to manage performing each task. The three 
    possible tasks (add, complete, display) all fall under a Task interface. This 
     allows the TaskManager to act as an invoker object, creating and executing each
     task object from one place.

#### External Resources
- Utilizing a Logger - https://www.vogella.com/tutorials/Logging/article.html
- Iterator Pattern - https://www.geeksforgeeks.org/iterator-pattern/
- Factory Pattern - https://www.geeksforgeeks.org/factory-method-design-pattern-in-java/
- Using a DateTimeFormatter - https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
- Printing a Dynamic Table - https://itsallbinary.com/java-printing-to-console-in-table-format-simple-code-with-flexible-width-left-align-header-separator-line/
- Pseudo functional programming - https://stackoverflow.com/questions/160970/how-do-i-invoke-a-java-method-when-given-the-method-name-as-a-string
- Utilizing mark to reset BufferedReader - https://docs.oracle.com/javase/7/docs/api/java/io/BufferedReader.html#mark(int)