import java.io.*;
import java.util.*;
import java.io.FileReader;

public class Parenthesis_Parser
{
    /*Program will take fileName as command line arguments
    Then create file or access the file
    read through the file, adding characters like [ ] { } " ( ) to the stack while checking if every character has the corresponding closing character
    Tell user whether it is proper or not
     */

    public static void main(String[] args)
    {
        Stack<Character> parseCharStack = new Stack<>();

        if (args.length < 1)
        {
            System.out.println("No fileName provided");
            System.exit(1);
        }
        else
        {
            try (FileReader fr = new FileReader(args[0]))
            {
                int cToRead;

                while ((cToRead = fr.read()) != -1)
                {
                    //first account for the quotes, since it'll pick up as duplicate if do it the same way as everything else
                    if (cToRead == '"')
                    {
                        if (parseCharStack.isEmpty())
                        {
                            parseCharStack.push('"');
                        }
                        else
                        {
                            if (parseCharStack.peek() == '"')
                            {
                                parseCharStack.pop();
                            }
                            else
                            {
                                parseCharStack.push('"');
                            }
                        }
                    }

                    if (cToRead == '{' || cToRead == '[' || cToRead == '(')
                    {
                        parseCharStack.push((char)cToRead);
                    }
                    else if (cToRead == '}')
                    {
                        if (parseCharStack.peek() == '{')
                        {
                            parseCharStack.pop();
                        }
                    }
                    else if (cToRead == ']')
                    {
                        if (parseCharStack.peek() == '[')
                        {
                            parseCharStack.pop();
                        }
                    }
                    else if (cToRead == ')')
                    {
                        if (parseCharStack.peek() == '(')
                        {
                            parseCharStack.pop();
                        }
                    }
                }
            } catch (Exception io)
            {
                System.out.println(io.getMessage());
                System.exit(2);
            }
        }

        if (parseCharStack.isEmpty())
        {
            System.out.println("Your stack is well-formed!");
        }
        else
        {
            System.out.println("Your stack is NOT well-formed");
        }
    }

}
