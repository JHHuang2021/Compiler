package Util;

import MIR.register;
import Util.error.semanticError;

import java.util.HashMap;

import org.antlr.v4.codegen.model.PlusBlock;

public class Scope {

    private HashMap<String, Type> members;
    public HashMap<String, register> entities = new HashMap<>();
    public Type returnType = null;
    private Scope parentScope;

    public enum ScopeType {
        FOR, WHILE, IF, FUNC
    };

    public ScopeType scopeType = null;

    public Scope(Scope parentScope) {
        members = new HashMap<>();
        this.parentScope = parentScope;
    }

    public Scope parentScope() {
        return parentScope;
    }

    public boolean containScopeType(ScopeType scopeType, boolean lookUpon) {
        if (this.scopeType == scopeType)
            return true;
        else if (parentScope != null && lookUpon)
            return parentScope.containScopeType(scopeType, lookUpon);
        else
            return false;
    }

    public void defineVariable(String name, Type t, position pos) {
        if (members.containsKey(name))
            throw new semanticError("Semantic Error: variable redefine", pos);
        members.put(name, t);
    }

    public boolean containsVariable(String name, boolean lookUpon) {
        if (members.containsKey(name))
            return true;
        else if (parentScope != null && lookUpon)
            return parentScope.containsVariable(name, true);
        else
            return false;
    }

    public Type getType(String name, boolean lookUpon) {
        if (members.containsKey(name))
            return members.get(name);
        else if (parentScope != null && lookUpon)
            return parentScope.getType(name, true);
        return null;
    }

    public register getEntity(String name, boolean lookUpon) {
        if (entities.containsKey(name))
            return entities.get(name);
        else if (parentScope != null && lookUpon)
            return parentScope.getEntity(name, true);
        return null;
    }
}
