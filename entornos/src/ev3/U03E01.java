package ev3;
import java.util.Date;
public class U03E01 {
	
    @SuppressWarnings("deprecation")
	public static void main(String[] args)
    {
        UtilidadesDNI dniu = new UtilidadesDNI();
        
        System.out.println(dniu.getLetra("12345678"));
        System.out.println(dniu.check("123456"));
        System.out.println(dniu.check("12345678"));
        System.out.println(dniu.check("12345678Z"));
        System.out.println(dniu.check("12345678A"));
        System.out.println(dniu.check("123456789Z"));
        
        System.out.println(dniu.getEdad(new Date("01/18/1991")));
    }    
}
