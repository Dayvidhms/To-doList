<template>
	<div class="template-container">
		<div class="template-header">
			<div class="template-logo">
				Trellinho
			</div>
			<div class="template-header-buttons-container">
				<q-btn 
					push 
					color="white" 
					text-color="primary" 
					label="Quadros" 
					@click="redirectTo('/')"
				/>
			</div>
		</div>
		<div class="template-body">
			<slot></slot>
		</div>
	</div>
</template>

<script>
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
	name: 'MainTemplate',
	setup() {
		const router = useRouter()

		const redirectTo = (view) => {
			router.push({ path: view })
		}
		
		return {
			redirectTo
		}
	},
})
</script>

<style lang="scss" scoped>

.template-container {
	width: 100vw;
	height: 100vh;
}

.template-header {
	width: 100vw;
	height: 56px;
	background-color: rgb(21, 156, 246);
	box-shadow: rgb(0 0 0 / 22%) 0px 3px 10px;

	display: flex;
	justify-content: space-between;
}

.template-header-buttons-container {
	padding: 10px;
}

.template-logo {
	padding: 6px;
	padding-left: 16px;
	color: white;
	font-size: 36px;
	font-weight: bold;
	font-family: 'Style Script', cursive;
}

.template-body {
	width: 100vw;
	height: calc(100vh - 56px);
	overflow-y: auto;
	overflow-x: hidden;
}

</style>
