package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    /*原先这里是Integer，问题在于Integer在实现的时候范围在（-128，127）内会从已有缓存
    * 中取值，所以用==能正常运行；但是超出范围后会新建类，此时二者的地址不同，故用==判断会出错
    * */
    public static boolean isSameNumber(int a, int b) {
        return a == b;
    }
}
