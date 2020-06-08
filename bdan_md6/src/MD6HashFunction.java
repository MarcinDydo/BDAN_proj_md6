import java.util.ArrayList;

public class MD6HashFunction {
    /**
     * class to represent MD6 hash function
     * MIT documentation: https://groups.csail.mit.edu/cis/md6/docs/2009-04-15-md6-report.pdf
     */
    //there are two variants of this algorithm, keyed and unkeyed
    private final String key;
    private Word[] keyWords;
    public static final int ROUNDS=168;
    public static final int L = 64;
    public static final int LENGHT_OF_HASH=512;

    public MD6HashFunction(String key){
        this.key=key;
        byte[] keyArray = toByteArray(key,64);
        keyWords = Word.divideBytes(keyArray);
    }
    public MD6HashFunction(){
        this.key="";
        byte[] keyArray = toByteArray(key,64);
        keyWords = Word.divideBytes(keyArray);
    }

    public String GenerateHash(byte[] messageArray){
        int padding=0;
        Word[] messageWords = Word.divideBytes(messageArray);
        padding+=Word.padding;

        Tree tree = new Tree(null);

        ArrayList<Chunk> chunklist = new ArrayList<>();
        padding+=Chunk.divideWords(messageWords,chunklist);
        padding+=Chunk.fill(chunklist);

        tree.build(chunklist,keyWords, padding,key.length());
        return tree.getRootData().toString();
    }

    public byte[] toByteArray(String message, int length){
        byte[] result = new byte[length];
        char[] c =message.toCharArray();
        for (int i = 0; i < result.length; i++) {
            if(i>=c.length) {
                result[i] = 0;
                continue;
            }
            result[i] = (byte) c[i];
        }
        return result;
    }


}
