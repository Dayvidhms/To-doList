<template>
	<div 
		class="quadro"
		:style="`background-color: ${color};`"
	>
		<div class="quadro-title" @click="redirectTo(`/quadro/${id}`)">
			{{ text }}
		</div>

		<q-btn flat round color="grey-4" icon="more_vert">
			<q-menu
				transition-show="jump-down"
				transition-hide="jump-up"
			>
				<q-list style="min-width: 100px">
					<q-item clickable @click="deleteQuadro(id)">
						<q-item-section class="quadro-menu-item">
							<q-icon name="delete" style="color: #bb2424"/>
							Deletar
						</q-item-section>
					</q-item>
				</q-list>
			</q-menu>
		</q-btn>
	</div>
</template>

<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
	props: {
		text: String,
		color: String,
		id: Number,
		deleteQuadro: Function
	},
	setup() {
		const router = useRouter()

		const redirectTo = (view) => {
			router.push({ path: view })
		}

		return {
			redirectTo,
		}
	},
})
</script>

<style lang="scss" scoped>
.quadro {
	height: 100px;
	width: 200px;
	padding: 8px;
	border-radius: 8px;
	margin: 4px 8px;
	box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
	transition: 0.3s;
	position: relative;

	& > .quadro-title {
		font-size: 16px;
		color:rgb(238, 238, 238);
		font-weight: bold;
		height: 80px;
		width: 180px;
	}

	&:hover, & > label:hover {
		cursor: pointer;
	}

	&:hover {
		background-color: rgba(0, 47, 68, 0.55) !important;
	}

	& > button {
		position: absolute;
		right: 0;
		top: 0;
	}
}

.quadro-menu-item {
	display: flex; 
	flex-direction: row;
	align-items: center;
	justify-content: start;
	flex-wrap: nowrap;

	& > i {
		font-size: 20px;
		margin-right: 8px;
	}
}
</style>