package lambda;

import java.util.ArrayList;
import java.util.List;

public class CumstomerEx {
	public static void main(String[] args) {
		//고객 명단 출력
		//총 여행 경비 계산
		//고객 중 20세 이상인사람의 이름을 정렬하여 출력
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("이순신",40,100));
		customerList.add(new Customer("김유신",20,100));
		customerList.add(new Customer("홍길동",13,50));
		customerList.add(new Customer("성춘향",18,70));
		
		//고객 명단 출력 - map, forEach
		customerList.stream().map(c -> c.getName()).forEach(n -> System.out.println(n));
		
		//총 여행 경비 계싼 - map, sum
		long total = customerList.stream().mapToInt(c -> c.getPrice()).sum();
		System.out.println("총 여행 경비 : "+total);
		
		//고객 중 20세 이상인 사람의 이름을 정렬하여 출력
		System.out.println("--- 20세 이상 고객 명단 ---");
		customerList.stream().filter(c -> c.getAge() >=20).map(c -> c.getName()).sorted().forEach(c -> System.out.println(c));
		
	}
}
