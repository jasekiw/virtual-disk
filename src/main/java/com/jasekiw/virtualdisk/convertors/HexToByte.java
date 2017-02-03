package com.jasekiw.virtualdisk.convertors;

public class HexToByte
{
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Transcodes a single hex character into a byte
     * @requires valid hex character to be given
     * @param hex the hex character to parse into a byte
     * @return The byte that is transcoded
     */
    public byte getDecimalByteFromHexChar(char hex) {
        int currentIndex = 0;
        boolean foundCharacter = false;
        while(currentIndex < hexArray.length && !foundCharacter)
        {
            if(hexArray[currentIndex] == hex)
                foundCharacter = true;
            else
                currentIndex++;
        }
        return (byte)currentIndex;
    }

    public byte[] getDecimalBytesFromHexString(String hex) {
        byte[] output = new byte[hex.length()];
        for(int i =0; i < hex.length(); i++)
            output[i] = getDecimalByteFromHexChar(hex.charAt(i));
        return output;
    }

    /**
     * Gets a byte that is still in base 16 format
     * @param hex
     * @return
     */
    public byte getHexByteFromHexString(String hex)
    {
        byte[] bytes = getDecimalBytesFromHexString(hex);
        String result = "";
        for(int i =0; i < bytes.length; i++)
            result += bytes[i];
        return Byte.parseByte(result);
    }

    /**
     * Gets a byte in decimal format from two bytes consisting of a hex value
     * @param bytes
     * @return
     */
    public byte getDecimalByteFromHexBytes(byte[] bytes) {
        byte firstByte = bytes[0];
        byte secondByte = bytes[1];
        return (byte) ((firstByte * 16) + secondByte);
    }

    /**
     * converts a byte in hex format to decimal format
     * @param hex
     * @return
     */
    public byte getDecimalByteFromHexByte(byte hex) {
        String hexString = String.valueOf(hex);
        Byte firstByte = (byte)hexString.charAt(0);
        Byte secondByte = (byte)hexString.charAt(1);
        return getHexByteFromHexString(hexString);
    }

    public String getHexStringFromHexBytes(byte arr) {
        return String.valueOf(hexArray[arr]);
    }

    public String getHexStringFromHexBytes(byte[] arr) {
        if(arr.length == 1)
            return String.valueOf(hexArray[arr[0]]);
        return String.valueOf(hexArray[arr[0]]) + String.valueOf(hexArray[arr[1]]);
    }


}
