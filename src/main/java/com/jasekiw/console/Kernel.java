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
            {
                try { return ((Command) App.getInjector().getInstance(defaultCommand())).run(); }
                catch (Exception e) { return e.getMessage(); }
            }
            return appUsage.getUsage();
        }

        Optional<Command> command = commandInstances.stream()
                .filter(c -> c.getSimpleSignature() != null && c.getSimpleSignature().equals(args[0]))
                .findFirst();
        if (command.isPresent()) {
            String[] options = new String[args.length - 1];
            System.arraycopy(args, 1, options, 0, args.length - 1);
            command.get().setOptions(options);
            try { return command.get().run(); }
            catch (Exception e) { return e.getMessage(); }
        }
        try { return ((Command) App.getInjector().getInstance(unknownCommand())).run(); }
        catch (Exception e) { return e.getMessage(); }
    }

    private void registerCommands()
    {
        for (Class command : commands())
            commandInstances.add((Command) App.getInjector().getInstance(command));
    }



}
