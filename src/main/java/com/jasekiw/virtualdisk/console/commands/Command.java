package com.jasekiw.virtualdisk.console.commands;


public abstract class Command
{
    protected String signature;
    private String[] options;
    public abstract String run();
    protected boolean hasOptions() {
        return options.length != 0;
    }
    protected String getOption(int index) {
        return options[index];
    }
    public void setOptions(String[] options) {
        this.options = options;
    }
    public abstract String getSignature();
}
