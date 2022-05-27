# helpdesk_API_REST_FUL_mysql
Projeto Spring Boot API REST FUL Mysql  JWT.

HELP DESK 

Projeto Spring Boot utilizando Java no Back End, Sistema de Help Desk feito com conceito de integrar o JWT Security, Projeto completo onde tive 
prazer de aprimorar meus conhecimento em java e API.

API rodando Heroku, sistema funcional para micro empresas, LINK  https://help-desk-rest.herokuapp.com/

Entrada no sistema 

POST https://help-desk-rest.herokuapp.com/login

postman 

    {
    "email":"arthur@gmail.com",
    "senha":"123"
    }
    
Depois do Login e acessar o Bearer Token:
e só acessar os Enpoint

GET https://help-desk-rest.herokuapp.com/tecnicos    !lista todos tecnicos!
GET https://help-desk-rest.herokuapp.com/tecnicos/1  !buscar tecnico passando ID!
POST https://help-desk-rest.herokuapp.com/tecnicos   !create tecnico!

PUT https://help-desk-rest.herokuapp.com/tecnicos/1 !Update passando ID!
DELETE https://help-desk-rest.herokuapp.com/tecnicos/1 !delete usuario!

++EndPoint > só seguir a sequência do resumo acima depois de logado:

GET https://help-desk-rest.herokuapp.com/clientes
GET https://help-desk-rest.herokuapp.com/chamado





    
