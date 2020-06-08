import java.util.ArrayList;
import java.util.Arrays;
import java.util.spi.AbstractResourceBundleProvider;

public class Chunk {
    private Word[] wordlist = new Word[16];

    public void setWordlist(Word[] wordlist) {
        this.wordlist = wordlist;
    }

    public Word[] getWordlist() {
        return wordlist;
    }

    public Chunk(Word[] wordlist){
        if(wordlist == null) {
            for (Word w : this.wordlist) {
                w = new Word();
                w.setContent(new byte[]{0, 0, 0, 0, 0, 0, 0, 0});
            }
        }
        for (int i = 0; i < 16; i++) {
            try {
                this.wordlist[i] = wordlist[i];
            }catch (NullPointerException p){
                    this.wordlist[i]=new Word();
            }
        }
    }
    public static int divideWords(Word[] wordlist, ArrayList<Chunk> res){
        int p=0;
        for (int i = 0; i < wordlist.length; i+=16) {
            Word[] temp = new Word[16];
            for (int j = 0; j < 16; j++) {
                if(i+j<wordlist.length)temp[j] = wordlist[i+j];
                else {
                    temp[j]=new Word();
                    p+=64;
                }
            }
            res.add(new Chunk(temp));
        }
        return p;
    }
    public static int fill(ArrayList<Chunk> list){
        int p = 0;
        while(list.size()%4!=0){
            list.add(new Chunk(null));
            p+=1024;
        }
        return p;
    }
    public static int fillTo64(ArrayList<Chunk> list){
        int p = 0;
        while(list.size()!=64){
            list.add(new Chunk(null));
            p+=1024;
        }
        return p;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i=0;
        switch (MD6HashFunction.LENGHT_OF_HASH){
            case 512:
                i=12;
                break;
            case 256:
                i=14;
                break;
            case 128:
                i=15;
                break;
            default:
                i=12;
                break;
        }
        for (; i < 16; i++) {
            for (byte y : wordlist[i].getContent()) {
                sb.append(String.format("%x", y));
            }
        }
        return sb.toString();
    }
}
