# language: pt
Funcionalidade: Restaurant

  @smoke
  Cenário: Registrar um novo restaurante
    Quando submeter um novo restaurante
    Então restaurante e registrado com sucesso

  Cenário: Buscar um restaurante existente
    Dado que um restaurante já foi publicado
    Quando requisitar a busca do restaurante
    Então o restaurante é exibido com sucesso

  Cenário: Listar restaurantes existente
    Dado que um restaurante já foi publicado
    Quando requisitar a lista de restaurantes
    Então os restaurante são exibidos com sucesso



  @ignore
  Cenário: Em desenvolvimento
    Dado passo em desenvolvimento