package com.jasekiw.console.exceptions;

public class ParameterNotFoundException extends ParameterException
{
    public ParameterNotFoundException()
    {
        super("A parameter is not found. Please check your arguments");
    }
}
