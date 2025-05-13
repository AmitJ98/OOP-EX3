package shell_commands;
import exceptions.ShellExceptions;

public interface ShellCommands {

  void run_command(String [] args) throws ShellExceptions ;
}
