# Counter Clojure Project

Exemplo de um contador implementado com:

- **Backend:** Clojure + Datomic, expondo APIs REST:
  - `GET /counter/count` → busca o valor atual
  - `PUT /counter/increment` → incrementa o valor
  - `DELETE /counter/reset` → reseta o valor
- **Frontend:** ClojureScript + Reagent + Re-frame, consumindo as APIs do backend

Frontend: **http://localhost:9000**  
Backend: **http://localhost:3000**

> ⚠️ Ambos os servidores devem estar rodando simultaneamente:
> 1. Backend: `routes.router.clj` na porta 3000
> 2. Frontend: `server.core.cljs` via Shadow CLJS na porta 9000

---

## Funcionalidades

- Mostrar o valor atual do contador  
- Incrementar ou resetar o contador com botões  
- Atualização automática após cada ação  

---

## Instalação e Execução

### Backend
1. Rode o servidor backend (`routes.router.clj`) na porta 3000:

```bash
clj -M -m routes.router
```

### Frontend
1. Instale dependências Node:

```bash
npm install
```

2. Rode o frontend via Shadow CLJS:

```bash
npx shadow-cljs watch app
```

3. Abra o navegador:

```
http://localhost:9000