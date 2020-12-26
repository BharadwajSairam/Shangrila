package le.ac;

import java.security.MessageDigest;

public class HashGenerator {

	public static String getMD5Hash(String data) {
		String result = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes("UTF-8"));
			return bytesToHex(hash); // make it printable
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder sb = new StringBuilder();
		for (byte b : hash) {
			sb.append(String.format("%02X", b));
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	public static void main() {
		System.out.println(getMD5Hash("admin"));
	}
}