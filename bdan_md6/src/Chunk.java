import java.util.ArrayList;
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
    public static ArrayList<Chunk> divideWords(Word[] wordlist){
        ArrayList<Chunk> res =new ArrayList<>();
        for (int i = 0; i < wordlist.length; i+=16) {
            Word[] temp = new Word[16];
            for (int j = 0; j < 16; j++) {
                temp[j] = wordlist[i+j];
            }
            res.add(new Chunk(temp));
        }
        return res;
    }
    public static void fill(ArrayList<Chunk> list){
        while(list.size()<64){
            list.add(new Chunk(null));
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Word w: wordlist) {
            result.append(w.getContent().toString());
        }
        return result.toString();
    }
}
