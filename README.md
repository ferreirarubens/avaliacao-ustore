# avaliacao-ustore

foi construída uma api de object storage utilizando o framework spring boot, seguindo ao máximo os requisitos da documentação solicitada.

# url projeto

foi feito o deploy na plataforma web heroku, para que possam ser feitos os testes.
link base da aplicação: https://avaliacao-ustore.herokuapp.com

# documentação api rest
no link abaixo há uma documentação na ferramenta postman referente às requisições rest necessárias para acesso.
https://documenter.getpostman.com/view/5365597/rwgnxkt1

# requisitos
como padrão utilizo os seguintes parâmetros para o sistema: é necessário a criação de um bucket (arquivo de diretório). baseado nisso, toda a manipulação de arquivo é feita baseada num bucket
  
# métodos rest suportados
```create_bucket```: cria um diretório bucket, informando o nome

```upload_file```: faz o upload de um arquivo para o servidor / bucket obrigatório

```upload_file_name```: faz o upload de um arquivo para o servidor, escolhendo o nome do arquivo / bucket obrigatório

```upload_file```: faz o upload de um arquivo para o servidor /bucket obrigatório

```get_file```:  faz download do arquivo / metadata no header do response / bucket obrigatório / nome do arquivo é obrigatório

```update_file```: faz a atualização do arquivo(blob) no servidor / bucket obrigatório

```get_metadata```: retorna metadata do arquivo / bucket obrigatório / nome do arquivo é obrigatório 

```update_metadata```: atualiza o metadata do arquivo /  passando no header as chaves e valores, com as chaves obrigatóriamente contendo o prefixo "meta." /  bucket obrigatório /  nome do arquivo obrigatório

```find_by_metadata```:  retorna uma lista de todos os arquivos no bucket que tem o valor de uma determinada chave no metadata / bucket é obrigatório
    
