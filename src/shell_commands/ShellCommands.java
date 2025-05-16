package shell_commands;

import exceptions.ShellExceptions;

/**
 * Represents a shell command that can be executed within the ASCII art shell interface.
 * Each implementing class defines specific behavior based on input arguments.
 * @author Amit Joseph, Maya Heilbrun
 */
public interface ShellCommands {

  /**
   * Executes the shell command with the given arguments.
   *
   * @param args The arguments passed to the command.
   * @throws ShellExceptions If an error occurs during command execution (e.g., invalid arguments).
   */
  void run_command(String[] args) throws ShellExceptions;
}
