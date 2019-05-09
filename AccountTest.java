/*
 *  Java Program to Implement AVL Tree
 */
import java.lang.Comparable; 
 import java.util.Scanner;
 import java.io.*;
 
 /* Class AVLNode */
 class AVLNode
 {    
     AVLNode leftNode, rightNode;
     Account data;
     int height;
 
     /* Constructor */
     public AVLNode()
     {
         leftNode = null;
         rightNode = null;
         data = null;
         height = 0;
     }
     /* Constructor */
     public AVLNode(Account n)
     {
         leftNode = null;
         rightNode = null;
         data = n;
         height = 0;
     }     
 }
 
 /* Class AVLTree */
 class AVLTree 
 {
     private AVLNode root;     
 
     /* Constructor */
     public AVLTree()
     {
         root = null;
     }
     /* Function to check if tree is empty */
     public boolean isEmpty()
     {
         return root == null;
     }
     /* Make the tree logically empty */
     public void makeEmpty()
     {
         root = null;
     }
     /* Function to insert data */
     public void insert(Account data)
     {
         root = insert(data, root);
     }
     /* Function to get height of node */
     private int height(AVLNode t )
     {
         return t == null ? -1 : t.height;
     }
     /* Function to max of leftNode/rightNode node */
     private int max(int lhs, int rhs)
     {
         return lhs > rhs ? lhs : rhs;
     }
     /* Function to insert data recursively */
     private AVLNode insert(Account x, AVLNode t)
     {
         if (t == null)
             t = new AVLNode(x);
         else 
			 if (x.compareTo(t.data)<0)
			 {
				 t.leftNode = insert( x, t.leftNode );
				 if( height( t.leftNode ) - height( t.rightNode ) == 2 )
					 if( x.compareTo(t.leftNode.data)<0 )
						 t = rotateWithleftNodeChild( t );
					 else
						 t = doubleWithleftNodeChild( t );
			 }
			 else 
				 if(x.compareTo(t.data)>0 )
				 {
					 t.rightNode = insert( x, t.rightNode );
					 if( height( t.rightNode ) - height( t.leftNode ) == 2 )
						 if( x.compareTo(t.rightNode.data)<0)
							 t = rotateWithrightNodeChild( t );
						 else
							 t = doubleWithrightNodeChild( t );
				 }
				 else
				   ;  // Duplicate; do nothing
         t.height = max( height( t.leftNode ), height( t.rightNode ) ) + 1;
         return t;
     }
     /* Rotate binary tree node with leftNode child */     
     private AVLNode rotateWithleftNodeChild(AVLNode k2)
     {
         AVLNode k1 = k2.leftNode;
         k2.leftNode = k1.rightNode;
         k1.rightNode = k2;
         k2.height = max( height( k2.leftNode ), height( k2.rightNode ) ) + 1;
         k1.height = max( height( k1.leftNode ), k2.height ) + 1;
         return k1;
     }
 
     /* Rotate binary tree node with rightNode child */
     private AVLNode rotateWithrightNodeChild(AVLNode k1)
     {
         AVLNode k2 = k1.rightNode;
         k1.rightNode = k2.leftNode;
         k2.leftNode = k1;
         k1.height = max( height( k1.leftNode ), height( k1.rightNode ) ) + 1;
         k2.height = max( height( k2.rightNode ), k1.height ) + 1;
         return k2;
     }
     /**
      * Double rotate binary tree node: first leftNode child
      * with its rightNode child; then node k3 with new leftNode child */
     private AVLNode doubleWithleftNodeChild(AVLNode k3)
     {
         k3.leftNode = rotateWithrightNodeChild( k3.leftNode );
         return rotateWithleftNodeChild( k3 );
     }
     /**
      * Double rotate binary tree node: first rightNode child
      * with its leftNode child; then node k1 with new rightNode child */      
     private AVLNode doubleWithrightNodeChild(AVLNode k1)
     {
         k1.rightNode = rotateWithleftNodeChild( k1.rightNode );
         return rotateWithrightNodeChild( k1 );
     }    
     /* Functions to count number of nodes */
     public int countNodes()
     {
         return countNodes(root);
     }
     private int countNodes(AVLNode r)
     {
         if (r == null)
             return 0;
         else
         {
             int l = 1;
             l += countNodes(r.leftNode);
             l += countNodes(r.rightNode);
             return l;
         }
     }
     /* Functions to search for an element */
    /* public boolean search(Account val)
     {
         return search(root, val);
     }
     */
     /* Function for inorder traversal */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(AVLNode r)
     {
         if (r != null)
         {
             inorder(r.leftNode);
             System.out.print(r.data +" ");
             inorder(r.rightNode);
         }
     }
     /* Function for preorder traversal */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(AVLNode r)
     {
         if (r != null)
         {
             System.out.print(r.data +" ");
             preorder(r.leftNode);             
             preorder(r.rightNode);
         }
     }
     /* Function for postorder traversal */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(AVLNode r)
     {
         if (r != null)
         {
             postorder(r.leftNode);             
             postorder(r.rightNode);
             System.out.print(r.data +" ");
         }
     }    
	
	
	public boolean search( int key)
     {
        AVLNode current=root;
      
      boolean isleftNodeChild = true;
			
		 while(current.data.Id != key)        // search for node
         {
			 if(key < current.data.Id)         // go leftNode?
				{
					isleftNodeChild = true;     
					current = current.leftNode;
				}
			 else                            // or go rightNode?
				{
				 isleftNodeChild = false;    
				 current = current.rightNode;
				}
			 if(current == null)             // end of the line,
				return false;                // didn't find it
         }
         return true;
     }
	
	public boolean delete(int key) // delete node with given key
      {                           // (assumes non-empty list)
      AVLNode current = root;      
	  AVLNode parent = root;
      boolean isleftNodeChild = true;

      while(current.data.Id != key)        // search for node
         {
			 parent = current;
			 if(key < current.data.Id)         // go leftNode?
				{
					isleftNodeChild = true;     
					current = current.leftNode;
				}
			 else                            // or go rightNode?
				{
				 isleftNodeChild = false;    
				 current = current.rightNode;
				}
			 if(current == null)             // end of the line,
				return false;                // didn't find it
         }  // end while
      // found node to delete
	  
		  if(current.leftNode==null && current.rightNode==null)
			 {
				 if(current == root)             // if root,
					root = null;                 // tree is empty
				 else if(isleftNodeChild)
					parent.leftNode = null;     // disconnect
				 else                            // from parent
					parent.rightNode = null;
			 }
		

			// if no rightNode child, replace with leftNode subtree
			 else if(current.rightNode==null)
			 {
				 if(current == root)
					root = current.leftNode;
				 else if(isleftNodeChild)
					parent.leftNode = current.leftNode;
				 else
					parent.rightNode = current.leftNode;
			 }
			  // if no leftNode child, replace with rightNode subtree
			  else if(current.leftNode==null)
			  {
				 if(current == root)
					root = current.rightNode;
				 else if(isleftNodeChild)
					parent.leftNode = current.rightNode;
				 else
					parent.rightNode = current.rightNode;
			  }
			  
			  else
			  
					// two children, so replace with inorder successor
				{
					 // get successor of node to delete (current)
					 AVLNode successor = getSuccessor(current);

					 // connect parent of current to successor instead
					 if(current == root)
						root = successor;
					 else if(isleftNodeChild)
						parent.leftNode = successor;
					 else
						parent.rightNode = successor;
					 // connect successor to current's leftNode child
					 successor.leftNode = current.leftNode;
				 }  // end else two children
					
					return true;    
			  }
			 
			 
	  
	   // returns node with next-highest value after delNode
			   // goes to rightNode child, then rightNode child's leftNode descendents
				
				private AVLNode getSuccessor(AVLNode delNode)
					  {
					  AVLNode successorParent = delNode;      
					  AVLNode successor = delNode;
					  AVLNode current = delNode.rightNode;   // go to rightNode child
					  while(current != null)               // until no more
						 {                                 // leftNode children,
						 successorParent = successor;
						 successor = current;
						 current = current.leftNode;      // go to leftNode child
						 }
														   // if successor not
					  if(successor != delNode.rightNode)  // rightNode child,
						 {                                 // make connections
						 successorParent.leftNode = successor.rightNode;
						 successor.rightNode = delNode.rightNode;
						 }
					  return successor;
					  }

	 
 }
 
 /* Class AVL Tree Test */
 public class AccountTest
 {
     public static void main(String[] args)
    {       
try
{	
        Scanner scan = new Scanner(System.in);
		
		String data [] = new String[100];
		String in = null,name = null;
		int count=0,Id=0,acntCount = 0 ,i =0;
		char ch;
		double balance = 0.0,Bal=0.0;
		
        AVLTree avlt = new AVLTree(); 
		FileReader fileReader=new FileReader(args[0]);
        BufferedReader bufferReader=new BufferedReader(fileReader);
		
           while ((in = bufferReader.readLine())!= null)
				 {	
						data[i++] = in;	
				 }	
				 
				 	
        
        /*  Perform tree operations  */
        do    
        {
			
			while (data[count] != null)
			{
				name = data[count];
				Id = Integer.parseInt(data[count + 1]);
				balance = Double.parseDouble(data[count + 2]);
						
			    avlt.insert( new Account(name,Id,balance) ); 
						
				count = count + 3;
				
				Bal = Bal + balance;
				
				acntCount++;
			}
					
			System.out.println("");
            System.out.println("1. Insert Account ");
			System.out.println("2. Delete Account");
            System.out.println("3. Search Account");
			System.out.println("4. Print Data");
            
 
            int choice = scan.nextInt();            
            switch (choice)
            {
				
            case 1 : 
			   scan.nextLine();
                System.out.println("Enter Account Holder's Name , ID and Balance");
                avlt.insert( new Account(scan.nextLine(),scan.nextInt(),scan.nextDouble()) ); 
				acntCount++;
				Bal = Bal + balance;
                break;

			case 2 : 
				System.out.println("Enter ID to Delete");
				Id = scan.nextInt();
			
			
              if(avlt.delete(Id))
			   {
				   System.out.println("Deleted");
			   }
			   else
			   {
					System.out.println("Not Deleted");
			   }
			   acntCount--;
			   
			   break;
             
            case 3 : 
			scan.nextLine();
                System.out.println("Enter ID to search");
                System.out.println("Search result : "+ avlt.search(scan.nextInt() ));
                break;	
                    
            case 4 : 
                //here total balance count
				System.out.println("Total deposit in bank ="+Bal);
				System.out.println("\nNumber of accounts ="+acntCount);
				System.out.println("");
				avlt.inorder();
                break;     
         
            default : 
                System.out.println("Wrong Entry \n ");
                break;   
            }
            
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');  
}
catch (Exception ecx)
{
	System.out.print("");
}
			
    }
 }

 
class Account implements Comparable<Account>
{

	int Id;
	double Balance;
	String Name;
	
	public Account( String Name,int Id,double Balance)
	{
	
		this.Name = Name;		
		this.Id = Id;
		this.Balance = Balance;
		
		
	
	}
	
	public int getId()
	{
		return Id;
		
	}
	@Override
		public int compareTo(Account k)
		{
			int key;
			if(this.getId() < k.getId())
			{
				return key=-1;
			}
			else if(this.getId() > k.getId())
			{
				return key=1;
			}
			else
			{
				return key=0;
			}
			
			
			
		}

		public String toString()
		{
			
			
			return Id+", "+Name+", "+Balance+"\n";
		}



}