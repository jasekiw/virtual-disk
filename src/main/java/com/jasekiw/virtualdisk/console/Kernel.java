package com.jasekiw.virtualdisk.console;

import com.jasekiw.virtualdisk.App;
import com.jasekiw.virtualdisk.console.commands.*;
import java.util.ArrayList;
import java.util.Optional;

public class Kernel
{
    /**
     * Commands provided by your application.
     */
    private Class[] commands = new Class[]{
            FormatCommand.class,
            HelpCommand.class,
            FileTypeCommand.class,
            DirectoryListingCommand.class
    };

    /**
     * The command provided when no command is found
     */
    private Class unkownCommand = UnknownCommand.class;

    /**
     * The command provided for default functionality
     */
    private Class defaultCommand = DefaultCommand.class;


    private ArrayList<Command> commandInstances = new ArrayList<>();

    public Kernel()
    {
        registerCommands();
    }

    public String handle(String[] args)
    {
        if (args.length == 0)
            return ((Command) App.getInjector().getInstance(defaultCommand)).run();
        Optional<Command> command = commandInstances.stream()
                .filter(c -> c.getSignature() != null && c.getSignature().equals(args[0]))
                .findFirst();
        if (command.isPresent()) {
            String[] options = new String[args.length - 1];
            System.arraycopy(args, 1, options, 0, args.length - 1);
            command.get().setOptions(options);
            return command.get().run();
        } else
            return ((Command) App.getInjector().getInstance(unkownCommand)).run();
    }

    private void registerCommands()
    {
        for (Class command : commands)
            commandInstances.add((Command) App.getInjector().getInstance(command));
    }

}
