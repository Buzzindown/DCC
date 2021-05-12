public class May112021 {
    // Good morning! Here's your coding interview problem for today.

    // This problem was asked by Google.
    
    // A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
    
    // Given the root to a binary tree, count the number of unival subtrees.
    
    // For example, the following tree has 5 unival subtrees:

    // first thought is a recursive dive through the tree, if we hit an end node its 
    // a unival subtree. if the two subtree's are both unival tree's then we're at a unival tree
    // brute solution would be to recursively check if each subtree is all the same

    // how can we do this smarter .... 
    // We could try and store the values


    int value;
    May112021 left;
    May112021 right;

    public May112021(int value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // let's start with a function that searches a full tree
    int solution(May112021 root){
        int ret = checksubTree(root, root.value);
        if(root.left != null){
           
           ret += solution(root.left);
        }
        if(root.right != null){
           ret += solution(root.right);
        }
        return ret;
    }

    int checksubTree(May112021 root, int value){
        // if we're at a dead end we return our value
        if(root.left == null && root.right == null){
            if(root.value == value){
                return 1;
            }else{
                return 0;
            }
        }
        int left = 0;
        int right = 0;
        if(root.left != null){
            left = checksubTree(root.left, value);
        }else{
            left = 1;
        }

        if(root.right != null){
            right = checksubTree(root.right, value);
        }else{
            right = 1;
        }

        if(left == 1 && right == 1){
            return 1;
        }else{
            return 0;
        }
    }

    // time complexity would be bad, we're searching through the tree recursively
    // and on each node we're doing another recursive search, binary recursive search
    // takes n and we're effectively doing it on every node so we would get something
    // along the lines of n*n = n^2 which isn't good. Although I think it can easily
    // be improved upon

    // how could we make it better though. The issue with the current solution
    // is that we're double calculating a bunch of trees, and the issue with the last 
    // solution was that I was unable to come up with a way to return the number of tree's for 
    // a specific node while also checking if the node was a unival tree, this is what sort
    // of forced me into searching each subtree which is super inefficient
    // so our new solution should return the count of subtrees, as well as if 
    // the current tree is a subtree. If we can tell the hireup nodes that a node below it isn't
    // a unival tree, then we know the tree rooted at that node also is not a unival tree, and so on so forth
    // I made a pair class for passing around two values

    pair solution2(May112021 root){
        // end of tree
        pair retPair = new pair();
        // end nodes are unival trees
        if(root.left == null && root.right == null){
            retPair.numUnival = 1;
            retPair.isTree = 1;
            return retPair;
        }

        pair left = null;
        pair right = null; 
        int total = 0;
        // checkout our two subtrees
        if(root.left != null){
            left = solution2(root.left);
            total += left.numUnival; 
        }
        if(root.right != null){
            right = solution2(root.right);
            total += right.numUnival;
        }

        // check if we got both the trees

        if(left != null && right != null){
            if(left.isTree == 1 && right.isTree == 1){
                if(root.left.value != root.value){
                    retPair.numUnival = total;
                    retPair.isTree = 0;
                    return retPair;
                }
                if(root.left.value != root.value){
                    retPair.numUnival = total;
                    retPair.isTree = 0;
                    return retPair;
                }

                // both subtrees are univals
                retPair.numUnival = total + 1;
                retPair.isTree = 1;
                return retPair;
            }

        }

        retPair.numUnival = total;
        retPair.isTree = 0;
        return retPair;
   
    }

    // time complexity will be n now that we're not repeating any trees

    

    public static void main(String[] args){

        May112021 root = new May112021(0);
        May112021 l1 = new May112021(1);
        May112021 r1 = new May112021(0);
        May112021 l2 = new May112021(1);
        May112021 r2 = new May112021(0);
        May112021 l3 = new May112021(1);
        May112021 r3 = new May112021(1);
        May112021 l4 = new May112021(1);
        May112021 r4 = new May112021(1);

        root.left = l1;
        root.right = r1;
        r1.right = r2;
        r1.left = l2;
        l2.left = l3;
        l2.right = r3;
        l3.left = l4;
        l3.right = r4;    
        System.out.println("Solution 1:" + root.solution(root));
        pair p = root.solution2(root);
        System.out.println( "Solution 2:" + p.numUnival);



    }
}

class pair{
    public int numUnival;
    public int isTree;

    public pair(){
        numUnival = 0;
        isTree = 0;
    }

    public pair(int uv, int tree){
        numUnival = uv;
        isTree = tree;
    }
}
