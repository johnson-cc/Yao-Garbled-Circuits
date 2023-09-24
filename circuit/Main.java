
package circuit;
import org.springframework.util.StopWatch;
import yao.Utils;


public class Main {
    public static void main(String [] args) throws Exception
	{
		Compare f= new Compare();
		f.getCircuit();
		boolean[] a = Utils.int2byte(90);
		boolean[] b = Utils.int2byte(830);
		for (int i = 0; i < 32; i++) {
			System.out.print((a[i]) ? 1 : 0);
		}
		System.out.println("");
		for (int i = 0; i < 32; i++) {
			System.out.print((b[i]) ? 1 : 0);
		}

		boolean result= f.evaluate(a, b,true);
		System.out.println("");
		System.out.println((result) ? 1 : 0);
//
//		 FullAdder f1= new FullAdder();
//
//                f1.getCircuit();
//
//
//                boolean FA_ab_result[]= f1.evaluate(true, false, true);
//
//
//                f1.getCircuit();
//
//
//                boolean FA_abc_result[]= f1.evaluate(FA_ab_result[1], false, true);
//
//                System.out.println("");
//                System.out.print("result of 3-integer Full Adder: ");
//                System.out.println((FA_abc_result[1]) ? 1 : 0);
//
//                System.out.println("");
//
//
//                IntEquality f= new IntEquality();
//
//
//                f.getCircuit();
//
//
//                boolean ab_result= f.evaluate(true, true, true);
//
//
//                f.getCircuit();
//
//
//                boolean abc_result= f.evaluate(ab_result, false, true);
//
//
//                System.out.println("");
//                System.out.print("result of 3-integer equality: ");
//                System.out.println((abc_result) ? 1 : 0);
                
                
                
               
	}
}
