package Util;

public class CheckName {
    String[] sp = { "void", "bool", "int", "string", "new", "class", "null", "true", "false", "this", "if", "else",
            "for", "while", "break", "continue", "return" };

    public boolean Check(String name) {
        for (String i : sp)
            if (name == i)
                return false;

        return true;
    }
}
