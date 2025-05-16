package ascii_art;

import ascii_output.ConsoleAsciiOutput;
import ascii_output.HtmlAsciiOutput;

/**
 * Manages the output destination for the ASCII art image matrix.
 * Supports console and HTML output using ConsoleAsciiOutput and HtmlAsciiOutput.
 * The output destination can be dynamically changed at runtime.
 *
 * @author Amit Joseph, Maya Heilbrun
 */
public class OutPutManager {
  /** Constant representing console output method. */
  private static final String OUT_PUT_CONSOLE = "console";

  /** Constant representing HTML output method. */
  private static final String OUT_PUT_HTML = "html";

  /** The font name used in the HTML output. */
  private static final String FONT_NAME = "New Courier";

  private final ConsoleAsciiOutput consoleOutPut;
  private final HtmlAsciiOutput htmlOutPut;
  private String currentOutput;

  /**
   * Constructs a new OutPutManager with the given starting output method and HTML file name.
   *
   * @param startingOutput The initial output method ("console" or "html").
   * @param fileName The file name for the HTML output.
   */
  public OutPutManager(String startingOutput, String fileName) {
    this.currentOutput = startingOutput;
    this.consoleOutPut = new ConsoleAsciiOutput();
    this.htmlOutPut = new HtmlAsciiOutput(fileName, FONT_NAME);
  }

  /**
   * Changes the current output method.
   *
   * @param newMethod The new output method to use ("console" or "html").
   */
  public void changeOutPut(String newMethod) {
    this.currentOutput = newMethod;
  }

  /**
   * Outputs the given ASCII art image matrix using the current output method.
   *
   * @param imageMatrix A 2D character array representing the ASCII image.
   */
  public void outPut(char[][] imageMatrix) {
    if (currentOutput.equals(OUT_PUT_CONSOLE)) {
      this.consoleOutPut.out(imageMatrix);
    } else if (currentOutput.equals(OUT_PUT_HTML)) {
      this.htmlOutPut.out(imageMatrix);
    }
  }
}
