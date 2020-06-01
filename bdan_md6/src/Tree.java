import javax.swing.tree.TreeNode;
import java.util.ArrayList;

public class Tree {
    private Node root;
    private int size;

    public Tree(Chunk rootData) {
        size =1;
        root = new Node();
        root.data = rootData;
        root.children = new ArrayList<Node>();
    }
    public Node getChildAt(int index){
        return root.getChildAt(index);
    }
    public Chunk getRootData(){
        return root.data;
    }
    public void build(ArrayList<Chunk> list){
        ArrayList<Node> toBuild = new ArrayList<>();
        for (Chunk value : list) {
            Node t = new Node();
            t.index =size;
            t.children = null;
            t.data = value;
            toBuild.add(t);
            size++;
        }
        build4aryTree(toBuild);
    }
    private void build4aryTree(ArrayList<Node> list){
        if(list.size()==1){
            root=list.get(0);
            return;
        }
        ArrayList<Node> parents= new ArrayList<>();
        for (int i = 0; i < list.size(); i+=4) {
            Node parent=new Node();
            //here compression function
            parent.index=size;
            size++;
            for (int j = 0; j < 4; j++) {
                list.get(i+j).parent=parent;
                parent.children.add(list.get(i+j));
            }
        parents.add(parent);
        }
        build4aryTree(parents);
    }
    public static class Node {
        private int index;
        private Chunk data;
        private Node parent;
        private ArrayList<Node> children = new ArrayList<>();;


        public Node getChildAt(int index) {
            return children.get(index);
        }
        public Node getParent(){
            return parent;
        }

        public int getChildCount() {
            int res=1;
            for (Node n:parent.children) {
                res+=n.getChildCount();
            }
            return res;
        }

        public void addChild(Node child){
            child.parent=this;
            this.children.add(child);
        }

        public int getIndex(TreeNode node) {
            return index;
        }

        public boolean isLeaf() {
            return children.isEmpty();
        }

    }
}