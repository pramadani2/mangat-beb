class Node {
    String namaFolder;
    int data;
    Node left;
    Node right;
    
    public Node(String namaFolder, int data) {
        this.namaFolder = namaFolder;
        this.data = data;
    }
}

class BST {
    Node root;
    
    public boolean isempty() {
        return root == null;
    }
    
    public void insert(String namaFolder, int data) {
        Node newNode = new Node(namaFolder, data);
        Node temp = null;
        Node state = root;
        
        if (isempty()) {
            root = newNode;

        } else {
            while (state != null) {
                temp = state;
                int x = newNode.namaFolder.length()-1;

                if (newNode.namaFolder.equals(state.namaFolder)) {
                    state = state.left;

                }
                else if (newNode.namaFolder.substring(0, x).equals(state.namaFolder)) {
                    state = state.left;
                }
                else {
                    state = state.right;
                }
            }

            int x = newNode.namaFolder.length()-1;

            if (newNode.namaFolder.equals(temp.namaFolder)) {
                temp.left = newNode;
                System.out.println("TAMBAH " + newNode.namaFolder + " PADA /"+ temp.namaFolder);    

            }
            else if (newNode.namaFolder.substring(0,x).equals(temp.namaFolder)) {
                temp.left = newNode;
                System.out.println("TAMBAH " + newNode.namaFolder + " PADA /"+ temp.namaFolder);
            }
            else {
                temp.right = newNode;
                System.out.println("TAMBAH " + newNode.namaFolder + " PADA /"+ temp.namaFolder);
            }
        }
    }
    
    public void searching(String namaFolder) {
        Node temp = root;
        boolean hasil = false;

        while(temp != null) {

            if(temp.namaFolder.equals(namaFolder)) {
                hasil = true;
                break;

            } else {
                temp = temp.right;
            }
        }
        
        if (hasil) {
            System.out.println("folder ditemukan");

        } else {
            System.out.println("folder tidak ditemukan");
        }
    }

    public void display(Node root) {
        if (root != null) {
            System.out.println(root.namaFolder + " (" + root.data + ") ");
            display(root.left);
            display(root.right);
        }
    }
}

public class RemedUTS {
    public static void main(String[] args) {
        BST a = new BST();
        a.insert("data", 0);

        a.insert("a", 23);       
        a.insert("b", 3587);
        a.insert("aa", 456);
        a.insert("c", 235);
        a.insert("d", 435);
        a.insert("ab", 3245);
        a.insert("ca", 2354);
        a.searching("ab");
        a.display(a.root);
    }
}