import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class MD6 {
private static int[] lt=new int[16];
private static int[] rt=new int[16];
private static int t0=17;
private static int t1=18;
private static int t2=21;
private static int t3=31;
private static int t4=67;
private static int w=64;
private static int r=168;
private static String S0="123456789abcdef";
private static String SG="7311c2812425cfa0";
private byte[] S;
private static int n=64;
private ArrayList<byte[]> SA=new ArrayList<byte[]>();
    private static final short BYTE_MASK = 0xFF;
    private static final short BYTE_SIZE = 8;
    short off=0;
    short len=7;
    short num=1;



public MD6(){


    fill_array();
    fill_lr();







}




    public static void rotr(byte[] buf, short off, short len, short rot) {
        if (len == 0) {
            // nothing to rotate (avoid division by 0)
            return;
        }

        final short lenBits = (short) (len * BYTE_SIZE);
        // doesn't always work for edge cases close to MIN_SHORT / MAX_SHORT
        rot = (short) ((rot + lenBits) % lenBits);

        // reused variables for byte and bit shift
        short shift, i;
        byte t1, t2;

        // --- byte shift

        shift = (short) (rot / BYTE_SIZE);

        // only shift when actually required
        if (shift != 0) {

            // values will never be used, src == start at the beginning
            short start = -1, src = -1, dest;

            // compiler is too stupid to see t1 will be assigned anyway
            t1 = 0;

            // go over all the bytes, but in stepwise fashion
            for (i = 0; i < len; i++) {
                // if we get back to the start
                // ... then we need to continue one position to the right
                if (src == start) {
                    start++;
                    t1 = buf[(short) (off + (++src))];
                }

                // calculate next location by stepping by the shift amount
                // ... modulus the length of course
                dest = (short) ((src + shift) % len);

                // save value, this will be the next one to be stored
                t2 = buf[(short) (off + dest)];
                // store value, doing the actual shift
                buf[(short) (off + dest)] = t1;

                // do the step
                src = dest;
                // we're going to store t1, not t2
                t1 = t2;
            }
        }

        // --- bit shift

        shift = (short) (rot % BYTE_SIZE);

        // only shift when actually required
        if (shift != 0) {

            // t1 holds previous byte, at other side
            t1 = buf[(short) (off + len - 1)];
            for (i = 0; i < len; i++) {
                t2 = buf[(short) (off + i)];
                // take bits from previous byte and this byte together
                buf[(short) (off + i)] = (byte) ((t1 << (BYTE_SIZE - shift)) | ((t2 & BYTE_MASK) >> shift));
                // current byte is now previous byte
                t1 = t2;
            }
        }
    }
    public static void rotl(byte[] buf, short off, short len, short bits) {
        final short lenBits = (short) (len * BYTE_SIZE);
        bits = (short) ((bits + lenBits) % lenBits);
        // we don't care if we pass 0 or lenBits, rotr will adjust
        rotr(buf, off, len, (short) (lenBits - bits));
    }
public static byte[] rotate_left(byte[] array,int value){
   // byte a0=array[0];
   // byte a1=array[1];
    //byte a2=array[2];
   // byte a3=array[3];
    //byte a4=array[4];
    //byte a5=array[5];
    //byte a6=array[6];
    //byte a7=array[7];
    //byte a8=array[8];
    //byte a9=array[9];
    //byte a10=array[10];
    //byte a11=array[11];
    //byte a12=array[12];
    //byte a13=array[13];
    //byte a14=array[14];
    //byte a15=array[15];


    ByteBuffer buffer=ByteBuffer.wrap(array);
    long k1=buffer.getLong();
    long k1_left=k1<<1;








return array;





}


public static byte[] shift_left(byte[] array,int value){

    BigInteger bigInt=new BigInteger(array);
    BigInteger shiftInt=bigInt.shiftLeft(value);
    byte[] shifted=shiftInt.toByteArray();
    return shifted;


}
    public static byte[] shift_right(byte[] array,int value){

        BigInteger bigInt=new BigInteger(array);
        BigInteger shiftInt=bigInt.shiftRight(value);
        byte[] shifted=shiftInt.toByteArray();
        return shifted;


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



    private static byte[] and_operator(byte[] array,byte[] array2){

    byte[]and_array=new byte[array.length];
    for(int i=0;i<array.length;i++){
        and_array[i]= (byte) (array[i] & array2[i]);




    }




return  and_array;
    }



    public static byte[] xor_operator(byte[] array,byte[] array2){

    byte[] xor_array=new byte[array.length];
        for(int i=0;i<array.length;i++){
            xor_array[i]= (byte) (array[i] ^ array2[i]);




        }

return xor_array;
    }

    private void fill_array(){

         byte[] s0=hexStringToByteArray(S0);
         S=hexStringToByteArray(SG);
        SA.add(S);
        for(int i=1;i<16;i++){
            byte[] temp =SA.get(i-1);
            rotl(temp,off,len,num);
            byte[] temp2=and_operator(SA.get(i-1),S);
            byte[] result=xor_operator(temp,temp2);
            SA.add(result);





        }

    }

private void fill_lr(){

    rt[0]=10;
    rt[1]=6;
    rt[2]=13;
    rt[3]=10;
    rt[4]=11;
    rt[5]=12;
    rt[6]=2;
    rt[7]=7;
    rt[8]=14;
    rt[9]=15;
    rt[10]=7;
    rt[11]=13;
    rt[12]=11;
    rt[13]=7;
    rt[14]=6;
    rt[15]=12;

    lt[0]=11;
    lt[1]=24;
    lt[2]=9;
    lt[3]=16;
    lt[4]=15;
    lt[5]=9;
    lt[6]=27;
    lt[7]=15;
    lt[8]=6;
    lt[9]=2;
    lt[10]=29;
    lt[11]=8;
    lt[12]=15;
    lt[13]=5;
    lt[14]=31;
    lt[15]=9;

}

    /**
     * input array [89][8] 89 words of 64 bits
     * @param N
     * @return
     */
    public byte[][] compress(byte[][] N){
    if(N.length!=89)
        System.out.println("Array length is not 89");

int c=16;
    int t=r*c;
    byte[][] A=N;



    for(int i=n;i<t+n-1;i++){
        int temp= (i-n)%16;
        byte[] temp2=xor_operator(SA.get(temp),A[i-n]);
        byte[] x=xor_operator(temp2,A[i-t0]);
        byte[] temp3=and_operator(A[i-t1],A[i-t2]);
        byte[] temp4=and_operator(A[i-t3],A[i-t4]);
        byte[] temp5=xor_operator(temp3,temp4);
        x=xor_operator(x,temp5);
        byte[] temp6 =shift_right(x,rt[temp]);
        x=xor_operator(temp6,x);
        byte[] temp7=shift_right(x,lt[temp]);
        x=xor_operator(x,temp7);

        for(int j=0;j<x.length;j++){
            A[i][j]=x[j];

        }

    }
        byte[][] C=new byte[16][8];
        for(int i=0;i<16;i++){

            for(int j=0;j<8;j++)
            C[i][j]=A[t=n-c+i][j];



        }



return C;
}


    public static void main(String[] args) {
        byte[] test=hexStringToByteArray(SG);
        System.out.println(test.length);
        for (byte y : test) {
            System.out.print(String.format("%x", y));
        }
        System.out.println();
        short a=0;
        short b=7;
        short c=1;
        rotl(test,a,b,c);
        for (byte y : test) {
            System.out.print(String.format("%x", y));

        }
        System.out.println();
    String t1="61";
       String t2="81";
        byte[] s1=hexStringToByteArray(t1);
        byte[] s2=hexStringToByteArray(t2);
        for (byte y : s1) {
            System.out.print(String.format("%x", y));

        }
        System.out.println();
        for (byte y : s2) {
            System.out.print(String.format("%x", y));

        }
        System.out.println();
        System.out.println(s2.length);

        byte[] s3=and_operator(s1,s2);


        for (byte y : s3) {
            System.out.print(String.format("%x", y));

        }
        System.out.println();
        System.out.println(s3.length);
        ArrayList<String> ada=new ArrayList<>();



        }



    }


