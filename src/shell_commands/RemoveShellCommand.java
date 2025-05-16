package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import exceptions.ShellExceptions;

/**
 * Implements the shell command "remove", which allows the user to remove characters
 * from the current charset used by the ASCII art algorithm.
 * @author Amit Joseph, Maya Heilbrun
 */
public class RemoveShellCommand implements ShellCommands {

  /** Error message for invalid input format. */
  private final String INVALID_ARGS_MSG = "Did not remove due to incorrect format.";

  /** Lowest printable ASCII value allowed. */
  private static final int FIRST_ASCII = 32;

  /** Highest printable ASCII value allowed. */
  private static final int LAST_ASCII = 126;

  /** Token representing the space character. */
  private static final String SPACE_TOKEN = "space";

  /** Token representing the entire ASCII character range. */
  private static final String ALL_CHARS_TOKEN = "all";

  private AsciiArtAlgorithm asciiArtAlgorithm;

  /**
   * Constructs a new RemoveShellCommand.
   *
   * @param asciiArtAlgorithm The ASCII art algorithm instance that manages the charset.
   */
  public RemoveShellCommand(AsciiArtAlgorithm asciiArtAlgorithm) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;
  }

  /**
   * Executes the "remove" command. Removes characters from the charset based on the given arguments.
   *
   * @param args A single argument specifying a character, character range, "space", or "all".
   * @throws ShellExceptions If the input format is invalid.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    if (args.length < 1) {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }

    String token = args[0];
    if (token.equals(SPACE_TOKEN)) {
      removeSingleChar(' ');
    } else if (token.equals(ALL_CHARS_TOKEN)) {
      removeCharFromRange(FIRST_ASCII, LAST_ASCII);
    } else {
      simplifyToChars(token);
    }
  }

  /*
   * Processes input strings to remove a single character or a range of characters.
   */
  private void simplifyToChars(String args) throws ShellExceptions {
    if (args.length() == 1) {
      removeSingleChar(args.charAt(0));
    } else if ((args.length() == 3) && args.charAt(1) == '-') {
      char start = args.charAt(0);
      char end = args.charAt(2);

      if (end < start) {
        char temp = start;
        start = end;
        end = temp;
      }

      if (FIRST_ASCII <= start && end <= LAST_ASCII) {
        removeCharFromRange(start, end);
      } else {
        throw new ShellExceptions(INVALID_ARGS_MSG);
      }
    } else {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
  }

  /*
   * Removes all characters in the given ASCII range from the charset.
   */
  private void removeCharFromRange(int start, int end) {
    for (int token = start; token <= end; token++) {
      removeSingleChar((char) token);
    }
  }

  /*
   * Removes a single character from the charset.
   */
  private void removeSingleChar(char token) {
    this.asciiArtAlgorithm.removeChar(token);
  }
}
