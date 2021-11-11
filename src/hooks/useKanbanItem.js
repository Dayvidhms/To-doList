import { ref } from 'vue'
import { api } from 'src/boot/axios'

const useKanbanItem = (listaId) => {
	const kanbanItems = ref([])

	const fetchKanbanItems = async () => {
		const { data } = await api.get('/cartao')

		if (data != null) {
			kanbanItems.value = data.filter(item => item.placaId == listaId)
		} else {
			kanbanItems.value = []
		}
	}

	const deleteKanbanItem = async (id) => {
		await api.delete(`/cartao/${id}`)
		await fetchKanbanItems()
	}

	const createKanbanItem = async (titulo, descricao) => {
		let body = {
			titulo,
			descricao,
			listaId
		}

		await api.post(`/cartao`, body)
		await fetchKanbanItems()
	}

	return {
		kanbanItems,
		fetchKanbanItems,
		createKanbanItem,
		deleteKanbanItem
	}
}

export default useKanbanItem