package com.jasekiw.virtualdisk.convertors;

public class ByteToHex
{
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public byte[] byteTohexArray(byte number)
    {
        double middle = (double)number / 16.0;
        byte secondDigit = (byte) middle;
        byte singleDigit = (byte) ((middle - secondDigit) * 16.0);
        return new byte[] {secondDigit, singleDigit};
    }






}
