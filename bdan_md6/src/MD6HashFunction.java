import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class MD6HashFunction {
    /**
     * class to represent MD6 hash function
     * MIT documentation: https://groups.csail.mit.edu/cis/md6/docs/2009-04-15-md6-report.pdf
     */
    //there are two variants of this algorithm, keyed and unkeyed
    private final String key;

    public MD6HashFunction(String key){
        this.key=key;
    }
    public MD6HashFunction(){
        this.key="";
    }

    public String GenerateHash(String message){
        byte[] messageArray = toByteArray(message);
        Word[] words = Word.divideBytes(messageArray);
        Word[] keyWords = Word.divideBytes(toByteArray(key));
        Tree tree = new Tree(null);
        ArrayList<Chunk> chunklist = Chunk.divideWords(words);
        Chunk.fill(chunklist);
        tree.build(chunklist,keyWords);
        return tree.getRootData().toString();
    }

    private byte[] toByteArray(String message){
        byte[] result = new byte[message.length()];
        char[] c =message.toCharArray();
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) c[i];
        }
        return result;
    }


}
