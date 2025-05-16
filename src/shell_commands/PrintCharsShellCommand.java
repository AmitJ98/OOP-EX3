package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import exceptions.ShellExceptions;
import java.util.Arrays;

/**
 * Implements the shell command "chars", which prints all characters currently in the charset
 * used by the ASCII art algorithm.
 * The characters are printed in ASCII order, space-separated on a single line.
 *
 * @author Amit Joseph, Maya Heilbrun
 */
public class PrintCharsShellCommand implements ShellCommands {

  private final AsciiArtAlgorithm asciiArtAlgorithm;

  /**
   * Constructs a new PrintCharsShellCommand.
   *
   * @param asciiArtAlgorithm The algorithm instance that manages the current charset.
   */
  public PrintCharsShellCommand(AsciiArtAlgorithm asciiArtAlgorithm) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;
  }

  /**
   * Executes the "chars" command.
   * Prints the characters in the charset in ascending ASCII order.
   *
   * @param args This command expects no arguments.
   * @throws ShellExceptions Never thrown in this implementation, but required by interface.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    char[] charList = asciiArtAlgorithm.getCharSetByList();
    Arrays.sort(charList);
    for (char ch : charList) {
      System.out.print(ch + " ");
    }
    System.out.println();
  }
}
