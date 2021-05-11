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
    // if we think about a tree and it's various subtrees
    // suppose we had
    //                          1
    //                  1               1
    //             1        1       1       1
    //         0
    


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
        if(ret == 1){
            System.out.println("made it!");
        }
        System.out.println(root.value);
        if(root.left != null){
           
            solution(root.left);
        }
        if(root.right != null){
            solution(root.right);
        }
        return 0;
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

        System.out.println(root.solution(root));



    }
}
