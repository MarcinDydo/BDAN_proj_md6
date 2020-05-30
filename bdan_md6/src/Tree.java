import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class Tree<T> {
    private Node<T> root;
    private int size;

    public Tree(T rootData) {
        size=1;
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }
    public Node<T> getChildAt(int index){
        return root.getChildAt(index);
    }
    public T getRootData(){
        return root.data;
    }
    public T getChildAtData(int index){
        return root.getChildAt(index).data;
    }
    public int getChildCount() {
        return root.getChildCount();
    }
    public int getSize(){
        return size;
    }

    public static class Node<T> {
        private int index;
        private T data;
        private Node<T> parent;
        private ArrayList<Node<T>> children;

        public Node() {
        }

        public Node<T> getChildAt(int index) {
            return children.get(index);
        }
        public Node<T> getParent(){
            return parent;
        }

        public int getChildCount() {
            int res=1;
            for (Node<T> n:parent.children) {
                res+=n.getChildCount();
            }
            return res;
        }

        public void addChild(Node<T> child){
            child.parent=this;
            this.children.add(child);
        }

        public int getIndex(TreeNode node) {
            return index;
        }

        public boolean isLeaf() {
            if(children.isEmpty())return true;
            else return false;
        }

    }
}