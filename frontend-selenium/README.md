# Projeto de Testes Automatizados (Selenium) + Frontend

Este projeto contém uma aplicação web simples (React/HTML) e uma suíte de testes em Python usando Selenium. A ideia é validar o fluxo de cadastro e a navegação entre páginas.

## Tecnologias usadas
- **Frontend**: React + TypeScript (Vite) / HTML estático
- **Testes**: Python + Selenium + WebDriver (Chrome)

## Como rodar o projeto

### 1. Iniciar o Frontend
Instale as dependências e suba o servidor local (porta 5174):
```bash
cd frontend-selenium
npm install
npm run dev -- --port 5174
```
Acesse: `http://localhost:5174/`

### 2. Executar os Testes (Python)
Em outro terminal, instale as dependências e rode os scripts:
```bash
cd frontend-selenium
python -m pip install -r requirements.txt
python executar_testes.py
```
Para rodar apenas o teste de fluxo completo:
```bash
python executar_testes.py --ultimo
```

## Detalhes dos Testes

Os testes utilizam os seguintes dados fictícios:
- **Nome**: Pamella Oliveira
- **Telefone**: 8199999-9999
- **Email**: pamela@gmail.com
- **Senha**: nininha123

**Cenários cobertos:**
1.  **Interação de Campo**: `send_keys` e `clear` no campo Nome.
2.  **Validação de Atributos**: `get_attribute` (placeholders e tipos).
3.  **Estado dos Elementos**: `is_selected`, habilitado/visível.
4.  **Navegação**: `current_url` e redirecionamentos.
5.  **Ações Complexas**: `ActionChains` (hover).
6.  **Fluxo Completo**: Cadastro do início ao fim.

## Observações
- O backend (Java Spring Boot) deve estar rodando na porta **8080** para que o cadastro funcione realmente (`mvn spring-boot:run` na pasta raiz).
- Os testes acessam principalmente `http://localhost:5174/register.html`.
- Mensagens do Chrome/DevTools no terminal são normais.
