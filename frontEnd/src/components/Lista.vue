<template>
  <div class="lista-container">
    <div class="lista-header">
      {{ titulo }}
      <q-btn flat round color="grey-8" icon="remove" @click="deleteLista(id)"/>
    </div>
    <div class="lista-body">
    </div>
  </div>
</template>
<script>
import { defineComponent } from 'vue'

export default defineComponent({
  props: {
    id: Number,
    titulo: String,
    deleteLista: Function
  },
  setup() {
    
  },
})
</script>

<style lang="scss" scoped>
.lista-container {
  width: 200px;
  background-color: rgb(218, 218, 218);
  height: calc(100% - 32px);
  margin: 16px 8px;
  border-radius: 8px;
  padding: 4px;
  white-space: nowrap;
  display: inline-block;
}

.lista-header {
  background-color: white;
  padding: 8px;
  width: 100%;
  border-radius: 8px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>