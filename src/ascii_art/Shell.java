package ascii_art;

import image.Image;
import java.io.IOException;
import java.util.HashMap;
import shell_commands.*;
import exceptions.ShellExceptions;

/**
 * Interactive shell interface for running the ASCII art generator program.
 * @author Amit Joseph, Maya Heilbrun
 */
public class Shell {

  private static final String COMMAND_PREFIX = ">>> ";
  private static final String INVALID_IMAGE_MSG = "Invalid image path";
  private static final String INVALID_COMMAND_MSG = "Did not execute due to incorrect command.";
  private static final String EXIT_SHELL_COMMAND = "exit";
  private static final char[] DEFAULT_CHAR_SET = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
  private static final int DEFAULT_RES = 2;
  private static final String DEFAULT_OUTPUT = "console";

  /**
   * Constructs an empty Shell instance.
   */
  public Shell() {}

  /**
   * Starts the shell interface and enters an interactive command loop.
   * Initializes the image, algorithm, output manager, and command map.
   * Prints the result or error message based on user input.
   *
   * @param imageName The file path to the input image.
   */
  public void run(String imageName) {
    Image image;
    try {
      image = new Image(imageName);
    } catch (IOException e) {
      System.out.println(INVALID_IMAGE_MSG);
      return;
    }

    AsciiArtAlgorithm asciiArtAlgorithm =
        new AsciiArtAlgorithm(image, DEFAULT_RES, DEFAULT_CHAR_SET);
    OutPutManager outputManager = new OutPutManager(DEFAULT_OUTPUT, "out.html");

    HashMap<String, ShellCommands> shellCommandsMap =
        ShellCommandFactory.createShellCommandsMap(asciiArtAlgorithm, outputManager);

    String commandLine;
    String[] commandParts;

    while (true) {
      System.out.print(COMMAND_PREFIX);
      commandLine = KeyboardInput.readLine();

      if (commandLine == null || commandLine.trim().isEmpty()) {
        System.out.println(INVALID_COMMAND_MSG);
        continue;
      }

      commandParts = commandLine.split(" ");
      String command = commandParts[0];

      if (command.equals(EXIT_SHELL_COMMAND)) {
        break;
      }

      ShellCommands commandHandler = shellCommandsMap.get(command);
      if (commandHandler == null) {
        System.out.println(INVALID_COMMAND_MSG);
        continue;
      }

      try {
        String[] args = new String[commandParts.length - 1];
        System.arraycopy(commandParts, 1, args, 0, args.length);
        commandHandler.run_command(args);
      } catch (ShellExceptions e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Entry point of the program. Launches the shell with the image path provided as an argument.
   *
   * @param args Command-line arguments. Expects one argument: the image file path.
   */
  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java Shell <image_path>");
      return;
    }
    Shell shell = new Shell();
    shell.run(args[0]);
  }
}
