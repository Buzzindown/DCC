import java.util.Stack;

public class June82021 {
    // no email today :(
    // problem asks for 2nd largest node in a BST
    // in order traversal for second last node seems like an okay solution
    // will be o(n) since we have to do the whole tree
    
    Stack<Integer> stack = new Stack<Integer>();
    void solve(treeNode node){
        if(node == null){
            return;
        }
        // left
        if(node.left != null){
            solve(node.left);
        }
        // push our val
        stack.push(node.id);

        if(node.right != null){
            solve(node.right);
        }

    }

    public static void main(String[] args) {
        
        June82021 test = new June82021();

        treeNode a = new treeNode(1);
        treeNode b = new treeNode(2);
        treeNode root = new treeNode(3);
        treeNode c = new treeNode(4);
        treeNode d = new treeNode(5);

        root.left = b;
        b.left = a;
        root.right = c;
        c.right = d;


        test.solve(root);

        test.stack.pop();

        System.out.println(test.stack.peek());
    }
}

class treeNode {
    treeNode left = null;
    treeNode right = null;
    int id;

    treeNode(int id){
        this.id = id;
    }


}
