import java.net.InetAddress;
import java.util.Scanner;

public class JavaNetScanner {
    private static String startIPAdress() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Start IP:");
        return scanner.next();
    }
    private static String endIPAdress() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("End   IP:");
        return scanner.next();
    }
    private static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }
    private static InetAddress longToIP(long ip) {
        byte[] octets = new byte[4];
        for (int i = 3; i >= 0; i--) {
            octets[i] = (byte) (ip & 0xff);
            ip >>= 8;
        }
        try {
            return InetAddress.getByAddress(octets);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static void scanIPRange(String startIP, String endIP) {
        try {
            InetAddress startAddress = InetAddress.getByName(startIP);
            InetAddress endAddress = InetAddress.getByName(endIP);

            for (long i = ipToLong(startAddress); i <= ipToLong(endAddress); i++) {
                InetAddress address = longToIP(i);
                System.out.println("Scanning: " + address.getHostAddress());
                if (address.isReachable(1000)) {
                    System.out.println("Host is reachable");
                } else {
                    System.out.println("Host is not reachable");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        scanIPRange(startIPAdress(),endIPAdress());
    }
}