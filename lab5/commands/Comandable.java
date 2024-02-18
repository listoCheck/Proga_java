package lab5.commands;

public interface Comandable {
    void execute(String EnteredCommand);
    void execute(String EnteredCommand, int id);
}
