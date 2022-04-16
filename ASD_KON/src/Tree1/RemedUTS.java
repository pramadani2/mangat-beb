package Tree1;
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
        Node prev = null;
        Node temp = root;
        
        if (isempty()) {
            root = newNode;

        } else {
            while (temp != null) {
                prev = temp;
                int x = newNode.namaFolder.length()-1;

                if (newNode.namaFolder.equals(temp.namaFolder)) {
                    temp = temp.left;
                }
                else if (newNode.namaFolder.substring(0, x).equals(temp.namaFolder)) {
                    temp = temp.left;
                }
                else {
                    temp = temp.right;
                }
            }

            int x = newNode.namaFolder.length()-1;

            if (newNode.namaFolder.equals(prev.namaFolder)) {
                prev.left = newNode;
                System.out.println("TAMBAH " + newNode.namaFolder + " PADA kiri /"+ prev.namaFolder);    

            }
            else if (newNode.namaFolder.substring(0, x).equals(prev.namaFolder)) {
                prev.left = newNode;
                System.out.println("TAMBAH " + newNode.namaFolder + " PADA kiri /"+ prev.namaFolder);
            }
            else {
                prev.right = newNode;
                System.out.println("TAMBAH " + newNode.namaFolder + " PADA kanan /"+ prev.namaFolder);
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
            if (this.root.namaFolder.equals(root.namaFolder)) {
                System.out.println(this.root.namaFolder);
            }
            else {
                System.out.println(root.namaFolder + " (" + root.data + ") ");
            }
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