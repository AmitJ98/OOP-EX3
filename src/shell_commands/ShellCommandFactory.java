package shell_commands;

import ascii_art.AsciiArtAlgorithm;
import ascii_art.OutPutManager;
import java.util.HashMap;

/**
 * A factory class responsible for constructing and providing a mapping from shell command strings
 * to their corresponding ShellCommands implementations.
 * @author Amit Joseph, Maya Heilbrun
 */
public class ShellCommandFactory {

  /**
   * Constructs a ShellCommandFactory. This constructor does nothing as all functionality
   * is provided through the static method createShellCommandsMap.
   */
  public ShellCommandFactory() {}

  /**
   * Creates a map of command strings to their corresponding ShellCommands implementations.
   *
   * @param asciiArtAlgorithm The algorithm instance to be passed to relevant command objects.
   * @param outPutManager The output manager to be passed to commands involving output.
   * @return A HashMap mapping command names to ShellCommands instances.
   */
  public static HashMap<String, ShellCommands> createShellCommandsMap(
      AsciiArtAlgorithm asciiArtAlgorithm, OutPutManager outPutManager) {

    HashMap<String, ShellCommands> shellCommandsMap = new HashMap<>();
    shellCommandsMap.put("chars", new PrintCharsShellCommand(asciiArtAlgorithm));
    shellCommandsMap.put("add", new AddShellCommand(asciiArtAlgorithm));
    shellCommandsMap.put("remove", new RemoveShellCommand(asciiArtAlgorithm));
    shellCommandsMap.put("res", new ResShellCommand(asciiArtAlgorithm));
    shellCommandsMap.put("round", new ChangeRoundShellCommand(asciiArtAlgorithm));
    shellCommandsMap.put("output", new ChangeOutOutShellCommand(outPutManager));
    shellCommandsMap.put("asciiArt", new RunAlgoShellCommand(asciiArtAlgorithm, outPutManager));

    return shellCommandsMap;
  }
}
