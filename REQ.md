
### Requisitos Funcionais do SolutioStockr

**1. Autenticação de Usuário:**

-   O sistema deve ter uma tela de login que solicita ao usuário as seguintes informações:
    -   Nome de usuário (User).
    -   Senha (Password).
-   O usuário só poderá acessar as funcionalidades do sistema após fazer login com sucesso.
-   O sistema deve realizar a validação das credenciais no backend, comparando-as com os registros no banco de dados.
-   Após um login bem-sucedido, o usuário será redirecionado para a tela de "Cadastro de Produto".

**2. Cadastro de Produto:**

-   A tela de cadastro de produto deve conter um formulário com os seguintes campos obrigatórios:
    -   Nome do produto.
    -   Fornecedor.
    -   Valor do produto.
-   Deve haver um botão "CADASTRAR" que permita ao usuário adicionar um novo produto ao sistema.
-   Quando o usuário clica em "CADASTRAR", os dados inseridos devem ser enviados ao backend para serem salvos no banco de dados (SQL SERVER, MySQL ou MongoDB).

**3. Listagem de Produtos:**

-   O sistema deve ter uma página que exibe uma tabela com a lista de produtos cadastrados no banco de dados.
-   Cada linha da tabela deve conter informações sobre um produto, incluindo nome, fornecedor e valor.
-   A tabela deve ter opções de "Edição" e "Remoção" para cada produto listado.

**4. Edição de Produtos:**

-   Para cada produto listado na tabela de produtos, deve haver uma opção de "Edição" que permita ao usuário editar os detalhes do produto.
-   A edição de um produto deve ser realizada em uma tela separada, que inclui um formulário preenchido com os dados atuais do produto.
-   Deve haver um botão de "Salvar" na tela de edição para atualizar as informações no banco de dados.

**5. Remoção de Produtos:**

-   Além da edição, o sistema deve oferecer uma opção de "Remoção" para cada produto listado, que permita ao usuário excluir o produto do sistema.
-   Ao clicar em "Remoção", o sistema deve confirmar a ação com o usuário antes de excluir o produto.

**6. Logout:**

-   O sistema deve incluir um botão "Logout" que permite ao usuário encerrar a sessão ativa no sistema.
-   Ao clicar em "Logout", o usuário deve ser redirecionado para a tela de login.

Estes são os requisitos funcionais do sistema TechSolutio, agora separados em itens individuais e sem a seção sobre tecnologias utilizadas. Certifique-se de que o sistema atenda a esses requisitos para concluir com sucesso o teste técnico.