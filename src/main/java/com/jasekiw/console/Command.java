package com.jasekiw.console;

public abstract class Command
{
    protected String signature;
    private String[] options;
    public abstract String run();
    protected boolean hasOptions() {
        return options.length != 0;
    }
    protected String getOption(int index) {
        if(options.length > index)
            return options[index];
        return null;
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