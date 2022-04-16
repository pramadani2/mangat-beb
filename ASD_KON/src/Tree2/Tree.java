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
        Node baru;
        baru = new Node();

        baru.setFolder(namaFolder, nilai);
        baru.setSibling(null);
        baru.setChild(null);
        root = baru;
    }

    void addChild(String namaFolder, int nilai, Node root){
        if(root != null){
            Node baru;
            baru = new Node();
            baru.setFolder(namaFolder, nilai);
            baru.setChild(null);

            if(root.getChild() == null){
                baru.setSibling(null);
                root.setChild(baru);
            }
            else{
                if(root.getChild().getSibling() == null){
                    baru.setSibling(root.getChild());
                    root.getChild().setSibling(baru);
                }
                else{
                    Node last = root.getChild();

                    while(last.getSibling() != root.getChild()){
                        last = last.getSibling();
                    }

                    baru.setSibling(root.getChild());
                    last.setSibling(baru);
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
                Node bantu = root.getChild();

                if(bantu != null){
                    if(bantu.getSibling() == null){
                        if(bantu.getNamaFolder() == namaFolder) {
                            hasil = bantu;
                        }
                        else{
                            hasil = findNode(namaFolder, bantu);
                        }
                    }
                    else{
                        boolean ketemu = false;

                        while((bantu.getSibling() != root.getChild()) && (ketemu == false)){
                            if(bantu.getNamaFolder() == namaFolder){
                                hasil = bantu;
                                ketemu = true;
                            }
                            else{
                                hasil = findNode(namaFolder, bantu);
                                bantu = bantu.getSibling();
                            }
                        }

                        if(ketemu == false){
                            if(bantu.getNamaFolder() == namaFolder){
                                hasil = bantu;
                            }
                            else{
                                hasil = findNode(namaFolder, bantu);
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

            Node bantu = root.getChild();
            if(bantu != null){
                if(bantu.getSibling() == null){
                    printTree(bantu);
                }
                else{
                    while(bantu.getSibling() != root.getChild()){
                        printTree(bantu);
                        bantu = bantu.getSibling();
                    }

                    printTree(bantu);
                }
            }
        }
    }

    int findSum(Node root){
        int jumlah = 0;
        if(root != null){
            jumlah = root.getNilai();

            Node bantu = root.getChild();
            if(bantu != null){
                if(bantu.getSibling() == null){
                    jumlah = jumlah + bantu.getNilai();
                    findSum(bantu);
                }
                else{
                    while(bantu.getSibling() != root.getChild()){
                        jumlah = jumlah + bantu.getNilai();
                        findSum(bantu);
                        bantu = bantu.getSibling();
                    }

                    jumlah = jumlah + bantu.getNilai();
                    findSum(bantu);
                }
            }
        }
        return jumlah;
    }

    void printSum(Node root){
        int jumlah = findSum(root);
        System.out.println(jumlah);
    }
    
}

class CobaPohon{
    public static void main(String[] args) {
        Tree T = new Tree();
        Node bantu;
        T.makeTree("data", 0);

        T.addChild("a", 1, T.getRoot());
        T.addChild("b", 2, T.getRoot());
        T.addChild("c", 3, T.getRoot());

        bantu = T.findNode("a", T.getRoot());
        T.addChild("aa", 4, bantu);
        T.addChild("ab", 5, bantu);

        bantu = T.findNode("c", T.getRoot());
        T.addChild("ca", 6, bantu);

        bantu = T.findNode("a", T.getRoot());
        T.printSum(bantu);

        System.out.println();
        T.printTree(T.getRoot());

        System.out.println();
        bantu = T.findNode("a", T.getRoot());
        T.printTree(bantu);
    }
}