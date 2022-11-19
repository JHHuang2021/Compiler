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

    public int GetSize() {
        return size;
    }
}
