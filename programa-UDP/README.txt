Instruções para compilação e execução do programa:

1 - Abra o diretório tp1 e execute o comando "Makefile.sh" para compilar o pacote uws.

2 - Inicie o(s) servidor(es) através do comando "java uws.UDPServer"

3 - Execute o Proxy através do comando "java uws.Proxy" passando como argumento o(s) IP(s) do(s) servidore(s) iniciados

    Por exemplo:
        Servidor 192.168.2.101: "java uws.Proxy 192.168.2.101"
        Servidores 192.168.2.102 e 192.168.2.103: "java uws.Proxy 192.168.2.102 e 192.168.2.103"    

4 - Executa o cliente com o comando "java uws.UDPClient" passando como argumento o IP do proxy, nome da cidade ou latitude e longitude
    
    Por exemplo: Cidade Ipatinga e IP proxy 192.168.2.100
        Busca pelo nome da cidade: "java uws.UDPClient 192.168.2.100 Ipatinga"
        Busca pela latitude e longitude: "java uws.UDPClient 192.168.2.100 -19.4454901 -42.6707614"

5 - Para simular n requisições de um cliente poderá executar o comando "java uws.SimulaClientes" passando como argumento o número de requisições que deseja e o intervalo de tempo entre elas
    
    Por exemplo:
        Para simular 100 clientes fazendo requisições a cada meio segundo (500 ms): "java uws.SimulaClientes 100 500"
