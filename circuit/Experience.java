package circuit;
import org.junit.Test;
import org.springframework.util.StopWatch;
import yao.Utils;
import org.apache.lucene.util.RamUsageEstimator;
public class Experience {
    public  int A = 8000;

    public  int B = 5000;
    public static int MAXINT = 2147483647;
    public static int SLEEPTIME = 10;
    public static void sleep(){
        try {
            Thread.sleep(SLEEPTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void compareBigTest () throws Exception {
        Compare f= new Compare();
        f.getCircuit();
        boolean[] a = Utils.int2byte(MAXINT);
        boolean[] b = Utils.int2byte(MAXINT);
        for (int i = 0; i < 32; i++) {
            System.out.print((a[i]) ? 1 : 0);
        }
        System.out.println("");
        for (int i = 0; i < 32; i++) {
            System.out.print((b[i]) ? 1 : 0);
        }

        boolean result= f.evaluateBigTest(a, b,true);
        System.out.println("");
        System.out.println((result) ? 1 : 0);
    }
    @Test
    public void compareTimeTest () throws Exception {
        Compare f= new Compare();
        f.getCircuit();
        boolean[] a = Utils.int2byte(MAXINT);
        boolean[] b = Utils.int2byte(MAXINT-1);
        for (int i = 0; i < 32; i++) {
            System.out.print((a[i]) ? 1 : 0);
        }
        System.out.println("");
        for (int i = 0; i < 32; i++) {
            System.out.print((b[i]) ? 1 : 0);
        }
        StopWatch stopWatch = new StopWatch("时间测试");
        stopWatch.start("时间测试");
        for (int i = 0; i < 100; i++) {
            boolean result= f.evaluateTimeTest(a, b,true);
        }
        stopWatch.stop();
        System.out.println("");
        System.out.println(stopWatch.prettyPrint());
    }

}
