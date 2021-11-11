<template>
	<MainTemplate>
		<div class="quadros-header">
			Quadros
			<q-btn round color="primary" icon="add" @click="openCloseDialog()"/>
		</div>
		<div class="quadros-container">
			<Quadro 
				v-for="quadro in quadros"
				:key="quadro.id"
				:id="quadro.id"
				:text="quadro.nome"
				:color="quadro.cor"
				:deleteQuadro="deleteQuadro"
			/>
		</div>
		<q-dialog v-model="dialogOpened">	
			<q-card class="create-quadro-form-container">
				<q-form
					@submit="onSubmit"
					@reset="onReset"
					class="q-gutter-md"
				>
					<h6>Criar novo quadro:</h6>
					<q-input
						filled
						v-model="nome"
						label="Nome do quadro"
						lazy-rules
						:rules="[ val => val && val.length > 0 || 'O nome do quadro é obrigatório']"
					/>

					<q-color v-model="color" format-model="hex" no-header no-footer style="width: 180px"/>

					<div class="create-quadro-form-buttons-container">
						<q-btn 
							label="Cancelar" 
							type="reset" 
							color="primary" 
							flat 
							class="q-ml-sm" 
							@click="openCloseDialog()"
						/>
						<q-btn 
							label="Cadastrar" 
							type="submit" 
							color="primary"
							:loading="isCreatingNewQuadro"
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
import Quadro from 'components/Quadro.vue'
import useQuadros from 'src/hooks/useQuadros'

export default defineComponent({
  name: 'PageIndex',
  components: {
		MainTemplate,
		Quadro
  },
  setup () {
		const dialogOpened = ref(false)
		const nome = ref('')
		const color = ref('')
		const isCreatingNewQuadro = ref(false)
		const { quadros, fetchQuadros, deleteQuadro, createQuadro } = useQuadros()

		const openCloseDialog = () => {
			dialogOpened.value = !dialogOpened.value
		}

		const onSubmit = async () => {
			isCreatingNewQuadro.value = true
			await createQuadro(nome.value, color.value)
			isCreatingNewQuadro.value = false

			openCloseDialog()
			onReset()
		}

		const onReset = () => {
			nome.value = ''
			color.value = ''
		}

		onMounted(fetchQuadros)

		return {
			quadros,
			dialogOpened,
			nome,
			isCreatingNewQuadro,
			color,
			deleteQuadro,
			openCloseDialog,
			onSubmit,
			onReset,
		}
  }
})
</script>

<style lang="scss" scoped>
.quadros-header {
	padding-top: 24px;
	padding-left: 40px;
	padding-right: 24px;
	font-size: 28px;
	font-weight: bold;
	display: flex;
	justify-content: space-between;
}

.quadros-container {
	display: flex;
	flex-wrap: wrap;
	padding: 36px;
}

.create-quadro-form-container {
	padding: 36px;
	width: 50%;
}

.create-quadro-form-buttons-container {
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