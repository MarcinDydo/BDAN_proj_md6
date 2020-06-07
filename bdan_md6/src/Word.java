public class Word {
    private byte[] content=new byte[]{0, 0, 0, 0, 0, 0, 0, 0};


    public Word(){}
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
                w.content[j]=array[i+j];
            }
            words[i/8]=w;
        }
        return words;
    }
    public byte[] getContent() {
        return content;
    }
}
