package ru.geekbrains.webstore.service;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
@RequiredArgsConstructor
public class AspectStatService {

  @Getter
  private Map<String, Long> durationMap = new HashMap<>();

  @Around("execution(public * ru.geekbrains.webstore.service.*.*(..))")
  public Object getStatistic(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    String classCalled = proceedingJoinPoint.getTarget().getClass().getSimpleName();
    long begin = System.currentTimeMillis();
    Object out = proceedingJoinPoint.proceed();
    long end = System.currentTimeMillis();
    long duration = end - begin;
    if (durationMap.containsKey(classCalled)) {
      durationMap.put(classCalled, durationMap.get(classCalled) + duration);
    } else {
      durationMap.put(classCalled, duration);
    }
    return out;
  }
}
