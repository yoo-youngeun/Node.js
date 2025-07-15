## 🚀 Node.js Study Project

Node.js를 활용한 기본 웹 서버 구현과 HTTP 요청/응답 처리, 라우팅 및 비동기 처리 구조를 연습하기 위한 학습용 레포지토리입니다.

---

### 🧰 기술 스택

- **Node.js** (v14 이상 권장)  
- **Express.js** (간단한 기본 웹 서버 구현)  
- **npm** or **yarn** 기반 패키지 관리  
- **JavaScript** (ES6+)
- **코드 린팅** (ESLint 등 사용 시 추가) 

---

### 📂 프로젝트 구조  

Node.js/   
├── src/   
│ ├── index.js # 서버 진입점   
│ ├── routes/   
│ │ └── index.js # 기본 라우터 정의   
│ ├── controllers/   
│ │ └── homeController.js # 컨트롤러 예제   
│ └── middleware/   
│ └── logger.js # 요청 로깅 미들웨어 예제   
├── package.json   
├── .gitignore   
└── README.md   


### ⚙️ 실행 방법  
의존성 설치:  
```
npm install
# 또는
yarn
```

개발 서버 시작:
```
npm start
# 또는
yarn start
```
브라우저 확인:
```
http://localhost:3000
```
