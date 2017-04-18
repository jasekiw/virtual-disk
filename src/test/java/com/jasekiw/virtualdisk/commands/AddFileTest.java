package com.jasekiw.virtualdisk.commands;

import com.jasekiw.console.exceptions.ConsoleException;
import com.jasekiw.virtualdisk.AppTestCase;
import com.jasekiw.virtualdisk.console.commands.AddFileCommand;

public class AddFileTest extends AppTestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AddFileTest(String testName)
    {
        super(testName);
    }

    public void test_handle() {
        AddFileCommand command = new AddFileCommand();
        command.setOptions(new String[] {
             "hello.txt"
        });
        String result = null;
        try {
            result = command.run();
        } catch (ConsoleException e) {
            fail();
        }
        assertNotNull(result);
        System.out.println(result);
    }


}
