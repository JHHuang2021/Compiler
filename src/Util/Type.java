package Util;

import java.util.ArrayList;

import AST.ExprNode;

public class Type {
    public String typeName;
    public boolean array = false;
    public int dim = 0;
    public ArrayList<ExprNode> dimArgs;
}
