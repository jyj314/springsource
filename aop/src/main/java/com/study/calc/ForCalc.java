package com.study.calc;

import org.springframework.stereotype.Component;
// 재귀호출로 factorial 구현
// 재귀호출 : 메소드 안에서 자신의 메소드를 다시 호출
//			기본단계와 재귀단계로 나뉨

// for 문으로 factorial 구현

@Component("forc")
public class ForCalc implements Calc {

   @Override
   public long factorial(long num) {
      long result = 1;
      
      for(int i=1;i<=num;i++) {
         result *= i;
      }
      return result;
   }
   
}