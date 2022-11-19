package Backend.IR.IRType;

import java.util.ArrayList;
import java.util.HashMap;

public class IRClass extends IRType {
    public ArrayList<IRType> var_type = new ArrayList<>();
    public HashMap<String, Integer> idt_to_rank = new HashMap<>();

    public IRClass(String name) {
        super(name, 0);
    }

    @Override
    public int GetSize() {
        int sum = 0;
        for (int i = 0; i < var_type.size(); i++)
            sum += var_type.get(i).GetSize();
        return sum;
    }

    public String ToString() {
        return "%" + name;
    }
}
