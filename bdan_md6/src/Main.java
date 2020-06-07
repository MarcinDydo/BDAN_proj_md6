import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MD6HashFunction m = new MD6HashFunction();
     /**   m.GenerateHash("uEq3V1kpO2vG5ENCKuyq06pHDP3DvplbTgCVK68Lzj1HF2JDYuQ3QAvCypRyoX0reQqYIFrx6HNYfgJMfOB" +
                "a8LgEfr0hwziaPY0sUHYVwPFKuYdWMPneXSvRxLykoUUaJQoCvQudck4hV5hIq8In0rwH9Jw2NiU42cXtn7qlu0ZG6T4gAIqcrTkD0s1bJR" +
                "1cMjlyQlBIUT1QMgGuUuK04c3vbTtjNPM3VWiF3qttM9WiIZDLAaKe7ykiq1S2sFo0B4KLqsR9mDkDGOIvBS4i1rw9hh76b3qiMtkmuE4N" +
                "tUZOmgMJN4keuCDmuH3z1N5eMs150HcimloM3G71tSczWnuRqvJH8ifF2V8mdpYBYfwGOrMBBSJXZuJp03YS7N8nZlDdZKoSzvvYr9xdc9t" +
                "bujiuHO28T2QDCVu9dEE3M0IXDLTBTmDmmZhf0Jpi3XPP9gRLX19SE4lkBwJkgABYNJOMnU37LenHle9Ux5PTJS6cVpTru7YjuWzZv2" +
                "u06g1bX4hIRepdM7k0fI5AIhZWwLve6N6HjTDzWZsTirPC4leLXKBpasfZEGd3uCRHansq0uPBtkLJ0w58Eo1Nh1TbcyQM9wmC3bMJ" +
                "pH2dZkcUyoVnTYwhGTiL7TEBITrITMdIGKN3LzUJQdEPuWTKawNcxfCz7iwyZS3DFeEZykKsfKbuSqD5r09rTails5smoE4alcH2hR" +
                "HQIwBUfuYGyCz1BYESz3Oq7vzxXYQCEy7vVgD9jwXrFAt1ULU7WJcpO7VRg7nCoc1vSPWNNn97LezN9LLCI0BAd3ONVG0tm68OJJqp" +
                "kcJVKoZ4xGnth3FoZDDDALBjTTCts4q3G5LdxayCbsSRrhUO6Rts1r3bqguV4rOB4zCicJeEi5gdBrkzGYb0C3NoQI3gJSOsnTXBX" +
                "2vUTdug76Mn63VZGQ90JSYWUB3NBlthobwU2STfQ7T2iDHtPMjDNvIIBE8ROsj1ah0tpVe5YKfozAixvRGDUWowUQ3YgeCqnHMNPCvJUxHkFecK",512);


**/


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

        MD6 md=new MD6();

        Chunk ctemp=md.compress(chunk_array,aux);
        Word[] tak=ctemp.getWordlist();
        byte[] testy=tak[1].getContent();
        String saa="723287e6ad8e24f8";
        String sbb="01c8ca1f9ab63809";









    }






}
