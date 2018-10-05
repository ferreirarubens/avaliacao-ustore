# avaliacao-ustore

Foi construída uma API de Object Storage utilizando o framework Spring Boot, seguindo ao máximo os requisitos da documentação solicitada.

# documentação API Rest
No link abaixo há uma documentação na ferramenta Postman referente às requisições rest necessárias para acesso.
https://documenter.getpostman.com/view/5365597/RWgnXKt1

# requisitos
Como padrão utilizo os seguintes parâmetros para o sistema:
  - é necessário a criação de um bucket (arquivo de diretório)
  > baseado nisso, toda a manipulação de arquivo é feita baseada num bucket
  
# Métodos REST Suportados
  - create_bucket
    > Cria um diretório bucket, informando o nome
    
  - upload_file
    > Faz o upload de um arquivo para o servidor
    > bucket obrigatório
    
  - upload_file_name
    > Faz o upload de um arquivo para o servidor, escolhendo o nome do arquivo
    > bucket obrigatório
    
  - upload_file
    > Faz o upload de um arquivo para o servidor
    > bucket obrigatório
    
  - get_file
    > Faz download do arquivo
    > Metadata no header do response
    > bucket obrigatório
    > nome do arquivo é obrigatório
    
  - update_file
    > Faz a atualização do arquivo(Blob) no servidor
    > bucket obrigatório
    
  - get_metadata
    > Retorna metadata do arquivo
    > bucket obrigatório
    > nome do arquivo é obrigatório
  
  - update_metadata
    > atualiza o metadata do arquivo
    > passando no header as chaves e valores, com as chaves obrigatóriamente contendo o prefixo "meta."
    > bucket obrigatório
    > nome do arquivo obrigatório
  
  - find_by_metadata
    > retorna uma lista de todos os arquivos no bucket que tem o valor de uma determinada chave no metadata
    > bucket é obrigatório
    
