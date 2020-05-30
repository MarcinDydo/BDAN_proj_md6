public class Word {
    private byte[] content;
    public Word(){
        content = new byte[8];
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

    public byte[] getContent() {
        return content;
    }
}
