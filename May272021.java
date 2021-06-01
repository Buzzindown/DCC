

public class May272021 {

// Good morning! Here's your coding interview problem for today.

// This problem was asked by Google.

// Implement locking in a binary tree. A binary tree node can be locked or unlocked 

// only if all of its descendants or ancestors are not locked.

// Design a binary tree node class with the following methods:

// is_locked, which returns whether the node is locked

// lock, which attempts to lock the node. If it cannot be locked, 

// then it should return false. Otherwise, it should lock it and return true.

// unlock, which unlocks the node. If it cannot be unlocked, then it should return false. 

// Otherwise, it should unlock it and return true.

// You may augment the node to add parent pointers or any other property you would like.

// You may assume the class is used in a single-threaded program, so there 

// is no need for actual locks or mutexes. Each method should run in O(h), where h is the height of the tree.

// this seems okay, we need to do some tree searching to see if we can
// lock the node, what does it mean by all of it's descendants OR ancestors 
// are not locked, this seems to create an interesting situation
// where if you had a completely unlocked tree, and you lock a node
// then any node that's an ancestor or descendant will still be able to lock
// because it will have a stream to an end of the tree whther that's back to the root
// or out a leaf node.  but should you try and lock a node who has a locked ancestor
// AND descendant, you will be unable to lock/unlock it, you would first have to remove
// one of the outer locks and work your way into the inner locks so that the lock
// has either a unlocked line of ancestors or descendants 

// all we really need to do for lock/unlock is a search of the tree and since it's binary
// the descendant search will be o(h) and the vertical search will also still be o(h) 
// and it shouldnt be too hard to implement

    
}

class node{
    node parent;
    node left;
    node right;
    boolean isLocked;

    node(node par, node le, node ri){
        this.parent = par;
        this.left = le;
        this.right = ri;
        this.isLocked = false;
    }

    boolean searchUpLocked(node nextNode){
        if(nextNode == null){
            return true;
        }
        // if we're good and our ancestors are good
        if(nextNode.isLocked == false){
            if(searchUpLocked(nextNode.parent)){
                return true;
            }
        }
        // our node was bad, or one of our ancestors was locked
        return false;
    }

    boolean searchDownLocked(node nextNode){
        // if we're null
        if(nextNode == null){
            return true;
        }
        // if we're locked
        if(nextNode.isLocked == true){
            return false;
        }
        if(searchDownLocked(nextNode.left) && searchDownLocked(nextNode.right)){
            // if both sides return true, we can return true here 
            // since we already checked if we're locked
            return true;
        }

        return false;
    }

    boolean isLocked(){
        return this.isLocked;
    }

    boolean canToggle(){
        if(searchDownLocked(this.parent) || (searchDownLocked(this.left) && searchDownLocked(this.right))){
            return true;
        }
        return false;
    }

    boolean lock(){
        // if we have unlocked ancestors or descendants, and our current node is unlockef we can lock it
        if(this.canToggle() && !this.isLocked){
            this.isLocked = true;
            return true;
        }
        return false;
    }

    boolean unLock(){
        if(this.canToggle() && this.isLocked){
            this.isLocked = false;
            return true;
        }
        return true;
    }

}
