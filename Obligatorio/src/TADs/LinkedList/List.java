package TADs.LinkedList;

public class List {
    public ListNode head;
    private int count;

    public List(){
        count=0;
    }

    public void add(int item){
        if (this.head!=null) {
            ListNode nodoNuevo = new ListNode(item);
            nodoNuevo.next = this.head;
            this.head = nodoNuevo;
        }else{
            this.head = new ListNode(item);
        }
        this.count+=1;
    }

    public int size(){
        return this.count;
    }

    public int get(int posicion){
        int value = 0;
        if (posicion >= 0 && posicion < this.count) {
            ListNode actual = this.head;
            int i = 0;
            while (i < posicion) {
                actual = actual.next;
                i++;
            }
            value = actual.item;
        }
        return value;
    }

    public void printList(){
        ListNode nodoActual = this.head;
        while (nodoActual != null) {
            System.out.println(nodoActual.item);
            nodoActual=nodoActual.next;
        }
    }

    public List crearListaMayorOIgualA0(){
        List listaNueva = new List();
        ListNode nodoNuevo = listaNueva.head;
        ListNode nodo = this.head;
        while (nodo!=null){
            if (nodo.item>=0){
                if (listaNueva.head.next!=null) {
                    nodoNuevo.item = nodo.item;
                    nodoNuevo.next = this.head;
                    listaNueva.head = nodoNuevo;
                    nodoNuevo = nodoNuevo.next;
                } else {
                    listaNueva.head=new ListNode(nodo.item);
                }
            }
            nodo=nodo.next;
        }
        return listaNueva;
    }

    public void eliminarNodosPositivos(){
        ListNode nodo = this.head;
        while (nodo.next!=null){
            if (nodo.next.item>=0){
                nodo.next=nodo.next.next;
                if (nodo.next==null) break;
            }
            nodo=nodo.next;
        }
        if (this.head.item>=0){
            this.head=this.head.next;
        }
    }

}
