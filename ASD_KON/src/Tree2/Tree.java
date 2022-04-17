package Tree2;

class Node{
    private String namaFolder;
    private int nilai;
    private Node sibling;
    private Node child;

    Node(){
    }

    void setFolder(String namaFolder, int nilai){
        this.namaFolder = namaFolder;
        this.nilai = nilai;
    }

    String getNamaFolder(){
        return namaFolder;
    }

    int getNilai(){
        return nilai;
    }

    void setChild(Node child){
        this.child = child;
    }

    Node getChild(){
        return child;
    }

    void setSibling(Node sibling){
        this.sibling = sibling;
    }

    Node getSibling(){
        return sibling;
    }

}

public class Tree{
    private Node root;

    Tree(){
    }

    void setRoot(Node root){
        this.root = root;
    }

    Node getRoot(){
        return root;
    }

    void makeTree(String namaFolder, int nilai){
        Node newNode;
        newNode = new Node();

        newNode.setFolder(namaFolder, nilai);
        newNode.setSibling(null);
        newNode.setChild(null);
        root = newNode;
    }

    void addChild(String namaFolder, int nilai, Node root){
        if(root != null){
            Node newNode;
            newNode = new Node();
            newNode.setFolder(namaFolder, nilai);
            newNode.setChild(null);

            if(root.getChild() == null){
                newNode.setSibling(null);
                root.setChild(newNode);
            }
            else{
                if(root.getChild().getSibling() == null){
                    newNode.setSibling(root.getChild());
                    root.getChild().setSibling(newNode);
                }
                else{
                    Node last = root.getChild();

                    while(last.getSibling() != root.getChild()){
                        last = last.getSibling();
                    }

                    newNode.setSibling(root.getChild());
                    last.setSibling(newNode);
                }
            }
        }
    }

    Node findNode(String namaFolder, Node root) {
        Node hasil = null;

        if(root != null){
            if(root.getNamaFolder() == namaFolder) {
                hasil = root;
            }
            else{
                Node temp = root.getChild();

                if(temp != null){
                    if(temp.getSibling() == null){
                        if(temp.getNamaFolder() == namaFolder) {
                            hasil = temp;
                        }
                        else{
                            hasil = findNode(namaFolder, temp);
                        }
                    }
                    else{
                        boolean ketemu = false;

                        while((temp.getSibling() != root.getChild()) && (ketemu == false)){
                            if(temp.getNamaFolder() == namaFolder){
                                hasil = temp;
                                ketemu = true;
                            }
                            else{
                                hasil = findNode(namaFolder, temp);
                                temp = temp.getSibling();
                            }
                        }

                        if(ketemu == false){
                            if(temp.getNamaFolder() == namaFolder){
                                hasil = temp;
                            }
                            else{
                                hasil = findNode(namaFolder, temp);
                            }
                        }
                    }
                }
            }
        }
        return hasil;
    }

    void printTree(Node root){
        if(root != null){
            System.out.println(root.getNamaFolder());

            Node temp = root.getChild();
            if(temp != null){
                if(temp.getSibling() == null){
                    printTree(temp);
                }
                else{
                    while(temp.getSibling() != root.getChild()){
                        printTree(temp);
                        temp = temp.getSibling();
                    }
                    printTree(temp);
                }
            }
        }
    }

    int findSum(Node root){
        int sum = 0;
        if(root != null){
            sum = root.getNilai();

            Node temp = root.getChild();
            if(temp != null){
                if(temp.getSibling() == null){
                    sum = sum + temp.getNilai();
                    findSum(temp);
                }
                else{
                    while(temp.getSibling() != root.getChild()){
                        sum = sum + temp.getNilai();
                        findSum(temp);
                        temp = temp.getSibling();
                    }

                    sum = sum + temp.getNilai();
                    findSum(temp);
                }
            }
        }
        return sum;
    }

    void printSum(Node root){
        int sum = findSum(root);
        System.out.println(sum);
    }
    
}

class CobaPohon{
    public static void main(String[] args) {
        Tree T = new Tree();
        Node temp;
        T.makeTree("data", 0);

        T.addChild("a", 1024, T.getRoot());
        T.addChild("b", 256, T.getRoot());
        T.addChild("c", 35, T.getRoot());

        temp = T.findNode("b", T.getRoot());
        T.addChild("ba", 100, temp);
        T.addChild("bc", 150, temp);
        T.addChild("bd", 125, temp);
        T.addChild("be", 75, temp);

        temp = T.findNode("c", T.getRoot());
        T.addChild("cb", 30, temp);
        T.addChild("ce", 50, temp);

        temp = T.getRoot();
        temp = T.findNode("b", temp);
        temp = T.findNode("ba", temp);
        T.addChild("baa", 500, temp);
        T.addChild("bac", 25, temp);

        temp = T.findNode("c", T.getRoot());
        T.printSum(temp);

        System.out.println();
        T.printTree(T.getRoot());

        System.out.println();
        temp = T.findNode("c", T.getRoot());
        T.printTree(temp);
    }
}