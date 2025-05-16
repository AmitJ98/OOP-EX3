package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import ascii_art.OutPutManager;
import exceptions.ShellExceptions;

/**
 * Implements the shell command "asciiArt", which triggers execution of the ASCII art
 * algorithm and outputs the resulting character matrix using the current output method.
 * @author Amit Joseph, Maya Heilbrun
 */
public class RunAlgoShellCommand implements ShellCommands {

  /** Error message if the charset has fewer than two characters. */
  private static final String INVALID_ARGS = "Did not execute. Charset is too small.";

  private final AsciiArtAlgorithm asciiArtAlgorithm;
  private final OutPutManager outPutManager;

  /**
   * Constructs a new RunAlgoShellCommand.
   *
   * @param asciiArtAlgorithm The algorithm instance responsible for generating the ASCII art.
   * @param outPutManager The manager responsible for displaying the output.
   */
  public RunAlgoShellCommand(AsciiArtAlgorithm asciiArtAlgorithm, OutPutManager outPutManager) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;
    this.outPutManager = outPutManager;
  }

  /**
   * Executes the "asciiArt" command. Runs the algorithm and outputs the resulting ASCII image.
   *
   * @param args The command arguments (ignored).
   * @throws ShellExceptions If the charset contains fewer than two characters.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    if (this.asciiArtAlgorithm.getCharSetByList().length < 2) {
      throw new ShellExceptions(INVALID_ARGS);
    }
    char[][] imageMatrix = asciiArtAlgorithm.run();
    outPutManager.outPut(imageMatrix);
  }
}
