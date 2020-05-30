public class MD6HashFunction {
    /**
     * class to represent MD6 hash function
     * MIT documentation: https://groups.csail.mit.edu/cis/md6/docs/2009-04-15-md6-report.pdf
     */
    //there are two variants of this algorithm, keyed and unkeyed
    private final long key;
    //80 is the default number of rounds
    private int rounds = 80;
    private int lengthOfHash;
    public MD6HashFunction(long key){
        this.key=key;
    }
    public MD6HashFunction(){
        this.key=0;
    }
    public void setRounds(int rounds){
        this.rounds=rounds;
    }

    public long GenerateHash(String message, int length){
        this.lengthOfHash=length%513;
        byte[] messageArray = toByteArray(message);
        for (byte b:messageArray) {

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
