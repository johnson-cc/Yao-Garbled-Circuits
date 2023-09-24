package circuit;

import yao.Utils;
import yao.gate.Gate;
import yao.gate.OrGate;
import yao.gate.Wire;
import yao.gate.XorGate;
public class SingleCompare {
    byte[][] lut_g1, lut_g2;
    Wire a, b, c, r1, r2;

    //A生成电路及混淆值
    public void getCircuit() throws Exception {
        System.out.println("Generate Compare Circuit");
        a = new Wire();
        b = new Wire();
        c = new Wire();

        r1 = new Wire();
        r2 = new Wire();

        Gate g1 = new XorGate(a, c, r1);
        Gate g2 = new OrGate(b, r1, r2);

        lut_g1 = g1.getLut();
        lut_g2 = g2.getLut();
        //Alice sent lut_g1,lut_g2 to Bob
        //Alice sent a,b,c to Bob

        System.out.println("Compare Circuit has been generated");
    }

    public boolean evaluate(boolean a_input, boolean b_input, boolean c_input) throws Exception {
        System.out.println("Evaluate Compare Circuit");

        //Bob选择a,b,c
        byte[] in_a = (a_input ? a.getValue1() : a.getValue0());
        byte[] in_b = (b_input ? b.getValue1() : b.getValue0());
        byte[] in_c = (c_input ? c.getValue1() : c.getValue0());

        Gate gate1 = new Gate(lut_g1);
        Gate gate2 = new Gate(lut_g2);

        byte[] ret1 = gate1.operate(in_c, in_a);
        byte[] ret2 = gate2.operate(ret1, in_b);


        System.out.println("Compare Circuit has been evaluated");

        //Bob发送ret2给Alice

        if(Utils.arraysAreEqual(ret2,r2.getValue0()))
            return false;
        else if(Utils.arraysAreEqual(ret2,r2.getValue1()))
            return true;


        return false;
    }
}
