## Chapter 5. 책임 할당하기
### 01. 책임 주도 설계를 향해
데이터 중심의 설계에서 책임 중심의 설계로 전환하기 위해서는 다음의 두 가지 원칙을 따라야 한다.
- 데이터보다 행동을 먼저 결정하라
  - 객체에게 중요한 것은 데이터가 아니라 외부에 제공하는 행동이다.
  - 데이터는 객체가 책임을 수행하는 데 필요한 재료를 제공할 뿐이다.
- 협력이라는 문맥 안에서 책임을 결정하라
  - 책임의 품질은 협력에 적합한 정도로 결정된다.
  - 책임은 객체의 입장이 아니라 객체가 참여하는 협력에 적합해야 한다.
  - 다시 말해서 메시지를 전송하는 클라이언트의 의도에 적합한 책임을 할당해야 한다.
  - 올바른 객체지향 설계는 클라이언트가 전송할 메시지를 결정한 후에야 비로소 객체의 상태를 저장하는 데 필요한 내부 데이터에 관해 만든다.
