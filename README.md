# Camping Gear Rental Service - Observer Pattern

이 프로젝트는 Observer 디자인 패턴을 사용하여 구현한 캠핑 장비 대여 시스템입니다.

## 프로젝트 구조

```
src/
├── observer/
│   ├── Subject.java              # 옵저버를 등록/해제하고 이벤트를 알리는 인터페이스
│   └── Observer.java             # 이벤트를 수신하는 옵저버 인터페이스
├── service/
│   ├── GearRentalService.java    # 장비 대여 서비스 (Subject 구현)
│   └── GearReturnService.java    # 장비 반납 서비스 (Subject 구현)
├── event/
│   ├── GearRented.java           # 장비 대여 이벤트 데이터
│   └── GearReturned.java         # 장비 반납 이벤트 데이터
├── observer/impl/
│   ├── InventoryObserver.java    # 재고 관리를 담당하는 옵저버
│   ├── PaymentObserver.java      # 결제 처리를 담당하는 옵저버
│   └── GearMaintenanceObserver.java  # 장비 점검을 담당하는 옵저버
└── Main.java                     # 데모 실행 클래스
```

## 주요 기능

### Subject (알림 주체)
- **GearRentalService**: 장비 대여를 처리하고 관련 이벤트를 발생시킵니다.
- **GearReturnService**: 장비 반납을 처리하고 관련 이벤트를 발생시킵니다.

### Observers (관찰자)
- **InventoryObserver**: 장비 대여/반납 시 재고를 관리합니다.
- **PaymentObserver**: 장비 대여 시 결제를 확인하고, 반납 시 늦은 반납료를 처리 및 예금을 반환합니다.
- **GearMaintenanceObserver**: 장비 반납 시 점검 대기열에 추가합니다.

## 실행 방법

### 컴파일
```bash
javac src/**/*.java
```

### 실행
```bash
java -cp src Main
```

## 디자인 패턴: Observer

Observer 패턴을 사용하여 시스템이 느슨하게 결합되어 있습니다:

1. **Subject (주체)**: 상태 변경을 알려야 하는 서비스들 (GearRentalService, GearReturnService)
2. **Observer (관찰자)**: 변경 사항을 받고 싶어하는 객체들 (InventoryObserver, PaymentObserver, GearMaintenanceObserver)

### 장점
- 서비스와 관찰자 간 느슨한 결합
- 런타임에 관찰자를 추가/제거 가능
- 단일 책임 원칙 준수 (각 관찰자는 특정 작업에만 집중)

## 실행 예시

프로그램을 실행하면 다음과 같은 시나리오를 볼 수 있습니다:

1. 장비 대여: 재고 감소 및 결제 확인
2. 정시 반납: 재고 증가 및 예금 반환
3. 늦은 반납: 늦은 반납료 처리 및 예금 반환
4. 장비 점검: 반납된 장비를 점검 대기열에 추가

