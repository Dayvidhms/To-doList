<template>
	<MainTemplate>
		<div class="listas-header">
			<q-btn
				push 
				color="white" 
				text-color="primary"  
				label="Criar nova lista" 
				@click="openCloseModal()"
			/>
		</div>
		<div class="listas-container">
			<Lista 
				v-for="lista in listas" 
				:id="lista.id" 
				:titulo="lista.titulo" 
				:deleteLista="deleteLista"
			/>
		</div>

		<q-dialog v-model="isOpen" persistent>	
			<q-card class="create-lista-form-container">
				<q-form
					@submit="onSubmit"
					@reset="onReset"
					class="q-gutter-md"
				>
					<h6>Criar nova lista:</h6>
					<q-input
						filled
						v-model="titulo"
						label="Nome da Lista"
						lazy-rules
						:rules="[ val => val && val.length > 0 || 'O nome da lista é obrigatória']"
					/>

					<div class="create-lista-form-buttons-container">
						<q-btn 
							label="Cancelar" 
							type="reset" 
							color="primary" 
							flat 
							class="q-ml-sm" 
							@click="openCloseModal()"
						/>
						<q-btn 
							label="Cadastrar" 
							type="submit" 
							color="primary"
						/>
					</div>
				</q-form>
			</q-card>
		</q-dialog>
	</MainTemplate>
</template>

<script>
import { defineComponent, onMounted, ref } from 'vue'
import MainTemplate from '../templates/MainTemplate.vue'
import { useRoute } from 'vue-router'
import useListas from 'src/hooks/useListas'
import Lista from 'src/components/Lista.vue'
import { Button } from 'spdata-design-system-components'

export default defineComponent({
	name: 'PageQuadro',
	components: {
		MainTemplate,
		Lista,
		Button
	},
	setup() {
		const { params: { id } } = useRoute()
		const { listas, fetchListas, createLista, deleteLista } = useListas(id)
		const isOpen = ref(false)
		const titulo = ref('')

		const openCloseModal = () => {
			isOpen.value = !isOpen.value
		}

		const onSubmit = async () => {
			await createLista(titulo.value)

			openCloseModal()
			onReset()
		}

		const onReset = () => {
			titulo.value = ''
		}

		onMounted(fetchListas)
		
		return {
			id,
			listas,
			titulo,
			isOpen,
			openCloseModal,
			onSubmit,
			onReset,
			deleteLista,
		}
	},
})
</script>
<style lang="scss" scoped>
.listas-header {
	padding-top: 16px;
	padding-left: 8px;
	padding-right: 8px;
	width: 100%;
	display: flex;
	justify-content: flex-end;
}

.listas-container {
  height: calc(100% - 52px);
  overflow-x: auto;
  overflow-y: hidden;
  white-space: nowrap;
}

.create-lista-form-container {
	padding: 36px;
	width: 50%;
}

.create-lista-form-buttons-container {
	width: 100%;
	margin: 0;
	display: flex;
	justify-content: flex-end;
	margin-top: 24px;


	& > button {
		margin-left: 8px;
	}
}
</style>
