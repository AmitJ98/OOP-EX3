package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import image_char_matching.SubImgCharMatcher.RoundingMode;
import exceptions.ShellExceptions;

/**
 * Implements the shell command "round", allowing the user to change the rounding mode used
 * when mapping image brightness to ASCII characters.
 * @author Amit Joseph, Maya Heilbrun
 */
public class ChangeRoundShellCommand implements ShellCommands {

  /** Error message for invalid rounding arguments. */
  private static final String INVALID_ARGS = "Did not change rounding method due to incorrect format.";

  private AsciiArtAlgorithm asciiArtAlgorithm;

  /**
   * Constructs a new ChangeRoundShellCommand.
   *
   * @param asciiArtAlgorithm The ASCII art algorithm instance whose rounding mode is to be updated.
   */
  public ChangeRoundShellCommand(AsciiArtAlgorithm asciiArtAlgorithm) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;
  }

  /**
   * Executes the round command. Updates the rounding mode of the algorithm.
   *
   * @param args The command arguments; expects one of: "up", "down", or "abs".
   * @throws ShellExceptions If the provided argument is invalid or missing.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    if (args.length < 1) {
      throw new ShellExceptions(INVALID_ARGS);
    }

    String newRoundingMode = args[0];
    if (newRoundingMode.equals("up")) {
      this.asciiArtAlgorithm.setRoundType(RoundingMode.UP);
    } else if (newRoundingMode.equals("down")) {
      this.asciiArtAlgorithm.setRoundType(RoundingMode.DOWN);
    } else if (newRoundingMode.equals("abs")) {
      this.asciiArtAlgorithm.setRoundType(RoundingMode.ABS);
    } else {
      throw new ShellExceptions(INVALID_ARGS);
    }
  }
}
