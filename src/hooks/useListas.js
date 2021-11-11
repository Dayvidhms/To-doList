import { ref } from 'vue'
import { api } from 'src/boot/axios'

const useListas = (quadroId) => {
	const listas = ref([])

	const fetchListas = async () => {
		const { data } = await api.get('/listas')

		if (data != null) {
			listas.value = data.filter(item => item.placaId == quadroId)
		} else {
			listas.value = []
		}
	}

	// const getQuadro = async (id) => {
	// 	const { data: quadroResponse } = await api.get(`/placas/${id}`)
	// 	lastSelectedQuadro.value = quadroResponse;
	// }

	const deleteLista = async (id) => {
		await api.delete(`/listas/${id}`)
		await fetchListas()
	}

	const createLista = async (titulo) => {
		let body = {
			titulo,
			placaId: quadroId
		}

		await api.post(`/listas`, body)
		await fetchListas()
	}

	return {
		listas,
		fetchListas,
		createLista,
		deleteLista
	}
}

export default useListas