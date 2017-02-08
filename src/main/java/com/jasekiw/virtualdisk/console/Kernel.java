package com.jasekiw.virtualdisk.console;

import com.jasekiw.virtualdisk.console.commands.*;

public class Kernel extends com.jasekiw.console.Kernel
{
    /**
     * Commands provided by your application.
     */
    @Override
    protected Class[] commands()
    {
        return new Class[]{
                FormatCommand.class,
                HelpCommand.class,
                FileTypeCommand.class,
                DirectoryListingCommand.class,
                VersionCommand.class,
                DiskUsageCommand.class
        };
    }

    /**
     * The command provided when no command is found
     */
    @Override
    protected Class unkownCommand()
    {
        return UnknownCommand.class;
    }

    /**
     * The command provided for default functionality
     */
    @Override
    protected Class defaultCommand()
    {
        return DefaultCommand.class;
    }
}
