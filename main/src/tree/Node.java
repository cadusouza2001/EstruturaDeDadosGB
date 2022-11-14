package tree;

import person.Pessoa;
import utils.DateFormat;

import java.util.Date;

public class Node {

    private String value;
    private Node left;
    private Node right;
    private int balanceFactor;
    private int height;
    private Pessoa pessoa;

    public Node(String value, Pessoa pessoa) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
        this.height = 0;
        this.pessoa = pessoa;
    }

    public Node(long value, Pessoa pessoa) {
        this.value = String.valueOf(value);
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
        this.height = 0;
        this.pessoa = pessoa;
    }

    public Node(Date value, Pessoa pessoa) {
        this.value = DateFormat.format(value);
        this.left = null;
        this.right = null;
        this.balanceFactor = 0;
        this.height = 0;
        this.pessoa = pessoa;
    }

    public String getValue() {
        return value;
    }

    public Node[] getChildren() {
        return new Node[]{this.left, this.right};
    }

    public Node find(String value) {
        if (this.value.compareToIgnoreCase(value) == 0) {
            return this;
        }
        if (value.compareToIgnoreCase(this.value) < 0 && this.left != null) {
            return this.left.find(value);
        }
        if (value.compareToIgnoreCase(this.value) > 0 && this.right != null) {
            return this.right.find(value);
        }
        return null;
    }

    public Node find(long value) {
        long thisValueLong = Long.parseLong(this.value);
        if (thisValueLong == value) {
            return this;
        }
        if (value < thisValueLong && this.left != null) {
            return this.left.find(value);
        }
        if (value > thisValueLong && this.right != null) {
            return this.right.find(value);
        }
        return null;
    }

    public Node find(Date value) {
        Date thisValueDate = DateFormat.parse(this.value);
        int compare = value.compareTo(thisValueDate);
        if (compare == 0) {
            return this;
        }
        if (compare < 0 && this.left != null) {
            return this.left.find(value);
        }
        if (compare > 0 && this.right != null) {
            return this.right.find(value);
        }
        return null;
    }

    //TODO melhorar essa lógica de busca
    public void startsWith(String value) {
        if (this.value.toLowerCase().startsWith(value)) {
            System.out.println(this.pessoa);
        }
        if (this.left != null) {
            this.left.startsWith(value);
        }
        if (this.right != null) {
            this.right.startsWith(value);
        }
    }

    //TODO melhorar essa lógica de busca tbm
    public void dateBetween(Date start, Date end) {
        Date thisValueDate = DateFormat.parse(this.value);
        if (thisValueDate.compareTo(start) >= 0 && thisValueDate.compareTo(end) <= 0) {
            System.out.println(this.pessoa);
        }
        if (this.left != null) {
            this.left.dateBetween(start, end);
        }
        if (this.right != null) {
            this.right.dateBetween(start, end);
        }
    }

    public boolean insert(String value, Pessoa pessoa) {
        if (this.value.compareToIgnoreCase(value) == 0) {
            return false;
        }
        if (value.compareToIgnoreCase(this.value) < 0) {
            if (this.left == null) {
                this.left = new Node(value, pessoa);
                this.updateBalanceFactor();
                this.updateHeight();
                return true;
            } else {
                if (this.left.insert(value, pessoa)) {
                    this.updateBalanceFactor();
                    this.updateHeight();
                    return true;
                }
            }
        } else {
            if (this.right == null) {
                this.right = new Node(value, pessoa);
                this.updateBalanceFactor();
                this.updateHeight();
                return true;
            } else {
                if (this.right.insert(value, pessoa)) {
                    this.updateBalanceFactor();
                    this.updateHeight();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean insert(long value, Pessoa pessoa) {
        long thisValueLong = Long.parseLong(this.value);
        if (value == thisValueLong) {
            return false;
        }
        if (value < thisValueLong) {
            if (this.left == null) {
                this.left = new Node(String.valueOf(value), pessoa);
                this.updateBalanceFactor();
                this.updateHeight();
                return true;
            } else {
                if (this.left.insert(value, pessoa)) {
                    this.updateBalanceFactor();
                    this.updateHeight();
                    return true;
                }
            }
        } else {
            if (this.right == null) {
                this.right = new Node(String.valueOf(value), pessoa);
                this.updateBalanceFactor();
                this.updateHeight();
                return true;
            } else {
                if (this.right.insert(value, pessoa)) {
                    this.updateBalanceFactor();
                    this.updateHeight();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean insert(Date value, Pessoa pessoa) {
        Date thisValueDate = DateFormat.parse(this.value);
        int compare = value.compareTo(thisValueDate);
        if (compare == 0) {
            return false;
        }
        if (compare < 0) {
            if (this.left == null) {
                this.left = new Node(DateFormat.format(value), pessoa);
                this.updateBalanceFactor();
                this.updateHeight();
                return true;
            } else {
                if (this.left.insert(value, pessoa)) {
                    this.updateBalanceFactor();
                    this.updateHeight();
                    return true;
                }
            }
        } else {
            if (this.right == null) {
                this.right = new Node(DateFormat.format(value), pessoa);
                this.updateBalanceFactor();
                this.updateHeight();
                return true;
            } else {
                if (this.right.insert(value, pessoa)) {
                    this.updateBalanceFactor();
                    this.updateHeight();
                    return true;
                }
            }
        }
        return false;
    }

    private void updateBalanceFactor() {
        int leftHeight = this.left == null ? -1 : this.left.height;
        int rightHeight = this.right == null ? -1 : this.right.height;
        this.balanceFactor = leftHeight - rightHeight;
        if (this.balanceFactor > 1 || this.balanceFactor < -1) {
            Node newNode = this.balanceTree();
            if (newNode.left == this) {
                this.updateNodeReference(newNode, true);
            } else if (newNode.right == this) {
                this.updateNodeReference(newNode, false);
            }
        }
    }

    private Node balanceTree() {
        if (this.balanceFactor > 1) {
            if (this.left.balanceFactor < 0) {
                this.left = this.left.rotateLeft();
            }
            return this.rotateRight();
        } else if (this.balanceFactor < -1) {
            if (this.right.balanceFactor > 0) {
                this.right = this.right.rotateRight();
            }
            return this.rotateLeft();
        }
        return this;
    }

    private Node rotateLeft() {
        Node newRoot = this.right;
        this.right = newRoot.left;
        newRoot.left = this;
        this.updateBalanceFactor();
        this.updateHeight();
        newRoot.updateBalanceFactor();
        newRoot.updateHeight();
        return newRoot;
    }

    private Node rotateRight() {
        Node newRoot = this.left;
        this.left = newRoot.right;
        newRoot.right = this;
        this.updateBalanceFactor();
        this.updateHeight();
        newRoot.updateBalanceFactor();
        newRoot.updateHeight();
        return newRoot;
    }

    private void updateNodeReference(Node newNode, boolean equalsLeftNode) {
        Node duplicateNode = this.duplicateNode();
        this.value = newNode.value;
        this.height = newNode.height;
        this.balanceFactor = newNode.balanceFactor;
        this.pessoa = newNode.pessoa;

        if (equalsLeftNode) {
            this.left = duplicateNode;
            this.right = newNode.right;
        } else {
            this.right = duplicateNode;
            this.left = newNode.left;
        }
    }

    private Node duplicateNode() {
        Node newNode = new Node(this.value, this.pessoa);
        newNode.left = this.left;
        newNode.right = this.right;
        newNode.balanceFactor = this.balanceFactor;
        newNode.height = this.height;
        return newNode;
    }

    private void updateHeight() {
        int leftHeight = this.left == null ? -1 : this.left.height;
        int rightHeight = this.right == null ? -1 : this.right.height;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }

    public Node delete(String value) {
        if (this.value.compareToIgnoreCase(value) == 0) {
            if (this.left == null && this.right == null) {
                return null;
            } else if (this.left == null) {
                return this.right;
            } else if (this.right == null) {
                return this.left;
            } else {
                String maxNode = this.left.maxValue();
                this.value = maxNode;
                this.left = this.left.delete(maxNode);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        } else if (value.compareToIgnoreCase(this.value) < 0) {
            if (this.left != null) {
                this.left = this.left.delete(value);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        } else {
            if (this.right != null) {
                this.right = this.right.delete(value);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        }
        return this;
    }

    public Node delete(long value) {
        long thisValueLong = Long.parseLong(this.value);
        if (thisValueLong == value) {
            if (this.left == null && this.right == null) {
                return null;
            } else if (this.left == null) {
                return this.right;
            } else if (this.right == null) {
                return this.left;
            } else {
                String maxNode = this.left.maxValue();
                this.value = maxNode;
                this.left = this.left.delete(Long.parseLong(maxNode));
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        } else if (value < thisValueLong) {
            if (this.left != null) {
                this.left = this.left.delete(value);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        } else {
            if (this.right != null) {
                this.right = this.right.delete(value);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        }
        return this;
    }

    public Node delete(Date value) {
        Date thisValueDate = DateFormat.parse(this.value);
        if (value.compareTo(thisValueDate) == 0) {
            if (this.left == null && this.right == null) {
                return null;
            } else if (this.left == null) {
                return this.right;
            } else if (this.right == null) {
                return this.left;
            } else {
                String maxNode = this.left.maxValue();
                this.value = maxNode;
                this.left = this.left.delete(DateFormat.parse(maxNode));
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        } else if (value.compareTo(thisValueDate) < 0) {
            if (this.left != null) {
                this.left = this.left.delete(value);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        } else {
            if (this.right != null) {
                this.right = this.right.delete(value);
                this.updateBalanceFactor();
                this.updateHeight();
                return this;
            }
        }
        return this;
    }

    private String maxValue() {
        if (this.right == null) {
            return this.value;
        } else {
            return this.right.maxValue();
        }
    }

    public String preOrder() {
        String result = "";
        result += this.value + " ";
        if (this.left != null) {
            result += this.left.preOrder();
        }
        if (this.right != null) {
            result += this.right.preOrder();
        }
        return result;
    }

    public String toString() {
        return "Node(" + this.value + ")" + " BF: " + this.balanceFactor + " H: " + this.height + " Left:[ " + (this.left == null ? "null" : this.left) + "] Right: [" + (this.right == null ? "null" : this.right) + "]";
    }

    public String personInfo(){
        return this.pessoa.toString();
    }
}
