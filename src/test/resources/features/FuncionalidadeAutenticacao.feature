# language: pt
Funcionalidade: Autorizacao

  Cenário: Acessar uma rota protegida com sucesso
    Dado Eu tenho um token de autenticação valido
    Quando Eu faço uma solicitação GET para "/api/login" com o token de autenticação
    Então Eu devo receber um código de resposta 200
