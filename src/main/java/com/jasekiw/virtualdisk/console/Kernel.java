package com.jasekiw.virtualdisk.console;

import com.google.inject.Inject;
import com.jasekiw.console.AppUsage;
import com.jasekiw.virtualdisk.console.commands.*;

public class Kernel extends com.jasekiw.console.Kernel
{
    @Inject
    public Kernel(AppUsage usage)
    {
        super(usage);
        usage.setAppHeader("Virtual Hard Disk. Version " + com.jasekiw.virtualdisk.App.getVersion());
    }

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
    protected Class unknownCommand()
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
