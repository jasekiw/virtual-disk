package com.jasekiw.console.exceptions;

public class ParameterIsIncorrectType extends ParameterException
{
    public ParameterIsIncorrectType()
    {
        super("A parameter is of the incorrect type. Please check your arguments");
    }
}
