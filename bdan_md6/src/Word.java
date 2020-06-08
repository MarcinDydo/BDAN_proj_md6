import java.math.BigInteger;
import java.util.ArrayList;

public class Word {
    private byte[] content=new byte[]{0, 0, 0, 0, 0, 0, 0, 0};
    public static int padding =0;

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
        padding=0;
        ArrayList<Word> words = new ArrayList<>();
        for (int i = 0; i < array.length; i+=8) {
            Word w = new Word();
            for (int j = 0; j < 8; j++) {
                if(i+j<array.length) {w.content[j] = array[i + j];}
                else {
                    w.content[j]=0;
                    padding++;
                }
            }
            words.add(w);
        }
        Word[] res = new Word[words.size()];
        for (int i = 0; i < words.size(); i++) {
            res[i]=words.get(i);
        }
        padding=padding*8;
        return res;
    }
    public byte[] getContent() {
        return content;
    }
    public static Word[] sqrt6(){
        /*
        final BigInteger frac = new BigInteger("4494897427831780981972840747058913919659474806566701284326925672509" +
                "6037745731502653985943310464023481859460122661418912485886545983775734162578395123727855282891274752767" +
                "6571247630105270911770223481310678986690853632443352545604033808808939374585567846574724361304144270270" +
                "21617420183830008");
        byte[] bytes = frac.toByteArray();
        return divideBytes(bytes);
         */
        Word w0=new Word("7311c2812425cfa0");
        Word w1=new Word("6432286434aac8e7");
        Word w2=new Word("b60450e9ef68b7c1");
        Word w3=new Word("e8fb23908d9f06f1");
        Word w4=new Word("dd2e76cba691e5bf");
        Word w5=new Word("0cd0d63b2c30bc41");
        Word w6=new Word("1f8ccf6823058f8a");
        Word w7=new Word("54e5ed5b88e3775d");
        Word w8=new Word("4ad12aae0a6d6031");
        Word w9=new Word("3e7f16bb88222e0d");
        Word w10=new Word("8af8671d3fb50c2c");
        Word w11=new Word("995ad1178bd25c31");
        Word w12=new Word("c878c1dd04c4b633");
        Word w13=new Word("3b72066c7a1552ac");
        Word w14=new Word("0d6f3522631effcb");
        return new Word[]{w0,w1,w2,w3,w4,w5,w6,w7,w8,w9,w10,w11,w12,w13,w14};
    }

}
