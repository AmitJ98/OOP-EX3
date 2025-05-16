package shell_commands;

import exceptions.ShellExceptions;
import ascii_art.AsciiArtAlgorithm;

/**
 * Implements the shell command "add", allowing the user to add characters to the current charset
 * used by the ASCII art algorithm.
 * @author Amit Joseph, Maya Heilbrun
 */
public class AddShellCommand implements ShellCommands {

  /** Error message for invalid arguments. */
  private final String INVALID_ARGS_MSG = "Did not add due to incorrect format.";

  /** First allowed ASCII character code. */
  private static final int FIRST_ASCII = 32;

  /** Last allowed ASCII character code. */
  private static final int LAST_ASCII = 126;

  /** Token representing the space character. */
  private static final String SPACE_TOKEN = "space";

  /** Token representing all valid ASCII characters. */
  private static final String ALL_CHARS_TOKEN = "all";

  private AsciiArtAlgorithm asciiArtAlgorithm;

  /**
   * Constructs a new AddShellCommand.
   *
   * @param asciiArtAlgorithm The algorithm object to which characters will be added.
   */
  public AddShellCommand(AsciiArtAlgorithm asciiArtAlgorithm) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;
  }

  /**
   * Executes the add command with the given arguments.
   *
   * @param args The arguments for the command.
   * @throws ShellExceptions If the arguments are invalid.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    if (args.length < 1) {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
    String token = args[0];
    if (token.equals(SPACE_TOKEN)) {
      addSingleChar(' ');
    } else if (token.equals(ALL_CHARS_TOKEN)) {
      addCharFromRange(FIRST_ASCII, LAST_ASCII);
    } else {
      simplifyToChars(token);
    }
  }

  /*
   * Parses character argument. Adds either a single character or a range of characters.
   */
  private void simplifyToChars(String args) throws ShellExceptions {
    if (args.length() == 1) {
      addSingleChar(args.charAt(0));
    } else if ((args.length() == 3) && args.charAt(1) == '-') {
      char start = args.charAt(0);
      char end = args.charAt(2);
      if (end < start) {
        char temp = start;
        start = end;
        end = temp;
      }
      if (FIRST_ASCII <= start && end <= LAST_ASCII) {
        addCharFromRange(start, end);
      } else {
        throw new ShellExceptions(INVALID_ARGS_MSG);
      }
    } else {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
  }

  /*
   * Adds all characters in the given ASCII range to the charset.
   */
  private void addCharFromRange(int start, int end) {
    for (int token = start; token <= end; token++) {
      addSingleChar((char) token);
    }
  }

  /*
   * Adds a single character to the charset.
   */
  private void addSingleChar(char token) {
    this.asciiArtAlgorithm.addChar(token);
  }
}
