package Backend.IR.IRType;

abstract public class IRType {
    public String name;
    public int size;

    public IRType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String ToString() {
        return size != 0 ? "i" + size : name;
    }

    public String GetType() {
        return ToString();
    }

    public int GetSize() {
        return size;
    }

    public IRType FatherType() {
        return null;
    }

    public String PrintArgs() {
        return "";
    }
}
