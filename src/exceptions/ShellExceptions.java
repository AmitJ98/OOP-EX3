package exceptions;

/**
 * Represents exceptions thrown during the execution of shell commands
 * in the ASCII art application. Used to signal invalid command arguments
 * or operations that cannot be performed.
 *
 * @author Amit Joseph, Maya Heilbrun
 */
public class ShellExceptions extends Exception {

  /**
   * Constructs a new ShellExceptions with the specified detail message.
   *
   * @param message The detail message explaining the reason for the exception.
   */
  public ShellExceptions(String message) {
    super(message);
  }
}
