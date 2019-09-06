import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		int cacheSize = 3;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};	//	return 50
//		int cacheSize = 3;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}; // return 21
//		int cacheSize = 2;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}; // return 60
//		int cacheSize = 5;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"}; // return 52
//		int cacheSize = 2;
//		String[] cities = {"Jeju", "Pangyo", "NewYork", "newyork"}; // return 16
//		int cacheSize = 0;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"}; // return 25
		
		System.out.println(new Solution().solution(cacheSize, cities));
	}

}

class Solution {
	  public int solution(int cacheSize, String[] cities) {
	      int answer = 0;
	      // 캐시가 없다면 모든 실행시간 5
	      if (cacheSize == 0)
	    	  return cities.length*5;
	      
	      // removeFirstOccurrence(arg[0]) : 첫번째 등장하는 arg[0] 요소를 삭제, arg[0]이 있었다면 true, 없었다면 false
//	      boolean hit = ((LinkedList<String>) queue).removeFirstOccurrence(cities[0].toUpperCase());
	      
	      LinkedHashMap<String, Integer> cache = new LinkedHashMap<>();
	      for (int i = 0; i < cities.length; i++) {
	    	  String city = cities[i].toLowerCase();
	    	  
	    	  if (cache.containsKey(city)) { // hit +1
	    		  answer++;
	    		  cache.put(city, i);
	    		  continue;
	    	  }
	    	  
	    	  if (cache.size() < cacheSize) { // miss and put
	    		  answer += 5;
	    		  cache.put(city, i);
	    	  } else { // miss and change by LRU alg.
	    		  String leastUsedCity="";
	    		  int min = cities.length;
	    		  for (Iterator<Entry<String, Integer>> it = cache.entrySet().iterator(); it.hasNext(); ) {
	    			  Entry<String, Integer> entry = it.next();
	    			  if (entry.getValue() < min) {
	    				  min = entry.getValue();
	    				  leastUsedCity = entry.getKey();
	    			  }
	    		  }
	    		  
	    		  cache.remove(leastUsedCity);
	    		  answer += 5;
	    		  cache.put(city, i);
	    	  }
	      }
	      
	      return answer;
	  }
}