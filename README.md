# Profile Service

회원 정보를 관리하고, 프로필 이미지를 업로드/다운로드할 수 있는 **Spring Boot 기반 Profile 관리 서비스**입니다.  
AWS RDS(MySQL), AWS Parameter Store를 활용해 **운영 환경에서도 안전하게 설정을 관리**하도록 구성했습니다.

---

## 주요 기능

- 회원 등록
- 회원 단건 조회
- 회원 프로필 이미지 업로드
- 회원 프로필 이미지 다운로드

## 과제 제출 요구 사항

### Lv0 - 요금 폭탄 방지 AWS Budget 설정
<details>
    <summary>AWS Budgets</summary>
<img width="1007" height="685" alt="Image" src="https://github.com/user-attachments/assets/ee05bb16-f447-45df-9998-3c4859db065d" />
</details>

### Lv1 - 네트워크 구축 및 핵심 기능 배포
- EC2 퍼블릭 IP: 52.79.253.182

### Lv2 - DB 분리 및 보안 연결하기
- /actuator/info: http://52.79.253.182:8080/actuator/info
<details>
    <summary>보안그룹</summary>
<img width="1054" height="567" alt="Image" src="https://github.com/user-attachments/assets/c34444f9-6807-4da2-8f16-ed6a9e5bb6b6" />
</details>

### Lv3 - 프로필 사진 기능 추가와 권한 관리
- presigned URL 과제 제출란에 첨부
