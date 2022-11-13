package tree;

import java.util.Iterator;
import java.util.List;

public class BTreePrinter {
    private final String name;
    private final List<BTreePrinter> children = new java.util.ArrayList<>();

    public BTreePrinter(Node root, Node[] children) {
        this.name = root.getValue();
        if (children != null) {
            for (Node child : children) {
                if (child != null) {
                    this.children.add(new BTreePrinter(child, child.getChildren()));
                }
            }
        }
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(name);
        buffer.append("\n");
        for (Iterator<BTreePrinter> it = children.iterator(); it.hasNext(); ) {
            BTreePrinter next = it.next();
            if (it.hasNext()) {
                next.print(buffer, childrenPrefix + "├── ", childrenPrefix + "│   ");
            } else {
                next.print(buffer, childrenPrefix + "└── ", childrenPrefix + "    ");
            }
        }
    }
}
