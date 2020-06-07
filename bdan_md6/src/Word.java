import java.math.BigInteger;

public class Word {
    private byte[] content=new byte[]{0, 0, 0, 0, 0, 0, 0, 0};


    public Word(){}
    public Word(byte[] content){
        this.content=content;
    }
    public Word(String word){
            byte[] b = new byte[word.length() / 2];
            for (int i = 0; i < b.length; i++) {
                int index = i * 2;
                int v = Integer.parseInt(word.substring(index, index + 2), 16);
                b[i] = (byte) v;
            }
            this.content=b;

    }
    public void setContent(byte[] content) {
        for (int i = 0; i < 8; i++) {
            try {
                this.content[i] = content[i];
            }catch (NullPointerException p){
                if (this.content != null) {
                    this.content[i]=0;
                }
            }
        }
    }
    public static Word[] divideBytes(byte[] array){
        Word[] words = new Word[array.length/8];
        for (int i = 0; i < array.length; i+=8) {
            Word w = new Word();
            for (int j = 0; j < 8; j++) {
                try {
                    w.content[j] = array[i + j];
                }catch (IndexOutOfBoundsException ignore){}
            }
            try {
                words[i/8]=w;
            }catch (IndexOutOfBoundsException ign){}
        }
        return words;
    }
    public byte[] getContent() {
        return content;
    }
    public static Word[] sqrt6(){
        final BigInteger frac = new BigInteger("4494897427831780981972840747058913919659474806566701284326925672509" +
                "6037745731502653985943310464023481859460122661418912485886545983775734162578395123727855282891274752767" +
                "6571247630105270911770223481310678986690853632443352545604033808808939374585567846574724361304144270270" +
                "21617420183830008");
        byte[] bytes = frac.toByteArray();
        return divideBytes(bytes);
    }
}
