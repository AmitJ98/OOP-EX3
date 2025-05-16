package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import exceptions.ShellExceptions;

/**
 * Implements the shell command "res", which allows the user to increase or decrease
 * the resolution (number of characters per row) used in the ASCII art output.
 *
 * @author Amit Joseph, Maya Heilbrun
 */
public class ResShellCommand implements ShellCommands {

  /** Error message when resolution exceeds limits. */
  private static final String EXCEEDING_BOUNDARIES_MSG = "Did not change resolution due to exceeding boundaries.";

  /** Error message for incorrect command format. */
  private static final String INVALID_ARGS_MSG = "Did not change resolution due to incorrect format.";

  /** Format string for resolution change printout. */
  private static final String RES_CHANGED_MSG = "Resolution set to %s.\n";

  /** Token to increase resolution. */
  private static final String UP_RES_COMMAND = "up";

  /** Token to decrease resolution. */
  private static final String DOWN_RES_COMMAND = "down";

  private AsciiArtAlgorithm asciiArtAlgorithm;

  /**
   * Constructs a new ResShellCommand.
   *
   * @param asciiArtAlgorithm The algorithm instance whose resolution will be modified.
   */
  public ResShellCommand(AsciiArtAlgorithm asciiArtAlgorithm) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;
  }

  /**
   * Executes the resolution command. Modifies or prints the resolution depending on arguments.
   *
   * @param args A single argument: "up", "down", or none to print current resolution.
   * @throws ShellExceptions If format is invalid or resolution boundaries are violated.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    int newRes = this.asciiArtAlgorithm.getResolution();

    if (args.length < 1) {
      System.out.printf(RES_CHANGED_MSG, newRes);
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }

    String command = args[0];
    if (command.equals(UP_RES_COMMAND)) {
      newRes *= 2;
    } else if (command.equals(DOWN_RES_COMMAND)) {
      newRes /= 2;
    } else {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }

    if (!validateResolution(newRes)) {
      throw new ShellExceptions(EXCEEDING_BOUNDARIES_MSG);
    }

    asciiArtAlgorithm.setResolution(newRes);
    System.out.printf(RES_CHANGED_MSG, newRes);
  }

  /*
   * Validates whether the new resolution falls within the allowed bounds,
   * calculated from the image's width and height.
   */
  private boolean validateResolution(int newRes) {
    int minCharsInRow = Math.max(1, asciiArtAlgorithm.getImageWidth() / asciiArtAlgorithm.getImageHeight());
    int maxCharsInRow = asciiArtAlgorithm.getImageWidth();
    return minCharsInRow <= newRes && newRes <= maxCharsInRow;
  }
}
