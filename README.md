# ProcessScheduler
  Para este trabalho, vocês devem se organizar em grupos de até 4 (quatro) pessoas. Cada grupo
deve então implementar um escalonador de tarefas para Time Sharing em uma máquina com
um único processador, criando assim um sistema simples de multiprogramação. A linguagem
usada na construção do escalonador deve ser Java.
  A máquina foi criada para rodar pequenos programas, em que cada processo pode contar,
no máximo, com 2 registradores de uso geral (além do Contador de Programa, como registrador
de uso especı́fico). Esses registradores são conhecidos internamente como X e Y. Além disso, o
processador para o qual vocês irão construir o escalonador é extremamente simples, possuindo
apenas 4 instruções:
  1. Atribuição: na forma X=<valor> ou Y=<valor>, onde <valor> é um número inteiro e
X e Y são os registradores de uso geral usados pelo processo (note a ausência de espaço
antes e depois do ‘=’).
  2. Entrada e saı́da: representada pela instrução E/S (que faz as vezes de uma chamada ao
sistema)
  3. Comando: a tarefa executada pela máquina, representada pela instrução COM
  4. Fim de programa: chamada com a única finalidade de remover o programa da memória,
executando a limpeza final. Representada pela instrução SAIDA
  Sabe-se que um processo pode estar em um dos seguintes estados: Executando, Pronto
ou Bloqueado. Enquanto há apenas um processo executando, pode haver vários prontos
para executar ou bloqueados, esperando alguma requisição de E/S se completar. Assim, sua
implementação deve contemplar uma lista de processos prontos e outra de bloqueados.
  Na ausência de um clock que comande a preempção, quem efetivamente rodará as instruções dos processos é o escalonador, que lê a instrução e a executa, funcionando como
um interpretador. Isso deixa o processo mais lento, naturalmente, mas garante o compartilhamento de tempo. Dentro do escalonador, a fila de processos prontos deve ser ordenada
conforme a prioridade do processo, enquanto que a fila de bloqueados é ordenada por ordem
de chegada.
  Seu sistema deve então possuir uma Tabela de Processos, representando todos os programas
que estão rodando simultaneamente. Cada linha da tabela deve conter uma referência ao Bloco
de Controle de Processo (BCP), sendo que este contém toda a informação necessária para que
o processo, após interrompido temporariamente, possa voltar a rodar. Ou seja, o BCP deve
conter, pelo menos, o Contador de Programa, o estado do processo (executando, pronto ou
bloqueado), sua prioridade, o estado atual de seus registradores de uso geral, uma referência à
região da memória em que está o código do programa executado (representado, por exemplo,
por um arranjo de Strings, que já é uma referência natural à memória em Java) e o nome do
programa.
  Vale notar que há somente o segmento de texto na memória (representado, por exemplo,
por um arranjo de 21 posições), em que é armazenado o código do programa. Por não conter
nem variáveis nem desvios (sub-rotinas etc), não há sentido em ter um segmento de dados e
da pilha. Além disso, lembre que, em java, qualquer instância a um objeto ou arranjo já é
uma referência a memória externa ao objeto em que essa instância está declarada.
Os programas executados serão dados na forma de arquivos-texto (ver 1.1). O escalonador
deve, então, carregar cada bloco de comandos (correspondente a um arquivo) na memória,
posicionando seu BCP na Tabela de Processos e na fila de processos prontos, seguindo sempre
sua ordem de prioridade. A prioridade de cada processo é, por sua vez, carregada a partir
de um arquivo intitulado “prioridades.txt”, que apresenta, a cada linha, a prioridade de cada
processo (quando estes são ordenados em ordem alfabética pelo nome de seus arquivos). Nesse
caso, quanto maior o valor do número contido no arquivo, maior a prioridade do processo.
  Como uma simplificação adicional, em vez de fatias de tempo, o escalonador irá permitir
que cada processo no estado executando rode no máximo n com comandos (ou seja, o quantum
será de n com comandos, em vez de uma quantia de milissegundos). Esse número de comandos
é uma simulação do tempo de ocupação do processador relacionado ao time-sharing, e deve ser
lido de um arquivo denominado “quantum.txt”. Esse arquivo conterá tão somente um inteiro.
  Uma vez tendo carregado todos os processos, o escalonador começa a rodá-los, usando o
seguinte algoritmo de prioridades (bastante semelhante ao usado no Linux):
  1. Inicialmente, distribua um número de créditos, a cada processo, igual à sua prioridade;
  2. Crie múltpilas filas, de acordo com número de créditos (do maior para o menor)
  3. Execute o primeiro processo da fila de maior prioridade
  4. Cada processo deve executar um número fixo de instruções (seu quantum):
    (a) Inicialmente recebe 1 quantum, e é suspenso
    (b) Então, o processo perde 2 crédito
    (c) O processo é reposicionado na fila de processos prontos e recebe 2 quantas para ser
utilizado quando for escalonado
    (d) Ao ser suspenso novamente perde 2 crédito e recebe 1 quantum a mais.
    (e) O primeiro da fila é posto a rodar (note que, dependendo da prioridade, pode ser
o mesmo processo de antes)
    (f) Quanto todos os processos estiverem com zero crédito, então os créditos são redistribuı́dos, conforme sua prioridade, voltando assim ao passo 1;
    (g) Na fila de prioridade 0, rode o algoritmo de roundo robin, considerando todos com
apenas 1 quantum
  5. Se, durante a execução de um quantum, o processo fizer uma entrada ou saı́da (instrução
“E/S”):
    (a) Ele será marcado como bloqueado, sendo então transferido para a lista de bloqueados, perde 2 crédito e recebe 1 quantum a mais;
    (b) A ele é atribuı́do um tempo de espera (inteiro representando quantos quanta ele
deve esperar para rodar novamente);
    (c) A cada processo que passe pelo estado executando (ou seja, ao sair desse estado),
esse tempo de espera é decrementado (note que todos na fila de bloqueados têm
seu tempo decrementado);
    (d) Cada processo fica bloqueado até que dois outros processos passem pelo estado
executando (esse é o tempo de espera), não importando quantos comandos cada
um executou (ou seja, se usou todo seu quantum ou não). Essa é uma simulação
do tempo de espera por um dispositivo de E/S (note que, uma vez que o tempo de
resposta de uma E/S é igual para todos, a lista de processos bloqueados acaba se
comportando como uma fila comum);
    (e) Quando o tempo de espera de algum processo bloqueado chegar a zero, este deve
receber o status de pronto, sendo então removido da fila de bloqueados e inserido
na fila de processos prontos, na posição correspondente ao seu número atual de
créditos. Note que ele não necessariamente ocasionará a preempção do processo em
execução, ou seja, o escalonador escolherá sempre o de maior número de créditos;
    (f) Quando esse processo for rodar novamente, deve reiniciar a partir da instrução
seguinte à E/S (uma vez que o PC é armazenado no BCP e este contém a instrução
seguinte à E/S). Atente que a instrução de E/S foi contada nas estatı́sticas do
sistema durante o momento anterior ao bloqueio (ver Seção 1.2).
  6. Se não houver nenhum processo em condição de ser executado (ex: existirem apenas
dois processos e ambos estiverem bloqueados), deve-se decrementar os tempos de espera
de todos os processos na fila de bloqueados, até que um chegue a zero, podendo então
ser rodado (como visto no item 5e).
  7. Ao encontrar o comando SAIDA, o escalonador deve remover o processo em execução
da fila apropriada e da tabela de processos.
Vale lembrar que apenas um máximo de n com instruções (de qualquer um dos 4 tipos
definidos mais adiante) podem ser executadas por vez pelo processador quando o processo
