package in.co.rays.proj4.model;

import java.util.HashMap;
import java.util.Set;

import in.co.rays.proj4.util.HTMLUtility;
        

public class Semester {
	public static void main(String[] args) {
		testGetListByMap();
	}

	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 169px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}
	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<>();
		map.put("1st", "1st");
		map.put("2nd", "2nd");

		String selectedValue = null;
		String htmlSelectFromMap = HTMLUtility.getList("semester", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}
}
