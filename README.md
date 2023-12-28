# 코코톤 TEAM5 - 추억네컷 📸🎞️

> *"추억이 더 아름다워지는 공간, **추억네컷**으로 특별한 순간을 공유하세요."*
> 

## 🛠️ 개발 환경

| 통합 개발 환경 | IntelliJ |
| --- | --- |
| 배포 | AWS EC2(Ubuntu), Nginx |
| 데이터베이스 | AWS RDS(MySQL) |
| Spring 버전 | 3.0.11 |
| Project 빌드 관리 도구 | Gradle |
| CI/CD 툴 | Github Actions / Docker |
| ERD 다이어그램 툴 | MySQL Workbench |
| Java version | Java 17 |
| 패키지 구조 | 계층형 패키지 구조 |
| 파일 업로드  | AWS S3 |
| 기타 | JPA, Spring Security |

### 시스템 아키텍처

<img src="https://github.com/COKOTHON2023-TEAM5/Memory4Cut-Server/assets/80024278/5e987196-d74c-40d9-b84a-e6eb71bcb450" width=790 />

### 프로젝트 폴더 구조

```
📂 Memory4CutServer

🗂 java/cokothon/Memory4CutServer
   📂 domain
	🗂 controller
	🗂 dto
	🗂 entity
	🗂 infrastructure
	🗂 service
   📂 global
	🗂 common
	   🗂 advice
	   🗂 exception
	   🗂 response
	🗂 config
	🗂 external
🗂 resources
  application-local.yml
  application-dev.yml

```

### API 명세서
<img src="https://github.com/COKOTHON2023-TEAM5/Memory4Cut-Server/assets/80024278/63c1f8a0-1c89-4936-b659-b08e20ec6d19" width=890 />


### 데이터베이스 ERD
<img src="https://github.com/COKOTHON2023-TEAM5/Memory4Cut-Server/assets/80024278/bb4828c5-f526-4710-ae0c-bae66adb8a51" width=600 />
