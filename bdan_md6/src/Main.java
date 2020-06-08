import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MD6HashFunction m = new MD6HashFunction();
        System.out.println(m.GenerateHash("abc"));







        byte[] test2=new byte[8];




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
        Word w15=new Word("0000000000000000");
        Word w16=new Word("0000000000000000");
        Word w17=new Word("0000000000000000");
        Word w18=new Word("0000000000000000");
        Word w19=new Word("0000000000000000");
        Word w20=new Word("0000000000000000");
        Word w21=new Word("0000000000000000");
        Word w22=new Word("0000000000000000");

        Word w23=new Word("0100000000000000");
        Word w24=new Word("00054010fe800100");
        Word w25=new Word("6162630000000000");

        Word[] www1=new Word[16];
        Word[] www2=new Word[16];
        Word[] www3=new Word[16];
        Word[] www4=new Word[16];




        for(int i=0;i<16;i++) {
            Word ttmp=new Word("0000000000000000");
            www1[i]=ttmp;
        }
        for(int i=0;i<16;i++) {
            Word ttmp=new Word("0000000000000000");
            www2[i]=ttmp;
        }
        for(int i=0;i<16;i++) {
            Word ttmp=new Word("0000000000000000");
            www3[i]=ttmp;
        }
        for(int i=0;i<16;i++) {
            Word ttmp=new Word("0000000000000000");
            www4[i]=ttmp;
        }

        www1[0]=w25;
        Word[] aux=new Word[25];
        aux[0]=w0;
        aux[1]=w1;
        aux[2]=w2;
        aux[3]=w3;
        aux[4]=w4;
        aux[5]=w5;
        aux[6]=w6;
        aux[7]=w7;
        aux[8]=w8;
        aux[9]=w9;
        aux[10]=w10;
        aux[11]=w11;
        aux[12]=w12;
        aux[13]=w13;
        aux[14]=w14;
        aux[15]=w15;
        aux[16]=w16;
        aux[17]=w17;
        aux[18]=w18;
        aux[19]=w19;
        aux[20]=w20;
        aux[21]=w21;
        aux[22]=w22;
        aux[23]=w23;
        aux[24]=w24;
        Chunk c1=new Chunk(www1);
        Chunk c2=new Chunk(www2);
        Chunk c3=new Chunk(www3);
        Chunk c4=new Chunk(www4);
        ArrayList<Chunk> chunk_array=new ArrayList<Chunk>();
        chunk_array.add(c1);
        chunk_array.add(c2);
        chunk_array.add(c3);
        chunk_array.add(c4);

        Compress md=new Compress();

        Chunk ctemp=md.compress(chunk_array,aux);
    }


}
