import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Klasa odpowiadająca za funkcję kompresji
 */
public class Compress {
    private static final int LONG_BYTES = 8;
    private static final short BYTE_MASK = 0xFF;
    private static final short BYTE_SIZE = 8;
    public static byte[] paca;
    static short off = 0;
    static short len = 8;
    static short num = 1;
    private static int[] lt = new int[16];
    private static int[] rt = new int[16];
    private static int t0 = 17;
    private static int t1 = 18;
    private static int t2 = 21;
    private static int t3 = 31;
    private static int t4 = 67;
    private static int w = 64;
    private static int r = 5;
    private static String S0 = "0123456789abcdef";
    private static String SG = "7311c2812425cfa0";
    private static byte[] S;
    private static int n = 64;
    private static ArrayList<byte[]> SA = new ArrayList<byte[]>();
    private static int rounds = 168;
    private static int lengthOfHash = 512;
    private static int L = 64;

    public Compress() {

        fill_array();
        fill_lr();

    }

    /**
     *
     * @param key
     * @param index
     * @param level
     * @param p
     * @param isFinal
     * @return
     */
    public static Word[] determineAuxilary(Word[] key, int index, int level, int p, boolean isFinal) {
        index -= 64;
        int keylen = 0;
        for (Word w : key) {
            for (byte b : w.getContent()) {
                if (b != 0) keylen++;
            }
        }
        Word[] aux = new Word[25];
        System.arraycopy(Word.sqrt6(), 0, aux, 0, Word.sqrt6().length);
        System.arraycopy(key, 0, aux, 15, key.length);
        //dependent AUX content
        byte[] ucontent = new byte[8];
        ucontent[0] = (byte) level;
        ucontent[7] = (byte) index;
        for (int i = 1; i < 7; i++) {
            ucontent[i] = 0;
        }
        Word U = new Word(ucontent);

        long vcontent = rounds;
        vcontent = vcontent << 8;
        vcontent += L;
        vcontent = vcontent << 4;
        if (isFinal) vcontent += vcontent;
        vcontent = vcontent << 16;
        vcontent += p;
        vcontent = vcontent << 8;
        vcontent += keylen;
        vcontent = vcontent << 12;
        vcontent += lengthOfHash;
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(vcontent);
        Word V = new Word(buffer.array());

        aux[23] = U;
        aux[24] = V;
        return aux;
    }

    static byte[] shiftLeft(byte[] byteArray, int shiftBitCount) {
        final int shiftMod = shiftBitCount % 8;
        final byte carryMask = (byte) ((1 << shiftMod) - 1);
        final int offsetBytes = (shiftBitCount / 8);
        byte[] array = new byte[8];
        for (int i = 0; i < 8; i++) {
            array[i] = byteArray[i];


        }
        int sourceIndex;
        for (int i = 0; i < array.length; i++) {
            sourceIndex = i + offsetBytes;
            if (sourceIndex >= array.length) {
                array[i] = 0;
            } else {
                byte src = array[sourceIndex];
                byte dst = (byte) (src << shiftMod);
                if (sourceIndex + 1 < array.length) {
                    dst |= array[sourceIndex + 1] >>> (8 - shiftMod) & carryMask;
                }
                array[i] = dst;
            }
        }
        return array;
    }


    static byte[] shiftRight(byte[] byteArray, int shiftBitCount) {
        final int shiftMod = shiftBitCount % 8;
        final byte carryMask = (byte) (0xFF << (8 - shiftMod));
        final int offsetBytes = (shiftBitCount / 8);
        byte[] array = new byte[8];

        for (int i = 0; i < 8; i++) {
            array[i] = byteArray[i];


        }


        int sourceIndex;
        for (int i = array.length - 1; i >= 0; i--) {
            sourceIndex = i - offsetBytes;
            if (sourceIndex < 0) {
                array[i] = 0;
            } else {
                byte src = array[sourceIndex];
                byte dst = (byte) ((0xff & src) >>> shiftMod);
                if (sourceIndex - 1 >= 0) {
                    dst |= array[sourceIndex - 1] << (8 - shiftMod) & carryMask;
                }
                array[i] = dst;
            }
        }
        return array;
    }



    public static void rotr64(byte[] inBuf, short inOff, byte[] outBuf, short outOff, short rot) {
        short byteRot = (short) ((rot & 0b00111000) >> 3);
        short bitRot = (short) (rot & 0b00000111);

        if (bitRot == 0) {

            if (byteRot == 0) {
                // --- no rotation
                return;
            }

            // --- only byte rotation
            for (short i = 0; i < LONG_BYTES; i++) {
                outBuf[(short) (outOff + (i + byteRot) % LONG_BYTES)] = inBuf[(short) (inOff + i)];
            }
        } else {
            // --- bit- and possibly byte rotation
            // note: also works for all other situations, but slower

            // put the last byte in t_lo
            short t = (short) (inBuf[inOff + LONG_BYTES - 1] & BYTE_MASK);
            for (short i = 0; i < LONG_BYTES; i++) {
                // shift t_lo into t_hi and add the next byte into t_lo
                t = (short) (t << BYTE_SIZE | (inBuf[(short) (inOff + i)] & BYTE_MASK));
                // find the byte to receive the shifted value within the short
                outBuf[(short) (outOff + (i + byteRot) % LONG_BYTES)] = (byte) (t >> bitRot);
            }
        }
    }

    public static void rotl64(byte[] inBuf, short inOff, byte[] outBuf, short outOff, short rot) {
        rotr64(inBuf, inOff, outBuf, outOff, (short) (64 - rot & 0b00111111));
    }












    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }


    private static byte[] and_operator(byte[] array, byte[] array2) {

        byte[] and_array = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            and_array[i] = (byte) (array[i] & array2[i]);


        }


        return and_array;
    }

    public static byte[] xor_operator(byte[] array, byte[] array2) {

        byte[] xor_array = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            xor_array[i] = (byte) (array[i] ^ array2[i]);


        }

        return xor_array;
    }

    public static void fill_array() {

        byte[] s0 = hexStringToByteArray(S0);

        S = hexStringToByteArray(SG);
        System.out.println();

        SA.add(s0);
        System.out.println();


        System.out.println("Stała S");
        for (int i = 1; i < 167; i++) {
            byte[] temp = SA.get(i - 1);

            byte[] tm = new byte[8];
            rotl64(temp, off, tm, off, num);


            byte[] temp2 = and_operator(SA.get(i - 1), S);
            byte[] result = xor_operator(tm, temp2);
            SA.add(result);


            System.out.println(i - 1);

            for (byte y : SA.get(i - 1)) {
                System.out.print(String.format("%x", y));

            }
            System.out.println("kkk");


        }

    }

    private static void fill_lr() {

        rt[0] = 10;
        rt[1] = 5;
        rt[2] = 13;
        rt[3] = 10;
        rt[4] = 11;
        rt[5] = 12;
        rt[6] = 2;
        rt[7] = 7;
        rt[8] = 14;
        rt[9] = 15;
        rt[10] = 7;
        rt[11] = 13;
        rt[12] = 11;
        rt[13] = 7;
        rt[14] = 6;
        rt[15] = 12;

        lt[0] = 11;
        lt[1] = 24;
        lt[2] = 9;
        lt[3] = 16;
        lt[4] = 15;
        lt[5] = 9;
        lt[6] = 27;
        lt[7] = 15;
        lt[8] = 6;
        lt[9] = 2;
        lt[10] = 29;
        lt[11] = 8;
        lt[12] = 15;
        lt[13] = 5;
        lt[14] = 31;
        lt[15] = 9;

    }

    static int fromByteArray(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }

    /**
     * input array [89][8] 89 words of 64 bits
     *
     * @param a, auxiliary
     * @return
     */
    public static Chunk compress(ArrayList<Chunk> a, Word[] auxiliary) {
        fill_lr();
        fill_array();
        int n = 89;
        Chunk c1 = a.get(0);
        Chunk c2 = a.get(1);
        Chunk c3 = a.get(2);
        Chunk c4 = a.get(3);
        Word[] w = new Word[89];
        Word[] w1 = c1.getWordlist();
        Word[] w2 = c2.getWordlist();
        Word[] w3 = c3.getWordlist();
        Word[] w4 = c4.getWordlist();


        for (int i = 0; i < 16; i++) {

            w[i + 25] = w1[i];
        }
        for (int i = 0; i < 16; i++) {

            w[41 + i] = w2[i];
        }
        for (int i = 0; i < 16; i++) {

            w[57 + i] = w3[i];
        }
        for (int i = 0; i < 16; i++) {

            w[73 + i] = w4[i];
        }
        for (int i = 0; i < 25; i++) {

            w[i] = auxiliary[i];
        }


        int c = 16;
        int t = r * c;
        Word[] A = new Word[t + n];

        for (int i = 0; i < 89; i++) {


            A[i] = w[i];
        }


        int tem = 0;
        int v = 0;
        byte[] u = new byte[8];
        byte[] uu = new byte[8];
        for (int i = n; i < t + n; i++) {
            int temp = (i - n) % 16;


            byte[] temp2 = xor_operator(SA.get(tem), A[i - n].getContent());
            //System.out.println("|i="+i);
            //System.out.println(i-t0);


            byte[] x = xor_operator(temp2, A[i - t0].getContent());


            byte[] temp3 = and_operator(A[i - t1].getContent(), A[i - t2].getContent());

            //System.out.println();
            byte[] temp4 = and_operator(A[i - t3].getContent(), A[i - t4].getContent());

            byte[] temp5 = xor_operator(temp3, temp4);


            u = xor_operator(x, temp5);
            paca = u;
            byte[] p = u;


            byte[] temp6 = shiftRight(u, rt[temp]);
            int hg = temp6.length;


            uu = xor_operator(temp6, u);

            byte[] temp7 = shiftLeft(uu, lt[temp]);

            //System.out.println("l7:"+temp7.length);

            byte[] res1 = xor_operator(temp7, uu);

            System.out.print("result"+i+" ");
            for (byte y : res1) {
                System.out.print(String.format("%x", y));

            }

            Word wtemp = new Word();


            System.out.println();

            // System.out.println("l:"+x.length);
            wtemp.setContent(res1);
            A[i] = wtemp;
            //System.out.println("v="+tem);

            if (v == 15) {
                v = 0;
                tem++;
            } else v++;


            for (int j = 0; j < x.length; j++) {
                // A[i][j]=x[j];

            }

        }


        Word[] res = new Word[16];

        for (int i = 0; i < 16; i++) {
            res[i] = A[t + n - c];


        }

        Chunk chunk = new Chunk(res);


        return chunk;
    }

    static byte[] longToByteArray(long value) {
        return ByteBuffer.allocate(8).putLong(value).array();
    }

    static long byteArrayToLong(byte[] array) {
        return ByteBuffer.wrap(array).getLong();
    }

    public static void main(String[] args) {

        fill_array();
        byte[] test = hexStringToByteArray(SG);
        System.out.println(test.length);
        for (byte y : test) {
            System.out.print(String.format("%x", y));
        }
        System.out.println();
        short a = 0;
        short b = 7;
        short c = 1;

        for (byte y : test) {
            System.out.print(String.format("%x", y));

        }
        System.out.println();
        String t1 = "61";
        String t2 = "81";
        byte[] s1 = hexStringToByteArray(t1);
        byte[] s2 = hexStringToByteArray(t2);
        for (byte y : s1) {
            System.out.print(String.format("%x", y));

        }
        System.out.println();
        for (byte y : s2) {
            System.out.print(String.format("%x", y));

        }


        byte[] s4 = hexStringToByteArray(S0);
        byte[] s5 = new byte[8];

        short t = 0;
        short h = 1;


        byte[] test2 = new byte[8];
        Word w0 = new Word("7311c2812425cfa0");
        Word w1 = new Word("6432286434aac8e7");
        Word w2 = new Word("b60450e9ef68b7c1");
        Word w3 = new Word("e8fb23908d9f06f1");
        Word w4 = new Word("dd2e76cba691e5bf");
        Word w5 = new Word("0cd0d63b2c30bc41");
        Word w6 = new Word("1f8ccf6823058f8a");
        Word w7 = new Word("54e5ed5b88e3775d");
        Word w8 = new Word("4ad12aae0a6d6031");
        Word w9 = new Word("3e7f16bb88222e0d");
        Word w10 = new Word("8af8671d3fb50c2c");
        Word w11 = new Word("995ad1178bd25c31");
        Word w12 = new Word("c878c1dd04c4b633");
        Word w13 = new Word("3b72066c7a1552ac");
        Word w14 = new Word("0d6f3522631effcb");
        Word w15 = new Word("0000000000000000");
        Word w16 = new Word("0000000000000000");
        Word w17 = new Word("0000000000000000");
        Word w18 = new Word("0000000000000000");
        Word w19 = new Word("0000000000000000");
        Word w20 = new Word("0000000000000000");
        Word w21 = new Word("0000000000000000");
        Word w22 = new Word("0000000000000000");

        Word w23 = new Word("0100000000000000");
        Word w24 = new Word("00054010fe800100");
        Word w25 = new Word("6162630000000000");

        Word[] www1 = new Word[16];
        Word[] www2 = new Word[16];
        Word[] www3 = new Word[16];
        Word[] www4 = new Word[16];


        for (int i = 0; i < 16; i++) {
            Word ttmp = new Word("0000000000000000");
            www1[i] = ttmp;
        }
        for (int i = 0; i < 16; i++) {
            Word ttmp = new Word("0000000000000000");
            www2[i] = ttmp;
        }
        for (int i = 0; i < 16; i++) {
            Word ttmp = new Word("0000000000000000");
            www3[i] = ttmp;
        }
        for (int i = 0; i < 16; i++) {
            Word ttmp = new Word("0000000000000000");
            www4[i] = ttmp;
        }

        www1[0] = w25;
        Word[] aux = new Word[25];
        aux[0] = w0;
        aux[1] = w1;
        aux[2] = w2;
        aux[3] = w3;
        aux[4] = w4;
        aux[5] = w5;
        aux[6] = w6;
        aux[7] = w7;
        aux[8] = w8;
        aux[9] = w9;
        aux[10] = w10;
        aux[11] = w11;
        aux[12] = w12;
        aux[13] = w13;
        aux[14] = w14;
        aux[15] = w15;
        aux[16] = w16;
        aux[17] = w17;
        aux[18] = w18;
        aux[19] = w19;
        aux[20] = w20;
        aux[21] = w21;
        aux[22] = w22;
        aux[23] = w23;
        aux[24] = w24;
        Chunk c1 = new Chunk(www1);
        Chunk c2 = new Chunk(www2);
        Chunk c3 = new Chunk(www3);
        Chunk c4 = new Chunk(www4);
        ArrayList<Chunk> chunk_array = new ArrayList<Chunk>();
        chunk_array.add(c1);
        chunk_array.add(c2);
        chunk_array.add(c3);
        chunk_array.add(c4);


        String saa = "823287e6ad8e24f8";
        String sbb = "01c8ca1f9ab63809";


        byte[] ada = xor_operator(hexStringToByteArray(saa), hexStringToByteArray(sbb));

        byte[] tescik = hexStringToByteArray("823287e6ad8e24f8");

        System.out.println(":::A");
        byte tess = tescik[0];

        System.out.print(String.format("%x", tess));
        System.out.println();


//byte[]k1=longToBytes(k);


        //  for (byte y : k1) {
        //      System.out.print(String.format("%x", y));
        // }

        //System.out.println(k);


        byte[] tescik2 = hexStringToByteArray("823287e6ad8e24f8");


    }

    public byte[] xor(byte[] array, byte[] array2) {


        byte[] g = new byte[8];


        for (int i = 0; i < 8; i++) {
            byte xor = (byte) (0xff & ((int) array[i]) ^ ((int) array2[i]));
            int spr = array[i];
            int spr2 = array2[i];
            int spr3 = (0xff & ((int) array[i]) ^ ((int) array2[i]));
            g[i] = xor;


        }
        return g;


    }


}






