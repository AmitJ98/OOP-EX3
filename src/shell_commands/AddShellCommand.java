package shell_commands;

import exceptions.ShellExceptions;
import ascii_art.AsciiArtAlgorithm;

public class AddShellCommand implements ShellCommands{
  static final String INVALID_ARGS_MSG = "Did not add due to incorrect format.";
  private static final int FIRST_ASCII = 32;
  private static final int LAST_ASCII = 126;
  private static final String SPACE_TOKEN = "space";
  private static final String ALL_CHARS_TOKEN = "all";

  private AsciiArtAlgorithm asciiArtAlgorithm;


  public AddShellCommand(AsciiArtAlgorithm asciiArtAlgorithm) {
    this.asciiArtAlgorithm = asciiArtAlgorithm;

  }

  @Override
  public void run_command(String[] args) throws ShellExceptions {
    if (args.length < 1) {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
    String token = args[0];
    if (token.equals(SPACE_TOKEN)){
      addSingleChar(' ');
    } else if (token.equals(ALL_CHARS_TOKEN)) {
      addCharFromRange(FIRST_ASCII, LAST_ASCII);
    }
    else {
      simplifyToChars(token);
    }
  }

  private void simplifyToChars(String args) throws ShellExceptions {
    if (args.length() == 1){
      addSingleChar(args.charAt(0));
    } else if ((args.length() == 3) && args.charAt(1) == '-') {
      char start = args.charAt(0);
      char end = args.charAt(2);

      if (end < start){
        char temp = start;
        start = end;
        end = temp;
      }
      if (FIRST_ASCII <= start && end <= LAST_ASCII){
        addCharFromRange(start, end);
      } else{
        throw new ShellExceptions(INVALID_ARGS_MSG);
      }
    } else {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
  }

  private void addCharFromRange(int start, int end) {
    for(int token = start; token <= end; token++){
      addSingleChar((char) token);
    }
  }

  private void addSingleChar(char token) {
    this.asciiArtAlgorithm.addChar(token);
  }
}
