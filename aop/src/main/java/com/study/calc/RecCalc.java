package com.study.calc;

import org.springframework.stereotype.Component;

@Component("rec")
public class RecCalc implements Calc {

	@Override
	public long factorial(long num) {
		if(num ==0) {
			return 1;
			
		}else {
			return num * factorial(num-1);
		}
	}

}
