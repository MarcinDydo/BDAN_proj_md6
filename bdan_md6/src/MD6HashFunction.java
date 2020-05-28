public class MD6HashFunction {
    /**
     * class to represent MD6 hash function
     * MIT documentation: https://groups.csail.mit.edu/cis/md6/docs/2009-04-15-md6-report.pdf
     */
    //there are two variants of this algorithm, keyed and unkeyed
    private final long key;
    //80 is the default number of rounds
    private int rounds = 80;
    //longest hash is the most desired
    private int lengthOfHash = 512;
    public MD6HashFunction(long key){
        this.key=key;
    }
    public MD6HashFunction(){
        this.key=0;
    }
    public void setParams(int rounds, int lengthOfHash){
        this.rounds=rounds;
        this.lengthOfHash=lengthOfHash;
    }

    public long GenerateHash(String message){
        byte[] result = new byte[lengthOfHash/8];
        byte[] messageArray = toByteArray(message);
        for (int i = 0; i < rounds; i++) {
        }
        return 0;
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
