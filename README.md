# ufmg.reuso.software
Repositório que contêm o código reutilizado do SimulES

Ana Luiza de Avelar Cabral, Herico Goncalves Valiati, Matheus Henrique Antunes Lima e Ramiro Costa Lopes

O projeto foi desenvolvido dentro do ambiente Eclipse IDE, utilizando-se Aspectj e a biblioteca commons-io-2.6 para viabilizar a conexão com serviços na WEB. O JDK deve ser 1.7+ e as demais instruções para executar o projeto estão na subseção seguinte. 

Passo a Passo

Após fazer o download do projeto disponível em https://github.com/reusogrupo9/ufmg.reuso.software ou cloná-lo com Git, importe-o no Eclipse e vá em Project Explorer->(Botão direito do mouse) ufmg.reuso.software-master->Properties->Java Build Path->Libraries. Edite a JRE para a versão no seu computador (deve ser 1.7+).

Após editar a JRE, clique em "Add External JAR" e adicione a commons-io-2.6.jar que pode ser baixada no link disponibilizado em http://ftp.unicamp.br/pub/apache//commons/io/binaries/commons-io-2.6-bin.zip.

Agora instale o plugin do Aspectj em Help->Eclipse->Marketplace-> buscar por "aspectj" e clique em instalar.

Executados os passos anteriores, agora basta executa-lo selecionando o projeto ufmg.reuso.software-master e apertanto o butão "run" da IDE do Eclipse.
