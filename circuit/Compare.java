package circuit;

import org.apache.lucene.util.RamUsageEstimator;
import yao.Utils;
import yao.gate.*;
public class Compare {

    byte[][] lut_g1, lut_g2;
    Wire a,b,c,r1,r2;

    public boolean evaluateBigTest(boolean[] a_input,boolean[] b_input,boolean c_input) throws Exception {
        System.out.println("Evaluate Compare Circuit");
        int length = a_input.length;
        //通信量
        long communication = 0;

        //Alice sent lut_g1,lut_g2 to Bob
        //Alice sent a,b,c to Bob
        //Alice sent a_input to Bob
        communication+= RamUsageEstimator.shallowSizeOf(lut_g1);
        communication+= RamUsageEstimator.shallowSizeOf(lut_g2);
        communication+= RamUsageEstimator.shallowSizeOf(a);
        communication+= RamUsageEstimator.shallowSizeOf(b);
        communication+= RamUsageEstimator.shallowSizeOf(c);
        communication+= RamUsageEstimator.shallowSizeOf(a_input);

        //Bob选择a,b,c
        byte[][] in_a = new byte[length][];
        byte[][] in_b = new byte[length][];
        byte[] in_c = (c_input ? c.getValue1() : c.getValue0());
        for (int i = 0; i < length; i++) {
            in_a[i] = (a_input[i] ? a.getValue1() : a.getValue0());
            in_b[i] = (b_input[i] ? b.getValue1() : b.getValue0());
        }

        Gate gate1 = new Gate(lut_g1);
        Gate gate2 = new Gate(lut_g2);

        byte[][] ret1 = new byte[length][];
        byte[][] ret2 = new byte[length][];
        for (int i = 0; i < length; i++) {
            ret1[i] = gate1.operate(in_c, in_a[i]);
            ret2[i] = gate2.operate(ret1[i], in_b[i]);
        }
        System.out.println("Compare Circuit has been evaluated");

        //Bob发送ret2给Alice
        communication+= RamUsageEstimator.shallowSizeOf(ret2);
        System.out.println("communication: "+communication);
        boolean[] result = new boolean[length];
        for (int i = length - 1; i > -1; i--) {
            if (Utils.arraysAreEqual(ret2[i], r2.getValue0()))
                result[i] = false;
            else if (Utils.arraysAreEqual(ret2[i], r2.getValue1()))
                result[i] = true;
            if (result[i])
                return true;
        }

//        for(int i= length;i>-1;i--){
//            if(Utils.arraysAreEqual(ret2[i],r2.getValue0()))
//                return false;
//            else if(Utils.arraysAreEqual(ret2[i],r2.getValue1()))
//                return true;
//        }

        return false;
    }

    public boolean evaluateTimeTest(boolean[] a_input,boolean[] b_input,boolean c_input) throws Exception {
        System.out.println("Evaluate Compare Circuit");
        int length = a_input.length;

        //Alice sent lut_g1,lut_g2 to Bob
        //Alice sent a,b,c to Bob
        //Alice sent a_input to Bob
        Experience.sleep();

        //Bob选择a,b,c
        byte[][] in_a = new byte[length][];
        byte[][] in_b = new byte[length][];
        byte[] in_c = (c_input ? c.getValue1() : c.getValue0());
        for(int i=0;i<length;i++){
            in_a[i] = (a_input[i] ? a.getValue1() : a.getValue0());
            in_b[i] = (b_input[i] ? b.getValue1() : b.getValue0());
        }

        Gate gate1 = new Gate(lut_g1);
        Gate gate2 = new Gate(lut_g2);

        byte[][] ret1 = new byte[length][];
        byte[][] ret2 = new byte[length][];
        for(int i=0;i<length;i++){
            ret1[i] = gate1.operate(in_c, in_a[i]);
            ret2[i] = gate2.operate(ret1[i], in_b[i]);
        }
        System.out.println("Compare Circuit has been evaluated");

        //Bob发送ret2给Alice
        Experience.sleep();
        boolean[] result = new boolean[length];
        for(int i=length-1;i>-1;i--){
            if(Utils.arraysAreEqual(ret2[i],r2.getValue0()))
                result[i] = false;
            else if(Utils.arraysAreEqual(ret2[i],r2.getValue1()))
                result[i] = true;
            if (result[i])
                return true;
        }

//        for(int i= length;i>-1;i--){
//            if(Utils.arraysAreEqual(ret2[i],r2.getValue0()))
//                return false;
//            else if(Utils.arraysAreEqual(ret2[i],r2.getValue1()))
//                return true;
//        }

        return false;

    }

    //A生成电路及混淆值
    public void getCircuit() throws Exception {
        System.out.println("Generate Compare Circuit");
        a=new Wire();
        b=new Wire();
        c=new Wire();

        r1 = new Wire();
        r2 = new Wire();

        Gate g1 = new XorGate(a, c, r1);
        Gate g2 = new AndGate(b, r1, r2);

        lut_g1 = g1.getLut();
        lut_g2 = g2.getLut();
        //Alice sent lut_g1,lut_g2 to Bob
        //Alice sent a,b,c to Bob

        System.out.println("Compare Circuit has been generated");
    }

    public boolean evaluate(boolean[] a_input,boolean[] b_input,boolean c_input) throws Exception {
        System.out.println("Evaluate Compare Circuit");
        int length = a_input.length;
        //Bob选择a,b,c
        byte[][] in_a = new byte[length][];
        byte[][] in_b = new byte[length][];
        byte[] in_c = (c_input ? c.getValue1() : c.getValue0());
        for(int i=0;i<length;i++){
            in_a[i] = (a_input[i] ? a.getValue1() : a.getValue0());
            in_b[i] = (b_input[i] ? b.getValue1() : b.getValue0());
        }

        Gate gate1 = new Gate(lut_g1);
        Gate gate2 = new Gate(lut_g2);

        byte[][] ret1 = new byte[length][];
        byte[][] ret2 = new byte[length][];
        for(int i=0;i<length;i++){
            ret1[i] = gate1.operate(in_c, in_a[i]);
            ret2[i] = gate2.operate(ret1[i], in_b[i]);
        }
        System.out.println("Compare Circuit has been evaluated");

        //Bob发送ret2给Alice
        boolean[] result = new boolean[length];
        for(int i=length-1;i>-1;i--){
            if(Utils.arraysAreEqual(ret2[i],r2.getValue0()))
                result[i] = false;
            else if(Utils.arraysAreEqual(ret2[i],r2.getValue1()))
                result[i] = true;
            if (result[i])
                return true;
        }

//        for(int i= length;i>-1;i--){
//            if(Utils.arraysAreEqual(ret2[i],r2.getValue0()))
//                return false;
//            else if(Utils.arraysAreEqual(ret2[i],r2.getValue1()))
//                return true;
//        }

        return false;

    }
}
