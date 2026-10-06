# Agi&Otta Bank — Trabalho 2 (MAF)

Aplicativo bancário desenvolvido em Kotlin com Jetpack Compose para o Trabalho 2 — Mínimo Aplicativo Funcional da disciplina de Desenvolvimento de Software Mobile.

## Integrantes

- Gabriel Arnold
- Adicionar nome do integrante 2
- Adicionar nome do integrante 3

## Funcionalidades

- Navegação real com `NavHost` e rotas nomeadas;
- barra de navegação inferior para Início, Cartões e Caixinhas;
- criação, listagem, remoção e detalhes de caixinhas;
- depósitos e retiradas com validação de saldo;
- histórico reativo de transações por caixinha;
- criação, listagem, remoção e detalhes de cartões;
- cálculo do limite disponível e percentual utilizado;
- lista de benefícios do clube e detalhes de cada benefício;
- dados mantidos em memória com `mutableStateListOf`.

## Telas

O aplicativo possui sete telas navegáveis:

1. Início;
2. Lista de caixinhas;
3. Detalhes da caixinha;
4. Lista de cartões;
5. Detalhes do cartão;
6. Clube Agi&Otta;
7. Detalhes do benefício.

## Como executar

1. Clone este repositório.
2. Abra o projeto no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Selecione um emulador ou dispositivo Android com API 24 ou superior.
5. Execute o módulo `app`.

Também é possível gerar o APK de desenvolvimento com:

```bash
./gradlew assembleDebug
```

## Evolução do Trabalho 1 para o Trabalho 2

No Trabalho 1, as telas possuíam dados fixos e a troca entre as áreas era controlada por uma variável local. Os botões serviam principalmente para representar a interface proposta.

No Trabalho 2, a navegação foi centralizada em `AppNavigation.kt` com `NavHost`, `NavController` e o objeto `Rotas`. As listas de caixinhas e cartões passaram a ser reativas e permitem adicionar e remover itens durante a execução. Cada item também abre sua própria tela de detalhes por meio do ID enviado na rota.

## Decisões de organização

- `navigation/Rotas.kt`: concentra os nomes e a montagem das rotas com argumentos;
- `navigation/AppNavigation.kt`: concentra o `NavHost` e conecta todas as telas;
- `state/AppState.kt`: mantém as listas reativas e as operações de negócio;
- `components/CaixinhaModel.kt` e `CartaoModel.kt`: contêm as classes que representam os dados;
- `screens/`: contém os componentes visuais de cada área.

O estado foi criado uma vez no `MainActivity` e enviado para a navegação. Assim, os dados não desaparecem quando o usuário troca de tela. Eles ainda são perdidos ao fechar o aplicativo, comportamento permitido nesta etapa do trabalho.

## Complexidade adicional da tela de detalhes

A tela de detalhes da caixinha não apenas repete os campos do item. Ela permite depositar e retirar valores, impede retiradas maiores que o saldo, recalcula o total e registra cada operação em um histórico reativo.

A tela de detalhes do cartão também apresenta informações calculadas: limite disponível, barra de progresso e percentual do limite utilizado.

## Dificuldades e soluções

A principal dificuldade foi manter os dados corretos durante a navegação. A solução foi centralizar as listas no `AppState` e enviar somente o ID pela rota. A tela de detalhes usa esse ID para buscar o item correto.

Outra dificuldade foi impedir inconsistências nas operações financeiras. Foram adicionadas validações para aceitar somente valores positivos e impedir retiradas superiores ao saldo da caixinha.

## Evidências do processo

Antes da entrega, adicionar na pasta `docs/imagens` prints reais do aplicativo executando no emulador e inserir as imagens nesta seção. Os registros recomendados são:

1. tela do Trabalho 1 antes das alterações;
2. tela inicial com a navegação inferior;
3. formulário de criação de caixinha;
4. lista com a nova caixinha;
5. detalhes da caixinha com transações;
6. formulário e lista de cartões;
7. detalhes do cartão;
8. Clube e detalhes de um benefício.

Exemplo para inserir um print:

```markdown
![Lista de caixinhas](docs/imagens/lista-caixinhas.png)
```

## Checklist de entrega

- [x] Novo repositório criado;
- [x] README com descrição e instruções de execução;
- [x] sete telas navegáveis;
- [x] `NavHost` central e objeto `Rotas`;
- [x] `NavigationBar` funcional;
- [x] duas listas com `LazyColumn`, `Card` e `mutableStateListOf`;
- [x] inclusão e remoção nas duas listas;
- [x] duas telas de detalhes com passagem de ID;
- [x] `TopAppBar` com `popBackStack()`;
- [x] complexidade extra na tela de detalhes da caixinha;
- [ ] nomes de todos os integrantes revisados;
- [ ] prints ou vídeo do aplicativo adicionados à documentação;
- [ ] demonstração final testada no emulador do grupo.
