public class Word {
    private byte[] content=new byte[]{0, 0, 0, 0, 0, 0, 0, 0};

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
