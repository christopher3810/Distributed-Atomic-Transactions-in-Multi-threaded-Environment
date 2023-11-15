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


### 문제 해결 방법

#### 1. Distributed Locking

---

![distributedLock](https://github.com/christopher3810/Distributed-Atomic-Transactions-in-Multi-threaded-Environment/assets/61622657/c828a57f-f457-415f-bd63-ae23231ced77)
출처 : [알리바바 클라우드](https://www.alibabacloud.com/blog/the-technical-practice-of-distributed-locks-in-a-storage-system_597141)

독립 실행형 환경에서 공유 리소스가 여러 스레드 또는 프로세스가 동시에 공유 리소스를 읽고 쓰면서 발생하는 데이터 손상을 방지하기 위한 상호 제외 기능을 제공할 수 없는 경우, 

시스템에는 타사에서 제공하는 상호 제외 기능이 필요함. 

대부분의 경우 상호 제외 기능을 제공하는 것은 커널 또는 클래스 라이브러리.

> 락 = 리소스 + 동시성 제어 + 소유권 표시

락 종류

> 스핀락 = BOOL + CAS(낙관적 잠금)

> 뮤텍스 = BOOL + CAS + 알림(비관적 잠금)

분산 환경에서 여러 서비스가 동일한 리소스에 동시에 액세스하지 않도록 하는 잠금.

쉽게 구현할 수 있으며, 동시성 문제를 효과적으로 해결할 수 있음.

데드락 발생 가능성이 있으며, 확장성에 제한이 있을 수 있음.

#### 2. Two-Phase Commit

---

![2phaseCommit](https://github.com/christopher3810/Distributed-Atomic-Transactions-in-Multi-threaded-Environment/assets/61622657/4bc242e0-0845-41fa-834c-7644bc4ddb52)
출처 : [baeldung](https://www.baeldung.com/cs/saga-pattern-microservices)

Two-Phase Commit protocol (2PC)은 분산 트랜잭션을 구현하는 데 널리 사용되는 패턴.

마이크로서비스 아키텍처에서 이 패턴을 사용하여 분산 트랜잭션을 구현할 수 있음.

The Two-Phase Commit protocol 에는 트랜잭션 제어를 담당하고 트랜잭션을 관리하는 로직을 포함하는 코디네이터 구성 요소가 있음.

다른 구성 요소는 로컬 트랜잭션을 실행하는 참여 노드(e.g., the microservices)임

**단점**

2PC는 분산 트랜잭션을 구현하는 데 유용하지만 다음과 같은 단점이 있음

트랜잭션의 책임은 코디네이터 노드에 있으며, 이 노드가 단일 장애 지점이 될 수 있음.

다른 모든 서비스는 가장 느린 서비스가 확인을 완료할 때까지 기다려야 함.

따라서 트랜잭션의 전반적인 성능은 가장 느린 서비스에 의해 좌우됨.


2PC은 많은 통신과 코디네이터에 대한 의존성으로 인해 설계상 느림.

따라서 여러 서비스가 포함된 마이크로서비스 기반 아키텍처에서는 확장성 및 성능 문제가 발생할 수 있음.

2PC은 NoSQL 데이터베이스에서 지원되지 않음.

따라서 하나 이상의 서비스가 NoSQL 데이터베이스를 사용하는 마이크로서비스 아키텍처에서는 2PC을 적용할 수 없음.


#### 3. Saga Pattern

---

각 서비스가 로컬 트랜잭션만을 사용하게 하고, 비즈니스 로직의 일관성을 유지하기 위해 보상 트랜잭션을 실행

서비스 간에 강한 결합이 없으므로 확장성이 높음. 시스템 전체가 블로킹되지 않아 성능이 좋음.

보상 트랜잭션 로직을 구현해야 하며. 롤백이 항상 가능한 상태를 보장하기 어려울 수 있음.