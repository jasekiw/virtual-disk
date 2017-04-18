package com.jasekiw.console;

import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.console.exceptions.ParameterIsIncorrectType;
import com.jasekiw.console.exceptions.ParameterNotFoundException;

public abstract class Command
{
    protected String signature;
    private String[] options;
    public abstract String run() throws ConsoleException;
    protected boolean hasOptions() {
        return options.length != 0;
    }
    protected String getOption(int index) throws ParameterNotFoundException
    {
        if(options.length > index)
            return options[index];
        throw new ParameterNotFoundException();
    }

    protected int getInt(int index) throws ParameterNotFoundException, ParameterIsIncorrectType
    {
        try {
            return Integer.parseInt(this.getOption(index));
        }
        catch (NumberFormatException e){
            throw new ParameterIsIncorrectType();
        }

    }

    public void setOptions(String[] options) {
        this.options = options;
    }
    public abstract String getSignature();
    public String getSimpleSignature() {
        return this.getSignature().split(" +")[0];
    }
    public abstract String getDescription();
}