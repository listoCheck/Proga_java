package lab5.commands;


public abstract class Command {
    private String name;
    public Command(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public boolean checkCommand(String EnteredCommand) {
        return EnteredCommand.equals(name);
    }
    public void execute(String EnteredCommand) {}

}
