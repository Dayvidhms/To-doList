import { ref } from 'vue'
import { api } from 'src/boot/axios'


const useQuadros = () => {
	const quadros = ref([])
	const lastSelectedQuadro = ref({})

	const fetchQuadros = async () => {
		const { data: quadrosResponse } = await api.get('/placas')
		quadros.value = quadrosResponse
	}

	const getQuadro = async (id) => {
		const { data: quadroResponse } = await api.get(`/placas/${id}`)
		lastSelectedQuadro.value = quadroResponse;
	}

	const deleteQuadro = async (id) => {
		await api.delete(`/placas/${id}`)
		await fetchQuadros()
	}

	const createQuadro = async (nome, cor) => {
		let body = {
			nome: nome,
			cor: cor,
			userId: 1
		}

		await api.post(`/placas`, body)
		await fetchQuadros()
	}

	return {
		quadros,
		lastSelectedQuadro,
		fetchQuadros,
		getQuadro,
		deleteQuadro,
		createQuadro,
	}
}

export default useQuadros