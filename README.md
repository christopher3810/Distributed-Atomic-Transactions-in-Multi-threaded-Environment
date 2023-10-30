분산 처리 환경에서 트랜잭션의 atomic 성을 보장하려면 좀 더 복잡한 전략과 도구가 필요함. 

분산 트랜잭션을 처리하기 위한 여러 전략 및 패턴이 존재함 

Two-Phase Commit, Saga Pattern 등

각 시나리오별로 분산 트랜잭션 문제와 해결 방법에 대해 구현 및 테스트 진행

## 시나리오

---

### 결제 상황

>**Note**
> 동일한 사용자 정보로 Request 가 들어 왔을시 Request 처리는 하나만 처리되어야 함.

이후 되고 나면

>**Note**
> 하나의 서비스에서 결제를 처리하고 다른 서비스에서 주문을 생성하는 시나리오에서 결제는 성공했지만 주문 생성에 실패하는 문제. \
>오류 발생 시 보상 트랜잭션을 실행되어야 함.

>**Note**
> 특정 상황에 대한 기능적 구현 및 시나리오 상의 문제 해결을 위한 임의이 시나리오를 채택. \
> 일반적인 시나리오라고 가정.
