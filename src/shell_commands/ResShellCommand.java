package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import exceptions.ShellExceptions;

public class ResShellCommand implements ShellCommands{
  public static final String EXCEEDING_BOUNDARIES_MSG = "Did not change resolution due to exceeding boundaries.";
  private static final String INVALID_ARGS_MSG = "Did not change resolution due to incorrect format.";
  private static final String RES_CHANGED_MSG = "Resolution set to %s.\n";
  private static final String UP_RES_COMMAND = "up";
  private static final String DOWN_RES_COMMAND = "down";


  private AsciiArtAlgorithm asciiArtAlgorithm;

  public ResShellCommand(AsciiArtAlgorithm asciiArtAlgorithm){
    this.asciiArtAlgorithm = asciiArtAlgorithm;

  }
  @Override
  public void run_command(String[] args) throws ShellExceptions {
    int newRes = this.asciiArtAlgorithm.getResolution() ;

    if (args.length < 1){
      System.out.printf(RES_CHANGED_MSG,newRes);
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
    String command = args[0];
    if (command.equals(UP_RES_COMMAND)){
      newRes *= 2;
    } else if (command.equals(DOWN_RES_COMMAND)) {
      newRes /= 2;
    } else {
      throw new ShellExceptions(INVALID_ARGS_MSG);
    }
    if (!validateResolution(newRes)){
      throw new ShellExceptions(EXCEEDING_BOUNDARIES_MSG);
    }
    asciiArtAlgorithm.setResolution(newRes);
    System.out.printf(RES_CHANGED_MSG,newRes);
  }

  private boolean validateResolution(int newRes){
    int minCharsInRow = Math.max(
        1, asciiArtAlgorithm.getImageWidth() / asciiArtAlgorithm.getImageHeight());
    int maxCharsInRow = asciiArtAlgorithm.getImageWidth();

    return minCharsInRow <= newRes && newRes <= maxCharsInRow;
  }

}
/**
 * TODO NEED TO UPDATE THE DEFUALT RES IN ASCIIARTALGO TO 2 (SECTION 2.6.5)
 */