# Programmers.2017_KAKAOBLINDRECRUITMENT_Java_Cache

## 프로그래머스 2017 카카오 블라인드 리쿠르트 > 캐시

### 1. 문제 설명

문제: https://programmers.co.kr/learn/courses/30/lessons/17680

input으로 캐시 크기 int cacheSize와 도시이름 배열 String[] cities가 들어온다. 캐시교체 알고리즘은 LRU(Least Recently Uesd)를 사용한다. hit일 경우 실행시간은 1초, miss일 경우 실행시간은 5초 소요되며 실행시간을 계산하여 return하는 문제.

### 2. 풀이

케이스를 분류해서 진행하였다. 

* 캐시 크기가 0일 경우

  즉 캐시가 존재하지 않을 경우 항상 miss이며 ```cities.length*5```를 return하여 해결하였다.

* 캐시 크기가 0이 아닐 경우

  캐시는 HashMap을 이용하였다. Entry로는 도시이름, 사용된 시간으로 <String, Integer>형을 이용하였다.
  cities의 길이만큼 loop를 돌며 도시이름이 캐시에 존재한다면 캐시에서 key값 city에 맞는 value를 loop index로 지정해 주었고 실행시간+1을 적용, 존재하지 않는다면 +5후 캐시의 크기와 현재 캐시내 존재하는 요소의 개수를 비교하였다.
  캐시에 빈공간이 있다면 도시정보를 캐시에 추가한다. 하지만 빈공간이 없을 경우 캐시내 가장 일찍 사용했던 요소의 key값을 찾아내 값을 캐시에서 제거, 새로운 도시정보를 캐시에 추가하여서 구현하였다.


### 3. 배울 점

HashMap도 다른 자료구조와 같이 Iterator가 있다 생각하고 가장 오래된 도시정보를 찾는데 이용하려 하였다. 하지만 Iterator에서 제네릭이 다른 자료구조와는 다르게 <Key, Value>값인 Entry형 Iterator가 있었다. Entry에서 getKey()나, getValue()와 같은 메서드를 이용하니 이를 몰랐을 때 하던 짓...
```java
Iterator<String> it = map.keySet().iterator;
while(it.hasNext()) {
  String key = it.next();
  Integer value = map.get(key);
  ...
  ...
}
```
이 생각났다. 지금이라도 알아서 다행이다.

다른사람의 풀이를 보던 중
```boolean java.util.LinkedList.removeFirstOccurrence(Object o)```를 사용하는 코드를 봤다
파라미터로 넘긴 값이 리스트에 존재한다면 그 값을 제거하고 return true, 존재하지 않는다면 return false.이를 이용하여 hit인지 miss인지 한번에 확인 할 수 있고, LinkedList의 특징으로 인해 LRU 알고리즘을 사용시간을 기록하지 않고도 head가 가장 오래된 요소임을 알 수 있어 알고리즘이 상당히 간단해 진다.

알아두자.
