/**
 *
 *  @author Filipiuk Igor S7334
 *
 */

package zad1;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.lang.reflect.Method;

public class Calc {
	
	 private HashMap<String, Method> op = new HashMap<String,Method>();
	 private MathContext mc = new MathContext(7);
	
	public Calc(){		
		
		
         try {
			Method m = BigDecimal.class.getMethod("add",BigDecimal.class);
			 op.put("+",m);
			 m = BigDecimal.class.getMethod("subtract",BigDecimal.class);
			 op.put("-",m);
			 m = BigDecimal.class.getMethod("multiply",BigDecimal.class);
			 op.put("*",m);
			 m = BigDecimal.class.getMethod("divide",BigDecimal.class,MathContext.class);
			 op.put("/",m);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
			
	}
	
	public String doCalc(String cmd) {
							
		String[] items = cmd.split("\\s+");
        BigDecimal a = null;
        BigDecimal b = null;
        Method m = null;
        BigDecimal result = null;
        try
        {
            a = new BigDecimal(items[0]);
            b = new BigDecimal(items[2]);
            m = op.get(items[1]);
            result = (BigDecimal)m.invoke(a,b);
            return result.toString();
        }
        catch(IllegalArgumentException e)
        {
            try
            {
                result = (BigDecimal)m.invoke(a,b,mc);
                return result.toString();
            }
            catch(Exception e1)
            {
                return "Invalid command to calc";                
            }
        }
        catch (Exception e2)
        {
            return "Invalid command to calc";
        }
		 
		
	}
	
}
