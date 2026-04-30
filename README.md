# 🐾 RRS - 펫시터 예약 중개 서비스

반려견 보호자와 펫시터를 연결하는 예약 중개 웹 서비스입니다.

## 📅 개발 기간
2024.10.11 ~ 2025.06.10

## 👥 팀 구성
3인 팀 프로젝트 (본인: 예약 시스템 담당)

## 🛠 기술 스택
| 구분 | 기술 |
|------|------|
| Backend | Java, Spring Boot, Spring Security, JPA |
| Frontend | TypeScript, React |
| Database | MySQL |
| API | 카카오 소셜 로그인, 메일 발송 |

## ✨ 주요 기능
- **회원 / 인증** : 소셜 로그인(카카오 OAuth2), 중복 체크, 메일 인증
- **펫시터 예약** : 요일별 가능 날짜 설정, 고객 희망 시간 매칭, 예약 이력 관리
- **반려견 관리** : 반려견 프로필 등록 및 건강 기록 관리
- **산책 서비스** : 산책 예약 및 기록 관리
- **리뷰** : 서비스 이용 후 펫시터 리뷰 작성
- **커뮤니티** : 게시글 작성, 댓글, 좋아요, 첨부파일 업로드
- **Todo 리스트** : 커스텀 캘린더 기반 일정 관리
- **고객센터** : 공지사항, 이용 가이드, 고객 문의
- **이벤트** : 이벤트 페이지 관리

## 📁 Repository
- [Backend (rrs_was)](https://github.com/RRS-PJ/rrs_was) - Java / Spring Boot
- [Frontend (rrs_web)](https://github.com/RRS-PJ/rrs_web) - TypeScript / React

## 💡 트러블슈팅
- **문제**: 펫시터 가능 시간과 고객 예약 시간 중복 발생
- **해결**: SQL 쿼리로 시간 슬롯 충돌 방지 로직 구현
