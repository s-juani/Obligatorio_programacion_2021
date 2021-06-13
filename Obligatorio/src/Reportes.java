import TADs.heap.Heap;
import TADs.heap.HeapImpl;
import entities.CastMember;

public abstract class Reportes {

    public static Object[][] reporte1(){  // args: hash de movieCastMember
        Object[][] top5 = new Object[5][2];
        Heap<Integer, CastMember> topHeap = new HeapImpl<Integer, CastMember>(5,"min");



        return top5;
    }
}
