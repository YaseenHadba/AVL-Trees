 // Names : Yaseen Hadba + Mohammed Mahamid
public class AVLTree {
	private AVLNode root;// pointer to the node
	private final AVLNode virtual=new AVLNode();//virtual node

    /**
     * This constructor creates an empty AVLTree.
     */
	//o(1)
    public AVLTree(){ 
        this.root=null;
    }
    //o(log(n))
    public AVLNode find(int k) {
        //Search for node k -  until node with key k is found (or virtual returned if k not in tree).
        	if (this.empty()) {
        		return null;
        	}
        	AVLNode currNode = this.root;
        	while (currNode != null){
            	if (k<currNode.getKey()) {
            		currNode = currNode.getLeft();
            	}
            	else if (k>currNode.getKey()) {
            		currNode = currNode.getRight();
            	}
            	else if (k==currNode.getKey()) {
            		return currNode;
            	}
            }
        	return this.virtual;
        }

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    //o(1)
    public boolean empty() {
       return this.root==null;
    }

    /**
     * public boolean search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    //o(log(n))
    public Boolean search(int k) {
        AVLNode curr=root; //search for the node with the key k 
        while(curr!=null)
        {
        	if(curr.getKey()==k) // we save the key when we have it
        	{
        		return curr.getValue();
        	}
        	if(k<curr.getKey())
        	{
        		curr=curr.getLeft();
        	}
        	if(k>curr.getKey())
        	{
        		curr=curr.getRight();
        	}
        }
        if(curr!=null)
        {
        return curr.getValue();
        }
        return null;// if nothing found we return null
    }
    int max(int a, int b) { //comparator to compare between two Integers
        return (a > b) ? a : b;
    }
    //o(1)- we just changing pointers
    public void RotateLeft(AVLNode node)
    {
       	AVLNode tmp=node.getRight();
    	node.setRight(tmp.getLeft());
    	if(tmp.getLeft()!=null) // if tmp has a left subtree
    	{
    		tmp.getLeft().setParent(node);
    	}
    	tmp.setParent(node.getParent()); // we change pointers
    	if(node.getParent()==null) // if the node was the root so we change the root pointer
    	{
    		this.root=tmp;
    	}
    	else if(node==node.getParent().getLeft()) // if the node is a left child
    	{
    		node.getParent().setLeft(tmp);
    	}
    	else // the node is a right child
    	{
    		node.getParent().setRight(tmp);
    	}
    	tmp.setLeft(node);// change pointers
    	node.setParent(tmp);
    	node.getParent().setSize(node.getSize());// we update the size 
    	node.getParent().setXor(node.getXor());
    	if(node.getRight()!=null && node.getLeft()!=null) // the node has two children
    	{
    		node.setSize(node.getRight().getSize()+node.getLeft().getSize()+1); // set the size to be the sum of the two sizes of the subtrees +1
    		node.setXor(node.getRight().getXor()+node.getLeft().getXor()+1);
    		
    	}
    	else if(node.getRight()!=null) // if the node has only right child we change the size to be the size of the right subtree +1
    	{
    		node.setSize(node.getRight().getSize()+1);
    		if(node.getValue()==true)
    			node.setXor(node.getRight().getXor()+1);
    		else node.setXor(node.getRight().getXor());
    	}
    	else if(node.getLeft()!=null)// if the node has only left child we change the size to be the size of the left subtree +1
    	{
    		node.setSize(node.getLeft().getSize()+1);
    		if(node.getValue()==true)
    			node.setXor(node.getLeft().getXor()+1);
    		else node.setXor(node.getLeft().getXor()+1);
    	}
    	else // the node is leaf
    	{
    		node.setSize(1);
    		node.setXor(1);
    	}
    	node.setHeight(node.CalcHeight());
    	node.getParent().setHeight(node.CalcHeight()); 	
    }
    //o(1) - this case is the same with the LEFTRotation but changing the right and the left. 
    public void RotateRight(AVLNode node)
    {
    	AVLNode tmp=node.getLeft();
    	node.setLeft(tmp.getRight());
    	if(tmp.getRight()!=null)
    	{
    		tmp.getRight().setParent(node);
    	}
    	tmp.setParent(node.getParent());
    	if(node.getParent()==null)
    	{
    		this.root=tmp;
    	}
    	else if(node==node.getParent().getLeft())
    	{
    		node.getParent().setLeft(tmp);
    	}
    	else 
    	{
    		node.getParent().setRight(tmp);
    	}
    	tmp.setRight(node);
    	node.setParent(tmp);
    	node.getParent().setSize(node.getSize());
    	
    	if(node.getRight()!=null && node.getLeft()!=null)
    	{
    		node.setSize(node.getRight().getSize()+node.getLeft().getSize()+1);
    		node.setXor(node.getRight().getXor()+node.getLeft().getXor()+1);
    	}
    	else if(node.getRight()!=null)
    	{
    		node.setSize(node.getRight().getSize()+1);
    		node.setXor(node.getRight().getXor()+1);
    	}
    	else if(node.getLeft()!=null)
    	{
    		node.setSize(node.getLeft().getSize()+1);
    		node.setXor(node.getLeft().getXor()+1);
    	}
    	else
    	{
    		node.setSize(1);
    		if(node.getValue()==true)
    			node.setXor(1);
    		node.setXor(0);
    	}
    	node.setHeight(node.CalcHeight());
    	node.getParent().setHeight(node.CalcHeight()); 	
    }

    /**
     * public int insert(int k, boolean i)
     * <p>
     * inserts an item with key k and info i to the AVL tree.
     * the tree must remain valid (keep its invariants).
	 * returns the number of nodes which require rebalancing operations (i.e. promotions or rotations).
	 * This always includes the newly-created node.
     * returns -1 if an item with key k already exists in the tree.
     */
  //o(log(n))
    public int insert(int k, boolean i) {
        AVLNode node=new AVLNode(k,i); // the node we want to add
        if(this.root==null) // if we have empty tree the root has to be the new inserted node
        {
        	this.root=node;
        	return 0;
        }
        AVLNode parent=null;
        AVLNode tmp=this.root;
        while(tmp!=null)// we check if there is a node with the key k ,if yes we return -1 
        {
        	parent=tmp;
        	if(tmp.getKey()==k)
        	{
        		return -1;
        	}
        	if(tmp.getKey()>k)
        	{
        		tmp=tmp.getLeft();
        	}
        	else if(tmp.getKey()<k)
        	{
        		tmp=tmp.getRight();
        	}
        }
        // at this point we have the parent of the new node so we know where to exactly add the node
        if(parent.getKey()<k)// we check if the node should be a left child or a right child 
        {
        	parent.setRight(node);
        	node.setParent(parent);
        	
        }
        else
        {
        	parent.setLeft(node);
        	node.setParent(parent);
        }
        tmp=parent;
        while(tmp!=null)// update the size of the parent of the new inserted node all the way up to the root
        {
        	if (node.getValue()==true)
        	{
        	tmp.setXor(tmp.getXor()+1);
        	}
        	tmp.setSize(tmp.getSize()+1);
        	tmp=tmp.getParent();
        }
        int balance; // the balance factor 
        int cnt=0; // counter to count the number of rotations
        while(parent!=null)// now we rotate from the parent of the inserted node to the root
        {
        	parent.setHeight(parent.CalcHeight()); // update the height
        	balance=parent.getBF();// get the balance factor
        	if(balance <=1 && balance >=-1)
        	{
        		parent=parent.getParent();
        	}
        	else
        	{
        		if(balance==-2 && parent.getRight().getBF()==-1)
        		{
        			this.RotateLeft(parent);
        			cnt+=1;
        		}
        		else if(balance==-2 && parent.getRight().getBF()==1)
        		{
        			this.RotateRight(parent.getRight());
        			this.RotateLeft(parent);
        			cnt+=2;			
        		}
        		else if(balance==2 && parent.getLeft().getBF()==-1)
        		{
        			this.RotateLeft(parent.getLeft());
        			this.RotateRight(parent);
        			cnt+=2;
        			
        		}
        		else 
        		{
        		this.RotateRight(parent);
        		cnt+=1;
        		}
        		
        	}
        	
        }
        
        return cnt;
    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * returns the number of nodes which required rebalancing operations (i.e. demotions or rotations).
     * returns -1 if an item with key k was not found in the tree.
     */
    //o(log(n))
    public int delete(int k) {
        if (this.empty()) // empty tree ---> there no items in the tree
        {
            return -1;
        }
        if (this.root.getRight()==null && this.root.getLeft()==null) // CASE1:the tree is a single node
        {
            if (this.root.getKey() == k) { // check if the key of the node is k , if yes we delete the node else we return -1
                this.root = null;
                return 0;
            }
            return -1;
        }
        if (this.root.getRight() == null && this.root.getLeft() != null) // CASE2:the root has left son but no right son AND The root key is k 
        {
            if (this.root.getKey() == k) {
                this.root = this.root.getLeft();
                this.root.setParent(null);
                return 0;
            }
        }
        if (this.root.getLeft() == null && this.root.getRight() != null) //CASE3 : the root has no left son but right son AND The root key is k 
        {
            if (this.root.getKey() == k) {
                this.root = this.root.getRight();
                this.root.setParent(null);
                return 0;
            }
        }
        AVLNode tmp=this.root;
        AVLNode succ;
        while (tmp != null) // search for the node with the key k
        { 
            if (tmp.getKey() == k) {
                break; 
            }
            else if (k < tmp.getKey()) {
                tmp= tmp.getLeft();
            }
            else {
            	tmp=tmp.getRight();
            }
        }

        if (tmp == null) //  there is no item with the key k we return -1
        { 
            return -1;
        }

        //on this stage we have the node we want to delete
        AVLNode tmp2;

        if (tmp.getRight()==null && tmp.getLeft()==null) //Case1- the node we want to delete is leaf
        {
            tmp2 = tmp.getParent();
            if (tmp2 != null) {
				if (tmp2.getRight() == tmp) { //if the node we want to delete is the right son of its parent
                    tmp2.setRight(null);
                }
                if (tmp2.getLeft() ==tmp) { //if the node wer want to delete is the left son of its parent
                    tmp2.setLeft(null);
                }
            }
            else // the node is the root 
            { 
                this.root = null;
            }
        }
        else if ((tmp.getLeft() != null) && (tmp.getRight() == null)) //Case 2 - the node we are trying to delete has no right son
        {
            tmp2 = tmp.getParent();
            if (tmp2 != null) {
                if (tmp2.getRight() == tmp) // if the node is a right son
                { 
                    tmp2.setRight(tmp.getLeft());
                }
                if (tmp2.getLeft() == tmp) // if the node is a left son
                { 
                    tmp2.setLeft(tmp.getLeft());
                }
                tmp.getLeft().setParent(tmp2);
            } else {
                this.root = null;
            }

        }
        else if ((tmp.getLeft() == null) && (tmp.getRight() != null)) //the node has no left son 
        {
            tmp2 = tmp.getParent();
            if (tmp2 != null) {
                if (tmp2.getRight() == tmp) { // if node is a right son 
                    tmp2.setRight(tmp.getRight());
                }
                if (tmp2.getLeft() == tmp) { // if node is a left son
                    tmp2.setLeft(tmp.getRight());
                }
                tmp.getRight().setParent(tmp2);
            } else {
                this.root = null;
            }
        }
        else // Case 3, the node has two children
        {
            // in this case we need to delete the successor physically and replace it with the node we are trying to delete
            succ = successor(tmp);
            // there are two cases :
            // 1) the successor is the right child of the node that we want to delete .
            // 2) the successor is in the left subtree of the right child of the node that we want to delete.

            if (succ != tmp.getRight()) // case 2
            {
                tmp2 = succ.getParent(); // here the path up to the root will start (the path where we update the size/height and do rotations)
                tmp2.setLeft(succ.getRight()); //adjusting pointers in order to delete the successor physically
                if (succ.getRight() != null) // adjusting pointers in case the right child isn't null
                {
                    succ.getRight().setParent(succ.getParent());
                }
                succ.setParent(tmp.getParent()); // adjusting more pointers in order to put the successor in the place of the node we wanted to delete
                succ.setRight(tmp.getRight());
                succ.setLeft(tmp.getLeft());
                succ.getLeft().setParent(succ);
                succ.getRight().setParent(succ);
                if (succ.getParent() != null) { // if the the node we were trying to delete was the root
                    if (succ.getParent().getLeft() == tmp) { //x was a left child so we set succ to be also a left child
                        succ.getParent().setLeft(succ);
                    }
                    else { // x was a right child so we set succ to be also a right child
                        succ.getParent().setRight(succ);
                    }
                }
                else { // if we try to delete the root of the tree
                    this.root = succ;
                }
                succ.setHeight(tmp.getHeight()); // we set the height of succ to be the height of x because we put succ in the place of x
                succ.setSize(tmp.getSize()); // we set the size of succ to be the size of x because we put succ in the place of x
                succ.setXor(tmp.getXor());
            }
            
            else { // case 2 - this means that succ = node.getright
                succ.getParent().setRight(succ.getRight()); // adjusting pointers in order to delete the successor physically
                if (succ.getRight() != null) {
                    succ.getRight().setParent(succ.getParent());
                }
                succ.setLeft(tmp.getLeft()); // adjusting more pointers in order to put succ in the place of the node
                succ.setRight(tmp.getRight());
                succ.setParent(tmp.getParent());
                succ.getLeft().setParent(succ);
                if (succ.getRight() != null) {
                    succ.getRight().setParent(succ);
                }
                if (succ.getParent() != null) {
                    if (succ.getParent().getLeft() == tmp) { // node was a left child so we set succ to be also a left child
                        succ.getParent().setLeft(succ);
                    }
                    else { // the node was a right child so we set succ to be also a right child
                        succ.getParent().setRight(succ);
                    }
                }
                else { // if we try to delete the root of the tree
                    this.root = succ;
                }
                succ.setHeight(tmp.getHeight()); // we set the height of succ to be the height of the node because we put succ in the place of x
                succ.setSize(tmp.getSize()); // we set the size of succ to be the size of the node because we put succ in the place of x
                succ.setXor(tmp.getXor());
                
                tmp2 = succ; // because the successor was the right child of the node that means that the path that goes up to the root in order to update the
                // height/size and do rotations should start at the node succ after we put it in the place of x
            }
        }
        // we deleted the node from the tree therefore we set it's pointers to null
        tmp.setLeft(null);
        tmp.setRight(null);
        tmp.setParent(null);
        AVLNode tmp3 = tmp2;
        while(tmp3!=null) // a loop that goes from tmp2 to the root and subtracts 1 from the sizes of the nodes
        {
        	
            tmp3.setSize(tmp3.getSize() - 1); // size of all sub trees on the path to the root is smaller by one because of the deletion
            if(tmp.getValue()==true)
            {
            	if(tmp3.getParent()!=null)
            		tmp3.getParent().setXor(tmp3.getParent().getXor()-1);
            }
            tmp3=tmp3.getParent();
        }
        int BF;
        int cnt = 0; // a counter of the number of rotations
        while(tmp2!=null) // a loop that goes from y to the root and changes the heights and does rotations if needed
        {
            tmp2.setHeight(tmp2.CalcHeight()); // updates the height if needed
            BF = tmp2.getBF(); // calculates the balance factor of the node
            if (BF <= 1 && BF >= -1) // a "good" balance factor
            {
                tmp2 = tmp2.getParent();
            }
            else // a "bad" balance factor therefore we have 6 cases of rotations
            {
                if((BF == -2)&&(tmp2.getRight().getBF()==-1))
                {
                    this.RotateLeft(tmp2);
                    cnt++;
                }
                else if ((BF == -2)&&(tmp2.getRight().getBF()==0))
                {
                    this.RotateLeft(tmp2);
                    cnt++;
                }
                else if((BF == -2)&&(tmp2.getRight().getBF()==1))
                {
                    this.RotateRight(tmp2.getRight());
                    this.RotateLeft(tmp2);
                    cnt=cnt+2;
                }
                else if((BF == 2)&&(tmp2.getLeft().getBF()==-1))
                {
                    this.RotateLeft(tmp2.getLeft());
                    this.RotateRight(tmp2);
                    cnt=cnt+2;
                }
                else if ((BF == 2)&&(tmp2.getLeft().getBF()==0))
                {
                    this.RotateRight(tmp2);
                    cnt++;
                }
                else {
                    this.RotateRight(tmp2);
                    cnt++;
                }
            }
        }
        return cnt; // returns the number of rotations done
    }

    

    /**
     * public Boolean min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     */
    public Boolean min() {
    	if(this.root==null)
    	{
    		return null;
    	}
    	AVLNode tmp=this.root;
    	while(tmp.getLeft()!=null)
    	{
    		tmp=tmp.getLeft();
    	}
        return tmp.getValue(); 
    }

    /**
     * public Boolean max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     */
    public Boolean max() {
    	if(this.root==null)
    	{
    		return null;
    	}
    	AVLNode tmp=this.root;
    	while(tmp.getRight()!=null)
    	{
    		tmp=tmp.getRight();
    	}
        return tmp.getValue(); 
    }

    /**
     * public int[] keysToArray()
     * <p>
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     */
    public void MakeArray1(int[] arr,AVLNode node,int i)// function helps with KeystoArray()
    {
    	if(node==null)
    	{
    		return;
    	}
    	MakeArray1(arr,node.getLeft(),i);
    	int s;
    	if(node.getLeft()==null)
    	{
    		s=0;
    	}
    	else {
    		s=node.getLeft().getSize();
    	}
    	arr[s+i]=node.getKey();
    	MakeArray1(arr,node.getRight(),s+i+1);
    }
    //o(n)
    public int[] keysToArray() {
    	if(this.root==null)
    	{
    		return new int[0];
    	}
        int[] arr = new int[this.size()]; 
        MakeArray1(arr,this.root,0) ;
        return arr;              
    }

    /**
     * public boolean[] infoToArray()
     * <p>
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     */
    public void MakeArray2(boolean[] arr,AVLNode node,int i)//function helps with infoToArray (in order traversal)
    {
    	if(node==null)
    	{
    		return;
    	}
    	MakeArray2(arr,node.getLeft(),i);
    	int s;
    	if(node.getLeft()==null)
    	{
    		s=0;
    	}
    	else {
    		s=node.getLeft().getSize();
    	}
    	arr[s+i]=node.getValue();
    	MakeArray2(arr,node.getRight(),s+i+1);
    }
    //o(n)
    public boolean[] infoToArray() {
    	if(this.root==null) {
    		return null;
    	}
    	boolean[] arr=new boolean[this.size()];
    	MakeArray2(arr,this.root,0);
    	return arr;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     */
    public int size() {
        if(this.root==null)
        {
        	return 0;
        }
        return this.root.getSize();
    }

    /**
     * public int getRoot()
     * <p>
     * Returns the root AVL node, or null if the tree is empty
     */
    public AVLNode getRoot() {
       return this.root;
    }

    /**
     * public boolean prefixXor(int k)
     *
     * Given an argument k which is a key in the tree, calculate the xor of the values of nodes whose keys are
     * smaller or equal to k.
     *
     * precondition: this.search(k) != null
     *
     */
    //o(log(n))
    public boolean prefixXor(int k){ // had some problems with the code
    	/*AVLNode node =this.find(k);
    	AVLNode y=node;
    	int r = 0;
    	while(y!=null)
    	{
    		if(y.getValue()==true) r+=1;
    		if(y==this.root)
    		{	
    			if(y.getLeft()!=null)
    			{
    				r+=y.getLeft().getXor();
    				if(y.getLeft().getValue()==true)
    					r+=1;
    				
    			}
    			break;
    		}
    		if(y.getParent().getValue()==true) r+=1;
    		if(y==y.getParent().getRight())
    		{
    			if(y.getParent().getLeft()!=null)
    			{
    				r+=y.getParent().getLeft().getXor();
    				if(y.getLeft()!=null)
    				{
    					r+=y.getLeft().getXor();
    					if(y.getLeft().getValue()==true)
    					{
    						r+=1;
    					}
    				}
    				if(y.getParent().getLeft().getValue()==true)
    					r+=1;
    			}
    		}
    		else if(y==y.getParent().getLeft())
    		{
    			if(y.getLeft()!=null)
    			{
    				r+=y.getLeft().getXor();
    				if(y.getLeft().getValue()==true)
    				{
    					r+=1;
    				}
    			}
    		}
    		y=y.getParent();	
    	}
    	if(r%2==0) return false;
    	return true;*/
    	return this.succPrefixXor(k);
  
        }
        
    
    /**
     * public AVLNode successor
     *
     * given a node 'node' in the tree, return the successor of 'node' in the tree (or null if successor doesn't exist)
     *
     * @param node - the node whose successor should be returned
     * @return the successor of 'node' if exists, null otherwise
     */
    //o(log(n))
    public AVLNode successor(AVLNode node){
        AVLNode succ=node;
        if(node.getRight()!=null)// if node has right son
        {
        	node=node.getRight();
        	while(node!=null)// we go all the way to the left of the right son and this will be the successor
        	{
        		succ=node;
        		node=node.getLeft();
        	}
        	return succ;
        }
        succ=node.getParent();
        while(succ!=null && node==succ.getRight())// we look for the first node with a right son till we get to null
        {
        	node=succ;
        	succ=node.getParent();
        }
        return succ;
    }

    /**
     * public boolean succPrefixXor(int k)
     *
     * This function is identical to prefixXor(int k) in terms of input/output. However, the implementation of
     * succPrefixXor should be the following: starting from the minimum-key node, iteratively call successor until
     * you reach the node of key k. Return the xor of all visited nodes.
     *
     * precondition: this.search(k) != null
     */
    public AVLNode getMin(AVLNode node) // returns the node with the minKey
    {
    	AVLNode tmp=node;
    	while(tmp.getLeft()!=null)
    	{
    		tmp=tmp.getLeft();
    	}
    	return tmp;	
    }
    public AVLNode getMax(AVLNode node)// returns the node with the maxKey

    {
    	AVLNode tmp=node;
    	while(tmp.getRight()!=null)
    	{
    		tmp=tmp.getRight();
    	}
    	return tmp;	
    }
    //o(nlogn)
    public boolean succPrefixXor(int k){ // we counts the number of trues in the nodes with smaller keys than k and then check if the counter is even or odd and return false and true  respectively
    	 int count=0;
         AVLNode tmp=this.getMin(this.root);
         while (tmp.getKey()<=k)
         {
        	 
             if (tmp.getValue()==true)
             {
                 count++;
             }
             if(tmp==this.getMax(this.root))
             {
            	 break;
             }	
             
             tmp=successor(tmp);
         }
         if ( count % 2==0)
         {
         return false;
         }
         else {
             return true;
         }
    }
   


    /**
     * public class AVLNode
     * <p>
     * This class represents a node in the AVL tree.
     * <p>
     * IMPORTANT: do not change the signatures of any function (i.e. access modifiers, return type, function name and
     * arguments. Changing these would break the automatic tester, and would result in worse grade.
     * <p>
     * However, you are allowed (and required) to implement the given functions, and can add functions of your own
     * according to your needs.
     */
    public class AVLNode {
    	private int key;
    	private Boolean info;
    	private AVLNode leftNode;
    	private AVLNode rightNode;
    	private AVLNode parent;
    	private int size;
    	private int height;
    	private int xor;
    	
    	public AVLNode()
    	{
    		this.key=-1;
    		this.height=-1;
    		this.info=null;
    		
    	}
    	public AVLNode(int key,Boolean info)
    	{
    		this.key=key;
    		this.info=info;
    		this.size=1;
    		this.height=0;
    		this.xor=0;
    	}
    	public int getXor() {
    		return this.xor;
    	}
    	public void setXor(int x) {
    		this.xor=x ;
    	}
        //returns node's key (for virtual node return -1)
        public int getKey() {
           return this.key;
        }

        //returns node's value [info] (for virtual node return null)
        public Boolean getValue() {
            return this.info;
        }

        //sets left child
        public void setLeft(AVLNode node) {
           this.leftNode=node;
        }

        //returns left child
		//if called for virtual node, return value is ignored.
        public AVLNode getLeft() {
          return this.leftNode;
        }

        //sets right child
        public void setRight(AVLNode node) {
            this.rightNode=node;
        }

        //returns right child 
		//if called for virtual node, return value is ignored.
        public AVLNode getRight() {
           return this.rightNode;
        }

        //sets parent
        public void setParent(AVLNode node) {
           this.parent=node;
        }

        //returns the parent (if there is no parent return null)
        public AVLNode getParent() {
            return this.parent;
        }

        // Returns True if this is a non-virtual AVL node
        public boolean isRealNode() {
           if(this.key==-1 && this.height==-1 && this.info==null)
           {
        	   return false;
           }
           return true;
        }

        // sets the height of the node
        public void setHeight(int height) {
            this.height=height;
        }

        // Returns the height of the node (-1 for virtual nodes)
        public int getHeight() {
            return this.height;
        }
        public void setSize(int size)
        {
        	this.size=size;
        }
        public int getSize()
        {
        	return this.size;
        }
        public int getBF() // computes the balance factor of a nude  
        {
        	int Lh;
        	int Rh;
        	if(this.leftNode==null)
        	{
        		Lh=-1;
        	}
        	else 
        	{
        		Lh=this.leftNode.height;
        	}
        	if(this.rightNode==null)
        	{
        		Rh=-1;	
        	}
        	else
        	{
        		Rh=this.rightNode.height;
        	}
        	return Lh-Rh;
        	
        }
        public int CalcHeight() // computes the new height of a node , it returns the max height between the height of its right son and its left son and then add 1 
        {
        	int Lh;
        	int Rh;
        	if(this.leftNode==null)
        	{
        		Lh=-1;
        	}
        	else 
        	{
        		Lh=this.leftNode.height;
        	}
        	if(this.rightNode==null)
        	{
        		Rh=-1;	
        	}
        	else
        	{
        		Rh=this.rightNode.height;
        	}
        	return Math.max(Rh,Lh)+1;
        	
        }
    }

}
