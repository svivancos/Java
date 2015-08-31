package ev2;
import java.util.Date;
public class U03E01 {
	
    @SuppressWarnings("deprecation")
	public static void main(String[] args)
    {
        DNIUtils dniu = new DNIUtils();
        
        //System.out.println(dniu.letraDNI("12345678"));
        //System.out.println(dniu.checkDNI("123456"));
        //System.out.println(dniu.checkDNI("12345678"));
        //System.out.println(dniu.checkDNI("12345678Z"));
        //System.out.println(dniu.checkDNI("12345678A"));
        //System.out.println(dniu.checkDNI("123456789Z"));
        
        System.out.println(dniu.edad(new Date("01/18/1991")));
    }    
}
