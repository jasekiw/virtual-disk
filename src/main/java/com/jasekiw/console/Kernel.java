package com.jasekiw.console;

import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Kernel
{
    protected AppUsage appUsage;
    /**
     * Commands provided by your application.
     */
    protected abstract Class[] commands();

    /**
     * The command provided when no command is found
     */
    protected abstract Class unknownCommand();

    /**
     * The command provided for default functionality
     */
    protected abstract Class defaultCommand();


    private ArrayList<Command> commandInstances = new ArrayList<>();

    public Kernel(AppUsage usage)
    {
        registerCommands();
        appUsage = usage;
        appUsage.setCommands(commandInstances);
    }

    public String handle(String[] args)
    {
        if (args.length == 0)
        {
            if(defaultCommand() != null)
                return ((Command) App.getInjector().getInstance(defaultCommand())).run();
            return appUsage.getUsage();
        }

        Optional<Command> command = commandInstances.stream()
                .filter(c -> c.getSimpleSignature() != null && c.getSignature().equals(args[0]))
                .findFirst();
        if (command.isPresent()) {
            String[] options = new String[args.length - 1];
            System.arraycopy(args, 1, options, 0, args.length - 1);
            command.get().setOptions(options);
            return command.get().run();
        } else
            return ((Command) App.getInjector().getInstance(unknownCommand())).run();
    }

    private void registerCommands()
    {
        for (Class command : commands())
            commandInstances.add((Command) App.getInjector().getInstance(command));
    }



}
