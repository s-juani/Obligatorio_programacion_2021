import TADs.ClosedHash.HashTable;
import TADs.DoubleHash.DoubleHashTable;
import TADs.heap.Heap;
import TADs.heap.HeapImpl;
import entities.CastMember;
import entities.MovieCastMember;

public abstract class Reportes {

    public static Object[] reporte1(DoubleHashTable<Integer, Integer, MovieCastMember> movieCastMemberHash){
        Object[] top5 = new Object[5];
        Heap<Integer, CastMember> topHeap = new HeapImpl<>(5,"min");

        /*
        int i = 0;
        for (CastMember c : movieCastMemberHash)  -- usar Iterator --
        -- contar cantidad de apariciones --
            if (i < 5){
                topHeap.add(apariciones,c)
                i++
            } else if (topHeap.top().getKey().compareTo(c.getKey()) < 0) {
                deleteValue = heapMin.remove()
                topHeap.add(apariciones,c)
            }

            for (int i=4; i>=0, i--){
                top5[i] = topHeap.remove();
            }






         */



        return top5;
    }
}
