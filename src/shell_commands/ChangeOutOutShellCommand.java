package shell_commands;

import exceptions.ShellExceptions;
import ascii_art.OutPutManager;

/**
 * Implements the shell command "output", allowing the user to change the output destination
 * of the ASCII art algorithm to either "console" or "html".
 * @author Amit Joseph, Maya Heilbrun
 */
public class ChangeOutOutShellCommand implements ShellCommands {

  /** Error message for invalid output arguments. */
  private static final String INVALID_ARGS = "Did not change output method due to incorrect format.";

  private final OutPutManager outPutManager;

  /**
   * Constructs a new ChangeOutOutShellCommand with the specified output manager.
   *
   * @param outPutManager The manager controlling the output method of the ASCII art.
   */
  public ChangeOutOutShellCommand(OutPutManager outPutManager) {
    this.outPutManager = outPutManager;
  }

  /**
   * Executes the output command. Changes the output method to either "console" or "html".
   *
   * @param args The arguments to the command; should contain exactly one valid output type.
   * @throws ShellExceptions If the provided argument is invalid or missing.
   */
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    if (args.length < 1) {
      throw new ShellExceptions(INVALID_ARGS);
    }

    String outPutMethod = args[0];
    if (outPutMethod.equals("console") || outPutMethod.equals("html")) {
      outPutManager.changeOutPut(outPutMethod);
    } else {
      throw new ShellExceptions(INVALID_ARGS);
    }
  }
}
