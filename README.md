# Blue-Horizon_Mobile

== INTEGRANTES DO PROJETO: ==
- Enrico do Nascimento Ferreira Galdino - rm552082 - 2TDSPH
- Eduardo Ferreira Silva de Jesus - rm98410 - 2TDSPN
- Leonardo Matheus Mariano Guedes da Silva - rm99824 - 2TDSPN
- Luiz Felipe dos Santos Tragl - rm99476 - 2TDSPB
- Pedro Henrique Chersoni Lins - rm99866 - 2TDSPW

COMO FUNCIONA O PROJETO:

1.Adicionar um Novo Item
Método: addItem

Este método é utilizado para adicionar um novo item à base de dados interna do aplicativo.

Exemplo: val newItem = Item(
    id = UUID.randomUUID().toString(),
    name = "Mar do Norte",
    pollutionLevel = 5,
    temperature = 15.2f,
    biodiversityIndex = 3.8f
)
itemViewModel.addItem(newItem)

Explicação

itemViewModel.addItem(newItem): Chama o método addItem na ItemViewModel para salvar o novo item no banco de dados local.

Retorno:
A adição é feita localmente. O LiveData observando a lista de itens é atualizado automaticamente.


2. Obter Dados de um Item
Método: getItem
Este método é usado para recuperar os dados de um item específico baseado no ID.

exemplo: itemViewModel.getItem(itemId!!).observe(this) { item ->
    if (item != null) {
        binding.editTextName.setText(item.name)
        binding.editTextPollutionLevel.setText(item.pollutionLevel.toString())
        binding.editTextTemperature.setText(item.temperature.toString())
        binding.editTextBiodiversityIndex.setText(item.biodiversityIndex.toString())
    }
}

Explicação:

itemId: O ID do item a ser recuperado.
itemViewModel.getItem(itemId!!): Retorna um LiveData<Item> que é observado para atualizações.


Retorno:
O método retorna um LiveData<Item>. Quando os dados do item são carregados, eles são exibidos nos campos apropriados da UI.


3. Atualizar um Item Existente
Método: updateItem

Este método é usado para atualizar os dados de um item existente.

exemplo:
val updatedItem = Item(
    id = itemId!!,
    name = "Mar Báltico",
    pollutionLevel = 3,
    temperature = 12.8f,
    biodiversityIndex = 4.2f
)
itemViewModel.updateItem(itemId, updatedItem)


Explicação:
updatedItem: Um objeto da classe Item contendo os dados atualizados.
itemViewModel.updateItem(itemId!!, updatedItem): Chama o método updateItem na ItemViewModel para salvar as atualizações no banco de dados local.


Retorno:
A atualização é feita localmente. O LiveData observando a lista de itens é atualizado automaticamente.


4. Deletar um Item
Método: deleteItem

Este método é usado para deletar um item da base de dados interna.

Exemplo:
itemViewModel.deleteItem(itemId)

Explicação:

itemId: O ID do item a ser deletado.

itemViewModel.deleteItem(itemId): Chama o método deleteItem na ItemViewModel para remover o item do banco de dados local.


Retorno:
A deleção é feita localmente. O LiveData observando a lista de itens é atualizado automaticamente.
