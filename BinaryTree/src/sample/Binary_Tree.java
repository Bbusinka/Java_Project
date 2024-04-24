package sample;
import java.util.ArrayList;
import java.util.Objects;


public class Binary_Tree {
    public ArrayList<String> saveValForDisplay = new ArrayList<>();
    public Node root;

    public Binary_Tree() {

        setRoot(null);
    }


    public void insert(String data,String type) {
        Node newNode = new Node(data);
        if (root == null) {
            setRoot(newNode);
            return;
        }

        Node current = root;
        Node parent = null;
        if (Objects.equals(type, "Integer")){
            while (true) {
                parent = current;
                if (Integer.parseInt(newNode.data) < Integer.parseInt(current.data)) {
                    if (current.left == null) {
                        parent.left = newNode;
                        return;
                    } else
                        current = current.left;
                } else {
                    if (current.right == null) {
                        parent.right = newNode;
                        return;
                    } else
                        current = current.right;
                }

            }
        }else if(Objects.equals(type, "Double")){
            while (true) {
                parent = current;
                if (Double.parseDouble(newNode.data) < Double.parseDouble(current.data)) {
                    if (current.left == null) {
                        parent.left = newNode;
                        return;
                    } else
                        current = current.left;
                } else {
                    if (current.right == null) {
                        parent.right = newNode;
                        return;
                    } else
                        current = current.right;
                }

            }
        }else {
            while (true) {
                parent = current;
                if (newNode.data.compareTo(current.data) < 0) {
                    if (current.left == null) {
                        parent.left = newNode;
                        return;
                    } else
                        current = current.left;
                } else {
                    if (current.right == null) {
                        parent.right = newNode;
                        return;
                    } else
                        current = current.right;
                }
            }
        }
    }

    public void deleteTree(Node root) {
        if (root == null)
            return;

        deleteTree(root.getRight());
        deleteTree(root.getLeft());
        setRoot(null);
    }

    public int maxDepth(Node root) {
        if (root == null)
            return 0;
        else {
            int lDepth = maxDepth(root.getLeft());
            int rDepth = maxDepth(root.getRight());

            return lDepth > rDepth ? (lDepth + 1) : (rDepth + 1);
        }

    }

    public void inOrder(Node root) {
        if (root == null)
            return;

        inOrder(root.getLeft());
        saveDisplayType(root.getData());
        inOrder(root.getRight());
    }

    public void preOrder(Node root) {
        if (root == null)
            return;

        saveDisplayType(root.getData());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    public void postOrder(Node root) {
        if (root == null)
            return;

        postOrder(root.getLeft());
        postOrder(root.getRight());
        saveDisplayType(root.getData());
    }

    public void print(Node root) {
        if (root == null)
            return;

        print(root.getLeft());
        System.out.println(root.getData());
        print(root.getRight());

    }


    public void saveDisplayType(String value) {
        saveValForDisplay.add(value);
    }


    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public class Node {
        String data;
        Node right, left;

        public Node(String data) {
            this.data = data;
            left = null;
            right = null;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public String getData() {
            return data;
        }
    }


}
