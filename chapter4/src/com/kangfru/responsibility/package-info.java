package com.kangfru.responsibility;
/**
 * data에서 책임을 위한 설계로 변경한 클래스
 * 1. 이 객체가 어떤 데이터를 포함해야 하는가?
 * 2. 이 객체가 데이터에 대해 수행해야 하는 오퍼레이션은 무엇인가?
 * 위 두 가지 질문으로 리팩토링을 진행한다.
 *
 * 변경 이후의 예제 역시 캡슐화가 진행되었지만 결합도가 높고 모자란 캡슐화를 보여준다.
 * 캡슐화는 단순히 객체 내부의 데이터를 외부로 감추는 것 이상의 의미를 가진다.
 * 캡슐화는 변경될 수 있는 어떤 것이라도 감추는 것을 의미한다.
 */