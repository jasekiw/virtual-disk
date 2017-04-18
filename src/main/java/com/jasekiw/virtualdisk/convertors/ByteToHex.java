package com.jasekiw.virtualdisk.convertors;

public class ByteToHex
{
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * converts a byte to an array that contains the number value of a hex value
     * @param number The byte to convert
     * @return The number representation of a hex value
     */
    public byte[] byteToHexArray(byte number)
    {
        double middle = (double)number / 16.0;
        byte secondDigit = (byte) middle;
        byte singleDigit = (byte) ((middle - secondDigit) * 16.0);
        return new byte[] {secondDigit, singleDigit};
    }

    /**
     * converts a byte to an array that contains the number value of a hex value
     * @param number The byte to convert
     * @return The number representation of a hex value
     */
    public byte[] byteToHexArray(int number)
    {
        return byteToHexArray((byte)number);
    }




}
