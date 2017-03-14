package com.jasekiw.console;

import java.util.ArrayList;

public class AppUsage
{
    protected ArrayList<Command> commands;
    protected String header;
    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }
    public void setAppHeader(String header) {
        this.header = header;
    }
    public String getAppHeader() {
        return header;
    }

    public String getUsage() {

        final String[] usage = {""};
        commands.forEach((Command command) -> usage[0] += command.getSignature() + " - " + command.getDescription() + "\n");
        return usage[0];
    }
}
