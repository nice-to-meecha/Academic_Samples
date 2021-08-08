package problem01.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import problem01.model.InitializeTodoList;
import problem01.model.ReadCSV;
import problem01.model.WriteCSV;
import problem01.view.InvalidArgumentException;
import problem01.view.InvalidNullArgumentException;

public class Main {

  public static void main(String[] args) {
    Logger log = Logger.getLogger(Main.class.getName());

    Parser parser = new Parser(args);
    try {
      String csvPath = parser.getPathToFile();

      try (BufferedReader read = new BufferedReader(new FileReader(csvPath))) {

        ReadCSV readCSV = new ReadCSV();
        readCSV.splitCSV(read);
        read.close();

        InitializeTodoList initializer = new InitializeTodoList(readCSV.getCSV(),
            readCSV.getHeaderTitleOrder());
        List<Todo> todoList = initializer.parseCSVEntries();

        parser.setTaskManager(todoList);
        TaskManager taskManager = parser.parseCommandLine();
        taskManager.validateArgsAllTasks();
        taskManager.performAllTasks();

        BufferedWriter write = new BufferedWriter(new FileWriter(csvPath));
        WriteCSV writeCSV = new WriteCSV(todoList, readCSV.getHeaderTitleOrder());
        writeCSV.writeContentsToCSV(write);
        write.close();

      } catch (IOException ioException) {
        log.log(Level.INFO, ioException.getMessage(), IOException.class);
      } catch (InvalidNullArgumentException nullArgumentException) {
        log.log(Level.INFO, nullArgumentException.getMessage(), InvalidNullArgumentException.class);
      } catch (NoSuchMethodException | InvocationTargetException |
          IllegalAccessException methodException) {
        log.log(Level.INFO, methodException.getMessage(), methodException.getClass());
      } catch (InvalidArgumentException invalid) {
        log.log(Level.INFO, invalid.getMessage(), InvalidArgumentException.class);
      }
    } catch (InvalidArgumentException invalidArgumentException) {
      log.log(Level.INFO, invalidArgumentException.getMessage(), InvalidArgumentException.class);
    }

  }
}
